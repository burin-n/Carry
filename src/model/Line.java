package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import main.Main;
import utility.InputUtility;

import java.util.ArrayList;


public class Line implements IDrawable{
	private ArrayList<Point> points;
	private ArrayList<Point> pivots;
	private Color color;
	
	
	public Line(Color color){
		this.points = new ArrayList<>();
		this.pivots = new ArrayList<>();
		this.color = color;
	}
	
	public Line(Color color,ArrayList<Point> points){
		this.points = new ArrayList<>(points);
		this.color = color;
		this.pivots = new ArrayList<>();
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
		gc.setStroke(color);
		gc.setLineWidth(10);
		for(int i=0;i<points.size()-1;i++){
			gc.strokeLine(points.get(i).getX(), points.get(i).getY(), points.get(i+1).getX(), points.get(i+1).getY());
		}
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
	

	
	public void addPoint(int x1,int y1,int x2,int y2,boolean append){
		ArrayList<Point> l1 = new ArrayList<>();
		ArrayList<Point> l2 = new ArrayList<>();
		int directionx;
		if(x2!=x1) directionx = (x2-x1)/Math.abs(x2-x1);
		else directionx = 0;
		int directiony; 
		if(y2!=y1) directiony= (y2-y1)/Math.abs(y2-y1);
		else directiony = 0;
		int inclineLength = Math.min(Math.abs(x2-x1), Math.abs(y2-y1));
		int horizontalLength = Math.abs(Math.abs(x2-x1) - inclineLength);
		int verticalLength = Math.abs(Math.abs(y2-y1) - inclineLength);
		
		System.out.println(inclineLength);
		System.out.println(horizontalLength);
		System.out.println(verticalLength);
		
		//if(pivots.isEmpty()) pivots.add(new Point(x1,y1));
		
		// horizontal first
		if(horizontalLength>0){
			l1.addAll(findHorizontal(x1, y1, directionx, horizontalLength));
		//	pivots.add(l1.get(l1.size()-1));
		}
		if(inclineLength>0){
			if(!l1.isEmpty()){ 
				l1.addAll(findIncline(l1.get(l1.size()-1).getX() + directionx, l1.get(l1.size()-1).getY() + directiony, directionx, directiony, inclineLength-1));
			}
			else l1.addAll(findIncline(x1, y1, directionx, directiony, inclineLength));
		//	pivots.add(l1.get(l1.size()-1));
		}
		if(verticalLength>0){
			if(!l1.isEmpty()){
				l1.addAll(findVertical(l1.get(l1.size()-1).getX(), l1.get(l1.size()-1).getY() + directiony, directiony, verticalLength-1));
				
			}
			else l1.addAll(findVertical(x1, y1, directiony, verticalLength));
		//	pivots.add(l1.get(l1.size()-1));
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
		
		Thread t = new Thread( () ->{
			boolean isl1 = true;
			while(true){
				try {
					Thread.sleep(100);
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
				if(InputUtility.isMouseLeftDown()){
					if(append){
						if(isl1)points.addAll(l1);
						else points.addAll(l2);
					}
					else{
						if(isl1) addFront(l1);
						else addFront(l2);					
					}
					LineHolder.getInstance().removeTemp();
					break;
				}
			}			
		});
		ThreadHolder.instance.addThread(t);
		t.start();
		
		
//		new Thread( () ->{
//			boolean isl1=true;
//			System.out.println("new thread");
//			while(true){
//				try {
//					Thread.sleep(10);
//					Platform.runLater(()->{
//						tl1.clear();
//						tl2.clear();
//					});
//					if(x2!=x1){
//						if(ccw(new Point(x1,y1),new Point(x2,y2), new Point(InputUtility.getMouseX(),InputUtility.getMouseY()))){	
//							if(directiony/directionx < 0 ){
//								Platform.runLater(()->tl2.drawPale(Main.gs.getGraphicsContext()));
//								isl1 = false;
//							}
//							else{
//								Platform.runLater(()->tl1.drawPale(Main.gs.getGraphicsContext()));
//								isl1 = true;
//							}
//						}
//						else{
//							if(directiony/directionx < 0 ){ 
//								Platform.runLater(()->tl1.drawPale(Main.gs.getGraphicsContext()));
//								isl1 = true;
//							}
//							else{
//								Platform.runLater(()->tl2.drawPale(Main.gs.getGraphicsContext()));
//								isl1 = false;
//							}
//						}
//					}
//					else Platform.runLater(()->tl1.drawPale(Main.gs.getGraphicsContext()));
//					
//					
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				if(InputUtility.isMouseLeftClicked()){
//						if(InputUtility.isMouseLeftClicked()){
//						if(isl1)
//							points.addAll(l1);
//						else points.addAll(l2);
//						break;
//					}
//				}
//			}
//		}).start();
		System.out.println("end thread");
//		System.out.println(l1);
		System.out.println(points);
//		System.out.println(l2);
	}
	
//	public void addPoint(int x2,int y2){
//		addPoint(points.get(points.size()-1).getX(),points.get(points.size()-1).getY(),x2,y2);
//	}
	
	public void clear() {
		// TODO Auto-generated method stub
		GraphicsContext gc = Main.gs.getGraphicsContext();
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
				return cross(p.getVector(q), p.getVector(r)) > 0;
		return cross(p.getVector(q), p.getVector(r)) < 0; 
	}
	
	private void addFront(ArrayList<Point> t){
		ArrayList<Point> temp = new ArrayList<>();
		for(int i=t.size()-1 ; i>=0 ;i--){
			temp.add(t.get(i));
		}
		temp.addAll(points);
		points = new ArrayList<Point>(temp);
	}
	
}

	