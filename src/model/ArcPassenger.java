package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

public class ArcPassenger extends Passenger{

	public ArcPassenger(double x, double y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}


	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.setFill(Color.BLACK);
		gc.fillArc(getX()-5, getY(), 20, 21, 55, 70, ArcType.ROUND);
	}
	
}
