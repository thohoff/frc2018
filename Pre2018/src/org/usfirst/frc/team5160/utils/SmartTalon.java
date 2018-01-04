package org.usfirst.frc.team5160.utils;

import java.nio.channels.Pipe;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Encoder;

public class SmartTalon extends CANTalon{
	
	private BasicPID pid;
	private Encoder encoder = null; 
	private double encoderScale = 1;
	public SmartTalon(int deviceNumber) {
		super(deviceNumber);
		pid = new BasicPID(this);
		ensureCorrectMode();
	}
	
	public BasicPID getPID(){
		return pid;
	}
	
	public void setEncoder(int channelA, int channelB){
		encoder = new Encoder(channelA, channelB);
	}
	
	public void setEncoder(Encoder enc){
		encoder = enc;
	}
	
	public void setScale(double encoderScalingFactor){
		encoderScale = encoderScalingFactor;
	}
	
	public double getScale(){
		return encoderScale;
	}
	
	
	public void updatePID(){
		pid = new BasicPID(this);
	}
	
	public void updatePID(double p, double i, double d){
		this.setPID(p, i, d);
		updatePID();
	}
	
	public void updatePID(double p, double i, double d, double f){
		this.setPID(p, i, d);
		this.setF(f);
		updatePID();
	}
	
	public void zeroEncoder(){
		this.setPosition(0);
		if(encoder != null){
			encoder.reset();
		}
	}
	
	public void setVelocity(int velocity){
		ensureCorrectMode();
		if(encoder == null){
			this.set(pid.runPID(this.getEncVelocity()*encoderScale, velocity));
		}
		else{
			this.set(pid.runPID(encoder.getRate()*encoderScale, velocity));
		}
	}
	
	public void setPosition(int position){
		ensureCorrectMode();
		if(encoder == null){
			this.set(pid.runPID(this.getEncVelocity()*encoderScale, position, this.getEncVelocity()*encoderScale));
		}
		else{
			this.set(pid.runPID(encoder.getDistance()*encoderScale, position, encoder.getRate()*encoderScale));
		}
	}
	
	
	public void ensureCorrectMode(){
		if(this.getControlMode() != CANTalon.TalonControlMode.PercentVbus){
			this.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		}
	}
	public void smartHoming(){

	}
}
