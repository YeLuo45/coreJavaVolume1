package com.yeluo.mvn.ch9;
import java.math.*;
import java.util.Scanner;
/**
 * 
 * @author YeLuo
 * @function 大数值
 */
/*
 * 1.问题：基本的整型和浮点类型精度不能满足需求
 * 解决：1.BigInteger类是实现了任意精度的整数运算
 *      2.BigDecimal实现了任意精度的浮点数运算
 *      上面两个类都是java.math包下的
 *      
 * 2.使用静态的valueOf方法可以将普通的数值转换为大数值。
 * 
 * 3.不能使用+和* 需要用add方法和multiply方法
 *  
 */
public class BigNumberTest {
	public static void main(String[] args) {
		/*test1();
		test2();
		test3();*/
		test4();
	}
	/* BigDecimal类
	 * public BigDecimal add(BigDecimal other)        +
	 * public BigDecimal subtract(BigDecimal other)    —
	 * public BigDecimal multiply(BigDecimal other)   *
	 * public BigDecimal divide(BigDecimal other)      /
	 * public int compareTo(BigDecimal other)          比较
	 * 当此 BigInteger 在数值上小于、等于或大于 val 时，返回 -1，0，或 1。 
	 * 
	 * public static BigDecimal valueOf(long val)
	 * public static BigDecimal valueOf(long unscaledVal,int scale)    
	 * 返回：其值为 (unscaledVal × 10-scale) 的 BigDecimal。
	 * public static BigDecimal valueOf(double val)
	 * 
	 * public long longValue()    将此 BigDecimal 转换为 long。
	 */
	private static void test4() {
		BigDecimal a=BigDecimal.valueOf(100);
		BigDecimal b=BigDecimal.valueOf(20);
		
		BigDecimal c=a.add(b);
		BigDecimal d=a.multiply(b);
		BigDecimal e=a.subtract(b);
		BigDecimal f=a.divide(b);
		long g=a.longValue();
		int h=a.compareTo(b);
		System.out.println("c:"+c);     //c:120	
		System.out.println("d:"+d);    //d:2000	
		System.out.println("e:"+e);   //e:80	
		System.out.println("f:"+f);  //f:5
		System.out.println("g:"+g); //g:100
		System.out.println("h:"+h);//h:1
		
	}
	/*
	 * public BigInteger add(BigInteger val)        +
	 * public BigInteger subtract(BigInteger val)   —
	 * public BigInteger multiply(BigInteger val)   *
	 * public BigInteger divide(BigInteger val)     /
	 * public BigInteger mod(BigInteger m)          求余
	 * public int compareTo(BigInteger val)         比较
	 * 当此 BigInteger 在数值上小于、等于或大于 val 时，返回 -1，0，或 1。
	 * 
	 */
	private static void test3() {
		BigInteger a=BigInteger.valueOf(100);
		BigInteger b=BigInteger.valueOf(20);
		
		BigInteger c=a.add(b);
		BigInteger d=a.multiply(b);
		BigInteger e=a.subtract(b);
		BigInteger f=a.divide(b);
		BigInteger g=a.mod(b);
		int h=a.compareTo(b);
		System.out.println("c:"+c);     //c:120	
		System.out.println("d:"+d);    //d:2000	
		System.out.println("e:"+e);   //e:80	
		System.out.println("f:"+f);  //f:5
		System.out.println("g:"+g); //g:0
		System.out.println("h:"+h);//h:1
	}

	private static void test2() {
		 Scanner in=new Scanner(System.in);
		 //彩票中奖率
		 BigInteger lotteryOdds=BigInteger.valueOf(1);
		 System.out.println("请输入数字n：");
		 int n=in.nextInt();
		 System.out.println("请输入数字k：");
		 int k=in.nextInt();
		 for(int i=1;i<=k;i++){
			 lotteryOdds=lotteryOdds
					 .multiply(BigInteger.valueOf(n-i+1))
					 .divide(BigInteger.valueOf(i));
		 }
		 System.out.println("中奖的概率:"+lotteryOdds);		
	}

	private static void test1() {
		//使用静态的valueOf方法可以将普通的数值转换为大数值
		BigInteger a=BigInteger.valueOf(100);
		BigInteger b=BigInteger.valueOf(20);
		
		//用add方法和multiply方法
		BigInteger c=a.add(b);
		BigInteger d=a.multiply(b);
		System.out.println("c:"+c);
		System.out.println("d:"+d);
				
		
	}
}
