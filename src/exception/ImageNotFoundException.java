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
			System.out.println("humanwalk-clipart.png is not found");
			
		}
		else if(code==1){
			System.out.println("clock-clipart.png is not found");
		}
		else
			System.out.println("all images are not found");
	
	}
	
	public int getCode(){
		return code;
	}
}
