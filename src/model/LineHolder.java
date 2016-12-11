package model;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class LineHolder {
	private static LineHolder instance = new LineHolder();
	private static ArrayList<Line> lines;
	private static Line temp;
	
	public LineHolder(){
		lines = new ArrayList<>();
		temp = null;
	}
	
	public void addLine(Line l){
		lines.add(l);
	}
	
	public void setTemp(Line l){
		temp = l;
	}
	
	public void removeTemp(){
		temp = null;
	}
	
	public Line getTemp(){
		return temp;
	}
	
	
	public void removeLine(Color c){
		for(Line l : lines){
			if(l.getColor() == c) lines.remove(l);
		}
	}

	public static LineHolder getInstance(){
		return instance;
	}

	public ArrayList<Line> getLines() {
		return lines;
	}
	
	public void drawTemp(GraphicsContext gc){
		if(temp != null){
			temp.drawPale(gc);
		}
		
	}
}
