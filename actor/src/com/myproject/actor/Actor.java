package com.myproject.actor;

public class Actor extends Thread {

	public void run(){
		System.out.println(getName()+"是一个演员");
		int count = 0;
		boolean keepRunning = true;
		while(keepRunning){
			System.out.println(getName()+"登台演出了:"+(++count));
			if(count==100){
				keepRunning = false;
			}
			
			if(count%10==0){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println(getName()+"的演出结束了");
	}
	
	
	
	
	public static void main(String[] args){
		Thread actor = new Actor();
		actor.setName("Mr.Thread man");
		actor.start();
		
		Thread actressThread = new Thread(new Actress(),"Ms.Runnabale women");
		actressThread.start();
	}
	
	
}



class Actress implements Runnable{

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName()+"是一个演员");
		int count = 0;
		boolean keepRunning = true;
		while(keepRunning){
			System.out.println(Thread.currentThread().getName()+"登台演出了:"+(++count));
			if(count==100){
				keepRunning = false;
			}
			
			if(count%10==0){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println(Thread.currentThread().getName()+"的演出结束了");
	}
	
}