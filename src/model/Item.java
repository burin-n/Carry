package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Item {
	private int number;
	public int getNumber(){
		return this.number;
	}
	public void addItem(){
		this.number++;
	}
	public boolean canUse(){
		if(this.getNumber() == 0)return false;
		else return true;
	}
	public void useItem(){
		if(canUse())this.number--;
	}
}
