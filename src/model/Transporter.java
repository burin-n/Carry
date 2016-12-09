package model;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Transporter implements IDrawable,IPassengerDrawable{
	protected double x,y;
	protected int direction;
	protected Color color; 
	ArrayList<Passenger> passengers = new ArrayList<>();
	
	public Transporter(double x,double y,Color color) {
		// TODO Auto-generated constructor stub
		setX(x);
		setY(y);
		setColor(color);
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.setFill(color);
		gc.fillOval(getX(), getY(), 20, 20);
	}
	
	public void addPassenger(Passenger p){
		int n = getNumberOfPassengers();
		p.setX(getX()+5+7.5*(n%2));
		p.setY(getY()+5+7.5*(n/2));
		p.setSize(2);
		p.setColor(this.getColor().deriveColor(50, 100, 100, 50));
		passengers.add(p);
	}
	public int getNumberOfPassengers(){
		return passengers.size();
	}

	public void setDirection(int direction){
		this.direction = direction;
	}
	public int getDirection(){
		return this.direction;
	}
	public void setColor(Color color){
		this.color = color;
	}
	public Color getColor(){
		return this.color;
	}
	public void setX(double x){
		this.x = x;
	}
	public void setY(double y){
		this.y = y;
	}
	public double getX(){
		return this.x;
	}
	public double getY(){
		return this.y;
	}

	@Override
	public void draw_passengers(GraphicsContext gc) {
		// TODO Auto-generated method stub
		for(Passenger p : passengers){
			p.draw(gc);
		}
	}
	
}
