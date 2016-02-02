package com.yeluo.mvn.ch1_ch5;

import java.io.File;

/**
 * 
 * @author YeLuo
 * @function  泛型程序设计
 */
/*
 * 1.使用泛型机制编写的程序代码要比那些杂乱地使用Object变量,然后再进行强制类型转换的代码具有更好的安全性和可读性.
 * 
 * 2.泛型程序设计意味着编写的代码可以被很多不同类型的对象所重用.
 * 
 * 3.在Java中增加泛型类之前,泛型程序设计是用继承实现的.
 * 
 * 4.类型参数的魅力在于:使得程序具有更好的可读性和安全性.
 * 
 * 5.一个泛型程序员的任务就是预测出所用类的未来可能有的所有用途.
 * 
 * 定义简单泛型类
 * 1.一个泛型类就是具有一个或多个类型变量的类.
 * 
 * 泛型方法
 * 1.普通方法中定义泛型方法:
 * 	public static <T> T getValue(){...}
 * 注意:类型变量放在修饰符的后面,返回类型的前面.
 * 2.java中有几种修饰符？
 * 方法的修饰符有两类：
 * 		1.访问控制修饰符(public,private,protected,default)
 * 		2.方法本身修饰符(static,final,abstract,native,synchronized)
 * 类的修饰符：
 * 		1.前缀修饰符(public,abstract,final)
 * 		2.后缀修饰符(extends,implements)
 * 注意:调用一个泛型方法时,在方法名前的尖括号中放入具体的类型
 * 
 * 类型变量的限定
 * 1.有时,类或方法需要对类型变量加以约束.
 * 格式: <T extends BoundingType>
 * 表示T应该是绑定类型的子类型.T和绑定类型可以是类,也可以接口.
 * 选择extends关键字的原因是更接近子类的概念,并且java的设计者也不打算在语言中再添加一个新的关键字.
 * 一个类型变量或通配符可以有多个限定,例如:
 * T extends Comparable & Serialization
 * 限定类型用"&"分隔,而逗号用来分隔类型变量.
 * 在java的继承中,可以根据需要拥有多个接口超类型,但是限定中至多有一个类.如果用一个类作为限定,它必须是限定列表中的第一个.
 * 
 * 泛型代码和虚拟机
 * 1.虚拟机没有泛型类型对象--所有的对象都属于普通类.
 * 无论何时定义一个泛型类型,都自动提供了一个相应的原始类型.原始类型的名字就是删去类型参数后的泛型类型名.
 * 擦除类型变量,并替换为限定类型(无限定的变量用Object).
 * 在程序中可以包含不同类型的Pair,例如Pair<String>和Pair<Double>.而擦除类型后就变成原始的Pair类型了.
 * 
 * 2.当程序调用泛型方法时,如果擦除返回类型,编译器自动插入强制类型转换.
 * 
 * 3.翻译泛型方法:桥方法
 * 
 * 4.需要记住有关java泛型 转换的事实:
 * 		1.虚拟机中没有泛型,只有普通的类和方法.
 * 		2.所有的类型参数都用它们的限定类型替换.
 * 		3.桥方法被合成保持多态
 * 		4.为保持类型安全性,必要时插入强制类型转换
 * 注意:在java虚拟机中,用参数类型和返回类型确定一个方法.
 * 
 * 5.设计反省类型时,主要目标是允许泛型代码和遗留代码之间能够互操作.
 */

public class GenericTest {
	public static void main(String[] args) {
		try {
			test();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			test1();
		} catch (Exception e) {
			e.printStackTrace();
		}
		test2();
	}

	/*
	 * 调用一个泛型方法时,在方法名前的尖括号中放入具体的类型
	 * ,也可省略(但是某些编译器会报错!).
	 */
	private static void test2() {
		//在方法名前的尖括号中放入具体的类型
		String middle=ArrayAlg.<String>getMiddle("Yeluo","LuLu","ming");
		//省略
		String middle1=ArrayAlg.getMiddle("Yeluo","LuLu","ming");
		System.out.println("middle:"+middle+"  middle1:"+middle1);
		//middle:LuLu  middle1:LuLu
		
		//报错了
		//double middle2=ArrayAlg.getMiddle(3.14,232,44);
		//Type mismatch: cannot convert from Number&Comparable<?> to double
		//double middle2=ArrayAlg.<Double>getMiddle(3.14,232,44);
		//The parameterized method <Double>getMiddle(Double...) of type ArrayAlg is not applicable for the arguments (Double, Integer, Integer)
		double middle2=(Double) ArrayAlg.getMiddle(3.14,232,44);
	}

	/*
	 * 有泛型的情况
	 * 使得程序具有更好的可读性和安全性
	 */
	private static void test1() throws Exception {
		//代码更具有可读性.
		Generic<String> bc=new Generic();
		String s="OK";
		File file=new File("e:/1.txt");
		//问题:在添加元素时,会检查错误,只可以向数组列表中添加指定类的对象.
		bc.add(s);
		//由于加入的元素不是String类型,故报错.
		//bc.add(file);//error  The method add(String) in the type Generic<String> is not applicable for the arguments (File)
	
		/*
		 * 不用强制转换
		 * 擦除get的返回类型后将返回Object类型.编译器自动插入String的强制类型转换.
		 * 也就说,编译器把这个方法调用翻译为两条虚拟机指令:
		 * 		1.对原始方法get的调用.
		 * 		2.将返回的Object类型强制转换为String类型.
		 */
		String s1=bc.get(0);	
	}

	/*
	 * 没有泛型的情况
	 * 存在两问题:
	 * 		1.当获取一个值是要强制类型转换
	 * 		2.在添加元素时,这里没有检查错误,可以向数组列表中添加任何类的对象. 
	 * 虽然对于这个添加,编译和运行都不会出错.然而在强制转换为指定类型,就可能出现错误.
	 */
	private static void test() throws Exception {
		BeforeGeneric bc=new BeforeGeneric();
		String s="OK";
		File file=new File("e:/1.txt");
		//问题:在添加元素时,这里没有检查错误,可以向数组列表中添加任何类的对象.
		bc.add(s);
		bc.add(file);
		//当获取一个值是要强制类型转换
		String s1=(String) bc.get(0);
		//原类型为File,强制转换为String类,会报java.lang.ClassCastException异常
		//String s2=(String) bc.get(1);
	}
}

//Syntax error on token "&", , expected
/*class Interval <Serializable&Comparable>{
	
}*/

class Interval <Serializable,Comparable>{
	
}