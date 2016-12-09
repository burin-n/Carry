package model;

import javafx.scene.paint.Color;
import javafx.util.Pair;

import java.util.ArrayList;

public class Line {
	private ArrayList<Pair<Integer,Integer>> points;
	private Color color;
	
	public Line(Color color){
		this.points = new ArrayList<>();
		this.color = color;
	}
	
	public void addPoint(int x1,int y1,int x2,int y2){
		ArrayList<Pair<Integer,Integer>> l1 = new ArrayList<>();
		ArrayList<Pair<Integer,Integer>> l2 = new ArrayList<>();
		int directionx = (x2-x1)/Math.abs(x2-x1);
		int directiony = (y2-y1)/Math.abs(y2-y1);

		int inclineLength = Math.min(Math.abs(x2-x1), Math.abs(y2-y1));
		int horizontalLength = Math.abs(Math.abs(x2-x1) - inclineLength);
		int verticalLength = Math.abs(Math.abs(y2-y1) - inclineLength);
		System.out.println(inclineLength);
		System.out.println(horizontalLength);
		System.out.println(verticalLength);
		// horizontal first
		if(horizontalLength>0) l1.addAll(findHorizontal(x1, y1, directionx, horizontalLength));
		if(inclineLength>0){
			if(!l1.isEmpty()) 
				l1.addAll(findIncline(l1.get(l1.size()-1).getKey(), l1.get(l1.size()-1).getValue(), directionx, directiony, inclineLength));
			else l1.addAll(findIncline(x1, y1, directionx, directiony, inclineLength));
		}
		if(verticalLength>0){
			if(!l1.isEmpty())
				l1.addAll(findVertical(l1.get(l1.size()-1).getKey(), l1.get(l1.size()-1).getValue(), directiony, verticalLength));
			else l1.addAll(findVertical(x1, y1, directiony, verticalLength));
		}
		// vertical first
		if(verticalLength>0) l2.addAll(findVertical(x1, y1, directiony, verticalLength));		
		if(inclineLength>0){
			if(!l2.isEmpty())
				l2.addAll(findIncline(l2.get(l2.size()-1).getKey(), l2.get(l2.size()-1).getValue(), directionx, directiony, inclineLength));
			else l2.addAll(findIncline(x1, y1, directionx, directiony, inclineLength));
		}
		if(horizontalLength>0){
			if(!l2.isEmpty())
				l2.addAll(findHorizontal(l2.get(l2.size()-1).getKey(), l2.get(l2.size()-1).getValue(), directionx, horizontalLength));
			else l2.addAll(findHorizontal(x1, y1, directionx, horizontalLength));
		}
		
		
		System.out.println(l1);
		System.out.println(l2);
		
	}
	
	
	private ArrayList<Pair<Integer, Integer>> findIncline(int x1,int y1,int directionx,int directiony,int inclineLength){
		ArrayList<Pair<Integer,Integer>> line = new ArrayList<>();
		int px = x1, py=y1, count=0;
		while(count < inclineLength){
			// incline line
			line.add(new Pair<Integer, Integer>(px, py));
			px += directionx;
			py += directiony;
			count ++;
		}
		line.add(new Pair<Integer,Integer>(px,py));
		return line;
	}
	
	
	private ArrayList<Pair<Integer, Integer>> findHorizontal(int x1,int y1,int directionx,int horizontalLength){
		ArrayList<Pair<Integer,Integer>> line = new ArrayList<>();
		int px = x1, py = y1, count=0;
		while(count < horizontalLength) {
			// horizontal line
			line.add(new Pair<Integer, Integer>(px,py));
			px += directionx;
			count++;
		}
		line.add(new Pair<Integer,Integer>(px,py));
		return line;
	
	}
	
	
	private ArrayList<Pair<Integer, Integer>> findVertical(int x1,int y1,int directiony,int verticalLength){
		ArrayList<Pair<Integer,Integer>> line = new ArrayList<>();
		int px = x1, py=y1, count=0;
		while(count < verticalLength){
			// incline line
			line.add(new Pair<Integer, Integer>(px, py));
			py += directiony;
			count++;
		}
		line.add(new Pair<Integer,Integer>(px,py));
		return line;
	}
	
	public static void main(String[] args){
		Line l = new Line(Color.GREEN);
		l.addPoint(1,1,5,6);
	}
}

	