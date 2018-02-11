package edu.wpi.first.wpilibj;

public class Timer {
	private long startTime = 0;
	public double get() {
		return 0.01;
	}

	public void start() {
		startTime = System.currentTimeMillis();
	}

	public void reset() {
		startTime = System.currentTimeMillis();		
	}


}
