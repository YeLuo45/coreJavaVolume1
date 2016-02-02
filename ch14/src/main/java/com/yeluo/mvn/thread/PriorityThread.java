package com.yeluo.mvn.thread;
/**
 * 
 * @author YeLuo
 * @function 用来测试优先级的线程
 */
public class PriorityThread extends Thread{
	private volatile boolean running=true;
	
	public PriorityThread(String name){
		super(name);
	}
	
	public void shutdown(){
		running=false;
	}
	
	public void run(){
		long count=0;
		System.out.println(this.getName()+" Priority:"
		+this.getPriority()+" begin running.");
		
		while(running){
			count++;
		}
		
		System.out.println(this.getName()+" Priority:"
		+this.getPriority()+" run "+count);
	}
}
