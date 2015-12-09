package com.yeluo.mvn.ch3_ch4;
/**
 * 
 * @author YeLuo
 *
 */
/*
 * 数据类型
 * 1.Java是一种强类型语言，这就意味着必须为每一个变量声明一种类型。
 * 
 * 2 1.整型
 * 	int       4个字节      范围：-10^31 ~ 10^31 - 1（正好超过20亿）
 *  short	  2个字节      范围：-10^15 ~ 10^15 - 1
 *  long   	  8个字节      范围：-10^63 ~ 10^63 - 1
 *  byte 	  1个字节      范围：-128~127
 *  2.在Java中整型的范围和运行Java代码的机器无关。
 *  long型数值有一个后缀L（l）  ；十六进制数值有一个前缀0x；八进制有一个前缀0（一般不建议使用八进制）
 *  从Java 7开始，加上前缀0b就可以写二进制数。
 *  同样，从Java 7开始，还可以为数字字面量加下划线，如用1_000_000或1_00_0000表示一百万。
 *  注意：Java没有任何符号类型（unsigned）。
 *  
 *  3.在C和C++中，int表示的整型和目标平台相关。在16位cpu上占2字节，在32位CPU上占用4个字节；
 *  类似地，在32位CPU上long值为4字节，在64位CPU上为8字节。
 *  
 *  4.Java有一个能够表示任意精度的算术包，通常称为“大数值”（big number）--一个Java对象，而不是Java类型。
 *  
 *  5.浮点类型
 * 	float       4个字节      范围：大约 ±3.402 823 47E + 38F（有效位数为6~7位）
 *  double	    2个字节      范围： 大约 ±1.797 693 134 862 315 70E + 308（有效位数为15位）
 *  绝大部分程序都采用double类型。在很多情况下，float类型的精度很难满足需求。
 *  float类型的数值有一个后缀F（f）。默认情况下是double类型的，也可以再数值后面加D（d）强调下。
 *  
 *  6.浮点类型不适用于禁止出现舍入误差的金融计算中。
 *  其主要原因是浮点数值采用二进制系统表示，而二进制系统中无法精确的表示分数1/10。就好像十进制无法精确表示1/3一样。
 *  如果需要在数值计算中不含有任何舍入误差，就应该使用BigDecimal类。
 *  
 *  7.char类型用于表示单个字符，通常用来表示字符常量。注意用单引号（''）括起来的。比如'A'对应编码为65的字符常量。
 *  Unicode编码单元可以表示为十六进制值，其范围从\u0000到\Uffff。
 *  除了可以采用转义字符序列符 \ u 表示Unicode代码单元的编码之外，还有一些用于表示特殊字符的转义字符序列符:
 *  \b--退格--\u0008    \t--制表--\u0009     \n--换行--\u000a   \r--回车--\u000d  
 *  \"--双引号--\u0022  \'--单引号--\u0027    \\--反斜杠--\u005c
 *  所有这些转义序列符都可以出现在字符常量或字符串的引号内。而 \ u 还可以出现在字符常量或字符串的引号之外。
 *  注意：在Java中，char类型也仅仅是描述 utf-16 编码的代码单元。 
 *  强烈建议不要使用char类型在程序中，除非你实际上是操纵 utf-16 编码单元。
 *  最好将需要处理的字符串用抽象数据类型表示。 
 *  
 *  8.boolean类型：只有两个值false和true。整型值和布尔值之间不能进行互相转换。
 *  而在C++里，0表示false，非0表示true。因此在Java中不会遇到if（x=0）通过编译的，而在C++中会通过，但是这是错误的。
 *  
 *  9.变量
 *   1.在Java中，每一个变量属于一种类型。在声明变量时，变量所属的类型位于变量名之前。每个声明都是以分号结束。
 *   2.变量名必须是以字母开头的有“字母”或“数字”构成的序列。
 *   “字母”包括'A'~'Z'、'a'~'z'、'_'、'$'或某种语言中代表字母的任何Unicode字符。
 *   注意：如果想要知道哪些Unicode字符属于Java中的“字母”，
 *   可以使用Character类的isJavaIdentifierStart和isJavaIdentifierPart方法进行检测。
 *   “数字”包括'0'~'9'和在某种语言中代表数字的任何Unicode字符。
 *   3.变量名中所有的字符都是有意义的，并且区分大小写。
 *   4.变量名的长度没有限制。
 *   5.提示：尽管'$'是一个合法的Java字符，但是不要在你自己的代码中使用这个字符。它只用在Java编译器或其他工具生成的名字中。
 *   6.不要使用Java的保留字作为变量名。
 *   7.不提倡在一行中声明多个变量，逐一声明每一个变量可以提高程序的可读性。
 *   
 *  10.变量的初始化
 *   在Java中，变量的声明尽可能地靠近变量第一次使用的地方，这是一种良好的程序编写风格。    
 *   注意：在C和C++中是区分变量的声明和定义。例如：  int i=10； 是一个定义，而 extern int i；是一个声明。
 *   在Java中，不区分变量的定义和声明。
 *   
 *  11.常量
 *   1.在Java中，利用关键字final指示常量。
 *   2.常量只能被赋值一次。一旦被赋值之后，就不能再更改了。
 *   3.习惯上，常量名使用全大写。
 *   4.在Java中，经常希望某个常量可以在一个类的多个方法中使用，通常称这些常量为类常量（用static final修饰）
 *   需要注意：类常量的定义位于main方法的外部。
 *  
 */
public class PrimitiveTypeTest {
	public static void main(String[] args) {	
		doubleTest1();
		doubleTest();
		booleanTest();
		
	}
	/*
	 * 测试if（x=0）能否通过编译
	 */
	private static void booleanTest() {
		int x=0;
		/*if(x=0){  //不能编译通过
			
		}*/
	}
	/*
	 * 测试浮点类型的精确度
	 */
	private static void doubleTest1() {
		//期望输出0.9
		System.out.println(2.0-1.1);//输出0.8999999999999999
		//由上可知，浮点类型的精度存在舍入误差
	}
	/*
	 * 测试double类型的溢出和出错的三个特殊情况的浮点数值：1.正无穷大  2.负无穷大 3.NaN（不是一个数字）
	 */
	private static void doubleTest() {	
		double d=1.0/0;
		System.out.println(d);  //输出infinity（正无穷大）
		double d1=Double.POSITIVE_INFINITY;
		System.out.println(d1); //输出infinity（正无穷大）
		double d2=-1/0.0;
		System.out.println(d2); //输出-infinity(负无穷大)
		double d3=Double.NEGATIVE_INFINITY; 
		System.out.println(d3);  //输出-infinity(负无穷大)
		double d4=0.0/0;
		System.out.println(d4);//输出NaN（不是一个数字）
		double d5=Double.NaN;   
		System.out.println(d5);//输出NaN（不是一个数字）
		//double d6=1/0;// 会报异常，而不是等于infinity
		double x=Double.NaN;
		//不能用下面的等于来检测一个特定值是否为Double.NaN
		if(x==Double.NaN){  //一直为false
			System.out.println("相等");  //不会输出  相等
		}
		//正确的姿势应该是用Double的isNaN方法
		if(Double.isNaN(x)){
			System.out.println("该姿势正确！"); //输出 该姿势正确！
		}		
		
	}

}
