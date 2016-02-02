package com.yeluo.mvn.thread;
/**
 * 
 * @author YeLuo
 * @function
 */
/**
 * 1.public void interrupt() 
 * 中断线程。 如果当前线程没有中断它自己（这在任何情况下都是允许的），则该线程的 checkAccess
 * 方法就会被调用，这可能抛出 SecurityException。
 * 
 * 如果线程在调用 Object 类的 wait()、wait(long) 或 wait(long, int) 方法，或者该类的
 * join()、join(long)、join(long, int)、sleep(long) 或 sleep(long, int)
 * 方法过程中受阻，则其中断状态将被清除，它还将收到一个 InterruptedException。
 * 
 * 如果该线程在可中断的通道上的 I/O 操作中受阻，则该通道将被关闭，该线程的中断状态将被设置并且该线程将收到一个
 * ClosedByInterruptException。
 * 
 * 如果该线程在一个 Selector 中受阻，则该线程的中断状态将被设置，它将立即从选择操作返回，并可能带有一个非零值，就好像调用了选择器的 wakeup
 * 方法一样。
 * 
 * 如果以前的条件都没有保存，则该线程的中断状态将被设置。
 * 
 * 中断一个不处于活动状态的线程不需要任何作用。
 *
 * 
 * 抛出： SecurityException - 如果当前线程无法修改该线程
 * 
 * 2.public static void sleep(long millis) throws InterruptedException
 * 在指定的毫秒数内让当前正在执行的线程休眠（暂停执行），此操作受到系统计时器和调度程序精度和准确性的影响。
 * 该线程不丢失任何监视器的所属权。
 * 
 * 参数： millis - 以毫秒为单位的休眠时间。 
 * 抛出： InterruptedException -
 * 如果任何线程中断了当前线程。当抛出该异常时，当前线程的中断状态 被清除。
 * 
 * 3.没有任何语言方面的需求要求一个被中断的线程应该终止.
 * 中断一个线程不过是引起它的注意.
 * 被中断的线程可以决定如何响应中断.
 * 某些线程是如此的重要以至于应该处理完异常后,继续执行,而不理会中断.
 * 但是,更加普遍的情况是,线程将简单地中断作为一个终止的请求.
 * 
 * 
 */
public class Thread5 extends Thread{
	public void run(){
		/*run0();
		run1();
		run2();
		run3();
		run4();*/
		run5();
		
	}
	
	private void run4() {
		try {
			join();
			System.out.println("当前正在运行的线程:" + Thread.currentThread());
			System.out.println("当前线程的状态:"+getState());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public void run0() {
		try {
			while(!isInterrupted()){
				System.out.println("当前正在运行的线程:" + Thread.currentThread());
			/*
			 *在中断状态被置位时调用sleep方法，它不会休眠。
			 *相反，它将清除这一状态并抛出InterruptedException。 
			 */
			interrupt();
			Thread.sleep(1000);
			//join();
			//wait();
			}
		}
		//在catch子句中调用Thread.currentThread().interrupt()来设置中断状态.
		//于是调用者可以对其进行检测.
		catch (InterruptedException e) {
			/*
			 * InterruptedException - 如果任何线程中断了当前线程。当抛出该异常时，当前线程的中断状态 被清除。
			 */
			e.printStackTrace();
		}finally{
			System.out.println("中断标志位的状态:"+isInterrupted());
			//interrupt();
		}
			/*
			 * output: 
			 * 当前正在运行的线程:Thread[Thread-0,5,main]  状态:RUNNABLE
			 * 抛出了java.lang.InterruptedException异常
			 * 中断标志位的状态:false
			 */			
	}
	/**
	 * sleep方法
	 */
	public void run1(){
		try {
			while(!isInterrupted()){
				System.out.println("当前正在运行的线程:" + Thread.currentThread());
			/*
			 *在中断状态被置位时调用sleep方法，它不会休眠。
			 *相反，它将清除这一状态并抛出InterruptedException。 
			 */
			interrupt();
			Thread.sleep(1000);
			//join();
			//wait();
			}
		}
		//在catch子句中调用Thread.currentThread().interrupt()来设置中断状态.
		//于是调用者可以对其进行检测.
		catch (InterruptedException e) {
			/*
			 * InterruptedException - 如果任何线程中断了当前线程。当抛出该异常时，当前线程的中断状态 被清除。
			 */
			Thread.currentThread().interrupt();
			/*e.printStackTrace();*/
		}finally{
			System.out.println("中断标志位的状态:"+isInterrupted());
			//interrupt();
		}
			/*
			 * output: 
			 * 当前正在运行的线程:Thread[Thread-0,5,main]  状态:RUNNABLE
			 * 抛出了java.lang.InterruptedException异常
			 * 中断标志位的状态:true
			 */		
	}
	
	/**
	 * join方法
	 */
	public void run2(){
		try {
			while(!isInterrupted()){
				System.out.println("当前正在运行的线程:" + Thread.currentThread());
			/*
			 *在中断状态被置位时调用sleep方法，它不会休眠。
			 *相反，它将清除这一状态并抛出InterruptedException。 
			 */
			interrupt();
			join();
			//wait();
			}
		}
		//在catch子句中调用Thread.currentThread().interrupt()来设置中断状态.
		//于是调用者可以对其进行检测.
		catch (InterruptedException e) {
			/*
			 * InterruptedException - 如果任何线程中断了当前线程。当抛出该异常时，当前线程的中断状态 被清除。
			 */
			Thread.currentThread().interrupt();
			/*e.printStackTrace();*/
		}finally{
			System.out.println("中断标志位的状态:"+isInterrupted());
			//interrupt();
		}
		/*
		 * output:
		 * 当前正在运行的线程:Thread[main,5,main] 状态:RUNNABLE
		 * 当前正在运行的线程:Thread[Thread-0,5,main] 
		 * 中断标志位的状态:true
		 */
	}

	/**
	 * wait方法 
	 * public final void wait() throws InterruptedException
	 * 在其他线程调用此对象的notify() 方法或 notifyAll() 方法前，导致当前线程等待。
	 * 换句话说，此方法的行为就好像它仅执行 wait(0) 调用一样。
	 * 当前线程必须拥有此对象监视器。该线程发布对此监视器的所有权并等待，直到其他线程通过调用 notify 方法，或 notifyAll
	 * 方法通知在此对象的监视器上等待的线程醒来。然后该线程将等到重新获得对监视器的所有权后才能继续执行。
	 * 
	 * 对于某一个参数的版本，实现中断和虚假唤醒是可能的，而且此方法应始终在循环中使用：
	 * 
	 * synchronized (obj) { while (<condition does not hold>) obj.wait(); ... //
	 * Perform action appropriate to condition }
	 * 此方法只应由作为此对象监视器的所有者的线程来调用。有关线程能够成为监视器所有者的方法的描述，请参阅 notify 方法。
	 * 
	 * 抛出： IllegalMonitorStateException - 如果当前线程不是此对象监视器的所有者。
	 * InterruptedException -
	 * 如果在当前线程等待通知之前或者正在等待通知时，任何线程中断了当前线程。在抛出此异常时，当前线程的中断状态 被清除。
	 */
	public void run3(){
		try {
			while(!isInterrupted()){
				System.out.println("当前正在运行的线程:" + Thread.currentThread());
			/*
			 *在中断状态被置位时调用sleep方法，它不会休眠。
			 *相反，它将清除这一状态并抛出InterruptedException。 
			 */
			interrupt();
			wait();
			}
		}
		//在catch子句中调用Thread.currentThread().interrupt()来设置中断状态.
		//于是调用者可以对其进行检测.
		catch (InterruptedException e) {
			/*
			 * InterruptedException - 如果任何线程中断了当前线程。当抛出该异常时，当前线程的中断状态 被清除。
			 */
			Thread.currentThread().interrupt();
			/*e.printStackTrace();*/
		}finally{
			System.out.println("中断标志位的状态:"+isInterrupted());
			//interrupt();
		}
		/*
		 * output: 
		 * 当前正在运行的线程:Thread[main,5,main] 状态:RUNNABLE
		 * 当前正在运行的线程:Thread[Thread-0,5,main] 
		 * 中断标志位的状态:true Exception in thread
		 * "Thread-0" java.lang.IllegalMonitorStateException at
		 * java.lang.Object.wait(Native Method) at java.lang.Object.wait(Unknown
		 * Source) at com.yeluo.mvn.thread.Thread5.run3(Thread5.java:136) at
		 * com.yeluo.mvn.thread.Thread5.run(Thread5.java:49)
		 */
	}
	/**
	 * 当一个线程执行到wait()方法时，他就进入到一个和该对象相关的等待池(Waiting Pool)中，
	 * 同时失去了对象的锁---暂时的，wait后还要返还对象锁。当前线程必须拥有当前对象的锁，
	 * 如果当前线程不是此锁的拥有者，会抛出IllegalMonitorStateException异常,
	 * 所以wait()必须在synchronized block中调用。
	 */
	public void run5() {
		try {
			synchronized(this){
				while(!isInterrupted()){
					System.out.println("当前正在运行的线程:" + Thread.currentThread());
				/*
				 *在中断状态被置位时调用sleep方法，它不会休眠。
				 *相反，它将清除这一状态并抛出InterruptedException。 
				 */
				interrupt();
				wait();
				}
			}
		}
		//在catch子句中调用Thread.currentThread().interrupt()来设置中断状态.
		//于是调用者可以对其进行检测.
		catch (InterruptedException e) {
			/*
			 * InterruptedException - 如果任何线程中断了当前线程。当抛出该异常时，当前线程的中断状态 被清除。
			 */
			//Thread.currentThread().interrupt();
			e.printStackTrace();
		}finally{
			System.out.println("中断标志位的状态:"+isInterrupted());
			//interrupt();
		}
		/*
		 * output: 
		 * 当前正在运行的线程:Thread[main,5,main] 状态:RUNNABLE
		 * 当前正在运行的线程:Thread[Thread-0,5,main] 
		 * 当前正在运行的线程:Thread[main,5,main]
		 * Thread-0当前线程的状态:WAITING 
		 * java.lang.InterruptedException 
		 * at java.lang.Object.wait(Native Method) 
		 * at java.lang.Object.wait(UnknownSource) 
		 * at com.yeluo.mvn.thread.Thread5.run5(Thread5.java:237)
		 * at com.yeluo.mvn.thread.Thread5.run(Thread5.java:52) 
		 * 中断标志位的状态:false
		 */
		
	}
	
}
