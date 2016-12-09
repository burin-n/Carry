package model;

public class Station {
	private int shape = 0;
	private int x,y;
	public Station(int shape, int x, int y){
		this.setX(x);
		this.setY(y);
		this.setShape(shape);
	}
	
	
	
	public int getShape() {
		return shape;
	}

	public void setShape(int shape) {
		this.shape = shape;
	}



	public int getX() {
		return x;
	}



	public void setX(int x) {
		this.x = x;
	}



	public int getY() {
		return y;
	}



	public void setY(int y) {
		this.y = y;
	}
	
}
