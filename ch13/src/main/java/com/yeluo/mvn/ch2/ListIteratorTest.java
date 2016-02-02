package com.yeluo.mvn.ch2;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * 
 * @author YeLuo
 * @function ListIterator
 */
/**
 * 系列表迭代器，允许程序员按任一方向遍历(既可以正向遍历也可以反向遍历)列表、迭代期间可以修改列表，
 * 并获得迭代器在列表中的当前位置。ListIterator 没有当前元素；
 * 它的光标位置 始终位于调用 previous() 所返回的元素和调用 next() 
 * 所返回的元素之间。长度为 n 的列表的迭代器有 n+1 个可能的指针位置，如下面的插入符举例说明： 
 *                       Element(0)   Element(1)   Element(2)   ... Element(n-1)
 *  cursor positions:  ^            ^            ^            ^                  ^
 *  注意，remove() 和 set(Object) 方法不是 根据光标位置定义的；
 *  它们是根据对调用 next() 或 previous() 所返回的最后一个元素的操作定义的。 
 * 此接口是 Java Collections Framework 的成员。
 */
/**
 * 该接口方法摘要 
 *  void add(E e) 
 *           将指定的元素插入列表（可选操作）
 * 		              它是在迭代器的当前位置之前加入元素并且添加元素之后光标会往后移动.
 * 如果链表有n个元素,则有n+1个位置可以添加新元素.
 *  boolean hasNext() 
 *           以正向遍历列表时，如果列表迭代器有多个元素，则返回 true
 *           （换句话说，如果 next 返回一个元素而不是抛出异常，则返回 true）。 
 *  boolean hasPrevious() 
 *           如果以逆向遍历列表，列表迭代器有多个元素，则返回 true。 
 *  E next() 
 *         返回列表中的下一个元素。 
 *  int nextIndex() 
 *          返回对 next 的后续调用所返回元素的索引。 
 *  E previous() 
 *           返回列表中的前一个元素。 
 *  int previousIndex() 
 *           返回对 previous 的后续调用所返回元素的索引。 
 *  void remove() 
 *          从列表中移除由 next 或 previous 返回的最后一个元素（可选操作）。 
 *  void set(E e) 
 *           用指定元素替换 next 或 previous 返回的最后一个元素（可选操作）。 
 *           
 * 注意:add方法只依赖迭代器的位置(next和previous各返回什么元素)，
 * 而remove方法和set方法依赖于迭代器的状态(是否执行了next或previous方法)
 */
public class ListIteratorTest {
	public static void main(String[] args) {
		/*test();
		test1();*/
		test2();
		test3();
	}
	/**
	 * 测试List接口的listIterator()和listIterator(int index)的区别 
	 */
	private static void test3() {
		int n=6;
		List<Integer> list = createLinkedList(n);
		ListIterator<Integer> iter=list.listIterator();
		deteminePosition(iter);
		//迭代器光标的位置在-1和0之间.
		
		iter=list.listIterator(2);
		deteminePosition(iter);
		//迭代器光标的位置在1和2之间.
		
	}
	/**
	 * 在结构上修改列表的方法
	 * 		add(E e)方法
	 * 		remove()方法
	 * 不是结构性修改的方法
	 * 		set(E e)方法
	 * 注意:add方法只依赖迭代器的位置(next和previous各返回什么元素)，
	 * 而remove方法和set方法依赖于迭代器的状态(是否执行了next或previous方法)
	 */
	private static void test2() {
		int n=6;
		List<Integer> list = createLinkedList(n);
		ListIterator<Integer> iter=list.listIterator();
		
		
		//不是结构性修改的方法
		deteminePosition(iter);
		int beforeI=iter.next();
		iter.set(110);
		int afterI=iter.previous();
		System.out.println("修改前的值:"+beforeI+"修改后的值:"+afterI);
		//修改前的值:1修改后的值:110
		deteminePosition(iter);
		System.out.println(list);
		//[110, 2, 3, 4, 5]
		
		
		//结构性修改的方法
		int nowV=iter.next();
		//移除nowV
		iter.remove();
		System.out.println(list);
		//[2, 3, 4, 5]
		
		//添加元素
		deteminePosition(iter);
		//迭代器光标的位置在-1和0之间.
		iter.add(1);
		System.out.println(list);
		//[1, 2, 3, 4, 5]
		deteminePosition(iter);
		//迭代器光标的位置在0和1之间.
		
		iter.add(0);
		deteminePosition(iter);
		//迭代器光标的位置在1和2之间.
		
	}
	/**
	 * 创建list对象,并批量添加元素
	 * @param n
	 * @return
	 */
	public static List<Integer> createLinkedList(int n) {
		List<Integer> list=new LinkedList<>();
		for(int i=1;i<n;i++){
			list.add(i);
		}
		return list;
	}

	/**
	 * 确定当前光标的位置
	 * nextIndex()方法
	 * previousIndex()方法
	 */
	private static void test1() {
		int n=6;
		List<Integer> list = createLinkedList(n);
		ListIterator<Integer> iter=list.listIterator();
		deteminePosition(iter);
		
	}
	/**
	 * 确定当前光标的位置
	 * @param iter
	 */
	public static void deteminePosition(ListIterator<Integer> iter) {
		int nextI=iter.nextIndex();
		int previousI=iter.previousIndex();
		System.out.println("nextI:"+nextI);
		System.out.println("previousI:"+previousI);
		System.out.println("迭代器光标的位置在"+previousI+"和"+nextI+"之间.");
	}

	/**
	 * 正向遍历的方法组合:
	 * 		hasNext()方法
	 * 		next()方法 
	 * 反向遍历的方法组合:
	 * 		hasPrevious()方法
	 * 		previous()方法
	 */
	private static void test() {
		int n=6;
		List<Integer> list = createLinkedList(n);
		ListIterator<Integer> iter=list.listIterator();
		
		System.out.println(list); 
		//[1, 2, 3, 4, 5]
		
		//用来计数
		int count=1;
		//正向遍历
		System.out.print("[");
		while(iter.hasNext()){
			System.out.print(iter.next());
			if(count!=list.size()){
				System.out.print(", ");			
			}
			count++;
		}
		System.out.println("]");
		//[1, 2, 3, 4, 5]
		
		//反向遍历
		count=list.size();
		System.out.print("[");
		while(iter.hasPrevious()){
			System.out.print(iter.previous());
			if(count!=1){
				System.out.print(", ");			
			}
			count--;
		}
		System.out.println("]");
		//[5, 4, 3, 2, 1]
	}
}
