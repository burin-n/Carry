package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

public class TrianglePassenger extends Passenger{

	public TrianglePassenger(double x, double y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}


	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.setFill(Color.BLACK);		
		double[] X = new double[]{getX()+5,getX()+10,getX()};
		double[] Y = new double[]{getY(),getY()+10,getY()+10};
		gc.fillPolygon(X,Y,3);
	}
	
}
