package model;

import java.util.ArrayList;

import javafx.scene.paint.Color;

public class LineHolder {
	private static LineHolder instance = new LineHolder();
	private static ArrayList<Line> lines;
	
	public LineHolder(){
		lines = new ArrayList<>();
	}
	
	public void addLine(Line l){
		lines.add(l);
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

}
