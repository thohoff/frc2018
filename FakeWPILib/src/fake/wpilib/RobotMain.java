package fake.wpilib;

import java.awt.Color;

import org.chargers.frc2018.Robot;
import org.usfirst.frc.team5160.utils.path.FalconLinePlot;

import edu.wpi.first.wpilibj.IterativeRobot;

public class RobotMain {
	public static void main(String[] args){
		Robot robot = new Robot();
		MetaRobot meta = new MetaRobot(robot);
		
		robot.robotInit();
		robot.autonomousInit();
		
		for(int i = 0; i < 500; i++){
			meta.update();
			robot.autonomousPeriodic();
		}
		
		FalconLinePlot fig2 = new FalconLinePlot(toDoubles(meta.posX.toArray()), toDoubles(meta.posY.toArray()));
	  	
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
