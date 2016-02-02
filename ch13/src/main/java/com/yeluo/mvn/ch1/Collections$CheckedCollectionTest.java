package com.yeluo.mvn.ch1;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;
/**
 * 
 * @author YeLuo
 * @function
 * @param <E>
 */
/**
 * 1.返回指定 collection 的一个动态类型安全视图。试图插入一个错误类型的元素将导致立即抛出 ClassCastException。
 * 假设在生成动态类型安全视图之前，collection 不包含任何类型不正确的元素，
 * 并且所有对该 collection 的后续访问都通过该视图进行，则可以保证 该 collection 不包含类型不正确的元素。 
 * 一般的编程语言机制中都提供了编译时（静态）类型检查，但是一些未经检查的强制转换可能会使此机制无效。
 * 
 * 2.通常这不是一个问题，因为编译器会在所有这类未经检查的操作上发出警告。
 * 但有的时候，只进行单独的静态类型检查并不够。
 * 例如，假设将 collection 传递给一个第三方库，则库代码不能通过插入一个错误类型的元素来毁坏 collection。 
 * 
 * 3.动态类型安全视图的另一个用途是调试。假设某个程序运行失败并抛出 ClassCastException，
 * 这指示一个类型不正确的元素被放入已参数化 collection 中。
 * 不幸的是，该异常可以发生在插入错误元素之后的任何时间，
 * 因此，这通常只能提供很少或无法提供任何关于问题真正来源的信息。
 * 如果问题是可再现的，那么可以暂时修改程序，使用一个动态类型安全视图来包装该 collection，
 * 通过这种方式可快速确定问题的来源。例如，以下声明： 
 *      Collection<String> c = new HashSet<String>();
 *  可以暂时用下面的声明代替： 
 *      Collection<String> c = Collections.checkedCollection(
 *          new HashSet<String>(), String.class);
 *  再次运行程序会造成它在将类型不正确的元素插入 collection 的地方失败，从而清楚地识别问题的来源。
 *  问题得以解决后，可以将修改后的声明转换回原来的声明。 
 * 
 * 4.返回的 collection 不会 将 hashCode 和 equals 操作传递给底层实现 collection，
 * 但这依赖于 Object 的 equals 和 hashCode 方法。
 * 在底层实现 collection 是一个 set 或一个列表的情况下，有必要遵守这些操作的协定。 
 * 
 * 5.如果指定 collection 是可序列化的，则返回的 collection 也将是可序列化的。 
 * 
 * 6.参数：
 * c - 将返回其动态类型安全视图的 collection
 * type - 允许 c 持有的元素类型 
 * 返回：
 * 指定 collection 的动态安全类型视图
 * 
 */
public class Collections$CheckedCollectionTest<E> implements Collection<E>, Serializable {
    private static final long serialVersionUID = 1578914078182001775L;

    //将返回其动态类型安全视图的 collection
    final Collection<E> c;
    //允许 c 持有的元素类型 
    final Class<E> type;

    /**
     * 类型检测
     * @param o
     * @return
     */
    @SuppressWarnings("unchecked")
    E typeCheck(Object o) {
        if (o != null && !type.isInstance(o))
            throw new ClassCastException(badElementMsg(o));
        return (E) o;
    }

   /**
    * 返回错误的元素类型信息
    * @param o
    * @return
    */
    private String badElementMsg(Object o) {
        return "Attempt to insert " + o.getClass() +
            " element into collection with element type " + type;
    }

    /**
     * 构造方法要检测传入的参数是否为null
     * @param c
     * @param type
     */
    Collections$CheckedCollectionTest(Collection<E> c, Class<E> type) {
        this.c = Objects.requireNonNull(c, "c");
        this.type = Objects.requireNonNull(type, "type");
    }

    /**
     * 直接调用父集合的相应方法
     */
    public int size()                 { return c.size(); }
    public boolean isEmpty()          { return c.isEmpty(); }
    public boolean contains(Object o) { return c.contains(o); }
    public Object[] toArray()         { return c.toArray(); }
    public <T> T[] toArray(T[] a)     { return c.toArray(a); }
    public String toString()          { return c.toString(); }
    public boolean remove(Object o)   { return c.remove(o); }
    public void clear()               {        c.clear(); }

    public boolean containsAll(Collection<?> coll) {
        return c.containsAll(coll);
    }
    public boolean removeAll(Collection<?> coll) {
        return c.removeAll(coll);
    }
    public boolean retainAll(Collection<?> coll) {
        return c.retainAll(coll);
    }
    /**
     * 直接调用父集合的迭代器方法
     */
    public Iterator<E> iterator() {
        // JDK-6363904 - unwrapped iterator could be typecast to
        // ListIterator with unsafe set()
    	//注意:LIstIterator迭代器没有带安全的set方法
        final Iterator<E> it = c.iterator();
        return new Iterator<E>() {
            public boolean hasNext() { return it.hasNext(); }
            public E next()          { return it.next(); }
            public void remove()     {        it.remove(); }};
    }

    /**
     * 添加元素
     * 注意:再添加元素前,先检测要添加的元素类型是否合理.
     */
    public boolean add(E e)          { return c.add(typeCheck(e)); }

    //懒加载
    private E[] zeroLengthElementArray; // Lazily initialized

    private E[] zeroLengthElementArray() {
        return zeroLengthElementArray != null ? zeroLengthElementArray :
            (zeroLengthElementArray = zeroLengthArray(type));
    }
    /**
     * 返回一个指定类型的、长度为0的数组
     * @param type
     * @return
     */
    @SuppressWarnings("unchecked")
    static <T> T[] zeroLengthArray(Class<T> type) {
    	/*
    	 * Object java.lang.reflect.Array.newInstance(Class<?> componentType, int length)
    	 *  throws NegativeArraySizeException
    	 */
        return (T[]) Array.newInstance(type, 0);
    }

    /**
     * 注意点:
     * 1.检测指定集合的toArray方法是否违反了toArray方法的协议
     * ??????
     * @param coll
     * @return
     */
    @SuppressWarnings("unchecked")
    Collection<E> checkedCopyOf(Collection<? extends E> coll) {
        Object[] a;
        try {
            E[] z = zeroLengthElementArray();
            a = coll.toArray(z);
            // Defend against coll violating the toArray contract
            //当coll对象的toArray方法违反了toArray方法的协议
            if (a.getClass() != z.getClass())
                a = Arrays.copyOf(a, a.length, z.getClass());
        } catch (ArrayStoreException ignore) {
            // To get better and consistent diagnostics,
            // we call typeCheck explicitly on each element.
            // We call clone() to defend against coll retaining a
            // reference to the returned array and storing a bad
            // element into it after it has been type checked.
        	/*
        	 * 为了得到更好的和一致的诊断，我们调用typeCheck方法明确每个元素。
        	 * 我们调用clone方法来保护coll集合对象来保留引用返回的数组存储一个错误的元素进去后已进行类型检查。
        	 */
        	//浅克隆
            a = coll.toArray().clone();
            for (Object o : a)
                typeCheck(o);
        }
        // A slight abuse of the type system, but safe here.
        return (Collection<E>) Arrays.asList(a);
    }

    /**
     * 批量添加元素
     * 注意:再添加元素前,先检测要添加的元素类型是否合理.
     */
    public boolean addAll(Collection<? extends E> coll) {
        // Doing things this way insulates us from concurrent changes
        // in the contents of coll and provides all-or-nothing
        // semantics (which we wouldn't get if we type-checked each
        // element as we added it)
        return c.addAll(checkedCopyOf(coll));
    }

    // Override default methods in Collection
    @Override
    public void forEach(Consumer<? super E> action) {c.forEach(action);}
    @Override
    public boolean removeIf(Predicate<? super E> filter) {
        return c.removeIf(filter);
    }
    @Override
    public Spliterator<E> spliterator() {return c.spliterator();}
    @Override
    public Stream<E> stream()           {return c.stream();}
    @Override
    public Stream<E> parallelStream()   {return c.parallelStream();}
}
