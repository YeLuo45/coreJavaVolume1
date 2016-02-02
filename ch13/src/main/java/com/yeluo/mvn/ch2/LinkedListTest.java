package com.yeluo.mvn.ch2;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @author YeLuo
 * @function
 */
/**
 * 在Java语言中,所有链表实际上都是双向链接的--即每个结点还存放着指向前驱结点的引用.
 * 
 * LinkedList类
 * 1.List 接口的链接列表实现。实现所有可选的列表操作，并且允许所有元素（包括 null）。
 * 除了实现 List 接口外，LinkedList 类还为在列表的开头及结尾 get、remove 和 insert 元素提供了统一的命名方法。
 * 这些操作允许将链接列表用作堆栈、队列或双端队列。
 * 
 * 2.此类实现 Deque 接口，为 add、poll 提供先进先出队列操作，以及其他堆栈和双端队列操作。

 * 3.所有操作都是按照双重链接列表的需要执行的。在列表中编索引的操作将从开头或结尾遍历列表（从靠近指定索引的一端）。
 * 注意，此实现不是同步的。如果多个线程同时访问一个链接列表，而其中至少一个线程从结构上修改了该列表，
 * 则它必须 保持外部同步。（结构修改指添加或删除一个或多个元素的任何操作；仅设置元素的值不是结构修改。）
 * 这一般通过对自然封装该列表的对象进行同步操作来完成。如果不存在这样的对象，则应该使用 Collections.synchronizedList 方法来“包装”该列表。
 * 最好在创建时完成这一操作，以防止对列表进行意外的不同步访问，如下所示： 
 *    List list = Collections.synchronizedList(new LinkedList(...));
 *    此类的 iterator 和 listIterator 方法返回的迭代器是快速失败 的：
 *    在迭代器创建之后，如果从结构上对列表进行修改，除非通过迭代器自身的 remove 或 add 方法，
 *    其他任何时间任何方式的修改，迭代器都将抛出 ConcurrentModificationException。
 *    因此，面对并发的修改，迭代器很快就会完全失败，而不冒将来不确定的时间任意发生不确定行为的风险。 
 * 注意，迭代器的快速失败行为不能得到保证，一般来说，存在不同步的并发修改时，不可能作出任何硬性保证。快速失败迭代器尽最大努力抛出 ConcurrentModificationException。因此，编写依赖于此异常的程序的方式是错误的，正确做法是：迭代器的快速失败行为应该仅用于检测程序错误。 
 * 
 * 4.此类是 Java Collections Framework 的成员。 
 * 
 * 5.链表是一个有序集合,每个对象的位置十分重要.LinkedList.add方法将对象添加在链表的尾部.
 * 但是,常常需要将元素添加到链表的中间.由于迭代器是描述集合中位置的,
 * 所以这种依赖于位置的add方法由迭代器负责.用Iterator的子接口ListIterator接口的add方法
 * 只有对自然有序的集合使用迭代器添加元素才有意义.
 * 注意:add方法只依赖于迭代器的位置,而remove方法依赖于迭代器的状态.
 * 
 * 6.链表不支持快速地随机访问.如果要查看链表中第n个元素,就必须从头开始,越过n-1个元素,没有捷径可走.
 * 因此,当程序需要采用整数索引访问元素时,程序员通常不采用链表.
 * 虽然LinkedList类中提供了一个用来访问某个特定元素的get方法,但是不建议使用(效率太低).
 * 该方法每次遍历一个元素,都要从列表的头部重新开始搜索,LinkedList对象根本不做任何缓存.
 */
/**
 * public class LinkedList<E>
 *     extends AbstractSequentialList<E>
 *     implements List<E>, Deque<E>, Cloneable, java.io.Serializable
 * LinkedList 是一个继承于AbstractSequentialList的双向链表。它也可以被当作堆栈、队列或双端队列进行操作。
 * LinkedList 实现 List 接口，能对它进行队列操作。
 * LinkedList 实现 Deque 接口，即能将LinkedList当作双端队列使用。
 * LinkedList 实现了Cloneable接口，即覆盖了函数clone()，能克隆。
 * LinkedList 实现java.io.Serializable接口，这意味着LinkedList支持序列化，能通过序列化去传输。
 * LinkedList 是非同步的。
 * 
 * 
 * 
 */
public class LinkedListTest {
	public static void main(String[] args) {
		/*test();
		test1();
		test2();
		test3();*/
		test4();
	}
	/**
	 * LinkedList类的克隆方法	 
	 * 浅度克隆方法-----clone方法
	 */
	private static void test4() { 
		LinkedList<StringBuffer> linked=new LinkedList<>();
		
		StringBuffer sb=new StringBuffer("0");
		linked.add(sb);
		for(int i=1;i<10;i++){
			linked.add(new StringBuffer(""+i));
		}
		System.out.println(linked);
		
		LinkedList<String> linked1=(LinkedList<String>) linked.clone();
		System.out.println(linked1);
		
		sb.append("Change");
		System.out.println(linked);//[0Change, 1, 2, 3, 4, 5, 6, 7, 8, 9]
		System.out.println(linked1);//[0Change, 1, 2, 3, 4, 5, 6, 7, 8, 9]
		
		//有上述可知,当原对象的元素改变时,克隆的对象也发生改变,故LinkedList类的clone方法为浅克隆
	}
	/**
	 * 堆/栈:后进先出
	 * 在栈顶添加元素-----push方法
	 * 移除栈顶的元素-----pop方法
	 */
	private static void test3() {
		LinkedList<Integer> linked=new LinkedList<>();
		//在栈顶添加元素-----push方法
		linked.push(1);
		linked.push(2);
		linked.push(3);
		linked.push(4);
		System.out.println(linked);//[4, 3, 2, 1]
		
		//移除栈顶的元素-----pop方法
		linked.pop();
		System.out.println(linked);//[3, 2, 1]
	}
	/**
	 * 双端队列:可以让人们有效地在头部或尾部同时添加或删除元素,但是不支持在队列中间添加元素
	 * 
	 * 添加成功返回true,否则返回false
	 * 在开头添加元素-----offerFirst方法
	 * 在结尾添加元素-----offerLast方法
	 * 
	 * 在双端队列没有元素时,以下的4个方法回返回null
	 * 返回开头的元素-----peekFirst方法
	 * 返回结尾的元素-----peekLast方法
	 * 移除开头的元素-----pollFirst方法
	 * 移除结尾的元素-----pollLast方法
	 */
	private static void test2() {
		LinkedList<Integer> linked=new LinkedList<>();
		
		//在开头添加元素-----offerFirst方法
		linked.offerFirst(1);
		System.out.println(linked.offerFirst(2)); //true
		System.out.println(linked);//[2, 1]
		
		//移除开头的元素-----pollFirst方法
		linked.pollFirst();
		System.out.println(linked);//[1]
		
		//在结尾添加元素-----offerLast方法
		linked.offerLast(2);
		System.out.println(linked); //[1, 2]
		
		//移除结尾的元素-----pollLast方法
		linked.pollLast();
		System.out.println(linked); //[1]
		
		//返回开头的元素-----peekFirst方法
		System.out.println(linked.peekFirst());//1
		
		//返回结尾的元素-----peekLast方法
		System.out.println(linked.peekLast()); //1
		
		
		linked.pollFirst();
		//在双端队列没有元素的情况下
		//peekFirst方法
		System.out.println("在双端队列没有元素的情况下,peekFirst方法的返回结果:"+linked.peekFirst());
		//在双端队列没有元素的情况下,peekFirst方法的返回结果:null
		//peekLast方法
		System.out.println("在双端队列没有元素的情况下,peekLast方法的返回结果:"+linked.peekLast());
		//在双端队列没有元素的情况下,peekLast方法的返回结果:null
		
		//pollFirst方法
		System.out.println("在双端队列没有元素的情况下,pollFirst方法的返回结果:"+linked.pollFirst());
		//在双端队列没有元素的情况下,pollFirst方法的返回结果:null
		//pollLast方法
		System.out.println("在双端队列没有元素的情况下,pollLast方法的返回结果:"+linked.pollLast());
		//在双端队列没有元素的情况下,pollLast方法的返回结果:null
	}
	/**
	 * 队列:先进先出
	 * 返回第一个元素的方法-----peek方法、element方法
	 * 移除第一个元素的方法-----poll方法、remove方法
	 * 在末尾添加元素的方法-----offer方法
	 * 测试在没有元素的队列里peek方法和element方法的区别,以及poll方法和remove方法的区别
	 */
	private static void test1() {
		LinkedList<Integer> linked=new LinkedList<>();
		//在末尾添加元素的方法-----offer方法
		linked.offer(1);
		linked.offer(2);
		linked.offer(3);
		System.out.println(linked); //[1, 2, 3]
		
		//移除第一个元素的方法-----poll方法、remove方法
		linked.remove();
		linked.poll();
		linked.poll();
		System.out.println(linked);  //[]
		
		//在队列没有元素时,poll方法和remove方法的区别
		System.out.println("在队列没有元素时,poll方法的返回结果:"+linked.poll());
		//在队列没有元素时,poll方法的返回结果:null
		//System.out.println("在队列没有元素时,remove方法的返回结果:"+linked.remove());
		//抛出了java.util.NoSuchElementException异常
		
		
		//在队列没有元素时,peek方法、element方法的区别
		System.out.println("在队列没有元素时,peek方法的返回结果:"+linked.peek());
		//在队列没有元素时,peek方法的返回结果:null
		//System.out.println("在队列没有元素时,element方法的返回结果:"+linked.element());
		//抛出了java.util.NoSuchElementException异常
	}

	/**
	 * 测试LinkedList对象和ArrayList对象访问某个特定元素的效率的区别
	 */
	@SuppressWarnings("unused")
	private static void test() {
		List<Integer> linked=new LinkedList<>();
		int n=10000;
		addElements(linked, n);
		
		List<Integer> array=new ArrayList<>();
		addElements(array, n);
		
		//测试LinkedList对象访问某个特定元素的效率
		Date before;	
		Date after;
		long count;
		testGetEfficiency(linked);
		//用get方法遍历所有元素所花的时间:168
		
		////测试LinkedList对象访问某个特定元素的效率
		testGetEfficiency(array);
		//用get方法遍历所有元素所花的时间:53
		
		//综上可知,s链表不适合用get方法访问某个特定的元素.
	}

	/**
	 * 测试List对象用get方法遍历所有元素
	 * @param list
	 */
	public static void testGetEfficiency(List<Integer> list) {
		Date before=new Date();
		System.out.print("[");
		for(int i=0;i<list.size();i++){
			System.out.print(list.get(i));
			if(i!=list.size()-1){
				System.out.print(" ,");
			}
		}
		System.out.println("]");
		Date after=new Date();
		long count=after.getTime()-before.getTime();
		System.out.println("用get方法遍历所有元素所花的时间:"+count);
	}

	/**
	 * 批量添加元素
	 * @param linked
	 * @param n
	 */
	public static  void addElements(List<Integer> linked, int n) {
		for(int i=0;i<n;i++){
			linked.add(i);
		}
	}
}
