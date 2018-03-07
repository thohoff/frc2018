package edu.wpi.first.wpilibj;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import fake.wpilib.RobotMain;

public class Joystick implements KeyListener{
	double x;
	double y;
	double rot = 90;
	double theta = 0;
	public Joystick(int i) {
		RobotMain.fig2.addKeyListener(this);
		System.out.println("joy");
	}

	public double getX() {
		
		return x;
	}

	public double getY() {
		// TODO Auto-generated method stub
		return y;
	}

	public double getX(Object kRight) {
		// TODO Auto-generated method stub
		return x;
	}

	public boolean getRawButton(int i) {
		return false;
	}

	public double getRawAxis(int i) {
		// TODO Auto-generated method stub
		return theta;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyChar() == 'a'){
			x =- 1;
		}
		else if(e.getKeyChar() == 'd'){
			x = 1;
		}
		else if(e.getKeyChar() == 'w'){
			y = 1;
		}
		else if(e.getKeyChar() == 's'){
			y =  - 1;
		}
		else if(e.getKeyChar() == 'e'){
			theta = 1;
		}
		else if(e.getKeyChar() == 'q'){
			theta =  - 1;
		}
		else if(e.getKeyCode() == KeyEvent.VK_UP){
			rot =  90;
		}
		else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			rot =  0;
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN){
			rot =  -90;
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT){
			rot =  180;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyChar() == 'a'){
			x = 0;
		}
		else if(e.getKeyChar() == 'd'){
			x = 0;
		}
		else if(e.getKeyChar() == 'w'){
			y = 0;
		}
		else if(e.getKeyChar() == 's'){
			y =0;
		}
		else if(e.getKeyChar() == 'q'){
			theta = 0;
		}
		else if(e.getKeyChar() == 'e'){
			theta =0;
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public double getAngle() {
		return rot;
	}

}
