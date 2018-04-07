package org.chargers.frc2018;

public class Constants {
	
	
	
	//Robot drivetrain size constants, in inches
	public static double kWheelDiameter = 6;
	public static double kTrackWidth = 26;
	public static double kCenterToIntakeDistance = 28;
	public static double kCenterToFrontBumperDistance = 17.5;
	public static double kCenterToSideBumperDistance = 19.5; 
	
	//Robot elevator size constants, in inches
	public static double kMaxHeight = 90;
	public static double kMinHeight = 0;
	public static double kDistanceElevatorToGround = 4;
	
	//Teleop drive train turning constants
	public static double kTeleTurnKp = 0.05;
	public static double kTeleTurnKi = 0;
	public static double kTeleTurnKd = 0.5;
	public static double kRotationJoystickDeadzone = 0.75;
	
	//Auto drive train turning and driving constants
	public static double kAutoTurnKp = 0.07;
	public static double kAutoTurnKi = 0.0001;
	public static double kAutoTurnKd = 0.1;
	public static double kAutoCubeIntakeDistance = 3;
	
	public static double kTrajectoryKp = 0.1;
	public static double kTrajectoryKd = 0;
	public static double kTrajectoryKv = 0;
	public static double kTrajectoryKa = 0;
	public static double kTrajectoryLookAhead = 14;
	public static double kTrajectoryAccelerationDistance = 48;
	public static double kTrajectoryKalpha = 2.7; // For turning in pursuit
	public static double kTrajectoryKbeta = 0.0; // For turning in pursuit
	
}
