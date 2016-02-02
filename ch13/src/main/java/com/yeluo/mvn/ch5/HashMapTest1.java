package com.yeluo.mvn.ch5;

import java.util.Map;

/**
 * 
 * @author YeLuo
 * @function
 */
public class HashMapTest1 {
	public static void main(String[] args) {
		test1();
	}

	/**
	 * 测试HashMap的equals方法
	 */
	private static void test1() {
		Map<Integer, String> map=new HashMapTest<>();
	
		Map<Integer, String> map1=null;
		
		map.put(1, "one");
		if(!(map1 instanceof Map))
			System.out.println("GG");
		System.out.println(map.equals(map1));
		
	}
}
