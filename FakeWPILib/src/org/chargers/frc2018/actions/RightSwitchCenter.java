package org.chargers.frc2018.actions;

import java.awt.Color;

import org.chargers.frc2018.Constants;
import org.chargers.frc2018.subsystems.Elevator.ELEVATOR_STATE;
import org.usfirst.frc.team5160.utils.path.FalconLinePlot;
import org.usfirst.frc.team5160.utils.path.Path;
import org.usfirst.frc.team5160.utils.path.Point;


public class RightSwitchCenter extends ActionGroup{
	public RightSwitchCenter(){
		double scoreBuffer = Constants.kCenterToFrontBumperDistance/3.0;
		double cubeBuffer = Constants.kCenterToIntakeDistance - Constants.kAutoCubeIntakeDistance;
	
		Point[] fromMiddleToRightSwitch = {
				//new Point(180,robotLength/2.0),
				new Point(180,36), new Point(230,65), new Point(208, 150 - scoreBuffer)
		};	
		
		Point[] fromRightSwitchToCube = {
				//new Point(230, 150 - robotLength/2.0), 
				new Point(210, 90) , new Point(180, 150-cubeBuffer*2)
		};
		
		Point[] fromRightSwitchToCube2 = {
				//new Point(230, 150 - robotLength/2.0), 
				 new Point(150, 140-cubeBuffer*2)
		};
		
		Point[] fromCubeToExchange = {
				new Point(135, scoreBuffer*2)
		};
		
		ParallelAction tmp = new ParallelAction(false)
	    		 .addAction(new PathDriveDynamic(fromMiddleToRightSwitch, false).setPower(0.65), 6)
	    		 .addAction(new ElevatorAction(ELEVATOR_STATE.SWITCH), 6);
	     
		 this.addAction(tmp);
		 this.addAction(new TimedAction(new TurnAction(90), 0.5) );	
		 this.addAction(new IntakeAction(0.7, 2));
		
		/*
		tmp = new ParallelAction(false)
	    		 .addAction(new PathDriveDynamic(fromRightSwitchToCube, true).setPower(0.65), 6)
	    		 .addAction(new ElevatorAction(ELEVATOR_STATE.ZERO), 6);
	     
		this.addAction(tmp);
		
		this.addAction(new TimedAction(new PathDriveDynamic(fromCubeToExchange, false), 3));
		
		this.addAction(new TimedAction(new PathDriveDynamic(fromRightSwitchToCube2, true), 3));
		this.addAction(new TimedAction(new PathDriveDynamic(fromCubeToExchange, false), 3));
		
		this.addAction(new TimedAction(new PathDriveDynamic(fromRightSwitchToCube2, true), 3));
		this.addAction(new TimedAction(new PathDriveDynamic(fromCubeToExchange, false), 3));
		
		this.addAction(new TimedAction(new PathDriveDynamic(fromRightSwitchToCube2, true), 3));
		this.addAction(new TimedAction(new PathDriveDynamic(fromCubeToExchange, false), 3));
		*/
		 
		 

			
	}
	
}
