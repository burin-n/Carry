package model;

import java.util.ArrayList;


import javafx.scene.canvas.GraphicsContext;

public abstract class Station implements IDrawable,IPassengerDrawable{
	protected double x,y;
	protected ArrayList<Passenger> passengers = new ArrayList<>();
	public Station(double x, double y){
		this.setX(x);
		this.setY(y);
	}
	public Passenger dequeuePassengers(){
		if(this.passengers.size() == 0)return null;
		
		Passenger temp = this.passengers.get(0);
		for(int i=this.passengers.size()-1;i>0;i--){
			this.passengers.get(i).setX(this.passengers.get(i-1).getX());
			this.passengers.get(i).setY(this.passengers.get(i-1).getY());
		}
		for(int i=0;i<this.passengers.size()-1;i++)
			this.passengers.set(i, this.passengers.get(i+1));
		this.passengers.remove(this.passengers.size()-1);
		return temp;
		
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
	
	public abstract double getCenterX();
	public abstract double getCenterY();
}
