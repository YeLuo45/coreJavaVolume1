package com.yeluo.mvn.ch6;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.yeluo.mvn.ch1_ch5.Generic;

/**
 * 
 * @author YeLuo
 * @function
 */
/*
 * 12.6约束与局限性
 * 1.大多数限制都是由类型擦除引起的.
 * 
 * 2.不能用类型参数代替基本类型(不能用基本类型实例化类型参数).
 * 因此,没有Pair<double>,只有Pair<Double>.当然,其原因时类型擦除.
 * 擦除后,Pair类含有Object类型的域,而Object不能存储double值.
 * 
 * 3.运行时类型查询只适用于原始类型.
 * 
 * 4.不能创建参数化类型的数组
 * 如果需要收集参数化类型对象,只有一个安全而有效的方法:使用ArrayList:ArrayList<Pair<String>>.
 * 
 * 5.不能使用像new T(...),new T[...]或T.class这样的表达式中的变量类型.
 * 注意:Class类本身是泛型.例如,String.class是一个Class<String>的实例(事实上,它是唯一的实例).
 * 
 * 6.不能在静态域或方法中引用类型变量.
 * 泛型类的静态上下文中类型变量无效.
 * 
 * 7.不能抛出或捕获泛型类的实例,实际上,甚至泛型类扩展Throwable都是不合法的.
 * catch子句中不能使用类型变量.
 * 注释:通过使用泛型类、擦除和@SuppressWarnings标注，就能消除java类型系统的部分基础限制。
 * 注意擦除后的冲突。
 * 
 */
public class Generic2Test {
	public static <T> void  addAll(Collection<T> coll,T... ts){
		for(T t: ts){
			coll.add(t);
		}
	}
	@SafeVarargs
	public static <E> E[] array(E... array){
		return array;
	}
	public static void main(String[] args) {
		test1();
		test2();
		try {
			test3();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test4();
	}

	/*
	 * 不能使用像new T(...),new T[...]或T.class这样的表达式中的变量类型.
	 */
	private static <T> void test4() {
		//error Cannot instantiate the type T
		//String t=(String) new T();
		
		//error Illegal class literal for the type parameter T
		//String  t1=(String) T.class.newInstance();
		
	}
	/*
	 * Varargs警告
	 * 使用@SuppressWarnings("unchecked")或@SafeVarargs("unchecked")
	 */
	@SuppressWarnings("unchecked")
	private static void test3() throws Exception {
		
		Collection<Generic<String>> table=new ArrayList<Generic<String>>();		
		Generic<String> g1=new Generic<String>();
		Generic<String> g2=new Generic<String>();
		//warning 
		//Type safety: A generic array of Generic<String> is created for a varargs parameter
		addAll(table,g1,g2);
		
		//隐藏的风险 
		Generic<String>[] table1=array(g1,g2);
		Object[] os=table1;
		os[0]=new Generic<Double>();
		//System.out.println(os[0]);
		//System.out.println(table1[0]);
		//虽然能顺利运行而不会出现ArrayStoreException异常(因为数组存储只会检查擦除的是类型),
		//但是在处理table[0]时,就会在别处得到一个异常
		//竟然没报错???
		System.out.println(table1[0].add("ok"));
		System.out.println(table1[0].get(0));
		
	}

	/*
	 * 不允许创建参数化类型的数组
	 */
	private static void test2() {
		//error
		//Cannot create a generic array of ArrayList<String>
		//List<String>[] table=new ArrayList<String>[10];
		
	}

	/*
	 * 运行时类型查询只适用于原始类型
	 * 记住下列的风险,无论何时使用instanceof或涉及泛型类型的强制类型转换表达式都会看到一个编译器警告.
	 */
	private static void test1() {
		ArrayList<String> a=new ArrayList<String>();
		/*
		 * error
		 * Cannot perform instanceof check against parameterized type ArrayList<String>.
		 *  Use the form ArrayList<?> instead since further generic type information 
		 *  will be erased at runtime
		 */
		/*if(a instanceof ArrayList<String>){
			
		}*/
		/*
		 * error
		 * Cannot perform instanceof check against parameterized type ArrayList<T>. 
		 * Use the form ArrayList<?> instead since further generic type information 
		 * will be erased at runtime
		 */
		/*if(a instanceof ArrayList<T>){
			
		}*/
		//测试a是否是任意类型的一个ArrayList
		if(a instanceof ArrayList<?>){
			System.out.println("yes");  //yes
		}
		if(a instanceof ArrayList){
			System.out.println("yes");  //yes
		}
		
		//强制类型转换  
		//记住下列的风险,无论何时使用instanceof或涉及泛型类型的强制类型转换表达式都会看到一个编译器警告.
		//warning  没有
		Generic<String> g=new Generic<>();
		Generic<String> g1=g;
		System.out.println(g1);
		ArrayList<String> a1=(ArrayList<String>)a; 
		
		//同样的道理,getClass返回的也是原始类型
		ArrayList<Double> d=new ArrayList<Double>();
		if(a.getClass()==d.getClass()){
			System.out.println("a.getClass():"+a.getClass());//a.getClass():class java.util.ArrayList
			System.out.println("d.getClass():"+d.getClass());//d.getClass():class java.util.ArrayList
			System.out.println("所有的类型查询只产生原始类型!");//所有的类型查询只产生原始类型!
		}
	}
}
