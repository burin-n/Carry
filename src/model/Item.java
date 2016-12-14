/* Write by
 * Ekkalak Leelasornchai 5830622421 
 * Burin Naowarat 5831034621
 * Progmeth project
 */ 
package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Item {
	private static int number;
	
	public Item(){
		number = 3;
	}
	
	public int getNumber(){
		return Item.number;
	}
	
	public void addItem(){
		Item.number++;
	}
	
	public void addItem(int n){
		Item.number+=n;
	}
	
	public boolean canUse(){
		if(this.getNumber() == 0)return false;
		else return true;
	}
	
	public void useItem(){
		if(canUse())Item.number--;
	}
	
}
