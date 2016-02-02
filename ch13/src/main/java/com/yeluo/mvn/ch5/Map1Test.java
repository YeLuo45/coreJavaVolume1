package com.yeluo.mvn.ch5;
/**
 * 
 * @author YeLuo
 * @function map(映射表)
 */
/**
 * 映射集
 * 1.映射表用来存放键值对.
 * 
 * 2.散列映射表对键进行散列,树映射表用键的整体顺序对元素进行排序,并将其组织成搜索树.
 * 散列或比较函数只能作用于键.与键关联的值不能进行散列或比较.
 * 注释:如果不需要按照排列顺序访问键,就最好选择散列(稍微快一些)
 * 
 * 3.键必须是唯一的.不能对同一个键存放两个值.
 * 
 * 4.map集合有三个视图:键集、值集合(不是集)和键/值对集.
 * Set<K> keySet();
 * Collection<K> values();
 * Set<Map.Entry<K,V>> entrySet();
 * 注意:keySet既不是HashSet,也不是TreeSet,而是实现了Set接口的某个其他类的对象.
 * 
 * 专用集与映射表类
 * 5.WeakHashMap(弱散列映射表)
 * WeakHashMap的作用:删除从长期存活的映射表中删除那些无用的值.
 * 
 * 6.LinkedHashSet(链接散列集)和LinkedHashMap(链接映射表)
 * LinkedHashSet和LinkedHashMap,用来记住插入元素项的顺序.
 * 这样就可以避免在散列表中的项从表面上看是随机排列的.当条目插入到表中时,就会并入双向链表中.
 * 
 * 7.LinkedHashMap将用于访问顺序,而不是插入顺序,对映射表条目进行迭代.
 * 访问顺序对于实现高速缓存的"最近很少使用"原则十分重要.
 * 
 * 8.枚举集与映射表
 * EnumSet是一个枚举类型元素集的高效实现.由于枚举类型只有有限个实例,所以EnumSet内部用位序列实现.
 * 如果对应的值在集中,则相应的位被置为1.
 * E怒骂Map是一个键类型为枚举类型的映射表.它可以直接且高效地用一个值数组实现.在使用时,需要在构造器中指定键类型.
 * 
 * 9.IndentityHashMap(标识散列映射表)
 * 这个类的键的散列值不是用hashCode函数计算的,而是用System.identityHashCode方法计算的.这是Object.hashCode方法
 * 根据对象的内存地址来计算散列码时所使用的方式.而且,在对两个对象进行比较时,IndentityHashMap类使用==,而不是equals.
 * 
 * 
 */
public class Map1Test {
	public static void main(String[] args) {
		
	}
}
