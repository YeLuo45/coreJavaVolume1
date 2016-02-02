package com.yeluo.mvn.ch1_ch3;

import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 * 
 * @author YeLuo
 * @function
 */
/*
 * 接口和内部类
 * 1.接口主要用来描述类具有什么功能，而并不给出每个功能的具体实现。
 * 一个类可以实现一个或多个接口，并在需要接口的地方，随时使用实现了相应接口的对象。
 * 
 * 2.对象的克隆：是指创建一个新对象，且新对象的状态与原始对象的状态相同。
 * 当克隆的新对象进行修改时，不会影响原始对象的状态。
 * 
 * 3.内部类机制：内部类定义在另一个类的内部，其中的方法可以访问包含它们的外部类的域。
 * 内部类技术主要是用于设计具有协作关系的类集合。
 * 特别是在编写GUI事件的代码时，使用它将可以让代码看起来更加简练专业。
 * 
 * 4.接口的作用：
 *		1.统一访问：接口的最主要的作用是达到统一访问，就是在创建对象的时候用接口创建，
 *【接口名】 【对象名】=new 【实现接口的类】，这样你像用哪个类的对象就可以new哪个对象了，不需要改原来的代码，
 *就和你的USB接口一样，插什么读什么，就是这个原理。就像你问的，都有个method1的方法，如果我用接口，
 *我上面就可以one.method1();是吧？那样我new a（）；就是用a的方法，new b（）就是用b的方法
 *		2.接口是一种规范（协议）。
 *统一标准的目的，是大家都知道这个是做什么的，但是具体不用知道具体怎么做。
 *		3.接口或者规范可以在开发过程中做到分离。
 *
 * 5.接口的所有方法 自动地属于public abstract
 * 接口的所有实例域默认是public static final
 * 接口不是类，尤其不能使用new运算符实例化一个接口，却可以声明接口的变量。
 * 接口变量必须引用实现了接口的类对象。
 * 可以使用instanceof来验证某个对象是否实现了某个特定的接口。
 * 
 * 6.为了让类实现一个接口，通常需要下面两个步骤：
 * 		1.将类声明为实现给定的接口       关键字：implements
 * 		2.对接口中的所有方法进行定义。
 * 
 * 7.接口和抽象类
 * 	1.语法层面上的区别
		1）抽象类可以提供成员方法的实现细节，而接口中只能存在public abstract 方法；
　　		2）抽象类中的成员变量可以是各种类型的，而接口中的成员变量只能是public static final类型的；
　　		3）接口中不能含有静态代码块以及静态方法，而抽象类可以有静态代码块和静态方法；
		4）一个类只能继承一个抽象类，而一个类却可以实现多个接口。
	2.设计层面上的区别
　　		1）抽象类是对一种事物的抽象，即对类抽象，而接口是对行为的抽象。抽象类是对整个类整体进行抽象，
	包括属性、行为，但是接口却是对类局部（行为）进行抽象。举个简单的例子，飞机和鸟是不同类的事物，
	但是它们都有一个共性，就是都会飞。那么在设计的时候，可以将飞机设计为一个类Airplane，将鸟设计为一个类Bird，
	但是不能将 飞行 这个特性也设计为类，因此它只是一个行为特性，并不是对一类事物的抽象描述。此时可以将 飞行 设计为一个接口Fly，
	包含方法fly( )，然后Airplane和Bird分别根据自己的需要实现Fly这个接口。然后至于有不同种类的飞机，比如战斗机、民用飞机
	等直接继承Airplane即可，对于鸟也是类似的，不同种类的鸟直接继承Bird类即可。从这里可以看出，继承是一个 "是不是"的关系，
	而 接口 实现则是 "有没有"的关系。如果一个类继承了某个抽象类，则子类必定是抽象类的种类，而接口实现则是有没有、具备不具备的关系，
	比如鸟是否能飞（或者是否具备飞行这个特点），能飞行则可以实现这个接口，不能飞行就不实现这个接口。
　　		2）设计层面不同，抽象类作为很多子类的父类，它是一种模板式设计。而接口是一种行为规范，它是一种辐射式设计。
	什么是模板式设计？最简单例子，大家都用过ppt里面的模板，如果用模板A设计了ppt B和ppt C，ppt B和ppt C公共的部分就是模板A了，
	如果它们的公共部分需要改动，则只需要改动模板A就可以了，不需要重新对ppt B和ppt C进行改动。而辐射式设计，
	比如某个电梯都装了某种报警器，一旦要更新报警器，就必须全部更新。也就是说对于抽象类，如果需要添加新的方法，
	可以直接在抽象类中添加具体的实现，子类可以不进行变更；而对于接口则不行，如果接口进行了变更，
	则所有实现这个接口的类都必须进行相应的改动。
	
	8.对象的克隆
	对于每个类，都需要做出下列的判断：
		1.默认的clone方法是否满足要求
		2.默认的clone方法是否能够通过调用可变子对象的clone得到修补
		3.是否不应该使用clone
	实际上，选项3是默认的。如果要选择1或2，类必须：
		1.实现Clonable接口（标记接口）
		2.使用public访问修饰符重新定义clone方法。
		
    9.标记接口没有方法，使用它的唯一目的是可以利用instanceof尽心类型检查。
           在Java 5.0以前的版本中，clone方法总是返回Object类型，而现在，返回类型允许指定正确的返回
 *	
 *	10.所有数组类型均包含一个clone方法 ，这个方法被设计为public，而不是protected。可以利用这个方法
 *创建一个包含所有数据元素拷贝的一个新数组。
 *
 *	11.接口和回调（callback）
 *		1.回调：是一种程序设计模式。在这个模式中，可以指出某个特定事件
 *		发生时应该采取的动作。
 *
 *
 */
public class InterfaceTest implements Occupation{
	public static void main(String[] args) {
		//compareTest();
		timePrinterTest();
	}
	/*
	 * 定时器
	 */
	private static void timePrinterTest() {
		ActionListener tp=new TimePrinter();
		Timer t=new Timer(10000,tp);
		t.start();
		JOptionPane.showMessageDialog(null, "Quit Program?");
		System.exit(0);
		
	}
	public String occupation() {
		//可以看出DESCRIPTION实例域是staitc的
		String description=Occupation.DESCRIPTION+"程序员";
		return description;
	}
	public void test(){
		//可以从编译器的报错看出DESCRIPTION实例域是final的
		//Occupation.RANK=100;  //error
	}
	
	/* Comparable接口：
	 * int compareTo(T o)
	 * 		比较此对象与指定对象的顺序。 
	 * Arrays类： 
	 * public static void sort(Object[] a)
	 * 		根据元素的自然顺序对指定对象数组按升序进行排序。
	 */
	private static void compareTest() {
		String s1="abcdefg";
		String s2="ABCDEFG";
		//String类实现了Comparable接口
		/*源码如下：
		 *  public int compareTo(String anotherString) {
        		int len1 = value.length;
        		int len2 = anotherString.value.length;
        		int lim = Math.min(len1, len2);
        		char v1[] = value;
        		char v2[] = anotherString.value;

        		int k = 0;
        		while (k < lim) {
            		char c1 = v1[k];
            		char c2 = v2[k];
            		if (c1 != c2) {
                		return c1 - c2;
            		}
            		k++;
        		}	
        		return len1 - len2;
    		}
		 * 
		 */
		int result=s1.compareTo(s2);
		System.out.println(result);  //32  'a'-'A'=32
		
		String[] ss={"abab","cccc","rugu","kexi","AAAA","BABA"};
		int count=0;
		Arrays.sort(ss);
		System.out.print("[");
		for(String s:ss){
			count++;
			System.out.print(s);
			if(count!=ss.length){
				System.out.print(", ");
			}		
		}
		System.out.println("]");
		//[AAAA, BABA, abab, cccc, kexi, rugu]
		System.out.println(Arrays.toString(ss));
		//[AAAA, BABA, abab, cccc, kexi, rugu]
		/*
		 * Arrays类toString方法的源码
	public static String toString(Object[] a) {
        if (a == null)
            return "null";

        int iMax = a.length - 1;
        if (iMax == -1)
            return "[]";

        StringBuilder b = new StringBuilder();
        b.append('[');
        for (int i = 0; ; i++) {
            b.append(String.valueOf(a[i]));
            if (i == iMax)
                return b.append(']').toString();
            b.append(", ");
        }
    }       
		 */
	}
}
	
