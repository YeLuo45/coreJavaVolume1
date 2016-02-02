package com.yeluo.mvn.ch2;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 
 * @author YeLuo
 * @function ArrayList
 */
/**
 * ArrayList类
 * 1.List 接口的大小可变数组的实现。实现了所有可选列表操作，并允许包括 null 在内的所有元素。除了实现 List 接口外，
 * 此类还提供一些方法来操作内部用来存储列表的数组的大小。（此类大致上等同于 Vector 类，除了此类是不同步的。）
 * 
 * 2.size、isEmpty、get、set、iterator 和 listIterator 操作都以固定时间运行。add 操作以分摊的固定时间 运行，
 * 也就是说，添加 n 个元素需要 O(n) 时间。其他所有操作都以线性时间运行（大体上讲）。
 * 与用于 LinkedList 实现的常数因子相比，此实现的常数因子较低。
 * 
 * 3.每个 ArrayList 实例都有一个容量。该容量是指用来存储列表元素的数组的大小。
 * 它总是至少等于列表的大小。随着向 ArrayList 中不断添加元素，其容量也自动增长。
 * 并未指定增长策略的细节，因为这不只是添加元素会带来分摊固定时间开销那样简单。
 * 
 * 4.在添加大量元素前，应用程序可以使用 ensureCapacity 操作来增加 ArrayList 实例的容量。这可以减少递增式再分配的数量。
 * 注意，此实现不是同步的。如果多个线程同时访问一个 ArrayList 实例，而其中至少一个线程从结构上修改了列表，
 * 那么它必须 保持外部同步。（结构上的修改是指任何添加或删除一个或多个元素的操作，或者显式调整底层数组的大小；
 * 仅仅设置元素的值不是结构上的修改。）这一般通过对自然封装该列表的对象进行同步操作来完成。如果不存在这样的对象，
 * 则应该使用 Collections.synchronizedList 方法将该列表“包装”起来。这最好在创建时完成，
 * 以防止意外对列表进行不同步的访问：
 * 
 * 5.       List list = Collections.synchronizedList(new ArrayList(...)); 
 * 此类的 iterator 和 listIterator 方法返回的迭代器是快速失败的：在创建迭代器之后，
 * 除非通过迭代器自身的 remove 或 add 方法从结构上对列表进行修改，否则在任何时间以任何方式对列表进行修改，
 * 迭代器都会抛出 ConcurrentModificationException。因此，面对并发的修改，迭代器很快就会完全失败，
 * 而不是冒着在将来某个不确定时间发生任意不确定行为的风险。
 * 注意，迭代器的快速失败行为无法得到保证，因为一般来说，不可能对是否出现不同步并发修改做出任何硬性保证。
 * 快速失败迭代器会尽最大努力抛出 ConcurrentModificationException。
 * 因此，为提高这类迭代器的正确性而编写一个依赖于此异常的程序是错误的做法：迭代器的快速失败行为应该仅用于检测 bug。
 * 
 * 6.此类是 Java Collections Framework 的成员。 *
 * 
 * 7.当用set和get方法随机地访问每个元素,使用ArrayList类.而不是LinkedList类
 * 
 * 8.ArrayList封装了一个动态在分配的对象数组.
 * 
 * 9.ArrayList是线程不安全,常用在单线程环境中.
 * 考虑线程安全地话可以使用 
 * List list = Collections.synchronizedList(new ArrayList(...)); 
 * 或者是只遍历不更新可以用CopyOnWriteArrayList,而Vector已废弃(不推荐使用)
 * 
 * 
 */
public class ArrayListTest {
	public static void main(String[] args) {
		Collection<Integer> c=new ArrayList<>();
		test1();
	}

	private static void test1() {
		
		
	}
}
