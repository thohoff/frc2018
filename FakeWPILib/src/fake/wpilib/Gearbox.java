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
		return motor.stallTorque - motor.stallTorque/motor.freeSpeed*(getLoadDegreesPerSecondDiameter(diameter)*60/360)*reduction;
	}
	
	public double getLoadCurrent(double diameter){
		return motor.stallCurrent - (motor.stallCurrent-motor.freeCurrent)/motor.freeSpeed*(getLoadDegreesPerSecondDiameter(diameter)*60/360)*reduction;
	}
	
	public double[][] plotPowerLoad(double diameter){
		double orig_load = load;
		double[][] tmp = new double[300][2];
		for(int i = 0; i < 300; i++){
			tmp[i][0] = i;
			load = i;
			double val = getLoadInchesPerSecondDiameter(diameter)*load/6;
			if(val < 0){
				val = 0;
			}
			tmp[i][1] = val;
		}
		load = orig_load;
		return tmp;
	}
	public double[][] plotPowerReduction(double diameter){
		double orig_red = reduction;
		double[][] tmp = new double[50][2];
		for(int i = 0; i < 50; i++){
			reduction = i;
			tmp[i][0] = i;
			double val = getLoadInchesPerSecondDiameter(diameter)*load/6;
			if(val < 0){
				val = 0;
			}
			tmp[i][1] = val;
		}
		reduction = orig_red;
		return tmp;
	}
	public double[][] plotSpeedReduction(double diameter){
		double orig_red = reduction;
		double[][] tmp = new double[50][2];
		for(int i = 0; i < 50; i++){
			reduction = i;
			tmp[i][0] = i;
			double val = getLoadInchesPerSecondDiameter(diameter);
			if(val < 0){
				val = 0;
			}
			tmp[i][1] = val;
		}
		reduction = orig_red;
		return tmp;
	}
	public double[][] plotAmperageReduction(double diameter){
		double orig_red = reduction;
		double[][] tmp = new double[50][2];
		for(int i = 0; i < 50; i++){
			reduction = i;
			tmp[i][0] = i;
			tmp[i][1] = getLoadCurrent(diameter);
		}
		reduction = orig_red;
		return tmp;
	}
	public double[][] plotSpeedPerAmperageReduction(double diameter){
		double orig_red = reduction;
		double[][] tmp = new double[50][2];
		for(int i = 0; i < 50; i++){
			reduction = i;
			tmp[i][0] = i;
			double val = getLoadInchesPerSecondDiameter(diameter);
			if(val < 0){
				val = 0;
			}
			tmp[i][1] = val*val*val/getLoadCurrent(diameter);
		}
		reduction = orig_red;
		return tmp;
	}
}
