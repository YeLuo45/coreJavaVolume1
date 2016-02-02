package com.yeluo.mvn.ch5;

public class Test1 implements Test{
	public int getEntry(){
		return 1;
	}
	
	public  int getMap(){
		return 1;
	}
	
	
	 public int getMap2(){
		 return 1;
	}
	 @Override
	public int getMap3() {
			return 0;
	}
	 public static void main(String[] args) {
		Test t=new Test1();
		System.out.println(Test.getValue());
		System.out.println(t.getKey());
	}

	
}
