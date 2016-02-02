package com.yeluo.mvn.ch5;

import java.io.IOException;
import java.util.AbstractCollection;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
/**
 * 
 * @author YeLuo
 * @function
 * @param <K>
 * @param <V>
 */
/**
 * public class LinkedHashMap<K,V>
 * extends HashMap<K,V>
 * implements Map<K,V>
 * 1.Map 接口的哈希表和链接列表实现，具有可预知的迭代顺序。
 * 此实现与 HashMap 的不同之处在于，后者维护着一个运行于所有条目的双重链接列表。
 * 此链接列表定义了迭代顺序，该迭代顺序通常就是将键插入到映射中的顺序（插入顺序）。
 * 注意，如果在映射中重新插入 键，则插入顺序不受影响。
 * （如果在调用 m.put(k, v) 前 m.containsKey(k) 返回了 true，则调用时会将键 k 重新插入到映射 m 中。） 
 * 
 * 2.此实现可以让客户避免未指定的、由 HashMap（及 Hashtable）所提供的通常为杂乱无章的排序工作，
 * 同时无需增加与 TreeMap 相关的成本。使用它可以生成一个与原来顺序相同的映射副本，而与原映射的实现无关：  
 *      void foo(Map m) {
 *          Map copy = new LinkedHashMap(m);
 *          ...
 *      }
 *  如果模块通过输入得到一个映射，复制这个映射，然后返回由此副本确定其顺序的结果，这种情况下这项技术特别有用。
 *  （客户通常期望返回的内容与其出现的顺序相同。） 
 *  
 * 3.提供特殊的构造方法来创建链接哈希映射，该哈希映射的迭代顺序就是最后访问其条目的顺序，从近期访问最少到近期访问最多的顺序
 * （访问顺序）。这种映射很适合构建 LRU 缓存。调用 put 或 get 方法将会访问相应的条目（假定调用完成后它还存在）。
 * putAll 方法以指定映射的条目集迭代器提供的键-值映射关系的顺序，为指定映射的每个映射关系生成一个条目访问。
 * 任何其他方法均不生成条目访问。特别是，collection 视图上的操作不 影响底层映射的迭代顺序。 
 * 
 * 4.可以重写 removeEldestEntry(Map.Entry) 方法来实施策略，以便在将新映射关系添加到映射时自动移除旧的映射关系。 
 * 
 * 5.此类提供所有可选的 Map 操作，并且允许 null 元素。
 * 与 HashMap 一样，它可以为基本操作（add、contains 和 remove）提供稳定的性能，假定哈希函数将元素正确分布到桶中。
 * 由于增加了维护链接列表的开支，其性能很可能比 HashMap 稍逊一筹，
 * 不过这一点例外：LinkedHashMap 的 collection 视图迭代所需时间与映射的大小 成比例。
 * HashMap 迭代时间很可能开支较大，因为它所需要的时间与其容量 成比例。 
 * 
 * 6.链接的哈希映射具有两个影响其性能的参数：初始容量和加载因子。
 * 它们的定义与 HashMap 极其相似。要注意，为初始容量选择非常高的值对此类的影响比对 HashMap 要小，
 * 因为此类的迭代时间不受容量的影响。 
 * 
 * 7.注意，此实现不是同步的。如果多个线程同时访问链接的哈希映射，而其中至少一个线程从结构上修改了该映射，
 * 则它必须 保持外部同步。这一般通过对自然封装该映射的对象进行同步操作来完成。
 * 如果不存在这样的对象，则应该使用 Collections.synchronizedMap 方法来“包装”该映射。
 * 最好在创建时完成这一操作，以防止对映射的意外的非同步访问： 
 *     Map m = Collections.synchronizedMap(new LinkedHashMap(...));
 *     结构修改是指添加或删除一个或多个映射关系，或者在按访问顺序链接的哈希映射中影响迭代顺序的任何操作。
 *     在按插入顺序链接的哈希映射中，仅更改与映射中已包含键关联的值不是结构修改。
 *     在按访问顺序链接的哈希映射中，仅利用 get 查询映射不是结构修改。） 
 *     
 * 8.Collection（由此类的所有 collection 视图方法所返回）的 iterator 方法返回的迭代器都是快速失败 的：
 * 在迭代器创建之后，如果从结构上对映射进行修改，除非通过迭代器自身的 remove 方法，其他任何时间任何方式的修改，
 * 迭代器都将抛出 ConcurrentModificationException。因此，面对并发的修改，迭代器很快就会完全失败，
 * 而不冒将来不确定的时间任意发生不确定行为的风险。 
 * 
 * 9.注意，迭代器的快速失败行为无法得到保证，因为一般来说，不可能对是否出现不同步并发修改做出任何硬性保证。
 * 快速失败迭代器会尽最大努力抛出 ConcurrentModificationException。
 * 因此，编写依赖于此异常的程序的方式是错误的，正确做法是：迭代器的快速失败行为应该仅用于检测程序错误。 
 * 
 * 10.此类是 Java Collections Framework 的成员。
 * 
 * 
 */
public class LinkedHashMapTest<K, V> 
extends HashMapTest<K, V> 
implements Map<K, V> {

	/*
	 * Implementation note. A previous version of this class was internally
	 * structured a little differently. Because superclass HashMap now uses
	 * trees for some of its nodes, class LinkedHashMap.Entry is now treated as
	 * intermediary node class that can also be converted to tree form. The name
	 * of this class, LinkedHashMap.Entry, is confusing in several ways in its
	 * current context, but cannot be changed. Otherwise, even though it is not
	 * exported outside this package, some existing source code is known to have
	 * relied on a symbol resolution corner case rule in calls to
	 * removeEldestEntry that suppressed compilation errors due to ambiguous
	 * usages. So, we keep the name to preserve unmodified compilability.
	 *
	 * The changes in node classes also require using two fields (head, tail)
	 * rather than a pointer to a header node to maintain the doubly-linked
	 * before/after list. This class also previously used a different style of
	 * callback methods upon access, insertion, and removal.
	 */

	/**
	 * HashMap.Node subclass for normal LinkedHashMap entries.
	 */
	static class Entry<K, V> extends HashMapTest.Node<K, V> {
		Entry<K, V> before, after;

		Entry(int hash, K key, V value, Node<K, V> next) {
			super(hash, key, value, next);
		}
	}

	private static final long serialVersionUID = 3801124242820219131L;

	/**
	 * The head (eldest) of the doubly linked list.
	 */
	transient LinkedHashMapTest.Entry<K, V> head;

	/**
	 * The tail (youngest) of the doubly linked list.
	 */
	transient LinkedHashMapTest.Entry<K, V> tail;

	/**
	 * The iteration ordering method for this linked hash map: <tt>true</tt> for
	 * access-order, <tt>false</tt> for insertion-order.
	 *
	 * @serial
	 */
	final boolean accessOrder;

	// internal utilities

	// link at the end of list
	private void linkNodeLast(LinkedHashMapTest.Entry<K, V> p) {
		LinkedHashMapTest.Entry<K, V> last = tail;
		tail = p;
		if (last == null)
			head = p;
		else {
			p.before = last;
			last.after = p;
		}
	}

	// apply src's links to dst
	private void transferLinks(LinkedHashMapTest.Entry<K, V> src, LinkedHashMapTest.Entry<K, V> dst) {
		LinkedHashMapTest.Entry<K, V> b = dst.before = src.before;
		LinkedHashMapTest.Entry<K, V> a = dst.after = src.after;
		if (b == null)
			head = dst;
		else
			b.after = dst;
		if (a == null)
			tail = dst;
		else
			a.before = dst;
	}

	// overrides of HashMap hook methods

	void reinitialize() {
		super.reinitialize();
		head = tail = null;
	}

	Node<K, V> newNode(int hash, K key, V value, Node<K, V> e) {
		LinkedHashMapTest.Entry<K, V> p = new LinkedHashMapTest.Entry<K, V>(hash, key, value, e);
		linkNodeLast(p);
		return p;
	}

	Node<K, V> replacementNode(Node<K, V> p, Node<K, V> next) {
		LinkedHashMapTest.Entry<K, V> q = (LinkedHashMapTest.Entry<K, V>) p;
		LinkedHashMapTest.Entry<K, V> t = new LinkedHashMapTest.Entry<K, V>(q.hash, q.key, q.value, next);
		transferLinks(q, t);
		return t;
	}

	TreeNode<K, V> newTreeNode(int hash, K key, V value, Node<K, V> next) {
		TreeNode<K, V> p = new TreeNode<K, V>(hash, key, value, next);
		linkNodeLast(p);
		return p;
	}

	TreeNode<K, V> replacementTreeNode(Node<K, V> p, Node<K, V> next) {
		LinkedHashMapTest.Entry<K, V> q = (LinkedHashMapTest.Entry<K, V>) p;
		TreeNode<K, V> t = new TreeNode<K, V>(q.hash, q.key, q.value, next);
		transferLinks(q, t);
		return t;
	}

	void afterNodeRemoval(Node<K, V> e) { // unlink
		LinkedHashMapTest.Entry<K, V> p = (LinkedHashMapTest.Entry<K, V>) e, b = p.before, a = p.after;
		p.before = p.after = null;
		if (b == null)
			head = a;
		else
			b.after = a;
		if (a == null)
			tail = b;
		else
			a.before = b;
	}

	void afterNodeInsertion(boolean evict) { // possibly remove eldest
		LinkedHashMapTest.Entry<K, V> first;
		if (evict && (first = head) != null && removeEldestEntry(first)) {
			K key = first.key;
			removeNode(hash(key), key, null, false, true);
		}
	}

	void afterNodeAccess(Node<K, V> e) { // move node to last
		LinkedHashMapTest.Entry<K, V> last;
		if (accessOrder && (last = tail) != e) {
			LinkedHashMapTest.Entry<K, V> p = (LinkedHashMapTest.Entry<K, V>) e, b = p.before, a = p.after;
			p.after = null;
			if (b == null)
				head = a;
			else
				b.after = a;
			if (a != null)
				a.before = b;
			else
				last = b;
			if (last == null)
				head = p;
			else {
				p.before = last;
				last.after = p;
			}
			tail = p;
			++modCount;
		}
	}

	void internalWriteEntries(java.io.ObjectOutputStream s) throws IOException {
		for (LinkedHashMapTest.Entry<K, V> e = head; e != null; e = e.after) {
			s.writeObject(e.key);
			s.writeObject(e.value);
		}
	}

/**
 * Constructs an empty insertion-ordered <tt>LinkedHashMap</tt> instance
 * with the specified initial capacity and load factor.
 *
 * @param  initialCapacity the initial capacity
 * @param  loadFactor      the load factor
 * @throws IllegalArgumentException if the initial capacity is negative
 *         or the load factor is nonpositive
 */
public LinkedHashMapTest(int initialCapacity, float loadFactor) {
    super(initialCapacity, loadFactor);
    accessOrder = false;
}

/**
 * Constructs an empty insertion-ordered <tt>LinkedHashMap</tt> instance
 * with the specified initial capacity and a default load factor (0.75).
 *
 * @param  initialCapacity the initial capacity
 * @throws IllegalArgumentException if the initial capacity is negative
 */
public LinkedHashMapTest(int initialCapacity) {
    super(initialCapacity);
    accessOrder = false;
}

/**
 * Constructs an empty insertion-ordered <tt>LinkedHashMap</tt> instance
 * with the default initial capacity (16) and load factor (0.75).
 */
public LinkedHashMapTest() {
    super();
    accessOrder = false;
}

/**
 * Constructs an insertion-ordered <tt>LinkedHashMap</tt> instance with
 * the same mappings as the specified map.  The <tt>LinkedHashMap</tt>
 * instance is created with a default load factor (0.75) and an initial
 * capacity sufficient to hold the mappings in the specified map.
 *
 * @param  m the map whose mappings are to be placed in this map
 * @throws NullPointerException if the specified map is null
 */
public LinkedHashMapTest(Map<? extends K, ? extends V> m) {
    super();
    accessOrder = false;
    putMapEntries(m, false);
}

/**
 * Constructs an empty <tt>LinkedHashMap</tt> instance with the
 * specified initial capacity, load factor and ordering mode.
 *
 * @param  initialCapacity the initial capacity
 * @param  loadFactor      the load factor
 * @param  accessOrder     the ordering mode - <tt>true</tt> for
 *         access-order, <tt>false</tt> for insertion-order
 * @throws IllegalArgumentException if the initial capacity is negative
 *         or the load factor is nonpositive
 */
public LinkedHashMapTest(int initialCapacity,
                     float loadFactor,
                     boolean accessOrder) {
    super(initialCapacity, loadFactor);
    this.accessOrder = accessOrder;
}

	/**
	 * Returns <tt>true</tt> if this map maps one or more keys to the specified
	 * value.
	 *
	 * @param value
	 *            value whose presence in this map is to be tested
	 * @return <tt>true</tt> if this map maps one or more keys to the specified
	 *         value
	 */
	public boolean containsValue(Object value) {
		for (LinkedHashMapTest.Entry<K, V> e = head; e != null; e = e.after) {
			V v = e.value;
			if (v == value || (value != null && value.equals(v)))
				return true;
		}
		return false;
	}

	/**
	 * Returns the value to which the specified key is mapped, or {@code null}
	 * if this map contains no mapping for the key.
	 *
	 * <p>
	 * More formally, if this map contains a mapping from a key {@code k} to a
	 * value {@code v} such that {@code (key==null ? k==null :
	 * key.equals(k))}, then this method returns {@code v}; otherwise it returns
	 * {@code null}. (There can be at most one such mapping.)
	 *
	 * <p>
	 * A return value of {@code null} does not <i>necessarily</i> indicate that
	 * the map contains no mapping for the key; it's also possible that the map
	 * explicitly maps the key to {@code null}. The {@link #containsKey
	 * containsKey} operation may be used to distinguish these two cases.
	 */
	public V get(Object key) {
		Node<K, V> e;
		if ((e = getNode(hash(key), key)) == null)
			return null;
		if (accessOrder)
			afterNodeAccess(e);
		return e.value;
	}

	/**
	 * {@inheritDoc}
	 */
	public V getOrDefault(Object key, V defaultValue) {
		Node<K, V> e;
		if ((e = getNode(hash(key), key)) == null)
			return defaultValue;
		if (accessOrder)
			afterNodeAccess(e);
		return e.value;
	}

	/**
	 * {@inheritDoc}
	 */
	public void clear() {
		super.clear();
		head = tail = null;
	}

	/**
	 * Returns <tt>true</tt> if this map should remove its eldest entry. This
	 * method is invoked by <tt>put</tt> and <tt>putAll</tt> after inserting a
	 * new entry into the map. It provides the implementor with the opportunity
	 * to remove the eldest entry each time a new one is added. This is useful
	 * if the map represents a cache: it allows the map to reduce memory
	 * consumption by deleting stale entries.
	 *
	 * <p>
	 * Sample use: this override will allow the map to grow up to 100 entries
	 * and then delete the eldest entry each time a new entry is added,
	 * maintaining a steady state of 100 entries.
	 * 
	 * <pre>
	 * private static final int MAX_ENTRIES = 100;
	 *
	 * protected boolean removeEldestEntry(Map.Entry eldest) {
	 * 	return size() &gt; MAX_ENTRIES;
	 * }
	 * </pre>
	 *
	 * <p>
	 * This method typically does not modify the map in any way, instead
	 * allowing the map to modify itself as directed by its return value. It
	 * <i>is</i> permitted for this method to modify the map directly, but if it
	 * does so, it <i>must</i> return <tt>false</tt> (indicating that the map
	 * should not attempt any further modification). The effects of returning
	 * <tt>true</tt> after modifying the map from within this method are
	 * unspecified.
	 *
	 * <p>
	 * This implementation merely returns <tt>false</tt> (so that this map acts
	 * like a normal map - the eldest element is never removed).
	 *
	 * @param eldest
	 *            The least recently inserted entry in the map, or if this is an
	 *            access-ordered map, the least recently accessed entry. This is
	 *            the entry that will be removed it this method returns
	 *            <tt>true</tt>. If the map was empty prior to the <tt>put</tt>
	 *            or <tt>putAll</tt> invocation resulting in this invocation,
	 *            this will be the entry that was just inserted; in other words,
	 *            if the map contains a single entry, the eldest entry is also
	 *            the newest.
	 * @return <tt>true</tt> if the eldest entry should be removed from the map;
	 *         <tt>false</tt> if it should be retained.
	 */
	protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
		return false;
	}

	/**
	 * Returns a {@link Set} view of the keys contained in this map. The set is
	 * backed by the map, so changes to the map are reflected in the set, and
	 * vice-versa. If the map is modified while an iteration over the set is in
	 * progress (except through the iterator's own <tt>remove</tt> operation),
	 * the results of the iteration are undefined. The set supports element
	 * removal, which removes the corresponding mapping from the map, via the
	 * <tt>Iterator.remove</tt>, <tt>Set.remove</tt>, <tt>removeAll</tt>,
	 * <tt>retainAll</tt>, and <tt>clear</tt> operations. It does not support
	 * the <tt>add</tt> or <tt>addAll</tt> operations. Its {@link Spliterator}
	 * typically provides faster sequential performance but much poorer parallel
	 * performance than that of {@code HashMap}.
	 *
	 * @return a set view of the keys contained in this map
	 */
	public Set<K> keySet() {
		Set<K> ks;
		return (ks = keySet) == null ? (keySet = new LinkedKeySet()) : ks;
	}

	final class LinkedKeySet extends AbstractSet<K> {
		public final int size() {
			return size;
		}

		public final void clear() {
			LinkedHashMapTest.this.clear();
		}

		public final Iterator<K> iterator() {
			return new LinkedKeyIterator();
		}

		public final boolean contains(Object o) {
			return containsKey(o);
		}

		public final boolean remove(Object key) {
			return removeNode(hash(key), key, null, false, true) != null;
		}

		public final Spliterator<K> spliterator() {
			return Spliterators.spliterator(this, Spliterator.SIZED | Spliterator.ORDERED | Spliterator.DISTINCT);
		}

		public final void forEach(Consumer<? super K> action) {
			if (action == null)
				throw new NullPointerException();
			int mc = modCount;
			for (LinkedHashMapTest.Entry<K, V> e = head; e != null; e = e.after)
				action.accept(e.key);
			if (modCount != mc)
				throw new ConcurrentModificationException();
		}
	}

	/**
	 * Returns a {@link Collection} view of the values contained in this map.
	 * The collection is backed by the map, so changes to the map are reflected
	 * in the collection, and vice-versa. If the map is modified while an
	 * iteration over the collection is in progress (except through the
	 * iterator's own <tt>remove</tt> operation), the results of the iteration
	 * are undefined. The collection supports element removal, which removes the
	 * corresponding mapping from the map, via the <tt>Iterator.remove</tt>,
	 * <tt>Collection.remove</tt>, <tt>removeAll</tt>, <tt>retainAll</tt> and
	 * <tt>clear</tt> operations. It does not support the <tt>add</tt> or
	 * <tt>addAll</tt> operations. Its {@link Spliterator} typically provides
	 * faster sequential performance but much poorer parallel performance than
	 * that of {@code HashMap}.
	 *
	 * @return a view of the values contained in this map
	 */
	public Collection<V> values() {
		Collection<V> vs;
		return (vs = values) == null ? (values = new LinkedValues()) : vs;
	}

	final class LinkedValues extends AbstractCollection<V> {
		public final int size() {
			return size;
		}

		public final void clear() {
			LinkedHashMapTest.this.clear();
		}

		public final Iterator<V> iterator() {
			return new LinkedValueIterator();
		}

		public final boolean contains(Object o) {
			return containsValue(o);
		}

		public final Spliterator<V> spliterator() {
			return Spliterators.spliterator(this, Spliterator.SIZED | Spliterator.ORDERED);
		}

		public final void forEach(Consumer<? super V> action) {
			if (action == null)
				throw new NullPointerException();
			int mc = modCount;
			for (LinkedHashMapTest.Entry<K, V> e = head; e != null; e = e.after)
				action.accept(e.value);
			if (modCount != mc)
				throw new ConcurrentModificationException();
		}
	}

	/**
	 * Returns a {@link Set} view of the mappings contained in this map. The set
	 * is backed by the map, so changes to the map are reflected in the set, and
	 * vice-versa. If the map is modified while an iteration over the set is in
	 * progress (except through the iterator's own <tt>remove</tt> operation, or
	 * through the <tt>setValue</tt> operation on a map entry returned by the
	 * iterator) the results of the iteration are undefined. The set supports
	 * element removal, which removes the corresponding mapping from the map,
	 * via the <tt>Iterator.remove</tt>, <tt>Set.remove</tt>, <tt>removeAll</tt>
	 * , <tt>retainAll</tt> and <tt>clear</tt> operations. It does not support
	 * the <tt>add</tt> or <tt>addAll</tt> operations. Its {@link Spliterator}
	 * typically provides faster sequential performance but much poorer parallel
	 * performance than that of {@code HashMap}.
	 *
	 * @return a set view of the mappings contained in this map
	 */
	public Set<Map.Entry<K, V>> entrySet() {
		Set<Map.Entry<K, V>> es;
		return (es = entrySet) == null ? (entrySet = new LinkedEntrySet()) : es;
	}

	final class LinkedEntrySet extends AbstractSet<Map.Entry<K, V>> {
		public final int size() {
			return size;
		}

		public final void clear() {
			LinkedHashMapTest.this.clear();
		}

		public final Iterator<Map.Entry<K, V>> iterator() {
			return new LinkedEntryIterator();
		}

		public final boolean contains(Object o) {
			if (!(o instanceof Map.Entry))
				return false;
			Map.Entry<?, ?> e = (Map.Entry<?, ?>) o;
			Object key = e.getKey();
			Node<K, V> candidate = getNode(hash(key), key);
			return candidate != null && candidate.equals(e);
		}

		public final boolean remove(Object o) {
			if (o instanceof Map.Entry) {
				Map.Entry<?, ?> e = (Map.Entry<?, ?>) o;
				Object key = e.getKey();
				Object value = e.getValue();
				return removeNode(hash(key), key, value, true, true) != null;
			}
			return false;
		}

		public final Spliterator<Map.Entry<K, V>> spliterator() {
			return Spliterators.spliterator(this, Spliterator.SIZED | Spliterator.ORDERED | Spliterator.DISTINCT);
		}

		public final void forEach(Consumer<? super Map.Entry<K, V>> action) {
			if (action == null)
				throw new NullPointerException();
			int mc = modCount;
			for (LinkedHashMapTest.Entry<K, V> e = head; e != null; e = e.after)
				action.accept(e);
			if (modCount != mc)
				throw new ConcurrentModificationException();
		}
	}

	// Map overrides

	public void forEach(BiConsumer<? super K, ? super V> action) {
		if (action == null)
			throw new NullPointerException();
		int mc = modCount;
		for (LinkedHashMapTest.Entry<K, V> e = head; e != null; e = e.after)
			action.accept(e.key, e.value);
		if (modCount != mc)
			throw new ConcurrentModificationException();
	}

	public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {
		if (function == null)
			throw new NullPointerException();
		int mc = modCount;
		for (LinkedHashMapTest.Entry<K, V> e = head; e != null; e = e.after)
			e.value = function.apply(e.key, e.value);
		if (modCount != mc)
			throw new ConcurrentModificationException();
	}

	// Iterators

	abstract class LinkedHashIterator {
		LinkedHashMapTest.Entry<K, V> next;
		LinkedHashMapTest.Entry<K, V> current;
		int expectedModCount;

		LinkedHashIterator() {
			next = head;
			expectedModCount = modCount;
			current = null;
		}

		public final boolean hasNext() {
			return next != null;
		}

		final LinkedHashMapTest.Entry<K, V> nextNode() {
			LinkedHashMapTest.Entry<K, V> e = next;
			if (modCount != expectedModCount)
				throw new ConcurrentModificationException();
			if (e == null)
				throw new NoSuchElementException();
			current = e;
			next = e.after;
			return e;
		}

		public final void remove() {
			Node<K, V> p = current;
			if (p == null)
				throw new IllegalStateException();
			if (modCount != expectedModCount)
				throw new ConcurrentModificationException();
			current = null;
			K key = p.key;
			removeNode(hash(key), key, null, false, false);
			expectedModCount = modCount;
		}
	}

	final class LinkedKeyIterator extends LinkedHashIterator implements Iterator<K> {
		public final K next() {
			return nextNode().getKey();
		}
	}

	final class LinkedValueIterator extends LinkedHashIterator implements Iterator<V> {
		public final V next() {
			return nextNode().value;
		}
	}

	final class LinkedEntryIterator extends LinkedHashIterator implements Iterator<Map.Entry<K, V>> {
		public final Map.Entry<K, V> next() {
			return nextNode();
		}
	}

}
