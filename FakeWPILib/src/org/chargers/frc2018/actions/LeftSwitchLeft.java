package org.chargers.frc2018.actions;

import java.awt.Color;

import org.chargers.frc2018.Constants;
import org.chargers.frc2018.subsystems.Elevator.ELEVATOR_STATE;
import org.usfirst.frc.team5160.utils.path.FalconLinePlot;
import org.usfirst.frc.team5160.utils.path.Path;
import org.usfirst.frc.team5160.utils.path.Point;


public class LeftSwitchLeft extends ActionGroup{
	public LeftSwitchLeft(){
		double scoreBuffer = Constants.kCenterToFrontBumperDistance * 1.25;
	
		Point[] fromLeftToLeftSwitch = {
			//new Point(55,robotLength/2.0), 
			new Point(55,36), new Point(30,80),  new Point(30, 130), new Point(60, 170), new Point(90 - scoreBuffer, 170)
		};
			
			
		//Drive and score in scale
	     ParallelAction tmp = new ParallelAction(false)
	    		 .addAction(new PathDriveDynamic(fromLeftToLeftSwitch, false).setPower(0.6), 6)
	    		 .addAction(new ElevatorAction(ELEVATOR_STATE.SWITCH), 6);
	     
		 this.addAction(tmp);
		 this.addAction(new TimedAction(new TurnAction(0), 1 ));	
		 this.addAction(new TimedAction(new TurnAction(0), 1 ));	
		 

			
	}
	
}
