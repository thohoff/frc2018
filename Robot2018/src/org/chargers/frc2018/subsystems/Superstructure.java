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

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


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
	public static Elevator elevator = new Elevator();
	public static Intake intake = new Intake();
	private Action autoMode = null;
	private SendableChooser autoChooser;
	
	//Auto configuration
	private StartingPosition startingPosition = StartingPosition.CENTER;
	private Priority priority = Priority.SWITCH;		
	private long counter = 0;
	
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
		autoChooser = new SendableChooser();
		autoChooser.addDefault("Default", StartingPosition.LEFT);
		autoChooser.addObject("Left", StartingPosition.LEFT);
		autoChooser.addObject("Right", StartingPosition.RIGHT);
		autoChooser.addObject("Center", StartingPosition.CENTER);
		SmartDashboard.putData("Starting position", autoChooser);
		for(Subsystem s : subsystems){
			s.robotInit();
		}
		//UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
		//camera.setResolution(400, 300);
	}

	@Override
	public void autoInit() {
		this.startingPosition = (StartingPosition) autoChooser.getSelected();
		String gameData = DriverStation.getInstance().getGameSpecificMessage();//"LLR";DriverStation.getInstance().getGameSpecificMessage();
        this.autoMode = new RightSwitchCenter();
		char low = gameData.charAt(0);
		char high = gameData.charAt(1);
		// Select the correct autonomous mode based on the field configuration
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
	}

	@Override
	public void autoPeriodic() {
		for(Subsystem s : subsystems){
			s.autoPeriodic();
		}
		executeAutoAction();
		System.out.println(driveTrain.getPositionX() + ", "+ driveTrain.getPositionY() + " #### "+driveTrain.getAngle() + " ####");
		updateDashboard();
	}

	@Override
	public void teleopPeriodic() {
		for(Subsystem s : subsystems){
			s.teleopPeriodic();
		}
		updateDashboard();
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
	
	private void updateDashboard(){
		
		/*if(counter % 10 == 0){
			SmartDashboard.putNumber("Elevator height", elevator.getHeightInches());
			SmartDashboard.putBoolean("Elevator bottom", elevator.atLowerLimit());
			SmartDashboard.putBoolean("Elevator top", elevator.atUpperLimit());
			SmartDashboard.putNumber("Intake Ultrasonic", intake.getUltrasonicDistance());
			SmartDashboard.putNumber("DT left Encoder", driveTrain.leftEncoder.getDistance());
			SmartDashboard.putNumber("DT right Encoder", driveTrain.rightEncoder.getDistance());
			SmartDashboard.putNumber("DT angle", driveTrain.getAngle());
			SmartDashboard.putNumber("DT posX", driveTrain.getPositionX());
			SmartDashboard.putNumber("DT posY", driveTrain.getPositionY());
			SmartDashboard.updateValues();
			System.out.println(driveTrain.leftEncoder.get() + ", "+ driveTrain.rightEncoder.get());
		}
		counter++;*/
	
	}
	
}
