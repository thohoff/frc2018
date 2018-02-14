package com.ctre.phoenix.motorcontrol.can;

public class WPI_TalonSRX extends TalonSRX{
	private double power = 0;
	public WPI_TalonSRX(int fRONT_RIGHT_CIM) {
		super(fRONT_RIGHT_CIM);
	}

	public void set(double d) {
		power = d;
	}

	public double get(){
		return power;
	}
	
}
