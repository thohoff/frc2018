package org.chargers.frc2018.actions;

import java.awt.Color;

import org.chargers.frc2018.Constants;
import org.usfirst.frc.team5160.utils.path.FalconLinePlot;
import org.usfirst.frc.team5160.utils.path.Path;
import org.usfirst.frc.team5160.utils.path.Point;


public class Forward extends ActionGroup{
	public Forward(){
			
		this.addAction(new TimedAction(new ForwardAction(), 1.5));
		 
		 

			
	}
	
}
