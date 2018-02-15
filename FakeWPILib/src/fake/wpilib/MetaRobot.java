package fake.wpilib;

import java.util.ArrayList;

import org.chargers.frc2018.Robot;
import org.chargers.frc2018.subsystems.DriveTrain;
import org.chargers.frc2018.subsystems.Elevator;
import org.usfirst.frc.team5160.utils.path.Path;
import org.usfirst.frc.team5160.utils.path.Point;

public class MetaRobot {
	DriveTrain driveTrain;
	Robot robot;
	Elevator elevator;
	
	public ArrayList<Double> posX = new ArrayList<Double>();
	public ArrayList<Double> posY = new ArrayList<Double>();
	public ArrayList<Double> elevatorHeights = new ArrayList<Double>();
	public ArrayList<Double> elevatorSpeeds = new ArrayList<Double>();
	public ArrayList<Double> times = new ArrayList<Double>();
	public ArrayList<Double> velocities = new ArrayList<Double>();
	
	public double x = 55, y = 18, angle = 90, elevatorHeight = 0;
	public static final double topSpeed = 15 * 12;
	public static final double width = 28;
	public static final double length = 26;
	public static final double wheelRadius = Math.sqrt(Math.pow(width/2.0, 2) + Math.pow(length/2.0, 2));
	public static final double elevatorTopSpeed = 100; 
	
	public double time = 0;
	public double leftSpeed = 0, rightSpeed = 0;
	public double momentum = 0;
	public double imbalance = 0;
	public double elevatorSpeed = 0;
	public double elevatorMomentum = 0.99;
	
	public MetaRobot(Robot robot, double momentum, double imbalance, double elevatorMomentum){
		driveTrain = robot.superstructure.driveTrain;
		elevator = robot.superstructure.elevator;
		this.robot = robot; 
		this.driveTrain.setPosition(x, y);
		this.momentum = momentum;
		this.elevatorMomentum = elevatorMomentum;
		this.elevator.error = 0;
	}
	
	public void update(){
		//Update logging values
		posX.add(x);
		posY.add(y);
		times.add(time);
		velocities.add(leftSpeed/2.0+rightSpeed/2.0);
		elevatorHeights.add(elevatorHeight);
		elevatorSpeeds.add(elevatorSpeed);
		
		double dt = 0.01; 
		
		//Run drive train calculations
		double leftPower = driveTrain.backLeft.get() / 2.0 + driveTrain.frontLeft.get() / 2.0;
		double rightPower = driveTrain.backRight.get() / 2.0 + driveTrain.frontRight.get() / 2.0;
		
		leftSpeed = topSpeed*leftPower*(1-momentum - imbalance) + leftSpeed*(momentum + imbalance);
		rightSpeed = topSpeed*rightPower*(1-momentum) + rightSpeed*momentum;
		
		
		double rotation = leftPower*topSpeed / 4.0 - rightPower*topSpeed / 4.0 + leftSpeed/4.0 - rightSpeed/4.0; 
		
		double forwards = leftSpeed / 2.0 + rightSpeed / 2.0;
		
		double dx =  forwards * Math.cos(Math.toRadians(angle)) * dt;
  		double dy =  forwards * Math.sin(Math.toRadians(angle)) * dt;
  		double deltaDegree = dt * rotation * 360.0 / (wheelRadius * 2*Math.PI);
  		
  		//Run elevator calculations
  		double elevatorPower = elevator.leftMotor.get()/2.0 + elevator.rightMotor.get()/2.0;
  		elevatorSpeed = elevatorTopSpeed*elevatorPower*(1-elevatorMomentum) + elevatorSpeed*elevatorMomentum;
  		double de = elevatorSpeed*dt - 1*dt;
  		
  		//Update robot state
		this.angle += deltaDegree;
  		this.x += dx;
  		this.y += dy;
  		this.elevatorHeight += de;
		  		
		driveTrain.leftEncoder.adjust(dt * leftPower * topSpeed);
		driveTrain.rightEncoder.adjust(dt * rightPower * topSpeed);
		driveTrain.gyro.adjust( deltaDegree);
		elevator.leftMotor.setEncoder(elevatorHeight);
		
		time += dt;
		
	}
	
	public double report(){
		Point robot = new Point(x, y);
		Point target = new Point(85, 300-17);
		return Path.DistanceBetweenPoints(robot, target) + Math.abs(this.angle - 90)/1.5 ;
	}
	
	public double reportElevator(){
		if(Math.abs(elevatorHeight - 100) > 3 ){
			return elevator.error + 50;
		}
		return elevator.error;
	}
	
}
