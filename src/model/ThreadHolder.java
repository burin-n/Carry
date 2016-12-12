package model;

import java.util.ArrayList;


public class ThreadHolder {
	public static ThreadHolder instance = new ThreadHolder();
	private static ArrayList<Thread> threads;
	private static ArrayList<ArrayList<Thread>> transThread;
	public ThreadHolder(){
		threads = new ArrayList<>(); 
		transThread = new ArrayList<ArrayList<Thread>>();
		for(int i=0;i<5;i++)
			transThread.add(new ArrayList<>());
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
		
		for(ArrayList<Thread> lineTrans : transThread)
			for(Thread e : lineTrans)
				e.interrupt();
	}
	
	public void removeTransThread(int line){
		for(Thread e : transThread.get(line) ){
			e.interrupt();
		}
		transThread.get(line).clear();
	}
	
	public void addTransThread(int line,Thread t){
		transThread.get(line).add(t);
	}
}

