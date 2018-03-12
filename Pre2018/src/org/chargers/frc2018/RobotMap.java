package org.chargers.frc2018;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
		//Drive motors
		public static int FRONT_LEFT_CIM = 14;
		public static int BACK_LEFT_CIM = 13;
		public static int FRONT_RIGHT_CIM = 2;
		public static int BACK_RIGHT_CIM = 1;
		
		//Elevator motors
		public static int ELEVATOR_LEFT_775 = 11;
		public static int ELEVATOR_RIGHT_775 = 4;
		
		
		//Climber motors
		public static int CLIMBER_LEFT_775 = 12; 
		public static int CLIMBER_RIGHT_775 = 3;
		
		//Intake motors
		public static int INTAKE_MIDDLE_RIGHT_775 = 7;
		public static int INTAKE_MIDDLE_LEFT_775 = 8;
		public static int INTAKE_FRONT_RIGHT_775 = 6;
		public static int INTAKE_FRONT_LEFT_775 = 9;
		
		//Drive encoders 
		public static int LEFT_ENCODER_CHANNEL_A = 0;
		public static int LEFT_ENCODER_CHANNEL_B = 1;
		public static int RIGHT_ENCODER_CHANNEL_A = 2;
		public static int RIGHT_ENCODER_CHANNEL_B = 3;
		
		//Elevator encoder
		public static int ELEVATOR_ENCODER_CHANNEL_A = 4;
		public static int ELEVATOR_ENCODER_CHANNEL_B = 5;
		
		//Limit switch
		public static int UPPER_SWITCH_CHANNEL = 4;
		public static int LOWER_SWITCH_CHANNEL = 5;
		
}
