package org.chargers.frc2018.actions;

import java.util.ArrayList;

public class ParallelAction extends Action {
	private ArrayList<Action> actions;
	private double timeLimit;
	private boolean finishOnFirst;
	
	public ParallelAction addAction(Action action){
		actions.add(action);
		return this;
	}
	
	public ParallelAction addAction(Action action, double timeLimit){
		actions.add(new TimedAction(action, timeLimit));
		return this;
	}
	
	@Override
	protected boolean isFinished() {
		boolean isFinished = false;
		for (Action action : actions){
			if(action.canCall() == false){
				return true;
			}
		}
		return false;
	}

	@Override
	protected void start() {
		for(Action action : actions){
			action.call();
		}
	}

	@Override
	protected void update() {
		for(Action action : actions){
			action.call();
		}
	}

	@Override
	protected void stop() {
		for(Action action : actions){
			action.callStop();
		}
	}

}
