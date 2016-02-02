package com.yeluo.mvn.ch1;

import com.yeluo.mvn.annotations.GuardedBy;
import com.yeluo.mvn.annotations.ThreadSafe;

/**
 * 
 * @author YeLuo
 * @function
 */
/**
 * 1.线程安全地序列生成器
 *  
 */
@ThreadSafe
public class Sequence {
	@GuardedBy("this")
	private int value;
	
	public synchronized int getNext(){
		return value++;
	}
}
