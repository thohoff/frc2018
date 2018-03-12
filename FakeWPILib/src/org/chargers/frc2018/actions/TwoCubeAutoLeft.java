package org.chargers.frc2018.actions;

import java.awt.Color;

import org.chargers.frc2018.Constants;
import org.chargers.frc2018.subsystems.Elevator.ELEVATOR_STATE;
import org.usfirst.frc.team5160.utils.path.FalconLinePlot;
import org.usfirst.frc.team5160.utils.path.Path;
import org.usfirst.frc.team5160.utils.path.Point;


public class TwoCubeAutoLeft extends ActionGroup{
	public TwoCubeAutoLeft(){
		double scoreBuffer = Constants.kCenterToFrontBumperDistance;
		double cubeBuffer = Constants.kCenterToIntakeDistance - Constants.kAutoCubeIntakeDistance;
		
		Point[] toLeftScale = {
				//new Point(55,robotLength/2.0), 
				new Point(55,36), new Point(48,200),new Point(85, 270 - scoreBuffer), new Point(85, 300 - scoreBuffer)
		};
			 
		Point[] toRightScale = {
				
		};
		
	}
	
}
