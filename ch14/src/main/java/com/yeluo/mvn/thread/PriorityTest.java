package com.yeluo.mvn.thread;
/**
 * 
 * @author YeLuo
 * @function 线程优先级测试
 */
/**
 * 实验环境:
 * 		Windows8.1中文版  
 * 		Intel(R) Core(TM) i5-5200 @2.20GHz  2.20GHz
 * 		双核心4线程的
 * 		4.00G内存
 * 		64位操作系统
 * 
 * 测试程序刚开始启动若干个子线程，设置优先级，然后主线程休眠10秒钟，
 * 醒来后，关掉所有子线程；子线程接到指令后停止循环，输出执行的运算次数。
 * 
 * 小结:
 * 从实验结果来看，如果CPU有空闲，即使是低优先级的线程，也可以得到足够的执行时间，接近满负荷执行。
 * 如果CPU比较繁忙，优先级的作用就体现出来了，优先级高的线程能得到比较多的执行时间，
 * 优先级比较低的线程也能得到一些执行时间，但会少一些；CPU越繁忙，差异通常越明显。
 */
public class PriorityTest {
	public static void main(String[] args) {
		
		//test1();
		
		//test2();
		
		//test4();
		
		//test8();
		
	}
	public static void test8() {
		/*
		 * 8个优先级分别为5的线程   
		 * output:
		 * priority2 Priority:5 begin running.
		 * priority3 Priority:5 begin running.
		 * priority4 Priority:5 begin running.
		 * priority6 Priority:5 begin running.
		 * priority7 Priority:5 begin running.
		 * priority1 Priority:5 begin running.
		 * priority8 Priority:5 begin running.
		 * priority5 Priority:5 begin running.
		 * priority7 Priority:5 run 544929683
		 * priority5 Priority:5 run 548049400
		 * priority6 Priority:5 run 550175046
		 * priority3 Priority:5 run 555207395
		 * priority2 Priority:5 run 554167157
		 * priority8 Priority:5 run 558453501
		 * priority1 Priority:5 run 554543538
		 * priority4 Priority:5 run 551655323
		 */
		 //priorityTest(8,5,1);
		 
		/*
		 * 从输出可以看出,线程的优先级越高,执行次数多
		 * 8个优先级从1到8的线程 
		 * output:
		 * priority4 Priority:4 begin running.
		 * priority1 Priority:1 begin running.
		 * priority2 Priority:2 begin running.
		 * priority3 Priority:3 begin running.
		 * priority8 Priority:8 begin running.
		 * priority6 Priority:6 begin running.
		 * priority7 Priority:7 begin running.
		 * priority5 Priority:5 begin running.
		 * priority7 Priority:7 run 1225570219
		 * priority8 Priority:8 run 1228024233
		 * priority5 Priority:5 run 1131434456
		 * priority6 Priority:6 run 1081665511
		 * priority2 Priority:2 run 0
		 * priority4 Priority:4 run 12852
		 * priority3 Priority:3 run 39178243
		 * priority1 Priority:1 run 12735
		 */
		priorityTest(8, 1, 2);
	}
	/**
	 * 执行次数差不多，都是11亿多次，优先级为8的线程比优先级为2的线程略微多执行了些。
  	 * 从结果上来看，执行次数只有之前的一半左右,可以认为这两个线程各占用了一个CPU。
	 */
	public static void test4() {
		/*
		 * 4个优先级相同的线程   优先级:5
		 * output:
		 * priority1 Priority:5 begin running.
		 * priority4 Priority:5 begin running.
		 * priority2 Priority:5 begin running.
		 * priority3 Priority:5 begin running.
		 * priority1 Priority:5 run 1179068435
		 * priority4 Priority:5 run 1161302017
		 * priority3 Priority:5 run 1148115802
		 * priority2 Priority:5 run 1149442155
		 */
		//priorityTest(4,5,1);
		
		/*
		 * 从输出可以看出优先级越高的执行的次数越多.
		 * 4个优先级分别为2,4,6,8的线程   
		 * output:
		 * priority3 Priority:6 begin running.
		 * priority1 Priority:2 begin running.
		 * priority2 Priority:4 begin running.
		 * priority4 Priority:8 begin running.
		 * priority3 Priority:6 run 1207732679
		 * priority1 Priority:2 run 1005756376
		 * priority4 Priority:8 run 1238382565
		 * priority2 Priority:4 run 1136601487
		 */
		 priorityTest(4,2,3);
	}
	/**
	 * 测试两个线程的情况
	 * 执行次数差不多，都是接近20亿次，CPU使用率为50%左右,可以认为这两个线程各占用了一个CPU。
	 */
	public static void test2() {
		/*
		 * 两个优先级相同的线程   优先级:5
		 * output:
		 * priority1 Priority:5 begin running.
		 * priority2 Priority:5 begin running.
		 * priority1 Priority:5 run 1768137791
		 * priority2 Priority:5 run 1742814294
		 */
		//priorityTest(2,5,1);
		
		/*
		 * 两个线程  一个优先级为5  另一个优先级为10
		 * output:
		 * priority1 Priority:5 begin running.
		 * priority2 Priority:10 begin running.
		 * priority2 Priority:10 run 1991816391
		 * priority1 Priority:5 run 1917814572
		 */
		priorityTest(2,5,3);
	}
	/**
	 * 测试一个线程的情况
	 * 三次执行次数差不多，都是20亿多次；CPU使用率为24-27%，双核心4线程的，可以认为这个线程占用了一个CPU。
	 */
	public static void test1() {
		/* 
		 * 一个线程  优先级为1
		 * output: 
		 * priority1 Priority:1 begin running. 
		 * priority1 Priority:1 run 2359238852
		 */
		//priorityTest(1,1,1);
		
		
		/* 
		 * 一个线程  优先级为5
		 * output: 
		 * priority1 Priority:5 begin running.
		 * priority1 Priority:5 run 2345393353
		 */
		//priorityTest(1,5,1);
		
		/* 
		 * 一个线程  优先级为10
		 * output: 
		 * priority1 Priority:5 begin running.
		 * priority1 Priority:10 run 2530705718
		 */
		priorityTest(1,10,1);
	}
	/**
	 * 
	 * @param n 线程个数
	 * @param m 优先级递增的度
	 * @param key 选择优先级模式
	 * 		  key=1  	相同模式
	 * 		  key=2 	公差为1模式
	 * 		  key=3		公比为m模式
	 * 		  key=其他        相同模式
	 */
	public static void priorityTest(int n,int m,int key) {
		//将主线程设置为最高级,以保证shutdown命令能按时发出去.
		Thread.currentThread().setPriority(10);
		
		//线程集合
		PriorityThread[] pts=new PriorityThread[n];
		
		for(int i=0;i<pts.length;i++){
			pts[i]=new PriorityThread("priority"+(i+1));
			switch (key) {
			case 1: pts[i].setPriority(m);
				break;
			case 2: pts[i].setPriority(i+m);
				break;
			case 3: pts[i].setPriority((i+1)*m);
				break;
			default:pts[i].setPriority(m);
				break;
			}
			
		}
		
		for(int i=0;i<pts.length;i++){
			pts[i].start();
		}
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		for(int i=0;i<pts.length;i++){
			pts[i].shutdown();
		}
	}
}
