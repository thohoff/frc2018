package org.chargers.frc2018.actions;

import java.awt.Color;

import org.usfirst.frc.team5160.utils.path.FalconLinePlot;
import org.usfirst.frc.team5160.utils.path.Path;
import org.usfirst.frc.team5160.utils.path.Point;

import fake.wpilib.RobotMain;

public class LeftSwitchCenter extends ActionGroup{
	public LeftSwitchCenter(){
		double robotLength = 34;
	
		Point[] fromMiddleToLeftSwitch = {
			//new Point(180,robotLength/2.0), 
			new Point(180,36), new Point(100,80), new Point(100, 150 - robotLength/2.0)
		};
			
		this.addAction(new PathDriveDynamic(fromMiddleToLeftSwitch, false));
		
		for(int i = 0; i< 50; i++){
			this.addAction(new TimedAction(new Nothing(), 0.0));
		}
		 
		 

			
	}
	
}
