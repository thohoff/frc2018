package org.chargers.frc2018.actions;

import java.awt.Color;

import org.usfirst.frc.team5160.utils.path.FalconLinePlot;
import org.usfirst.frc.team5160.utils.path.Path;
import org.usfirst.frc.team5160.utils.path.Point;


public class RightSwitchCenter extends ActionGroup{
	public RightSwitchCenter(){
		double robotLength = 34;
	
		Point[] fromMiddleToRightSwitch = {
				//new Point(180,robotLength/2.0),
				new Point(180,36), new Point(230,80), new Point(230, 150 - robotLength/2.0)
		};	
		
		Point[] fromRightSwitchToCube = {
				//new Point(230, 150 - robotLength/2.0), 
				new Point(210, 110) , new Point(180, 150-robotLength)
		};
		
		Point[] fromRightSwitchToCube2 = {
				//new Point(230, 150 - robotLength/2.0), 
				 new Point(150, 140-robotLength)
		};
		
		Point[] fromCubeToExchange = {
				new Point(135, robotLength)
		};
		
		this.addAction(new TimedAction(new PathDriveDynamic(fromMiddleToRightSwitch, false),4));
		this.addAction(new TimedAction(new TurnAction(90), 1) );	
		
		this.addAction(new TimedAction(new PathDriveDynamic(fromRightSwitchToCube, true), 3));
		this.addAction(new TimedAction(new PathDriveDynamic(fromCubeToExchange, false), 3));
		
		this.addAction(new TimedAction(new PathDriveDynamic(fromRightSwitchToCube2, true), 3));
		this.addAction(new TimedAction(new PathDriveDynamic(fromCubeToExchange, false), 3));
		
		this.addAction(new TimedAction(new PathDriveDynamic(fromRightSwitchToCube2, true), 3));
		this.addAction(new TimedAction(new PathDriveDynamic(fromCubeToExchange, false), 3));
		
		this.addAction(new TimedAction(new PathDriveDynamic(fromRightSwitchToCube2, true), 3));
		this.addAction(new TimedAction(new PathDriveDynamic(fromCubeToExchange, false), 3));
		
		 
		 

			
	}
	
}
