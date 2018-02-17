package org.chargers.frc2018.actions;

import java.awt.Color;

import org.usfirst.frc.team5160.utils.path.FalconLinePlot;
import org.usfirst.frc.team5160.utils.path.Path;
import org.usfirst.frc.team5160.utils.path.Point;


public class ThreeCubeAutoRight extends ActionGroup{
	public ThreeCubeAutoRight(){
		double robotStartX = 48;
		double robotLength = 34;
		
		Point[] fromRightToScale = {
				//new Point(280,robotLength/2.0),
				new Point(280,36), new Point(336-48,200),new Point(336-85, 270 - robotLength/2.0), new Point(336-85, 300 - robotLength/2.0)
			};
		
		Point[] fromRightScaleToCube = {
				 //new Point(336-85,300 - robotLength/2.0),
				 new Point(336-90,200 + robotLength/2.0)
		};
			
			Point[] fromCubeToRightScale = {
					// new Point(336-90,203 + robotLength/2.0), 
					 new Point(336-85,303 - robotLength/2.0)
			};
		
			Point[] fromRightScaleToCube2 = {
					 //new Point(336-85,300 - robotLength/2.0), 
					 new Point(336-85,300 - robotLength), new Point(336-120,200 + robotLength), new Point(336 - 120,200 + robotLength/2.0)
				};
					
				Point[] fromCubeToRightScale2 = {
						 //new Point(336 - 120,203 + robotLength/2.0),
						 new Point(336 - 120,200 + robotLength), new Point(336 - 85,300 - robotLength), new Point(336 - 85,303 - robotLength/2.0)
				};
			  	
				

				Point[] fromRightScaleToCube3 = {
					 //new Point(336 - 85,300 - robotLength/2.0),
					 new Point(336 - 85,300 - robotLength), new Point(336 - 150,200 + robotLength), new Point(336- 150,200 + robotLength/2.0)
				};
					
				Point[] fromCubeToRightScale3 = {
						 //new Point(336- 150,203 + robotLength/2.0),
						 new Point(336- 150,200 + robotLength), new Point(336 - 85,300 - robotLength), new Point(336 - 85,303 - robotLength/2.0)
				};

		 
		 this.addAction(new PathDriveDynamic(fromRightToScale, false));

		 for(int i = 0; i< 50; i++){
		 this.addAction(new TimedAction(new Nothing(), 0.0));
		 }
		 this.addAction(new PathDriveDynamic(fromRightScaleToCube, true));
		 
		 
		 for(int i = 0; i< 50; i++){
			 this.addAction(new TimedAction(new Nothing(), 0.0));
		 }
		 this.addAction(new PathDriveDynamic(fromCubeToRightScale, false));
		

		 for(int i = 0; i< 50; i++){
			 this.addAction(new TimedAction(new Nothing(), 0.0));
		 }
		 
		 this.addAction(new PathDriveDynamic(fromRightScaleToCube2, true));
		
		 

		 for(int i = 0; i< 50; i++){
			 this.addAction(new TimedAction(new Nothing(), 0.0));
		 }
		 
		 this.addAction(new PathDriveDynamic(fromCubeToRightScale2, false));
		

		 for(int i = 0; i< 50; i++){
			 this.addAction(new TimedAction(new Nothing(), 0.0));
		 }

		 this.addAction(new PathDriveDynamic(fromRightScaleToCube3, true));
		

		 for(int i = 0; i< 50; i++){
			 this.addAction(new TimedAction(new Nothing(), 0.0));
		 }
		 
		 this.addAction(new PathDriveDynamic(fromCubeToRightScale3, false));
		
			
	}
	
}
