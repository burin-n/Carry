package model;

import java.util.Random;

import controller.LineController;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
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
		if(r%4 == 0)p = new SquarePassenger(getX() + 30.5 + getNumberOfPassengers()*12, this.y,1,Color.BLACK);
		else if(r%4 == 1)p = new ArcPassenger(getX() + 30.5 + getNumberOfPassengers()*12, this.y,1,Color.BLACK);
		else if(r%4 == 2)p = new CrossPassenger(getX() + 30.5 + getNumberOfPassengers()*12, this.y,1,Color.BLACK);
		else if(r%4 == 3)p = new TrianglePassenger(getX() + 30.5 + getNumberOfPassengers()*12, this.y,1,Color.BLACK);
		
		passengers.add(p);
	}
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.setLineWidth(4.0);
		gc.setStroke(Color.BLACK);
		gc.strokeArc(getX()-8, getY(), 42, 42, 55, 70, ArcType.ROUND);
	}

	@Override
	public double getCenterX() {
		// TODO Auto-generated method stub
		return x+10.5;
	}

	@Override
	public double getCenterY() {
		// TODO Auto-generated method stub
		return y+10.5;
	}



}
