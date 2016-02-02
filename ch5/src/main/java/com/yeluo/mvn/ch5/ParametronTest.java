package com.yeluo.mvn.ch5;

import static java.lang.System.out;

import java.io.PrintStream;

/**
 * 
 * @author YeLuo
 * @function
 */
/*参数数量可变的方法
 * 1.Java 5.0版本提供了用可变的参数数量调用的方法
 * 比如System.out.printf()方法。
 * 源码：
 * 		public PrintStream printf(String format, Object ... args) {
        	return format(format, args);
    	}
 *
 *	2.在具有可变长参数的方法中可以把参数当成数组使用
 *
 *	3.调用的时候可以给出任意多个参数也可不给参数
 *
 *	4.一个方法只能有一个可变长参数，并且这个可变长参数必须是该方法的最后一个参数
 *
 */
public class ParametronTest {
	public static void main(String[] args) {
		test1();
	}

	/*
	 * 在调用方法的时候，如果能够和固定参数的方法匹配，也能够与可变长参数的方法匹配，则选择固定参数的方法。
	 */
	private static void test1() {
		 One test = new One();
	     test.print("hello");
	     test.print("hello", "alexia");	
	     /*output:
	        ----------
			hello
			alexia
	      * 
	      */
	}
}
class One {
	//以下两种方法定义都是错误的。
	//The variable argument type String of the method test must be the last parameter
	/* public void test(String... strings,ArrayList list){
	  
	 }
	 
	 public void test1(String... strings,ArrayList... list){
	  
	 }*/

	 public void print(String... args) {
		 //在具有可变长参数的方法中可以把参数当成数组使用
	     for (int i = 0; i < args.length; i++) {
	         out.println(args[i]);
	     }
	 }

	 public void print(String test) {
	     out.println("----------");
	 }

	}
class Two {

    public void print(String... args) {
        for (int i = 0; i < args.length; i++) {
            out.println(args[i]);
        }
    }

    public void print(String test,String...args ){
          out.println("----------");
    }

    public static void main(String[] args) {
        Two test = new Two();
        /*test.print("hello");
        test.print("hello", "alexia");*/
        //The method print(String[]) is ambiguous for the type Two
        //对于上面的代码，main方法中的两个调用都不能编译通过，因为编译器不知道该选哪个方法调用，如下所示
    }
}