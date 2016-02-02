package com.yeluo.mvn.ch3_ch6;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

/**
 * 
 * @author YeLuo
 * @function
 */
/**
 * 1.构造器
 * 		1.构造器和类同名
 * 		2.每个类可以有一个以上的构造器
 * 		3.构造器可以有0个、1个或多个参数
 * 		4.构造器没有返回值
 * 		5.构造器总是伴随着new操作符
 * 注意：不要在构造器中定义与实例域重名的局部变量，因为这些变量只能在构造器内部访问。
 * 这些变量屏蔽了同名的实例域。
 * 
 * 2.隐式参数和显式参数
 * 	隐式参数就是实例域，每个方法中，关键字this表还是隐式参数。
 * 	而显式参数就是在方法名后面括号中的数值，明显地列在方法声明中的。
 * 
 * 3.封装的优点
 * 	在有些时候，需要获得或设置实例域的值。因此，应该提供下面三项内容：
 * 		1.一个私有的数据域
 * 		2.一个公有的域访问器方法
 * 		3.一个公有的域更改器方法
 * 好处：可以改变内部实现，除了该类的方法之外，不会影响其他代码。
 * 注意：不要编写返回引用可变对象的访问器方法。
 * 
 * 4.final
 * final实例域必须在构造器执行之后，每个域的值被设置，并且在后面的操作中，不能再对其修改了。
 * final修饰引用对象时，不能修改引用地址，但是可以修改引用指向的内容。
 * 		1.final修饰类，该类不可继承
 * 		2.final修饰方法，该方法不可重写
 * 		3.fianl修饰成员变量，该变量的值不可改变
 * 
 * 5.静态域和静态方法
 * 	用static修饰实例域，每个类中只有一个这样的域，每个对象将会共享该静态域，它是属于类的。
 * 而每个对象对所有的实例域却都有自己的一份拷贝。
 * 	static经常和final一起使用，作为静态常量。
 * 一般来说，final修饰的实例域不能修改，但是如果该修改方法是本地方法，那么是可以修改final变量的值，
 * 比如： System类的out变量可以通过本地方法setOut方法修改out成不同的流。
 * 	静态方法不能向对象实施操作的方法。该方法是属于类的。也叫做类方法。
 * 类方法中没有隐式参数，可以访问自身类中的静态域。对象虽然能够调用静态方法，但是最好不要这样做，建议使用类调用。
 * 	走起下面两种情况下使用静态方法：
 * 		1.一个方法不需要访问对象状态，其所需参数都是通过显式参数提供。
 * 		2.一个方法只需要访问类的静态域。
 * 	静态方法的一种常见的用途就是工厂方法。例如NumberFormat类使用工厂方法产生不同风格的对象。
 * NumberFormat 是所有数值格式的抽象基类。此类提供格式化和解析数值的接口。
 * NumberFormat 还提供了一些方法来确定哪些语言环境具有数值格式，以及它们的名称是什么。 
 * 	为什么NumberFormat类不利用构造器完成这些操作呢？主要有两个原因：
 * 		1.无法命名构造器。构造器的名字必须与类名相同。但是，这里希望将得到的货币和百分比实例采取不同的名字。
 * 		2.当使用构造器时，无法改变所构造的对象类型。而Factory方法将返回一个DecimalFormat对象，这是NumberFormat的子类。
 * 
 * 6.main方法是静态方法，使用static修饰的
 * 
 * 7.方法参数
 * 	在Java中，采用按值传递（方法得到的所有参数值的一个拷贝，特别是，方法不能修改传递给它的任何参数变量的内容）。
 * 	总结下Java程序设计语言中方法参数的使用情况：
 * 		1.一个方法不能修改一个基本数据类型的参数
 * 		2.一个方法可以改变一个对象参数的状态
 * 		3.一个方法不能让对象参数引用一个新的对象
 * 
 * 8.重载和重写
 * 	重载（静态多态）：方法名相同、参数列表不同（参数类型、个数、顺序）
 * 	方法的签名：要完整地描述一个方法，需要指出方法名以及参数类型。
 * 	注意：返回类型不是方法签名的一部分。	
 * 	重写（动态多态）：在子类中，与父类的某个方法名相同、参数列表相同；
 * 	返回类型小于等于、抛出的异常小于等于、访问修饰符大于等于。
 * 
 * 9.如果构造器没有显示地给域赋予初值，那么就会被自动赋为默认值。
 * 	数值为0、布尔值为false、对象引用为null。
 * 	注意：如果不明确地对域进行初始化，就会影响程序代码的可读性。
 * 
 * 10.在类中，当且仅当没有提供任何构造方法的时候，系统会默认包含一个无参构造方法，
 * 如果显示定义了一个构造方法，则没有了默认构造方法。
 * 
 * 11.调用另一个构造方法
 * 利用关键字this引用构造方法来调用另一个构造方法。  形式：this(参数)
 * 
 * 12.初始化的方法
 * 		1.在构造器中设置值
 * 		2.在声明中赋值
 * 		3.在初始块中赋值（不常见、不常用）
 * 	调用构造器的具体处理步骤：
 * 		1.所有数据域被初始化为默认值（0、false或null）；
 * 		2.按照在类声明中出现的次序，依次执行所有域初始化语句和初始化块；
 * 		3.如果构造器第一行调用了第二个构造器，则执行第二个构造器主体；
 * 		4.执行这个构造器的主体。
 * 	在类第一次加载的时候，将会进行静态域的初始化。所有的静态初始化语句以及静态初始化块都将依照类定义的顺序执行。
 * 
 * 13.随机生成数的类Random
 * 
 * 14.不要依赖使用finalize方法回收任何短缺的资源，这是因为很难知道这个方法什么时候才能够调用。
 */
@SuppressWarnings("unused")
public class ClassTest {
	int i;
	boolean b;
	char c;
	String s;
	{
		i=1;
		System.out.println("初始化块!");
	}
	public ClassTest(){
		System.out.println("另一个构造器!");
	}
	public ClassTest(char c){
		this();
		this.c=c;
		
	}
	public static void main(String[] args) {
		/*encapsulationTest();
		
		finalTest();
		
		NumberFormatTest();
		
		callByValueTest();
		
		defaultInitializationTest();*/
		
		RandomTest();
		
	}
	/**
	 * Random类的方法
	 * public int nextInt(int n)
	 * 下一个伪随机数，在此随机数生成器序列中 0（包括）和 n（不包括）之间均匀分布的 int 值。
	 * 
	 */
	private static void RandomTest() {
		Random ran=new Random();
		int result=ran.nextInt(6)+1;
		System.out.println(result);
	}
	/*
	 * 1.默认域初始化
	 * 2.调用构造器的具体处理步骤：
	 * 		1.所有数据域被初始化为默认值（0、false或null）；
	 * 		2.按照在类声明中出现的次序，依次执行所有域初始化语句和初始化块；
	 * 		3.如果构造器第一行调用了第二个构造器，则执行第二个构造器主体；
	 * 		4.执行这个构造器的主体。 
	 */
	
	private static void defaultInitializationTest() {
		ClassTest classTest=new ClassTest('A');
		System.out.println("i="+classTest.i+" c="
		+classTest.c+" b="+classTest.b
		+" s="+classTest.s);//i=0 c=  b=false s=null
		if(classTest.c=='\u0000'){
			System.out.println("char类型的默认值为'\u0000'");//char类型的默认值为''
		}
		
		if(classTest.c==0){
			System.out.println("char类型的默认值为0");//char类型的默认值为0
		}
	}
	/**
	 * 在Java中，采用按值传递
	 */
	private static void callByValueTest() {
		//基本数据类型传递
		int a=1;
		int b=2;
		System.out.println("before a="+a+" b="+b);   //before a=1 b=2
		swap(a,b);
		System.out.println("after a="+a+" b="+b);  //after a=1 b=2
		
		//引用数据类型传递
		String s="OMG";
		System.out.println("before s="+s);    //before s=OMG
		update(s);
		System.out.println("after s="+s);   //after s=OMG
		
		//由上面两个例子可知，Java中的参数传递是值传递
	}
	private static void update(String s) {
		s="EDG";	
	}
	/**
	 * 用异或来交换两个整数
	 */
	private static void swap(int a, int b) {
		a=a^b;
		b=a^b;
		a=a^b;
	}
	/**
	 * NumberFormat类使用工厂方法产生不同风格的对象
	 */
	private static void NumberFormatTest() {
		//获得货币实例
		NumberFormat currencyFormatter=NumberFormat.getCurrencyInstance();
		//获得百分比实例
		NumberFormat percentFormatter=NumberFormat.getPercentInstance();
		double x=0.1;
		System.out.println(currencyFormatter.format(x));   //￥0.10
		System.out.println(percentFormatter.format(x));    //10%
	}

	/*
	 * fianl修饰引用对象时，不能修改引用地址，但是可以修改引用指向的内容。
	 */
	private static  void finalTest() {
		//fianl修饰引用对象时，不能修改引用地址，但是可以修改引用指向的内容。
		final int[] i={1,2,3,4,5};
		System.out.println(Arrays.toString(i));//[1, 2, 3, 4, 5]
		i[0]=0;
		System.out.println(Arrays.toString(i)); //[0, 2, 3, 4, 5]
		int[] i1={1,3,5};
		//i=i1;//error The final local variable i cannot be assigned. It must be blank and not using a compound assignment
		
	}

	/**
	 * 测试封装性
	 * 不要编写返回引用可变对象的访问器方法。
	 */
	@SuppressWarnings("deprecation")
	public static void encapsulationTest() {
		Employee jack=new Employee();
		jack.setHirdate(new Date());
		Date d=jack.getHirdate();
		System.out.println(jack.getHirdate().toLocaleString());  //2015-12-14 10:39:38
		long oneyear=1*365*24*60*60*1000L;
		//可以不通过setHirdate方法就可以更改hiredate的值，这样就破坏力封装性。
		d.setTime(d.getTime()-oneyear);
		System.out.println(jack.getHirdate().toLocaleString());  //2014-12-14 10:39:38
	}
}
class Employee{
	//可变实例域
	private Date hirdate;
	//返回引用可变对象
	public Date getHirdate() {
		return hirdate;
	}

	public void setHirdate(Date hirdate) {
		this.hirdate = hirdate;
	}
	
}