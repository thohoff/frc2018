package fake.wpilib;

import org.chargers.frc2018.Robot;

public class Main {
	public static void main(String[] args){
		Robot robot = new Robot();
		robot.robotInit();
		runAuto(robot,10);
	}
	
	public static void runAuto(Robot robot, int times){
		robot.autonomousInit();
		for(int i = 0; i < times; i++){
			robot.autonomousPeriodic();
		}
	}
	
	public static void runTele(Robot robot){
		for(;;){
			robot.teleopPeriodic();
		}
	}
	
}
