/* Write by
 * Ekkalak Leelasornchai 5830622421 
 * Burin Naowarat 5831034621
 * Progmeth project
 */ 
package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class CrossPassenger extends Passenger{

	public CrossPassenger(double x, double y,double size,Color color) {
		super(x, y, size, color);
		// TODO Auto-generated constructor stub
	}


	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.setFill(color);	

		gc.setGlobalAlpha(1);
		double[] X1 = new double[]{getX()+3/size,getX()+7/size,getX()+7/size,getX()+10/size,getX()+10/size,getX()+7/size,getX()+7/size,getX()+3/size,getX()+3/size,getX(),getX(),getX()+3/size};
		double[] Y1 = new double[]{getY(),getY(),getY()+3/size,getY()+3/size,getY()+7/size,getY()+7/size,getY()+10/size,getY()+10/size,getY()+7/size,getY()+7/size,getY()+3/size,getY()+3/size};
		gc.fillPolygon(X1,Y1,12);
	}


	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "Cross";
	}
	
}
