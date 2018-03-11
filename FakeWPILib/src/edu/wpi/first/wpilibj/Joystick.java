package edu.wpi.first.wpilibj;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.ivan.xinput.XInputDevice;
import com.ivan.xinput.exceptions.XInputNotLoadedException;

import fake.wpilib.RobotMain;

public class Joystick implements KeyListener{
	double x;
	double y;
	double rotX = 0;
	double rotY = 0;
	double lift = 0;
	public Joystick(int i) {
		RobotMain.fig2.addKeyListener(this);
		try {
			XInputDevice[] devices = XInputDevice.getAllDevices();
		} catch (XInputNotLoadedException e) {
			e.printStackTrace();
		}
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
		if(i == 4){
		return rotX;
		}
		else{
			return rotY; 
		}
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
		else if(e.getKeyChar() == 'q'){
			lift = -1;
		}
		else if(e.getKeyChar() == 'e'){
			lift =  1;
		}
		else if(e.getKeyCode() == KeyEvent.VK_UP){
			rotY =  1;
		}
		else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			rotX =  1;
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN){
			rotY =  -1;
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT){
			rotX=  -1;
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
			y = 0;
		}
		else if(e.getKeyChar() == 'q'){
			lift = 0;
		}
		else if(e.getKeyChar() == 'e'){
			lift =  0;
		}
		else if(e.getKeyCode() == KeyEvent.VK_UP){
			rotY =  0;
		}
		else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			rotX =  0;
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN){
			rotY =  0;
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT){
			rotX=  0;
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public boolean getRawButtonPressed(int i) {
		if(i == 3 && lift > 0){
			return true;
		}
		else if(i == 4 && lift < 0){
			return true;
		}
		return false;
	}

}
