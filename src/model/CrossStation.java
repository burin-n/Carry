/* Write by
 * Ekkalak Leelasornchai 5830622421 
 * Burin Naowarat 5831034621
 * Progmeth project
 */ 
package model;

import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class CrossStation extends Station{

	public CrossStation(double x, double y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	public void AddPassenger(){
		Random R = new Random();
		int r = R.nextInt(3);
		Passenger p = null;
		if(r%3 == 0)p = new SquarePassenger(getX() + 28 + getNumberOfPassengers()*12, this.y,1,Color.BLACK);
		else if(r%3 == 1) p = new ArcPassenger(getX() + 28 + getNumberOfPassengers()*12, this.y,1,Color.BLACK);
		else if(r%3 == 2) p = new TrianglePassenger(getX() + 28 + getNumberOfPassengers()*12, this.y,1,Color.BLACK);
		passengers.add(p);
		
	}
	
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.setLineWidth(4.0);
		gc.setStroke(Color.BLACK);
		double[] X1 = new double[]{getX()+7,getX()+17,getX()+17,getX()+24,getX()+24,getX()+17,getX()+17,getX()+7,getX()+7,getX(),getX(),getX()+7};
		double[] Y1 = new double[]{getY(),getY(),getY()+7,getY()+7,getY()+17,getY()+17,getY()+24,getY()+24,getY()+17,getY()+17,getY()+7,getY()+7};
		gc.strokePolygon(X1,Y1,12);
	}

	@Override
	public double getCenterX() {
		// TODO Auto-generated method stub
		return x+12;
	}

	@Override
	public double getCenterY() {
		// TODO Auto-generated method stub
		return y+12;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "Cross";
	}

	@Override
	public synchronized void recievePeople(Passenger p) {
		// TODO Auto-generated method stub
		Passenger newP = null;
		if(p.getType().compareTo("Square") == 0 ) newP = new SquarePassenger(getX() + 28 + getNumberOfPassengers()*12, this.y,1,Color.BLACK);
		else if(p.getType().compareTo("Arc") == 0 ) newP = new ArcPassenger(getX() + 28 + getNumberOfPassengers()*12, this.y,1,Color.BLACK);
		else if(p.getType().compareTo("Cross") == 0 ) newP = new CrossPassenger(getX() + 28 + getNumberOfPassengers()*12, this.y,1,Color.BLACK);
		else if(p.getType().compareTo("Triangle") == 0 ) newP = new TrianglePassenger(getX() + 28 + getNumberOfPassengers()*12, this.y,1,Color.BLACK);
		if(newP==null) System.out.println("recievePeople:null");
		else System.out.println("recievePeople:mainull");
		tempPassengers.add(newP);
	}

	public synchronized void updateRecievePeople(){
		for(Passenger p : tempPassengers){
			Passenger newP = null;
			if(p.getType().compareTo("Square") == 0 ) newP = new SquarePassenger(getX() + 28 + getNumberOfPassengers()*12, this.y,1,Color.BLACK);
			else if(p.getType().compareTo("Arc") == 0 ) newP = new ArcPassenger(getX() + 28 + getNumberOfPassengers()*12, this.y,1,Color.BLACK);
			else if(p.getType().compareTo("Cross") == 0 ) newP = new CrossPassenger(getX() + 28 + getNumberOfPassengers()*12, this.y,1,Color.BLACK);
			else if(p.getType().compareTo("Triangle") == 0 ) newP = new TrianglePassenger(getX() + 28 + getNumberOfPassengers()*12, this.y,1,Color.BLACK);
			passengers.add(newP);
		}
		tempPassengers.clear();

	}
}
