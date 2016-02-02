package com.yeluo.mvn.thread;
/**
 * 
 * @author YeLuo
 * @function 不安全的仓库
 */
@SuppressWarnings("unused")
public class UnsafeWareHouse implements WareHouse{
	
	private String name;
	private  int num;
	public final  int max;
	

	public UnsafeWareHouse(String name,int num,int max){
		this.name=name;
		this.num=num;
		this.max=max;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
	public void add(){
		//每次执行都判断仓库是否已满
		while (num >= max) {
			System.out.println("仓库已满!");

			// 如果满了,就进入等待池
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//数量加1
		num++;
		//打印当前仓库的货物数量
		System.out.println(Thread.currentThread().toString()+" put "+num);
		//仓库中已经有东西可以取了,则通知所有的消费者线程来拿
		this.notify();	
	}

	public void reduce() {
		// 每次执行都判断仓库是否空了
		while (num <= 0) {
			System.out.println("仓库空了!");

			// 如果空了,就进入等待池
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// 数量加1
		num--;
		// 打印当前仓库的货物数量
		System.out.println(Thread.currentThread().toString() + " get " + num);
		// 仓库中已经有东西可以取了,则通知所有的消费者线程来拿
		this.notify();
	}
}
