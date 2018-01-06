package org.chargers.frc2018.actions;

public interface Action {
	public boolean isFinished();
	public void start();
	public void update();
	public void stop();
}
