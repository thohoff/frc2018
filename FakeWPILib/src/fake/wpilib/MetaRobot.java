package fake.wpilib;

import java.util.ArrayList;

import org.chargers.frc2018.Robot;
import org.chargers.frc2018.subsystems.DriveTrain;

public class MetaRobot {
	DriveTrain driveTrain;
	Robot robot;
	public ArrayList<Double> posX = new ArrayList<Double>();
	public ArrayList<Double> posY = new ArrayList<Double>();
	public double x = 48+18, y = 18, angle = 90;
	public static final double topSpeed = 15 * 12; 
	public static final double width = 28;
	public static final double length = 26;
	public static final double wheelRadius = Math.sqrt(Math.pow(width/2.0, 2) + Math.pow(length/2.0, 2));
	
	public double time = 0;
	
	public MetaRobot(Robot robot){
		driveTrain = robot.superstructure.driveTrain;
		this.robot = robot; 
		this.driveTrain.setPosition(x, y);
	}
	
	public void update(){
		posX.add(x);
		posY.add(y);
		
		double dt = 0.01; 
		
		
		double leftPower = driveTrain.backLeft.get() / 2.0 + driveTrain.frontLeft.get() / 2.0;
		double rightPower = driveTrain.backRight.get() / 2.0 + driveTrain.frontRight.get() / 2.0;
		double rotation = leftPower / 2.0 - rightPower / 2.0; 
		
		double forwards = leftPower / 2.0 + rightPower / 2.0;
		
		double dx =  forwards * topSpeed * Math.cos(Math.toRadians(angle)) * dt;
  		double dy =  forwards * topSpeed * Math.sin(Math.toRadians(angle)) * dt;
  		double deltaDegree = -dt * rotation * topSpeed * 360.0 / (wheelRadius * 2*Math.PI);
  		
		this.angle += deltaDegree;
  		this.x += dx;
  		this.y += dy;
		
		driveTrain.leftEncoder.adjust(dt * leftPower * topSpeed);
		driveTrain.rightEncoder.adjust(dt * rightPower * topSpeed);
		driveTrain.gyro.adjust( deltaDegree);
		time += dt;
	}
	
}
