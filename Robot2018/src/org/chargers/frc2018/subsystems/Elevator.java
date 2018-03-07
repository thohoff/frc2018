package org.chargers.frc2018.subsystems;

import org.chargers.frc2018.OI;
import org.chargers.frc2018.RobotMap;
import org.usfirst.frc.team5160.utils.BasicPID;
import org.usfirst.frc.team5160.utils.TrajectoryPID;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;

public class Elevator extends Subsystem {
	public WPI_TalonSRX leftMotor; 
	public WPI_TalonSRX rightMotor;
	
	public TrajectoryPID pid;
	public static double p = 0.2, i = 0.0, d = 5, v = 0.6, a = 0.4;
	
	
	private DigitalInput upperLimitSwitch;
	private DigitalInput lowerLimitSwitch;
	private double targetHeight = 0;
	private double startHeight = 0;
	public double error = 0;
	private static final double TICK_TO_INCH = 2*Math.PI/1024.0; //1024 ticks per revolution, 2" diameter wheel, no other reduction
	
	@Override
	public void robotInit() {
		leftMotor = new WPI_TalonSRX(RobotMap.ELEVATOR_LEFT_775);
		rightMotor = new WPI_TalonSRX(RobotMap.ELEVATOR_RIGHT_775);
		
		configureMotor(leftMotor);
		configureMotor(rightMotor);
		
		upperLimitSwitch = new DigitalInput(RobotMap.UPPER_SWITCH_CHANNEL);
		lowerLimitSwitch = new DigitalInput(RobotMap.LOWER_SWITCH_CHANNEL);
		
		pid = new TrajectoryPID(p, i, d, v, a, 24);
	}
	
	@Override
	public void teleopPeriodic() {
		setPower(pid.runPID(getHeightInches(),startHeight, targetHeight));
		error += Math.abs(targetHeight - getHeightInches());
		if(getHeightInches() > targetHeight){
			error += Math.abs(targetHeight - getHeightInches()) *4;
		}
		this.setPower(OI.getElevatorPower()*0.75);

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
	
	private void setPower(double power){
		leftMotor.set(power);
		rightMotor.set(power);
	}
	
	
	public void setTarget(double target){
		startHeight = getHeightInches();
		if(target < 0){
			this.targetHeight = 0;
		}
		else{
			this.targetHeight = target;
		}
	}
	
	private void configureMotor(TalonSRX motor){
		motor.configOpenloopRamp(0.2, 100);
		motor.enableCurrentLimit(true);
		motor.configContinuousCurrentLimit(40, 100);
		motor.configPeakCurrentDuration(300, 100);
		motor.configPeakCurrentLimit(60, 100);
		motor.setNeutralMode(NeutralMode.Brake);
	}
	
}
