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
			if(e.isInterrupted())
				threads.remove(e);
	}
	
	public void check(){
		int num =0;
		for(Thread e : threads){
			if(e.isAlive()) num++;
		}
		for(ArrayList<Thread> e : transThread){
			for(Thread t : e)
				if(t.isAlive()) num++;
		}
		
		System.out.println(num);
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

