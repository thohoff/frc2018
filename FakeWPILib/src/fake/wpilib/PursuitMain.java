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
				 new Point(90,200 + robotLength/2.0), new Point(85,300 - robotLength/2.0)
		};
		 
	  	
	  	
	  	Path path = new Path();
	  	Point[] ps = makePath(toScale);
	  	path.addPoints(ps);
	  	
	  	double rx = 48+18;
	  	double ry = 0;
	  	double rv = 0;
	  	double ra = 1.5;
	  	double rd = 0;
	  	double dt = 0.1;
	  	double rl = 24;
	  	double[][] points = new double[80][2]; 
	  	PursuitController pc = new PursuitController(path, rl, 15);
	  	for(int i = 0; i < 80; i++){
	  		points[i][0] = rx;
	  		points[i][1] = ry;
	  		double[] res = pc.getDrive(new Point(rx, ry, ra, rv,rd));
	  		
	  		double dx =  rv * Math.cos(ra) * dt;
	  		double dy =  rv * Math.sin(ra) * dt;
	  		
	  		rx = rx + dx;
	  		ry = ry + dy;
	  		ra = ra + rv / rl * Math.tan(res[1]) * dt;
	  		rv = rv + res[0] * dt;
	  		rd += Math.sqrt(dx*dx + dy*dy);
	  	}
	  	FalconLinePlot fig2 = new FalconLinePlot(Path.ToDoubleArray(ps), Color.RED, Color.RED);
	  	fig2.addData(points, Color.blue);
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
	
}
