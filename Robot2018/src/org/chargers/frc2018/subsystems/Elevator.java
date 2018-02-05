package org.chargers.frc2018.subsystems;

import org.chargers.frc2018.RobotMap;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;

public class Elevator extends Subsystem {
	private TalonSRX leftMotor; 
	private TalonSRX rightMotor;
	private DigitalInput upperLimitSwitch;
	private DigitalInput lowerLimitSwitch;
	private static final double TICK_TO_INCH = 2*Math.PI/1024.0; //1024 ticks per revolution, 2" diameter wheel, no other reduction
	
	@Override
	public void robotInit() {
		leftMotor = new TalonSRX(RobotMap.ELEVATOR_LEFT_775);
		rightMotor = new TalonSRX(RobotMap.ELEVATOR_RIGHT_775);
		
		configureMotor(leftMotor);
		configureMotor(rightMotor);
		
		upperLimitSwitch = new DigitalInput(RobotMap.UPPER_SWITCH_CHANNEL);
		lowerLimitSwitch = new DigitalInput(RobotMap.LOWER_SWITCH_CHANNEL);
	}
	
	public double getHeightInches(){
		return leftMotor.getSensorCollection().getQuadraturePosition() * TICK_TO_INCH;
	}
	
	public boolean atLowerLimit(){
		return lowerLimitSwitch.get();
	}
	
	public boolean atUpperLimit(){
		return upperLimitSwitch.get();
	}
	
	private void setPower(){
		
	}
	
	private void configureMotor(TalonSRX motor){
		motor.configOpenloopRamp(0.5, 100);
		motor.enableCurrentLimit(true);
		motor.configContinuousCurrentLimit(60, 100);
		motor.configPeakCurrentDuration(300, 100);
		motor.configPeakCurrentLimit(80, 100);
		motor.setNeutralMode(NeutralMode.Brake);
	}
	
}
