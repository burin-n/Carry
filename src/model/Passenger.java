package model;

import javafx.scene.paint.Color;

public abstract class Passenger implements IDrawable{
	protected double x,y,size;
	Color color;
	public Passenger(double x, double y,double size,Color color){
		this.setX(x);
		this.setY(y);
		this.setSize(size);
		this.setColor(color);
	}
	
	
	public void setColor(Color color){
		this.color = color;
	}

	public double getX() {
		return x;
	}



	public void setX(double x) {
		this.x = x;
	}

	public double getSize() {
		return this.size;
	}



	public void setSize(double size) {
		this.size = size;
	}

	public double getY() {
		return y;
	}



	public void setY(double y) {
		this.y = y;
	}
}
