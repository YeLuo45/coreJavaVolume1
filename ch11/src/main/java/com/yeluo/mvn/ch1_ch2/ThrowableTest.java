package com.yeluo.mvn.ch1_ch2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

/**
 * 
 * @author YeLuo
 * @function  第11章   异常、断言、日志和调式
 */
/*
 * 1.程序中遇到错误的解决，至少要做到以下几点：
 * 		1.向用户通告错误
 * 		2.保存所有的工作结果
 * 		3.允许用户以妥善的形式退出程序
 * 
 * 2.在测试期间，需要进行大量的检测以验证程序操作的正确性。然而，这些检测可能非常耗时，在测试完成后不必保留它们，
 * 因此可以将这些测试删掉，并在其他测试需要时将它们粘贴回来，这是一件很乏味的事情。可以使用断言来有选择地启用检测。
 * 
 * 3.异常处理的任务：将控制权从错误产生的地方转移给能够处理这种情况的错误处理器。
 * 
 * 4.常见错误：
 * 		1.用户输入错误
 * 		2.设备错误
 * 		3.物理限制
 * 		4.代码错误
 * 
 * 5.异常分类
 * 	1.所有异常都是由Throwable继承而来，它的两个子类是Error和Exception。
 * 	2.Error类层次结构描述了Java运行时系统的内部错误和资源耗尽错误。应用程序不应该抛出这种类型的对象 。
 * 如果出现了这样的内部错误，除了通告给用户，并尽力使程序安全地终止之外，再也无能为力了。这种情况很少出现。
 * 	3.Exception有两个子类：RuntimeException和IOException（其他异常）
 * 	划分规则：由程序错误导致的异常属于RuntimeException；而程序本身没有问题，但是由于I/O错误这类问题导致的异常属于其他异常。
 * 	4.派生于RuntimeException的异常包含下面几种情况：
 * 		1.错误的类型转换 （ClassCastException）
 * 		2.数组访问越界 （ArrayIndexOutOfBoundsException）
 * 		3.访问空指针 （NullPointerException）
 * 	5.不是派生于RuntimeException的异常包括：
 * 		1.试图在文件尾部后面读取数据
 * 		2.试图打开一个不存在的文件
 * 		3.试图根据给定的字符串查找Class对象，而这个字符串表示的类并不存在。
 *  注意：如果出现RuntimeException异常，那么一定是你的问题。
 *  6.Java语言规范将派生于Error类或RuntimeException类的所有异常称为未检查异常，所有其他的异常称为检查异常。、
 *  编译器将核查是否为所有的已检查异常提供了异常处理器。
 *  注意：RuntimeException这个名字很容易让人混淆。实际上，现在讨论的所有错误都是发生在运行时。
 *  
 *  6.一个方法不仅需要告诉编译器将要返回什么值，还要告诉编译器有可能发生什么错误。
 *  
 *  7.什么时候需要在方法中用throws子句声明异常，需要记住在遇到下面4中情况应该抛出异常：
 *  		1.调用一个抛出已检查异常的方法，如：FileNotFoundException
 *  		2.程序运行过程中发现错误，并且利用throw语句抛出一个已检查异常
 *  		3.程序出现错误，如ArrayIndexOutOfBoundsException这样未检查异常
 *  		4.Java虚拟机和运行时库出现的内部错误
 *  如果遇到前两种情况之一，则必须告诉调用这个方法的程序员有可能抛出异常。
 *  因为任何一个抛出异常的方法都有可能是一个死亡陷阱。如果没有处理器捕获这个异常，当前执行的线程就会结束。
 *  对于可能被他人使用的方法，应该抛出可能发生的异常
 *  注意：构造器也可以声明可能抛出异常
 *	
 *  8.对于已检查异常，应该在方法的首部列出所有的异常类，每个异常类之间用逗号隔开。
 *  但是，不需要声明Java的内部错误,即从Error继承的错误——不可控制的
 *  同样，也不应该声明从RuntimeException继承的那些未检查异常——应该避免发生的
 *  注意：如果子类重写父类方法，且该方法抛出异常，则子类重写的方法抛出的异常应该为父类方法的异常的子类或本身。
 *  
 *  9.对于一个已经存在的异常类，将其抛出非常容易。在这种情况下：
 *  	1.找到一个合适的异常类
 *  	2.创建这个类的一个对象
 *  	3.将对象抛出
 *  一旦方法抛出了异常，这个方法就不可能返回到调用者（不必为返回的默认值或错误代码担忧）。
 *  
 *  10.try语句块中的任何代码抛出了一个在catch子句中说明的异常类,那么
 *  	1.程序将跳过try语句块的其余代码
 *  	2.程序将执行catch子句中的处理器代码
 *  如果try块中没有抛出异常,那么程序将会跳过catch子句
 *  注意:通常而言,在有异常时,最好什么也不做,而是将异常传递给调用者处理.
 *  
 *  11.在Java 7中,同一个catch子句可以捕获多个异常类型.
 *  前提是只有当捕获的异常类型彼此之间不存在子类关系时,才需要这个特性.
 *  注释:捕获多个异常时,异常变量隐含为final变量.例如,不能在以下子句体中为e赋不同的值:
 *  catch(FileNotFoundException|UnknownHostException){ ... }
 *  
 *  12.在catch子句中可以抛出一个异常,这样做的目的是改变异常的类型.
 *  例子,ServletException异常
 *  try{
 *  	access the database
 *  }
 *  catch(SQLException e){
 *  	throw new ServletException("database error:"+e.getMessage());
 *  }
 *  这里,ServletException用带有异常信息文本的构造器来构造.
 *  改进方法:
 *  try{
 *  	access the database
 *  }
 *  catch(SQLException e){
 *  	Throw se=new ServletException("database error");
 *  	se.initCause(e);
 *  	throw se;
 *  }
 *  当捕获到异常时,就可以使用下面这条语句重新得到原始异常:
 *  	Throwable e=se.getCause();
 *  强烈建议使用这种包装技术.这样可以让用户抛出子系统中的高级异常,而不会丢失原始异常的细节.
 *  提示:如果在一个方法中发生了一个已检查异常,而不允许抛出它,那么包装技术就十分有用.
 *  我们可以捕获这个已检查异常,并将它包装成一个运行时异常.
 *  
 *  13.当发生异常时,恰当地关闭所有数据库的连接时非常重要的.可以用finally块来实现.
 * 
 * 	14.带资源的try块很好的解决finally块的问题.原来的异常会重新抛出,而close方法抛出的异常会"被抑制".
 * 这些异常将自动捕获,并由addSuppressed方法增加到原来的异常.
 * 
 * 	15.堆栈跟踪(stack trace)是方法调用过程的列表,它包含了程序执行过程中方法调用的特定位置.
 */
public class ThrowableTest {
	public static void main(String[] args) {
		/*
		 * 捕获异常
		 * 最好不要将异常抛给main方法处理(其实就是jvm处理)
		 * 注意:catch中的异常,子类异常放在前面,父类异常放在后面.
		 * Throwable类
		 * public String getMessage()
		 * 返回此 throwable 的详细消息字符串。 
		 * void printStackTrace() 
          		将此 throwable 及其追踪输出至标准错误流。 
		 * 
		 * 注意:当发生异常时,线程不会中断.
		 */
		try {	
			checkedExceptionTest();	
			defineExceptionTest();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (FileFormatException e) {
			//返回此异常的详细消息字符串。     
			System.out.println(e.getMessage());  //文件的后缀不是.txt
			e.printStackTrace();
			
			 /* com.yeluo.mvn.ch1.FileFormatException: 文件的后缀不是.txt
				at com.yeluo.mvn.ch1.ThrowableTest.defineExceptionTest(ThrowableTest.java:107)
				at com.yeluo.mvn.ch1.ThrowableTest.main(ThrowableTest.java:84)*/

			 
		}catch (IOException e) {
			e.printStackTrace();
		}		
		
		finallyTest();
		finally1Test();
		int r=returnTest();
		System.out.println(r);   //1  覆盖try块里的原始返回值
		
		try {
			tryTest();	
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		
		getStackTraceTest();
	}

	/* Throwable类
	 * public StackTraceElement[] getStackTrace()
	 * 提供编程访问由 printStackTrace() 输出的堆栈跟踪信息。
	 * 
	 * Thread类
	 * static Map<Thread,StackTraceElement[]> getAllStackTraces() 
          	返回所有活动线程的堆栈跟踪的一个映射。 
	 *
	 *	StackTraceElement类
	 *	 String getClassName() 
          	返回类的完全限定名，该类包含由该堆栈跟踪元素所表示的执行点。 
 		String getFileName() 
          	返回源文件名，该文件包含由该堆栈跟踪元素所表示的执行点。 
 		int getLineNumber() 
          	返回源行的行号，该行包含由该堆栈该跟踪元素所表示的执行点。 
 		String getMethodName() 
          	返回方法名，此方法包含由该堆栈跟踪元素所表示的执行点。 
        boolean isNativeMethod() 
          	如果包含由该堆栈跟踪元素所表示的执行点的方法是一个本机方法，则返回 true。 
	 */
	private static void getStackTraceTest() {
		Throwable t=new Throwable();
		StackTraceElement[] frames=t.getStackTrace();
		for(StackTraceElement frame:frames){
			System.out.println("fileName:"+frame.getFileName());
			System.out.println("lineNumber:"+frame.getLineNumber());
			System.out.println("className:"+frame.getClassName());
			System.out.println("methodName:"+frame.getMethodName());
			System.out.println("is native method:"+frame.isNativeMethod());
			System.out.println(frame);
		}
		/*
		 *  fileName:ThrowableTest.java
			lineNumber:163
			className:com.yeluo.mvn.ch1.ThrowableTest
			methodName:main
			is native method:false
		    com.yeluo.mvn.ch1.ThrowableTest.getStackTraceTest(ThrowableTest.java:165)
			com.yeluo.mvn.ch1.ThrowableTest.main(ThrowableTest.java:161)
		 */
		
		Map<Thread,StackTraceElement[]> map=Thread.getAllStackTraces();
		for(Thread t1: map.keySet()){
			StackTraceElement[] frames1=map.get(t1);
			System.out.println(Arrays.toString(frames1));
		}
		/*
		 *  [java.lang.Object.wait(Native Method), java.lang.ref.ReferenceQueue.remove(Unknown Source), java.lang.ref.ReferenceQueue.remove(Unknown Source), java.lang.ref.Finalizer$FinalizerThread.run(Unknown Source)]
			[]
			[]
			[java.lang.Object.wait(Native Method), java.lang.Object.wait(Unknown Source), java.lang.ref.Reference$ReferenceHandler.run(Unknown Source)]
			[java.lang.Thread.dumpThreads(Native Method), java.lang.Thread.getAllStackTraces(Unknown Source), com.yeluo.mvn.ch1.ThrowableTest.getStackTraceTest(ThrowableTest.java:185), com.yeluo.mvn.ch1.ThrowableTest.main(ThrowableTest.java:163)]
		 */
	}

	/* Java 7.0版本的
	 * 带资源的try块
	 * try(Resource res=... ){
	 * 		work with res
	 * }
	 * try块退出时,会自动调用res.close().
	 * 这就好像使用了finally块一样.
	 * 还可以指定多个资源,用封号隔开.
	 * PrintWriter(String fileName) 
          	创建具有指定文件名称且不带自动行刷新的新 PrintWriter。
       void println(String x) 
          	打印 String，然后终止该行。 
	 */
	private static void tryTest() throws FileNotFoundException {
		//Scanner in=null;  //error in cannot be resolved to a type	
		try(Scanner in=new Scanner(new FileInputStream("e:/1.txt"))){
			while(in.hasNext()){
				System.out.println(in.next());  //kk
			}
		}
		//指定多个资源
		try(Scanner in=new Scanner(new FileInputStream("e:/1.txt"));
				PrintWriter out=new PrintWriter("e:/out.txt")){
			while(in.hasNext()){
				out.println(in.next().toUpperCase()); 
			}
		}
	}

	/*
	 * 当finally子句出现return语句时,将会出现意想不到的结果.它会覆盖try块里的原始返回值.
	 */
	private static int returnTest() {
		int n=2;
		int r=0;
		try{
			r=n*n;
			return r;
		}finally{
			System.out.println(r);  //4
			return 1;
		}
		
	}

	/*
	 * 改进finallyTest方法
	 * 独立使用try/catch和try/finally语句块
	 * 这样可以提高代码的清晰度.
	 * 内层的try块只有一个职责,就是确保关闭输入流.
	 * 外层的try块就是确保报告出现的错误.
	 * 这种设计不仅清楚而且会报告finally子句中出现的错误.
	 */
	private static void finally1Test() {
		String filename="e:/1.txt";
		InputStream in=null;
		try {
			try{
				System.out.println(1);
				in=new FileInputStream(filename);
				System.out.println(2);
			}finally{
				System.out.println(5);
				if(in!=null){				
						try {
							in.close();
						} catch (IOException e) {
							e.printStackTrace();
						}				
				}	
				System.out.println(6);
			}			
		} catch (FileNotFoundException e) {
			System.out.println(3);
			e.printStackTrace();
			System.out.println(4);
		}
		System.out.println(7);		
	}

	/*
	 * try catch finally子句的执行次序
	 * 无论在try语句块中是否遇到异常,finally子句中的in.close语句都会被执行.
	 * 注意:当finally语句块中,也抛出异常,有可能会将原始异常丢失,转而抛出finally块的异常.
	 */
	private static void finallyTest() {
		String filename="d:/1.txt";
		InputStream in=null;
		try {
			System.out.println(1);
			in=new FileInputStream(filename);
			System.out.println(2);
		} catch (FileNotFoundException e) {
			System.out.println(3);
			e.printStackTrace();
			System.out.println(4);
		}finally{
			System.out.println(5);
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}	
			System.out.println(6);
		}
		System.out.println(7);
		/*1.代码没有抛出异常
		 * output:
		 *  1
			2
			5
			6
			7
		 * 
		 * 2.代码抛出异常
		 *  1
			3
			4
			5
			6
			7
		 */
	}

	/*
	 * 
	 * 抛出自定义异常
	 * throw new 异常()
	 *
	 */
	private static void defineExceptionTest() throws FileFormatException {
		String filename="e:/1.exe";
		String description="文件的后缀不是.txt";
		//这种判断有些不足   改进:正则表达式
		if(!filename.contains(".txt")){
			throw new FileFormatException(description);		
		}		
	}

	/*
	 * 已检查异常
	 * 声明抛出异常
	 * 用throws抛出已检查异常
	 * public int read() throws IOException
	 * 从此输入流中读取一个数据字节。如果没有输入可用，则此方法将阻塞。
	 * 返回：下一个数据字节；如果已到达文件末尾，则返回 -1。 
	 */
	private static void checkedExceptionTest() throws IOException {
		String filename="e:/1.txt";
		InputStream in=new FileInputStream(filename);
		int b=0;
		while((b=in.read())!=-1){
			System.out.println(b);
		}
	}
	
	
}
