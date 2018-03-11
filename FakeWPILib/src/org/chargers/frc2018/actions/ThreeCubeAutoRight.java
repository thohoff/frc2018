package org.chargers.frc2018.actions;

import org.chargers.frc2018.Constants;
import org.chargers.frc2018.subsystems.Elevator.ELEVATOR_STATE;
import org.usfirst.frc.team5160.utils.path.Point;

public class ThreeCubeAutoRight extends ActionGroup {
	public ThreeCubeAutoRight() {
		double scoreBuffer = Constants.kCenterToFrontBumperDistance;
		double cubeBuffer = Constants.kCenterToIntakeDistance - Constants.kAutoCubeIntakeDistance;

		Point[] fromRightToScale = {
				// new Point(280,robotLength/2.0),
				new Point(280, 36), new Point(336 - 48, 200), new Point(336 - 85, 270 - scoreBuffer),
				new Point(336 - 85, 300 - scoreBuffer) };

		Point[] fromRightScaleToCube = {
				// new Point(336-85,300 - robotLength/2.0),
				new Point(336 - 90, 200 + cubeBuffer) };

		Point[] fromCubeToRightScale = {
				// new Point(336-90,203 + robotLength/2.0),
				new Point(336 - 85, 300 - scoreBuffer) };

		Point[] fromRightScaleToCube2 = {
				// new Point(336-85,300 - robotLength/2.0),
				new Point(336 - 85, 300 - scoreBuffer * 2), new Point(336 - 120, 200 + cubeBuffer * 2),
				new Point(336 - 120, 200 + cubeBuffer) };

		Point[] fromCubeToRightScale2 = {
				// new Point(336 - 120,203 + robotLength/2.0),
				new Point(336 - 120, 200 + cubeBuffer * 2), new Point(336 - 85, 300 - scoreBuffer * 2),
				new Point(336 - 85, 300 - scoreBuffer) };

		Point[] fromRightScaleToCube3 = {
				// new Point(336 - 85,300 - robotLength/2.0),
				new Point(336 - 85, 300 - scoreBuffer * 2), new Point(336 - 150, 200 + cubeBuffer * 2),
				new Point(336 - 150, 200 + cubeBuffer) };

		Point[] fromCubeToRightScale3 = {
				// new Point(336- 150,203 + robotLength/2.0),
				new Point(336 - 150, 200 + cubeBuffer * 2), new Point(336 - 85, 300 - scoreBuffer * 2),
				new Point(336 - 85, 303 - scoreBuffer) };

		//Drive and score in scale
	     ParallelAction tmp = new ParallelAction(false)
	    		 .addAction(new PathDriveDynamic(fromRightToScale, false).setPower(0.7), 6)
	    		 .addAction(new ElevatorAction(ELEVATOR_STATE.SCALE), 6);
	     
		 this.addAction(tmp);
		 this.addAction(new TimedAction(new TurnAction(90), 0.5) );	
			
		 //Drive and intake cube
		 tmp = new ParallelAction(false)
	    		 .addAction(new PathDriveDynamic(fromRightScaleToCube, true).setPower(0.7), 4)
	    		 .addAction(new ElevatorAction(ELEVATOR_STATE.ZERO), 4);
		 
		 this.addAction(tmp);
		 this.addAction(new TimedAction(new TurnAction(90), 0.5) );	
			
		//Drive and score in scale
		 tmp = new ParallelAction(false)
	    		 .addAction(new PathDriveDynamic(fromCubeToRightScale, false).setPower(0.65), 4)
	    		 .addAction(new ElevatorAction(ELEVATOR_STATE.SCALE), 4);
		 
		 this.addAction(tmp);
		 this.addAction(new TimedAction(new TurnAction(90), 0.5) );	
			
		 
		//Drive and intake cube
		 tmp = new ParallelAction(false)
	    		 .addAction(new PathDriveDynamic(fromRightScaleToCube2, true).setPower(0.65), 4)
	    		 .addAction(new ElevatorAction(ELEVATOR_STATE.ZERO), 4);
		 
		 this.addAction(tmp);
		 this.addAction(new TimedAction(new TurnAction(90), 0.5) );	
			
		//Drive and score in scale
		 tmp = new ParallelAction(false)
	    		 .addAction(new PathDriveDynamic(fromCubeToRightScale2, false).setPower(0.65), 4)
	    		 .addAction(new ElevatorAction(ELEVATOR_STATE.SCALE), 4);
		 this.addAction(tmp);
		 this.addAction(new TimedAction(new TurnAction(90), 0.5) );	
		 
		//Drive and intake cube
		 tmp = new ParallelAction(false)
	    		 .addAction(new PathDriveDynamic(fromRightScaleToCube3, true).setPower(0.65), 4)
	    		 .addAction(new ElevatorAction(ELEVATOR_STATE.ZERO), 4);
		 
		 this.addAction(tmp);
		 this.addAction(new TimedAction(new TurnAction(90), 0.5) );	
			
		//Drive and score in scale
		 tmp = new ParallelAction(false)
	    		 .addAction(new PathDriveDynamic(fromCubeToRightScale3, false).setPower(0.65), 4)
	    		 .addAction(new ElevatorAction(ELEVATOR_STATE.SCALE), 7);
		 
		 this.addAction(tmp);
		 this.addAction(new TimedAction(new TurnAction(90), 1) );
		 this.addAction(new TimedAction(new TurnAction(90), 1) );

	}

}
