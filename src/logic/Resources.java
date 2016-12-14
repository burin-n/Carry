/* Write by
 * Ekkalak Leelasornchai 5830622421 
 * Burin Naowarat 5831034621
 * Progmeth project
 */ 
package logic;

import exception.ImageNotFoundException;
import exception.SoundNotFoundException;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;

public class Resources {
	public static Resources instance = new Resources();
	public static Image clock,people;
	public static AudioClip soundNormal,soundExcited,soundClick,soundGameOver;
	
	private static boolean isFoundHuman;
	private static boolean isFoundClock;
	private static boolean isFoundExciting;
	private static boolean isFoundNormal;
	private static boolean isFoundClick;
	private static boolean isFoundGameOver;
	
	
	public Resources(){
		isFoundClock = false;
		isFoundClick = false;
		isFoundHuman = false;
		isFoundExciting = false;
		isFoundNormal = false;
		clock = null;
		people = null;
		soundNormal = null;
		soundExcited = null;
		
		try{
			getImage();
			getSound();
		}catch(ImageNotFoundException e){
			if(e.getCode() == 0) clock = new Image(ClassLoader.getSystemResourceAsStream("clock-clipart.png"));	
			else if(e.getCode() == 1) people = new Image(ClassLoader.getSystemResourceAsStream("humanwalk-clipart.png"));
 			e.printStackTrace();
		}
		catch (SoundNotFoundException e) {
			// TODO: handle exception
			if(e.getCode()%10 == 0) soundClick = new AudioClip(ClassLoader.getSystemResource("click-sound.mp3").toString()); 
			if((e.getCode()/10)%10==0)soundExcited = new AudioClip(ClassLoader.getSystemResource("exciting.mp3").toString());
			if((e.getCode()/100)%10 == 0 ) soundNormal = new AudioClip(ClassLoader.getSystemResource("normal.mp3").toString());
			if(e.getCode()/1000 ==0 ) soundGameOver = new AudioClip(ClassLoader.getSystemResource("GameOver.mp3").toString());
			e.printStackTrace();
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}



	private void getImage() throws ImageNotFoundException{
		if(ClassLoader.getSystemResourceAsStream("humanwalk-clipart.png")!=null) isFoundHuman = true;
		if(ClassLoader.getSystemResourceAsStream("clock-clipart.png")!=null) isFoundClock = true; 
	
		if(!isFoundHuman){
			if(!isFoundClock) throw new ImageNotFoundException(2);
			else throw new ImageNotFoundException(0);
		}
		else if(!isFoundClock) throw new ImageNotFoundException(1);
		Resources.clock = new Image(ClassLoader.getSystemResourceAsStream("clock-clipart.png"));	
		Resources.people = new Image(ClassLoader.getSystemResourceAsStream("humanwalk-clipart.png"));
	}

	private void getSound() throws SoundNotFoundException{
		if(ClassLoader.getSystemResource("exciting.mp3")!=null) isFoundExciting = true;
		if(ClassLoader.getSystemResource("normal.mp3")!=null) isFoundNormal = true;
		if(ClassLoader.getSystemResource("click-sound.mp3")!=null) isFoundClick = true;
		if(ClassLoader.getSystemResource("GameOver.mp3")!=null) isFoundGameOver = true;
		int code = 0;
		if(!isFoundClick) code += 1;
		if(!isFoundExciting) code += 10;
		if(!isFoundNormal) code += 100;
		if(!isFoundGameOver)  code+= 1000;
		if(code!=0) throw new SoundNotFoundException(code);
		soundExcited = new AudioClip(ClassLoader.getSystemResource("exciting.mp3").toString());
		soundNormal = new AudioClip(ClassLoader.getSystemResource("normal.mp3").toString());
		soundClick = new AudioClip(ClassLoader.getSystemResource("click-sound.mp3").toString());
		soundGameOver = new AudioClip(ClassLoader.getSystemResource("GameOver.mp3").toString());
	}
	
	public static boolean isFoundHuman() {
		return isFoundHuman;
	}

	public static boolean isFoundClock() {
		return isFoundClock;
	}

	public static boolean isFoundExciting() {
		return isFoundExciting;
	}

	public static boolean isFoundNormal() {
		return isFoundNormal;
	}
	public static boolean isFoundClick() {
		return isFoundClick;
	}

	public static boolean isFoundGameOver() {
		return isFoundGameOver;
	}

}
