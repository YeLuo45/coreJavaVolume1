package com.yeluo.mvn.ch8;
import java.util.Date;
import java.util.Scanner;
/**
 * 
 * @author YeLuo
 * @function
 */
/*
 * 1.当需要对某个表达式的多个值进行检测时，可以使用switch语句
 * 
 * 2.块作用域：不能在嵌套的两个块中声明同名的变量
 * 注：在C++中，可以在嵌套的块中冲定义一个变量。在内层定义的变量会覆盖外层的定义的变量。这样有可能引起程序设计错误。
 * 
 * 3.条件语句（if else语句）：为了程序的可读性或代码的清晰度，应该用花括号将语句括起来。
 * else子句与最邻近的if构成一组。
 * 
 * 4.循环
 * 1.for循环、while循环和do while循环：
 *  1>for:适合于循环次数是已知的。最好选择for循环
	2>while:适合于循环次数是未知的。最好选择while循环.
	3>do while:适合于循环至少执行一次的。最好选择do while循环.
   2.do
	{
		表达式4;
		表达式3; 
	}
	while(表达式2);
	注:do while循环要有分号";"结束。
   3.while(1)和for(;;)两个死循环的区别
	一般for(;;)性能更优
	for(;;)  
	{}  
	这两个;; 空语句，编译器一般会优掉的，直接进入死循环
	while(true)  
	{}  
	每循环一次都要判断常量1是不是等于零，在这里while比for多做了这点事

	不过从汇编的角度来说，都是一样的代码。
   4.for循环：1.对计数器初始化  2.给出每次新一轮循环执行前要检测得循环条件 3.指示如何更新计数器
	规则：3个部分应该对同一个计数器变量进行初始化、检测和更新。否则，编写的循环常常晦涩难懂。
	注意：在循环中，检测两个浮点数是否相等需要格外小心。因为由于舍入的误差，最终可能得不到精确值。可能永远不会结束。
	因为0.1无法精确地用二进制表示。
	
   5.多重选择：switch语句
   switch语句将从与选项值相匹配的case标签处开始执行直到遇到break语句，或者执行到switch语句的结束处为止。
        如果没有相匹配的case标签，而又default子句，就执行这个子句。
        注意：如果在case分支语句没有break语句，那么就会接着执行下一个case分支语句。这种情况相当危险，常常会引起错误。
        为此我们在程序中从不使用switch语句
   case标签可以是：1.类型为char、byte、short、int或他们的包装器类的常量表达式   2.枚举常量   3.Java7开始，字符串字面量也可以。
   
   6.中断控制流程语句
   	1.不带标签的break语句，与用于退出switch的break语句功能一样，也可以用于退出循环语句。
   	带标签的break语句，标签必须放在希望跳出的最外层循环之前，并且必须紧跟一个冒号。
   	通过执行带标签的break语句跳转到带标签的语句块末尾。
   	注意:对任何使用break语句的代码都需要检测循环是正常结束，还是有break跳出的。
   	只能跳出语句块，而不能跳入语句块。
   	注意不提倡使用这种方式。
   	2.continue语句：结束当前循环，继续执行下一次循环。 
   
 */
public class ControlProcessTest {
	
	public static void main(String[] args) {
		blocktest1();
		test2();
		test3();
		test4();
		switchTest();
		breakContinueTest();
	}
	/*
	 * break和continue的区别
	 */
	private static void breakContinueTest() {
		int i=0;
		for(;i<10;i++){
			if(i==5){
				break;
			}
			System.out.println(i);
		}
		System.out.println("break:"+i);
		for(i=0;i<10;i++){
			if(i==5){
				continue;
			}
			System.out.println(i);
		}
		System.out.println("continue:"+i);
		
		/*
		 * 结果：
		 *  0
			1
			2
			3
			4
			break:5
			0
			1
			2
			3
			4
			6
			7
			8
			9
			continue:10

		 */
		
	}
	/*
	 * 菜单表
	 * 1.注册
	 * 2.登录
	 * 3.退出
	 */
	private static void switchTest() {
		Scanner in=new Scanner(System.in);
		System.out.println("Select an option (1,2,3)");
		int choice=in.nextInt();
		switch(choice){
			case 1:
				System.out.println("注册");
				break;
			case 2:
				System.out.println("登录");
				break;
			case 3:
				System.out.println("注销");
				break;
		}
	}
	/*
	 * 从1~n个数字中抽取k个数字来抽奖，求中奖的概率
	 */
	private static void test4() {
		 Scanner in=new Scanner(System.in);
		 //彩票中奖率
		 int lotteryOdds=1;
		 System.out.println("请输入数字n：");
		 int n=in.nextInt();
		 System.out.println("请输入数字k：");
		 int k=in.nextInt();
		 for(int i=1;i<=k;i++){
			 lotteryOdds=lotteryOdds*(n-i+1)/i;
		 }
		 System.out.println("中奖的概率:"+1.0/lotteryOdds);
	}
	/*
	 * 1.倒计时
	 * 2.检测浮点类型
	 */
	private static void test3() {
		//倒计时
		for(int i=10;i>0;i--){
			System.out.println("counting down..."+i);
		}
		System.out.println("BlastOff!");
		
		//检测浮点类型
		for(double x=0;x!=10;x+=0.1){
			System.out.println(x);  //永远也不会结束
		}
	}
	/*
	 * while(1)和for(;;)两个死循环的性能测试
	 */
	private static void test2() {
		Date d1=new Date();
		for(int i=0;i<1000000;i++){
			for(;;)  
			{break;}
		}				
		Date d2=new Date();
		System.out.println(d2.getTime()-d1.getTime());//0
		
		Date d3=new Date();
		for(int i=0;i<1000000;i++){
			while(true) 
			{break;}
		}
		Date d4=new Date();
		System.out.println(d4.getTime()-d3.getTime());//16
		
		//综上可知，for(;;)性能更优
	}
	/*
	 * 块作用域：不能在嵌套的两个块中声明同名的变量
	 */
	private static void blocktest1() {
		/*int i=1;
		for(int i=0;i<10;i++){   //Link all references for a local rename (does not change references in other files)
			
		}*/		
	}
}
