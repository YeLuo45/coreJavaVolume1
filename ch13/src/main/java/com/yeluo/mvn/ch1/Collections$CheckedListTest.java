package com.yeluo.mvn.ch1;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;

/**
 * 
 * @author YeLuo
 * @function
 */
/**
 * 1.返回指定列表的一个动态类型安全视图。试图插入一个错误类型的元素将导致立即抛出 ClassCastException。
 * 假设在生成动态类型安全视图之前，列表不包含任何类型不正确的元素，并且所有对该列表的后续访问都通过该视图进行，
 * 则可以保证 此列表不包含类型不正确的元素。
 *  
 * 2.可以在 checkedCollection 方法的文档中找到有关使用动态类型安全视图的讨论。 
 * 
 * 3.如果指定列表是可序列化的，则返回的列表也将是可序列化的。 
 * 
 * 参数：
 * list - 将返回其动态类型安全视图的列表
 * type - 允许 list 持有的元素类型 
 * 返回：
 * 指定列表的一个动态安全类型视图
 */
public class Collections$CheckedListTest<E> 
extends Collections$CheckedCollectionTest<E> 
implements List<E> {
	private static final long serialVersionUID = 65247728283967356L;
	final List<E> list;

	Collections$CheckedListTest(List<E> list, Class<E> type) {
		super(list, type);
		this.list = list;
	}

	/**
	 * 重写了equals方法
	 */
	public boolean equals(Object o) {
		return o == this || list.equals(o);
	}

	/**
	 * 直接调用父集合的相应方法
	 */
	public int hashCode() {
		return list.hashCode();
	}

	public E get(int index) {
		return list.get(index);
	}

	public E remove(int index) {
		return list.remove(index);
	}

	public int indexOf(Object o) {
		return list.indexOf(o);
	}

	public int lastIndexOf(Object o) {
		return list.lastIndexOf(o);
	}

	/**
	 * 修改指定元素前,先检测新元素的类型是否合格
	 */
	public E set(int index, E element) {
		return list.set(index, typeCheck(element));
	}

	 /**
     * 添加元素
     * 注意:再添加元素前,先检测要添加的元素类型是否合理.
     */
	public void add(int index, E element) {
		list.add(index, typeCheck(element));
	}

	 /**
     * 批量添加元素
     * 注意:再添加元素前,先检测要添加的元素类型是否合理.
     */
	public boolean addAll(int index, Collection<? extends E> c) {
		return list.addAll(index, checkedCopyOf(c));
	}

	public ListIterator<E> listIterator() {
		return listIterator(0);
	}

	public ListIterator<E> listIterator(final int index) {
		final ListIterator<E> i = list.listIterator(index);

		return new ListIterator<E>() {
			public boolean hasNext() {
				return i.hasNext();
			}

			public E next() {
				return i.next();
			}

			public boolean hasPrevious() {
				return i.hasPrevious();
			}

			public E previous() {
				return i.previous();
			}

			public int nextIndex() {
				return i.nextIndex();
			}

			public int previousIndex() {
				return i.previousIndex();
			}

			public void remove() {
				i.remove();
			}

			/**
			 * 修改指定元素前,先检测新元素的类型是否合格
			 */
			public void set(E e) {
				i.set(typeCheck(e));
			}

			 /**
		     * 添加元素
		     * 注意:再添加元素前,先检测要添加的元素类型是否合理.
		     */
			public void add(E e) {
				i.add(typeCheck(e));
			}

			@Override
			public void forEachRemaining(Consumer<? super E> action) {
				i.forEachRemaining(action);
			}
		};
	}

	/**
	 * 返回安全的子视图
	 */
	public List<E> subList(int fromIndex, int toIndex) {
		return new Collections$CheckedListTest<>(list.subList(fromIndex, toIndex), type);
	}

	/**
	 * {@inheritDoc}
	 *
	 * @throws ClassCastException
	 *             if the class of an element returned by the operator prevents
	 *             it from being added to this collection. The exception may be
	 *             thrown after some elements of the list have already been
	 *             replaced.
	 */
	@Override
	public void replaceAll(UnaryOperator<E> operator) {
		Objects.requireNonNull(operator);
		list.replaceAll(e -> typeCheck(operator.apply(e)));
	}

	/**
	 * 按照指定比较器对象进行比较
	 */
	@Override
	public void sort(Comparator<? super E> c) {
		list.sort(c);
	}
}
