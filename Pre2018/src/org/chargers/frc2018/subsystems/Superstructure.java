package org.chargers.frc2018.subsystems;

import java.util.ArrayList;

public class Superstructure extends Subsystem {
	
	private ArrayList<Subsystem> subsystems = new ArrayList<Subsystem>();
	private DriveTrain driveTrain = new DriveTrain();
	
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

}
