package com.yeluo.mvn.ch3;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 
 * @author YeLuo
 * @function
 */
/*
 * 以下是基于典型的生产者-使用者场景的一个用例。注意，BlockingQueue 可以安全地与多个生产者和多个使用者一起使用。
 */
public class BlockingQueue1Test {
	public static void main(String[] args) {
		//BlockingQueue q = new SomeQueueImplementation();
		BlockingQueue q = new LinkedBlockingQueue();
		
	     Producer p = new Producer(q);
	     Consumer c1 = new Consumer(q);
	     Consumer c2 = new Consumer(q);
	     new Thread(p).start();
	     new Thread(c1).start();
	     new Thread(c2).start();
	}
}
class Producer implements Runnable {
	   private final BlockingQueue queue;
	   Producer(BlockingQueue q) { queue = q; }
	   public void run() {
	     try {
	       while(true) 
	       { 
	    	   queue.put(produce()); 
	    	   Thread.sleep(1000);
	       }
	     } catch (InterruptedException ex) { 
	    	// ... handle ...
	    	 }
	   }
	   public Object produce() { 
		   System.out.println("produce");
		   return "KO";
	   }
	 }

	 class Consumer implements Runnable {
	   private final BlockingQueue queue;
	   Consumer(BlockingQueue q) { queue = q; }
	   public void run() {
	     try {
	       while(true) {
	    	   consume(queue.take());
	    	   Thread.sleep(1000);
	    	   }
	     } catch (InterruptedException ex) { 
		    	// ... handle ...
    	 }
	   }
	   public void consume(Object x) { 
		   System.out.println(x);
		   System.out.println("cousume");
	   }
	 }
