package com.yeluo.mvn.ch3_ch5;

import java.util.Date;
import java.util.EmptyStackException;
import java.util.Stack;

/**
 * 
 * @author YeLuo
 * @function
 */
/*
 *  使用异常机制的技巧
 *  1.异常处理不能代替简单的测试
 *  
 *  2.不要过分地细化异常,会使代码量急剧膨胀.
 *  细化异常:就是将每一条语句都封装在一个独立的try语句块中.
 *  
 *  3.利用异常层次结构
 *  不要只抛出RuntimeException异常.应该寻找更加适当的子类或创建自己的异常类.
 *  不要只捕获Throwable异常,否则,会使程序代码更加难读  更难维护.
 *  将一种异常转换成另一种更加合适的异常时不要犹豫.
 *  
 *  4.不要压制异常
 *  
 *  5.在检测错误时,"苛刻"要比放任更好
 *  
 *  6.不要羞于传递异常
 *  
 *  5和6可以归纳为"早抛出,晚捕获".
 *  
 *  使用断言
 *  1.在一个具有自我保护能力的程序中,断言很常用.
 *  
 *  2.断言机制允许在测试期间向代码中插入检测语句.当代码发布时,这些插入的测试语句将会被自动地移走.
 *  
 *  3.Java语言引入了关键字assert.这个关键字有两种形式:
 *  	1.assert 条件;
 *  	2.assert 条件:表达式;
 *  这两种形式都会对条件进行检测,如果结果为false,则抛出一个AssertError异常.
 *  在第二种形式中,表达式将被传入AssertError的构造器,并转换成一个消息字符串.
 *  "表达式"的唯一目的就是产生一个消息字符串.如果使用表达式的值,
 *  就会鼓励程序员试图从断言中恢复程序的运行,这不符合断言机制的初衷.
 *  
 *  4.默认情况下,断言被禁用.可以在运行程序时用-enableassertions或-ea选项启用它
 *  需要注意的是,在启用或禁用断言时不必重新编译程序.启用或禁用断言是类加载器的功能.
 *  用选项-disableassertions或-da禁用某个特定类和包的断言
 *  对于没有类加载器的"系统类"上,需要使用-enablesystemassertions/-esa开关启用断言.
 *  在程序中也可以控制类加载器的断言状态.
 *  
 *  5.在Java语言中,给出了3中处理系统错误的机制:
 *  		1.抛出一个异常
 *  		2.日志
 *  		3.使用断言
 *  
 *  6.什么时候应该选择使用断言呢?
 *  	1.断言失败是致命的,不可恢复的错误.
 *  	2.断言检查只用于开发和测试阶段.
 *  因此不应该使用断言向程序的其他部分通告发生了可恢复性的错误,或者,不应该作为程序向用户通告问题的手段.
 *  断言只应该用于在测试阶段确定程序内部的错误位置.	
 */
public class ThrowableTest2 {
	public static void main(String[] args) {
		test1();
	}

	/*
	 * 异常处理不能代替简单的测试
	 * 与执行简单的测试相比,捕获异常所花费的时间大大超过前者,
	 * 因此使用异常的基本规则是:只在异常情况下使用异常机制.
	 */
	private static void test1() {
		Stack<String> s=new Stack<>();		
		Date now=new Date();
		if(!s.isEmpty()){
			s.pop();
		}
		Date later=new Date();
		System.out.println(later.getTime()-now.getTime());  //0
		
		
		
		now=new Date();
			try{
				s.pop();
			}
			catch(EmptyStackException e){
				e.printStackTrace();
			}
		later=new Date();
		System.out.println(later.getTime()-now.getTime());//1
		/*
		 *  0
			java.util.EmptyStackException
				at java.util.Stack.peek(Unknown Source)
				at java.util.Stack.pop(Unknown Source)
				at com.yeluo.mvn.ch3.ThrowableTest2.test1(ThrowableTest2.java:37)
				at com.yeluo.mvn.ch3.ThrowableTest2.main(ThrowableTest2.java:18)
		    1

		 */
	}
}
