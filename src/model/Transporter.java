package model;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Transporter implements IDrawable,IPassengerDrawable{
	protected double x,y;
	protected int direction;
	protected Color color; 
	protected static final int size=50;
	protected int positionIndex;
	protected static final int speed=5;
	
	ArrayList<Passenger> passengers = new ArrayList<>();
	
	public Transporter(double x,double y,Color color,int direction) {
		// TODO Auto-generated constructor stub
		setX(x);
		setY(y);
		setColor(color);
		setDirection(direction);
		
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.setFill(color);
		gc.fillOval(getX()-size/2, getY()-size/2, size, size);
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(2);
		gc.strokeOval(getX()-size/2, getY()-size/2, size, size);
		gc.setGlobalAlpha(0.5);
		gc.setFill(Color.WHITE);
		gc.fillOval(getX()-size/2, getY()-size/2, size, size);
		gc.setGlobalAlpha(1.0);
	}
	public void addPassenger(Passenger p){
		p.setSize(2);
		p.setColor(this.getColor().deriveColor(50, 100, 100, 50));
		passengers.add(p);
	}
	
	public synchronized int getNumberOfPassengers(){
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
	public synchronized void setX(double x){
		this.x = x;
	}
	public synchronized void setY(double y){
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
		int n = 0;
		for(Passenger p : passengers){
			p.setX(getX()+5+7.5*(n%2));
			p.setY(getY()+5+7.5*(n/2));
			n++;
			p.draw(gc);
		}
	}
	
	public boolean isAddPassenger(){
		return false;
		
	}
	
	public synchronized boolean isFull(){
		return passengers.size()>=4;
	}
	
	public synchronized void drop(int index){
		passengers.remove(index);
	}
	
	public synchronized void transfer(int index, Station st){
		st.recievePeople(passengers.remove(index));
	}
	
}
