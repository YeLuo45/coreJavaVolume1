package com.yeluo.mvn.ch4;

import java.io.InvalidObjectException;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Spliterator;

/**
 * 
 * @author YeLuo
 * @function HashSet
 */
/**
 * public class HashSet<E>
 * extends AbstractSet<E>
 * implements Set<E>, Cloneable, Serializable
 * 1.此类实现 Set 接口，由哈希表（实际上是一个 HashMap 实例）支持。
 * 它不保证 set 的迭代顺序；特别是它不保证该顺序恒久不变。此类允许使用 null 元素。 
 * 
 * 2.此类为基本操作提供了稳定性能，这些基本操作包括 add、remove、contains 和 size，
 * 假定哈希函数将这些元素正确地分布在桶中。
 * 对此 set 进行迭代所需的时间与 HashSet 实例的大小（元素的数量）和底层 HashMap 实例（桶的数量）的“容量”的和成比例。
 * 因此，如果迭代性能很重要，则不要将初始容量设置得太高（或将加载因子设置得太低）。 
 *  
 * 3.注意，此实现不是同步的。如果多个线程同时访问一个哈希 set，而其中至少一个线程修改了该 set，那么它必须 保持外部同步。
 *  这通常是通过对自然封装该 set 的对象执行同步操作来完成的。
 *  如果不存在这样的对象，则应该使用 Collections.synchronizedSet 方法来“包装” set。
 *  最好在创建时完成这一操作，以防止对该 set 进行意外的不同步访问：
 *    Set s = Collections.synchronizedSet(new HashSet(...));
 *    此类的 iterator方法返回的迭代器是快速失败的：
 *    在创建迭代器之后，如果对 set 进行修改，除非通过迭代器自身的 remove 方法，
 *    否则在任何时间以任何方式对其进行修改，Iterator 都将抛出 ConcurrentModificationException。
 *    因此，面对并发的修改，迭代器很快就会完全失败，而不冒将来在某个不确定时间发生任意不确定行为的风险。 
 * 
 * 4.注意，迭代器的快速失败行为无法得到保证，因为一般来说，不可能对是否出现不同步并发修改做出任何硬性保证。
 * 快速失败迭代器在尽最大努力抛出 ConcurrentModificationException。
 * 因此，为提高这类迭代器的正确性而编写一个依赖于此异常的程序是错误做法：迭代器的快速失败行为应该仅用于检测 bug。 
 * 
 * 5.此类是 Java Collections Framework 的成员。 
 * 
 * 
 */
public class HashSetTest<E> 
extends AbstractSet<E> 
implements Set<E>, Cloneable, java.io.Serializable {
	static final long serialVersionUID = -5024744406713321676L;

	private transient HashMap<E, Object> map;

	// Dummy value to associate with an Object in the backing Map
	private static final Object PRESENT = new Object();

	/**
	 * 构造一个新的、空的set集；
	 * 该实例默认容量为16，加载因子为0.75。
	 * Constructs a new, empty set; the backing t<tt>HashMap</tt> instance has
	 * default initial capacity (16) and load factor (0.75).
	 */
	public HashSetTest() {
		map = new HashMap<>();
	}

	/**
	 * 按照指定集合来创建
	 * Constructs a new set containing the elements in the specified collection.
	 * The <tt>HashMap</tt> is created with default load factor (0.75) and an
	 * initial capacity sufficient to contain the elements in the specified
	 * collection.
	 *
	 * @param c
	 *            the collection whose elements are to be placed into this set
	 * @throws NullPointerException
	 *             if the specified collection is null
	 */
	public HashSetTest(Collection<? extends E> c) {
		map = new HashMap<>(Math.max((int) (c.size() / .75f) + 1, 16));
		addAll(c);
	}

	/**
	 * 指定初始容量和加载因子创建
	 * Constructs a new, empty set; the backing <tt>HashMap</tt> instance has
	 * the specified initial capacity and the specified load factor.
	 *
	 * @param initialCapacity
	 *            the initial capacity of the hash map
	 * @param loadFactor
	 *            the load factor of the hash map
	 * @throws IllegalArgumentException
	 *             if the initial capacity is less than zero, or if the load
	 *             factor is nonpositive
	 */
	public HashSetTest(int initialCapacity, float loadFactor) {
		map = new HashMap<>(initialCapacity, loadFactor);
	}

	/**
	 * 指定初始容量创建
	 * Constructs a new, empty set; the backing <tt>HashMap</tt> instance has
	 * the specified initial capacity and default load factor (0.75).
	 *
	 * @param initialCapacity
	 *            the initial capacity of the hash table
	 * @throws IllegalArgumentException
	 *             if the initial capacity is less than zero
	 */
	public HashSetTest(int initialCapacity) {
		map = new HashMap<>(initialCapacity);
	}

	/**
	 * 该构造方法是包可见的，而且不是HashMap的实例了，而是LinkedHashMap的实例了
	 * Constructs a new, empty linked hash set. (This package private
	 * constructor is only used by LinkedHashSet.) The backing HashMap instance
	 * is a LinkedHashMap with the specified initial capacity and the specified
	 * load factor.
	 *
	 * @param initialCapacity
	 *            the initial capacity of the hash map
	 * @param loadFactor
	 *            the load factor of the hash map
	 * @param dummy
	 *            ignored (distinguishes this constructor from other int, float
	 *            constructor.)
	 * @throws IllegalArgumentException
	 *             if the initial capacity is less than zero, or if the load
	 *             factor is nonpositive
	 */
	HashSetTest(int initialCapacity, float loadFactor, boolean dummy) {
		map = new LinkedHashMap<>(initialCapacity, loadFactor);
	}

	/**
	 * 返回一个没有特别次序的元素集合的迭代器
	 * Returns an iterator over the elements in this set. The elements are
	 * returned in no particular order.
	 *
	 * @return an Iterator over the elements in this set
	 * @see ConcurrentModificationException
	 */
	public Iterator<E> iterator() {
		//Set<E> java.util.HashMap.keySet()
		return map.keySet().iterator();
	}

	/**
	 * Returns the number of elements in this set (its cardinality).
	 *
	 * @return the number of elements in this set (its cardinality)
	 */
	public int size() {
		return map.size();
	}

	/**
	 * Returns <tt>true</tt> if this set contains no elements.
	 *
	 * @return <tt>true</tt> if this set contains no elements
	 */
	public boolean isEmpty() {
		return map.isEmpty();
	}

	/**
	 * Returns <tt>true</tt> if this set contains the specified element. More
	 * formally, returns <tt>true</tt> if and only if this set contains an
	 * element <tt>e</tt> such that
	 * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</tt>.
	 *
	 * @param o
	 *            element whose presence in this set is to be tested
	 * @return <tt>true</tt> if this set contains the specified element
	 */
	public boolean contains(Object o) {
		return map.containsKey(o);
	}

	/**
	 * 如果此 set 中尚未包含指定元素，则添加指定元素。
	 * 更确切地讲，如果此 set 没有包含满足 (e==null ? e2==null : e.equals(e2)) 的元素 e2，
	 * 则向此 set 添加指定的元素 e。如果此 set 已包含该元素，则该调用不更改 set 并返回 false。
	 * 注释:在HashMap集合中,添加的key对应的value都是PRESENT
	 * 
	 * Adds the specified element to this set if it is not already present. More
	 * formally, adds the specified element <tt>e</tt> to this set if this set
	 * contains no element <tt>e2</tt> such that
	 * <tt>(e==null&nbsp;?&nbsp;e2==null&nbsp;:&nbsp;e.equals(e2))</tt>. If this
	 * set already contains the element, the call leaves the set unchanged and
	 * returns <tt>false</tt>.
	 *
	 * @param e
	 *            element to be added to this set
	 * @return <tt>true</tt> if this set did not already contain the specified
	 *         element
	 */
	public boolean add(E e) {
		return map.put(e, PRESENT) == null;
	}

	/**
	 * 移除指定的元素.移除成功,则返回true;否则返回false
	 * Removes the specified element from this set if it is present. More
	 * formally, removes an element <tt>e</tt> such that
	 * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</tt>, if this
	 * set contains such an element. Returns <tt>true</tt> if this set contained
	 * the element (or equivalently, if this set changed as a result of the
	 * call). (This set will not contain the element once the call returns.)
	 *
	 * @param o
	 *            object to be removed from this set, if present
	 * @return <tt>true</tt> if the set contained the specified element
	 */
	public boolean remove(Object o) {
		return map.remove(o) == PRESENT;
	}

	/**
	 * 移除set集合的所有元素
	 * Removes all of the elements from this set. The set will be empty after
	 * this call returns.
	 */
	public void clear() {
		map.clear();
	}

	/**
	 * 浅克隆
	 * Returns a shallow copy of this <tt>HashSet</tt> instance: the elements
	 * themselves are not cloned.
	 *
	 * @return a shallow copy of this set
	 */
	@SuppressWarnings("unchecked")
	public Object clone() {
		try {
			HashSetTest<E> newSet = (HashSetTest<E>) super.clone();
			newSet.map = (HashMap<E, Object>) map.clone();
			return newSet;
		} catch (CloneNotSupportedException e) {
			throw new InternalError(e);
		}
	}

	/**
	 * 保存这个HashSet实例的状态到流中(那就是序列化它)
	 * Save the state of this <tt>HashSet</tt> instance to a stream (that is,
	 * serialize it).
	 *
	 * @serialData The capacity of the backing <tt>HashMap</tt> instance (int),
	 *             and its load factor (float) are emitted, followed by the size
	 *             of the set (the number of elements it contains) (int),
	 *             followed by all of its elements (each an Object) in no
	 *             particular order.
	 */
	private void writeObject(java.io.ObjectOutputStream s) throws java.io.IOException {
		// Write out any hidden serialization magic
		s.defaultWriteObject();

		// Write out HashMap capacity and load factor
		/*s.writeInt(map.capacity());
		s.writeFloat(map.loadFactor());*/

		// Write out size
		s.writeInt(map.size());

		// Write out all elements in the proper order.
		for (E e : map.keySet())
			s.writeObject(e);
	}

	/**
	 * 从一个流中,重建HashSet实例
	 * 反序列化
	 * Reconstitute the <tt>HashSet</tt> instance from a stream (that is,
	 * deserialize it).
	 */
	private void readObject(java.io.ObjectInputStream s) throws java.io.IOException, ClassNotFoundException {
		// Read in any hidden serialization magic
		s.defaultReadObject();

		// Read capacity and verify non-negative.
		int capacity = s.readInt();
		if (capacity < 0) {
			throw new InvalidObjectException("Illegal capacity: " + capacity);
		}

		// Read load factor and verify positive and non NaN.
		float loadFactor = s.readFloat();
		if (loadFactor <= 0 || Float.isNaN(loadFactor)) {
			throw new InvalidObjectException("Illegal load factor: " + loadFactor);
		}

		// Read size and verify non-negative.
		int size = s.readInt();
		if (size < 0) {
			throw new InvalidObjectException("Illegal size: " + size);
		}

		// Set the capacity according to the size and load factor ensuring that
		// the HashMap is at least 25% full but clamping to maximum capacity.
		/*capacity = (int) Math.min(size * Math.min(1 / loadFactor, 4.0f), HashMap.MAXIMUM_CAPACITY);*/

		// Create backing HashMap
		/*map = (((HashSetTest<?>) this) instanceof LinkedHashSet ? new LinkedHashMap<E, Object>(capacity, loadFactor)
				: new HashMap<E, Object>(capacity, loadFactor));*/

		// Read in all elements in the proper order.
		for (int i = 0; i < size; i++) {
			@SuppressWarnings("unchecked")
			E e = (E) s.readObject();
			map.put(e, PRESENT);
		}
	}

	/**
	 * Creates a <em><a href="Spliterator.html#binding">late-binding</a></em>
	 * and <em>fail-fast</em> {@link Spliterator} over the elements in this set.
	 *
	 * <p>
	 * The {@code Spliterator} reports {@link Spliterator#SIZED} and
	 * {@link Spliterator#DISTINCT}. Overriding implementations should document
	 * the reporting of additional characteristic values.
	 *
	 * @return a {@code Spliterator} over the elements in this set
	 * @since 1.8
	 */
	/*public Spliterator<E> spliterator() {
		return new HashMap.KeySpliterator<E, Object>(map, 0, -1, 0, 0);
	}*/
}
