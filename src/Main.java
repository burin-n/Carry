
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Group root = new Group();
		Scene s = new Scene(root,500,500);
		//Fill in here
		
		primaryStage.setScene(s);
		
		GameManager gm = new GameManager();
		GameScreen gs = new GameScreen(500,500);
		
		root.getChildren().add(gs);
		GraphicsContext gc = gs.getGraphicsContext2D();
		gs.paintComponents();
		
		
		primaryStage.show();
		
		/*s.setOnKeyPressed(new EventHandler<KeyEvent>(){
			public void handle(KeyEvent e){
				gm.receiveKey(e.getCode());
			}
		});
		
		s.setOnKeyReleased(new EventHandler<KeyEvent>(){
			public void handle(KeyEvent e){
				gm.dropKey(e.getCode());
			}
		});
		*/
		new AnimationTimer() {
			Long start=0l;
			@Override
			public void handle(long now) {
				// TODO Auto-generated method stub
				if(start==0l)start=now;
				long diff = now-start;
				if(diff>=100000000l){ //100000000l = 100ms.
					//Fill in here
					
					start = 0l;
					
					gs.paintComponents();
					gm.update();
				}
			}
		}.start();
		
	}

}
