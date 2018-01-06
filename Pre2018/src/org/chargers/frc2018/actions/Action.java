package org.chargers.frc2018.actions;

public abstract class Action {
	
	private boolean hasStarted = false;
	
	public abstract boolean isFinished();
	public abstract void start();
	public abstract void update();
	public abstract void stop();
	
	public boolean hasStarted(){
		return hasStarted;
	}
	
	public boolean canCall(){
		return (hasStarted == false) || (this.isFinished() == false);
	}
	
	public void call(){
		if(hasStarted == false){
			this.start();
		}
		else{
			this.update();
		}
	}
	
}
