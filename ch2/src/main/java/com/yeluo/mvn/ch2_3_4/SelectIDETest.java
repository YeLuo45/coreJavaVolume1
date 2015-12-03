package com.yeluo.mvn.ch2_3_4;
/**
 * 
 * @author YeLuo
 *
 */
/*
 * 
 */
/*
 *1.一般推荐选择Eclipse和NetBeans。
 *
 *2. 使用命令行工具，比如，编译和执行Welcome.Java文件的命令为javac Welcome.java      java Welcome
 *javac程序是一个Java编译器。它将.java文件编译成字节码文件，并发送到Java虚拟机。JVM执行class文件中的字节码文件。
 *
 *3.使用命令行需要注意一下几点：
 *1.输入源程序一定要注意大小写（尤其是类名）
 *2.编译时需要提供一个文件名（注意要加文件扩展名即.java），而运行时，只需要指定类名。
 *3.如果出现Bad command or file name或javac：command not found2这类消息，
 *就要检查一下安装是否出现了问题，特别是执行路径的设置。
 *4.如果javac报告了一个错误”cannot read：Welcome.java“，就要检查一下目录中是否存在这个文件。
 *5.如果运行程序之后，收到关于java.lang.NoClassDefFoundError的错误消息，就应该仔细地检查出问题的类名。
 *6.如果键入Java Welcome，而虚拟机没有找到Welcome类，就应该检查一下是否系统的CLASSPATH环境变量被别人修改了。
 *可以在当前的shell窗口中键入下列命令，临时取消CLASSPATH环境变量的设置(Windows下)：set CLASSPATH=
 *
 *
 *
 *
 */
public class SelectIDETest {

}
