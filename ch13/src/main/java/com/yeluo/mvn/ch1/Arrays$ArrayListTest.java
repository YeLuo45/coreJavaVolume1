package com.yeluo.mvn.ch1;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.RandomAccess;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;
/**
 * 
 * @author YeLuo
 * @function Arrays$ArrayList的源码解析
 * @param <E>
 */
public class Arrays$ArrayListTest<E> extends AbstractList<E>
implements RandomAccess, java.io.Serializable
{
	private static final long serialVersionUID = -2764017481108945198L;
	private final E[] a;
	
	Arrays$ArrayListTest(E[] array) {
		//检测array是否为空
		a = Objects.requireNonNull(array);
	}

	@Override
	public int size() {
		return a.length;
	}

	/*
	 * 浅克隆(non-Javadoc)
	 * 返回对象数组
	 * @see java.util.AbstractCollection#toArray()
	 */
	@Override
	public Object[] toArray() {
		return a.clone();
	}

	/**
	 * 返回指定类型的数组
	 */
	@Override
	@SuppressWarnings("unchecked")
	public <T> T[] toArray(T[] a) {
		int size = size();
		if (a.length < size)
			return Arrays.copyOf(this.a, size,
                             (Class<? extends T[]>) a.getClass());
		System.arraycopy(this.a, 0, a, 0, size);
		if (a.length > size)
			a[size] = null;
		return a;
	}

	/**
	 * 返回指定索引的元素
	 */
	@Override
	public E get(int index) {
		return a[index];
	}

	/**
	 * 修改指定索引的元素
	 */
	@Override
	public E set(int index, E element) {
		E oldValue = a[index];
		a[index] = element;
		return oldValue;
	}
	
	/**
	 * 返回指定对象的索引(第一次出现的)
	 */
	@Override
	public int indexOf(Object o) {
		E[] a = this.a;
		if (o == null) {
			for (int i = 0; i < a.length; i++)
				if (a[i] == null)
					return i;
		} else {
		for (int i = 0; i < a.length; i++)
            if (o.equals(a[i]))
                return i;
		}
		return -1;
	}
	
	/**
	 * 检测是否包含该对象
	 */
	@Override
	public boolean contains(Object o) {
		return indexOf(o) != -1;
	}

	@Override
	public Spliterator<E> spliterator() {
		return Spliterators.spliterator(a, Spliterator.ORDERED);
	}

	@Override
	public void forEach(Consumer<? super E> action) {
		Objects.requireNonNull(action);
		for (E e : a) {
			action.accept(e);
		}
	}

	@Override
	public void replaceAll(UnaryOperator<E> operator) {
		Objects.requireNonNull(operator);
		E[] a = this.a;
		for (int i = 0; i < a.length; i++) {
			a[i] = operator.apply(a[i]);
		}
	}

	/**
	 * 按照指定的比较器对象进行排序
	 */
	@Override
	public void sort(Comparator<? super E> c) {
		Arrays.sort(a, c);
	}
	}