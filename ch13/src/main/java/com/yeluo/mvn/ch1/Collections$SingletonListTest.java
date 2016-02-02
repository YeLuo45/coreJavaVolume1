package com.yeluo.mvn.ch1;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.RandomAccess;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

/**
 * 
 * @author YeLuo
 * @function Collections$SingletonList的源码解析
 */
public class Collections$SingletonListTest<E>
extends AbstractList<E>
implements RandomAccess, Serializable {

	private static final long serialVersionUID = 3093736618740652951L;

	private final E element;
	
	/**
	 * 只有一个元素的集合
	 * @param obj
	 */
	Collections$SingletonListTest(E obj)                {element = obj;}

	
	public Iterator<E> iterator() {
		return singletonIterator(element);
	}

	/**
	 * 返回只有一个元素的迭代器
	 * 并且remove操作是不允许的
	 * @param e
	 * @return
	 */
	static <E> Iterator<E> singletonIterator(final E e) {
        return new Iterator<E>() {
            private boolean hasNext = true;
            public boolean hasNext() {
                return hasNext;
            }
            public E next() {
                if (hasNext) {
                    hasNext = false;
                    return e;
                }
                throw new NoSuchElementException();
            }
            public void remove() {
                throw new UnsupportedOperationException();
            }
            @Override
            public void forEachRemaining(Consumer<? super E> action) {
                Objects.requireNonNull(action);
                if (hasNext) {
                    action.accept(e);
                    hasNext = false;
                }
            }
        };
    }
	
	/**
	 * 返回集合的元素个数:1
	 */
	public int size()                   
	{return 1;}

	
	public boolean contains(Object obj) {return eq(obj, element);}
	
	/**
     * Returns true if the specified arguments are equal, or both null.
     *
     * NB: Do not replace with Object.equals until JDK-8015417 is resolved.
     */
    static boolean eq(Object o1, Object o2) {
        return o1==null ? o2==null : o1.equals(o2);
    }

	public E get(int index) {
		if (index != 0)
			throw new IndexOutOfBoundsException("Index: "+index+", Size: 1");
		return element;
	}

	// Override default methods for Collection
	@Override
	public void forEach(Consumer<? super E> action) {
		action.accept(element);
	}
	@Override
	public boolean removeIf(Predicate<? super E> filter) {
		throw new UnsupportedOperationException();
	}
	@Override
	public void replaceAll(UnaryOperator<E> operator) {
		throw new UnsupportedOperationException();
	}
	@Override
	public void sort(Comparator<? super E> c) {
	}
	@Override
	public Spliterator<E> spliterator() {
		return singletonSpliterator(element);
	}
	
	/**
     * Creates a {@code Spliterator} with only the specified element
     *
     * @param <T> Type of elements
     * @return A singleton {@code Spliterator}
     */
    static <T> Spliterator<T> singletonSpliterator(final T element) {
        return new Spliterator<T>() {
            long est = 1;

            @Override
            public Spliterator<T> trySplit() {
                return null;
            }

            @Override
            public boolean tryAdvance(Consumer<? super T> consumer) {
                Objects.requireNonNull(consumer);
                if (est > 0) {
                    est--;
                    consumer.accept(element);
                    return true;
                }
                return false;
            }

            @Override
            public void forEachRemaining(Consumer<? super T> consumer) {
                tryAdvance(consumer);
            }

            @Override
            public long estimateSize() {
                return est;
            }

            @Override
            public int characteristics() {
                int value = (element != null) ? Spliterator.NONNULL : 0;

                return value | Spliterator.SIZED | Spliterator.SUBSIZED | Spliterator.IMMUTABLE |
                       Spliterator.DISTINCT | Spliterator.ORDERED;
            }
        };
    }
	}
