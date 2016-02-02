package com.yeluo.mvn.ch4;
/**
 * 
 * @author YeLuo
 * @function
 */
/*
 * 自动装箱和拆箱
 * 1.基本类型对应的包装器：Integer、Long、Float、Double、Short、Byte、Character、Boolean还有Void（前面6个数值类
 * 的父类是Number类）
 * 
 * 2.注意对象包装器类和String一样是不可变的，即一旦构造了包装器，就不允许更改包装在其中的值。
 * 同时包装器类也是final，因此不能定义它们的子类。
 * 
 * 3.强调一下：
 * 装箱和拆箱是编译器认可的 ，而不是虚拟机。编译器生成类的字节码时，插入必要的调用方法。虚拟机只是执行这些字节码。
 */
public class BoxingAndUnboxingTest {
	public static void main(String[] args) {
		/*test();
		
		ConversionOfStringAndIntTest();*/
		methodTest();
	}

	/*
	 *  static int reverse(int i) 
          	返回通过反转指定 int 值的二进制补码表示形式中位的顺序而获得的值。 
		static int reverseBytes(int i) 
          	返回通过反转指定 int 值的二进制补码表示形式中字节的顺序而获得的值。 

	 * static int signum(int i) 
          	返回指定 int 值的符号函数。 
		static String toBinaryString(int i) 
          	以二进制（基数 2）无符号整数形式返回一个整数参数的字符串表示形式。 
		static String toHexString(int i) 
          	以十六进制（基数 16）无符号整数形式返回一个整数参数的字符串表示形式。 
		static String toOctalString(int i) 
          	以八进制（基数 8）无符号整数形式返回一个整数参数的字符串表示形式。 

	 *
	 *	static int rotateLeft(int i, int distance) 
          	返回根据指定的位数循环左移指定的 int 值的二进制补码表示形式而得到的值。 
		static int rotateRight(int i, int distance) 
          	返回根据指定的位数循环右移指定的 int 值的二进制补码表示形式而得到的值。 

	 */
	private static void methodTest() {
		int reverse=Integer.reverse(1);  
		System.out.println(reverse);    //-2147483648
		int reverse1=Integer.reverseBytes(1);
		System.out.println(reverse1);   //16777216
		
		int sign=Integer.signum(-10);
		System.out.println(sign);  //-1
		int sign1=Integer.signum(10);
		System.out.println(sign1); //1
		
		String binary=Integer.toBinaryString(4);
		System.out.println(binary);  	//100
		String hex=Integer.toHexString(100);
		System.out.println(hex);        //64
		String octal=Integer.toOctalString(100);
		System.out.println(octal);      //144
		
		int left=Integer.rotateLeft(1, 2);
		System.out.println(left);       //4
		//相等于
		int left1=1<<2;
		System.out.println(left1); 	    //4
	}

	/*
	 * int和String的转换
	 * 1. String toString() 
          	返回一个表示该 Integer 值的 String 对象。 
		static String toString(int i) 
          	返回一个表示指定整数的 String 对象。 
		static String toString(int i, int radix) 
          	返回用第二个参数指定基数表示的第一个参数的字符串表示形式。 

	 * 2.static String valueOf(int i) 
          	返回 int 参数的字符串表示形式。 
          	        	
	 * 1. static int parseInt(String s) 
          	将字符串参数作为有符号的十进制整数进行解析。 
		static int parseInt(String s, int radix) 
          	使用第二个参数指定的基数，将字符串参数解析为有符号的整数。 

	 *	2.static Integer valueOf(String s) 
          	返回保存指定的 String 的值的 Integer 对象。 
		static Integer valueOf(String s, int radix) 
          	返回一个 Integer 对象，该对象中保存了用第二个参数提供的基数进行解析时从指定的 String 中提取的值。 

	 *
	 *
	 */
	private static void ConversionOfStringAndIntTest() {
		//int类型转String   有3种方法:
		//1.连接符+
		String s1=1+"";
		//2.Integer类的toString方法
		String s2=Integer.toString(1);
		//String的valueOf方法
		String s3=String.valueOf(1);
		/*
		 *  
			第1种方法：s= "" + i; //会产生两个String对象。
			第3种方法：s=String.valueOf(i); //直接使用String类的静态方法，只产生一个对象。
		 */
		
		//String类型转int  有2个方法:
		//1.Integer类的parseInt方法
		int i1=Integer.parseInt("2");
		//2.Integer的valueOf方法
		int i2=Integer.valueOf("2");
		
		
	}

	/*
	 *  static Integer valueOf(int i) 
          	返回一个表示指定的 int 值的 Integer 实例。 
		static Integer valueOf(String s) 
          	返回保存指定的 String 的值的 Integer 对象。 
		static Integer valueOf(String s, int radix) 
        	 返回一个 Integer 对象，该对象中保存了用第二个参数提供的基数进行解析时从指定的 String 中提取的值。 

	 *
	 *	 int intValue() 
          	以 int 类型返回该 Integer 的值。 
 		 long longValue() 
          	以 long 类型返回该 Integer 的值。 
         short shortValue() 
          	以 short 类型返回该 Integer 的值。 

	 */
	private static void test() {
		//自动装箱
		Integer i=12;
		//相等于
		Integer i1=Integer.valueOf(12);
		
		//static Integer valueOf(String s, int radix) 
		i1=Integer.valueOf("100", 2);
		System.out.println(i1);
		
		//自动拆箱
		int ii=new Integer(12);
		//相等于
		int ii1=new Integer(12).intValue();
			
	}
}
