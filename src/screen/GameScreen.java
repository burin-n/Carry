package screen;

import java.awt.im.InputContext;
import java.util.ArrayList;
import java.util.Random;

import controller.LineController;

import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;
 
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import logic.Scorebar;
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

public class GameScreen extends Scene{
	private Canvas canvas;
	public static GraphicsContext gc;

	public static final int width=1024,heigth=768;
	
	public GameScreen(Pane parent,int width,int heigth){
		super(parent);
		this.canvas = new Canvas(width,heigth);
		parent.getChildren().add(canvas);
		gc = canvas.getGraphicsContext2D();
		
		clearScreen();

		
		addListener();
	}
	public GraphicsContext getGraphicsContext(){
		return gc;
	}
	
	public void drawArea(){

		gc.setFill(Color.BLACK);
		//gc.fillText("control bar", 400, 730);
		gc.setFill(Color.PINK);
		gc.fillRect(724-50, 0, 1024-724+50, 68);
		gc.setFill(Color.BLACK);
		
		gc.setGlobalAlpha(0.4);
		gc.setFill(Color.LIGHTGREEN);
		gc.fillRect(30, 30, 1024-80, 768-60);
		gc.setGlobalAlpha(1.0);
		
	}
	
	public void drawBar(GraphicsContext gc){
		Scorebar.getInstance().draw(gc);
		LineController.getInstance().draw(gc);
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

		for(Line l : LineHolder.getInstance().getLines())
			l.draw(gc);
		
		LineHolder.getInstance().drawTemp(gc);
		
		for(Station e : StationHolder.getInstance().getStations()){
			e.drawCrowded(gc);
			e.draw(gc);
			e.draw_passengers(gc);
		}
		
	}
	
	public void drawGameOver(){
		gc.setGlobalAlpha(0.5);
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, GameScreen.width, GameScreen.heigth);
		FontLoader fontLoader= Toolkit.getToolkit().getFontLoader();
		gc.setFont(Font.font("Tohoma",FontPosture.ITALIC,50));
		double font_width= fontLoader.computeStringWidth("Game Over", gc.getFont());
		double font_height= fontLoader.getFontMetrics(gc.getFont()).getLineHeight();
		gc.setFill(Color.WHITE);
		gc.setGlobalAlpha(1.0);
		gc.fillText("Game Over", GameScreen.width/2 - font_width/2, GameScreen.heigth/2 - font_height/2);
	}
	
	private void addListener(){
		this.setOnMouseEntered((event)->InputUtility.setMouseOnScreen(true));
		this.setOnMouseExited((event)->InputUtility.setMouseOnScreen(false));
		
		this.setOnMousePressed(new EventHandler<MouseEvent>() {
			
			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub

				//System.out.println("kodd");
				if(event.getButton() == MouseButton.PRIMARY){
					InputUtility.setMouseLeftLastDown(true);
					InputUtility.setMouseLeftDown(true);

				}
				else InputUtility.setMouseRightDown(true);
				System.out.println("object on screen:"+model.StationHolder.getInstance().isStation(event.getX(), event.getY()));
				System.out.println(event.getX());
				System.out.println(event.getY());
			}
		});

		this.setOnMouseReleased((MouseEvent event)->InputUtility.setMouseLeftLastDown(false));
		
		this.setOnMouseMoved((MouseEvent event) -> {
			InputUtility.setMouseX((int) event.getX());
			InputUtility.setMouseY((int) event.getY());
		});
		
		
		this.setOnKeyPressed((KeyEvent event)->{
			System.out.println("keycode:"+event.getCode());
			InputUtility.setKeyPressed(event.getCode(), true);
			InputUtility.setKeyTriggered(event.getCode(), true);
		});
		
		this.setOnKeyReleased((KeyEvent event)->{
			InputUtility.setKeyPressed(event.getCode(), false);	
			System.out.println("abcddfefsadf");
		});
	}
	

}
