package fake.wpilib;

import java.awt.Color;

import org.chargers.frc2018.Robot;
import org.usfirst.frc.team5160.utils.RMath;
import org.usfirst.frc.team5160.utils.path.FalconLinePlot;
import org.usfirst.frc.team5160.utils.path.Path;
import org.usfirst.frc.team5160.utils.path.Point;
import org.usfirst.frc.team5160.utils.path.PursuitController;

import edu.wpi.first.wpilibj.IterativeRobot;

class Conditions{
	double momentum, imbalance;  
	Conditions(){
		momentum = 0.97;//FRandRange((float) 0.97f, 0.98f);
		imbalance = 0;//RMath.FRandRange(-0.01f, 0.01f);
	}
}

class Parameters {
	double Kp, Ka, Kb, Kv, Lf, La; 
}

public class PidOptimizerMain {
	
	public static Point[] points;

	public static void main(String[] args){
		Conditions[] conds = new Conditions[10];
		for(int i = 0; i < 10; i++){
			conds[i] = new Conditions();
		}
		double minScore = 5000;
		for(double Kp = 0.06; Kp < 0.2; Kp += 0.03){
			for(double Kv = 0.0; Kv < 0.01; Kv += 0.1){
				//double Kb = 0, Ka = 0;
				for(double Kb = 0.0; Kb < 3; Kb += 0.3){
					for(double Ka = 0; Ka < 3; Ka += 0.3){
						PursuitController.Kp = Kp;
						PursuitController.Kv = Kv;
						PursuitController.Ka = Ka;
						PursuitController.Kb = Kb;
						double score = score(conds);
						if(score < minScore){
							minScore = score;
							System.out.println(minScore);
							System.out.println("Kp : " + Kp + " , Kv : "+ Kv + " , Kb : "+ Kb + " , Ka : "+ Ka);
							System.out.println("");
						}
					
					}
				}
			}
		}
		System.out.println(minScore);
	}
	
	public static double score(Conditions[] conds){
		double average = 0;
		for(int i = 0; i < 10; i++){
			PursuitController.Kp += RMath.FRand()*0.01;
			PursuitController.Kv += RMath.FRand()*0.01;
			PursuitController.Ka += RMath.FRand()*0.01;
			PursuitController.Kb += RMath.FRand()*0.01;
			Robot robot = new Robot();
			Conditions c = conds[i];
			MetaRobot meta = new MetaRobot(robot, c.momentum, c.imbalance, 0);
			
			robot.robotInit();
			robot.autonomousInit();
			
			for(int k = 0; k < 350; k++){
				meta.update();
				robot.autonomousPeriodic();
			}

			average += meta.report()/10.0;
			
			robot = new Robot();
			meta = new MetaRobot(robot, 0, 0, 0);
			
			robot.robotInit();
			robot.autonomousInit();
			
			
			for(int k = 0; k < 1; k++){
				meta.update();
				robot.autonomousPeriodic();
			}
			
		}
		//System.out.println(average);
		return average;
	}
	
	
	public static double[] toDoubles(Object[] objs){
		double[] tmp = new double[objs.length];
		for(int i = 0; i < objs.length; i++){
			tmp[i] = (double) objs[i];
		}
		return tmp;
	}
	/*
	 * 3.3524116457599002
Kp : 0.05 , Kv : 0.0 , Kb : 0.0 , Ka : 1.7999999999999998

3.262654562477004
Kp : 0.05 , Kv : 0.0 , Kb : 0.0 , Ka : 1.9999999999999998

2.7381125874571866
Kp : 0.05 , Kv : 0.0 , Kb : 1.2 , Ka : 0.2

2.591040936344001
Kp : 0.05 , Kv : 0.1 , Kb : 1.2 , Ka : 0.2

2.4005165170015967
Kp : 0.05 , Kv : 0.1 , Kb : 1.4 , Ka : 0.2

2.242920565332133
Kp : 0.05 , Kv : 0.2 , Kb : 1.2 , Ka : 0.2

2.1457425659696656
Kp : 0.05 , Kv : 0.30000000000000004 , Kb : 1.4 , Ka : 0.2

1.9690691779025093
Kp : 0.05 , Kv : 0.4 , Kb : 1.2 , Ka : 0.2

1.8355515720318973
Kp : 0.05 , Kv : 0.5 , Kb : 1.4 , Ka : 0.2

1.7442262818662642
Kp : 0.05 , Kv : 0.7 , Kb : 1.2 , Ka : 0.2

1.6842047835861
Kp : 0.05 , Kv : 0.7999999999999999 , Kb : 1.2 , Ka : 0.2

1.4853588811545217
Kp : 0.060000000000000005 , Kv : 0.0 , Kb : 1.4 , Ka : 0.2

1.4777730437195105
Kp : 0.060000000000000005 , Kv : 0.1 , Kb : 1.4 , Ka : 0.2

1.3915288756239077
Kp : 0.06 , Kv : 0.2 , Kb : 1.4 , Ka : 0.2

1.3373025832378995
Kp : 0.06 , Kv : 0.30000000000000004 , Kb : 1.4 , Ka : 0.2

1.256008193205099
Kp : 0.06 , Kv : 0.4 , Kb : 1.4 , Ka : 0.2


1.1416764889248396
Kp : 0.06999999999999999 , Kv : 0.5 , Kb : 0.8 , Ka : 0.0

1.0737758484191022
Kp : 0.06999999999999999 , Kv : 0.7 , Kb : 1.0 , Ka : 0.0

	 */
}
