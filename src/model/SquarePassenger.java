package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class SquarePassenger extends Passenger{

	public SquarePassenger(double x, double y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}


	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.setFill(Color.BLACK);
		gc.fillRect(getX(), getY(), 10, 10);
	}
	
}
