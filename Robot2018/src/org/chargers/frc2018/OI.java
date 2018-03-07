package org.chargers.frc2018;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public static Joystick joystick = new Joystick(0);
	
	public static double getJoystickX(){
		if(Math.abs(joystick.getX()) > 0.05){
			return joystick.getX()*joystick.getX() * Math.signum(joystick.getX());
		}
		return 0;
	}
	
	public static double getJoystickY(){
		if(Math.abs(joystick.getY()) > 0.05){
			return joystick.getY()*joystick.getY() * Math.signum(joystick.getY());
		}
		return 0;
	}
	
	public static double getJoystickRotationX(){
		if(Math.abs(joystick.getRawAxis(4)) > 0.05){
			return joystick.getRawAxis(4);
		}
		return 0;
	}
	public static double getJoystickRotationY(){
		if(Math.abs(joystick.getRawAxis(5)) > 0.05){
			return joystick.getRawAxis(5);
		}
		return 0;
	}
	
	public static double getElevatorPower(){
		if(joystick.getRawButton(3)){
			return 1;
		}
		else if(joystick.getRawButton(4)){
			return -1;
		}
		return 0;
	}
	public static double getIntakePower(){
		if(joystick.getRawButton(1)){
			return 1;
		}
		else if(joystick.getRawButton(2)){
			return -1;
		}
		return 0;
	}
	
}