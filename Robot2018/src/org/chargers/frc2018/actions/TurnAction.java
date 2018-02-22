package org.chargers.frc2018.actions;

import org.chargers.frc2018.Robot;
import org.chargers.frc2018.subsystems.DriveTrain;
import org.usfirst.frc.team5160.utils.BasicPID;
import org.usfirst.frc.team5160.utils.RMath;
import org.usfirst.frc.team5160.utils.path.Path;
import org.usfirst.frc.team5160.utils.path.Point;
import org.usfirst.frc.team5160.utils.path.PursuitController;

import edu.wpi.first.wpilibj.drive.MecanumDrive;

public class TurnAction extends Action{
	
	private BasicPID pid;
	private DriveTrain dt;
	private double target;
	private int met = 0;
	public TurnAction(double target){
		this.target = target;
		pid = new BasicPID(0.07, 0.0001, 0.4);
	}

	@Override
	public boolean isFinished() {
		if(Math.abs(dt.getAngle()- target) < 2){
			met++;
			if(met > 5){
				return true;
			}
		}
		else{
			met = 0;
		}
		return false;
	}

	@Override
	public void start() {
		dt = Robot.superstructure.driveTrain;
	}

	@Override
	public void update() {
		dt.mecanumDrive(0, 0, -pid.runPID(dt.getAngle(), target));
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
