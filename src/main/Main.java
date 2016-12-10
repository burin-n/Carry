package main;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import logic.GameLogic;
import screen.GameScreen;

import model.ArcStation;
import model.CrossStation;
import model.SquareStation;
import model.ThreadHolder;
import model.Transporter;
import model.TriangleStation;


public class Main extends Application{
	public static Main instance;
	private Stage primaryStage;
	public static GameLogic gl;
	public static GameScreen gs;
	private static final int width=1024,heigth=768;
	
	
	public static void main(String[] args){
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub

		instance = this;
		this.primaryStage = primaryStage;
		gs = new GameScreen(width,heigth);
		gl = new GameLogic(gs);
		Scene scene = new Scene(gs);
		primaryStage.setScene(scene);				
		

//		StackPane root = new StackPane();
//		Scene scene = new Scene(root);
//		primaryStage.setScene(scene);
//		Canvas canvas = new Canvas(1024,768);
//		root.getChildren().add(canvas);
//		
//		GraphicsContext gc = canvas.getGraphicsContext2D();
//	//	Button btn = new Button("FM");
//		gc.setFill(Color.WHITESMOKE);
//		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
//		//gc.setFill(Color.BLACK);
//		//gc.fillOval(400, 400, 100, 100);
//		SquareStation Sq = new SquareStation(100,100);
//		TriangleStation Tr = new TriangleStation(100,160);
//		CrossStation Cr = new CrossStation(250,160);
//		ArcStation Ar = new ArcStation(250,100);
//		Transporter Tp = new Transporter(400, 100, Color.RED);
//		Transporter Tp2 = new Transporter(450, 100, Color.DARKGREEN);
//		Transporter Tp3 = new Transporter(500, 100, Color.DARKBLUE);
//		Transporter Tp4 = new Transporter(550, 100, Color.ORANGE);
//		Tp.draw(gc);
//		Tp2.draw(gc);
//		Tp3.draw(gc);
//		Tp4.draw(gc);
//		//Sq.draw(gc);
//		//Tr.draw(gc);
//		//Cr.draw(gc);
//		//Ar.draw(gc);
//		Sq.AddPassenger();
//		Sq.AddPassenger();
//		Sq.AddPassenger();
//		Sq.AddPassenger();
//		Sq.AddPassenger();
//		Ar.AddPassenger();
//		Ar.AddPassenger();
//		Ar.AddPassenger();
//		Ar.AddPassenger();
//		Ar.AddPassenger();
//		Cr.AddPassenger();
//		Cr.AddPassenger();
//		Cr.AddPassenger();
//		Cr.AddPassenger();
//		Cr.AddPassenger();
//		Tr.AddPassenger();
//		Tr.AddPassenger();
//		Tr.AddPassenger();
//		Tr.AddPassenger();
//		Tr.AddPassenger();
//		Tp.addPassenger(Tr.dequeuePassengers());
//		Tp.draw_passengers(gc);
//		Tp2.addPassenger(Cr.dequeuePassengers());
//		Tp2.addPassenger(Cr.dequeuePassengers());
//		Tp2.draw_passengers(gc);
//		Tp3.addPassenger(Ar.dequeuePassengers());
//		Tp3.addPassenger(Ar.dequeuePassengers());
//		Tp3.addPassenger(Ar.dequeuePassengers());
//		Tp3.draw_passengers(gc);
//		Tp4.addPassenger(Sq.dequeuePassengers());
//		Tp4.addPassenger(Sq.dequeuePassengers());
//		Tp4.addPassenger(Sq.dequeuePassengers());
//		Tp4.addPassenger(Sq.dequeuePassengers());
//		Tp4.draw_passengers(gc);
//		
//		Sq.draw(gc);
//		Tr.draw(gc);
//		Cr.draw(gc);
//		Ar.draw(gc);
//		Cr.draw_passengers(gc);
//		Ar.draw_passengers(gc);
//		Tr.draw_passengers(gc);
//		Sq.draw_passengers(gc);
//		System.out.println(Sq.getNumberOfPassengers());
//		System.out.println(Tr.getNumberOfPassengers());
//		System.out.println(Cr.getNumberOfPassengers());
//		System.out.println(Ar.getNumberOfPassengers());
//		
//		gc.setFill(Color.PINK);
//		gc.setGlobalAlpha(0.3);
//		gc.fillRect(100-65, 100-65, 150, 150);
		primaryStage.show();
		
	}
	
	public void stop(){
		ThreadHolder.instance.stopAll();
		ThreadHolder.instance.check();
		ThreadHolder.instance.update();
		System.out.println(ThreadHolder.instance.getThreads().size());
	}
}
