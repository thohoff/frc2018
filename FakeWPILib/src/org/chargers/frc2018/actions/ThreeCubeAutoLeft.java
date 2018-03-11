package org.chargers.frc2018.actions;

import java.awt.Color;

import org.chargers.frc2018.Constants;
import org.chargers.frc2018.subsystems.Elevator.ELEVATOR_STATE;
import org.usfirst.frc.team5160.utils.path.FalconLinePlot;
import org.usfirst.frc.team5160.utils.path.Path;
import org.usfirst.frc.team5160.utils.path.Point;


public class ThreeCubeAutoLeft extends ActionGroup{
	public ThreeCubeAutoLeft(){
		double scoreBuffer = Constants.kCenterToFrontBumperDistance;
		double cubeBuffer = Constants.kCenterToIntakeDistance - Constants.kAutoCubeIntakeDistance;
		
		Point[] fromLeftToScale = {
				//new Point(55,robotLength/2.0), 
				new Point(55,36), new Point(48,200),new Point(85, 270 - scoreBuffer), new Point(85, 300 - scoreBuffer)
			};
			 
			Point[] fromLeftScaleToCube = {
				 //new Point(85,300 - robotLength/2.0),
				 new Point(90,200 + cubeBuffer)
			};
			
			Point[] fromCubeToLeftScale = {
					 //new Point(90,203 + robotLength/2.0), 
					 new Point(85,300 - scoreBuffer)
			};
			
			Point[] fromLeftScaleToCube2 = {
				 //new Point(85,300 - robotLength/2.0), 
				 new Point(85,300 - scoreBuffer * 2), new Point(120,200 + cubeBuffer*2), new Point(120,200 + cubeBuffer)
			};
				
			Point[] fromCubeToLeftScale2 = {
					 //new Point(120,203 + robotLength/2.0),
					 new Point(120,200 + cubeBuffer*2), new Point(85,300 - scoreBuffer * 2), new Point(85,300 - scoreBuffer)
			};
		  	
			

			Point[] fromLeftScaleToCube3 = {
				 //new Point(85,300 - robotLength/2.0),
				 new Point(85, 300 - scoreBuffer * 2), new Point(150,200 + cubeBuffer*2), new Point(150,200 + cubeBuffer)
			};
				
			Point[] fromCubeToLeftScale3 = {
					 //new Point(150,203 + robotLength/2.0),
					 new Point(150,200 + cubeBuffer*2), new Point(85,300 - scoreBuffer*2), new Point(85,300 - scoreBuffer)
			};
		
			
		//Drive and score in scale
	     ParallelAction tmp = new ParallelAction(false)
	    		 .addAction(new PathDriveDynamic(fromLeftToScale, false).setPower(0.7), 6)
	    		 .addAction(new ElevatorAction(ELEVATOR_STATE.SCALE), 6);
	     
		 this.addAction(tmp);
		 this.addAction(new TimedAction(new TurnAction(90), 0.5) );	
			
		 //Drive and intake cube
		 tmp = new ParallelAction(false)
	    		 .addAction(new PathDriveDynamic(fromLeftScaleToCube, true).setPower(0.7), 4)
	    		 .addAction(new ElevatorAction(ELEVATOR_STATE.ZERO), 4);
		 
		 this.addAction(tmp);
		 this.addAction(new TimedAction(new TurnAction(90), 0.5) );	
			
		//Drive and score in scale
		 tmp = new ParallelAction(false)
	    		 .addAction(new PathDriveDynamic(fromCubeToLeftScale, false).setPower(0.65), 4)
	    		 .addAction(new ElevatorAction(ELEVATOR_STATE.SCALE), 4);
		 
		 this.addAction(tmp);
		 this.addAction(new TimedAction(new TurnAction(90), 0.5) );	
			
		 
		//Drive and intake cube
		 tmp = new ParallelAction(false)
	    		 .addAction(new PathDriveDynamic(fromLeftScaleToCube2, true).setPower(0.65), 4)
	    		 .addAction(new ElevatorAction(ELEVATOR_STATE.ZERO), 4);
		 
		 this.addAction(tmp);
		 this.addAction(new TimedAction(new TurnAction(90), 0.5) );	
			
		//Drive and score in scale
		 tmp = new ParallelAction(false)
	    		 .addAction(new PathDriveDynamic(fromCubeToLeftScale2, false).setPower(0.65), 4)
	    		 .addAction(new ElevatorAction(ELEVATOR_STATE.SCALE), 4);
		 this.addAction(tmp);
		 this.addAction(new TimedAction(new TurnAction(90), 0.5) );	
		 
		//Drive and intake cube
		 tmp = new ParallelAction(false)
	    		 .addAction(new PathDriveDynamic(fromLeftScaleToCube3, true).setPower(0.65), 4)
	    		 .addAction(new ElevatorAction(ELEVATOR_STATE.ZERO), 4);
		 
		 this.addAction(tmp);
		 this.addAction(new TimedAction(new TurnAction(90), 0.5) );	
			
		//Drive and score in scale
		 tmp = new ParallelAction(false)
	    		 .addAction(new PathDriveDynamic(fromCubeToLeftScale3, false).setPower(0.65), 4)
	    		 .addAction(new ElevatorAction(ELEVATOR_STATE.SCALE), 7);
		 
		 this.addAction(tmp);
		 this.addAction(new TimedAction(new TurnAction(90), 1) );
		 this.addAction(new TimedAction(new TurnAction(90), 1) );
		
	}
	
}
