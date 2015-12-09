package com.yeluo.mvn.ch5;
/**
 * 
 * @author YeLuo
 * @function  Java运算符
 */
/*1.运算符
 *  1.算术运算符+、-、*、/
 * 2.当参加/运算的两个操作数都是整数时，表示整数除法；否则，拜师浮点除法。
 * 3.整数的求余操作（取模）用%表示。
 * 4.注意，整数被0整除将会产生一个异常，而浮点数被0整除将会得到无穷大或NaN结果。
 * 5.在赋值语句中可以采用一种简化的格式书写二元算术运算符。例如：x+=4；
 * 6.使用strictfp关键字标记的方法必须使用严格的浮点计算来产生理想的结果。
 *  如果一个类用strictfp修饰，则这个类的所有方法都要使用严格的浮点计算。
 *  默认情况下，虚拟机设计者允许将中间计算结果采用扩展的精度。
 * 7.自增运算符与自减运算符
 *  注意：++n先进行加1运算；n++先使用变量值，在进行加1
 *  不建议在其他表达式的内部使用++，这样编写的代码很容易令人困惑，并会产生烦人的bug
 * 8.关系运算符与boolean运算符
 *   == !=  <  >  <=　>=
 *   &&  ||  ！    
 *   注意：&& 和 ||是按照“短路”方式求值的。如果第一个操作数已经能够确定表达式的值，第二个操作数就不必计算来。
 *   这种方式可以避免一些错误的发生。例如，表达式：x!=0 && 1/x>x+y
 *   三目运算符：  condition？expression1：expression2；当条件为真时，计算第一个表达式，否则计算第二个表达式。
 * 9.位运算符  & |  ^  ~
 *   在处理整型数值时，可以直接对数值的各个位进行操作。这就意味着可以使用屏蔽技术获得整数中的各个位。
 *   注意&和|运算符应用于布尔值，得到的结果也是布尔值。这两个运算符和&&与||很相似，但是不按照“短路”方式运算。
 *  ">>"和">>"运算符将二进制右移或左移操作。当需要屏蔽某些位时，使用这两个运算符十分方便。
 *   x>>n 相当于x/（2^n）
 *   >>> 运算符将用0来填充高位；>>运算符用符号位填充高位。没有<<<运算符。
 *   警告：对移位运算符右侧的参数进行模32的运算（除非左边的操作数是long类型，在这种情况下需对右侧操作数模64）。
 *   例如：1<<35与1<<3或8是相同的。
 *2. 数学函数与常量
 *	1.计算一个数值的平方根，可以用Math的sqrt方法
 *	public static double sqrt(double a)
 *  2.在Java中，没有幂运算，因此需要借助于Math类的pow方法  
 *  public static double pow(double a,double b)
 *  3.一些常用的三角函数：  Math的sin方法、cos方法、tan方法、atan方法、atan2方法
 *  public static double sin(double a)    a - 以弧度表示的角。
 *  4.指数函数和它的反函数：Math的exp方法（指数函数）、log方法（自然对数）、log10方法（以10为底的对数）
 *  public static double exp(double a)   返回欧拉数 e 的 double 次幂的值。
 *  public static double log(double a)   返回 double 值的自然对数（底数是 e）。
 *  5.Java还提供了两个用于表示 π 和 e常量的近似值：Math.PI  he  Math.E
 *  提示：当在源文件里 import static  java.lang.Math.*;(静态导入) ，数学方法名和常量名前添加前缀“Math.”
 *  
 *3.数值类型之间的转换
 *	1.自动转换：byte -> short -> int -> long    int --> double  long --> float  long -->double
 *	char -> int   ->的箭头表示无信息损失的转换  ;  -->的箭头表示可能有精度损失的转换
 *	2.强制类型转换  例如： int x=(int)1.1;  
 *	警告：尽量少用强制类型转换，存在信息丢失的可能性。
 *
 *4.括号与运算符级别
 *	
 *5.枚举类型
 *  如果不用枚举类型，用1、2、3、4来表示服装的大小。那么会存在着一定的隐患。在变量中很可能保存的是一个错误的值（0、7等）
 *  针对这种情况，可以自定义枚举类型。枚举类型包括有限个命名的值。
 *  例如,  enum  Size{SMALL,MEDIUM,LARGE,EXTPA_LARGE}    声明： Size s=Size.MEDIUM;
 *  Size类型的变量只能存储这个类型声明中给定的某个枚举类型，或者是null值。
 *
 */

public class OperatorTest {
	public static void main(String[] args) {
		
		typeConversionTest();
		mathTest();
		bitOperatorTest1();
		bitOperatorTest();
		complementationTest();
	}

	/*
	 * 测试类型转换
	 */
	private static void typeConversionTest() {
		// int --> float  损失精度
		int x=123456789;
		float y=x;
		System.out.println(y);  //输出1.23456792E8
		
	}

	/*
	 * 测试Math类的一些方法
	 */
	private static void mathTest() {
		/*1.平方根方法
		 * println方法是有System.out对象调用的，这种方法叫做成员方法，
		 * 而sqrt方法是有类直接调用，这种方法叫做静态方法（类方法）
		 */
		double x=4;
		double y=Math.sqrt(x);
		System.out.println(y);//输出2.0
		
		/*2.幂运算方法
		 */
		y=Math.pow(x, 2);
		System.out.println(y);
		
		/*3.三角函数
		 * 
		 */
		double x1=1/3;
		System.out.println(x1);  //注意1/3不是0.333...，而是0.0
		double x2=1.0/3;
		System.out.println(x2);  //输出0.3333333333333333
		double d=(1.0/3)*(Math.PI);
		System.out.println(Math.sin(d));
		
		/*4.round方法、ceil方法和floor方法
		 * 
		 * public static long round(double a)
		 * 返回最接近参数的 long。结果将舍入为整数：加上 1/2，对结果调用 floor 并将所得结果强制转换为 long 类型。
		 * 换句话说，结果等于以下表达式的值：  (long)Math.floor(a + 0.5d)
		 * 
		 * public static double ceil(double a) 
		 * 返回最小的（最接近负无穷大）double 值，该值大于等于参数，并等于某个整数。  
		 * 设返回值为x，则 x>a 而且是该集合里最小的（最接近负无穷大）double 值，是个整数
		 * 注意，Math.ceil(x) 的值与 -Math.floor(-x) 的值完全相同。 
		 * 
		 * public static double floor(double a)
		 * 返回最大的（最接近正无穷大）double 值，该值小于等于参数，并等于某个整数。
		 * 设返回值为x，则 x<a 而且是该集合里最大的（最接近正无穷大）double 值，是个整数
		 */
		 double test=0.99;
		 System.out.println(Math.round(test)); //输出1
		 
		 System.out.println(Math.ceil(test));  //输出1.0
		 
		 System.out.println(Math.floor(test));  //输出0.0
	}

	/*
	 * 测试">>"、">>"和">>>"的填充情况
	 * 
	 */
	private static void bitOperatorTest1() {
		int bit=8;
		int bit1=-8;
		System.out.println(bit>>3);  //1      	  填充符号位0
		System.out.println(bit>>>3); //1      	  填充0
		
		System.out.println(bit1>>3); //-1   	  填充符号位1
		System.out.println(0b11111111_11111111_11111111_11111111+"  ++++    ");
		
		System.out.println(bit1>>>3);//536870911  填充0
		System.out.println(0b00011111_11111111_11111111_11111111+"  ++++    ");
		
		System.out.println();
		System.out.println(bit<<3);  //64                       填充0
		System.out.println(bit1<<3); //-64                    填充0		
	}

	/*
	 * 测试为位运算符的屏蔽技术
	 */
	private static void bitOperatorTest() {
		int n=8;
		int bit=(n&0b1000)/0b1000;
		System.out.println(bit);//输出1表示第4位为1；输出0表示第4位为0,		
		
		int bit1=(n&(1<<3))>>3;
		System.out.println(bit1);//输出1表示第4位为1；输出0表示第4位为0,
	}

	/*
	 * 测试求余操作
	 * 求余操作的结果的符号和求余符号的左边的数值一致。
	 * 求余的数值可以是浮点类型的。
	 */
	private static void complementationTest() {
		System.out.println(5%2);
		System.out.println(5%-2);
		System.out.println(-5%2);
		System.out.println(-5%-2);
		System.out.println(5.0%2);	
	}
}
