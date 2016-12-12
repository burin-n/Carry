package logic;

import controller.LineController;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Scorebar {
	
	private static Scorebar instance = new Scorebar();
	private static int score,time,day=0;
	private String[] days = {"MON","TUE","WED","THU","FRI","SAT","SUN"};
	private Image clock,people;
	
	
	public Scorebar(){
		
		setScore(0);
		setTime(0);
		people = new Image(ClassLoader.getSystemResourceAsStream("humanwalk-clipart.png"));
		clock = new Image(ClassLoader.getSystemResourceAsStream("clock-clipart.png"));
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
		gc.drawImage(clock, 870	, 4, 60, 60);
		gc.drawImage(people, 680, 4, 60, 60);
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
		gc.fillText(days[day], 947, 48);
		//gc.strokeRect(934, 70, 90, 30);
		//gc.setFill(Color.LIGHTGREEN);
		//gc.fillRect(934, 70.5, time*(2.25), 29);
	}
	
	public synchronized void updateTime(){
		if(time==20){
			day = (day+1)%7;
			if(day == 6){
				LineController.getInstance().getItem().addItem();
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setContentText("Sunday !!! \n You got an item.");
				alert.show();
			}
			time = 0;
		}	
		else time++;
	}
	
	public static Scorebar getInstance(){
		return instance;
	}
}
