package org.chargers.frc2018.actions;

import edu.wpi.first.wpilibj.Timer;

public class TimedAction extends Action {
	
	private Action action;
	private double startTime;
	private double maxTime;
	private Timer timer;
	/**
	 * Executes and action, ending when time expires or when the action finishes
	 * @param action The action to execute
	 * @param time The maximum time to execute it, in seconds
	 */
	public TimedAction(Action action, double maxTime){
		this.action = action;
		this.maxTime = maxTime;
		this.timer = new Timer();
	}
	
	@Override
	public boolean isFinished() {
		if(action.canCall() == false || timer.get() > maxTime){
			return true;
		}
		return false;
	}

	@Override
	public void start() {
		timer.start();
		action.call();
	}

	@Override
	public void update() {
		action.call();
	}

	@Override
	public void stop() {
		action.callStop();
	}

}
