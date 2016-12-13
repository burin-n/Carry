package exception;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import logic.Scorebar;
import screen.GameScreen;

public class ImageNotFoundException extends Exception{
	public ImageNotFoundException(int p){
		if(p==0){
			System.out.println("humanwalk-clipart.png is not found");
			Scorebar.clock = new Image(ClassLoader.getSystemResourceAsStream("clock-clipart.png"));	
		}
		else if(p==1){
			System.out.println("clock-clipart.png is not found");
			Scorebar.people = new Image(ClassLoader.getSystemResourceAsStream("humanwalk-clipart.png"));
		}
		else
			System.out.println("all images are not found");
	
	}
}
