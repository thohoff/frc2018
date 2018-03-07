package org.usfirst.frc.team5160.utils;

/**
 * The Basic PID gives us finer control over the built in PID implementation, but may be slightly slower. 
 * @author thomas
 */
public class TrajectoryPID {
	
	protected double P = 0;

	protected double I = 0;

	protected double D = 0;

	protected double V = 0;
	
	private double A = 0;
	
	protected double La = 0; //length of acceleration (distance, not time)
	
	protected boolean firstRun = true;
	
	protected double integral = 0;
	
	protected double lastError = 0; 
	
	/**
	 * @param p Proportional error weight
	 * @param i Integral error weight
	 * @param d Derivitave error weight
	 * @param v Velocity control weight
	 * @param La 
	 */
	public TrajectoryPID(double p, double i, double d, double v, double a, double La){
		setP(p);
		setI(i);
		setD(d);
		setV(v);
		setA(a);
		setLa(La);
	}
	
	
	


	public double runPID(double position, double start, double target){
		double error = (target - position);
		double pVal = P * (target - position); //Calculate proportional part, just P * error or the different between current and desired positions. 
		double iVal = I * (integral); //Calculate integral part, this is done before adding in current error
		
		double vVal = 0; // It is just a constant
		double aVal = 0;
		int direction = RMath.sign(target-position);
		
		if(Math.abs(target-start) > 2*La){
			double totalDistance = Math.abs(start-target);
			double percentage = (Math.abs(position - start) ) / totalDistance;
			double flatStart = (start + La*direction) / totalDistance;
			double flatEnd = (target - La*direction) / totalDistance;
			
			if(percentage <= flatStart){
				vVal = percentage / flatStart;
				aVal = A;
			}
			else if (percentage >= flatStart && percentage <= flatEnd){
				vVal = 1;
				aVal = 0;
			}
			else if (percentage >= flatEnd){
				vVal = (1-percentage)/(1-flatEnd);
				aVal = -A;
			}
			//System.out.println(vVal +","  + aVal + ", "+percentage);
		}
		else{
			double totalDistance = Math.abs(start-target);
			double percentage = (Math.abs(position - start) ) / totalDistance;
			double flatStart = (start + La * direction) / totalDistance;
			double flatEnd = target - La * direction / totalDistance;
			if(percentage <= 0.5){
				vVal = percentage / flatStart;
				aVal = A;
			}
			else if(percentage >= 0.5){
				vVal = (1-percentage)/(1-flatEnd);
				aVal = -A;
			}
			
		}
		double dVal = D * (error - lastError ); //Calculate derivative part, is negative because this slows down rapid changes
		aVal = aVal*direction;
		
		//Set the derivative to zero on first run, because there is nothing to base it on
		if(firstRun){
			dVal = 0;
			firstRun = false;
		}
		double val = pVal + iVal + dVal + aVal;
		vVal = V* (vVal - val)*direction;
		val += vVal;
		
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

	public double getV() {
		return V;
	}

	public void setV(double v) {
		V = v;
	}
	public double getLa(){
		return La;
	}
	public void setLa(double La) {
		this.La = La;
	}





	public double getA() {
		return A;
	}





	public void setA(double a) {
		A = a;
	}
}
