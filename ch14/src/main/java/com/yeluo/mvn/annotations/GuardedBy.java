package com.yeluo.mvn.annotations;
/**
 * 
 * @author YeLuo
 * @function
 */
/**
 * @GuardedBy(lock)线程只有在持有了一个特定的锁后,才能访问某个域或方法.
 * lock参数代表一个锁,只有持有了该锁,才能访问被标注的域或方法.
 * lock的可能值如下:
 * 		1.this:是指包含在对象中的内部锁(方法或域是这个对象的一个成员)
 * 		2.fieldName:是指与fieldName引用的对象相关联的锁,
 * 		它或者是一个隐式锁(fieldName没有引用一个lock),
 * 		或者是一个显式锁(fieldName引用了一个Lock);
 * 		3.ClassName.fieldName:类似于fieldName,
 * 		不过所引用的锁对象是存储在另一个类中的静态域;
 * 		4.methodName():是指锁对象是methodName()方法的返回值;
 * 		5.ClassName.class:是指ClassName类的直接量对象.
 * 
 * 使用@GuardedBy标识出每一个需要加锁的状态变量,这个锁确保它会有助于维护和代码审查,
 * 而且能够帮助自动化分析工具发现潜在的线程安全性错误.
 */
public @interface GuardedBy {

	String value();

}
