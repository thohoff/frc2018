package org.chargers.frc2018.actions;

import java.awt.Color;

import org.chargers.frc2018.Constants;
import org.chargers.frc2018.subsystems.Elevator.ELEVATOR_STATE;
import org.usfirst.frc.team5160.utils.path.FalconLinePlot;
import org.usfirst.frc.team5160.utils.path.Path;
import org.usfirst.frc.team5160.utils.path.Point;


public class RightSwitchRight extends ActionGroup{
	public RightSwitchRight(){
		double scoreBuffer = Constants.kCenterToFrontBumperDistance/2;
	
		Point[] fromRightToRightSwitch = {
				new Point(280,36), new Point(300,80),new Point(300, 130), new Point(280, 175), new Point(245+scoreBuffer, 175)
		};	
		
		ParallelAction tmp = new ParallelAction(false)
	    		 .addAction(new PathDriveDynamic(fromRightToRightSwitch, false).setPower(0.66), 6)
	    		 .addAction(new ElevatorAction(ELEVATOR_STATE.SWITCH), 6);
	     
		 this.addAction(tmp);
		 this.addAction(new TimedAction(new TurnAction(180), 3 ));	
		 //this.addAction(new TimedAction(new TurnAction(180), 1 ));	
		 this.addAction(new IntakeAction(1, 2));
			
	}
	
}
