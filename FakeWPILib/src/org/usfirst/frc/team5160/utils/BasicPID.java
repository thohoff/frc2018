package org.usfirst.frc.team5160.utils;


/**
 * The Basic PID gives us finer control over the built in PID implementation, but may be slightly slower. 
 * @author thomas
 */
public class BasicPID {
	
	protected double P = 0;

	protected double I = 0;

	protected double D = 0;

	protected double F = 0;
	
	protected boolean firstRun = true;
	
	protected double integral = 0;
	
	protected double lastError = 0; 
	
	/**
	 * @param p Proportional error weight
	 * @param i Integral error weight
	 * @param d Derivitave error weight
	 */
	public BasicPID(double p, double i, double d){
		setP(p);
		setI(i);
		setD(d);
	}
	
	/**
	 * @param p Proportional error weight
	 * @param i Integral error weight
	 * @param d Derivitave error weight
	 * @param f Baseline power to be supplied
	 */
	public BasicPID(double p, double i, double d, double f){
		setP(p); 
		setI(i);
		setD(d);
		setF(f);
	}
	
	public double runPID(double position, double target){
		double error = (target - position);
		double pVal = P * error; //Calculate proportional part, just P * error or the different between current and desired positions. 
		double iVal = I * (integral); //Calculate integral part, this is done before adding in current error
		double dVal = D * (error - lastError); //Calculate derivative part, is negative because this slows down rapid changes
		double fVal = F; // It is just a constant
		//Set the derivative to zero on first run, because there is nothing to base it on
		if(firstRun){
			dVal = 0;
			firstRun = false;
		}
		
		double val = pVal + iVal + dVal + fVal;
		
		lastError = error;
		integral = integral + error;
		if(I != 0){
			integral = RMath.clamp(-1/I, 1/I, integral);
		}
		return RMath.clamp(-1, 1, val);
	}
	
	
	public void reset(){
		firstRun = true;
		integral = 0;
		lastError = 0;
	}
	
	public double getP() {
		return P;
	}

	public void setP(double p) {
		P = p;
	}

	public double getI() {
		return I;
	}

	public void setI(double i) {
		I = i;
	}

	public double getD() {
		return D;
	}

	public void setD(double d) {
		D = d;
	}

	public double getF() {
		return F;
	}

	public void setF(double f) {
		F = f;
	}
}
