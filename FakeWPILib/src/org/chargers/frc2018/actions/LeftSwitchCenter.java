package org.chargers.frc2018.actions;

import java.awt.Color;

import org.usfirst.frc.team5160.utils.path.FalconLinePlot;
import org.usfirst.frc.team5160.utils.path.Path;
import org.usfirst.frc.team5160.utils.path.Point;


public class LeftSwitchCenter extends ActionGroup{
	public LeftSwitchCenter(){
		double robotLength = 34;
	
		Point[] fromMiddleToLeftSwitch = {
			//new Point(180,robotLength/2.0), 
			new Point(180,36), new Point(100,80), new Point(100, 150 - robotLength/2.0)
		};
		Point[] fromLeftSwitchToCube = {
				//new Point(100, 150 - robotLength/2.0), 
				new Point(120, 110) ,new Point(140, 150-robotLength)
		};
		
		Point[] fromLeftSwitchToCube2 = {
				//new Point(100, 150 - robotLength/2.0), 
				new Point(150, 140-robotLength)
		};
		
		Point[] fromCubeToExchange = {
				new Point(135, robotLength)
		};
		
		this.addAction(new TimedAction(new PathDriveDynamic(fromMiddleToLeftSwitch, false),4));
		this.addAction(new TimedAction(new TurnAction(90), 1) );	
		
		this.addAction(new TimedAction(new PathDriveDynamic(fromLeftSwitchToCube, true), 3));
		this.addAction(new TimedAction(new PathDriveDynamic(fromCubeToExchange, false), 3));
		
		this.addAction(new TimedAction(new PathDriveDynamic(fromLeftSwitchToCube2, true), 3));
		this.addAction(new TimedAction(new PathDriveDynamic(fromCubeToExchange, false), 3));
		
		this.addAction(new TimedAction(new PathDriveDynamic(fromLeftSwitchToCube2, true), 3));
		this.addAction(new TimedAction(new PathDriveDynamic(fromCubeToExchange, false), 3));
		
		this.addAction(new TimedAction(new PathDriveDynamic(fromLeftSwitchToCube2, true), 3));
		this.addAction(new TimedAction(new PathDriveDynamic(fromCubeToExchange, false), 3));
		
	
	}
	
}
