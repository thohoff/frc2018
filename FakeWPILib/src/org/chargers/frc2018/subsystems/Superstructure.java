package org.chargers.frc2018.subsystems;

import java.util.ArrayList;

import org.chargers.frc2018.actions.Action;
import org.chargers.frc2018.actions.LeftSwitchCenter;
import org.chargers.frc2018.actions.LeftSwitchLeft;
import org.chargers.frc2018.actions.LeftSwitchRight;
import org.chargers.frc2018.actions.PathDrive;
import org.chargers.frc2018.actions.RightSwitchCenter;
import org.chargers.frc2018.actions.RightSwitchLeft;
import org.chargers.frc2018.actions.RightSwitchRight;
import org.chargers.frc2018.actions.ThreeCubeAutoLeft;
import org.chargers.frc2018.actions.ThreeCubeAutoRight;
import org.usfirst.frc.team5160.utils.path.Path;
import org.usfirst.frc.team5160.utils.path.Point;

import fake.wpilib.MetaRobot;



public class Superstructure extends Subsystem {
	
	public enum StartingPosition {
		LEFT, RIGHT, CENTER;	
	};
	
	public enum Priority {
		NONE, SCALE, SWITCH;
	}
	
	private ArrayList<Subsystem> subsystems = new ArrayList<Subsystem>();
	public static DriveTrain driveTrain = new DriveTrain();
	public static Elevator elevator = new Elevator();
	public static Intake intake = new Intake();
	private Action autoMode = null;
	
	private StartingPosition startingPosition = StartingPosition.LEFT;
	private Priority priority = Priority.NONE;		
	
	public Superstructure(){
		subsystems.add(driveTrain);
		subsystems.add(elevator);
		subsystems.add(intake);
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
		String gameData = "LLL";
        this.autoMode = new RightSwitchLeft();
		char low = gameData.charAt(0);
		char high = gameData.charAt(1);
		
		if(startingPosition == StartingPosition.LEFT){
			driveTrain.setPosition(55, 18);
			if(low == 'L' && high == 'L'){
				if(priority == Priority.SWITCH){
					this.autoMode = new LeftSwitchLeft();
				}
				else{
					this.autoMode = new ThreeCubeAutoLeft();
				}
			}
			else if(high == 'L'){
				this.autoMode = new ThreeCubeAutoLeft();
			}
			else if(low == 'L'){
				this.autoMode = new LeftSwitchLeft();
			}
			else if(low == 'R' ){
				this.autoMode = new RightSwitchLeft();
			}
			
		}
		else if(startingPosition == StartingPosition.CENTER){
			driveTrain.setPosition(180, 18);
			if(low == 'L'){
				this.autoMode = new LeftSwitchCenter();
			}
			else if(low == 'R' ){
				this.autoMode = new RightSwitchCenter();
			}
			else{
				
			}
		}
		else if(startingPosition == StartingPosition.RIGHT){
			driveTrain.setPosition(280, 18);
			if(low == 'R' && high == 'R'){
				if(priority == Priority.SWITCH){
					this.autoMode = new RightSwitchRight();
				}
				else{
					this.autoMode = new ThreeCubeAutoRight();
				}
			}
			else if(high == 'R'){
				this.autoMode = new ThreeCubeAutoRight();
			}
			else if(low == 'R'){
				this.autoMode = new RightSwitchRight();
			}
			else if(low == 'L' ){
				this.autoMode = new LeftSwitchRight();
			}
		}
		
		
		for(Subsystem s : subsystems){
			s.autoInit();
		}
		
		executeAutoAction();
		MetaRobot.x = driveTrain.getPositionX();
		MetaRobot.y = driveTrain.getPositionY();
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
