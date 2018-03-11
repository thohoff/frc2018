package org.chargers.frc2018.actions;

import org.chargers.frc2018.Robot;
import org.chargers.frc2018.subsystems.Elevator.ELEVATOR_STATE;

public class ElevatorAction extends Action {

	private ELEVATOR_STATE target;
	
	public ElevatorAction(ELEVATOR_STATE target) {
		this.target = target;
	}
	
	@Override
	protected boolean isFinished() {
		return Robot.superstructure.elevator.getCurrentState() == target;
	}

	@Override
	protected void start() {
		Robot.superstructure.elevator.setTarget(target);
	}

	@Override
	protected void update() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void stop() {
		// TODO Auto-generated method stub

	}

}
