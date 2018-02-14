package org.chargers.frc2018.actions;

import java.awt.Color;

import org.usfirst.frc.team5160.utils.path.FalconLinePlot;
import org.usfirst.frc.team5160.utils.path.Path;
import org.usfirst.frc.team5160.utils.path.Point;

import fake.wpilib.RobotMain;

public class ThreeCubeAuto extends ActionGroup{
	public ThreeCubeAuto(){
		double robotStartX = 48;
		double robotLength = 34;
		
		Point[] test = {
				new Point(robotStartX+18,robotLength/2.0), new Point(100, 100)};
		
		Point[] toScale = {
				//new Point(robotStartX+18,robotLength/2.0), 
				new Point(robotStartX+18,36), new Point(48,200),new Point(80, 270 - robotLength/2.0), new Point(80, 300 - robotLength/2.0)
			};
			 
			Point[] fromScaleToCube = {
				 //new Point(80,300 - robotLength/2.0), 
				 new Point(90,200 + robotLength/2.0)
			};
			
			Point[] fromCubeToScale = {
					// new Point(90,203 + robotLength/2.0), 
					 new Point(80,303 - robotLength/2.0)
			};
			
			Point[] fromScaleToCube2 = {
				 //new Point(80,300 - robotLength/2.0), 
				 new Point(80,300 - robotLength), new Point(120,200 + robotLength), new Point(120,200 + robotLength/2.0)
			};
				
			Point[] fromCubeToScale2 = {
					// new Point(120,203 + robotLength/2.0),
					 new Point(120,200 + robotLength), new Point(80,300 - robotLength), new Point(80,303 - robotLength/2.0)
			};
		  	
			

			Point[] fromScaleToCube3 = {
				 //new Point(80,300 - robotLength/2.0), 
				 new Point(80,300 - robotLength), new Point(150,200 + robotLength), new Point(150,200 + robotLength/2.0)
			};
				
			Point[] fromCubeToScale3 = {
					 //new Point(150,203 + robotLength/2.0),
					 new Point(150,200 + robotLength), new Point(80,300 - robotLength), new Point(80,303 - robotLength/2.0)
			};

		 
		 this.addAction(new PathDriveDynamic(toScale, false));

		 for(int i = 0; i< 50; i++){
		 this.addAction(new TimedAction(new Nothing(), 0.0));
		 }
		 this.addAction(new PathDriveDynamic(fromScaleToCube, true));
		 
		 
		 for(int i = 0; i< 50; i++){
			 this.addAction(new TimedAction(new Nothing(), 0.0));
		 }
		 this.addAction(new PathDriveDynamic(fromCubeToScale, false));
		

		 for(int i = 0; i< 50; i++){
			 this.addAction(new TimedAction(new Nothing(), 0.0));
		 }
		 
		 this.addAction(new PathDriveDynamic(fromScaleToCube2, true));
		 

		 for(int i = 0; i< 50; i++){
			 this.addAction(new TimedAction(new Nothing(), 0.0));
		 }
		 
		 this.addAction(new PathDriveDynamic(fromCubeToScale2, false));
		
		 
		 //this.addAction(new PathDrive(makePath(fromScaleToCube3), true));
		 
		 //this.addAction(new PathDrive(makePath(fromCubeToScale3), false));
		
			
	}
	
	private Path makePath(Point[] ps){
		Path path = new Path();
	  	ps = Path.InjectPoints(ps, 5);
	  	ps = Path.SmoothPoints(ps);
	  	ps = Path.SmoothPoints(ps);
	  	ps = Path.InjectPoints(ps, 5);
	  	ps = Path.SmoothPoints(ps);
	  	ps = Path.SmoothPoints(ps);
	  	path.addPoints(ps);
	  	RobotMain.fig2.addData(Path.ToDoubleArray(ps), Color.BLUE);
	  	return path;
	}
}
