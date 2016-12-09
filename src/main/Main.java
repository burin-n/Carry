package main;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import screen.GameScreen;

public class Main extends Application{
	public static Main instance;
	private Stage primaryStage;
	public static GameScreen gs;
	
	
	public static void main(String[] args){
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		instance = this;
		this.primaryStage = primaryStage;
		gs = new GameScreen();
		Scene scene = new Scene(gs);
		primaryStage.setScene(scene);		
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(true){
					try {
						Thread.sleep(100);
						Platform.runLater(()->{
						//	gs.clearScreen();
							gs.clearElement();
							gs.draw();
						});
					} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
					}
				}
			}
		}).start();

		primaryStage.show();
		
	}
}
