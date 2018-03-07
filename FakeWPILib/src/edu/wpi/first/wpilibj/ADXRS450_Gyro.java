package edu.wpi.first.wpilibj;

import org.usfirst.frc.team5160.utils.RMath;

public class ADXRS450_Gyro {
	
	double angle = 0;
	
	public double getAngle() {
		// TODO Auto-generated method stub
		return angle + RMath.FRandRange(-2.5f, 2.5f);
	}
	
	public void set(double d){
		angle = d;
	}
	
	public void adjust(double d){
		angle += d ;//+ 0.5*RMath.SignedFRand();
	}

	public void reset() {
		// TODO Auto-generated method stub
		angle = 0;
	}

}
