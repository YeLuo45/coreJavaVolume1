package com.yeluo.mvn.ch5;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Random;

/**
 * 
 * @author YeLuo
 * @function
 */
/*
 * 1.利用代理可以在运行时创建一个实现了一组给定接口的新类。这种功能只有在编译时无法确定需要实现哪个接口时才有必要使用。
 * 这对于应用程序设计人员来说，遇到这种情况的机会很少。
 * 然而对于系统程序设计人员来说，代理带来的灵活性却十分重要。
 * 
 * 2.代理类可以在运行时创建全新的类。这样的代理类能够实现指定的接口。尤其是，它具有下列方法：
 * 		1.指定接口的全部方法
 * 		2.Object类的全部方法
 * 
 * 3.要想创建一个代理对象，需要使用Proxy类的newProxyInstance方法，这个方法有3个参数
 * 		1.一个类加载器。对于系统类和从因特网上下载下来的类，可以使用不同的类加载器。为null，表示使用默认的类加载器。
 * 		2.一个Class对象数组，每个元素都是需要实现的接口。
 *		3.一个调用处理器。
 *
 * 4.还有两个需要解决的问题。如何定义一个处理器？能够用结果代理对象做些什么？
 * 		1.路由对远程服务器的方法调用
 * 		2.在程序运行期间，将用户接口事件与动作关联起来。
 * 		3.为调式，跟踪方法调用。
 * 
 * 5.需要记住，代理类是在程序运行中创建的。然而，一旦被创建，就变成了常规类，与虚拟机中的任何其他类没有什么区别。
 * 
 * 6.所有的代理类都扩展于Proxy类。一个代理类只有一个实例域——调用处理器，它定义在超类Proxy中。
 * 为了履行代理对象的职责，所需要的任何附加数据都必须存储在调用处理器中。
 * 
 * 7.对于特定的类加载器和预设的一组接口来说，只能有一个代理类。也就是说，
 * 如果使用同一个类加载器和接口数组调用两次newProxyInstance方法的话，那么只能够得到同一个类的两个对象。
 * 可以使用getProxyClass方法来获得这个类： 
 * 		Class proxyClass=Proxy.getProxyClass(null,interfaces);
 * 
 * 8.代理类一定是public和final。如果代理类实现的接口都是public，则代理类就不属于特定的包。
 * 否则，所有的非公有的接口都必须属于同一个包，同时，代理类属于这个包。
 */
public class ProxyTest {
	/*
	 * 该示例的作用：使用代理和调用处理器跟踪方法调用，并且定义了一个TraceHandler包装器类存储包装的对象。
	 */
	public static void main(String[] args) {
		Object[] elements=new Object[1000];
		Class[] interfaces=null;
		//fill elements with proxies for the integers 1...1000
		for(int i=0;i<elements.length;i++){
			Integer value=i+1;
			//创建调用处理器对象
			InvocationHandler handler=new TraceHandler(value);
			interfaces=new Class[]{Comparable.class};
			//创建代理对象
			Object proxy=Proxy.newProxyInstance(null, interfaces, handler);
			elements[i]=proxy;
		}
		//construct a random integer
		Integer key=new Random().nextInt(elements.length)+1;
		
		//search for the key
		int result=Arrays.binarySearch(elements, key);
		
		//print match if found
		if(result>=0){
			/*
			 * 无论何时调用代理对象的方法，调用处理器的invoke方法都会被调用，并向其传递Method对象和原始的调用参数。
			 * 该处调用了代理对象的toString方法，这个调用也会被重定向到滴啊用处理器上。
			 */
			System.out.println(elements[result]);
		}
		
		//Proxy类的getProxyClass方法
		Class proxyClass=Proxy.getProxyClass(null, interfaces);
		System.out.println(proxyClass);   //class com.sun.proxy.$Proxy0
	}
}
