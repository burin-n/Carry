package model;

import java.util.ArrayList;

public class StationHolder {
	private static StationHolder instance = new StationHolder();
	private ArrayList<Station> stations;
	private static final double gap = 300;
	
	public StationHolder(){
		stations = new ArrayList<>();
	}
	
	public synchronized void addStation(Station e){
		stations.add(e);
	}
	
	public synchronized ArrayList<Station> getStations(){
		return stations;
	}
	
	public synchronized static StationHolder getInstance(){
		return instance;
	}
	

	public boolean isStationNear(int x,int y){
		for(Station e : stations){
			if(Math.abs(e.getCenterX() - x) <= gap/2.0 && Math.abs(e.getCenterY() - y) <= gap/2.0)
				return true;
		}
		return false;
	}
	public Station isStation(double d,double f){
		for(Station e : stations){
			if(Math.abs(e.getCenterX() - d) <= 16 && Math.abs(e.getCenterY() - f) <= 16)

				return e;

		}
		return null;
	}
}
