package fake.wpilib;

import org.chargers.frc2018.Robot;
import org.usfirst.frc.team5160.utils.path.FalconLinePlot;

public class Main {
	public static void main(String[] args){
		Motor PRO = Motor.M_775_PRO;
		Gearbox box = new Gearbox(PRO, 1, 20, 14); //20-40 for the climber
		//box.plotPower(4);							//25-30 for a BAG Middle Intake -- Maybe 16-20 -- Double that for back intake
													//20-25 for a 775 Middle Intake -- Maybe 16 -- Double that for back intake
													//16-20 for a 775 Front Intake
													//16-20 for the elevator-- maybe as low as 10-12
		FalconLinePlot fig4 =  new FalconLinePlot(box.plotPowerLoad(4));
		FalconLinePlot fig3 =  new FalconLinePlot(box.plotAmperageReduction(4));
		FalconLinePlot fig2 =  new FalconLinePlot(box.plotSpeedReduction(4));
		FalconLinePlot fig1 =  new FalconLinePlot(box.plotSpeedPerAmperageReduction(4));
		//FalconLinePlot fig1 =  new FalconLinePlot(box.plotPowerReduction(4));
		fig1.setTitle("Speed per amp vs Reduction");
		fig2.setTitle("Speed vs Reduction");
		fig3.setTitle("Amperage vs Reduction");
		fig4.setTitle("Power vs Load");
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
