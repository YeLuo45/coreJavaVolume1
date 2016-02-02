package com.yeluo.mvn.ch6_ch8;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;
import java.util.logging.Logger;

/**
 * 
 * @author YeLuo
 * @function
 */
/*
 * 1.调试技巧
 * 在调试器启动之前,先给出一些有价值的建议.
 * 		1.可以用下面的方法打印或记录任意变量值:
 * 		System.out.println("x="+x);
 * 	或
 * 		Logger.getGlobal().info("x="+x);
 * 		2.一个不太为人所知但却非常有效的技巧是在每一个类中放置一个main方法.这样就可以对每一个类进行单元测试.
 * 		3.JUnit是一个非常常见的单元测试框架,利用它可以很容易地组织几套测试用例.只要修改类,就需要运行测试.
 * 在发现bug时,还要补充一些其他的测试用例.
 * 		4.日志代理(logging proxy)是一个子类对象,它可以窃取方法调用,并进行日志记录,然后调用超类中的方法.
 * 		5.利用Throwable类提供的printStackTrace方法,可以从任何一个异常对象中获得堆栈情况.
 * 注意:不一定要通过异常来生成堆栈跟踪.只要在代码的任何位置插入下面这条语句就可获得堆栈跟踪:
 * 		Thread.dumpStack();     将当前线程的堆栈跟踪打印至标准错误流。 
 * 		6.一般来说,堆栈跟踪显示在System.err上.也可以利用printStackTrace(PrintWriter s)方法将它发送到一个文件中.
 * 另外,如果想记录或显示堆栈跟踪,就可以采用下面的方式,将它捕获到一个字符串中:
 * 		ByteArrayOutputStream out=new ByteArrayOutputStream();
 *		PrintStream ps=new PrintStream(out);
 *		new Throwable().printStackTrace(ps);
 *		System.out.println(out.toString());
 * 		7.通常,将一个程序中的错误信息保存在一个文件中时非常有用的.然而,错误信息被发送到System.err中,而不是System.out中
 * 因此,不能通过该条语句获取它们: java  MyProgram >  errors.txt
 * 而是采用下面的方式捕获错误流:    java  MyProgram 2>  errors.txt
 * 要想在同一个文件中同时捕获System.err和System.out,需要使用该条命令:   java  MyProgram >&  errors.txt
 * 这些命令将工作在Windows shell中.
 * 		8.让非捕获异常的堆栈跟踪出现在System.err中并不是一个很理想的方法.比较好的方法就是将这些内容记录到一个文件中.
 * 可以调用静态的Thread.setDefaultUncaughtExceptionHandler方法改变非捕获异常的处理器:
 * 		Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
 *			
 *			@Override
 *			public void uncaughtException(Thread t, Throwable e) {
 *				// save information in logfile
 *				
 *			}
 *		});
 *		9.要想观察类的加载过程,可以用-verbose标志启动Java虚拟机.这种方法有助于诊断由于类路径引发的问题.
 *		10.Xlint选项告诉编译器对一些普遍容易出现的代码问题进行检查.例如,使用该条指令:javac -Xlint:fallthrough
 *现在通常用于描述查找可疑但不违背语法规则的代码问题的工具.
 *		下面列出了可以使用的选项:
 *		-Xlint或-Xlint:all			执行所有的检查
 *		-Xlint:deprecation			与-deprecation一样,检查废弃的方法
 *		-Xlint:fallthrough			检查switch语句中是否缺少break语句
 *		-Xlint:finally				警告finally子句不能正常地执行
 *		-Xlint:none					不执行任何检查
 *		-Xlint:path					检查类路径和源代码路径上的所有目录是否存在
 *		-Xlint:serial				警告没有serialVersionUID的串行化类
 *		-Xlint:unchecked			对通用类型与原始类型之间的危险转换给予警告
 *		11.Java虚拟机增加了对Java应用程序进行监控和管理的支持.它允许利用虚拟机中的代理装置跟踪内存消耗、线程使用、类加载等情况。
 *例子，JDK加载了一个称为jconsole的图形工具，可以用于显示虚拟机性能的统计结果。
 *		12.使用jmap使用工具获得一个堆的转储，其中显示了堆中的每个对象。
 *		13.使用-Xprof标志运行Java虚拟机，就会运行一个基本的剖析器来跟踪那些代码中经常被调用的方法。剖析信息将发送给System.out.
 *输出结果中还会显示哪些方法是由即时编译器编译的.
 * 警告:编译器的-X选项并没有正式支持.
 * 
 * 		1.使用调试器:
 *	
 */
public class DebuggingTest {
	public static void main(String[] args) {
		
		test1();
		test2();
		test3();
		test4();
		
		
	}

	/*
	 * 让非捕获异常的堆栈跟踪出现在System.err中并不是一个很理想的方法.比较好的方法就是将这些内容记录到一个文件中.
	 * 可以调用静态的Thread.setDefaultUncaughtExceptionHandler方法改变非捕获异常的处理器
	 */
	private static void test4() {
		Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
			
			@Override
			public void uncaughtException(Thread t, Throwable e) {
				// save information in logfile
				
			}
		});
		
	}

	/*
	 *  void printStackTrace() 
          	将此 throwable 及其追踪输出至标准错误流。 
 		void printStackTrace(PrintStream s) 
          	将此 throwable 及其追踪输出到指定的输出流。 
 		void printStackTrace(PrintWriter s) 
          	将此 throwable 及其追踪输出到指定的 PrintWriter。 

	 */
	private static void test3() {
		ByteArrayOutputStream out=new ByteArrayOutputStream();
		PrintStream ps=new PrintStream(out);
		new Throwable().printStackTrace(ps);
		System.out.println(out.toString());
		/*
		 * 	java.lang.Throwable
				at com.yeluo.mvn.ch6_ch8.DebuggingTest.test3(DebuggingTest.java:44)
				at com.yeluo.mvn.ch6_ch8.DebuggingTest.main(DebuggingTest.java:36)
		 */
		new Throwable().printStackTrace();
		/*
		 * 	java.lang.Throwable
				at com.yeluo.mvn.ch6_ch8.DebuggingTest.test3(DebuggingTest.java:60)
				at com.yeluo.mvn.ch6_ch8.DebuggingTest.main(DebuggingTest.java:36)

		 */
	}

	/*
	 * 日志代理
	 */
	private static void test2() {
		Random generator=new Random(){
			public double nextDouble(){
				double result=super.nextDouble();
				Logger.getGlobal().info("nextDouble:"+result);
				return result;
			}
		};	
		Double d=generator.nextDouble();
		/*
		 *  十二月 25, 2015 12:22:31 下午 com.yeluo.mvn.ch6_ch8.DebuggingTest$1 nextDouble
			信息: nextDouble:0.9074463146725618
		 */
		Thread.dumpStack();
		/*
		 * java.lang.Exception: Stack trace
				at java.lang.Thread.dumpStack(Unknown Source)
				at com.yeluo.mvn.ch6_ch8.DebuggingTest.test2(DebuggingTest.java:53)
				at com.yeluo.mvn.ch6_ch8.DebuggingTest.main(DebuggingTest.java:32)

		 */
	}

	private static void test1() {
		int x=0;
		System.out.println("x="+x);
		Logger.getGlobal().info("x="+x);
		/*
		 *  x=0
			十二月 25, 2015 12:17:00 下午 com.yeluo.mvn.ch6_ch8.DebuggingTest test1
			信息: x=0
		 */
	}
}
