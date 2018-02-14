package fake.wpilib;

import java.awt.Color;

import org.chargers.frc2018.Robot;
import org.usfirst.frc.team5160.utils.path.FalconLinePlot;
import org.usfirst.frc.team5160.utils.path.Path;
import org.usfirst.frc.team5160.utils.path.Point;

import edu.wpi.first.wpilibj.IterativeRobot;

public class ElevatorMain {
	

	public static void main(String[] args){
		Robot robot = new Robot();
		MetaRobot meta = new MetaRobot(robot, 0, 0, 0.97);
		robot.robotInit();
		meta.elevator.setTarget(100);
		for(int i = 0; i < 200; i++){
			meta.update();
			robot.teleopPeriodic();
		}
		
		meta.elevator.setTarget(0);
		for(int i = 0; i < 200; i++){
			meta.update();
			robot.teleopPeriodic();
		}
		FalconLinePlot fig2 = new FalconLinePlot(toDoubles(meta.times.toArray()), toDoubles(meta.elevatorHeights.toArray()), Color.RED, Color.RED);
		fig2.addData(toDoubles(meta.times.toArray()), toDoubles(meta.elevatorSpeeds.toArray()), Color.BLUE, Color.BLUE);
		fig2.addData(new double[]{0,6}, new double[]{0,0}, Color.BLACK);
		System.out.println(meta.time);		
	}
	
	public static double[] toDoubles(Object[] objs){
		double[] tmp = new double[objs.length];
		for(int i = 0; i < objs.length; i++){
			tmp[i] = (double) objs[i];
		}
		return tmp;
	}
	
}
