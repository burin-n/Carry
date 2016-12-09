package model;

import javafx.scene.canvas.GraphicsContext;

public class CrossStation extends Station{

	public CrossStation(double x, double y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}


	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.setLineWidth(4.0);
		
		double[] X1 = new double[]{getX()+7,getX()+17,getX()+17,getX()+24,getX()+24,getX()+17,getX()+17,getX()+7,getX()+7,getX(),getX(),getX()+7};
		double[] Y1 = new double[]{getY(),getY(),getY()+7,getY()+7,getY()+17,getY()+17,getY()+24,getY()+24,getY()+17,getY()+17,getY()+7,getY()+7};
		gc.strokePolygon(X1,Y1,12);
	}


	@Override
	public void draw_passengers(GraphicsContext gc) {
		// TODO Auto-generated method stub
		
	}
	
}
