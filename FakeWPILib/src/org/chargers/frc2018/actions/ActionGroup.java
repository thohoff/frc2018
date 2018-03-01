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
			actions.get(0).callStop();
			actions.remove(0); //Remove the first action if it cannot be called
		}
	}
	@Override
	public void update() {
		System.out.println(actions.size());
		if(actions.get(0).canCall()){
			actions.get(0).call();
		}
		else{
			actions.get(0).callStop();
			actions.remove(0);
		}
	}
	@Override
	public void stop() {
		if(actions.isEmpty() == false){
			actions.get(0).callStop();
			actions.remove(0); //Always remove an action once stopped
		}
	}
}
