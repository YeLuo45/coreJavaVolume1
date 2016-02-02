package com.yeluo.mvn.ch2;

import java.util.Arrays;
import java.util.Date;

/**
 * 
 * @author YeLuo
 * @function Object
 */
/*
 * 1.Object类是所有类的超类。
 * 
 * 2.Java语言规范要求equals方法具有下面的特性：
 * 		1.自反性：对于任何非空引用，x.equals(x)应该返回true；
 * 		2.对称性：对于任何引用x和y，当且仅当y.equals(x)返回true，x.equals(y)也应该返回true；
 * 		3.传递性：对于任何引用x、y和z，如果x.equals(y)返回true，y.equals(z)返回true，x.equals(z)也应该返回true；
 * 		4.一致性：如果x和y引用的对象没有发生变化，反复调用x.equals(y)应该返回同样的结果；
 * 		5.对于任意非空引用x，x.equals(null)应该返回false。
 * 问题：使用instanceof进行检测类型是否匹配有时候会与对称性不符，所以尽量不要使用这种处理方式。
 * 下面可以从两个截然不同的情况看一下这个问题：
 * 		1.如果子类能够拥有自己的相等概念，则对称性需求将强制采用getClass进行检测。
 * 		2.如果有超类决定相等的概念，那么就可以使用instanceof进行检测，这样可以在不同子类的对象之间进行相等的比较。
 * 编写完美的一个equals()方法的建议
 * 		1.显示参数命名为otherObject，稍后需要将它转换成另一个叫做other的变量
 * 		2.检测this与otherObject是否引用同一个对象：
 * 			if(this == otherObject) 
    			return true;
    	 这条语句只是一个优化。实际上，这是一种经常采用的形式。因为计算这个等式要比一个一个地比较类中的域所付出的的代价要小的多。
 * 		3.检测otherObject是否为null，如果为null，返回false。这项检测是很必要的。
 * 			if(otherObject == null) 
    			return false;   	
 * 		4.比较this与otherObject是否属于同一个类。
 * 			如果equals的语义在每一个子类中有所改变，就使用getClass检测：
 * 			if(getClass() != otherObject.getClass())
    			return false;
    		 如果所有的子类都拥有统一的语义，就使用instanceof检测：
    		 if(!(otherObject instanceof ClassName))
    			return false;
 * 		5.将otherObject转换为相应的类类型变量：
 * 			ClassName oher = (ClassName) otherObject;
 * 		6.  现在开始对所有需要比较的域进行比较了。使用 == 比较基本类型域，使用equals比较对象域。
 * 		如果所有的域都匹配，就返回true，否则返回false。
 * 			return field1 == other.field1 && field2.equals(other.field2) && ...;
 * 		如果在子类中重新定义equals，就要在其中包含调用super.equals(other)。
 * 
 * 3.hashCode方法
 * 1.散列码/哈希码（hash code）是有对象导出的一个整型值。散列码是没有规律的。
 * 如果x和y是两个不同的对象，那么x.hashCode()和y.hashCode()基本上不会相同。
 * 注意：如果要重写equals方法，就必须重写hashCode方法，以便用户可以将对象插入到散列表中。
 * 
 * 4.equals和hashCode的关系：
 * 如果根据 equals(Object) 方法，两个对象是相等的，
 * 那么对这两个对象中的每个对象调用 hashCode 方法都必须生成相同的整数结果。
 * 如果根据 equals(java.lang.Object) 方法，两个对象不相等，
 * 那么对这两个对象中的任一对象上调用 hashCode 方法不 要求一定生成不同的整数结果。
 * 但是，程序员应该意识到，为不相等的对象生成不同整数结果可以提高哈希表的性能。
 * 
 * 5.随处可见toString方法的主要原因是：只要与一个字符串通过操作符“+”连接起来，
 * Java编译就会自动地调用toString方法，以便获得这个对象的字符串描述
 * 提示：强烈建议为自定义的每一个类增加toString方法。这样做不仅自己受益，
 * 而且所有使用这个类的程序员也会从这个日志记录支持中受益匪浅。
 * 
 * 6.clone方法
 * obj.clone().getClass()==obj.getClass()，即它们具有相同的类型。
 * 还有一点，因为只是简单的将对象的空间进行复制，所以如果类具有引用类型的实例变量的话，
 * 也只是将这个引用进行拷贝，并不复制其引用的对象。这就导致拷贝对象的引用实例变量与原对象的指向相同的对象，
 * 这就是传说中的“浅拷贝”。
 * 如果实例变量引用的对象是不可变的，类似于String，则拷贝对象与原对象不能互相影响，这样的拷贝是成功的。
 * 但是如果引用的是可变对象，它们就会影响彼此，对于成功的拷贝而言，这是不允许的。
 * 可以对可变的实例变量对象进行特殊处理，以实现拷贝对象和原对象不能相互影响的“深拷贝”。 
*/
public class ObjectTest {
	public static void main(String[] args) {
		/*equalsTest();
		hashCodeTest();
		toStringTest();
		getClassTest();*/
		cloneTest();
	}
	/*
	 * 还有一点，因为只是简单的将对象的空间进行复制，所以如果类具有引用类型的实例变量的话，
	 * 也只是将这个引用进行拷贝，并不复制其引用的对象。这就导致拷贝对象的引用实例变量与原对象的指向相同的对象，
	 * 这就是传说中的“浅拷贝”。
	 * 如果实例变量引用的对象是不可变的，类似于String，则拷贝对象与原对象不能互相影响，这样的拷贝是成功的。
	 * 但是如果引用的是可变对象，它们就会影响彼此，对于成功的拷贝而言，这是不允许的。
	 * 可以对可变的实例变量对象进行特殊处理，以实现拷贝对象和原对象不能相互影响的“深拷贝”。  
	 * 
	 * 注意：通过set方法修改,可以是“浅拷贝”产生“深拷贝”的效果
	 */
	private static void cloneTest() {
		//浅拷贝
		LightClone lc=new LightClone(1, 10, "OK",new StringBuilder("Hi"));
		LightClone lc1=null;
		try {
			lc1=(LightClone) lc.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		System.out.println(lc);   //com.yeluo.mvn.ch2.LightClone[ i=1,l=10,s=OK,sb=Hi ]
		System.out.println(lc1);  //com.yeluo.mvn.ch2.LightClone[ i=1,l=10,s=OK,sb=Hi ]
		//修改lc1的域值
		int ii=lc1.getI();
		ii=100;
		String s1=lc1.getS();
		s1="KO";
		StringBuilder sbb=lc1.getSb();
		sbb.append("wawawa");
		System.out.println(lc);   //com.yeluo.mvn.ch2.LightClone[ i=1,l=10,s=OK,sb=Hiwawawa ]
		System.out.println(lc1);  //com.yeluo.mvn.ch2.LightClone[ i=1,l=10,s=OK,sb=Hiwawawa ]
		
		//通过set方法修改,可以产生“深拷贝”的效果
		lc1.setI(100);
		lc1.setS("OMG");
		lc1.setSb(new StringBuilder("改变"));
		System.out.println(lc);   //com.yeluo.mvn.ch2.LightClone[ i=1,l=10,s=OK,sb=Hiwawawa ]
		System.out.println(lc1);  //com.yeluo.mvn.ch2.LightClone[ i=100,l=10,s=OMG,sb=改变 ]
		
		//深拷贝
		DeepClone dc=new DeepClone(new StringBuilder("OK"),new Date(1993, 6-1, 5));
		
		DeepClone dc1=null;
		try {
			dc1=(DeepClone) dc.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		System.out.println(dc);   //com.yeluo.mvn.ch2.DeepClone[ sb=OK,birthday=Mon Jun 05 00:00:00 CST 3893 ]
		System.out.println(dc1);  //com.yeluo.mvn.ch2.DeepClone[ sb=OK,birthday=Mon Jun 05 00:00:00 CST 3893 ]
		Date d=dc1.getBirthday();
		d.setMonth(10-1);
		System.out.println(dc);  //com.yeluo.mvn.ch2.DeepClone[ sb=OK,birthday=Mon Jun 05 00:00:00 CST 3893 ]
		System.out.println(dc1); //com.yeluo.mvn.ch2.DeepClone[ sb=OK,birthday=Thu Oct 05 00:00:00 CST 3893 ]
		
		/*
		 * 从上面可知对于浅拷贝来说，只是简单的将对象的空间进行复制，所以如果类具有引用类型的实例变量的话，
		 * 也只是将这个引用进行拷贝，并不复制其引用的对象。如果实例变量引用的对象是不可变的，类似于String，
		 * 则拷贝对象与原对象不能互相影响，这样的拷贝是成功的。但是如果引用的是可变对象，它们就会影响彼此，
		 * 对于成功的拷贝而言，这是不允许的。
		 */
	}
	/* 
	 * getClass方法是本地方法
	 * Class类的toString方法
	 * return (isInterface() ? "interface " : (isPrimitive() ? "" : "class "))
            + getName();
       getSuperclass方法也是本地方法
	 */
	private static void getClassTest() {
		String s="LOL";
		Class clazz=s.getClass();
		Class superclazz=clazz.getSuperclass();
		String name=clazz.getName();
		
		System.out.println(clazz); //class java.lang.String
		System.out.println(superclazz);//class java.lang.Object
		System.out.println(name);  //java.lang.String

				
		
	}
	/*
	 * toString方法用于返回表示对象值的字符串。
	 * Object类toStrin方法的源码： return getClass().getName() + "@" + Integer.toHexString(hashCode());
	 * 类名加上散列码
	 * 绝大数多（但是不是全部）的toString方法都遵循这样的格式：类的名字，随后是一对方括号括起来的域值。
	 * 类名可以通过调用getClass().getName()来获取。
	 */
	private static void toStringTest() {
		int[] i={1,2,3,4,5};
		System.out.println(i.toString());  //[I@2a139a55
		System.out.println(i); 			   //[I@2a139a55
		System.out.println(Arrays.toString(i));  //[1, 2, 3, 4, 5]
		//输出可以省略toString方法
		String s="Hello!";
		System.out.println(s);   //Hello!    String重写了toString方法
		
		String s1=s+i;
		System.out.println(s1);  //Hello![I@2a139a55
	}
	/*
	 * Object类的hashCode方法是本地方法。
	 */
	private static void hashCodeTest() {
		Object x=new Object();
		Object y=new Object();
		Object z=x;
		int x1=x.hashCode();
		int y1=y.hashCode();
		int z1=z.hashCode();
		System.out.println(x1+"　"+y1);  //705927765　366712642
		System.out.println(x1+"　"+z1);  //705927765　705927765
		
		String s="OK";
		StringBuilder sb=new StringBuilder(s);
		System.out.println(s.hashCode()+" "+sb.hashCode());  //2524 1829164700
		String s1=new String("OK");
		StringBuilder sb1=new StringBuilder(s1);
		System.out.println(s1.hashCode()+" "+sb1.hashCode());//2524 2018699554
		/*
		 * 为什么s和s1拥有相同的散列码，这是因为String类重写hashCode方法（字符串的散列码是由内容导出的），它的公式是
		 * s[0]*31^(n-1) + s[1]*31^(n-2) + ... + s[n-1]
		 * 而StringBuilder类没有重写hashCode方法，所以它的散列码是由Object类的默认hashCode方法导出的对象存储地址。
		 */
	}	
	/*
	 * 在Object类中equals方法仅仅是判断两个对象是否指向同一个引用
	 * 然而，经常需要检测两个对象状态的相等性，如果两个对象的状态相等，就认为这两个对象是相等。
	 * 这时就需要重写equal方法
	 * 
	 */
	private static void equalsTest() {
		int[] i1={1,2,3,4};
		int[] i2=i1;
		int[] i3={1,2,3,4};
		//调用Object的equals方法
		if(i1.equals(i2)){
			System.out.println("i1和i2两个对象指向同一个引用");
		}
		if(i2.equals(i3)){
			System.out.println("i2和i3两个对象指向同一个引用");
		}else{
			System.out.println("i2和i3两个对象不指向同一个引用");
		}
		/*
		 * 输出：
		 *  i1和i2两个对象指向同一个引用
			i2和i3两个对象不指向同一个引用
		 */
		
		//想要验证两个对象的状态是否相等，就需要调用Arrays类的equals方法
		if(Arrays.equals(i2, i3)){
			System.out.println("i2和i3两个对象的状态相等");
		}
		//输出：i2和i3两个对象的状态相等
	}
	/*
	 * Arrays类的equals方法的源代码
	 * 数组之间的比较的步骤：
	 * 1.判断两个数组是否指向同一个引用
	 * 2.判断对象是否为null
	 * 3.判断两个数组的长度是否相等
	 * 4.判断数组内容是否相等
	 */
	 public static boolean equals(int[] a, int[] a2) {
	        if (a==a2)
	            return true;
	        if (a==null || a2==null)
	            return false;

	        int length = a.length;
	        if (a2.length != length)
	            return false;

	        for (int i=0; i<length; i++)
	            if (a[i] != a2[i])
	                return false;

	        return true;
	    }
}
