package org.chargers.frc2018.actions;

import org.chargers.frc2018.Robot;
import org.chargers.frc2018.subsystems.DriveTrain;
import org.usfirst.frc.team5160.utils.RMath;
import org.usfirst.frc.team5160.utils.path.Path;
import org.usfirst.frc.team5160.utils.path.Point;
import org.usfirst.frc.team5160.utils.path.PursuitController;

import edu.wpi.first.wpilibj.drive.MecanumDrive;

public class PathDrive extends Action{
	
	private PursuitController controller;
	private double distanceTraveled = 0; 
	private DriveTrain dt;
	private double lastX = 0, lastY = 0;
	private boolean reverse = false;
	public PathDrive(Path path, boolean reverse){
		controller = new PursuitController(path, 28, 15*12);
		this.reverse = reverse; 
	}

	@Override
	public boolean isFinished() {
		return controller.isFinished(distanceTraveled);
	}

	@Override
	public void start() {
		dt = Robot.superstructure.driveTrain;
		this.lastX = dt.getPositionX();
		this.lastY = dt.getPositionY();
	}

	@Override
	public void update() {
		Point point = new Point(dt.getPositionX(), dt.getPositionY(), dt.getAngle(), dt.getSpeed());
		point.distance = distanceTraveled;
		double[] power = RMath.normalizeTwo(controller.getDrive(point));
		if (reverse == false){
			dt.mecanumDrive(power[0], 0, power[1]);
		}
		else{
			dt.mecanumDrive(-power[0],  0,-power[1]);
		}
		
		distanceTraveled += Math.sqrt(Math.pow(point.x - lastX, 2) + Math.pow(point.y - lastY, 2));
		lastX = point.x;
		lastY = point.y;
	}

	@Override
	public void stop() {
		dt.mecanumDrive(0, 0, 0);
	}
	
	public static double limit(double x){
		if(x > 0.3){
			return 0.3;
		}
		if(x < -0.3){
			return -0.3;
		}
		return x;
	}
	
}
