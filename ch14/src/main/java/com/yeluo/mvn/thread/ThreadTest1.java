package com.yeluo.mvn.thread;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @author YeLuo
 * @function 测试Thread类中的方法功能
 */
/**
 * 
 */
@SuppressWarnings("unused")
public class ThreadTest1 {
	public static void main(String[] args) {
		/*unsynchronizedTest();
		synchronizedTest();
		startTest();
		stateTest();
		interruptTest();*/
		threadPropertiesTest();
	}
	
	/**
	 * 线程属性 
	 * 1.线程优先级 
	 * 默认一个线程的优先级和创建他的线程优先级相同。 每当线程调度器有机会选择新线程时,它首先选择具有较高优先级的线程.
	 * 但是,线程优先级是高度依赖于系统的. 当虚拟机依赖于宿主机平台的线程实现机制时,
	 * Java线程的优先级被映射到宿主机平台的优先级上,优先级个数也许更多,也许更少.
	 * 例如,Windows有7个优先级别,一些Java优先级将映射到相同的操作系统优先级.
	 * 在Sun为Linux提供的Java虚拟机,线程的优先级被忽略---所有线程具有相同的优先级.
	 * 
	 * 2.守护线程 
	 * 守护线程与普通线程写法上基本么啥区别，调用线程对象的方法setDaemon(true)，则可以将其设置为守护线程。
	 * 
	 * 守护线程使用的情况较少，但并非无用，举例来说，JVM的垃圾回收、内存管理等线程都是守护线程。
	 * 还有就是在做数据库应用时候，使用的数据库连接池，
	 * 连接池本身也包含着很多后台线程，监控连接个数、超时时间、状态等等。
	 * 
	 * setDaemon方法的详细说明：
	 * public final void setDaemon(boolean on)
	 * 将该线程标记为守护线程或用户线程。当正在运行的线程都是守护线程时，Java 虚拟机退出。
	 * = 该方法必须在启动线程前调用。
	 * 该方法首先调用该线程的 checkAccess 方法，且不带任何参数。这可能抛出 SecurityException（在当前线程中）。
	 * 参数：
	 * on - 如果为 true，则将该线程标记为守护线程。 
	 * 抛出： 
	 * IllegalThreadStateException -如果该线程处于活动状态。 
	 * SecurityException - 如果当前线程无法修改该线程。
	 * 
	 * 守护线程与普通线程的唯一区别是：
	 * 		理解一 :  守护线程就是main同生共死，当main退出，它将终止，而普通线程是在任务执行结束才停止。
	 * 		理解二： 用户线程：Java虚拟机在它所有非守护线程已经离开后自动离开。守护线程则是用来服务用户线程的，
	 * 		如果没有其他用户线程在运行，那么就没有可服务对象，也就没有理由继续下去。
	 * 
	 *  3.未捕获异常处理器
	 *  线程的run方法不能抛出任何被检测的异常,但是,不被检测的异常会导致线程终止.在这种情况下,线程就死亡了.
	 */
	public static void threadPropertiesTest() {
		/*hasForegroundThreadTest();
		NoForegroundThreadTest();*/
	}

	/**
	 * 当没有前台线程时，守护线程daemonThread ，随着main结束，而结束。
	 */
	public static void NoForegroundThreadTest() {
		Thread1 daemonThread=new Thread1();
		daemonThread.setDaemon(true);
		daemonThread.setName("后台线程");
		daemonThread.start();
		System.out.println("+++++++++");
	}

	/**
	 * 因为有前台线程t1的存在，守护线程daemonThread 一直执行
	 */
	public static void hasForegroundThreadTest() {
		Thread1 t1=new Thread1();
		t1.setName("前台线程");
		Thread1 daemonThread=new Thread1();
		daemonThread.setDaemon(true);
		daemonThread.setName("后台线程");
		t1.start();
		daemonThread.start();
		System.out.println("+++++++++");
	}

	/**
	 * 中断线程:
	 * 1.run方法执行方法体中最后一条语句后,并经由执行return语句返回时
	 * 2.使用interrupt方法终止线程 ,出现了在方法中没有捕获的异常时
	 * public void interrupt()
	 * 3.用stop方法(已过时,而且有不可意料的后果)
	 */
	private static void interruptTest() {
		/*test1();
		test2();
		test3();*/
		test4();
	}
	private static void test4() {
		Thread5 t5=new Thread5();
		t5.start();
		System.out.println("当前正在运行的线程:" + Thread.currentThread()+" 状态:"+t5.getState());
		System.out.println("当前正在运行的线程:" + Thread.currentThread());
		System.out.println(t5.getName()+"当前线程的状态:"+t5.getState());
		try {		
			t5.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(t5.getName()+"线程当前的状态:"+t5.getState());
		/*
		 * 当前正在运行的线程:Thread[main,5,main] 状态:RUNNABLE
		 * 当前正在运行的线程:Thread[Thread-0,5,main] 中断标志位的状态:false
		 * java.lang.InterruptedException: sleep interrupted at
		 * java.lang.Thread.sleep(Native Method) at
		 * com.yeluo.mvn.thread.Thread5.run(Thread5.java:37)
		 * Thread-0线程当前的状态:TERMINATED
		 */
	}

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
	private static void test3() {
		Thread4 t4=new Thread4();
		t4.start();
		System.out.println("当前正在运行的线程:" + Thread.currentThread()+" 状态:"+t4.getState());
		try {		
			t4.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(t4.getName()+"线程当前的状态:"+t4.getState());
		
		/*
		 * 线程中断被忽略，因为在中断时不处于活动状态的线程将由此返回 false 的方法反映出来。
		 */
		System.out.println("中断标志位的状态:"+t4.interrupted());
		System.out.println("中断标志位的状态:"+t4.isInterrupted());
		
		/*
		 * output: 当前正在运行的线程:Thread[main,5,main] 状态:RUNNABLE
		 * 当前正在运行的线程:Thread[Thread-0,5,main] 
		 * 中断标志位的状态:true
		 * 中断标志位的状态:true
		 * 中断标志位的状态:true
		 * 中断标志位的状态:false
		 * Thread-0线程当前的状态:TERMINATED
		 * 中断标志位的状态:false
		 *中断标志位的状态:false
		 */
	}

	/**
	 * run方法执行方法体中最后一条语句后(并经由执行return语句返回时)
	 */
	private static void test1() {
		Thread3 t3=new Thread3();
		t3.start();
		System.out.println(t3.getName()+"线程当前的状态:"+t3.getState());
		try {
			t3.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(t3.getName()+"线程当前的状态:"+t3.getState());
		/*
		 * output: 
		 * Thread-0线程当前的状态:RUNNABLE 
		 * 当前正在运行的线程:Thread[Thread-0,5,main]
		 * Thread-0线程当前的状态:TERMINATED
		 */
	}

	/**
	 * 使用interrupt方法终止线程 
	 * 出现了在方法中没有捕获的异常时
	 */
	public static void test2() {
		Thread2 t2=new Thread2();
		t2.start();	
	    t2.interrupt();	
		try {
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(t2.getName()+"线程当前的状态:"+t2.getState());
		
		/*
		 * output: 
		 * java.lang.InterruptedException: sleep interrupted 
		 * at java.lang.Thread.sleep(Native Method) 
		 * at com.yeluo.mvn.thread.Thread2.run(Thread2.java:7)
		 * Thread-0线程当前的状态:TERMINATED
		 */
	}

	/**
	 * 没有用synchronized同步的影响
	 */
	private static void unsynchronizedTest() {
		WareHouse wareHouse=new UnsafeWareHouse("富士康",0,10);
		Producer pro=new Producer(wareHouse);
		Consumer con=new Consumer(wareHouse);
		Thread t=new Thread(pro);
		Thread t1=new Thread(con);
		t.setName("生产者1号");
		t1.setName("消费者1号");
		t.start();
		t1.start();
		/*
		 * output: Thread[消费者1号,5,main] get 0 Exception in thread "消费者1号"
		 * Exception in thread "生产者1号" Thread[生产者1号,5,main] put 0
		 * java.lang.IllegalMonitorStateException at
		 * java.lang.Object.notify(Native Method) at
		 * com.yeluo.mvn.thread.UnsafeWareHouse.add(UnsafeWareHouse.java:50) at
		 * com.yeluo.mvn.thread.Producer.run(Producer.java:21) at
		 * java.lang.Thread.run(Unknown Source)
		 * java.lang.IllegalMonitorStateException at
		 * java.lang.Object.notify(Native Method) at
		 * com.yeluo.mvn.thread.UnsafeWareHouse.reduce(UnsafeWareHouse.java:70)
		 * at com.yeluo.mvn.thread.Consumer.run(Consumer.java:21) at
		 * java.lang.Thread.run(Unknown Source)
		 * 
		 */
	}

	/**
	 * 使用synchronized同步的作用
	 */
	private static void synchronizedTest() {
		SafeWareHouse wareHouse=new SafeWareHouse("富士康",0,10);
		Producer pro=new Producer(wareHouse);
		Consumer con=new Consumer(wareHouse);
		Thread t=new Thread(pro);
		Thread t1=new Thread(con);
		t.setName("生产者1号");
		t1.setName("消费者1号");
		System.out.println("当前正在运行的线程:"+Thread.currentThread());
		System.out.println(t.getName()+"线程当前的状态:"+t.getState());
		System.out.println(t1.getName()+"线程当前的状态:"+t1.getState());
		t.start();
		t1.start();
		System.out.println(t.getName()+"线程当前的状态:"+t.getState());
		System.out.println(t1.getName()+"线程当前的状态:"+t1.getState());
	}

	/**
	 * NEW:刚创建还没启动
	 * 
	 * RUNNABLE:可以执行 可运行线程的线程状态。处于可运行状态的某一线程正在 Java
	 * 虚拟机中运行，但它可能正在等待操作系统中的其他资源，比如处理器。 
	 * 注释:处于RUNNABLE线程状态,可能是可运行状态/就绪状态,也可能是运行状态/执行状态.
	 * 在Java规范里并没有将它作为一个单独状态.一个正在运行中的线程仍然处于可运行状态.
	 * 
	 * BLOCKED:堵塞状态，等待持有锁 受阻塞并且正在等待监视器锁的某一线程的线程状态。
	 * 处于受阻塞状态的某一线程正在等待监视器锁，以便进入一个同步的块/方法， 
	 * 或者在调用 Object.wait 之后再次进入同步的块/方法。
	 * 当一个线程试图获取一个内部的对象锁(而不是java.util.concurrent库中额锁),而该锁被其他线程持有,
	 * 则该线程进入阻塞状态.当所有其他线程释放该锁,并且线程调度器允许本线程持有它的时候,该线程将变成非阻塞状态.
	 * 
	 * WAITING : 处理等待状态 
	 * 某一等待线程的线程状态。
	 * 某一线程因为调用下列方法之一而处于等待状态： 
	 * 		不带超时值的 Object.wait
	 * 		不带超时值的 Thread.join 
	 * 		LockSupport.park 
	 * 		等待java.util.concurrent库中Lock或Condition时,就会出现等待状态.
	 * 处于等待状态的线程正等待另一个线程，以执行特定操作。
	 * 例如，已经在某一对象上调用了 Object.wait() 的线程正等待另一个线程，以便在该对象上调用 Object.notify() 或
	 * Object.notifyAll()。已经调用了 Thread.join() 的线程正在等待指定线程终止。
	 * 
	 * TIMED_WAITING:计时等待
	 * 具有指定等待时间的某一等待线程的线程状态。
	 * 某一线程因为调用以下带有指定正等待时间的方法之一而处于定时等待状态：
	 * 		Thread.sleep
	 * 		带有超时值的 Object.wait 
	 * 		带有超时值的 Thread.join
	 * 		LockSupport.parkNanos
	 * 		LockSupport.parkUntil
	 * 		Lock.tryLock的计时版
	 * 		Condition.await的计时版
	 * TERMINATED:终止
	 */
	private static void stateTest() {
		//test01();
		test02();
	}

	public static void test02() {
		Thread1 t1=new Thread1();
		Thread1 t2=new Thread1();
		t1.setName("<状态线程1>");
		t2.setName("<状态线程2>");
		System.out.println(t1.getName()+"线程当前的状态:"+t1.getState());
		//output: <状态线程> 线程当前的状态:NEW
		t1.start();	
		t2.start();
		//t1.setExitFlag(true);
		//t2.setExitFlag(true);
		System.out.println(Thread.currentThread()+"   "+Thread.currentThread().getState());
		//output:Thread[main,5,main]   RUNNABLE-->表示了  <状态线程> 线程还处于就绪状态,当前正在运行的是main线程
		System.out.println(t1.getName()+"线程当前的状态:"+t1.getState());
		System.out.println(t2.getName()+"线程当前的状态:"+t2.getState());
		/*try {
			t1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
		System.out.println(t1.getName()+"线程当前的状态:"+t1.getState());
		System.out.println(t2.getName()+"线程当前的状态:"+t2.getState());
	}

	public static void test01() {
		Thread1 t1=new Thread1();
		t1.setName(" <状态线程> ");	
		/*
		 * 设置t1线程的优先级
		 * 优先级高的线程并不一定比优先级低的线程执行的机会高，只是执行的机率高
		 */
		t1.setPriority(10);
		System.out.println(t1.getName()+"线程当前的状态:"+t1.getState());
		//output: <状态线程> 线程当前的状态:NEW
		t1.start();	
		System.out.println(Thread.currentThread()+"   "+Thread.currentThread().getState());
		//output:Thread[main,5,main]   RUNNABLE-->表示了  <状态线程> 线程还处于就绪状态,当前正在运行的是main线程
		System.out.println(t1.getName()+"线程当前的状态:"+t1.getState());
		t1.setExitFlag(true);
		System.out.println(t1.getName()+"线程当前的状态:"+t1.getState());
		try {
			t1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(t1.getName()+"线程当前的状态:"+t1.getState());
	}

	/**
	 * 多次启动一个线程是非法的。特别是当线程已经结束执行后，不能再重新启动。
	 * 会抛出一个异常:IllegalThreadStateException
	 */	
	private static void startTest() {
		SafeWareHouse wareHouse=new SafeWareHouse("富士康",0,10);
		Producer pro=new Producer(wareHouse);
		Consumer con=new Consumer(wareHouse);
		Thread t=new Thread(pro);
		Thread t1=new Thread(con);
		t.setName("生产者1号");
		t1.setName("消费者1号");
		/*
		 * 多次启动一个线程是非法的。特别是当线程已经结束执行后，不能再重新启动。
		 */
		t.start();
		t.start();
		t1.start();

	}
}
