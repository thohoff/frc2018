package org.chargers.frc2018.actions;

import java.awt.Color;

import org.chargers.frc2018.Constants;
import org.chargers.frc2018.subsystems.Elevator.ELEVATOR_STATE;
import org.usfirst.frc.team5160.utils.path.FalconLinePlot;
import org.usfirst.frc.team5160.utils.path.Path;
import org.usfirst.frc.team5160.utils.path.Point;


public class LeftSwitchCenter extends ActionGroup{
	public LeftSwitchCenter(){
		double scoreBuffer = Constants.kCenterToFrontBumperDistance;
		double cubeBuffer = Constants.kCenterToIntakeDistance - Constants.kAutoCubeIntakeDistance;
	
		Point[] fromMiddleToLeftSwitch = {
			//new Point(180,robotLength/2.0), 
			new Point(180,36), new Point(100,80), new Point(100, 150 - scoreBuffer)
		};
		Point[] fromLeftSwitchToCube = {
				//new Point(100, 150 - robotLength/2.0), 
				new Point(120, 90) ,new Point(140, 150-cubeBuffer*2)
		};
		
		Point[] fromLeftSwitchToCube2 = {
				//new Point(100, 150 - robotLength/2.0), 
				new Point(150, 140-cubeBuffer*2)
		};
		
		Point[] fromCubeToExchange = {
				new Point(135, scoreBuffer*2)
		};
		
		//Drive and score in scale
		ParallelAction tmp = new ParallelAction(false)
	    		 .addAction(new PathDriveDynamic(fromMiddleToLeftSwitch, false).setPower(0.65), 6)
	    		 .addAction(new ElevatorAction(ELEVATOR_STATE.SWITCH), 6);
	     
		 this.addAction(tmp);
		 this.addAction(new TimedAction(new TurnAction(90), 0.5) );		
		
		/*
		tmp = new ParallelAction(false)
	    		 .addAction(new PathDriveDynamic(fromLeftSwitchToCube, true).setPower(0.65), 6)
	    		 .addAction(new ElevatorAction(ELEVATOR_STATE.ZERO), 6);
	     
		this.addAction(tmp);
		
		this.addAction(new TimedAction(new PathDriveDynamic(fromCubeToExchange, false), 3));
		
		this.addAction(new TimedAction(new PathDriveDynamic(fromLeftSwitchToCube2, true), 3));
		this.addAction(new TimedAction(new PathDriveDynamic(fromCubeToExchange, false), 3));
		
		this.addAction(new TimedAction(new PathDriveDynamic(fromLeftSwitchToCube2, true), 3));
		this.addAction(new TimedAction(new PathDriveDynamic(fromCubeToExchange, false), 3));
		
		this.addAction(new TimedAction(new PathDriveDynamic(fromLeftSwitchToCube2, true), 3));
		this.addAction(new TimedAction(new PathDriveDynamic(fromCubeToExchange, false), 3));
		*/
	
	}
	
}
