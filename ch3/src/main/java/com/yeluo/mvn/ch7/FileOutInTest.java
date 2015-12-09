package com.yeluo.mvn.ch7;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * 
 * @author YeLuo
 * @function  文件输入与输出
 */
/*
 * 
 */
public class FileOutInTest {
	public static void main(String[] args) {
		test();
	}

	private static void test() {
		//应该将输入输出流对象在try块外声明，否则不能在finally块调用对象的方法
		Scanner in=null;
		PrintWriter out=null;
		try {
			//输入   既可以用两个反斜杠，也可以用一个斜杠
			//Scanner in=new Scanner(Paths.get("E:\\test.txt"));
			in=new Scanner(new File("E:/test.txt"));
			//System.out.println(in.next());
			
			//输出
			out=new PrintWriter("E:\\test1.txt");
			out.write(in.next());
			
			/*
			 * public PrintWriter append(CharSequence csq)  将指定的字符序列添加到此 writer。 
			 * 此方法调用 out.append(csq) 的行为与调用下列方法完全相同：  out.write(csq.toString()) \
			 * append方法和writer方法都重载了
			 */
			out.append("make a dream!");
			out.write("Oh My God!");
			//刷新该流的缓冲。
			out.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			/*
			 * 在不能确保对象一定不是null的前提下，要调用对象的方法，必须先判断对象是否为null;
			 * 否则会报NullPointerException
			 * public class NullPointerExceptionextends RuntimeException当应用程序试图在需要对象的地方使用 null 时，抛出该异常。
			 * 这种情况包括： 
				调用 null 对象的实例方法。 
				访问或修改 null 对象的字段。 
				将 null 作为一个数组，获得其长度。 
				将 null 作为一个数组，访问或修改其时间片。 
				将 null 作为 Throwable 值抛出。 
				应用程序应该抛出该类的实例，指示其他对 null 对象的非法使用。 
			 */
			if(null!=in){
			   in.close();
			}		
			if(null!=out){
				   out.close();
				}	
		}
		
	}
}
