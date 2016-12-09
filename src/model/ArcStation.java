package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.ArcType;

public class ArcStation extends Station{

	public ArcStation(double x, double y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}


	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.setLineWidth(4.0);
		gc.strokeArc(getX(), getY(), 50, 50, 55, 70, ArcType.ROUND);
	}


	@Override
	public void draw_passengers(GraphicsContext gc) {
		// TODO Auto-generated method stub
		
	}
	
}
