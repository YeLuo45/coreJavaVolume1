package com.yeluo.mvn.ThreadLocal;
/**
 * 
 * @author YeLuo
 * @function
 */
/**
 * 
 */
public class ThreadLocalTest1 {
	private ThreadLocal<Long> longLocal=new ThreadLocal<>();
	private ThreadLocal<String> stringLocal=new  ThreadLocal<>();
	
	//设置当前线程的两个局部变量副本
	public void set(){
		longLocal.set(Thread.currentThread().getId());
		stringLocal.set(Thread.currentThread().getName());
	}
	
	public Long getLongLocal(){
		return longLocal.get();
	}
	
	public String getStringLocal(){
		return stringLocal.get();
	}
	//调用该对象的get方法
	public String toString(){
		return "Id:"+getLongLocal()+",ThreadName:"+getStringLocal();
	}
	public static void main(String[] args) {
		test();	
		try {
			test1();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
	}
	/**
	 * 总结一下：
	 *　  	1）实际的通过ThreadLocal创建的副本是存储在每个线程自己的threadLocals中的；
	 *　　	2）为何threadLocals的类型ThreadLocalMap的键值为ThreadLocal对象，
	 *		因为每个线程中可有多个threadLocal变量，就像上面代码中的longLocal和stringLocal；
	 *　　	3）在进行get之前，必须先set，否则会报空指针异常；
	 *　如果想在get之前不需要调用set就能正常访问的话，必须重写initialValue()方法。
	 *　因为在上面的代码分析过程中，我们发现如果没有先set的话，即在map中查找不到对应的存储，
	 *则会通过调用setInitialValue方法返回i，而在setInitialValue方法中，
	 *有一个语句是T value = initialValue()， 而默认情况下，initialValue方法返回的是null。
	 */
	private static void test1() throws InterruptedException {
		final ThreadLocalTest1 t1=new ThreadLocalTest1();
		t1.set();
		System.out.println(t1);
		
		Thread t2=new Thread(new Runnable(){
			@Override
			public void run() {
				t1.set();
				System.out.println(t1);
			}		
		});
		
		t2.start();
		t2.join();
		
		System.out.println(t1);
		
		/* output:
		 * Id:1,ThreadName:main
		 * Id:10,ThreadName:Thread-0
		 * Id:1,ThreadName:main
		 * 从这段代码的输出结果可以看出，在main线程中和Thread-0线程中，
		 * longLocal保存的副本值和stringLocal保存的副本值都不一样。
		 * 最后一次在main线程再次打印副本值是为了证明在main线程中和Thread-0线程中的副本值确实是不同的
		 */
	}

	/**
	 * 如果不为当前线程设置两个局部变量副本的值
	 * 则通过get方法将会返回null
	 */
	private static void test() {
		final ThreadLocalTest1 t1=new ThreadLocalTest1();
		System.out.println(t1);
		//Id:null,ThreadName:null
	}
}
