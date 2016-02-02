package com.yeluo.mvn.ch1;

import com.yeluo.mvn.annotations.NotThreadSafe;

/**
 * 
 * @author YeLuo
 * @function
 */
/**
 * 1.非线程安全的序列生成器
 * 
 * 2.UnsafeSequence中的问题是,在一些特殊的时序情况下,两个线程可以调用getNext方法并且得到相同的返回值.
 * 由于getNext方法里的value++不是原子操作,这些操作发生在多线程中,这些线程可能交替占有运行时,
 * 所以两个线程很可能同时读取这个值,并都使之增加1,结果就是不同的线程返回了相同的序列数.
 */
@NotThreadSafe
public class UnsafeSequence {
	private int value;
	
	/**
	 * 返回一个唯一值
	 * 
	 * 注释:value++是非原子操作
	 * value++的步骤:
	 * 1.先给value赋值(load)     value->9
	 * 2.接着为value加一(add)		9+1->10
	 * 3.最后在给value赋值(store) value=10
	 *
	 * @return
	 */
	public int getNext(){
		return value++;
	}
}
