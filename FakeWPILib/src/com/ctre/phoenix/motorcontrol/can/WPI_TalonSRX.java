package com.ctre.phoenix.motorcontrol.can;

import org.usfirst.frc.team5160.utils.RMath;

public class WPI_TalonSRX extends TalonSRX{
	private double power = 0;
	public WPI_TalonSRX(int fRONT_RIGHT_CIM) {
		super(fRONT_RIGHT_CIM);
	}

	public void set(double d) {
		power = RMath.clamp(-1, 1, d);
	}

	public double get(){
		return power;
	}
	
}
