package org.chargers.frc2018.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.Timer;

import org.chargers.frc2018.OI;

public class DriveTrain extends Subsystem {
	private AHRS driveIMU;
	private RobotDrive robotDrive;
	private double posX = 0, posY = 0;
	private Timer timeSinceLastDrive = new Timer();
	@Override
	public void robotInit() {
		try {
	          /* Communicate w/navX-MXP via the MXP SPI Bus.                                     */
	          /* Alternatively:  I2C.Port.kMXP, SerialPort.Port.kMXP or SerialPort.Port.kUSB     */
	          /* See http://navx-mxp.kauailabs.com/guidance/selecting-an-interface/ for details. */
	          this.driveIMU  = new AHRS(Port.kMXP); 
	      } catch (RuntimeException ex ) {
	          DriverStation.reportError("Error instantiating navX-MXP:  " + ex.getMessage(), true);
	      }
		this.robotDrive = new RobotDrive(1,2,3,0);
	}

	@Override
	public void autoInit() {
		this.reset();
	}

	@Override
	public void autoPeriodic() {
		if(this.timeSinceLastDrive.get()>0.075){
			this.mecanumDrive(0, 0, 0);
		}
	}

	@Override
	public void teleopPeriodic() {
		if(this.timeSinceLastDrive.get()>0.075){
			this.mecanumDrive(0, 0, 0);
		}
		
		this.mecanumDrive(OI.getJoystickY(), OI.getJoystickX(), 0);
		
		if(OI.getJoystickX() > 0.5){
			this.reset();
		}
	}

	@Override
	public void stop() {

	}
	
	public AHRS getIMU(){
		return this.driveIMU;
	}
	
	public double getPositionX(){
		return this.posX;
	}
	
	public double getPositionY(){
		return this.posY;
	}
	
	public double getAngle(){
		return this.driveIMU.getAngle();
	}
	
	public double getSpeed(){
		return 0;//Math.sqrt(Math.pow(this.velX,2) + Math.pow(this.velY,2));
	}
	
	public void reset(){
		posX = 0;
		posY = 0;
		driveIMU.reset();
		driveIMU.resetDisplacement();
		timeSinceLastDrive.reset();
		timeSinceLastDrive.start();
	}
	
	public void mecanumDrive(double forwards, double sideways, double rotation){
		System.out.println(getPositionX() + ", "+getPositionY() + ", "+ getAngle());
		robotDrive.mecanumDrive_Cartesian(sideways, forwards, rotation, 0);
		
		double deltaTime = timeSinceLastDrive.get();
		double velX = 144 * forwards * Math.cos(Math.toRadians(this.driveIMU.getAngle()));
		double velY = 144 * forwards * Math.sin(Math.toRadians(this.driveIMU.getAngle()));
		this.posX = this.posX + deltaTime*velX;
		this.posY = this.posY + deltaTime*velX;
		
		timeSinceLastDrive.reset();
	}
	
}
