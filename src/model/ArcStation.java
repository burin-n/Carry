package model;

import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.ArcType;

public class ArcStation extends Station{

	public ArcStation(double x, double y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	public void AddPassenger(){
		Random R = new Random();
		int r = R.nextInt(4);
		Passenger p = null;
		if(r%4 == 0)p = new SquarePassenger(getX() + 30.5 + getNumberOfPassengers()*12, this.y);
		else if(r%4 == 1)p = new ArcPassenger(getX() + 30.5 + getNumberOfPassengers()*12, this.y);
		else if(r%4 == 2)p = new CrossPassenger(getX() + 30.5 + getNumberOfPassengers()*12, this.y);
		else if(r%4 == 3)p = new TrianglePassenger(getX() + 30.5 + getNumberOfPassengers()*12, this.y);
		
		passengers.add(p);
	}
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.setLineWidth(4.0);
		gc.strokeArc(getX()-8, getY(), 42, 42, 55, 70, ArcType.ROUND);
	}



}
