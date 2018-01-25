package fake.wpilib;

public class Gearbox {
	public double reduction, load, armLength;
	public int motorCount;
	public Motor motor;
	public double efficiency = 0.75;
	public Gearbox(Motor motor, int motorCount, double reduction, double load){
		this.motor = motor;
		this.motorCount = motorCount;
		this.reduction = reduction;
		this.load = load;
	}
	
	public double getNoLoadDegreesPerSecond(){
		return motor.freeSpeed/60.0/this.reduction*360;
	}
	
	
	public double getLoadInchesPerSecondDiameter(double diameter){
		return getLoadDegreesPerSecondDiameter(diameter)*diameter*Math.PI/360.0;
	}
	public double getLoadDegreesPerSecondDiameter(double diameter){
		return getNoLoadDegreesPerSecond() - (getNoLoadDegreesPerSecond()*load/getStallLoadDiameter(diameter));
	}
	public double getStallLoadDiameter(double diameter){
		return motor.stallTorque * motorCount * reduction * 39.37*0.2248*efficiency/ (diameter/2.0);
	}
	public double getLoadTorque(double diameter){
		return motor.stallTorque - motor.stallTorque/motor.freeSpeed*(getLoadDegreesPerSecondDiameter(diameter)*60/360);
	}
	
	
	public double[][] plotPowerLoad(double diameter){
		double orig_load = load;
		double[][] tmp = new double[300][2];
		for(int i = 0; i < 300; i++){
			tmp[i][0] = i;
			load = i;
			tmp[i][1] = getLoadInchesPerSecondDiameter(diameter)*i/6;
		}
		load = orig_load;
		return tmp;
	}
	public double[][] plotPowerReduction(double diameter){
		double orig_red = reduction;
		double[][] tmp = new double[90][2];
		for(int i = 0; i < 90; i++){
			reduction = i;
			tmp[i][0] = i;
			tmp[i][1] = getLoadInchesPerSecondDiameter(diameter)*load/6;
		}
		reduction = orig_red;
		return tmp;
	}
	public double[][] plotSpeedReduction(double diameter){
		double orig_red = reduction;
		double[][] tmp = new double[40][2];
		for(int i = 0; i < 40; i++){
			reduction = i+10;
			tmp[i][0] = i+10;
			tmp[i][1] = getLoadInchesPerSecondDiameter(diameter);
		}
		reduction = orig_red;
		return tmp;
	}
}
