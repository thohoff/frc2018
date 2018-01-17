package org.chargers.frc2018.actions;

import org.chargers.frc2018.Robot;
import org.chargers.frc2018.subsystems.DriveTrain;
import org.usfirst.frc.team5160.utils.path.Path;
import org.usfirst.frc.team5160.utils.path.Point;
import org.usfirst.frc.team5160.utils.path.PursuitController;

import edu.wpi.first.wpilibj.drive.MecanumDrive;

public class PathDrive extends Action{
	
	private PursuitController controller;
	private double distanceTraveled = 0; 
	private DriveTrain dt;
	private double lastX = 0, lastY = 0;
	public PathDrive(Path path){
		controller = new PursuitController(path, 28, 10);
	}

	@Override
	public boolean isFinished() {
		return controller.isFinished(distanceTraveled);
	}

	@Override
	public void start() {
		dt = Robot.superstructure.driveTrain;
		dt.resetOrientation();
	}

	@Override
	public void update() {
		Point point = new Point(dt.getPositionX(), dt.getPositionY(), dt.getAngle(), dt.getSpeed());
		double[] power = controller.getDrive(point, distanceTraveled);
		dt.mecanumDrive(power[0], 0, power[1]);
		distanceTraveled += Math.sqrt(Math.pow(point.x - lastX, 2) + Math.pow(point.y - lastY, 2));
		lastX = point.x;
		lastY = point.y;
		System.out.println(point.x+", "+  point.y +", " + point.angle);
	}

	@Override
	public void stop() {
		dt.mecanumDrive(0, 0, 0);
	}
	
	
	
}
