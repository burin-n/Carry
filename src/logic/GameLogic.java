package logic;

import java.util.Random;

import javafx.application.Platform;
import main.Main;
import model.ArcStation;
import model.CrossStation;
import model.Line;
import model.LineHolder;
import model.Point;
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
	private boolean isClickedStation;
	private double tx,ty;
	private Station st;
	
	public GameLogic(GameScreen gs){
		this.gs = gs;
		tx = ty = 0;
		st = null;
		isClickedStation = false;
		addStation();
		addStation();
		addStation();
		creatingFailCount = 0;
		Thread controller = new Thread(new Runnable() {
			@Override
			public void run() {
				
				while(true){
					try {
					//	System.out.println("Yo");
						Thread.sleep(100);
						Platform.runLater(()->{
							gs.clearScreen();
							gs.drawArea();
							gs.draw();		
						});
						if(InputUtility.isMouseLeftDown()){
							System.out.println(StationHolder.getInstance().isStation(InputUtility.getMouseX(), InputUtility.getMouseY()));
							Station clickstation = StationHolder.getInstance().isStation((int)InputUtility.getLastmouseX(),(int)InputUtility.getLastmouseY());
							if(clickstation != null){
								if(!isClickedStation){
									isClickedStation = true;
									st = clickstation;
								}
								else{
									for(Line l : LineHolder.getInstance().getLines()){
										if((st.getX() == l.firstPoint().getX()) && (st.getY() == l.firstPoint().getY())){
											l.addPoint(x1, y1, x2, y2, append);
										}
									}
								}
							}
						}
					} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							break;
					}
					
					ThreadHolder.instance.update();
					InputUtility.postUpdate();
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
	//	ThreadHolder.instance.addThread(controller);
		
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
		return !( isScorebar(x,y) || isControlbar(y) || isStation(x,y) || isOutOfScreen(x, y)) ;	
	}
	
	private boolean isScorebar(int x,int y){
		if( x>= 674 && x<=1024 && y>=0 && y<=68) return true;
		else return false;
	}
	
	private boolean isControlbar(int y){
		if(y>=700-50 && y <= 768) return true;
		else return false;
	}
	
	private boolean isStation(int x,int y){
		if(StationHolder.getInstance().isStation(x,y) == null) return false;
		else return true;
	}
	
	private boolean isOutOfScreen(int x,int y){
		if(x<=30 || x>= GameScreen.width-30-30) return true;
		if(y<=30 || y>= GameScreen.width-30-30) return true;
		return false;
	}
	

}
