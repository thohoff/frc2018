package org.chargers.frc2018.actions;

import java.awt.Color;

import org.usfirst.frc.team5160.utils.path.FalconLinePlot;
import org.usfirst.frc.team5160.utils.path.Path;
import org.usfirst.frc.team5160.utils.path.Point;


public class ThreeCubeAutoLeft extends ActionGroup{
	public ThreeCubeAutoLeft(){
		double robotStartX = 48;
		double robotLength = 34;
		
		Point[] fromLeftToScale = {
				//new Point(55,robotLength/2.0), 
				new Point(55,36), new Point(48,200),new Point(85, 270 - robotLength/2.0), new Point(85, 300 - robotLength/2.0)
			};
			 
			Point[] fromLeftScaleToCube = {
				 //new Point(85,300 - robotLength/2.0),
				 new Point(90,200 + robotLength/2.0)
			};
			
			Point[] fromCubeToLeftScale = {
					 //new Point(90,203 + robotLength/2.0), 
					 new Point(85,300 - robotLength/2.0)
			};
			
			Point[] fromLeftScaleToCube2 = {
				 //new Point(85,300 - robotLength/2.0), 
				 new Point(85,300 - robotLength), new Point(120,200 + robotLength), new Point(120,200 + robotLength/2.0)
			};
				
			Point[] fromCubeToLeftScale2 = {
					 //new Point(120,203 + robotLength/2.0),
					 new Point(120,200 + robotLength), new Point(85,300 - robotLength), new Point(85,300 - robotLength/2.0)
			};
		  	
			

			Point[] fromLeftScaleToCube3 = {
				 //new Point(85,300 - robotLength/2.0),
				 new Point(85,300 - robotLength), new Point(150,200 + robotLength), new Point(150,200 + robotLength/2.0)
			};
				
			Point[] fromCubeToLeftScale3 = {
					 //new Point(150,203 + robotLength/2.0),
					 new Point(150,200 + robotLength), new Point(85,300 - robotLength), new Point(85,303 - robotLength/2.0)
			};

		 
		 this.addAction(new PathDriveDynamic(fromLeftToScale, false));
		 this.addAction(new TimedAction(new TurnAction(90), 0.5) );	
			
		 this.addAction(new PathDriveDynamic(fromLeftScaleToCube, true));
		 this.addAction(new TimedAction(new TurnAction(90), 0.5) );	
			
		 this.addAction(new PathDriveDynamic(fromCubeToLeftScale, false));
		 this.addAction(new TimedAction(new TurnAction(90), 0.5) );	
			
		 
		 this.addAction(new PathDriveDynamic(fromLeftScaleToCube2, true));
		 this.addAction(new TimedAction(new TurnAction(90), 0.5) );	
			
		 

		 
		 this.addAction(new PathDriveDynamic(fromCubeToLeftScale2, false));
		 this.addAction(new TimedAction(new TurnAction(90), 0.5) );	
			

		
		 this.addAction(new PathDriveDynamic(fromLeftScaleToCube3, true));
		 this.addAction(new TimedAction(new TurnAction(90), 0.5) );	
			

		 
		 this.addAction(new PathDriveDynamic(fromCubeToLeftScale3, false));
		 this.addAction(new TimedAction(new TurnAction(90), 0.5) );
			
			
	}
	
}
