package model;

import javafx.scene.canvas.GraphicsContext;

public class SquareStation extends Station{

	public SquareStation(double x, double y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}


	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.fillRect(this.x, this.y, 50, 50);
	}
	
}
