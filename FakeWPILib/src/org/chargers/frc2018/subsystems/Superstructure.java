package org.chargers.frc2018.subsystems;

import java.util.ArrayList;

import org.chargers.frc2018.actions.Action;
import org.chargers.frc2018.actions.PathDrive;
import org.chargers.frc2018.actions.ThreeCubeAuto;
import org.usfirst.frc.team5160.utils.path.Path;
import org.usfirst.frc.team5160.utils.path.Point;

public class Superstructure extends Subsystem {
	
	private ArrayList<Subsystem> subsystems = new ArrayList<Subsystem>();
	public static DriveTrain driveTrain = new DriveTrain();
	public static Elevator elevator = new Elevator();
	private Action autoMode = null;
	
	public Superstructure(){
		subsystems.add(driveTrain);
		subsystems.add(elevator);
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
		this.autoMode = new ThreeCubeAuto();
				
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
			autoMode.callStop();
			autoMode = null;
		}
	}
	
	private void executeAutoAction(){
		if(autoMode != null){
			if(autoMode.canCall() == false){
				autoMode.callStop();
				autoMode = null;
			}
			else{
				autoMode.call();
			}
		}
	}
	
}
