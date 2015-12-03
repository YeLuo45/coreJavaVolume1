package com.yeluo.mvn.ch5;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;

/**
 * 
 * @author YeLuo
 * @function 一个简单的图像查看器，它可以加载并显示一个图像。
 */
public class ImageViewerTest {
	public static void main(String[] args) {
		/*
		 * 1.public static void invokeLater(Runnable runnable)
		 * 导致 runnable 的 run 方法在 the system EventQueue 的指派线程中被调用。 
		 * 参数：runnable - Runnable 对象，其 run 方法应该在 EventQueue 上同步执行
		 * 
		 * 2.Frame的setTitle方法：为这个框架设置标题
		 * 
		 * 3.JFrame的setDefaultCloseOperation方法：设置用户在此窗体上发起 "close" 时默认执行的操作。
		 * EXIT_ON_CLOSE（在 JFrame 中定义）：使用 System exit 方法退出应用程序。仅在应用程序中使用。
		 * 
		 * 4.public void setVisible(boolean b)根据参数 b 的值显示或隐藏此 Window。
		 * b - 如为 true，则使 Window 可见，否则隐藏 Window。
		 */
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				JFrame frame=new ImageViewerFrame();
				frame.setTitle("ImageViewer");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);			
			}
		});
		
	}

}
/*
 * 一个带有标签的可显示图片的框架
 */
/*
 * 1.Java的属性要设置成为私有的的原因如下:为了实现良好的封装性,如果外面的程序可以随意修改一个类的成员变量，会造成不可预料的程序错误。如果是public类型的话，就可以在其它的类中这样student.age = -100;
	要是通过setter方法再进行赋值的话，就可以对传入的数据进行筛选
	public void setAge(int age) {
		if(0<age<150){
			this.age = age;//加入逻辑，使得数据符合我们的要求
		} 
	}	
 *
 *	
 */
class ImageViewerFrame extends JFrame{
	//属性一般都是用private来修饰的，即面向对象的封装性
	private JLabel label;    //定义一个标签属性
	private JFileChooser chooser;  //定义一个文件选择器
	//用static final 修饰的属性即是常量，且常量一般是全英文大写的
	private static final int DEFAULT_WIDTH=300;  //定义一个常量：默认宽度
	private static final int DEFAULT_HEIGHT=400; //定义一个常量：默认高度
	//如果不是单例模式，构造方法一般是public的, 它的方法名就是类名
	public ImageViewerFrame(){
		setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
		//用标签显示图片
		label=new JLabel();
		/*
		 * add方法是JFrame父类Container的方法
		 * public Component add(Component comp)将指定组件追加到此容器的尾部。
		 * 这是一个适用于 addImpl(java.awt.Component, java.lang.Object, int) 的便捷方法。 
		 * 注：如果已经将某个组件添加到显示的容器中，则必须在此容器上调用 validate，以显示新的组件。
		 * 如果添加多个组件，那么可以在添加所有组件之后，通过只调用一次 validate 来提高效率。 
		 * 
		 * 类里的方法有带static的和不带的，带static的是类方法，不带的是成员方法。
		 * 成员方法要通过这个类的一个实例对象来调用，
		 * 比如一个类classa，要调用类里的成员方法就需要先声明一个实例classa a=new classa();
		 * （这里这个带括号的东西是构造方法，创建实例）
		 * 如果是本类里面的某个方法调用其他成员方法，那么一般用this.方法名或是方法名。
		 * 如果是类方法（静态的）那么可以不通过对象调用，也可以通过对象调用。
		 */
		add(label);
		//设置文件选择器
		/*
		 * setCurrentDirectory方法
		 */
		chooser=new JFileChooser();
		chooser.setCurrentDirectory(new File("."));
		
		//设置菜单栏
		JMenuBar menuBar=new JMenuBar();
		setJMenuBar(menuBar);  //设置此窗体的菜单栏。
		
		//菜单
		JMenu menu=new JMenu("File");
		menuBar.add(menu);  //在菜单栏上添加名为“File”的菜单
		
		//菜单项--Open
		JMenuItem openItem=new JMenuItem("Open");
		menu.add(openItem);
		//匿名内部类
		openItem.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				/*
				 * 
				 * 显示文件选择的对话
				 * public int showOpenDialog(Component parent) throws HeadlessException
				 * 弹出一个 "Open File" 文件选择器对话框。
				 * 注意，approve 按钮上显示的文本由 L&F 决定。
				 */
				int result=chooser.showOpenDialog(null);
				
				//如果选择了文件，将它设为标签的图标
				if(result==JFileChooser.APPROVE_OPTION){
					/*
					 * 1.JFileChooser的getSelectedFile方法：返回所选的文件
					 * 2.File的getPath方法：
					 * public String getPath()将此抽象路径名转换为一个路径名字符串。
					 * 所得字符串使用默认名称分隔符分隔名称序列中的名称。
					 * 3.JLabel的setIcon方法：定义此组件将要显示的图标。
					 * 4.ImageIcon(String filename) ：根据指定的文件创建一个 ImageIcon。
					 */
					String name=chooser.getSelectedFile().getPath();
					label.setIcon(new ImageIcon(name));
				}
				
			}
			
		});
		//菜单项--Exit
		JMenuItem exitItem=new JMenuItem("Exit");
		menu.add(exitItem);
		exitItem.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				/*
				 * public static void exit(int status)
				 * 终止当前正在运行的 Java 虚拟机。参数用作状态码；根据惯例，非 0 的状态码表示异常终止。
				 */
				System.exit(0);
			}
		});
	}
}