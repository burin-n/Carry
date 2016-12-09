package model;

public abstract class Station implements IDrawable{
	protected double x,y;
	public Station(double x, double y){
		this.setX(x);
		this.setY(y);
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
