package org.chargers.frc2018.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SPI.Port;
import org.chargers.frc2018.OI;

public class DriveTrain extends Subsystem {
	private AHRS driveIMU;
	private RobotDrive robotDrive;
	@Override
	public void robotInit() {
		try {
	          /* Communicate w/navX-MXP via the MXP SPI Bus.                                     */
	          /* Alternatively:  I2C.Port.kMXP, SerialPort.Port.kMXP or SerialPort.Port.kUSB     */
	          /* See http://navx-mxp.kauailabs.com/guidance/selecting-an-interface/ for details. */
	          driveIMU  = new AHRS(Port.kMXP); 
	      } catch (RuntimeException ex ) {
	          DriverStation.reportError("Error instantiating navX-MXP:  " + ex.getMessage(), true);
	      }
		driveIMU.reset();
		driveIMU.resetDisplacement();
		robotDrive = new RobotDrive(1,2,3,0);
	}

	@Override
	public void autoInit() {
		driveIMU.reset();
		driveIMU.resetDisplacement();
	}

	@Override
	public void autoPeriodic() {

	}

	@Override
	public void teleopPeriodic() {
		this.mecanumDrive(OI.getJoystickY(), 0, OI.getJoystickX());
		System.out.println(getPositionX() + ", "+getPositionY() + ", "+ getAngle());
		if(OI.getJoystickX() > 0.5){
		driveIMU.resetDisplacement();
		driveIMU.reset();
		}
	}

	@Override
	public void stop() {

	}
	
	public AHRS getIMU(){
		return driveIMU;
	}
	
	public double getPositionX(){
		return driveIMU.getDisplacementX() * 39.37;
	}
	
	public double getPositionY(){
		return driveIMU.getDisplacementY() * 39.37;
	}
	
	public double getAngle(){
		return driveIMU.getAngle();
	}
	
	public double getSpeed(){
		return Math.sqrt(Math.pow(driveIMU.getVelocityX(),2) + Math.pow(driveIMU.getVelocityY(),2));
	}
	
	public void resetOrientation(){
	}
	
	public void mecanumDrive(double forwards, double sideways, double rotation){
		robotDrive.mecanumDrive_Cartesian(sideways, forwards, rotation, 0);
	}
	
}
