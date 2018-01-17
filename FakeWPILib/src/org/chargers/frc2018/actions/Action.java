package org.chargers.frc2018.actions;

public abstract class Action {
	
	private boolean hasStarted = false;
	private boolean hasStopped = false;
	protected abstract boolean isFinished();
	protected abstract void start();
	protected abstract void update();
	protected abstract void stop();
	
	public boolean hasStarted(){
		return hasStarted;
	}
	
	public boolean hasStopped(){
		return hasStopped;
	}
	
	public boolean canCall(){
		return (hasStarted == false) || (this.isFinished() == false) && hasStopped == false;
	}
	
	public void call(){
		if(canCall() == true){
			if(hasStarted == false){
				this.start();
				hasStarted = true;
			}
			else{
				this.update();
			}
		}
		else if (hasStopped == false){
			callStop();
			hasStopped = true;
		}
	}
	
	public void callStop(){
		this.stop();
		hasStopped = true;
	}
	
}
