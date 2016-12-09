package main;


import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.ArcStation;
import model.CrossStation;
import model.SquareStation;
import model.Transporter;
import model.TriangleStation;

public class Main extends Application{
	
	public static void main(String[] args){
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		StackPane root = new StackPane();
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		Canvas canvas = new Canvas(1024,768);
		root.getChildren().add(canvas);
		
		GraphicsContext gc = canvas.getGraphicsContext2D();
	//	Button btn = new Button("FM");
		gc.setFill(Color.WHITESMOKE);
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		//gc.setFill(Color.BLACK);
		//gc.fillOval(400, 400, 100, 100);
		SquareStation Sq = new SquareStation(100,100);
		TriangleStation Tr = new TriangleStation(100,160);
		CrossStation Cr = new CrossStation(250,160);
		ArcStation Ar = new ArcStation(250,100);
		Transporter Tp = new Transporter(300, 100, Color.RED);
		Tp.draw(gc);
		Sq.draw(gc);
		Tr.draw(gc);
		Cr.draw(gc);
		Ar.draw(gc);
		Sq.AddPassenger();
		Sq.AddPassenger();
		Sq.AddPassenger();
		Sq.AddPassenger();
		Sq.AddPassenger();
		Sq.draw_passengers(gc);
		Ar.AddPassenger();
		Ar.AddPassenger();
		Ar.AddPassenger();
		Ar.AddPassenger();
		Ar.AddPassenger();
		Ar.draw_passengers(gc);		
		Cr.AddPassenger();
		Cr.AddPassenger();
		Cr.AddPassenger();
		Cr.AddPassenger();
		Cr.AddPassenger();
		Cr.draw_passengers(gc);
		Tr.AddPassenger();
		Tr.AddPassenger();
		Tr.AddPassenger();
		Tr.AddPassenger();
		Tr.AddPassenger();
		Tr.draw_passengers(gc);
		//root.setEffect(new GaussianBlur());
		primaryStage.show();
		
	}
}
