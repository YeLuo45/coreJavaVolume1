package com.yeluo.mvn.ch3;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 
 * @author YeLuo
 * @function Queue
 */
/*
 * Queue类
 * 1.在处理元素前用于保存元素的 collection。除了基本的 Collection 操作外，队列还提供其他的插入、提取和检查操作。
 * 每个方法都存在两种形式：一种抛出异常（操作失败时），另一种返回一个特殊值（null 或 false，具体取决于操作）。
 * 插入操作的后一种形式是用于专门为有容量限制的 Queue 实现设计的；在大多数实现中，插入操作不会失败。 
 * 
 * 2. 	抛出异常 		返回特殊值 
 * 插入 	add(e) 		offer(e) 
 * 移除 	remove() 	poll() 
 * 检查 	element() 	peek() 
 * 
 * 3.队列通常（但并非一定）以 FIFO（先进先出）的方式排序各个元素。不过优先级队列和 LIFO 队列（或堆栈）例外，
 * 前者根据提供的比较器或元素的自然顺序对元素进行排序，后者按 LIFO（后进先出）的方式对元素进行排序。
 * 无论使用哪种排序方式，队列的头 都是调用 remove() 或 poll() 所移除的元素。在 FIFO 队列中，所有的新元素都插入队列的末尾。
 * 其他种类的队列可能使用不同的元素放置规则。每个 Queue 实现必须指定其顺序属性。 
 * 如果可能，offer 方法可插入一个元素，否则返回 false。这与 Collection.add 方法不同，
 * 该方法只能通过抛出未经检查的异常使添加元素失败。offer 方法设计用于正常的失败情况，而不是出现异常的情况，
 * 例如在容量固定（有界）的队列中。 
 * remove() 和 poll() 方法可移除和返回队列的头。到底从队列中移除哪个元素是队列排序策略的功能，
 * 而该策略在各种实现中是不同的。remove() 和 poll() 方法仅在队列为空时其行为有所不同：remove() 方法抛出一个异常，
 * 而 poll() 方法则返回 null。 
 * element() 和 peek() 返回，但不移除，队列的头。 
 * 
 * 4.Queue 接口并未定义阻塞队列的方法，而这在并发编程中是很常见的。
 * BlockingQueue 接口定义了那些等待元素出现或等待队列中有可用空间的方法，这些方法扩展了此接口。 
 * Queue 实现通常不允许插入 null 元素，尽管某些实现（如 LinkedList）并不禁止插入 null。
 * 即使在允许 null 的实现中，也不应该将 null 插入到 Queue 中，
 * 因为 null 也用作 poll 方法的一个特殊返回值，表明队列不包含元素。 
 * 
 * 5.Queue 实现通常未定义 equals 和 hashCode 方法的基于元素的版本，而是从 Object 类继承了基于身份的版本，
 * 因为对于具有相同元素但有不同排序属性的队列而言，基于元素的相等性并非总是定义良好的。 
 * 
 * 6.此接口是 Java Collections Framework 的成员。 
 * 
 * 7.队列通常的两种实现方法:
 * 		1.循环数组--ArrayDeque
 * 		2.使用链表--LinkedList
 * 
 * ArrayDeque类
 * 1.Deque 接口的大小可变数组的实现。数组双端队列没有容量限制；它们可根据需要增加以支持使用。
 * 它们不是线程安全的；在没有外部同步时，它们不支持多个线程的并发访问。禁止 null 元素。
 * 此类很可能在用作堆栈时快于 Stack，在用作队列时快于 LinkedList。 
 * 
 * 2.大多数 ArrayDeque 操作以摊销的固定时间运行。
 * 异常包括 remove、removeFirstOccurrence、removeLastOccurrence、contains、iterator.remove() 以及批量操作，
 * 它们均以线性时间运行。 
 * 
 * 3.此类的 iterator 方法返回的迭代器是快速失败 的：如果在创建迭代器后的任意时间
 * 通过除迭代器本身的 remove 方法之外的任何其他方式修改了双端队列，则迭代器通常将抛出 ConcurrentModificationException。
 * 因此，面对并发修改，迭代器很快就会完全失败，而不是冒着在将来不确定的时刻任意发生不确定行为的风险。 
 * 注意，迭代器的快速失败行为不能得到保证，一般来说，存在不同步的并发修改时，不可能作出任何坚决的保证。
 * 快速失败迭代器尽最大努力抛出 ConcurrentModificationException。因此，编写依赖于此异常的程序是错误的，
 * 正确做法是：迭代器的快速失败行为应该仅用于检测 bug。 
 * 
 * 4.此类及其迭代器实现 Collection 和 Iterator 接口的所有可选 方法。 
 * 
 * 5.此类是 Java Collections Framework 的成员。
 * 
 * 6.小结:
 * java.util.ArrayDeque 类提供了可调整大小的阵列，并实现了Deque接口。以下是关于阵列双端队列的要点：
 * 		1.数组双端队列没有容量限制，使他们增长为必要支持使用。
 * 		2.它们不是线程安全的;如果没有外部同步。
 * 		3.不支持多线程并发访问。
 * 		4.null元素被禁止使用在数组deques。
 * 		5.它们要比堆栈Stack和LinkedList快。
 * 
 * 
 */
public class QueueTest {
	public static void main(String[] args) {
		queueTest();
	}
 
	/*
	  * 2. 	抛出异常 		返回特殊值 
	  * 插入 	add(e) 		offer(e) 
	  * 移除 	remove() 	poll() 
	  * 检查 	element() 	peek()  
	 */
	private static void queueTest() {
		Queue<String> queue=new ArrayDeque<String>();
		//remove方法和poll方法
		/*
		 * remove方法   移除并返回队列头部的元素    如果队列为空，则抛出一个NoSuchElementException异常
		 * Exception in thread "main" java.util.NoSuchElementException
		 * 	at java.util.ArrayDeque.removeFirst(Unknown Source)
		 * 	at java.util.ArrayDeque.remove(Unknown Source)
		 * 	at com.yeluo.mvn.ch1.QueueTest.queueTest(QueueTest.java:84)
		 * 	at com.yeluo.mvn.ch1.QueueTest.main(QueueTest.java:73)
		 */
		//queue.remove();
		//poll方法     移除并返问队列头部的元素    如果队列为空，则返回null
		System.out.println(queue.poll());  //null
		
		//element方法和peek方法
		/*
		 * element  返回队列头部的元素             如果队列为空，则抛出一个NoSuchElementException异常
		 * Exception in thread "main" java.util.NoSuchElementException
		 * 	at java.util.ArrayDeque.getFirst(Unknown Source)
		 * 	at java.util.ArrayDeque.element(Unknown Source)
		 * 	at com.yeluo.mvn.ch1.QueueTest.queueTest(QueueTest.java:98)
		 * 	at com.yeluo.mvn.ch1.QueueTest.main(QueueTest.java:73)
		 */
		//queue.element();
		//peek方法     返回队列头部的元素             如果队列为空，则返回null
		System.out.println(queue.peek());  //null
		
		//add方法和offer方法
		/*
		 * 由于ArrayDeque的对象的元素空间会自动增长,故测不出add方法和offer方法的效果
		 * 注意：poll和peek方法出错进返回null。因此，向队列中插入null值是不合法的。
		 */
		for(int i=0;i<16;i++){
			queue.add("Google");
		}
		System.out.println(queue);
		System.out.println(queue.size());
		queue.add("Android");
		System.out.println(queue.offer("TengXun"));
		/*
		 * Exception in thread "main" java.lang.NullPointerException
		 * 	at java.util.ArrayDeque.addLast(Unknown Source)
		 * 	at java.util.ArrayDeque.add(Unknown Source)
		 * 	at com.yeluo.mvn.ch1.QueueTest.queueTest(QueueTest.java:119)
		 * 	at com.yeluo.mvn.ch1.QueueTest.main(QueueTest.java:73)
		 */
		//queue.add(null);
		/*
		 * Exception in thread "main" java.lang.NullPointerException
		 * 	at java.util.ArrayDeque.addLast(Unknown Source)
		 * 	at java.util.ArrayDeque.offerLast(Unknown Source)
		 * 	at java.util.ArrayDeque.offer(Unknown Source)
		 * 	at com.yeluo.mvn.ch1.QueueTest.queueTest(QueueTest.java:126)
		 * 	at com.yeluo.mvn.ch1.QueueTest.main(QueueTest.java:73)
		 */
		//queue.offer(null);
	}
}
