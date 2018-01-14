package org.chargers.frc2018.actions;

public class TimedAction extends Action {
	
	private Action action;
	private double startTime;
	private double time;
	/**
	 * Executes and action, ending when time expires or when the action finishes
	 * @param action The action to execute
	 * @param time The maximum time to execute it, in seconds
	 */
	public TimedAction(Action action, double time){
		
	}
	
	@Override
	public boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void start() {
		action.start();
	}

	@Override
	public void update() {
		action.update();
	}

	@Override
	public void stop() {
		action.stop();
	}

}
