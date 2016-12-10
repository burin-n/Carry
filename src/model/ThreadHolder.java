package model;

import java.util.ArrayList;

public class ThreadHolder {
	public static ThreadHolder instance = new ThreadHolder();
	private static ArrayList<Thread> threads;
	public ThreadHolder(){
		threads = new ArrayList<>(); 
	}
	
	public ArrayList<Thread> getThreads(){
		return threads;
	}
	
	public void addThread(Thread e){
		threads.add(e);
	}
	
	public void update(){
		for(Thread e: threads)
			if(!e.isAlive())
				threads.remove(e);
	}
	
	public void check(){
		for(Thread e : threads){
			System.out.println(e.isInterrupted());
		}
	}
	
	public void stopAll(){
		for(Thread e : threads)
			e.interrupt();
	}
}

