package com.yeluo.mvn.ch1;
/**
 * 
 * @author YeLuo
 * @function  集合
 */
/**
 * 1.集合有两个基本的接口:Collection和Map
 * 
 * 2.LIst是一个有序集合.元素可以添加到容器中某个特定的位置.	
 * 将对象放置在某个位置上可以采用两种方式:使用整数索引或使用列表迭代器.
 *
 * 3.RandomAccess接口(标记接口)没有任何方法,
 * 但可以用来检测一个特定的集合是否支持高效的随机访问.
 *
 * 4.Set接口和Collection接口是一样的,只是其方法的行为有着更加严谨的定义.
 * 既然方法签名是相同的,为什么还要建立独立的接口呢?从概念上讲,并不是所有集合都是集.
 * 建立Set接口后,可以让程序员编写仅接受集的方法.
 * 
 * 5.SortedSet和SortedMap接口暴露了用于排序的比较器对象,并且定义了方法可以获得集合的子集视图.
 * 
 * 6.NavigableSet和NavigableMap,包含了几个用于在有序集和映射表中查找和遍历的方法.
 */
public class CollectionTest2 {

}
