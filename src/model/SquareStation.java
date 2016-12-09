package model;

import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class SquareStation extends Station{
	
	public SquareStation(double x, double y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	public void AddPassenger(){
		Random R = new Random();
		int r = R.nextInt(4);
		Passenger p = null;
		if(r%4 == 0)p = new SquarePassenger(getX() + 25 + getNumberOfPassengers()*12, this.y,1,Color.BLACK);
		else if(r%4 == 1) p = new ArcPassenger(getX() + 25 + getNumberOfPassengers()*12, this.y,1,Color.BLACK);
		else if(r%4 == 2) p = new CrossPassenger(getX() + 25 + getNumberOfPassengers()*12, this.y,1,Color.BLACK);
		else if(r%4 == 3) p = new TrianglePassenger(getX() + 25 + getNumberOfPassengers()*12, this.y,1,Color.BLACK);
		passengers.add(p);
		
	}
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.setLineWidth(4.0);
		gc.strokeRect(getX(), getY(), 20, 20);
	}


}
