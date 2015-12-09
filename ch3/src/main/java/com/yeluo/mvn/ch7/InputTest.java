package com.yeluo.mvn.ch7;

import java.io.Console;
import java.util.Scanner;

/**
 * 
 * @author YeLuo
 * @function Scanner   Console
 */
/*
 *1.要通过控制台进行输入，首先需要构造一个Scanner对象，并与“标准输入流”System.in关联 
 *
 *2.因为Scanner的输入是可见的，所以Scanner类不适用于控制台读取密码。
 * 故Java 6特别引入了Console类实现这个目的。
 * public static Console console()返回与当前 Java 虚拟机关联的唯一 Console 对象（如果有）。 
 * 返回：系统控制台（如果有），否则返回 null。
 */
public class InputTest {
	public static void main(String[] args) {
		/*scannerTest();
		scannerTest1();*/
		consoleTest();
				
	}
	/*
	 * Console类
	 */
	private static void consoleTest() {
		/*
		 *  public static Console console()返回与当前 Java 虚拟机关联的唯一 Console 对象（如果有）。 
		 * 返回：系统控制台（如果有），否则返回 null。
		 */
		System.out.println(System.console()); //输出null表示没有系统控制台
		/*Console cons=System.console();
		String username=cons.readLine("Username:");
		char[] passwd=cons.readPassword("Password:");*/
		
	}
	private static void scannerTest1() {
		/*
		 *  boolean hasNext()          如果此扫描器的输入中有另一个标记，则返回 true。   
		 *  String next()         查找并返回来自此扫描器的下一个完整标记。 
		 *  Scanner useDelimiter(String pattern)          将此扫描器的分隔模式设置为从指定 String 构造的模式。
		 *  以上三个方法都重载了 
		 */
		Scanner in=new Scanner(System.in);
		in.useDelimiter("\n");  //设置"\n"为分隔符
		String s="";
		System.out.println("please input :");
		while(in.hasNext()){
			s=in.next();
			System.out.println(s);
		}
		
	}
	public static void scannerTest() {
		Scanner in=new Scanner(System.in);
		
		System.out.println("请输入：");
		String name=in.nextLine();
		System.out.println(name);
		System.out.println("请输入一个单词：");
		String test=in.next();
		System.out.println(test);
		System.out.println("请输入一个整数：");
		int i=in.nextInt();
		System.out.println(i);
		System.out.println("请输入一个浮点数：");
		double d=in.nextDouble();
		System.out.println(d);
		
	}
}
