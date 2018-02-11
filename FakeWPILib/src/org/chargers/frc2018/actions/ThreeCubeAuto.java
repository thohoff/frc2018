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
			new Point(robotStartX+18,robotLength/2.0), new Point(robotStartX+18,36), new Point(48,200),new Point(85, 260 - robotLength/2.0), new Point(85, 300 - robotLength/2.0)
		};
		 
		Point[] fromScaleToCube = {
			 new Point(85,300 - robotLength/2.0), new Point(90,200 + robotLength/2.0)
		};
		
		Point[] fromCubeToScale = {
				 new Point(90,200 + robotLength/2.0), new Point(85,300 - robotLength/2.0)
		};
		 
		 this.addAction(new PathDrive(makePath(toScale), false));

		// this.addAction(new PathDrive(makePath(fromScaleToCube), true));
		 
		 //this.addAction(new PathDrive(makePath(fromCubeToScale), false));
		
		 
			
	}
	
	private Path makePath(Point[] ps){
		Path path = new Path();
	  	ps = Path.InjectPoints(ps, 4);
	  	ps = Path.SmoothPoints(ps);
	  	ps = Path.InjectPoints(ps, 4);
	  	ps = Path.SmoothPoints(ps);
	  	path.addPoints(ps);
	  	RobotMain.points = ps;
	  	return path;
	}
}
