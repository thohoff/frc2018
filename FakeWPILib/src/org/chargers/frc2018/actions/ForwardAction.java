package org.chargers.frc2018.actions;

import org.chargers.frc2018.Constants;
import org.chargers.frc2018.Robot;
import org.chargers.frc2018.subsystems.DriveTrain;
import org.usfirst.frc.team5160.utils.BasicPID;
import org.usfirst.frc.team5160.utils.RMath;
import org.usfirst.frc.team5160.utils.path.Path;
import org.usfirst.frc.team5160.utils.path.Point;
import org.usfirst.frc.team5160.utils.path.PursuitController;

import edu.wpi.first.wpilibj.drive.MecanumDrive;

public class ForwardAction extends Action{
	
	private DriveTrain dt;
	public ForwardAction(){
	}

	@Override
	public boolean isFinished() {
		return false;
	}

	@Override
	public void start() {
		dt = Robot.superstructure.driveTrain;
	}

	@Override
	public void update() {
		dt.mecanumDrive(0.7, 0,0);
	}

	@Override
	public void stop() {
		dt.mecanumDrive(0, 0, 0);
	}
	
	public static double limit(double x){
		if(x > 0.3){
			return 0.3;
		}
		if(x < -0.3){
			return -0.3;
		}
		return x;
	}
	
}
