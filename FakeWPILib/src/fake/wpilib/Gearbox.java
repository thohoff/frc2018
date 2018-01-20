package fake.wpilib;

public class Gearbox {
	public double reduction, load, armLength;
	public int motorCount;
	public Motor motor;
	
	public Gearbox(Motor motor, int motorCount, double reduction, double load, double armLength){
		this.motor = motor;
		this.motorCount = motorCount;
		this.reduction = reduction;
		this.load = load;
		this.armLength = armLength;
	}
	
}
