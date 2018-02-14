package com.ctre.phoenix.motorcontrol;

public class SensorCollection {
	double encoder = 0;
	private static final double TICK_TO_INCH = 2*Math.PI/1024.0; //1024 ticks per revolution, 2" diameter wheel, no other reduction
	private static final double INCH_TO_TICK = 1.0 / TICK_TO_INCH;
	public SensorCollection(double encoder) {
		this.encoder = encoder;
	}

	public double getQuadraturePosition() {
		// TODO Auto-generated method stub
		return encoder * INCH_TO_TICK;
	}

}
