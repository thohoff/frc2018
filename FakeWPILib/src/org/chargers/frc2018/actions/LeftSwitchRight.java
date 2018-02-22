package org.chargers.frc2018.actions;

import java.awt.Color;

import org.usfirst.frc.team5160.utils.path.FalconLinePlot;
import org.usfirst.frc.team5160.utils.path.Path;
import org.usfirst.frc.team5160.utils.path.Point;


public class LeftSwitchRight extends ActionGroup{
	public LeftSwitchRight(){
		double robotLength = 34;
	
		Point[] fromRightToLeftSwitch = {
				//new Point(280,robotLength/2.0), 
				new Point(280,48), new Point(100,80),new Point(100, 130), new Point(100, 150 - robotLength/2.0)
		};	
			
		this.addAction(new PathDriveDynamic(fromRightToLeftSwitch, false));
		
		for(int i = 0; i< 50; i++){
			this.addAction(new TimedAction(new Nothing(), 0.0));
		}
		 
		 

			
	}
	
}
