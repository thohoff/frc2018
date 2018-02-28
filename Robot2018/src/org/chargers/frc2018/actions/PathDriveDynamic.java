package org.chargers.frc2018.actions;


import org.chargers.frc2018.Robot;
import org.chargers.frc2018.subsystems.DriveTrain;
import org.usfirst.frc.team5160.utils.RMath;
import org.usfirst.frc.team5160.utils.path.Path;
import org.usfirst.frc.team5160.utils.path.Point;
import org.usfirst.frc.team5160.utils.path.PursuitController;



public class PathDriveDynamic extends Action{
	private PursuitController controller;
	private double distanceTraveled = 0; 
	private DriveTrain dt;
	private double lastX = 0, lastY = 0;
	private boolean reverse = false;
	private Point[] points;
	private double power = 0.7;
	public PathDriveDynamic(Point[] ps, boolean reverse){
		this.reverse = reverse; 
		this.points = ps;
	}
	public void setPower(double power){
		this.power = power;
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
		points = Path.AddStart(points, new Point(lastX, lastY));
		controller = new PursuitController(makePath(points), 34, 15*12);
		update();
		dt.setPosition(lastX, lastY);
	}

	@Override
	public void update() {
		Point point = new Point(dt.getPositionX(), dt.getPositionY(), dt.getAngle(), dt.getSpeed());
		point.distance = distanceTraveled;
		double[] output = RMath.normalizeTwo(controller.getDrive(point));
		if (reverse == false){
			dt.mecanumDrive(output[0]*power, 0, output[1]*power);
		}
		else{
			dt.mecanumDrive(-output[0]*power,  0,-output[1]*power);
		}
		distanceTraveled += Math.sqrt(Math.pow(point.x - lastX, 2) + Math.pow(point.y - lastY, 2));
		lastX = point.x;
		lastY = point.y;
	}

	@Override
	public void stop() {
		dt.mecanumDrive(0, 0, 0);
	}
	
	private Path makePath(Point[] ps){
		Path path = new Path();
	  	ps = Path.InjectPoints(ps,5);
	  	ps = Path.SmoothPoints(ps);
	  	ps = Path.SmoothPoints(ps);
	  	ps = Path.SmoothPoints(ps);
	  	ps = Path.SmoothPoints(ps);
	  	ps = Path.InjectPoints(ps,2);
	  	ps = Path.SmoothPoints(ps);
	  	ps = Path.SmoothPoints(ps);
	  	ps = Path.SmoothPoints(ps);
	  	ps = Path.SmoothPoints(ps);
	  	ps = Path.InjectPoints(ps,2);
	  	ps = Path.SmoothPoints(ps);
	  	path.addPoints(ps);
	  	//RobotMain.fig2.addData(Path.ToDoubleArray(ps), Color.BLUE);
	  	return path;
	}
	
}
