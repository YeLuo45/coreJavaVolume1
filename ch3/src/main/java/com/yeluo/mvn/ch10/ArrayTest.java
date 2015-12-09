package com.yeluo.mvn.ch10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 * @author YeLuo
 * @function  数组
 */

/* 0.数组是一种结构，用来存储同一类型值的集合。通过一个整型下标可以访问数组中的每一个值。
 * 一旦创建了数组，就不能改变它的大小。
 * 如果需要经常扩展数组的大小，就应该使用另一种数据结构--数组列表（array list）
 * 1.声明数组的方法：1.int[] a   2.int a[]   推荐使用第一种
 * 2.初始化数组：使用new运算符创建    或不使用new的方式：int[] a={2,3,4}；
 * 注意：在Java中，允许数组长度为0。  new  elementType[0]
 * 3.创建一个数字数组是，所有元素都初始化为0.
 *   boolean数组的元素会初始化为false
 *   对象数组的元素则初始化为一个特殊值null
 * 
 * 4.for each循环：增强型的for循环
 * 不必为制定下标值而分心。
 * 
 * 5.Java中的[]运算符被预定义为检查数组边界，而且没有指针运算，即不能通过a加1得到数组的下一个元素。
 * 
 * 6.命令行参数
 * 	每一个Java程序都有一个带String arg[] 参数的main方法。这表明main方法将接受一个字符串数组(命令行参数)
 *  例如： Java Message ye  luo  xing       Message是程序名
 *  则   args[0]:  ye   args[1]:  luo    args[2]:  xing
 * 
 * 7.数组排序：Arrays类的sort方法（使用了优化的快速排序算法）
 * 
 * 8.Java时间上没有多维数组，只有一维数组。多维数组被解释为“数组的数组”
 */
public class ArrayTest {
	public static void main(String[] args) {
		/*test();
		forEachTest();
		arrayCopyTest();
		arraySortTest();
		binarySearchTest();
		ArraysCommonMethodTest();*/
		MultiArrayTest();
		
	}
	/*
	 * 多维数组
	 */
	private static void MultiArrayTest() {
		//规则的多维数组
		int[][] a=new int[4][4];
		/*
		 * 用Arrays类的deepToString方法
		 * public static String deepToString(Object[] a)
		 * 返回指定数组“深层内容”的字符串表示形式。如果数组包含作为元素的其他数组，则字符串表示形式包含其内容等。
		 * 此方法是为了将多维数组转换为字符串而设计的。 
		 */
		System.out.println(Arrays.deepToString(a));
		
		//不规则的多维数组
		int n=10;
		int[][] b=new int[n+1][];
		for(int i=0;i<=n;i++){
			b[i]=new int[i+1];
		}
		System.out.println(Arrays.deepToString(b));
	}
	/*
	 * Arrays类的常用方法
	 */
	private static void ArraysCommonMethodTest() {
		//1. 打印数组
		int[] a={1,2,4,45,34};
		System.out.println(Arrays.toString(a));
		
		/*
		 * 2.根据数组创建ArrayList 
		 * public static <T> List<T> asList(T... a)
		 *   返回一个受指定数组支持的固定大小的列表。
		 * ArrayList类的构造器 public ArrayList(Collection<? extends E> c)
		 */
		Integer[] ai={1,2,4,45,34};   //自动装箱
		
		//两个异常
		//Exception in thread "main" java.lang.ClassCastException: 
		//java.util.Arrays$ArrayList cannot be cast to java.util.ArrayList
		//ArrayList<Integer> a1=(ArrayList<Integer>) Arrays.asList(ai);
		
		//The constructor ArrayList<Integer>(List<int[]>) is undefined
		//ArrayList<Integer> a1=new ArrayList<Integer>(Arrays.asList(a));
		
		ArrayList<Integer> a1=new ArrayList<Integer>(Arrays.asList(ai));
		System.out.println(a1); 
		
		String[] stringArray = { "a", "b", "c", "d", "e" };  
		ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(stringArray));  
		// [a, b, c, d, e]  
		System.out.println(arrayList); 
		
		/*
		 * 3. 检查数组是否包含某个值
		 * ArrayList的contains方法
		 * boolean contains(Object o) 
		 * 如果此列表中包含指定的元素，则返回 true。
		 */
		boolean b=a1.contains("a");    //a1：[1, 2, 4, 45, 34]
		System.out.println(b);
		boolean b1=arrayList.contains("a"); //arrayList：[a, b, c, d, e]
		System.out.println(b1);
		
		/*
		 * 4. 合并连接两个数组
		 * 
		 */
		Integer[] a2={7,3,40,2};
		boolean re=a1.addAll(Arrays.asList(a2));
		System.out.println(a1);  //[1, 2, 4, 45, 34, 7, 3, 40, 2]
				
		/*
		 * 5.将ArrayList转换为数组
		 * public Object[] toArray()
		 * 按适当顺序（从第一个到最后一个元素）返回包含此列表中所有元素的数组。 
		 * public <T> T[] toArray(T[] a)
		 * 按适当顺序（从第一个到最后一个元素）返回包含此列表中所有元素的数组；返回数组的运行时类型是指定数组的运行时类型。
		 * 如果指定的数组能容纳列表，则将该列表返回此处。否则，将分配一个具有指定数组的运行时类型和此列表大小的新数组。
		 */
		String[] sl={ "a", "b" };
		String[] s={ "a", "b", "c", "d", "c", "d" };
		String[] s1=arrayList.toArray(s);
		System.out.println("s1.length:"+s1.length+" arrayList.size():"+arrayList.size());
		System.out.println(Arrays.toString(s1)); // [a, b, c, d, e, null]  s1.length>arrayList.size()
		
		s=stringArray;
		s1=arrayList.toArray(s);
		System.out.println("s1.length:"+s1.length+" arrayList.size():"+arrayList.size());
		System.out.println(Arrays.toString(s1));//[a, b, c, d, e]   s1.length<=arrayList.size()
		
		s=sl;
		s1=arrayList.toArray(s);
		System.out.println("sl.length:"+sl.length+" arrayList.size():"+arrayList.size());
		System.out.println(Arrays.toString(s1));//[a, b, c, d, e]   s1.length<=arrayList.size()
		
		/*
		 * 6.将数组转换为Set
		 * HashSet类的构造器：HashSet(Collection<? extends E> c) 
		 */
		Set<String> set = new HashSet<String>(Arrays.asList(stringArray));  	
		System.out.println(set); //[a, b, c, d, e] 
		
		/*
		 *7.将所有元素都设置为一个值 
		 */
		int[] i=new int[10];
		Arrays.fill(i, 1);
		System.out.println(Arrays.toString(i));  //[1, 1, 1, 1, 1, 1, 1, 1, 1, 1]
		
		/*
		 * 8.判断两个数组是否相等，并且下标相同的元素对应相等。
		 */
		int[] j=new int[10];
		boolean result=Arrays.equals(i, j);
		System.out.println(result);     //false	
		result=i.equals(j);
		System.out.println(result);  //用Object类的equals方法只能验证是否指向同一个引用，必须重写（Arrays类的equals方法）
		Arrays.fill(j, 1);
		result=Arrays.equals(i,j);
		System.out.println(result);   //true
	}
	/*
	 * Arrays类的binarySearch方法
	 * static int binarySearch(int[] a, int key) 
	 *  使用二分搜索法来搜索指定的 int 型数组，以获得指定的值。
	 * static int binarySearch(int[] a, int fromIndex, int toIndex, int key) 
	 *  使用二分搜索法来搜索指定的 int 型数组的范围，以获得指定的值。
	 *  返回：
	 *  如果它包含在数组中，则返回搜索键的索引；否则返回 (-(插入点) - 1)。
	 *  插入点 被定义为将键插入数组的那一点：即第一个大于此键的元素索引，
	 *  如果数组中的所有元素都小于指定的键，则为 a.length。注意，这保证了当且仅当此键被找到时，返回的值将 >= 0。
	 *  
	 * 上面两个方法重载
	 */
	private static void binarySearchTest() {
		int[] a={1,2,3,4,91,12,90,34};
		int result=Arrays.binarySearch(a, 5);
		System.out.println(result);   //-5    插入点为4
		result=Arrays.binarySearch(a, 90);  
		System.out.println(result);   //6
		result=Arrays.binarySearch(a, 100);
		System.out.println(result);  //-9   插入点为a.lengh=8
		result=Arrays.binarySearch(a, 1,3,1);
		System.out.println(result);  //-2   插入点为1
	} 
	/*
	 * 从49个数值，抽取6个作为数值集合
	 * random方法--范围在[0,1)之间。
	 * 问题：如何保证抽取的数不一样
	 * 方法1：移除已经收取的数
	 * 方法2：使用set集合保证抽取的数不一样
	 * 方法3：使用1~n对应的标记数组，未抽取的标记为0，已经抽取的标记为1
	 */
	private static void arraySortTest() {
		int n=49;
		int k=6;
		int[] numbers=new int[49];
		int[] result=new int[6];
		
		int r=0;
		//赋值
		for(int i=0;i<numbers.length;i++){
			numbers[i]=i+1;
		}
		System.out.println(Arrays.toString(numbers));
		for(int j=0;j<result.length;j++){
			//获得随机数的下标
			r=(int) (Math.random()*n); //[0,n-1)
			//将随机数下标对应的随机数收集
			result[j]=numbers[r];
			//移除已经收集的随机数
			numbers[r]=numbers[n-1];
			n--;
		}
		//排序
		Arrays.sort(result);
		System.out.println(Arrays.toString(result ));
	}
	/*
	 * 数组拷贝
	 * 1.直接将引用赋给变量
	 * 2.使用Arrays类的copyOf方法
	 * static int[] copyOf(int[] original, int newLength)  
	 * 复制指定的数组，截取或用 0 填充（如有必要），以使副本具有指定的长度。
	 * 原数组的副本，截取或用 0 填充以获得指定的长度 
	 * 
	 * static int[] copyOfRange(int[] original, int from, int to) --包头不包尾
	 * 将指定数组的指定范围复制到一个新数组。
	 * 上面两个方法都重载了
	 */
	private static void arrayCopyTest(){
		int[] i=new int[]{1,2,3,4};
		int[] j=i;   //两个变量将引用同一个数组。
		System.out.println(Arrays.toString(j)); 
		int[] k=Arrays.copyOf(i, i.length);  
		System.out.println(Arrays.toString(k));
		//数字的不足的，补零
		int[] k1=Arrays.copyOf(i, 2*i.length); 
		System.out.println(Arrays.toString(k1));   //[1, 2, 3, 4, 0, 0, 0, 0]
		//对象的不足的，补null
		String[] s={"a","b"};
		String[] s1=Arrays.copyOf(s, 2*s.length);  
		System.out.println(Arrays.toString(s1));  //[a, b, null, null]
		
		//布尔类型的 不足的 补false
		boolean[] b={true,true};
		boolean[] b1=Arrays.copyOf(b, 2*b.length);
		System.out.println(Arrays.toString(b1));  //[true, true, false, false]
		
		//截取
		String[] s2=Arrays.copyOf(s, 1);   
		System.out.println(Arrays.toString(s2));   //[a]		
	}
	/*
	 * for each
	 */
	private static void forEachTest() {
		int[] a=new int[10];      //所有元素都初始化为0
		for(int i:a){
			System.out.println(i);
		}
		//上面的for each 等同于下面的for 
		for(int i=0;i<a.length;i++){
			System.out.println(a[i]);
		}
		
		//更加简单的打印所有数组元素
		System.out.println(Arrays.toString(a));
	}
	/*
	 * 初始化数组
	 * 
	 */
	private static void test() {
		//初始化
		int[] a=new int[100];
		int n=10;
		int[] b=new int[n];
		int[] c=new int[]{1,2,3};
		int[] d={1,2,3};
		//int[] e=new int[3]{1,2,3};  //error
		//int[] d=new int[];  //error
		
		//数组长度为空
		int[] k1={};   
		int[] k2=new int[0];
		
		System.out.println("k1.length:"+k1.length);
		System.out.println("k2.length:"+k2.length);
	}
}
