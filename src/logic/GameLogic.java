package logic;

import java.util.Random;

import org.omg.CosNaming.NamingContextExtPackage.AddressHelper;

import javafx.application.Platform;
import main.Main;
import model.ArcStation;
import model.CrossStation;
import model.SquareStation;
import model.Station;
import model.StationHolder;
import model.ThreadHolder;
import model.TriangleStation;
import screen.GameScreen;
import utility.InputUtility;

public class GameLogic {
	private GameScreen gs;
	private int creatingFailCount;
	
	public GameLogic(GameScreen gs){
		this.gs = gs;
		addStation();
		addStation();
		addStation();
		creatingFailCount = 0;
		Thread controller = new Thread(new Runnable() {
			@Override
			public void run() {
				
				while(true){
					try {
						System.out.println("Yo");
						Thread.sleep(1000);
						Platform.runLater(()->{
						//	gs.clearScreen();
						 	gs.clearElement();
							gs.draw();
						});
					} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							break;
					}
				}
			}
		});
	
		Thread creating = new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				// TODO Auto-generated method stub
				while(creatingFailCount <= 10){
					System.out.println("creating station");
					try {
						Thread.sleep(5000);
						Platform.runLater(() -> addStation() );
					} catch (InterruptedException e) {
					// 	TODO Auto-generated catch block
						e.printStackTrace();
						break;
					}
			
				}
			}
		});
		
		ThreadHolder.instance.addThread(creating);
		ThreadHolder.instance.addThread(controller);
		
		creating.start(); controller.start();
	}


	public void addStation(){
		Random r = new Random();
		int x,y,c=0;
		do{
			if(c>=1000) {
				System.out.println("fail");
				creatingFailCount ++;
				return ;
			}
			x = r.nextInt(GameScreen.width-30);
			y = r.nextInt(GameScreen.heigth-30);
			c++;
		}while(!isFreeSpace(x,y));
		
		int s = r.nextInt(4);
		Station newStation;
		if(s == 0) newStation = new SquareStation(x-10, y-10);
		else if(s == 1) newStation = new ArcStation(x-10, y-10);
		else if(s == 2) newStation = new CrossStation(x, y);
		else newStation = new TriangleStation(x, y);
		
		StationHolder.getInstance().addStation(newStation);
		
	}
	
	private boolean isFreeSpace(int x,int y){
		return !( isScorebar(x,y) || isControlbar(y) || isStationNear(x,y) || isOutOfScreen(x, y)) ;	
	}
	
	private boolean isScorebar(int x,int y){
		if( x>= 674 && x<=1024 && y>=0 && y<=68) return true;
		else return false;
	}
	
	private boolean isControlbar(int y){
		if(y>=700-50 && y <= 768) return true;
		else return false;
	}
	
	private boolean isStationNear(int x,int y){
		return StationHolder.getInstance().isStationNear(x,y);
	}
	
	private boolean isOutOfScreen(int x,int y){
		if(x<=30 || x>= GameScreen.width-30-30) return true;
		if(y<=30 || y>= GameScreen.width-30-30) return true;
		return false;
	}
	

}
