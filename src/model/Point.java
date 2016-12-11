package model;

public class Point {
	private int x,y;
	
	public Point(int x,int y){
		this.setX(x);
		this.setY(y);
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
	
	public Point getVector(Point a){
		return new Point(a.getX()-x,a.getY()-y);
	}
	
	public String toString(){
		return x +":"+ y;
	}
	
	public boolean isSamePoint(Point x){
		if(x.getX() == this.x && x.getY() == this.y) return true;
		else return false;
	}
	
	public boolean isSamePoint(Station x){
		if(x.getCenterX() == this.x && x.getCenterY() == this.y) return true;
		return false;
	}
}
