package com.yeluo.mvn.ch1;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * 
 * @author YeLuo
 * @function Collection
 */
/*
 * 在java类库中,集合类的基本接口是Collection接口 .
 * 这个接口的两个基本方法:
 * 1.add方法
 * boolean add(E e);
 * 注意:集合中不允许重复的元素,但是具体还的看实现
 * 2.iterator方法
 * Iterator<E> iterator();
 * public interface Iterator<E> {
    boolean hasNext();
    E next();
    default void remove() {
        throw new UnsupportedOperationException("remove");
    }
    default void forEachRemaining(Consumer<? super E> action) {
        Objects.requireNonNull(action);
        while (hasNext())
            action.accept(next());
    }
  }
 * 对 collection 进行迭代的迭代器。迭代器取代了 Java Collections Framework 中的 Enumeration。
 * 迭代器与枚举有两点不同： 
 * 		1.迭代器允许调用者利用定义良好的语义在迭代期间从迭代器所指向的 collection 移除元素。 
 * 		2.方法名称得到了改进。
 * Iterator接口的next和hasNext方法与Enumeration接口的nextElement和hasMoreElements方法的作用一样.
 * 注意:应该认为java的迭代器是在两个元素之间.
 * 3."for each"循环可以更加简练地表示用迭代器一样的循环操作.
 * 编译器简单地将"for each"循环翻译为带有迭代器的循环.
 * "for each"循环可以与任何实现了Iterable接口的对象一起工作.
 * Collection接口扩展了Iterable接口.因此,对于标准类库中的任何集合都可以使用"for each"循环.
 * 注意:元素被访问的顺序取决于集合类型.
 * 
 * 4.泛型使用方法
 * int size();
 * boolean isEmpty();
 * boolean contains(Object o);
 * Object[] toArray();
 * <T> T[] toArray(T[] a);
 * boolean remove(Object o); 
 * boolean containsAll(Collection<?> c); 
 * boolean addAll(Collection<? extends E> c);
 * boolean removeAll(Collection<?> c);
 * boolean retainAll(Collection<?> c);
 * void clear();
 * default Stream<E> stream() {
        return StreamSupport.stream(spliterator(), false);
    }
 *  default Stream<E> parallelStream() {
        return StreamSupport.stream(spliterator(), true);
    }
 *  default boolean removeIf(Predicate<? super E> filter) {
        Objects.requireNonNull(filter);
        boolean removed = false;
        final Iterator<E> each = iterator();
        while (each.hasNext()) {
            if (filter.test(each.next())) {
                each.remove();
                removed = true;
            }
        }
        return removed;
    }
 *
 * Java库中具体集合
 * 1.ArrayList			一种可以动态增长和缩减的索引序列
 * 2.LinkedList			一种可以在任何位置进行高效地插入和删除操作的有序序列
 * 3.ArrayDeque			一种用循环数组实现的双端队列
 * 4.HashSet			一种没有重复元素的无序集合
 * 5.TreeSet			一种有序集
 * 6.EnumSet			一种包含枚举类型值得集合
 * 7.LinkedHashSet		一种可以记住元素插入次序的集合
 * 8.PriorityQueue		一种允许高效删除最小元素的集合
 * 9.HashMap			一种存储键/值关联的数据结构
 * 10.TreeMap			一种键值有序排列的映射表
 * 11.EnumMap			一种键值属于枚举类型的映射表
 * 12.LinkedHashMap		一种可以记住键/值项添加次序的映射表
 * 13.WeakHashMap		一种其值无用武之地后可以被垃圾回收期回收的映射表
 * 14.IdentityHashMap	一种用==而不是用equal是比较键值的映射表
 */
@SuppressWarnings("unused")
public class CollectionTest {
	public static void main(String[] args) {
		addTest();
		iteratorTest();
		foreachTest();
		iteratorTest1();
		defaultTest();
		allTest();
		
	}

	/*
	 * addAll方法--并集
	 * removeAll方法--补集
	 * retainAll方法--交集
	 * containsAll方法--子集
	 */
	private static void allTest() {
		Collection<Integer> a=new ArrayList<>();
		Collection<Integer> b=new ArrayList<>();
		for(int i=1;i<11;i++){
			a.add(i);
		}
		for(int i=1;i<6;i++){
			b.add(i);
		}
		for(int i=11;i<16;i++){
			b.add(i);
		}
		System.out.println("a:"+a+" b:"+b);
		//a:[1, 2, 3, 4, 5, 6, 7, 8, 9, 10] b:[1, 2, 3, 4, 5, 11, 12, 13, 14, 15]
		
		
		
		//addAll方法
		/*a.addAll(b);
		System.out.println("a:"+a);*/
		//a:[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1, 2, 3, 4, 5, 11, 12, 13, 14, 15]
		
		//removeAll方法
		/*a.removeAll(b);
		System.out.println("a:"+a);*/
		//a:[6, 7, 8, 9, 10]
		
		//retainAll方法
		/*a.retainAll(b);
		System.out.println("a:"+a);*/
		//a:[1, 2, 3, 4, 5]
		
		Boolean result=a.containsAll(b);
		if(result){
			System.out.println("b是a的子集.");
		}else{
			System.out.println("b不是a的子集.");
		}
		//b不是a的子集.
	}

	/*
	 * Collection集合的default修饰的方法
	 */
	private static void defaultTest() {
		Collection<Integer> c=new HashSet<>();
		System.out.println(c.stream());  //java.util.stream.ReferencePipeline$Head@15db9742
		System.out.println(c.parallelStream());  //java.util.stream.ReferencePipeline$Head@6d06d69c
		System.out.println();
	}


	private static void iteratorTest1() {
		Collection<Integer> c1=new HashSet<>();
		c1.add(1);
		c1.add(2);
		Iterator<Integer> it=c1.iterator();
		/*for(int i=0;i<3;i++){
			System.out.println(it.next());
		}*/
		/* next方法到了集合末尾将会抛出NoSuchElementException异常
		 * 所以需要在调用next方法之前调用hasNext方法.
		 * 1
		 * Exception in thread "main" 2
		 * java.util.NoSuchElementException
		 * 	at java.util.HashMap$HashIterator.nextNode(Unknown Source)
		 * 	at java.util.HashMap$KeyIterator.next(Unknown Source)
		 * 	at com.yeluo.mvn.ch1.CollectionTest.iteratorTest1(CollectionTest.java:57)
		 * 	at com.yeluo.mvn.ch1.CollectionTest.main(CollectionTest.java:48)
		 */
		
		/*
		 * 测试remove方法
		 */
		/*while(it.hasNext()){
			it.remove();
			System.out.println(it.next());	
		}*/
		/* 如果尚未调用 next 方法，或者在上一次调用 next 方法之后已经调用了 remove 方法。
		 * 就会抛出IllegalStateException异常
		 * Exception in thread "main" java.lang.IllegalStateException
		 * 	at java.util.HashMap$HashIterator.remove(Unknown Source)
		 * 	at com.yeluo.mvn.ch1.CollectionTest.iteratorTest1(CollectionTest.java:71)
		 * 	at com.yeluo.mvn.ch1.CollectionTest.main(CollectionTest.java:48)
		 */
	
		/*while(it.hasNext()){
			System.out.println(it.next());
			c1.add(3);
		}*/
		/*
		 * 迭代器里,对集合的修改操作只能用迭代器对象的remove方法,
		 * 其余对象的方法将抛出ConcurrentModificationException异常
		 * Exception in thread "main" java.util.ConcurrentModificationException
		 * 	at java.util.HashMap$HashIterator.nextNode(Unknown Source)
		 * 	at java.util.HashMap$KeyIterator.next(Unknown Source)
		 * 	at com.yeluo.mvn.ch1.CollectionTest.iteratorTest1(CollectionTest.java:86)
		 * 	at com.yeluo.mvn.ch1.CollectionTest.main(CollectionTest.java:48)
		 */
		while(it.hasNext()){
			System.out.println(it.next());
			it.remove();
		}
		System.out.println(c1);  //[]
	}

	/*
	 * foreach迭代元素
	 */
	private static void foreachTest() {
		Collection<Integer> c1=new HashSet<>();
		c1.add(1);
		c1.add(2);
		foreachElement(c1);
		
		Collection<Integer> c2=new ArrayList<>();
		c2.add(1);
		c2.add(2);
		foreachElement(c2);
		
		Collection<Integer> c3=new ArrayDeque<Integer>();
		c2.add(1);
		c2.add(2);
		foreachElement(c3);
	}

	/*
	 * foreach
	 */
	public static <T> void foreachElement(Collection<T> c) {
		for(T i:c){
			System.out.println(i);
		}
	}

	/*
	 * 迭代器
	 */
	private static void iteratorTest() {
		Collection<Integer> c1=new HashSet<>();
		c1.add(1);
		c1.add(2);
		iteratorElement(c1);
		
		Collection<Integer> c2=new ArrayList<>();
		c2.add(1);
		c2.add(2);
		iteratorElement(c2);
		
		Collection<Integer> c3=new ArrayDeque<Integer>();
		c3.add(1);
		c3.add(2);
		iteratorElement(c3);
	}

	/*
	 * 迭代集合元素
	 */
	public static <T> void iteratorElement(Collection<T> c) {
		Iterator<T> it=c.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
	}

	/*
	 * Set集合不能添加重复元素
	 * List集合可添加重复元素
	 * Queue集合可添加重复元素
	 */
	private static void addTest() {
		Collection<Integer> c1=new HashSet<>();
		addDuplicateElementTest(c1,1);
		
		Collection<Integer> c2=new ArrayList<>();
		addDuplicateElementTest(c2,1);
		
		Collection<Integer> c3=new ArrayDeque<Integer>();
		addDuplicateElementTest(c3,1);
	}

	/*
	 * 检验集合是否可以添加重复元素
	 */
	public static <T> void addDuplicateElementTest(Collection<T> c,T t) {
		boolean result;
		c.add(t);
		result=c.add(t);
		if(result==true){
			System.out.println(c.getClass()+"集合能添加重复元素.");
		}else{
			System.out.println(c.getClass()+"集合不能添加重复元素.");
		}
		System.out.println("集合的元素:"+c);
	}
}
