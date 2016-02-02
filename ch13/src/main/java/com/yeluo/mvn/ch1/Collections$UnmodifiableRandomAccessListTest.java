package com.yeluo.mvn.ch1;

import java.util.List;
import java.util.RandomAccess;
/**
 * 
 * @author YeLuo
 * @function 对Collections$UnmodifiableRandomAccessList的源码解析
 * @param <E>
 */

public class Collections$UnmodifiableRandomAccessListTest<E> 
extends Collections$UnmodifiableListTest<E> 
implements RandomAccess {
	Collections$UnmodifiableRandomAccessListTest(List<? extends E> list) {
		super(list);
	}

	public List<E> subList(int fromIndex, int toIndex) {
		return new Collections$UnmodifiableRandomAccessListTest<>(list.subList(fromIndex, toIndex));
	}

	private static final long serialVersionUID = -2542308836966382001L;

	/**
	 * Allows instances to be deserialized in pre-1.4 JREs (which do not have
	 * UnmodifiableRandomAccessList). UnmodifiableList has a readResolve method
	 * that inverts this transformation upon deserialization.
	 */
	private Object writeReplace() {
		return new Collections$UnmodifiableListTest<>(list);
	}
}