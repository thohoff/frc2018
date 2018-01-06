package org.chargers.frc2018.actions;

import java.util.ArrayList;

public class ActionGroup {
	private ArrayList<Action> actions = new ArrayList<Action>();
	protected void addParallel(Action...actions){
		
	}
	protected void addSequential(Action action){
		actions.add(new TimedAction(action, 30));
	}
}
