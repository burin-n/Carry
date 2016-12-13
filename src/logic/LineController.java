package logic;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import model.IDrawable;
import model.Item;
import model.Line;
import model.Point;
import model.Station;
import model.StationHolder;
import screen.GameScreen;

public class LineController implements IDrawable{
	private static LineController instance = new LineController();
	private ArrayList<Point> points = new ArrayList<>();
	private ArrayList<Point> centerpoints = new ArrayList<>();
	private static final Color[] colors = {Color.ORANGERED,Color.YELLOW,Color.HOTPINK,Color.MEDIUMPURPLE,Color.DEEPSKYBLUE,Color.BLACK,Color.BLACK};
	private int[] sizes = {20,20,20,20,20,40,40};
	private Item item;
	private boolean[] status = {false,false,false,false,false,false,false};
	private boolean[] isUsed = {false,false,false,false,false,false,false}; 
	public LineController(){
		Point p1 = new Point(350,704);
		Point p2 = new Point(420,704);
		Point p3 = new Point(490,704);
		Point p4 = new Point(560,704);
		Point p5 = new Point(630,704);
		Point p6 = new Point(960,704);
		Point p7 = new Point(250,704);
		item = new Item();
		points.add(p1);
		points.add(p2);
		points.add(p3);
		points.add(p4);
		points.add(p5);
		points.add(p6);
		points.add(p7);
		Point cp1 = new Point(350 + 30,704 + 30);
		Point cp2 = new Point(420 + 30,704 + 30);
		Point cp3 = new Point(490 + 30,704 + 30);
		Point cp4 = new Point(560 + 30,704 + 30);
		Point cp5 = new Point(630 + 30,704 + 30);
		Point cp6 = new Point(960 + 30,704 + 30);
		Point cp7 = new Point(250 + 30,704 + 30);
		centerpoints.add(cp1);
		centerpoints.add(cp2);
		centerpoints.add(cp3);
		centerpoints.add(cp4);
		centerpoints.add(cp5);
		centerpoints.add(cp6);
		centerpoints.add(cp7);
	}
	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub	
		double X = 150;
		gc.setFill(Color.LIGHTBLUE);
		gc.fillRect(0, 700, 1024, 68);
		
		/*gc.setFill(Color.BLACK);
		gc.fillOval(X + 30, 704, 60, 60);*/

		drawItem(gc);
		
		for(int i=0;i<5;i++){
			gc.setFill(colors[i]);
			
			int size = getSizes()[i];
			boolean sta = getStatus()[i];
			boolean isUsed = getIsUsed()[i];
			if(sta)size += 15;
			if(isUsed)size += 20;
			gc.fillOval(points.get(i).getX() + 30-size/2.0, points.get(i).getY() + 30-size/2.0, size, size);
			
		}
		gc.setLineWidth(2.0);
		gc.setFill(Color.BLACK);
		int size = getSizes()[5];
		if(getStatus()[5])size+=20;
		gc.strokeOval(960 + 30-size/2.0, 704 + 30-size/2.0, size, size);
		gc.setFont(new Font(25));
		gc.fillText("X", 960+22, 704+40);
		/*gc.setFill(Color.RED);
		gc.fillOval(X + 200 + 15, 704 + 15, 30, 30);

		gc.setFill(Color.YELLOW);
		gc.fillOval(X + 270, 704, 60, 60);

		gc.setFill(Color.PINK);
		gc.fillOval(X + 340, 704, 60, 60);

		gc.setFill(Color.PURPLE);
		gc.fillOval(X + 410, 704, 60, 60);

		gc.setFill(Color.BLUE);
		gc.fillOval(X + 480, 704, 60, 60);*/
		

		/*gc.setFill(Color.BLACK);
		gc.fillOval(X + 580, 704, 60, 60);*/
	}
	public int IndexisLineControl(int x,int y){
		for(int i=0;i<7;i++){
		Point p = getCenterPoints().get(i);
		int size = getSizes()[i];
		boolean sta = getStatus()[i];
		boolean isUsed = getIsUsed()[i];
		if(sta)size += 15;
		if(isUsed)size += 20;
			if(Math.abs(p.getX() - x) <= size/2.0 + 5 && Math.abs(p.getY() - y) <= size/2.0 + 5)
				return i;
		}
		return -1;
		
	}
	public void drawItem(GraphicsContext gc){
		//gc.setFill(Color.BLACK);
		gc.setLineWidth(2.0);
		gc.setFill(Color.BLACK);
		int size = getSizes()[6];
		if(getStatus()[6])size+=20;
		gc.strokeOval(250 + 30-size/2.0, 704 + 30-size/2.0, size, size);
		gc.fillOval(250+30-size/4, 704+30-size/4,size/2,size/2);
		System.out.println(Integer.toString(item.getNumber()));
		Font font = new Font(20);
		gc.setFont(font);
		int f=0;
		if(getStatus()[6])f=10;
		gc.fillText(Integer.toString(item.getNumber()), 295+f, 764+f/5);
		//gc.setFill(Color.WHITESMOKE);
	}
	public int[] getSizes(){
		return this.sizes;
	}	
	public boolean[] getStatus(){
		return this.status;
	}
	public ArrayList<Point> getCenterPoints(){
		return this.centerpoints;
	}
	public ArrayList<Point> getPoints(){
		return this.points;
	}
	public static LineController getInstance(){
		return instance;
	}
	public Color[] getColors(){
		return this.colors;
	}
	public boolean[] getIsUsed() {
		return isUsed;
	}
	public Item getItem(){
		return item;
	}

	public void setIsUsed(Color c,boolean set){
		for(int i=0;i<colors.length;i++){
			if(c == colors[i]){
				isUsed[i] = set;
			}
		}
	}
	
	public int getColorIndex(Color c){
		int index=0;
		for(Color color : colors){
			if(c == color)
				break;
			else index++;
		}
		return index;
	}
}
