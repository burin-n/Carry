/* Write by
 * Ekkalak Leelasornchai 5830622421 
 * Burin Naowarat 5831034621
 * Progmeth project
 */ 
package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class SquarePassenger extends Passenger{

	public SquarePassenger(double x, double y,double size,Color color) {
		super(x, y, size, color);
		// TODO Auto-generated constructor stub
	}


	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.setFill(color);
		gc.setGlobalAlpha(1);
		gc.fillRect(getX(), getY(), 10/size, 10/size);
	}


	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "Square";
	}
	
}
