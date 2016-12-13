package model;

import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import logic.Scorebar;
import screen.GameScreen;
import utility.InputUtility;

import java.util.ArrayList;

import controller.LineController;


public class Line implements IDrawable{
	private ArrayList<Point> points;
	private Color color;
	private ArrayList<Transporter> transporters; 
	private boolean isCreateTranFront;
	protected boolean[] isPass;
	
	public Line(Color color){
		this.points = new ArrayList<>();
		this.transporters = new ArrayList<>();
		this.color = color;
		isCreateTranFront = true;
		
	}
	
	public Line(Color color,ArrayList<Point> points){
		this.points = new ArrayList<>(points);
		this.transporters = new ArrayList<>();
		this.color = color;
		isCreateTranFront = true;
		this.isPass = new boolean[5];
		for(int i=0;i<5;i++) isPass[i] = false;
	}
	
	public Color getColor(){
		return color;
	}
	
	public Point firstPoint(){
		if(points.isEmpty()) return null;
		return points.get(0);
	}
	
	public Point lastPoint(){
		if(points.isEmpty()) return null;
		return points.get(points.size()-1);
	}
	
	public void draw(GraphicsContext gc){
		gc.setGlobalAlpha(1.0);
		gc.setStroke(color);
		gc.setLineWidth(10);
		for(int i=0;i<points.size()-1;i++){
			gc.strokeLine(points.get(i).getX(), points.get(i).getY(), points.get(i+1).getX(), points.get(i+1).getY());
		}
		gc.setFill(color);
		if(firstPoint()!=null) gc.fillOval(firstPoint().getX()-23, firstPoint().getY()-23, 46, 46);
		if(lastPoint()!=null) gc.fillOval(lastPoint().getX()-23, lastPoint().getY()-23, 46, 46);
	}
	
	public void drawPale(GraphicsContext gc){
		gc.setStroke(color);
		gc.setLineWidth(10);
		gc.setGlobalAlpha(0.08);
		for(int i=0;i<points.size()-1;i++){
			gc.strokeLine(points.get(i).getX(), points.get(i).getY(), points.get(i+1).getX(), points.get(i+1).getY());
		}
		gc.setGlobalAlpha(1.0);		
	}
	public void ap2(int x1,int y1,int x2,int y2){
		Point p1 = new Point(x1, y1);
		Point p2 = new Point(x2, y2);
		this.getPoints().add(p1);
		this.getPoints().add(p2);
	}
	public ArrayList<Point> getPoints(){
		return points;
	}
	
	//public void addPoint(int x1,int y1,int x2,int y2,boolean append){
	public void addPoint(Station s1,Station s2, boolean append){
		int x1 = (int)s1.getCenterX(), y1 = (int)s1.getCenterY();
		int x2 = (int)s2.getCenterX(), y2 = (int)s2.getCenterY();
		ArrayList<Point> l1 = new ArrayList<>();
		ArrayList<Point> l2 = new ArrayList<>();
		int directionx;
		if(x2!=x1) directionx = (x2-x1)/Math.abs(x2-x1);
		else directionx = 0;
		int directiony; 
		if(y2!=y1) directiony = (y2-y1)/Math.abs(y2-y1);
		else directiony = 0;
		int inclineLength = Math.min(Math.abs(x2-x1), Math.abs(y2-y1));
		int horizontalLength = Math.abs(Math.abs(x2-x1) - inclineLength);
		int verticalLength = Math.abs(Math.abs(y2-y1) - inclineLength);
		
		System.out.println(inclineLength);
		System.out.println(horizontalLength);
		System.out.println(verticalLength);
		
		
		// horizontal first
		if(horizontalLength>0){
			l1.addAll(findHorizontal(x1, y1, directionx, horizontalLength));

		}
		if(inclineLength>0){
			if(!l1.isEmpty()){ 
				l1.addAll(findIncline(l1.get(l1.size()-1).getX() + directionx, l1.get(l1.size()-1).getY() + directiony, directionx, directiony, inclineLength-1));
			}
			else l1.addAll(findIncline(x1, y1, directionx, directiony, inclineLength));
		}
		if(verticalLength>0){
			if(!l1.isEmpty()){
				l1.addAll(findVertical(l1.get(l1.size()-1).getX(), l1.get(l1.size()-1).getY() + directiony, directiony, verticalLength-1));
				
			}
			else l1.addAll(findVertical(x1, y1, directiony, verticalLength));
		}
		
		// vertical first
		if(verticalLength>0) l2.addAll(findVertical(x1, y1, directiony, verticalLength));		
		if(inclineLength>0){
			if(!l2.isEmpty())
				l2.addAll(findIncline(l2.get(l2.size()-1).getX() + directionx, l2.get(l2.size()-1).getY() + directiony, directionx, directiony, inclineLength-1));
			else l2.addAll(findIncline(x1, y1, directionx, directiony, inclineLength));
		}
		if(horizontalLength>0){
			if(!l2.isEmpty())
				l2.addAll(findHorizontal(l2.get(l2.size()-1).getX() + directionx, l2.get(l2.size()-1).getY(), directionx, horizontalLength-1));
			else l2.addAll(findHorizontal(x1, y1, directionx, horizontalLength));
		}
		
		Line tl1 = new Line(color,l1);
		Line tl2 = new Line(color,l2);
		
		// thread for catch chooseLineEvent when mouse is clicked
		// and ESC to cancel creating line
		Thread t = new Thread( () ->{
			boolean isl1 = true;
			while(true){
				try {
					Thread.sleep(75);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					break;
				}
				if(x2!=x1){
					if(ccw(new Point(x1,y1),new Point(x2,y2), new Point(InputUtility.getMouseX(),InputUtility.getMouseY()))){	
						if(directiony/directionx < 0 ){
							LineHolder.getInstance().setTemp(tl2); isl1 = false;
						}
						else{
							LineHolder.getInstance().setTemp(tl1); isl1 = true;
						}
					}
					else{
						if(directiony/directionx < 0 ){ 
							LineHolder.getInstance().setTemp(tl1); isl1 = true;
						}
						else{
							LineHolder.getInstance().setTemp(tl2); isl1 = false;
						}
					}
				}
				else{
					LineHolder.getInstance().setTemp(tl1);
				}
				//System.out.println(InputUtility.isMouseLeftDown());
				if(InputUtility.isMouseLeftDown()){
					System.out.println("mouse down in Line");
					if(append){
						if(isl1)points.addAll(l1);
						else points.addAll(l2);
					}
					else{
						if(isl1) addFront(l1);
						else addFront(l2);					
					}
					LineHolder.getInstance().removeTemp();
					for(int i=0 ; i<LineController.getInstance().getColors().length ; i++ ){
						if( color == LineController.getInstance().getColors()[i] ){
							System.out.println("line founded in Line");
							LineController.getInstance().getIsUsed()[i] = true;
						}
					}
					addStationTypeToLine();
					break;
				}
				
				else if(InputUtility.getKeyPressed(KeyCode.ESCAPE)){
					System.out.println("ESC down in Line");
					LineHolder.getInstance().removeTemp();
					for(int i=LineController.getInstance().getColors().length-1 ; i >= 0; i-- ){
						if( color == LineController.getInstance().getColors()[i] ){
							System.out.println("line founded in Line");
							Line L = LineHolder.getInstance().getLine(color);
							if(L != null && L.getPoints().size()==0 ){
								LineHolder.getInstance().removeLine(color);
								LineController.getInstance().getIsUsed()[i] = false;	
							}
							break;
						}
					}				
					
					break;
				}
				
			}			
		});
		ThreadHolder.instance.addThread(t);
		t.start();
		
		System.out.println("end thread");
		System.out.println("points:" + points);

	}
	
//	public void addPoint(int x2,int y2){
//		addPoint(points.get(points.size()-1).getX(),points.get(points.size()-1).getY(),x2,y2);
//	}

	
	public void clear() {
		// TODO Auto-generated method stub
		GraphicsContext gc = GameScreen.gc;
		gc.setLineWidth(10);
		gc.setStroke(Color.WHITESMOKE);
		gc.setGlobalAlpha(1.0);
		for(int i=0;i<points.size()-1;i++){
			gc.strokeLine(points.get(i).getX(), points.get(i).getY(), points.get(i+1).getX(), points.get(i+1).getY());
		}
			
	}

	private ArrayList<Point> findIncline(int x1,int y1,int directionx,int directiony,int inclineLength){
		ArrayList<Point> line = new ArrayList<>();
		int px = x1, py=y1, count=0;
		while(count < inclineLength){
			// incline line
			line.add(new Point(px, py));
			px += directionx;
			py += directiony;
			count ++;
		}
		line.add(new Point(px,py));
		return line;
	}
	
	
	private ArrayList<Point> findHorizontal(int x1,int y1,int directionx,int horizontalLength){
		ArrayList<Point> line = new ArrayList<>();
		int px = x1, py = y1, count=0;
		while(count < horizontalLength) {
			// horizontal line
			line.add(new Point(px,py));
			px += directionx;
			count++;
		}
		
		line.add(new Point(px,py));
		return line;
	
	}
	
	
	private ArrayList<Point> findVertical(int x1,int y1,int directiony,int verticalLength){
		ArrayList<Point> line = new ArrayList<>();
		int px = x1, py=y1, count=0;
		while(count < verticalLength){
			// incline line
			line.add(new Point(px, py));
			py += directiony;
			count++;
		}
		line.add(new Point(px,py));
		return line;
	}
	
	private double cross(Point a, Point b) {
		return a.getX() * b.getY() - a.getY() * b.getX(); 
	}
	
	private boolean ccw(Point p, Point q, Point r) {
		if(p.getX()!=q.getX())
			if((q.getY()-p.getY())/(q.getX()-p.getX()) < 0)
				return cross(p.getVector(q), p.getVector(r)) < 0;
		return cross(p.getVector(q), p.getVector(r)) < 0; 
	}
	
	private void addFront(ArrayList<Point> t){
		ArrayList<Point> temp = new ArrayList<>();
		for(int i=t.size()-1 ; i>=0 ;i--){
			temp.add(t.get(i));
		}
		temp.addAll(points);
		points = new ArrayList<Point>(temp);
		for(Transporter tr : transporters){
			tr.positionIndex+=temp.size();
		}
	}
	
	public boolean addTransporter(){
		if(points.isEmpty()){
			return false;
		}
		Transporter tran;
		if(isCreateTranFront){
			tran = new Transporter(firstPoint().getX(), firstPoint().getY(), color, 1);
			tran.positionIndex = 0;
		}
		else{
			tran = new Transporter(lastPoint().getX(), lastPoint().getY(), color, -1);
			tran.positionIndex = points.size()-1;
		}
		isCreateTranFront = !isCreateTranFront;
		transporters.add(tran);
		
		Thread t = new Thread(()->{
			while(true){
				try {
					Thread.sleep(100);
					Platform.runLater(()->{
						tran.setX(points.get(tran.positionIndex).getX());
						tran.setY(points.get(tran.positionIndex).getY());
					});
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					break;
				}

				// moving
				for(int i=0;i<Transporter.speed;i++){
					Station st = StationHolder.getInstance().isStation(tran.x, tran.y);
					if(st!=null){ // passing station	
						// drop people
						for(int j=tran.passengers.size()-1 ; j>=0 ;j--){
							if(tran.passengers.get(j).getType().compareTo(st.getType()) == 0){
								tran.drop(j);
								Scorebar.getInstance().setScore(Scorebar.getInstance().getScore()+1);
								break;
							}
						}
						// get people
						if(!tran.isFull() && st.getNumberOfPassengers()>0){
							tran.addPassenger(st.dequeuePassengers());
							break;
						}
					}
					
					// moving
					tran.positionIndex+=tran.direction;
					if(tran.positionIndex<0){
						tran.positionIndex = 0;
						tran.direction = 1;
					}
					else if(tran.positionIndex>=points.size()){
						tran.positionIndex = points.size()-1;
						tran.direction = -1;
					}
				}
				
								
				
			}	
		});
		
		ThreadHolder.instance.addTransThread(LineController.getInstance().getColorIndex(color), t);
		t.start();
		return true;
		
	}
	
	
	public void drawTrans(GraphicsContext gc){
		gc.setFill(color);
		gc.setGlobalAlpha(1.0);
		for(Transporter e : transporters){
			e.draw(gc);
			e.draw_passengers(gc);
		}
	}
	
	public int length(){
		return points.size();
	}
	
	private void addStationTypeToLine(){
		
	}
}

	