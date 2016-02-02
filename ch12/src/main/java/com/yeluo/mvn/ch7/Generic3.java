package com.yeluo.mvn.ch7;
/**
 * 
 * @author YeLuo
 * @function
 */
/*
 * 12.7泛型类型的继承规则
 * 问题:考虑一个类和一个子类,如Employee和Manager.Generic<Manager>是Generic<Employee>的一个子类吗?
 * 回答:不是.
 * 注意:无论T和S有什么关系,通常,Generic<T>和Generic<S>没有什么联系.
 * 注释:必须注意泛型与java数组之间的重要区别.
 * 
 * 12.8通配符类型
 * 1.固定的泛型类型系统使用起来并没有那么令人愉快,所以java的设计者发明了:通配符.
 * 可以解决12.7的泛型的继承规则问题.
 * Pair<? extends Employee> 表示任何泛型Pair类型,它的类型参数是Employee的子类.
 * 引入有限定符的关键之处.现在已经有办法区分安全的访问器方法和不安全的更改器方法了.
 * 
 * 2.通配符的超类型限定
 * ? super Manager 这个通配符限制为Manager的所有超类型.
 * 直观地讲,带有超类型限定的通配符可以向泛型对象写入,带有子类型限定的通配符可以从泛型对象读取.
 * 
 * 3.无限定通配符
 * Pair<?>
 * Pair<?>和Pair本质的不同在于:可以用任意Object对象调用原始的Pair类的setObject方法.
 * 注释:可以调用setObject(null);
 * 
 * 12.9反射和泛型
 * 1.类型参数十分有用,这是因为它允许Class<T>方法的返回类型更加具有针对性.
 *  T newInstance() 
 *            创建此 Class 对象所表示的类的一个新实例。 
 * 比如:newInstance方法的返回类型被声明为T,这样就免除了类型转换.
 * 
 * 2.有时,匹配泛型方法中的Class<T>参数的类型变量很有实用价值.
 * Employee.class是类型Class<Employee>的一个对象.
 * 
 * 3.Java泛型的卓越特性之一是在虚拟机中泛型类型的擦除.
 * 令人感到奇怪的是,擦除的类仍然保留一些泛型祖先的微弱记忆.
 * 例子,看一下方法
 * public static Comparable min(Comparable[] a)
 * 这是一个泛型方法的擦除
 * public static <T extends Comparable<? super T>> T min(T[] a)
 * 可以使用反射API来确定:
 * 		1.这个泛型方法有一个叫做T的类型参数
 * 		2.这个类型参数有一个子类型限定,其自身又是一个泛型类型.
 * 		3.这个限定类型有一个通配符参数.
 * 		4.这个通配符参数有一个超类型限定.
 * 		5.这个泛型方法有一个泛型数组参数.
 * 换句话说,需要重新构造实现者的泛型类以及方法中的所有内容.但是,
 * 不会知道对于特定的对象或方法调用,如何解释类型参数.
 * 注释:包含在类文件中,可以让泛型反射可用的类型信息与旧的虚拟机不兼容.
 *
 */
public class Generic3 {

}
