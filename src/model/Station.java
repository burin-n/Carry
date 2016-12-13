package model;


import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import logic.GameLogic;
import logic.Scorebar;

public abstract class Station implements IDrawable,IPassengerDrawable{
	protected double x,y;
	//protected ArrayList<Passenger> passengers;
	protected CopyOnWriteArrayList<Passenger> passengers;
	protected CopyOnWriteArrayList<Passenger> tempPassengers;
	protected boolean isCrowded;
	protected double crowdedState;
	protected Set<Color> lines;
	
	public Station(double x, double y){
		lines = new HashSet<>();
		passengers = new CopyOnWriteArrayList<Passenger>();
		tempPassengers = new CopyOnWriteArrayList<>();
		this.setX(x);
		this.setY(y);
		
		
		crowdedState = 0.0;
		isCrowded = false;
		
		Thread t = new Thread(()->{
			while(true){
			try {
					Thread.sleep(10000);
					Random r = new Random();
					if( r.nextInt(2) == 1 ){
						for(int i=0;i< r.nextInt(3)+1;i++){
							AddPassenger();
						}
					}
					if(passengers.size()>7 ) GameLogic.isGameOver = /*true*/false;
					else if( passengers.size() > 4 ){
						//Scorebar.getInstance().setNumberOfCrowded(Scorebar.getInstance().getNumberOfCrowded()+1);
						isCrowded = true;
					}
					else{
						crowdedState = 0.0;
						//Scorebar.getInstance().setNumberOfCrowded(Scorebar.getInstance().getNumberOfCrowded()-1);
						isCrowded = false;
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		ThreadHolder.instance.addThread(t);
		t.start();
	}
	
	public void drawCrowded(GraphicsContext gc){
		if(!isCrowded ) return;
		gc.setGlobalAlpha(crowdedState);
		gc.setFill(Color.BLACK);
		gc.fillOval(getCenterX()-40, getCenterY()-40, 80, 80);
		gc.setGlobalAlpha(1.0);
		crowdedState = crowdedState >= 0.5 ? 0.05 : crowdedState + 0.05;
	}
	
	
	public synchronized Passenger dequeuePassengers(){
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
	public synchronized int getNumberOfPassengers(){
		return passengers.size();
	}


	public double getX() {
		return x;
	}
	
	public abstract void AddPassenger();



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
	public abstract String getType();
	
	public void addLine(Color c){
		//TODO rew rew
		lines.add(c);
	}
	
	public void removeLine(Color l){
		//TODO naja
		lines.remove(l);
	}
	
	public boolean canGo(String type){
		boolean canGo = false;
		for(Color c : lines){
			 canGo = canGo || LineHolder.getInstance().getLine(c).canGo(type);
		}
		return canGo;
	}
	public boolean isClowded(){
		return isCrowded;
	}
	public abstract void recievePeople(Passenger p);
	public abstract void updateRecievePeople();
}
