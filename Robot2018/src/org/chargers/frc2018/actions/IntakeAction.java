package org.chargers.frc2018.actions;

import org.chargers.frc2018.Robot;

import edu.wpi.first.wpilibj.Timer;

public class IntakeAction extends Action {
	double power = 0;
	
	private double maxTime;
	private Timer timer;
	
	public IntakeAction(double power, double time) {
		this.maxTime = time;
		this.power = power;
		this.timer = new Timer();
	}
	
	@Override
	protected boolean isFinished() {
		return timer.get() > maxTime;
	}

	@Override
	protected void start() {
		timer.start();
		
	}

	@Override
	protected void update() {
		Robot.superstructure.intake.setPower(power*0.875);
	}

	@Override
	protected void stop() {
		Robot.superstructure.intake.setPower(0);
		
	}

	

}
