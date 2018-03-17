package org.chargers.frc2018.subsystems;

import org.chargers.frc2018.OI;
import org.chargers.frc2018.RobotMap;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;

public class Intake extends Subsystem {
	public WPI_TalonSRX middleLeftMotor;
	public WPI_TalonSRX middleRightMotor; 
	public WPI_TalonSRX backLeftMotor;
	public WPI_TalonSRX backRightMotor;
	
	
	public AnalogInput ultrasonicSensor;
	public double InchesPerVolt = 40.31496; // Scaling factor Vcc/5120 per mm per the sensor specifications. Converted is ~40 inches per volt
	
	@Override
	public void robotInit(){
		middleLeftMotor = new WPI_TalonSRX(RobotMap.INTAKE_MIDDLE_LEFT_775);
		middleRightMotor = new WPI_TalonSRX(RobotMap.INTAKE_MIDDLE_RIGHT_775);
		backLeftMotor = new WPI_TalonSRX(RobotMap.INTAKE_BACK_LEFT_775);
		backRightMotor = new WPI_TalonSRX(RobotMap.INTAKE_BACK_RIGHT_775);
		
		configureMotor(middleLeftMotor);
		configureMotor(middleRightMotor);
		configureMotor(backLeftMotor);
		configureMotor(backRightMotor);
		ultrasonicSensor = new AnalogInput(3); //Ultrasonic sensor is in port 3
	}
	
	@Override
	public void teleopPeriodic(){
		this.setPowerSafe(OI.getIntakePower());
	}
	
	public void setPower(double power){
		middleLeftMotor.set(power);
		middleRightMotor.set(-power);
		backLeftMotor.set(power);
		backRightMotor.set(power);
	}
	public double getUltrasonicDistance(){
		return ultrasonicSensor.getVoltage() * InchesPerVolt;
	}
	public void setPowerSafe(double power){
		
		double minDistance = 3;
		double distance = ultrasonicSensor.getVoltage() * InchesPerVolt;
		
		if(distance <= minDistance){
			middleLeftMotor.set(0);
			middleRightMotor.set(0);
		}
		else{
			middleLeftMotor.set(power);
			middleRightMotor.set(-power);
			backLeftMotor.set(power);
			backRightMotor.set(power);
		}
	}
	private void configureMotor(TalonSRX motor){
		motor.configOpenloopRamp(0.2, 100);
		motor.enableCurrentLimit(true);
		motor.configContinuousCurrentLimit(35, 100);
		motor.configPeakCurrentDuration(300, 100);
		motor.configPeakCurrentLimit(60, 100);
		motor.setNeutralMode(NeutralMode.Brake);
	}
	
	
}
