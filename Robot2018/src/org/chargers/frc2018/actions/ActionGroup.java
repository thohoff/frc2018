package org.chargers.frc2018.actions;

import java.util.ArrayList;

public class ActionGroup extends Action{
	private ArrayList<Action> actions = new ArrayList<Action>();
	
	public void addAction(Action action){
		actions.add(new TimedAction(action, 20));
	}
	@Override
	public boolean isFinished() {
		return actions.size() == 0; //stop if there are no more actions left
	}
	@Override 
	public void start() {
		if(actions.get(0).canCall()){
			actions.get(0).call();
		}
		else{
			actions.remove(0);
		}
	}
	@Override
	public void update() {
		if(actions.get(0).canCall()){
			actions.get(0).call();
		}
		else{
			actions.get(0).stop();
			actions.remove(0);
		}
	}
	@Override
	public void stop() {
		if(actions.isEmpty() == false){
			actions.get(0).stop();
		}
	}
}
