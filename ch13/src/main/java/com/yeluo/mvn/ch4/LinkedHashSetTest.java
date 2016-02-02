package com.yeluo.mvn.ch4;

import java.util.Collection;
import java.util.Set;
import java.util.Spliterator;
import java.util.Spliterators;

public class LinkedHashSetTest<E> 
extends HashSetTest<E> 
implements Set<E>, Cloneable, java.io.Serializable {

	private static final long serialVersionUID = -2851667679971038690L;

	/**
	 * Constructs a new, empty linked hash set with the specified initial
	 * capacity and load factor.
	 *
	 * @param      initialCapacity the initial capacity of the linked hash set
	 * @param      loadFactor      the load factor of the linked hash set
	 * @throws     IllegalArgumentException  if the initial capacity is less
	 *               than zero, or if the load factor is nonpositive
	 */
	public LinkedHashSetTest(int initialCapacity, float loadFactor) {
		super(initialCapacity, loadFactor, true);
	}

	/**
	 * Constructs a new, empty linked hash set with the specified initial
	 * capacity and the default load factor (0.75).
	 *
	 * @param   initialCapacity   the initial capacity of the LinkedHashSet
	 * @throws  IllegalArgumentException if the initial capacity is less
	 *              than zero
	 */
	public LinkedHashSetTest(int initialCapacity) {
		super(initialCapacity, .75f, true);
	}

	/**
	 * Constructs a new, empty linked hash set with the default initial
	 * capacity (16) and load factor (0.75).
	 */
	public LinkedHashSetTest() {
		super(16, .75f, true);
	}

	/**
	 * Constructs a new linked hash set with the same elements as the
	 * specified collection.  The linked hash set is created with an initial
	 * capacity sufficient to hold the elements in the specified collection
	 * and the default load factor (0.75).
	 *
	 * @param c  the collection whose elements are to be placed into
	 *           this set
	* @throws NullPointerException if the specified collection is null
	*/
	public LinkedHashSetTest(Collection<? extends E> c) {
		super(Math.max(2*c.size(), 11), .75f, true);
		addAll(c);
	}

	/**
	 * Creates a <em><a href="Spliterator.html#binding">late-binding</a></em>
	 * and <em>fail-fast</em> {@code Spliterator} over the elements in this set.
	 *
	 * <p>
	 * The {@code Spliterator} reports {@link Spliterator#SIZED},
	 * {@link Spliterator#DISTINCT}, and {@code ORDERED}. Implementations should
	 * document the reporting of additional characteristic values.
	 *
	 * @implNote The implementation creates a
	 *           <em><a href="Spliterator.html#binding">late-binding</a></em>
	 *           spliterator from the set's {@code Iterator}. The spliterator
	 *           inherits the <em>fail-fast</em> properties of the set's
	 *           iterator. The created {@code Spliterator} additionally reports
	 *           {@link Spliterator#SUBSIZED}.
	 *
	 * @return a {@code Spliterator} over the elements in this set
	 * @since 1.8
	 */
	@Override
	public Spliterator<E> spliterator() {
		return Spliterators.spliterator(this, Spliterator.DISTINCT | Spliterator.ORDERED);
	}
}
