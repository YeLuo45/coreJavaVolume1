package com.yeluo.mvn.ch1_ch5;
/**
 * 
 * @author YeLuo
 * @function 简单的泛型类
 */
/*
 * 1.SimpleGenericClass类引入了一个类型变量T,用尖括号(< >)括起来,并放在类名的后面.
 * 泛型类可以有多个类型变量.例如,public class Test<K,V>{...}
 * 2.类定义中的类型变量指定方法的返回类型以及域和局部变量的类型.例如,private T first;
 * 3.用具体的类型替换类型变量就可以实例化泛型类型,例如  Test<String>
 * 注释:泛型类可看作普通类的工厂.
 *
 */
public class SimpleGenericClass<T> {
	private T first;
	private T second;
	public SimpleGenericClass(){
		first=null;
		second=null;
	}
	public SimpleGenericClass(T first,T second){
		this.first=first;
		this.second=second;
	}
	public T getFirst() {
		return first;
	}
	public void setFirst(T first) {
		this.first = first;
	}
	public T getSecond() {
		return second;
	}
	public void setSecond(T second) {
		this.second = second;
	}
	
}
