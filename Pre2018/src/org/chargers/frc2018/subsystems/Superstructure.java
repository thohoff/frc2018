package org.chargers.frc2018.subsystems;

import java.util.ArrayList;

import org.chargers.frc2018.actions.Action;

public class Superstructure extends Subsystem {
	
	private ArrayList<Subsystem> subsystems = new ArrayList<Subsystem>();
	public static final DriveTrain driveTrain = new DriveTrain();
	private Action autoMode = null;
	
	public Superstructure(Action auto){
		subsystems.add(driveTrain);
	}
	
	public void setAutoAction(Action action){
		this.autoMode = action;
	}
	
	@Override
	public void robotInit() {
		for(Subsystem s : subsystems){
			s.robotInit();
		}
	}

	@Override
	public void autoInit() {
		for(Subsystem s : subsystems){
			s.autoInit();
		}
		
		executeAutoAction();
		
	}

	@Override
	public void autoPeriodic() {
		
		for(Subsystem s : subsystems){
			s.autoPeriodic();
		}
		
		executeAutoAction();
		
	}

	@Override
	public void teleopPeriodic() {
		for(Subsystem s : subsystems){
			s.teleopPeriodic();
		}
	}

	@Override
	public void stop() {
		for(Subsystem s : subsystems){
			s.stop();
		}
		if(autoMode != null){
			autoMode.stop();
			autoMode = null;
		}
	}
	
	private void executeAutoAction(){
		if(autoMode != null){
			if(autoMode.canCall() == false){
				autoMode.stop();
				autoMode = null;
			}
			else{
				autoMode.call();
			}
		}
	}
	
}
