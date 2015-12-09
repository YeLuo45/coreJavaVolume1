package com.yeluo.mvn.ch7;

import java.util.Date;

/**
 * 
 * @author YeLuo
 * @function
 */
/*
 * 1.System.out.println(x)将以x对应数据类型所允许的最大非0数字位数打印输出x。--格式化输出
 * 
 * 2.Java 5.0沿用了C语言的printf方法。例如，System.out.println("%8.2f",x);
 * 用于printf的转换符
 * d--十进制整数           x--十六进制整数           o--八进制整数       f--定点浮点数          e--指数浮点数           g--通用浮点数
 * a--十六进制浮点数         s--字符串             c--字符                b--布尔              h--散列码         tx--日期时间           %--百分号
 * n--与平台相关的行分隔符
 * 
 * 3.可以给控制格式化输出的各种标志。链接：http://note.youdao.com/share/?id=41bb3650871d9dbdc6e1f73e248b0eb5&type=note
 * 
 * 4.格式说明符语法
 * %  参数索引$  标志    宽度      .   精度      转换字符
 * %  参数索引$  标志    宽度      t   转换字符
 */
public class OutputTest {
	public static void main(String[] args) {
		printTest();
		printfDateTest();
	}

	/*
	 * 日期和时间的转换符  链接：http://note.youdao.com/share/?id=41bb3650871d9dbdc6e1f73e248b0eb5&type=note
	 */
	private static void printfDateTest() {
		        //输出日期
				System.out.printf("%tc\n",new Date());
				System.out.printf("%tF\n",new Date());  
		        System.out.printf("%tD\n",new Date());  
		        System.out.printf("%tT\n",new Date());  
		        System.out.printf("%tr\n",new Date());  
		        System.out.printf("%tR\n",new Date());  
		        System.out.printf("%tY\n",new Date());  
		        System.out.printf("%ty\n",new Date());  
		        System.out.printf("%tC\n",new Date());  
		        System.out.printf("%tb\n",new Date());  
		        System.out.printf("%tm\n",new Date());  
		        System.out.printf("%td\n",new Date());  
		        System.out.printf("%te\n",new Date());  
		        System.out.printf("%tA\n",new Date());  
		        System.out.printf("%ta\n",new Date());  
		        System.out.printf("%tj\n",new Date());  
		        System.out.printf("%tH\n",new Date());  
		        System.out.printf("%tk\n",new Date());  
		        System.out.printf("%tI\n",new Date());  
		        System.out.printf("%tl\n",new Date());  
		        System.out.printf("%tM\n",new Date());  
		        System.out.printf("%tS\n",new Date());  
		        System.out.printf("%tL\n",new Date());  
		        System.out.printf("%tN\n",new Date());  
		        System.out.printf("%tp\n",new Date());  
		        //System.out.printf("%tP\n",new Date());//此方法报错 应该和时区有关系  
		        System.out.printf("%tz\n",new Date());  
		        System.out.printf("%tZ\n",new Date());  
		        System.out.printf("%ts\n",new Date());  
		        System.out.printf("%tQ\n",new Date()); 
		        
		        //加参数索引，注意参数索引值是从1开始的，而不是从0开始的
		        System.out.printf("%1$s %2$tB %2$te,%2$tY\n","Due date:",new Date());
		        //使用 <标志
		        System.out.printf("%s %tB %<te,%<tY\n","Due date:",new Date());
		
	}

	private static void printTest() {
		double x=10000/3.0;
		float y=10000/3.0f;
		System.out.println("x="+x+" ;y="+y); //x=3333.3333333333335 ;y=3333.3333
		
		/*
		 * + -- 打印正数和负数的符号
		 * 空格 -- 在正数之前添加空格
		 */
		System.out.printf("%+,.2f\n",y); //+3,333.33
		System.out.printf("% .2f\n",y);  // 3333.33(空格+3333.33)
		System.out.printf("% .2f\n",-y); //-3333.33--负数前面没有空格
		
		
		/*
		 * 创建一个格式化的字符串
		 */
		String name="yeluo";
		int age=23;
		String message=String.format("Hello,%s.Next year,you'll be %d\n",name,age);
		System.out.printf(message);  //Hello,yeluo.Next year,you'll be 23
		
		
		
	}
}
