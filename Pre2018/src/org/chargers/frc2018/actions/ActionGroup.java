package org.chargers.frc2018.actions;

import java.util.ArrayList;

public class ActionGroup extends Action{
	private ArrayList<Action> actions = new ArrayList<Action>();
	
	protected void addAction(Action action){
		actions.add(new TimedAction(action, 20));
	}
	@Override
	public boolean isFinished() {
		return actions.size() == 0; //stop if there are no more action left
	}
	@Override 
	public void start() {
		
	}
	@Override
	public void update() {
		
	}
	@Override
	public void stop() {
		actions.get(0).stop();
	}
}
