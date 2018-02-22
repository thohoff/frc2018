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
		
		this.addAction(new PathDriveDynamic(fromMiddleToRightSwitch, false));
		//this.addAction(new PathDriveDynamic(fromRightSwitchToCube, true));
		
		/*for(int i = 0; i< 50; i++){
			this.addAction(new TimedAction(new Nothing(), 0.0));
		}*/
		 
		 

			
	}
	
}
