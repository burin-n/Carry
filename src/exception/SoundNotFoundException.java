package exception;

import javafx.scene.media.AudioClip;
import logic.Resources;

public class SoundNotFoundException extends Exception{
	private int p;
	public SoundNotFoundException(int p){
		this.p = p;
		
		if(p%10 == 1) System.out.println("click-sound.mp3 is not found");
		if(p/100 == 1){
			System.out.println("normal.mp3 is not found");
		}
		if((p/10)%10 == 1){
			System.out.println("exciting.mp3 is not found");
		}
//		else{
//			System.out.println("sound is not found");
//		}
	}
	
	public int getCode(){
		return p;
	}
}
