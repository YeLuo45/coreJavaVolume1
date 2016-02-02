package com.yeluo.mvn.thread;

public class Thread2 extends Thread{
	public void run(){
		try {
			//暂停50秒
			sleep(50000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
