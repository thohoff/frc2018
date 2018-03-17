package org.chargers.frc2018.subsystems;

import org.chargers.frc2018.OI;
import org.chargers.frc2018.RobotMap;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;

public class Climber extends Subsystem {
	public WPI_TalonSRX climberLeftMotor;
	public WPI_TalonSRX climberRightMotor; 
	
	
	
	@Override
	public void robotInit(){
		climberLeftMotor = new WPI_TalonSRX(RobotMap.CLIMBER_LEFT_775);
		climberRightMotor = new WPI_TalonSRX(RobotMap.CLIMBER_RIGHT_775);
		
		configureMotor(climberLeftMotor);
		configureMotor(climberRightMotor);
	}
	
	@Override
	public void teleopPeriodic(){
	}
	
	public void setPower(double power){
		climberLeftMotor.set(power);
		climberRightMotor.set(power);
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
