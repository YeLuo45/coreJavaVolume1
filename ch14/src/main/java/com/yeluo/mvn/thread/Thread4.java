package com.yeluo.mvn.thread;
/**
 * 
 * @author YeLuo
 * @function
 */
/**
 * public static boolean interrupted()
 * 测试当前线程是否已经中断。线程的中断状态 由该方法清除。换句话说，如果连续两次调用该方法，则第二次调用将返回 false
 * （在第一次调用已清除了其中断状态之后，且第二次调用检验完中断状态前，当前线程再次中断的情况除外）。
 * 线程中断被忽略，因为在中断时不处于活动状态的线程将由此返回 false 的方法反映出来。
 * 
 * public boolean isInterrupted()
 * 测试线程是否已经中断。线程的中断状态 不受该方法的影响。 
 * 线程中断被忽略，因为在中断时不处于活动状态的线程将由此返回 false 的方法反映出来。
 */
public class Thread4 extends Thread{
	public void run(){
		
		while(!isInterrupted()){
				System.out.println("当前正在运行的线程:" + Thread.currentThread());
			
				interrupt();
				/*
				 * 线程的中断状态 不受该方法的影响。 
				 */
				System.out.println("中断标志位的状态:"+isInterrupted());
				System.out.println("中断标志位的状态:"+isInterrupted());
				/*
				 * 线程的中断状态 由该方法清除。换句话说，如果连续两次调用该方法，则第二次调用将返回 false
				 */
				System.out.println("中断标志位的状态:"+interrupted());
				System.out.println("中断标志位的状态:"+interrupted());
				interrupt();
		}	
	}
	
}
