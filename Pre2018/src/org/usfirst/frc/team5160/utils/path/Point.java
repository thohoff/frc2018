package org.usfirst.frc.team5160.utils.path;

public class Point {
	public double x = 0;
	public double y = 0;
	public double velocity = 0;
	public double angle = 0;
	
	public Point(Point p){
		this.x = p.x;
		this.y = p.y;
		this.angle = p.angle;
		this.velocity = p.velocity;
	}
	
	public Point(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public Point(double x, double y, double angle){
		this.x = x;
		this.y = y;
		this.angle = angle; 
	}

	public Point(double x, double y, double angle, double velocity) {
		this.x = x;
		this.y = y;
		this.angle = angle; 
		this.velocity = velocity;
	}
}
