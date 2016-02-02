package com.yeluo.mvn.thread;

public class Thread3 extends Thread{
	public void run(){
		System.out.println("当前正在运行的线程:"+Thread.currentThread());
	}
}
