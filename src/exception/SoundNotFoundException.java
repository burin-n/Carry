package exception;

import javafx.scene.media.AudioClip;
import logic.Resources;

public class SoundNotFoundException extends Exception{
	private int p;
	public SoundNotFoundException(int p){
		this.p = p;
		
		if(p%10 == 1) System.err.println("click-sound.mp3 is not found");
		if((p/100)%10 == 1){
			System.err.println("normal.mp3 is not found");
		}
		if((p/10)%10 == 1){
			System.err.println("exciting.mp3 is not found");
		}
		if(p/1000 == 1) System.err.println("GaveOver.mp3 is not found");

	}
	
	public int getCode(){
		return p;
	}
}
