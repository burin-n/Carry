package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Transporter implements IDrawable{
	protected double x,y;
	protected int direction;
	protected Color color;
	
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
	public void setDirection(int direction){
		this.direction = direction;
	}
	public int getDirection(){
		return this.direction;
	}
	public void setColor(Color color){
		this.color = color;
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
	
}
