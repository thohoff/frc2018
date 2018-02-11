package edu.wpi.first.wpilibj;

import org.chargers.frc2018.Robot;

public class Encoder {

	
	private static final double TICK_TO_INCH = 6.0*Math.PI/256.0;//256 ticks per rev, 6 inch diameter wheels
	private static final double INCH_TO_TICK = 1.0 / TICK_TO_INCH;
	
	double value = 0;
	
	
	public Encoder(int lEFT_ENCODER_CHANNEL_A, int lEFT_ENCODER_CHANNEL_B, boolean b, String k4x) {
		// TODO Auto-generated constructor stub
	}

	public void reset() {
		value = 0;
	}

	public double get() {
		// TODO Auto-generated method stub
		return value * INCH_TO_TICK;
	}
	
	public void set(double d){
		value = d;
	}
	
	public void adjust(double d){
		value += d;
	}

}
