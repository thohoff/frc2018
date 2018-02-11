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
		momentum = RMath.FRandRange((float) 0.96, 1);
		imbalance = RMath.FRandRange(-0.01f, 0.01f);
	}
}

class Parameters {
	double Kp, Ka, Kb, Kv, Lf, La; 
}

public class PidOptimizerMain {
	
	public static Point[] points;

	public static void main(String[] args){
		Conditions[] conds = new Conditions[50];
		for(int i = 0; i < 50; i++){
			conds[i] = new Conditions();
		}
		double minScore = 50;
		for(double Kp = 0.06; Kp < 0.061; Kp += 0.02){
			for(double Kv = 0.1; Kv < 0.651; Kv += 0.05){
				//double Kb = 0, Ka = 0;
				for(double Kb = 0; Kb < 0.4; Kb += 0.05){
					for(double Ka = 0; Ka < 0.5; Ka += 0.1){
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
		for(int i = 0; i < 50; i++){
			Robot robot = new Robot();
			Conditions c = conds[i];
			MetaRobot meta = new MetaRobot(robot, c.momentum, c.imbalance);
			
			robot.robotInit();
			robot.autonomousInit();
			
			for(int k = 0; k < 400; k++){
				meta.update();
				robot.autonomousPeriodic();
			}

			average += meta.report()/50.0;
			
			robot = new Robot();
			meta = new MetaRobot(robot, 0, 0);
			
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
	
}
