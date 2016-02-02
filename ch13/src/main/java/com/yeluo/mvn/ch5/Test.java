package com.yeluo.mvn.ch5;
/**
 * 
 * @author YeLuo
 * @function
 */
/**
 * 接口里的抽象方法的修饰符有:默认(即public abstract)、public abstract、public、abstract
 * 这是因为Java接口中的方法默认都是public,abstract类型的(都可省略),没有方法体,不能被实例化
 * 
 * 接口里的有方法体的方法的修饰符有:public static、default、static(这是JDK1.8新添加的特性)
 */
public interface Test {
	
	int getEntry();
	
	public abstract int getMap();
	
	
	abstract int getMap2();
	
	public int getMap3();
	
	//接口里的有方法体的方法的修饰符有:public static、default
	public static int getValue(){
		return 1;
	}
	
	static int getValue1(){
		return 1;
	}
	
	default int getKey(){
		return 1;
	}
}
