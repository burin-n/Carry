package model;

import javafx.scene.canvas.GraphicsContext;

public class TriangleStation extends Station{

	public TriangleStation(double x, double y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}


	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.setLineWidth(4.0);
		
		double[] X = new double[]{getX()+11.0,getX()+22,getX()};
		double[] Y = new double[]{getY(),getY()+22,getY()+22};
		gc.strokePolygon(X,Y,3);
		
	}


	@Override
	public void draw_passengers(GraphicsContext gc) {
		// TODO Auto-generated method stub
		
	}
	
}
