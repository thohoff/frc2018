package org.chargers.frc2018.actions;

import java.awt.Color;

import org.chargers.frc2018.Constants;
import org.usfirst.frc.team5160.utils.path.FalconLinePlot;
import org.usfirst.frc.team5160.utils.path.Path;
import org.usfirst.frc.team5160.utils.path.Point;


public class LeftSwitchRight extends ActionGroup{
	public LeftSwitchRight(){
		double scoreBuffer = Constants.kCenterToFrontBumperDistance * 1.25;
	
		Point[] fromRightToLeftSwitch = {
				//new Point(280,robotLength/2.0), 
				new Point(280,48), new Point(100,80),new Point(100, 130), new Point(100, 150 - scoreBuffer)
		};	
			
		this.addAction(new PathDriveDynamic(fromRightToLeftSwitch, false));
		 
		 

			
	}
	
}
