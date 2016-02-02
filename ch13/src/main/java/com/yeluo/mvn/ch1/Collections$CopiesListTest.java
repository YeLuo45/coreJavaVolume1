package com.yeluo.mvn.ch1;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.List;
import java.util.RandomAccess;
import java.util.Spliterator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 
 * @author YeLuo
 * @function Collections$CopiesList的源码解析
 * 这个视图是都有的元素都相同的集合
 */
public class Collections$CopiesListTest<E>
extends AbstractList<E>
implements RandomAccess, Serializable
{
	private static final long serialVersionUID = 2739099268398711800L;
	//元素的个数
	final int n;
	//元素
	final E element;

	Collections$CopiesListTest(int n, E e) {
		//断言元素个数大于等于0
		assert n >= 0;
		this.n = n;
		element = e;
	}
	/**
	 * 返回集合的长度
	 */
	public int size() {
		return n;
	}
	/**
	 * 检测该集合是否包含指定的对象
	 */
	public boolean contains(Object obj) {
		return n != 0 && eq(obj, element);
	}
	/**
     * Returns true if the specified arguments are equal, or both null.
     *
     * NB: Do not replace with Object.equals until JDK-8015417 is resolved.
     */
    static boolean eq(Object o1, Object o2) {
        return o1==null ? o2==null : o1.equals(o2);
    }
    
    /**
     * 返回指定元素的索引(第一次出现的)
     * 由于在视图的所有元素都是相同的,所以
     * 返回0,就代表存在该元素
     * 返回-1,就代表不存在该元素
     */
	public int indexOf(Object o) {
		return contains(o) ? 0 : -1;
	}
	/**
     * 返回指定元素的索引(最后一次出现的)
     * 由于在视图的所有元素都是相同的,所以
     * 返回n-1,就代表存在该元素
     * 返回-1,就代表不存在该元素
     */
	public int lastIndexOf(Object o) {
		return contains(o) ? n - 1 : -1;
	}

	/**
	 * 获得指定索引的元素
	 */
	public E get(int index) {
		if (index < 0 || index >= n)
			throw new IndexOutOfBoundsException("Index: "+index+
                                            ", Size: "+n);
		return element;
	}
	
	/**
	 * 返回一个对象数组
	 */
	public Object[] toArray() {
		final Object[] a = new Object[n];
		if (element != null)
			Arrays.fill(a, 0, n, element);
		return a;
	}

	/**
	 * 返回一个指定类型的数组
	 * 如果传进去的数组的长度小于集合的个数,
	 * 则用java.lang.reflect.Array.newInstance方法构造
	 * 再用Arrays.fill方法填充
	 * 如果传进去的数组的长度大于等于集合的个数,
	 * 多余的用null填充.
	 */
	@SuppressWarnings("unchecked")
	public <T> T[] toArray(T[] a) {
		final int n = this.n;
		if (a.length < n) {
			a = (T[])java.lang.reflect.Array
					.newInstance(a.getClass().getComponentType(), n);
			if (element != null)
				Arrays.fill(a, 0, n, element);
		} else {
			Arrays.fill(a, 0, n, element);	
			if (a.length > n)
				a[n] = null;
		}
		return a;
	}

	/**
	 * 返回指定范围的子集
	 */
	public List<E> subList(int fromIndex, int toIndex) {
		if (fromIndex < 0)
			throw new IndexOutOfBoundsException("fromIndex = " + fromIndex);
		if (toIndex > n)
			throw new IndexOutOfBoundsException("toIndex = " + toIndex);
		if (fromIndex > toIndex)
			throw new IllegalArgumentException("fromIndex(" + fromIndex +
                                           ") > toIndex(" + toIndex + ")");
		return new Collections$CopiesListTest<>(toIndex - fromIndex, element);
	}

	// Override default methods in Collection
	@Override
	public Stream<E> stream() {
		return IntStream.range(0, n).mapToObj(i -> element);
	}

	@Override
	public Stream<E> parallelStream() {
		return IntStream.range(0, n).parallel().mapToObj(i -> element);
	}

	@Override
	public Spliterator<E> spliterator() {
		return stream().spliterator();
	}
	}

