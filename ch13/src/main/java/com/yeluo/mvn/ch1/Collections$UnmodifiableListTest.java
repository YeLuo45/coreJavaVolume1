package com.yeluo.mvn.ch1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;
/**
 * 
 * @author YeLuo
 * @function 对Collections$UnmodifiableList源码解析
 * @param <E>
 */
public class Collections$UnmodifiableListTest<E> 
extends Collections$UnmodifiableCollectionTest<E> 
implements List<E> {
	

	private static final long serialVersionUID = -283967356065247728L;

	final List<? extends E> list;

	/**
	 * 传入的集合不能为null
	 * @param list
	 */
	Collections$UnmodifiableListTest(List<? extends E> list) {
		super(list);
		this.list = list;
	}

	/**
	 * 重写了equals方法
	 */
	public boolean equals(Object o) {
		return o == this || list.equals(o);
	}

	public int hashCode() {
		return list.hashCode();
	}

	public E get(int index) {
		return list.get(index);
	}

	/**
	 * 不能对该子集进行非结构性修改和结构性修改
	 * set方法、add方法、remove方法
	 */
	public E set(int index, E element) {
		throw new UnsupportedOperationException();
	}

	public void add(int index, E element) {
		throw new UnsupportedOperationException();
	}

	public E remove(int index) {
		throw new UnsupportedOperationException();
	}

	public int indexOf(Object o) {
		return list.indexOf(o);
	}

	public int lastIndexOf(Object o) {
		return list.lastIndexOf(o);
	}

	/**
	 * 不能对该子集进行非结构性修改和结构性修改
	 * addAll方法、replaceAll方法、sort方法
	 */
	public boolean addAll(int index, Collection<? extends E> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void replaceAll(UnaryOperator<E> operator) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void sort(Comparator<? super E> c) {
		throw new UnsupportedOperationException();
	}

	public ListIterator<E> listIterator() {
		return listIterator(0);
	}

	public ListIterator<E> listIterator(final int index) {
		return new ListIterator<E>() {
			private final ListIterator<? extends E> i = list.listIterator(index);

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

			/**
             * 不能在迭代器里进行结构性修改--移除
             */
			public void remove() {
				throw new UnsupportedOperationException();
			}
			/**
             * 不能在迭代器里进行非结构性修改--设置
             */
			public void set(E e) {
				throw new UnsupportedOperationException();
			}

			/**
             * 不能在迭代器里进行结构性修改--添加
             */
			public void add(E e) {
				throw new UnsupportedOperationException();
			}

			@Override
			public void forEachRemaining(Consumer<? super E> action) {
				i.forEachRemaining(action);
			}
		};
	}

	public List<E> subList(int fromIndex, int toIndex) {
		return new Collections$UnmodifiableListTest<>(list.subList(fromIndex, toIndex));
	}

	/**
	 * UnmodifiableRandomAccessList instances are serialized as UnmodifiableList
	 * instances to allow them to be deserialized in pre-1.4 JREs (which do not
	 * have UnmodifiableRandomAccessList). This method inverts the
	 * transformation. As a beneficial side-effect, it also grafts the
	 * RandomAccess marker onto UnmodifiableList instances that were serialized
	 * in pre-1.4 JREs.
	 *
	 * Note: Unfortunately, UnmodifiableRandomAccessList instances serialized in
	 * 1.4.1 and deserialized in 1.4 will become UnmodifiableList instances, as
	 * this method was missing in 1.4.
	 */
	private Object readResolve() {
		return (list instanceof RandomAccess ? new Collections$UnmodifiableRandomAccessListTest<>(list) : this);
	}
}