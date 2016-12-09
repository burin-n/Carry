package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class CrossPassenger extends Passenger{

	public CrossPassenger(double x, double y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}


	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.setFill(Color.BLACK);		
		double[] X1 = new double[]{getX()+3,getX()+7,getX()+7,getX()+10,getX()+10,getX()+7,getX()+7,getX()+3,getX()+3,getX(),getX(),getX()+3};
		double[] Y1 = new double[]{getY(),getY(),getY()+3,getY()+3,getY()+7,getY()+7,getY()+10,getY()+10,getY()+7,getY()+7,getY()+3,getY()+3};
		gc.fillPolygon(X1,Y1,12);
	}
	
}
