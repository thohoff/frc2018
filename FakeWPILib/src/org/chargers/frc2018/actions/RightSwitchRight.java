package org.chargers.frc2018.actions;

import java.awt.Color;

import org.usfirst.frc.team5160.utils.path.FalconLinePlot;
import org.usfirst.frc.team5160.utils.path.Path;
import org.usfirst.frc.team5160.utils.path.Point;

import fake.wpilib.RobotMain;

public class RightSwitchRight extends ActionGroup{
	public RightSwitchRight(){
		double robotLength = 34;
	
		Point[] fromRightToRightSwitch = {
				new Point(280,robotLength/2.0), new Point(280,36), new Point(300,80),new Point(300, 130), new Point(280, 170), new Point(245+robotLength/2.0, 170)
		};	
		
		this.addAction(new PathDriveDynamic(fromRightToRightSwitch, false));
		
		for(int i = 0; i< 50; i++){
			this.addAction(new TimedAction(new Nothing(), 0.0));
		}
		 
		 

			
	}
	
}