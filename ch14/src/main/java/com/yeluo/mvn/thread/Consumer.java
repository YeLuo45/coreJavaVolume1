package com.yeluo.mvn.thread;
/**
 * 
 * @author YeLuo
 * @function 消费者
 */
public class Consumer implements Runnable{
	private WareHouse wareHouse;
	
	public Consumer(){	
	}
	public Consumer(WareHouse wareHouse){
		this.wareHouse=wareHouse;
	}
	
	@Override
	public void run() {
		//永久循环
		while(true){
			//从仓库里取出货物
			wareHouse.reduce();
			try {
				//为了更加直观,休息1秒钟
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
