package fake.wpilib;

public class Motor {
	public double freeSpeed, freeCurrent, stallTorque, stallCurrent;
	
	//Free Speed (RPM)	Stall Torque (N*m)	Stall Current (Amp)	Free Current (Amp)
	//18730				0.71				134					0.7
	public static final Motor M_775_PRO = new Motor(18730, 0.7, 0.71, 134);
	
	
	//Free Speed (RPM)	Stall Torque (N*m)	Stall Current (Amp)	Free Current (Amp)
	//5330				2.41				131					2.7
	public static final Motor M_CIM = new Motor(5330, 2.7, 2.41, 131);
	
	
	//Free Speed (RPM)	Stall Torque (N*m)	Stall Current (Amp)	Free Current (Amp)
	//13180				0.43				53					1.8
	public static final Motor M_BAG = new Motor(13180, 1.8, 0.43, 53);
	
	public Motor(double fs, double fc, double st, double sc){
		this.freeSpeed = fs;
		this.stallTorque = fc;
		this.stallCurrent = st;
		this.freeCurrent = sc;
	}
	
	
}
