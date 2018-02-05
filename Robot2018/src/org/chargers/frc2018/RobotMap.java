package org.chargers.frc2018;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
		//drive motors
		public static int FRONT_LEFT_CIM = 14;
		public static int BACK_LEFT_CIM = 13;
		public static int FRONT_RIGHT_CIM = 1;
		public static int BACK_RIGHT_CIM = 2;
		
		public static int ELEVATOR_LEFT_775 = 12;
		public static int ELEVATOR_RIGHT_775 = 3;
		
		
		public static int LEFT_ENCODER_CHANNEL_A = 0;
		public static int LEFT_ENCODER_CHANNEL_B = 1;
		public static int RIGHT_ENCODER_CHANNEL_A = 2;
		public static int RIGHT_ENCODER_CHANNEL_B = 3;
		
		public static int UPPER_SWITCH_CHANNEL = 4;
		public static int LOWER_SWITCH_CHANNEL = 5;
		
}
