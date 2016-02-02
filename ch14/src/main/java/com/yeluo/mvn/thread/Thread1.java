package com.yeluo.mvn.thread;
/**
 * 
 * @author YeLuo
 * @function
 */

public class Thread1 extends Thread{
	//volatile:同一时刻,只有一个线程修改exitFlag
	private volatile boolean exitFlag;
	private  static int count;
	
	public boolean isExitFlag() {
		return exitFlag;
	}

	public void setExitFlag(boolean exitFlag) {
		this.exitFlag = exitFlag;
	}

	public void run(){
		//run1();
		//run2();
		//run3();
		//run4();
		run5();
	}

	/**
	 * 测试守护线程
	 */
	public void run5() {
		while (true) {
			for(int i=1;i<100;i++)
				System.out.println("当前正在运行的是 "+Thread.currentThread());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 测试线程优先级
	 */
	public synchronized void run4() {
		while (count<100) {
			count++;
			System.out.println("count:"+count);
			System.out.println(Thread.currentThread() + "线程当前的状态:" + getState());
			yield();
		}
	}

	/**
	 * BLOCKED:堵塞状态，等待持有锁 受阻塞并且正在等待监视器锁的某一线程的线程状态。
	 * 处于受阻塞状态的某一线程正在等待监视器锁，以便进入一个同步的块/方法， 
	 * 或者在调用 Object.wait 之后再次进入同步的块/方法。
	 */
	public void run3() {
			synchronized(this){
				while(!exitFlag){
					System.out.println(Thread.currentThread()+"线程当前的状态:"+getState());
				}			
			}
	}

	/**
	 * WAITING : 处理等待状态 
	 * 某一等待线程的线程状态。
	 * 某一线程因为调用下列方法之一而处于等待状态： 
	 * 		不带超时值的 Object.wait
	 * 		不带超时值的 Thread.join 
	 * 		LockSupport.park 
	 * 处于等待状态的线程正等待另一个线程，以执行特定操作。
	 * 例如，已经在某一对象上调用了 Object.wait() 的线程正等待另一个线程，以便在该对象上调用 Object.notify() 或
	 * Object.notifyAll()。已经调用了 Thread.join() 的线程正在等待指定线程终止。
	 */
	public void run2() {
		while(!exitFlag){
			try {
				join();
				System.out.println(Thread.currentThread()+"线程当前的状态:"+getState());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("当前正在运行的线程:"+Thread.currentThread());
		}	
		/*
		 * output:
		 *  <状态线程> 线程当前的状态:NEW
		 * Thread[main,5,main] RUNNABLE
		 *  <状态线程> 线程当前的状态:WAITING
		 *  <状态线程> 线程当前的状态:WAITING
		 */
	}

	/**
	 * TIMED_WAITING:计时等待
	 * 具有指定等待时间的某一等待线程的线程状态。
	 * 某一线程因为调用以下带有指定正等待时间的方法之一而处于定时等待状态：
	 * 		Thread.sleep
	 * 		带有超时值的 Object.wait 
	 * 		带有超时值的 Thread.join
	 * 		LockSupport.parkNanos
	 * 		LockSupport.parkUntil
	 */
	public void run1(){
		while(!exitFlag){
			try {
				Thread.sleep(10000);
				System.out.println(Thread.currentThread()+"线程当前的状态:"+getState());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("当前正在运行的线程:"+Thread.currentThread());
		}
		/*
		 * output: 
		 *  <状态线程> 线程当前的状态:NEW 
		 * Thread[main,5,main] RUNNABLE 
		 *  <状态线程> 线程当前的状态:RUNNABLE
		 *  <状态线程> 线程当前的状态:TIMED_WAITING
		 *  <状态线程> 线程当前的状态:TERMINATED
		 */
	}
}
