package org.chargers.frc2018.actions;

public class TimedAction extends Action {
	
	private Action action;
	private double startTime;
	private double time;
	
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
