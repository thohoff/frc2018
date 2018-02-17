package org.chargers.frc2018.subsystems;

import org.chargers.frc2018.OI;
import org.chargers.frc2018.RobotMap;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class Intake extends Subsystem {
	public WPI_TalonSRX middleLeftMotor;
	public WPI_TalonSRX middleRightMotor; 
	
	@Override
	public void robotInit(){
		middleLeftMotor = new WPI_TalonSRX(RobotMap.INTAKE_MIDDLE_LEFT_775);
		middleRightMotor = new WPI_TalonSRX(RobotMap.INTAKE_MIDDLE_RIGHT_775);
		
		configureMotor(middleLeftMotor);
		configureMotor(middleRightMotor);
	}
	
	@Override
	public void teleopPeriodic(){
		this.setPower(OI.getIntakePower());
	}
	
	public void setPower(double power){
		middleLeftMotor.set(power);
		middleRightMotor.set(-power);
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
