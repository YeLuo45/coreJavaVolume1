package com.yeluo.mvn.ch5;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
/**
 * 
 * @author YeLuo
 * @function
 */
public class TraceHandler implements InvocationHandler{
	
	private Object target;
	
	public TraceHandler(Object target){
		this.target=target;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[])
	 *	调用处理器（invocation handler）
	 *	调用处理器是实现了InvocationHandler接口的类对象。
	 *	注意：无论何时调用代理对象的方法，调用处理器的invoke方法都会被调用，并向其传递Method对象和原始的调用参数。
	 *调用处理器必须给出处理调用的方式。
	 *
	 */
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		//print implicit argument   打印隐式参数
		System.out.print(target);
		//print method name         打印方法名
		System.out.print("."+method.getName()+"(");
		
		//print explicit arguments  打印显式参数
		if(args!=null){
			for(int i=0;i<args.length;i++){
				System.out.print(args[i]);
				if(i<args.length-1){
					System.out.print(", ");
				}
			}
		}
		System.out.println(")");
		
		//invoke actual method   调用实际的方法
		return method.invoke(target,args);
	}

}
