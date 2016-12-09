package model;

import java.util.Random;

import javafx.scene.canvas.GraphicsContext;

public class SquareStation extends Station{
	
	public SquareStation(double x, double y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.setLineWidth(4.0);
		gc.strokeRect(getX(), getY(), 20, 20);
	}
	public void AddPassenger(){
		//Random R = new Random(4);
		SquarePassenger Sp = new SquarePassenger(getX() + 25 + getNumberOfPassengers()*5, this.y);
		passengers.add(Sp);
		
	}
	public int getNumberOfPassengers(){
		return passengers.size();
	}
	@Override
	public void draw_passengers(GraphicsContext gc) {
		// TODO Auto-generated method stub
		for(Passenger e: passengers){
			
			e.draw(gc);
		}
	}
}
