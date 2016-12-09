package model;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.canvas.GraphicsContext;

public abstract class Station implements IDrawable,IPassengerDrawable{
	protected double x,y;
	ArrayList<Passenger> passengers = new ArrayList<>();
	public Station(double x, double y){
		this.setX(x);
		this.setY(y);
	}
	

	public void draw_passengers(GraphicsContext gc) {
		// TODO Auto-generated method stub
		for(Passenger e: passengers){
			
			e.draw(gc);
		}
	}
	public int getNumberOfPassengers(){
		return passengers.size();
	}


	public double getX() {
		return x;
	}



	public void setX(double x) {
		this.x = x;
	}



	public double getY() {
		return y;
	}



	public void setY(double y) {
		this.y = y;
	}
	
}
