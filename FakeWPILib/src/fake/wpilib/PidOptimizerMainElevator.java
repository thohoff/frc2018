package fake.wpilib;

import java.awt.Color;

import org.chargers.frc2018.Robot;
import org.chargers.frc2018.subsystems.Elevator;
import org.usfirst.frc.team5160.utils.RMath;
import org.usfirst.frc.team5160.utils.path.FalconLinePlot;
import org.usfirst.frc.team5160.utils.path.Path;
import org.usfirst.frc.team5160.utils.path.Point;
import org.usfirst.frc.team5160.utils.path.PursuitController;

import edu.wpi.first.wpilibj.IterativeRobot;

public class PidOptimizerMainElevator {
	
	public static Point[] points;

	public static void main(String[] args){
		double[] conds = new double[50];
		for(int i = 0; i < 50; i++){
			conds[i] = RMath.FRandRange(0.965f, 0.985f);
		}
		double minScore = 100000;
		for(double Kp = 0.1; Kp < 0.7; Kp += 0.1){
			for(double Kv = 0; Kv < 1; Kv += 0.1){
				for(double Kd = 0; Kd < 6; Kd += 1){
					for (double Ka = 0; Ka < 0.5; Ka += 0.1){
					Elevator.p = Kp;
					Elevator.v = Kv;
					Elevator.d = Kd;
					Elevator.a = Ka;
						double score = score(conds);
						if(score < minScore){
							minScore = score;
							System.out.println(minScore);
							System.out.println("Kp : " + Kp + " , Kv : "+ Kv + " , Kd : "+ Kd + ", Ka " + Ka );
							System.out.println("");				
						}
					}
				}
			}
		}
		System.out.println(minScore);
	}
	
	public static double score(double[] conds){
		double average = 0;
		for(int i = 0; i < 50; i++){
			Robot robot = new Robot();
			Double c = conds[i];
			MetaRobot meta = new MetaRobot(robot, 0, 0, conds[i]);

			robot.robotInit();
			meta.elevator.setTarget(100);			
			for(int k = 0; k < 1000; k++){
				meta.update();
				robot.teleopPeriodic();
			}
			/*meta.elevator.setTarget(0);
			for(int k = 0; k < 150; k++){
				meta.update();
				robot.teleopPeriodic();
			}*/
			average += meta.reportElevator()/50.0/200.0;
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
	
}
