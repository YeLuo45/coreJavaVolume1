package com.yeluo.mvn.ch1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * 
 * @author YeLuo
 * @function 视图
 */
/**
 * 视图和包装器
 * 1.轻量级集包装器
 * Arrays的asList方法将返回一个普通的Java数组的List包装器.
 * 注意:返回的对象不是ArrayList对象.它是一个视图对象,带有访问底层数组的get和set方法.
 * 改变数组大小的所有方法(add和remove方法)都会抛出一个UnsupportedOperationException异常.
 * 要了解Arrays$ArrayList的方法可以看Arrays$ArrayListTest里的方法
 * 
 * 2.Collections的nCopies方法将返回一个实现了list接口的不可修改的对象,并给人一种包含n个元素,
 * 每个元素都像是一个anObject的错觉.
 * 
 * 3.Collections.singleton(anObject)将返回一个视图对象.这个对象实现了Set接口 (与产生List接口的ncopies方法不同).
 * 返回的对象实现了一个不可修改的单元素集,而不需要付出建立数据结构的开销.singletonMap方法与singletonList方法类似.
 * 
 * 4.子范围(该子视图的修改会对修改父集合)
 * 可以为很多集合建立子范围视图.比如List接口的subList方法、SortedSet接口的subSet方法和SortedMap接口的subMap方法
 * 
 * 5.不可修改的视图
 * Collections的
 * Collections.unmodifiableCollection
 * Collections.unmodifiableList
 * Collections.unmodifiableSet
 * Collections.unmodifiableSortedSet
 * Collections.unmodifiableMap
 * Collections.unmodifiableSortedMap
 * 注释:不可修改的视图并不是集合本身本科修改.仍然可以通过集合的原始引用对集合进行修改,并且仍然可以让集合的元素调用更改器方法.
 * 由于视图只是包装了接口而不是实际的集合对象,所以只能访问接口中的定义的方法.
 * 注意:unmodifiableCollection方法将返回一个集合,但是它的equals方法不调用底层的集合的equals方法,
 * 只是检测两个对象是否是同一个对象.并且视图以同样的方式处理hashCode方法
 * 然而,unmodifiableList类和unmodifiableSet类却使用底层集合的equals方法和hashCode方法.
 * 
 * 6.同步视图
 * 提前:多线程访问集合
 * 类库的设计者使用视图机制来确保常规集合的线程安全,而不是实现线程安全的集合类.
 * Collections的
 * Collections.synchronizedCollection
 * Collections.synchronizedList
 * Collections.synchronizedSet
 * Collections.synchronizedSortedSet
 * Collections.synchronizedMap
 * Collections.synchronizedSortedMap
 * 
 * 7.检查视图
 * Java 5.0增加了一组"检查"视图,用来对泛型类型发生问题时提供调试支持.
 * 实际上将错误类型的元素私自带到泛型集合中的问题极有可能发生.但是这个错误在add方法运行时检测不到,
 * 相反,只有在稍后的另一部分代码中调用get方法,并将结果强转化为指定类型时,这个类才会抛出异常.
 * 而检查视图可以探测到这类问题.
 * Collections的
 * Collections.checkedCollection
 * Collections.checkedList
 * Collections.checkedSet
 * Collections.checkedSortedSet
 * Collections.checkedMap
 * Collections.checkedSortedMap
 * 警告:被检查视图受限于虚拟机可以运行的运行时检查.例如,对于ArrayList<Pair<String>>,
 * 由于虚拟机有一个单独的"原始"Pair类,所以,无法阻止插入Pair<Date>
 * 
 * 8.关于集合和迭代器接口的API文档中,许多方法描述为"可选操作"的说明
 * 通常,视图有一些局限性,即可能只可以读、无法改变大小、只支持删除而不支持插入，这些与映射表的键视图情况相同。
 * 如果视图进行不恰当的操作，受限制旳视图就会抛出UnSupportedOperationException
 * 
 */
public class ViewsTest {
	public static void main(String[] args) {
		test1();
		test2();
		test3();
		test4();
		test5();
		test7();
	}
	/**
	 *  7.检查视图
	 * Java 5.0增加了一组"检查"视图,用来对泛型类型发生问题时提供调试支持.
	 * 实际上将错误类型的元素私自带到泛型集合中的问题极有可能发生.但是这个错误在add方法运行时检测不到,
	 * 相反,只有在稍后的另一部分代码中调用get方法,并将结果强转化为指定类型时,这个类才会抛出异常.
	 * 而检查视图可以探测到这类问题.
	 * Collections的
	 * Collections.checkedCollection
	 * Collections.checkedList
	 * Collections.checkedSet
	 * Collections.checkedSortedSet
	 * Collections.checkedMap
	 * Collections.checkedSortedMap
	 * 警告:被检查视图受限于虚拟机可以运行的运行时检查.例如,对于ArrayList<Pair<String>>,
	 * 由于虚拟机有一个单独的"原始"Pair类,所以,无法阻止插入Pair<Date> 
	 */
	private static void test7() {
		ArrayList<String> strings=new ArrayList<>();
		ArrayList rawList=strings;
		//将错误类型的元素私自带到泛型集合中的问题,而且这个错误在add方法运行时检测不到
		rawList.add(new Date());
		//java.lang.ClassCastException
		//String a1=(String) rawList.get(0);
		//java.lang.ClassCastException
		//String a2=(String) strings.get(0);
		
		//第一个方法:要在集合后面加指定泛型强调,但是还是存在一个问题,如果原先已经存入非String类型,运行时还是没有报错.
		ArrayList<String> rawList1=strings;
		//he method add(String) in the type ArrayList<String> is not applicable for the arguments (Date)
		//rawList1.add(new Date());		
		//java.lang.ClassCastException
		//String a3=(String) rawList1.get(0);
		
		//第二个方法:Collections.checkedXXX方法,但是还是存在一个问题,如果原先已经存入非String类型,运行时还是没有报错.
		List rawList2=Collections.checkedList(strings, String.class);
		//java.lang.ClassCastException
		//rawList2.add(new Date());
		//java.lang.ClassCastException
		//String a4=(String) rawList2.get(0);
							
	}
	/**
	 * 5.不可修改的视图
	 * Collections的
	 * Collections.unmodifiableCollection
	 * Collections.unmodifiableList
	 * Collections.unmodifiableSet
	 * Collections.unmodifiableSortedSet
	 * Collections.unmodifiableMap
	 * Collections.unmodifiableSortedMap
	 * 注释:不可修改的视图并不是集合本身本科修改.仍然可以通过集合的原始引用对集合进行修改,并且仍然可以让集合的元素调用更改器方法.
	 * 由于视图只是包装了接口而不是实际的集合对象,所以只能访问接口中的定义的方法.
	 * 注意:unmodifiableCollection方法将返回一个集合,但是它的equals方法不调用底层的集合的equals方法,
	 * 只是检测两个对象是否是同一个对象.并且视图以同样的方式处理hashCode方法
	 * 然而,unmodifiableList类和unmodifiableSet类却使用底层集合的equals方法和hashCode方法.
	 */
	@SuppressWarnings("unused")
	private static void test5() {
		Collection<Integer> coll=new ArrayList<>();
		addElements(coll, 10);
		
		/*
		 * Collections.unmodifiableCollection
		 */
		Collection<Integer> coll1=Collections.unmodifiableCollection(coll);
		System.out.println(coll1.getClass());//class java.util.Collections$UnmodifiableCollection
		
		//该子集不能进行结构性修改,否则会抛出java.lang.UnsupportedOperationException异常
		//coll1.add(1);
		
		Iterator<Integer> ite=coll1.iterator();
		//该子集在迭代器里也不能进行结构性修改,否则会抛出java.lang.UnsupportedOperationException异常
		//ite.remove();
		
		//检测该子集的equals方法,Collections$UnmodifiableCollection类没有重写equals方法
		Collection<Integer> coll2=Collections.unmodifiableCollection(coll);
		Boolean result=coll1.equals(coll2);
		if(result==false){
			System.out.println("Collections$UnmodifiableCollection类没有重写equals方法");
		}
		//Collections$UnmodifiableCollection类没有重写equals方法
		
		/*
		 * Collections.unmodifiableList
		 */
		List<Integer> list=new ArrayList<>(coll);
		List<Integer> list1=Collections.unmodifiableList(list);
		List<Integer> list2=Collections.unmodifiableList(list);
		result=list1.equals(list2);
		if(result==false){
			System.out.println("Collections$UnmodifiableList类没有重写equals方法");
		}else{
			System.out.println("Collections$UnmodifiableList类重写equals方法");
		}
		//Collections$UnmodifiableList类重写equals方法
	}
	/**
	 * 可以为很多集合建立子范围视图.比如subList方法
	 * 该子视图的修改会对修改父集合
	 */
	private static void test4() {
		List<Integer> list=new ArrayList<Integer>();
		addElements(list,10);
		System.out.println(list);//[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
		List<Integer> list1=list.subList(4, 7);
		System.out.println(list1.getClass());//class java.util.ArrayList$SubList
		System.out.println(list1);//[4, 5, 6]
		
		ListIterator<Integer> ite=(ListIterator<Integer>) list1.iterator();
		ite.add(1);
		System.out.println(list1);//[1, 4, 5, 6]
		System.out.println(list); //[0, 1, 2, 3, 1, 4, 5, 6, 7, 8, 9]
		
		//remove方法使用的前提是得看ListIterator对象的状态,如果lastRet=-1,就会抛出java.lang.IllegalStateException异常
		//ite.remove();
		
		//set方法使用的前提是得看ListIterator对象的状态,如果lastRet=-1,就会抛出java.lang.IllegalStateException异常
		//ite.set(2);
		
		int next=ite.nextIndex();
		int previous=ite.previousIndex();
		System.out.println("迭代器的指向在"+previous+"和"+next+"之间");
		//迭代器的指向在0和1之间
	}
	/**
	 * 批量添加元素
	 * @param coll
	 * @param n
	 */
	public static void addElements(Collection<Integer> coll,int n) {
		for(int i=0;i<n;i++){
			coll.add(i);
		}
	}
	/**
	 * 3.Collections.singleton(anObject)将返回一个视图对象.这个对象实现了Set接口 (与产生List接口的ncopies方法不同).
	 * 返回的对象实现了一个不可修改的单元素集,而不需要付出建立数据结构的开销.singletonMap方法与singletonList方法类似.
	 */
	private static void test3() {
		List<String> list=Collections.singletonList("OK");
		System.out.println(list.getClass());//class java.util.Collections$SingletonList
		
		//Collections$SingletonList对象的元素个数
		int size=list.size();
		System.out.println(size);  //1
		
		Iterator<String> ite=list.iterator();
		while(ite.hasNext()){
			System.out.println(ite.next());
		}
		//OK
		
		//ite.remove();
		//操作remove方法会抛出java.lang.UnsupportedOperationException异常
	}
	/**
	 * 2.Collections的nCopies方法将返回一个实现了list接口的不可修改的对象,并给人一种包含n个元素,
	 * 每个元素都像是一个anObject的错觉.
	 * 
	 */
	private static void test2() {
		List<String> list=Collections.nCopies(10,"1");
		System.out.println(list.getClass());  //class java.util.Collections$CopiesList
		
		/*
		 * 测试toArray方法(两个)
		 */
		// java.lang.ClassCastException: [Ljava.lang.Object; cannot be cast to [Ljava.lang.String;
		//String[] s=(String[]) list.toArray();
		Object[] o=list.toArray();
		System.out.println(Arrays.toString(o));
		
		String[] s=null;
		s=list.toArray(new String[0]);
		System.out.println(Arrays.toString(s));
		
		s=list.toArray(new String[12]);
		System.out.println(Arrays.toString(s));
	}
	/**
	 * 1.轻量级集包装器
	 * Arrays的asList方法将返回一个普通的Java数组的List包装器.
	 * 注意:返回的对象不是ArrayList对象.它是一个视图对象,带有访问底层数组的get和set方法.
	 * 改变数组大小的所有方法(add和remove方法)都会抛出一个UnsupportedOperationException异常.
	 */
	private static void test1() {
		List<Integer> list=Arrays.asList(1,2,3,4,5,6,7,8,9,10);
		System.out.println(list.getClass());  //class java.util.Arrays$ArrayList
		//返回的对象不是ArrayList对象.它是一个视图对象
		
		//list.add(1);
		//抛出了 java.lang.UnsupportedOperationException
		
		//list.remove(1);
		//抛出了 java.lang.UnsupportedOperationException
	}
}
