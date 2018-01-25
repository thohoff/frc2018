package fake.wpilib;

import org.chargers.frc2018.Robot;
import org.usfirst.frc.team5160.utils.path.FalconLinePlot;

public class Main {
	public static void main(String[] args){
		Motor PRO = Motor.M_775_PRO;
		Gearbox box = new Gearbox(PRO, 1, 30, 200);
		System.out.println(box.getLoadInchesPerSecondDiameter(1));
		//box.plotPower(4);
		FalconLinePlot fig2 =  new FalconLinePlot(box.plotSpeedReduction(1));
		FalconLinePlot fig1 =  new FalconLinePlot(box.plotPowerLoad(1));
		//Robot robot = new Robot();
		//robot.robotInit();
		//runAuto(robot,10);
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
