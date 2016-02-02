package com.yeluo.mvn.ch5;
/**
 * 
 * @author YeLuo
 * @function
 */
/*
 * 枚举类
 * 1.枚举类的好处：
 * 		1.枚举作为返回类型来讲比较容易操作  
 * 		2.判断时会更高效    
 * 		3.体现编程人员的专业..这是最主要的..  
 * 		4.当然最最主要的是你写方法得出的返回值一定不会超出枚举的范围,这样避免了使用其判断时失误而带来的麻烦
 * 
 * 2.注意：
 *   1.values() 方法是编译器插入到enum 定义中的static 方法，
 *   所以，当你将enum 实例向上转型为父类Enum 是，values() 就不可访问了。
 *   解决办法：在Class 中有一个getEnumConstants() 方法，所以即便Enum 接口中没有values() 方法，
 *   我们仍然可以通过Class 对象取得所有的enum 实例
 *   2. 无法从enum 继承子类，如果需要扩展enum 中的元素，在一个接口的内部，
 *   创建实现该接口的枚举，以此将元素进行分组。达到将枚举元素进行分组。
 *   3.使用EnumSet 代替标志。enum 要求其成员都是唯一的，但是enum 中不能删除添加元素。
 *   4.EnumMap 的key 是enum ，value 是任何其他Object 对象。
 *   5.enum 允许程序员为eunm 实例编写方法。所以可以为每个enum 实例赋予各自不同的行为。
 *   6.  使用enum 的职责链(Chain of Responsibility) . 这个关系到设计模式的职责链模式。
 *   以多种不同的方法来解决一个问题。然后将他们链接在一起。当一个请求到来时，遍历这个链，
 *   直到链中的某个解决方案能够处理该请求。
 *   7.使用enum 的状态机
 *   8.使用enum 多路分发
 *   
 * 3.注意枚举类型的valueOf(String arg)方法和values方法
 * 不是来自于父类Enum的
 */
public class EnumTest {
	public static void main(String[] args) {
		printAllEnum();
		methodTest();
	}

	/*
	 *  String name() 
          	返回此枚举常量的名称，在其枚举声明中对其进行声明。 
 		int ordinal() 
          	返回枚举常量的序数（它在枚举声明中的位置，其中初始常量序数为零）。 
        static <T extends Enum<T>> T valueOf(Class<T> enumType, String name) 
          	返回带指定名称的指定枚举类型的枚举常量。 
         int compareTo(E o) 
          		比较此枚举与指定对象的顺序。 

	 */
	private static void methodTest() {
		Size s1=Size.valueOf("SMALL");
		System.out.println("枚举常量的名称:"+s1.name()
		+" 枚举常量的序数："+s1.ordinal());
		//枚举常量的名称:SMALL 枚举常量的序数：0
		Size s2=Size.valueOf(Size.class, "MEDIUM");
		System.out.println("枚举常量的名称:"+s2.name()
		+" 枚举常量的序数："+s2.ordinal());
		//枚举常量的名称:MEDIUM 枚举常量的序数：1
		
		int i12=s1.compareTo(s2);
		int i21=s2.compareTo(s1);
		System.out.println(" i12="+i12+" i21="+i21);
		// i12=-1 i21=1
	}

	/*
	 * 遍历出所有的枚举类实例
	 */
	public static void printAllEnum() {
		Size[] sizes=Size.values();
		for(Size s:sizes){
			System.out.println(s);
		}
		/*
		 *  SMALL
			MEDIUM
			LARGE
			EXTRA_LARGE
		 */
	}
}
