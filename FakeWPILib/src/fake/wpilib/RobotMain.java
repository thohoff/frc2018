package fake.wpilib;

import java.awt.Color;

import org.chargers.frc2018.Robot;
import org.usfirst.frc.team5160.utils.path.FalconLinePlot;
import org.usfirst.frc.team5160.utils.path.Path;
import org.usfirst.frc.team5160.utils.path.Point;

import edu.wpi.first.wpilibj.IterativeRobot;

public class RobotMain {
	
	public static FalconLinePlot fig2 = new FalconLinePlot(new double[][]{{0,0}});

	public static void main(String[] args) throws InterruptedException{
		
		
		
		PursuitMain.makeGraph(fig2);
		fig2.setFocusable(true);
		FalconLinePlot fig1 = new FalconLinePlot(new double[][]{{0,0}});
		Robot robot = new Robot();
		MetaRobot meta = new MetaRobot(robot, 0.95, 0.0,0.95 );
		
		robot.robotInit();
	//	robot.autonomousInit();
	//	meta.autoInit();
		drawRobot(meta.x, meta.y, meta.angle, Color.PINK);
		for(int i = 0; i < 15000; i++){
			if(i % 1 == 0){
				
				fig2.removeLastNode();
				drawRobot(meta.x, meta.y, meta.angle, Color.PINK);
				fig1.clearNodes();
				fig1.addData(toDoubles(meta.times.toArray()), toDoubles(meta.elevatorHeights.toArray()), Color.BLUE);
				fig1.updateUI();
			}
			meta.update();
			robot.teleopPeriodic();
			
			Thread.sleep(10);
				
		}
		robot.autonomousPeriodic();
		
		fig2.addData(toDoubles(meta.posX.toArray()), toDoubles(meta.posY.toArray()), Color.RED, Color.RED);
		
		
		
		fig2.updateUI();
		FalconLinePlot fig3 = new FalconLinePlot(toDoubles(meta.times.toArray()), toDoubles(meta.velocities.toArray()), Color.BLACK, Color.BLACK);
		//FalconLinePlot fig4 = new FalconLinePlot(toDoubles(meta.times.toArray()), toDoubles(meta.accelerations.toArray()), Color.BLACK, Color.BLACK);
		FalconLinePlot fig5 = new FalconLinePlot(toDoubles(meta.times.toArray()), toDoubles(meta.jerks.toArray()), Color.BLACK, Color.BLACK);
		fig3.setTitle("Velocity");
		//fig4.setTitle("Acceleration");
		fig5.setTitle("Jerk");
		System.out.println(meta.time + ", "+meta.report());		
	}
	
	public static double[] toDoubles(Object[] objs){
		double[] tmp = new double[objs.length];
		for(int i = 0; i < objs.length; i++){
			tmp[i] = (double) objs[i];
		}
		return tmp;
	}
	public static void drawRobot(double x, double y, double angle, Color c){
		double rad = Math.toRadians(angle);
		double length = 33;
		double width = 38;
		double[][] tmp = new double[][]{
			{x,y}, {x + width/2*Math.cos(rad), y+length/2*Math.sin(rad)}
		};
		
		double[][] tmp2 = new double[][]{
			{width/2*Math.cos(rad) - length/2*Math.sin(rad) + x, width/2*Math.sin(rad) + length/2*Math.cos(rad) + y},
			{-width/2*Math.cos(rad) - length/2*Math.sin(rad) + x, -width/2*Math.sin(rad) + length/2*Math.cos(rad) + y},
			{-width/2*Math.cos(rad) + length/2*Math.sin(rad) + x, -width/2*Math.sin(rad) - length/2*Math.cos(rad) + y},
			{width/2*Math.cos(rad) + length/2*Math.sin(rad) + x, width/2*Math.sin(rad) - length/2*Math.cos(rad) + y},
			{width/2*Math.cos(rad) - length/2*Math.sin(rad) + x, width/2*Math.sin(rad) + length/2*Math.cos(rad) + y}
		};
		
		//fig2.addData(tmp, c);
		fig2.addData(tmp2, c);
		fig2.updateUI();
	}
}
