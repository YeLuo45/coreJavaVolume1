package com.yeluo.mvn.thread;
/**
 * 
 * @author YeLuo
 * @function 生产者
 */
public class Producer implements Runnable{
	private WareHouse wareHouse;
	
	public Producer(){	
	}
	public Producer(WareHouse wareHouse){
		this.wareHouse=wareHouse;
	}
	
	@Override
	public void run() {
		//永久循环
		while (true) {
			//往仓库里添加货物
			wareHouse.add();
			try {
				//为了更加直观,休息1秒钟
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
