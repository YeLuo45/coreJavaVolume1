package com.yeluo.mvn.ch6;
/**
 * 
 * @author YeLuo
 * @function
 * @param <T>
 */
/*
 * 泛型类的静态上下文中类型变量无效
 */
public class Singleton<T> {
	//error
	//private static T singleInstance;  //Cannot make a static reference to the non-static type T
	private static String singleInstance;
	
	//error
	//Cannot make a static reference to the non-static type T
	/*public  static T getSingleInstance(){  
		if(singleInstance==null){
			return singleInstance;
		}
	}*/
}
