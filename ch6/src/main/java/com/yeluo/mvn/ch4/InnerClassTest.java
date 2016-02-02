package com.yeluo.mvn.ch4;

import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * 
 * @author YeLuo
 * @function inner class
 */
/*内部类
 * 1.为什么使用内部类？
 * 		1.内部类方法可以访问该类定义所在的作用域中的数据，包括私有的数据
 * 		2.内部类可以对同一个包中的其它类隐藏起来
 * 		3.当想要定义一个回调函数且不想编写大量代码时，使用匿名内部类比较便捷。
 * 
 * 2.只有内部类可以是私有类，而不必提供仅用于访问其他类的访问器。
 * 而常规类只可以具有公有可见性（public）或包可见性（default）。
 * 
 * 3.内部类有一个外围类的引用outer（outer不是Java的关键字，只是用来来说明内部类中的机制）。
 * 使用外围类引用的正规语法：OuterClass.this-->表示外围类引用。
 * 
 * 4.采用
 * 	outObject.new InnerClass(construction parameters)
 * 语法格式更加明确地编写内部类对象的构造器
 * 注意：在外围类的作用域之外，可以这样引用内部类    OuterClass.InnerClass
 * 
 * 5.内部类是一种编译器现象，与虚拟机无关。编译器将会把内部类翻译成用$分隔外部类名与内部类名的常规类文件，
 * 而虚拟机则对此一无所知。
 * 例如：在TalkingClock类内部的TimePrinter类将翻译成类文件TalkingClock$TimePrinter.class。
 * 
 * 6.局部内部类：
 *	局部类不能用public或private访问说明符进行声明。它的作用域被限定在声明这个局部类的块中。
 *	局部类的优势1，即对外部世界可以完全隐藏起来。即时外部类中的其他方法也不能访问它。
 *	优势2：它们不仅能够访问包含它们的外部类，还可以访问局部变量。不过这些局部变量必须被声明为final。
 *	
 * 7.若将一个数组变量声明为final，仅仅表示不可以让它引用另外一个数组。但是数组中的数据可以自由的改变。
 * 
 * 8.匿名内部类
 * 假如只创建这个类的一个对象，就不必命名了。这种类被称为匿名内部类。
 * 通常的语法格式：
 * 	new SuperType(construction parmeters){
 * 		inner class methods and data
 *  }
 *  由于构造器的名字必须与类名相同，而匿名了没有类名，所以匿名类不能有构造器，
 *  取而代之的是，将构造器参数传递给超类构造器。尤其是在内部类实现接口的时候，
 *  不能有任何构造参数。不仅如此，还有像下面这样提供一组括号：
 *  	new InterfaceType(){
 *  		methods and data
 *  	}
 *  注意：对于匿名内部类使用时，对于equals方法要特别担心。
 *  if(getClass()!=other.getClass()) return false;
 *  这个测试对于匿名内部类做这个测试时会失败。
 *  
 *  9.生成日志或调试消息时，通常希望包含当前类的类名，如：
 *  System.err.println("Something awful happened in "+getClass()):
 *  不过这对于静态方法并没有奏效。因为静态方法没有this。
 *  应该使用以下表达式：
 *  new Object(){}.getClass().getEnclosingClass()  //get class of static method
 *  getEnclosingClass方法可以得到外围类（即包含这个静态方法的类）
 *  
 *  10.静态内部类
 *  有时候，使用内部类只是为了把一个类隐藏在另一个类的内部，并不需要内部类一弄外围类对象。
 *  为此，可以将内部类声明为static，以便取消产生的引用。
 *  注意：只有内部类才可以声明为static。
 *  注释：在内部类不需要访问外围类对象的时候，应该使用静态内部类。
 * 		声明在接口的内部类自动成为是他static和public类。
 * 
 */
public class InnerClassTest {
	public static void main(String[] args) {
		//test();
		test1();
	}

	/*
	 * 双括号初始化
	 */
	private static void test1() {
		//正常创建
		ArrayList<String> friends=new ArrayList<String>();
		friends.add("Joker");
		friends.add("UZI");		
		System.out.println(friends);  //[Joker, UZI]
		
		//利用匿名内部类创建
		ArrayList<String> friends1=new ArrayList<String>(){
			{
				add("Joker");
				add("UZI");
			}
		};
		System.out.println(friends);  //[Joker, UZI]
		
		
	}


	public static void test() {
		TalkingClock tc=new TalkingClock(1000, true);
		//在外围类之外创建TalkingClock类的内部类
		TalkingClock.TimePrinter tp=tc.new TimePrinter();
		//编译器将会把内部类翻译成用$分隔外部类名与内部类名的常规类文件
		Class clazz=tp.getClass();
		String name=clazz.getName();
		System.out.println(name);
		//com.yeluo.mvn.ch4.TalkingClock$TimePrinter
		tc.start();
		
		JOptionPane.showMessageDialog(null, "Quit program?");
		System.exit(0);
	}
}
