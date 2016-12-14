/* Write by
 * Ekkalak Leelasornchai 5830622421 
 * Burin Naowarat 5831034621
 * Progmeth project
 */ 
package exception;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import logic.Resources;
import logic.Scorebar;
import screen.GameScreen;

public class ImageNotFoundException extends Exception{
	private int code;
	public ImageNotFoundException(int code){
		this.code = code;
		if(code==0){
			System.err.println("humanwalk-clipart.png is not found");
			
		}
		else if(code==1){
			System.err.println("clock-clipart.png is not found");
		}
		else
			System.err.println("all images are not found");
	
	}
	
	public int getCode(){
		return code;
	}
}
