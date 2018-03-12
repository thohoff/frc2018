package org.chargers.frc2018.subsystems;

import java.util.ArrayList;

import org.usfirst.frc.team5160.utils.path.Path;
import org.usfirst.frc.team5160.utils.path.Point;


public class Superstructure extends Subsystem {
	
	public enum StartingPosition {
		LEFT, RIGHT, CENTER;	
	};
	
	public enum Priority {
		NONE, SCALE, SWITCH;
	}
	
	//Robot subsystems
	private ArrayList<Subsystem> subsystems = new ArrayList<Subsystem>();
	public static DriveTrain driveTrain = new DriveTrain();
	
	//Auto configuration
	private StartingPosition startingPosition = StartingPosition.CENTER;
	private Priority priority = Priority.NONE;		
	
	public Superstructure(){
		subsystems.add(driveTrain);
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
		
	}
	
	private void executeAutoAction(){
		
	}
	
}
