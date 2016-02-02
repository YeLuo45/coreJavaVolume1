package com.yeluo.mvn.ch3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
 * @author YeLuo
 * @function
 */
/*
 * 1.List 接口的大小可变数组的实现。实现了所有可选列表操作，并允许包括 null 在内的所有元素。除了实现 List 接口外，
 * 此类还提供一些方法来操作内部用来存储列表的数组的大小。（此类大致上等同于 Vector 类，除了此类是不同步的。）
 * 
 * 2.每个 ArrayList 实例都有一个容量。该容量是指用来存储列表元素的数组的大小。它总是至少等于列表的大小。
 * 随着向 ArrayList 中不断添加元素，其容量也自动增长。并未指定增长策略的细节，因为这不只是添加元素会带来分摊固定时间开销那样简单。
 *
 * 3.在添加大量元素前，应用程序可以使用 ensureCapacity 操作来增加 ArrayList 实例的容量。这可以减少递增式再分配的数量
 * 
 * 4.注意，此实现不是同步的。如果多个线程同时访问一个 ArrayList 实例，而其中至少一个线程从结构上修改了列表，
 * 那么它必须 保持外部同步.这一般通过对自然封装该列表的对象进行同步操作来完成。如果不存在这样的对象，
 * 则应该使用 Collections.synchronizedList 方法将该列表“包装”起来。这最好在创建时完成，以防止意外对列表进行不同步的访问：
 * 		List list = Collections.synchronizedList(new ArrayList(...));
 * 
 *  5.此类的 iterator 和 listIterator 方法返回的迭代器是快速失败的：在创建迭代器之后，
 *  除非通过迭代器自身的 remove 或 add 方法从结构上对列表进行修改，否则在任何时间以任何方式对列表进行修改，
 *  迭代器都会抛出 ConcurrentModificationException。因此，面对并发的修改，迭代器很快就会完全失败，
 *  而不是冒着在将来某个不确定时间发生任意不确定行为的风险。
 *  注意，迭代器的快速失败行为无法得到保证，因为一般来说，不可能对是否出现不同步并发修改做出任何硬性保证。
 *  快速失败迭代器会尽最大努力抛出 ConcurrentModificationException。
 *  因此，为提高这类迭代器的正确性而编写一个依赖于此异常的程序是错误的做法：迭代器的快速失败行为应该仅用于检测 bug。
 * 
 *	6.注意 capacity(容量)和size(长度)的区别
 *	capacity是指该数组列表只是拥有保存该容量个数的元素的潜力。
 *	size是指该数组列表当前的元素个数。
 */
public class ArrayListTest {
	public static void main(String[] args) {
		constructorTest();
		sizeTest();
		methodTest();
		sizeTest();
	}
	/*
	 * 注意:形参index 的范围是0~size()-1之间
	 *  boolean add(E e) 
          	将指定的元素添加到此列表的尾部。 
 		void add(int index, E element) 
          	将指定的元素插入此列表中的指定位置。 
 		boolean addAll(Collection<? extends E> c) 
          	按照指定 collection 的迭代器所返回的元素顺序，将该 collection 中的所有元素添加到此列表的尾部。 
 		boolean addAll(int index, Collection<? extends E> c) 
          	从指定的位置开始，将指定 collection 中的所有元素插入到此列表中。 

	 *
	 *	 E remove(int index) 
          	移除此列表中指定位置上的元素。 
 		boolean remove(Object o) 
          	移除此列表中首次出现的指定元素（如果存在）。 
		protected  void removeRange(int fromIndex, int toIndex) 
          	移除列表中索引在 fromIndex（包括）和 toIndex（不包括）之间的所有元素。 

	 *	 void clear() 
          	移除此列表中的所有元素。 
	 *
	 *	boolean contains(Object o) 
          	如果此列表中包含指定的元素，则返回 true。 
	 *
	 *	 E get(int index) 
          	返回此列表中指定位置上的元素。 
 		int indexOf(Object o) 
          	返回此列表中首次出现的指定元素的索引，或如果此列表不包含元素，则返回 -1。 
 		boolean isEmpty() 
          	如果此列表中没有元素，则返回 true 
 		int lastIndexOf(Object o) 
          	返回此列表中最后一次出现的指定元素的索引，或如果此列表不包含索引，则返回 -1。 

	 */
	private static void methodTest() {
		List<String> list=new ArrayList<>();
		list.add("1");
		list.add("2");
		System.out.println(list);  //[1, 2]
		//在指定位置添加  注意：形参index 的范围是0~size()-1之间
		list.add(0, "3");
		System.out.println(list);  //[3, 1, 2]
		//在指定位置修改值      set方法是ArrayList父类AbstractList类的方法
		/*AbstractList
		 *  E set(int index, E element) 
          		用指定元素替换列表中指定位置的元素（可选操作）。 
		 */
		list.set(0, "0");
		System.out.println(list);  //[0, 1, 2]
		
		List<String> list1=new ArrayList<>(list);
		list.addAll(list1);
		System.out.println(list);  //[0, 1, 2, 0, 1, 2]
		//remove方法
		list.remove(4);
		list.remove(3);
		System.out.println(list);  //[0, 1, 2, 2]
		//移除此列表中首次出现的指定元素（如果存在）。 
		list.remove("2");
		System.out.println(list);//[0, 1, 2]
		//removeRange(int fromIndex, int toIndex) 
		//((ArrayList<String>) list1).removeRange(0,1);   //在不同包不同类中，该方法不可见  
		
		//contains方法
		boolean result=list.contains("4");
		System.out.println(result);   //false
		//clear方法
		list1.clear();
		//isEmpty方法
		result=list1.isEmpty();
		System.out.println(result);  //true
		
		list.add("2");
		System.out.println(list);//[0, 1, 2, 2]
		
		//indexOf方法和lastIndexOf方法
		int index=list.indexOf("2");
		System.out.println(index);   //2
		index=list.lastIndexOf("2");
		System.out.println(index);   //3
		
		 //get(int index)
		String s=list.get(2);
		System.out.println(s);    //2
		s=list.get(3);
		System.out.println(s);     //2
	}
	/*
	 * int size() 
          	返回此列表中的实际元素数。 
	 */
	private static void sizeTest() {
		List<String> list=new ArrayList<>();
		list.add("1");
		list.add("2");
		System.out.println(list);  //[1, 2]
		int size=list.size();
		System.out.println(size); //2
		
		
	}
	/*
	 * ArrayList的构造器：
	 * 		1.ArrayList()   构造一个初始容量为 10 的空列表。 
			2.ArrayList(Collection<? extends E> c) 
          		构造一个包含指定 collection 的元素的列表，这些元素是按照该 collection 的迭代器返回它们的顺序排列的。 
			3.ArrayList(int initialCapacity) 构造一个具有指定初始容量的空列表。 
	 */
	private static void constructorTest() {
		//ArrayList()  默认容量
		List<String> list=new ArrayList<>();
		//ArrayList(int initialCapacity)   指定初始容量 并且进行同步操作
		List<String> list1=Collections.synchronizedList(new ArrayList<String>(20));
		//添加
		list.add("one");
		list.add("two");
		list.add("three");
		list.add("four");
		list.add("five");
		
		//ArrayList(Collection<? extends E> c) 
		//构造一个包含指定 collection 的元素的列表，这些元素是按照该 collection 的迭代器返回它们的顺序排列的。 
		List<String> list2=new ArrayList<>(list);
		
		System.out.println(list);   //[one, two, three, four, five]
		System.out.println(list2);  //[one, two, three, four, five]
	}
}
