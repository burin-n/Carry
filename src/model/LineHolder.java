package model;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class LineHolder {
	private static ArrayList<Line> lines;
	private static Line temp;
	private static final Color[] colors = {Color.RED,Color.YELLOW,Color.PINK,Color.MEDIUMPURPLE,Color.DEEPSKYBLUE};
	private static LineHolder instance = new LineHolder();
	public LineHolder(){
		lines = new ArrayList<>();
		/*for(int i=0;i<5;i++)
			addLine(new Line(colors[i]));
		 */
		temp = null;
	}
	
	public synchronized void addLine(Line l){
		lines.add(l);
	}
	
	public synchronized void setTemp(Line l){
		temp = l;
	}
	
	public synchronized void removeTemp(){
		temp = null;
	}
	
	public synchronized Line getTemp(){
		return temp;
	}
	
	
	public synchronized void removeLine(Color c){
		for(Line l : lines){
			if(l.getColor() == c) lines.remove(l);
		}
	}

	public synchronized static LineHolder getInstance(){
		return instance;
	}

	public synchronized ArrayList<Line> getLines() {
		return lines;
	}
	
	public synchronized Line getLine(int index){
		return lines.get(index);
	}
	
	public synchronized Line getLine(Color color){
		for(Line l : lines)
			if(l.getColor() == color)
				return l;
		return null;
	}
	
	public synchronized void drawTemp(GraphicsContext gc){
		if(temp != null){
			temp.drawPale(gc);
		}
		
	}
}
