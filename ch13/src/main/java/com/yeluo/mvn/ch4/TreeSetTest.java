package com.yeluo.mvn.ch4;

import java.util.AbstractSet;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.NoSuchElementException;
import java.util.SortedSet;
import java.util.Spliterator;

import com.yeluo.mvn.ch5.TreeMapTest;

/**
 * 
 * @author YeLuo
 * @function
 */
/**
 * public class TreeSet<E>
 * extends AbstractSet<E>
 * implements NavigableSet<E>, Cloneable, Serializable
 * 1.基于 TreeMap 的 NavigableSet 实现。使用元素的自然顺序对元素进行排序，
 * 或者根据创建 set 时提供的 Comparator 进行排序，具体取决于使用的构造方法。 
 * 
 * 2.此实现为基本操作（add、remove 和 contains）提供受保证的 log(n) 时间开销。 
 * 
 * 3.注意，如果要正确实现 Set 接口，则 set 维护的顺序（无论是否提供了显式比较器）必须与 equals 一致。
 * （关于与 equals 一致 的精确定义，请参阅 Comparable 或 Comparator。）
 * 这是因为 Set 接口是按照 equals 操作定义的，但 TreeSet 实例使用它的 compareTo（或 compare）方法对所有元素进行比较，
 * 因此从 set 的观点来看，此方法认为相等的两个元素就是相等的。
 * 即使 set 的顺序与 equals 不一致，其行为也是 定义良好的；它只是违背了 Set 接口的常规协定。 
 * 
 * 3.注意，此实现不是同步的。如果多个线程同时访问一个 TreeSet，而其中至少一个线程修改了该 set，那么它必须 外部同步。
 * 这一般是通过对自然封装该 set 的对象执行同步操作来完成的。
 * 如果不存在这样的对象，则应该使用 Collections.synchronizedSortedSet 方法来“包装”该 set。
 * 此操作最好在创建时进行，以防止对 set 的意外非同步访问： 
 *   SortedSet s = Collections.synchronizedSortedSet(new TreeSet(...));
 *   此类的 iterator 方法返回的迭代器是快速失败 的：
 *   在创建迭代器之后，如果从结构上对 set 进行修改，除非通过迭代器自身的 remove 方法，
 *   否则在其他任何时间以任何方式进行修改都将导致迭代器抛出 ConcurrentModificationException。
 *   因此，对于并发的修改，迭代器很快就完全失败，而不会冒着在将来不确定的时间发生不确定行为的风险。 
 * 
 * 4.注意，迭代器的快速失败行为无法得到保证，一般来说，存在不同步的并发修改时，不可能作出任何肯定的保证。
 * 快速失败迭代器尽最大努力抛出 ConcurrentModificationException。
 * 因此，编写依赖于此异常的程序的做法是错误的，正确做法是：迭代器的快速失败行为应该仅用于检测 bug。 
 * 
 * 5.此类是 Java Collections Framework 的成员。 
 * 
 * 
 */
public class TreeSetTest<E> 
extends AbstractSet<E> 
implements NavigableSet<E>, Cloneable, java.io.Serializable {
	/**
	 * The backing map.
	 */
	private transient NavigableMap<E, Object> m;

	// Dummy value to associate with an Object in the backing Map
	private static final Object PRESENT = new Object();

	/**
	 * Constructs a set backed by the specified navigable map.
	 */
	TreeSetTest(NavigableMap<E,Object> m) {
    this.m = m;
	}

	/**
	 * Constructs a new, empty tree set, sorted according to the
	 * natural ordering of its elements.  All elements inserted into
	 * the set must implement the {@link Comparable} interface.
	 * Furthermore, all such elements must be <i>mutually
	 * comparable</i>: {@code e1.compareTo(e2)} must not throw a
	 * {@code ClassCastException} for any elements {@code e1} and
	 * {@code e2} in the set.  If the user attempts to add an element
	 * to the set that violates this constraint (for example, the user
	 * attempts to add a string element to a set whose elements are
	 * integers), the {@code add} call will throw a
	 * {@code ClassCastException}.
	 */
	public TreeSetTest() {
		this(new TreeMapTest<E,Object>());
	}

	/**
	 * Constructs a new, empty tree set, sorted according to the specified
	 * comparator. All elements inserted into the set must be <i>mutually
	 * comparable</i> by the specified comparator: {@code comparator.compare(e1,
	 * e2)} must not throw a {@code ClassCastException} for any elements
	 * {@code e1} and {@code e2} in the set. If the user attempts to add an
	 * element to the set that violates this constraint, the {@code add} call
	 * will throw a {@code ClassCastException}.
	 *
	 * @param comparator
	 *            the comparator that will be used to order this set. If
	 *            {@code null}, the {@linkplain Comparable natural ordering} of
	 *            the elements will be used.
	 */
	public TreeSetTest(Comparator<? super E> comparator) {
		this(new TreeMapTest<>(comparator));
	}

	/**
	 * Constructs a new tree set containing the elements in the specified
	 * collection, sorted according to the <i>natural ordering</i> of its
	 * elements. All elements inserted into the set must implement the
	 * {@link Comparable} interface. Furthermore, all such elements must be
	 * <i>mutually comparable</i>: {@code e1.compareTo(e2)} must not throw a
	 * {@code ClassCastException} for any elements {@code e1} and {@code e2} in
	 * the set.
	 *
	 * @param c
	 *            collection whose elements will comprise the new set
	 * @throws ClassCastException
	 *             if the elements in {@code c} are not {@link Comparable}, or
	 *             are not mutually comparable
	 * @throws NullPointerException
	 *             if the specified collection is null
	 */
	public TreeSetTest(Collection<? extends E> c) {
		this();
		addAll(c);
	}

	/**
	 * Constructs a new tree set containing the same elements and using the same
	 * ordering as the specified sorted set.
	 *
	 * @param s
	 *            sorted set whose elements will comprise the new set
	 * @throws NullPointerException
	 *             if the specified sorted set is null
	 */
	public TreeSetTest(SortedSet<E> s) {
		this(s.comparator());
		addAll(s);
	}

	/**
	 * Returns an iterator over the elements in this set in ascending order.
	 *
	 * @return an iterator over the elements in this set in ascending order
	 */
	public Iterator<E> iterator() {
		return m.navigableKeySet().iterator();
	}

	/**
	 * Returns an iterator over the elements in this set in descending order.
	 *
	 * @return an iterator over the elements in this set in descending order
	 * @since 1.6
	 */
	public Iterator<E> descendingIterator() {
		return m.descendingKeySet().iterator();
	}

	/**
	 * @since 1.6
	 */
	public NavigableSet<E> descendingSet() {
		return new TreeSetTest<>(m.descendingMap());
	}

	/**
	 * Returns the number of elements in this set (its cardinality).
	 *
	 * @return the number of elements in this set (its cardinality)
	 */
	public int size() {
		return m.size();
	}

	/**
	 * Returns {@code true} if this set contains no elements.
	 *
	 * @return {@code true} if this set contains no elements
	 */
	public boolean isEmpty() {
		return m.isEmpty();
	}

	/**
	 * Returns {@code true} if this set contains the specified element. More
	 * formally, returns {@code true} if and only if this set contains an
	 * element {@code e} such that
	 * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</tt>.
	 *
	 * @param o
	 *            object to be checked for containment in this set
	 * @return {@code true} if this set contains the specified element
	 * @throws ClassCastException
	 *             if the specified object cannot be compared with the elements
	 *             currently in the set
	 * @throws NullPointerException
	 *             if the specified element is null and this set uses natural
	 *             ordering, or its comparator does not permit null elements
	 */
	public boolean contains(Object o) {
		return m.containsKey(o);
	}

	/**
	 * Adds the specified element to this set if it is not already present. More
	 * formally, adds the specified element {@code e} to this set if the set
	 * contains no element {@code e2} such that
	 * <tt>(e==null&nbsp;?&nbsp;e2==null&nbsp;:&nbsp;e.equals(e2))</tt>. If this
	 * set already contains the element, the call leaves the set unchanged and
	 * returns {@code false}.
	 *
	 * @param e
	 *            element to be added to this set
	 * @return {@code true} if this set did not already contain the specified
	 *         element
	 * @throws ClassCastException
	 *             if the specified object cannot be compared with the elements
	 *             currently in this set
	 * @throws NullPointerException
	 *             if the specified element is null and this set uses natural
	 *             ordering, or its comparator does not permit null elements
	 */
	public boolean add(E e) {
		return m.put(e, PRESENT) == null;
	}

	/**
	 * Removes the specified element from this set if it is present. More
	 * formally, removes an element {@code e} such that
	 * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</tt>, if this
	 * set contains such an element. Returns {@code true} if this set contained
	 * the element (or equivalently, if this set changed as a result of the
	 * call). (This set will not contain the element once the call returns.)
	 *
	 * @param o
	 *            object to be removed from this set, if present
	 * @return {@code true} if this set contained the specified element
	 * @throws ClassCastException
	 *             if the specified object cannot be compared with the elements
	 *             currently in this set
	 * @throws NullPointerException
	 *             if the specified element is null and this set uses natural
	 *             ordering, or its comparator does not permit null elements
	 */
	public boolean remove(Object o) {
		return m.remove(o) == PRESENT;
	}

	/**
	 * Removes all of the elements from this set. The set will be empty after
	 * this call returns.
	 */
	public void clear() {
		m.clear();
	}

	/**
	 * Adds all of the elements in the specified collection to this set.
	 *
	 * @param c
	 *            collection containing elements to be added to this set
	 * @return {@code true} if this set changed as a result of the call
	 * @throws ClassCastException
	 *             if the elements provided cannot be compared with the elements
	 *             currently in the set
	 * @throws NullPointerException
	 *             if the specified collection is null or if any element is null
	 *             and this set uses natural ordering, or its comparator does
	 *             not permit null elements
	 */
	public boolean addAll(Collection<? extends E> c) {
		// Use linear-time version if applicable
		if (m.size() == 0 && c.size() > 0 && c instanceof SortedSet && m instanceof TreeMapTest) {
			SortedSet<? extends E> set = (SortedSet<? extends E>) c;
			TreeMapTest<E, Object> map = (TreeMapTest<E, Object>) m;
			Comparator<?> cc = set.comparator();
			Comparator<? super E> mc = map.comparator();
			if (cc == mc || (cc != null && cc.equals(mc))) {
				map.addAllForTreeSet(set, PRESENT);
				return true;
			}
		}
		return super.addAll(c);
	}

	/**
	 * @throws ClassCastException
	 *             {@inheritDoc}
	 * @throws NullPointerException
	 *             if {@code fromElement} or {@code toElement} is null and this
	 *             set uses natural ordering, or its comparator does not permit
	 *             null elements
	 * @throws IllegalArgumentException
	 *             {@inheritDoc}
	 * @since 1.6
	 */
	public NavigableSet<E> subSet(E fromElement, boolean fromInclusive, E toElement, boolean toInclusive) {
		return new TreeSetTest<>(m.subMap(fromElement, fromInclusive, toElement, toInclusive));
	}

	/**
	 * @throws ClassCastException
	 *             {@inheritDoc}
	 * @throws NullPointerException
	 *             if {@code toElement} is null and this set uses natural
	 *             ordering, or its comparator does not permit null elements
	 * @throws IllegalArgumentException
	 *             {@inheritDoc}
	 * @since 1.6
	 */
	public NavigableSet<E> headSet(E toElement, boolean inclusive) {
		return new TreeSetTest<>(m.headMap(toElement, inclusive));
	}

	/**
	 * @throws ClassCastException
	 *             {@inheritDoc}
	 * @throws NullPointerException
	 *             if {@code fromElement} is null and this set uses natural
	 *             ordering, or its comparator does not permit null elements
	 * @throws IllegalArgumentException
	 *             {@inheritDoc}
	 * @since 1.6
	 */
	public NavigableSet<E> tailSet(E fromElement, boolean inclusive) {
		return new TreeSetTest<>(m.tailMap(fromElement, inclusive));
	}

	/**
	 * @throws ClassCastException
	 *             {@inheritDoc}
	 * @throws NullPointerException
	 *             if {@code fromElement} or {@code toElement} is null and this
	 *             set uses natural ordering, or its comparator does not permit
	 *             null elements
	 * @throws IllegalArgumentException
	 *             {@inheritDoc}
	 */
	public SortedSet<E> subSet(E fromElement, E toElement) {
		return subSet(fromElement, true, toElement, false);
	}

	/**
	 * @throws ClassCastException
	 *             {@inheritDoc}
	 * @throws NullPointerException
	 *             if {@code toElement} is null and this set uses natural
	 *             ordering, or its comparator does not permit null elements
	 * @throws IllegalArgumentException
	 *             {@inheritDoc}
	 */
	public SortedSet<E> headSet(E toElement) {
		return headSet(toElement, false);
	}

	/**
	 * @throws ClassCastException
	 *             {@inheritDoc}
	 * @throws NullPointerException
	 *             if {@code fromElement} is null and this set uses natural
	 *             ordering, or its comparator does not permit null elements
	 * @throws IllegalArgumentException
	 *             {@inheritDoc}
	 */
	public SortedSet<E> tailSet(E fromElement) {
		return tailSet(fromElement, true);
	}

	public Comparator<? super E> comparator() {
		return m.comparator();
	}

	/**
	 * @throws NoSuchElementException
	 *             {@inheritDoc}
	 */
	public E first() {
		return m.firstKey();
	}

	/**
	 * @throws NoSuchElementException
	 *             {@inheritDoc}
	 */
	public E last() {
		return m.lastKey();
	}

	// NavigableSet API methods

	/**
	 * @throws ClassCastException
	 *             {@inheritDoc}
	 * @throws NullPointerException
	 *             if the specified element is null and this set uses natural
	 *             ordering, or its comparator does not permit null elements
	 * @since 1.6
	 */
	public E lower(E e) {
		return m.lowerKey(e);
	}

	/**
	 * @throws ClassCastException
	 *             {@inheritDoc}
	 * @throws NullPointerException
	 *             if the specified element is null and this set uses natural
	 *             ordering, or its comparator does not permit null elements
	 * @since 1.6
	 */
	public E floor(E e) {
		return m.floorKey(e);
	}

	/**
	 * @throws ClassCastException
	 *             {@inheritDoc}
	 * @throws NullPointerException
	 *             if the specified element is null and this set uses natural
	 *             ordering, or its comparator does not permit null elements
	 * @since 1.6
	 */
	public E ceiling(E e) {
		return m.ceilingKey(e);
	}

	/**
	 * @throws ClassCastException
	 *             {@inheritDoc}
	 * @throws NullPointerException
	 *             if the specified element is null and this set uses natural
	 *             ordering, or its comparator does not permit null elements
	 * @since 1.6
	 */
	public E higher(E e) {
		return m.higherKey(e);
	}

	/**
	 * @since 1.6
	 */
	public E pollFirst() {
		Map.Entry<E, ?> e = m.pollFirstEntry();
		return (e == null) ? null : e.getKey();
	}

	/**
	 * @since 1.6
	 */
	public E pollLast() {
		Map.Entry<E, ?> e = m.pollLastEntry();
		return (e == null) ? null : e.getKey();
	}

	/**
	 * Returns a shallow copy of this {@code TreeSet} instance. (The elements
	 * themselves are not cloned.)
	 *
	 * @return a shallow copy of this set
	 */
	@SuppressWarnings("unchecked")
	public Object clone() {
		TreeSetTest<E> clone;
		try {
			clone = (TreeSetTest<E>) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new InternalError(e);
		}

		clone.m = new TreeMapTest<>(m);
		return clone;
	}

	/**
	 * Save the state of the {@code TreeSet} instance to a stream (that is,
	 * serialize it).
	 *
	 * @serialData Emits the comparator used to order this set, or {@code null}
	 *             if it obeys its elements' natural ordering (Object), followed
	 *             by the size of the set (the number of elements it contains)
	 *             (int), followed by all of its elements (each an Object) in
	 *             order (as determined by the set's Comparator, or by the
	 *             elements' natural ordering if the set has no Comparator).
	 */
	private void writeObject(java.io.ObjectOutputStream s) throws java.io.IOException {
		// Write out any hidden stuff
		s.defaultWriteObject();

		// Write out Comparator
		s.writeObject(m.comparator());

		// Write out size
		s.writeInt(m.size());

		// Write out all elements in the proper order.
		for (E e : m.keySet())
			s.writeObject(e);
	}

	/**
	 * Reconstitute the {@code TreeSet} instance from a stream (that is,
	 * deserialize it).
	 */
	private void readObject(java.io.ObjectInputStream s) throws java.io.IOException, ClassNotFoundException {
		// Read in any hidden stuff
		s.defaultReadObject();

		// Read in Comparator
		@SuppressWarnings("unchecked")
		Comparator<? super E> c = (Comparator<? super E>) s.readObject();

		// Create backing TreeMap
		TreeMapTest<E, Object> tm = new TreeMapTest<>(c);
		m = tm;

		// Read in size
		int size = s.readInt();

		tm.readTreeSet(size, s, PRESENT);
	}

	/**
	 * Creates a <em><a href="Spliterator.html#binding">late-binding</a></em>
	 * and <em>fail-fast</em> {@link Spliterator} over the elements in this set.
	 *
	 * <p>
	 * The {@code Spliterator} reports {@link Spliterator#SIZED},
	 * {@link Spliterator#DISTINCT}, {@link Spliterator#SORTED}, and
	 * {@link Spliterator#ORDERED}. Overriding implementations should document
	 * the reporting of additional characteristic values.
	 *
	 * <p>
	 * The spliterator's comparator (see
	 * {@link java.util.Spliterator#getComparator()}) is {@code null} if the
	 * tree set's comparator (see {@link #comparator()}) is {@code null}.
	 * Otherwise, the spliterator's comparator is the same as or imposes the
	 * same total ordering as the tree set's comparator.
	 *
	 * @return a {@code Spliterator} over the elements in this set
	 * @since 1.8
	 */
	public Spliterator<E> spliterator() {
		return TreeMapTest.keySpliteratorFor(m);
	}

	private static final long serialVersionUID = -2479143000061671589L;
}
