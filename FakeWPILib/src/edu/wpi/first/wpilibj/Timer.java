package edu.wpi.first.wpilibj;

import fake.wpilib.MetaRobot;

public class Timer {
	private double startTime = 0;
	public double get() {
		if(MetaRobot.time - startTime < 0.01){
			return 0.01;
		}
		return 0.01;
		//return MetaRobot.time - startTime;
	}

	public void start() {
		startTime = (double) MetaRobot.time;
	}

	public void reset() {
		startTime = (double) MetaRobot.time;		
	}


}
