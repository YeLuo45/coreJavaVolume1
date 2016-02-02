package com.yeluo.mvn.ch2;
/**
 * 
 * @author YeLuo
 * @function Serializable
 */
/**
 * 1.对象序列化的条件
 * 	1.该对象类必须实现Serializable接口
 * 	2.如果该类有直接或间接的不可序列化的基类,那么该基类必须有一个默认的构造器.
 *  该派生类需要负责将其基类中的数据写入流中.
 *  3.建议所有序列化类都显示声明serialVersionUID值.
 *  	1.serialVersionUID在反序列化过程中用于验证序列化对象的发送者或接受者
 *  	是否为该对象加载了与序列化兼容的类.
 *  	2.如果接受者加载的该对象的类serialVersionUID与对应的发送者的类版本号不同,
 *  	则反序列化将会导致InvalidClassException.
 *  
 *  2.transient关键字
 *  	1.transient修饰的属性不进行序列化的操作,起到一定消息屏蔽的效果.
 *  	2.被transient修饰的属性可以正确的创建,但系统赋为默认值.即int类型为0,String类型为null.
 *  
 *  3.对象序列化:将对象转换为字节序列的过程.
 *  
 *  4.反序列化:根据字节序列恢复对象的过程.
 *  
 *  5.通过使用ObjectInputStream和ObjectOutputStream类保存和读取对象的机制叫做序列化机制.
 *  注意:ObjectInputStream和ObjectOutputStream类不会保存和读取对象中的transient和static类型的成员变量.
 *  
 *  6.序列化一般用于以下的场景:
 *  	1.永久性保存对象,保存对象的字节序列到本地文件中.
 *  	2.通过序列化对象在网络中传递对象(RMI-远程方法调用-web service)
 *  	3.通过序列化在进程间传递对象.
 *  
 * 
 */
public class SerializableTest {

}
