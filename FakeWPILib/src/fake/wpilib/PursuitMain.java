package fake.wpilib;
import java.awt.Color;
import java.util.ArrayList;

import org.usfirst.frc.team5160.utils.BasicPID;
import org.usfirst.frc.team5160.utils.path.FalconLinePlot;
import org.usfirst.frc.team5160.utils.path.Path;
import org.usfirst.frc.team5160.utils.path.Point;
import org.usfirst.frc.team5160.utils.path.PursuitController;

public class PursuitMain {
	
	public static void main(String[] args){
		double robotStartX = 48;
		double robotLength = 34;
		
		
		
		
		Point[] toScale = {
			new Point(robotStartX+18,robotLength/2.0), new Point(robotStartX+18,36), new Point(48,200),new Point(85, 270 - robotLength/2.0), new Point(85, 300 - robotLength/2.0)
		};
		 
		Point[] fromScaleToCube = {
			 new Point(85,300 - robotLength/2.0), new Point(90,200 + robotLength/2.0)
		};
		
		Point[] fromCubeToScale = {
				 new Point(90,203 + robotLength/2.0), new Point(85,303 - robotLength/2.0)
		};
		
		Point[] fromScaleToCube2 = {
			 new Point(85,300 - robotLength/2.0), new Point(85,300 - robotLength), new Point(120,200 + robotLength), new Point(120,200 + robotLength/2.0)
		};
			
		Point[] fromCubeToScale2 = {
				 new Point(120,203 + robotLength/2.0),new Point(120,200 + robotLength), new Point(85,300 - robotLength), new Point(85,303 - robotLength/2.0)
		};
	  	
		

		Point[] fromScaleToCube3 = {
			 new Point(85,300 - robotLength/2.0), new Point(85,300 - robotLength), new Point(150,200 + robotLength), new Point(150,200 + robotLength/2.0)
		};
			
		Point[] fromCubeToScale3 = {
				 new Point(150,203 + robotLength/2.0),new Point(150,200 + robotLength), new Point(85,300 - robotLength), new Point(85,303 - robotLength/2.0)
		};
		
	  	Path path = new Path();
	  	Point[] ps = makePath(toScale);
	  	path.addPoints(ps);
	  	
	  	
	  	
	  	FalconLinePlot fig2 = new FalconLinePlot(Path.ToDoubleArray(ps), Color.RED, Color.RED);
	  	
	  	path = new Path();
	  	ps = makePath(fromScaleToCube);
	  	path.addPoints(ps);
	  	
	  	fig2.addData(Path.ToDoubleArray(ps), Color.BLUE, Color.BLUE);
	  	
	  	path = new Path();
	  	ps = makePath(fromCubeToScale);
	  	path.addPoints(ps);
	  	
	  	fig2.addData(Path.ToDoubleArray(ps), Color.RED, Color.RED);
	  	
	  	
	  	path = new Path();
	  	ps = makePath(fromScaleToCube2);
	  	path.addPoints(ps);
	  	
	  	fig2.addData(Path.ToDoubleArray(ps), Color.BLUE, Color.BLUE);
	  	
	  	
	  	path = new Path();
	  	ps = makePath(fromCubeToScale2);
	  	path.addPoints(ps);
	  	
	  	fig2.addData(Path.ToDoubleArray(ps), Color.RED, Color.RED);
	  	
	  	
	  	path = new Path();
	  	ps = makePath(fromScaleToCube3);
	  	path.addPoints(ps);
	  	
	  	fig2.addData(Path.ToDoubleArray(ps), Color.BLUE, Color.BLUE);
	  	
	  	
	  	path = new Path();
	  	ps = makePath(fromCubeToScale3);
	  	path.addPoints(ps);
	  	
	  	fig2.addData(Path.ToDoubleArray(ps), Color.RED, Color.RED);
	  	
	  	makeGraph(fig2);
	  	
	}
	
	public static void makeGraph(FalconLinePlot fig2){
		double fieldLength = 264+36+36;
		double[][] field = new double[][]{
			{0, 300}, {0,30}, {36,0}, {fieldLength - 36, 0}, {fieldLength, 30}, {fieldLength, 300}
		};
		
		double[][] exchangeZone = new double[][]{
			{fieldLength/2.0 - 12, 0}, {fieldLength/2.0-12,36}, {fieldLength/2.0 - 12 -48,36}, {fieldLength/2.0 - 12 -48,0}
		};
		
		double[][] lowGoal = new double[][]{
			{85.25, 168-24}, {85.25, 168+24}, {fieldLength - 85.25, 168+24}, {fieldLength - 85.25, 168-24}, {85.25, 168-24}
		};
		
		double[][] highGoal = new double[][]{
			{72, 300}, {72, 300+48}, {fieldLength - 72, 300+48}, {fieldLength - 72, 300}, {72, 300}
		};
	  	
	  	fig2.addData(field, Color.BLACK);
	  	
	  	fig2.addData(exchangeZone, Color.BLACK);
	  	
	  	fig2.addData(lowGoal, Color.BLACK);
	  	
	  	fig2.addData(highGoal, Color.BLACK);
	  	
	  	for(int x = 85; x < 250; x+= 31){
	  		fig2.addData(buildBlock(x), Color.ORANGE);
	  	}
	}
	
	private static Point[] makePath(Point[] ps){
	  	ps = Path.InjectPoints(ps, 5);
	  	ps = Path.SmoothPoints(ps);
	  	ps = Path.SmoothPoints(ps);
	  	ps = Path.InjectPoints(ps, 5);
	  	ps = Path.SmoothPoints(ps);
	  	ps = Path.SmoothPoints(ps);
	  	return ps;
	}
	
	private static double[][] buildBlock(double x){
		double[][] tmp = new double[][]{
			{x, 168+24}, {x, 168+24+12}, {x+12, 168+24 + 12}, {x+12, 168+24}, {x, 168+24}
		};
		return tmp;
	}
	
	private static void doStuff(){
		double rx = 48+18;
	  	double ry = 0;
	  	double rv = 0;
	  	double ra = 1.5;
	  	double rd = 0;
	  	double dt = 0.1;
	  	double rl = 24;
	  	double[][] points = new double[8][2]; 
	  	PursuitController pc = new PursuitController(path, rl, 15);
	  	for(int i = 0; i < 8; i++){
	  		points[i][0] = rx;
	  		points[i][1] = ry;
	  		double[] res = pc.getDrive(new Point(rx, ry, Math.toDegrees(ra), rv,rd));
	  		
	  		double dx =  rv * Math.cos(ra) * dt;
	  		double dy =  rv * Math.sin(ra) * dt;
	  		
	  		rx = rx + dx;
	  		ry = ry + dy;
	  		ra = ra + rv / rl * Math.tan(res[1]) * dt;
	  		rv = rv + res[0] * dt;
	  		rd += Math.sqrt(dx*dx + dy*dy);
	  	}
	}
	
}
