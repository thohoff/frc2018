package org.chargers.frc2018.actions;

import java.util.ArrayList;

public class ParallelAction extends Action {
	private ArrayList<Action> actions;
	private boolean finishOnFirst;
	
	public ParallelAction(boolean finishOnFirst){
		this.actions = new ArrayList<Action>();
		this.finishOnFirst = finishOnFirst;
	}
	
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
		if(finishOnFirst){
			for (Action action : actions){
				if(action.canCall() == false){
					return true;
				}
			}
		}
		else{
			boolean allFinished = true;
			for (Action action : actions){
				allFinished = allFinished == true && action.canCall() == false;
			}
			return allFinished;
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
