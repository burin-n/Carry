package model;

import java.util.ArrayList;

public class StationHolder {
	private static StationHolder instance = new StationHolder();
	private ArrayList<Station> stations;
	private static final double gap = 300;
	
	public StationHolder(){
		stations = new ArrayList<>();
	}
	
	public void addStation(Station e){
		stations.add(e);
	}
	
	public ArrayList<Station> getStations(){
		return stations;
	}
	
	public static StationHolder getInstance(){
		return instance;
	}
	

	public Station isStation(int x,int y){
		for(Station e : stations){
			if(Math.abs(e.getCenterX() - x) <= gap/2.0 && Math.abs(e.getCenterY() - y) <= gap/2.0)
				return e;
		}
		return null;
	}
	/*public boolean isStation(int x,int y){
		for(Station e : stations){
			if(Math.abs(e.getCenterX() - x) <= 16 && Math.abs(e.getCenterY() - y) <= 16)
				return true;
		}
		return false;
	}*/
}