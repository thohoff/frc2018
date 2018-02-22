package org.chargers.frc2018.actions;

import java.awt.Color;

import org.usfirst.frc.team5160.utils.path.FalconLinePlot;
import org.usfirst.frc.team5160.utils.path.Path;
import org.usfirst.frc.team5160.utils.path.Point;


public class RightSwitchLeft extends ActionGroup{
	public RightSwitchLeft(){
		double robotLength = 34;
	
		Point[] fromLeftToRightSwitch = {
				//new Point(55,robotLength/2.0), 
				new Point(55,48), new Point(230,90),  new Point(230, 130), new Point(230, 150 - robotLength/2.0)
		};	
		
		this.addAction(new PathDriveDynamic(fromLeftToRightSwitch, false));
		
		for(int i = 0; i< 50; i++){
			this.addAction(new TimedAction(new Nothing(), 0.0));
		}
		 
		 

			
	}
	
}
