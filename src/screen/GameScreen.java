package screen;

import java.awt.im.InputContext;
import java.util.ArrayList;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import model.ArcStation;
import model.CrossStation;
import model.Line;
import model.LineHolder;
import model.Point;
import model.SquareStation;
import model.Station;
import model.StationHolder;
import model.TriangleStation;
import utility.InputUtility;

public class GameScreen extends StackPane{
	private Canvas canvas;
	private GraphicsContext gc;
	private Line temp;
	private Line temp2;
	public static final int width=1024,heigth=768;
	
	public GameScreen(int width,int heigth){
		
		this.canvas = new Canvas(width,heigth);
		this.getChildren().add(canvas);
		gc = canvas.getGraphicsContext2D();
		
		clearScreen();
//		Line l = new Line(Color.BLUE);
//		temp = l;
//		l.addPoint(200, 300, 400, 700);
//		l.addPoint(250, 400, 700, 700);
//		l.addPoint(700, 700, 600, 432);
	
//		Line l2 = new Line(Color.RED);
//		l2.addPoint(150, 150, 100, 470);
//		
		//temp2 = l2;
		gc.setFill(Color.LIGHTSKYBLUE);
		gc.fillRect(0, 700, 1024, 68);
		gc.setFill(Color.BLACK);
		gc.fillText("control bar", 400, 730);
		gc.setFill(Color.PINK);
		gc.fillRect(724-50, 0, 350, 68);
		gc.setFill(Color.BLACK);
		gc.fillText("score&time bar", 800, 30);
	//	temp.draw(gc);
		gc.setGlobalAlpha(0.4);
		gc.setFill(Color.LIGHTGREEN);
		gc.fillRect(30, 30, 1024-60, 768-60);
		gc.setGlobalAlpha(1.0);
		addListener();
	}
	public GraphicsContext getGraphicsContext(){
		return gc;
	}
	
	public void clearScreen(){
		gc.setFill(Color.WHITESMOKE);
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
	}
	
	public void clearElement(){
		gc.setFill(Color.WHITESMOKE);
		//temp.clear();
	}
	
	public void draw(){

		for(Station e : StationHolder.getInstance().getStations())
			e.draw(gc);
		
		for(Line l : LineHolder.getInstance().getLines())
			l.draw(gc);
		
		
	}
	
	
	private void addListener(){
		canvas.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				if(event.getButton() == MouseButton.PRIMARY){
					InputUtility.setMouseLeftDown(true);
					System.out.println(StationHolder.getInstance().isStation(InputUtility.getMouseX(), InputUtility.getMouseY()));
				}
				else{
					
					if(!InputUtility.isMouseRightDown()){
						InputUtility.setLastmouseX((int)event.getX());
						InputUtility.setLastmouseY((int)event.getY());
						InputUtility.setMouseRightDown(true);
					}
					else{
						InputUtility.setMouseRightDown(false);						
						temp.addPoint(InputUtility.getMouseX(), InputUtility.getMouseY());
					}
				}
				System.out.println(event.getX());
				System.out.println(event.getY());
			}
		});
		
		canvas.setOnMouseReleased((event)->InputUtility.setMouseLeftDown(false));
		
		canvas.setOnMouseMoved((event) -> {
			InputUtility.setMouseX((int) event.getX());
			InputUtility.setMouseY((int) event.getY());
		});
		
	}
	

}
