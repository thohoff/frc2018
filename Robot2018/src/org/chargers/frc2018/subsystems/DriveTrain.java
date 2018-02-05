package org.chargers.frc2018.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.Timer;

import org.chargers.frc2018.OI;
import org.chargers.frc2018.RobotMap;

public class DriveTrain extends Subsystem {
	private AHRS driveIMU;
	private RobotDrive robotDrive;
	private double posX = 0, posY = 0;
	private double lastEncoderDistance;
	private static final double TICK_TO_INCH = 6.0*Math.PI/256.0;//256 ticks per rev, 6 inch diameter wheels
	private Timer timeSinceLastDrive = new Timer();
	
	private WPI_TalonSRX frontRight; 
	private WPI_TalonSRX backRight;
	private WPI_TalonSRX frontLeft;
	private WPI_TalonSRX backLeft;
	private Encoder leftEncoder;
	private Encoder rightEncoder;
	
	@Override
	public void robotInit() {
		frontRight = new WPI_TalonSRX(RobotMap.FRONT_RIGHT_CIM);
		backRight = new WPI_TalonSRX(RobotMap.BACK_RIGHT_CIM);
		frontLeft = new WPI_TalonSRX(RobotMap.FRONT_LEFT_CIM);
		backLeft = new WPI_TalonSRX(RobotMap.BACK_LEFT_CIM);
		
		configureMotor(frontLeft);
		configureMotor(frontRight);
		configureMotor(backLeft);
		configureMotor(backRight);
		
		leftEncoder = new Encoder(RobotMap.LEFT_ENCODER_CHANNEL_A, RobotMap.LEFT_ENCODER_CHANNEL_B, false, EncodingType.k4X);
		rightEncoder = new Encoder(RobotMap.RIGHT_ENCODER_CHANNEL_A, RobotMap.RIGHT_ENCODER_CHANNEL_B, false, EncodingType.k4X);
		
		try {
	          /* Communicate w/navX-MXP via the MXP SPI Bus.                                     */
	          /* Alternatively:  I2C.Port.kMXP, SerialPort.Port.kMXP or SerialPort.Port.kUSB     */
	          /* See http://navx-mxp.kauailabs.com/guidance/selecting-an-interface/ for details. */
	          this.driveIMU  = new AHRS(Port.kMXP); 
	      } catch (RuntimeException ex ) {
	          DriverStation.reportError("Error instantiating navX-MXP:  " + ex.getMessage(), true);
	      }
		this.robotDrive = new RobotDrive(frontLeft,backLeft,frontRight,frontRight);
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
		leftEncoder.reset();
		rightEncoder.reset();
	}
	
	public void mecanumDrive(double forwards, double sideways, double rotation){
		//System.out.println(getPositionX() + ", "+getPositionY() + ", "+ getAngle());
		robotDrive.mecanumDrive_Cartesian(sideways, forwards, rotation, 0);
		
		
		double deltaTime = timeSinceLastDrive.get();
		
		double encoderDistance = (leftEncoder.get() * TICK_TO_INCH + rightEncoder.get() * TICK_TO_INCH) / 2.0;
		double deltaDistance = encoderDistance - lastEncoderDistance;
		lastEncoderDistance = encoderDistance;
		
		double deltaX = deltaDistance * Math.cos(Math.toRadians(this.driveIMU.getAngle()));
		double deltaY = deltaDistance * Math.sin(Math.toRadians(this.driveIMU.getAngle()));
		this.posX = this.posX + deltaX;
		this.posY = this.posY + deltaY;
		
		timeSinceLastDrive.reset();
	}
	
	private void configureMotor(TalonSRX motor){
		motor.configOpenloopRamp(0.25, 100);
		motor.enableCurrentLimit(true);
		motor.configContinuousCurrentLimit(60, 100);
		motor.configPeakCurrentDuration(2500, 100);
		motor.configPeakCurrentLimit(80, 100);
		motor.setNeutralMode(NeutralMode.Brake);
	}
	
}
