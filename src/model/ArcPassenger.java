package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

public class ArcPassenger extends Passenger{

	public ArcPassenger(double x, double y,double size,Color color) {
		super(x, y, size, color);
		// TODO Auto-generated constructor stub
	}


	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.setFill(color);

		gc.setGlobalAlpha(1);
		gc.fillArc(getX()-5, getY(), 20/size, 21/size, 55, 70, ArcType.ROUND);
	}


	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "Arc";
	}
	
}
