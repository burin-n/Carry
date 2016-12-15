/* Write by
 * Ekkalak Leelasornchai 5830622421 
 * Burin Naowarat 5831034621
 * Progmeth project
 */ 
package logic;

import java.io.File;

import exception.ImageNotFoundException;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class Scorebar {
	private static int level = 1;
	private static Scorebar instance = new Scorebar();
	private static int score,time,day=0,week=1;
	private String[] days = {"MON","TUE","WED","THU","FRI","SAT","SUN"};
	private int numberOfCrowded;

	public Scorebar(){

		setNumberOfCrowded(0);
		setScore(0);
		setTime(0);
	}

	
	public synchronized int getScore() {
		return score;
	}

	public synchronized void setScore(int score) {
		this.score = score;
	}

	public synchronized int getTime() {
		return time;
	}

	public synchronized void setTime(int time) {
		this.time = time;
	}
	
	public void draw(GraphicsContext gc){
		
		gc.setGlobalAlpha(1);
		gc.setFont(Font.font("Tahoma",FontPosture.ITALIC,20));
		gc.setFill(Color.BLACK);
		if(Resources.isFoundHuman() ) gc.drawImage(Resources.people, 680, 4, 60, 60);
		else gc.fillText("Score:", 690, 45);
		if(Resources.isFoundClock() ) gc.drawImage(Resources.clock, 870, 4, 60, 60);
		else gc.fillText("Day:", 880, 45);
		
		
		gc.setFill(Color.DIMGREY);
		gc.setFont(Font.font("Tahoma",32));
		if(score<10)
			gc.fillText(""+score,800, 48);
		else if(score<100)
			gc.fillText(""+score, 790, 48);
		else if(score<1000)
			gc.fillText(""+score, 780, 48);
		else if(score<10000)
			gc.fillText(""+score, 770, 48);
		else if(score<100000)
			gc.fillText(""+score, 760, 48);
		else 
			gc.fillText(""+score, 750, 48);
		gc.fillText(days[day%7], 947, 48);
		//gc.strokeRect(934, 70, 90, 30);
		//gc.setFill(Color.LIGHTGREEN);
		//gc.fillRect(934, 70.5, time*(2.25), 29);
	}

	
	public synchronized void updateTime(){
		int tlevel = level;
		if(time==30){
			day++;
			if(day%7 == 6){
				week++;
				LineController.getInstance().getItem().addItem();
				// change level
				if(week == 7 ) level ++;
				else if(week == 13) level++;
				else if(week == 18) level++;
				else if(week == 22) level++;
				//else if(week > 22 && (day-1)%3==0 ) level++;	
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle(null);
				if(tlevel!=level) alert.setContentText("Week "+week + "\nYou reach level "+level+"!!!\nYou got a new Transporter."); 
				else alert.setContentText("Week "+week + "\n You got a new Transporter.");
				alert.show();
				
				
			}
			time = 0;
		}	
		else time++;
		

	}
	
	public static Scorebar getInstance(){
		return instance;
	}

	public int getNumberOfCrowded() {
		return numberOfCrowded;
	}

	public void setNumberOfCrowded(int numberOfCrowded) {
		this.numberOfCrowded = numberOfCrowded;
	}

	public int getlevel(){
		return level;
	}
}
