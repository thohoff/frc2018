package org.chargers.frc2018.actions;

import java.awt.Color;

import org.usfirst.frc.team5160.utils.path.FalconLinePlot;
import org.usfirst.frc.team5160.utils.path.Path;
import org.usfirst.frc.team5160.utils.path.Point;


public class LeftSwitchLeft extends ActionGroup{
	public LeftSwitchLeft(){
		double robotLength = 34;
	
		Point[] fromLeftToLeftSwitch = {
			//new Point(55,robotLength/2.0), 
			new Point(55,36), new Point(30,80),  new Point(30, 130), new Point(60, 170), new Point(90 - robotLength/2.0, 170)
		};
			
			
		this.addAction(new PathDriveDynamic(fromLeftToLeftSwitch, false));
		
		for(int i = 0; i< 50; i++){
			this.addAction(new TimedAction(new Nothing(), 0.0));
		}
		 
		 

			
	}
	
}
