package com.yeluo.mvn.ch2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
/**
 * 
 * @author YeLuo
 * @function  测试ArrayList类的add方法的源码
 * @param <E>
 */
public class ArrayListTest1<E> {
	 //默认容量
	 private static final int DEFAULT_CAPACITY = 10;  
	 //用于空实例的共享空数组实例
	 private static final Object[] EMPTY_ELEMENTDATA = {};
	 //默认容量的空对象数组
	 private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
	 //对象数组变量
	 transient Object[] elementData; // non-private to simplify nested class access
	 //元素个数
	 private int size;
	 /*
	  *用来表示已在结构上修改此列表的次数。结构上的修改是指改变列表的大小，或其他方式，遍历过程中可能产生不正确的结果。
	  */
	 protected transient int modCount = 0;
	 //最大的数组长度   2147483647-8=2147483639    为什么减去8?
	 private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
	 
	 
	 public ArrayListTest1() {
		 	//默认容量的空对象数组
	        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
	    }
	 
	 /**
	     * Constructs a list containing the elements of the specified
	     * collection, in the order they are returned by the collection's
	     * iterator.
	     *
	     * @param c the collection whose elements are to be placed into this list
	     * @throws NullPointerException if the specified collection is null
	     */
	    public ArrayListTest1(Collection<? extends E> c) {
	        elementData = c.toArray();
	        if ((size = elementData.length) != 0) {
	            // c.toArray might (incorrectly) not return Object[] (see 6260652)
	            if (elementData.getClass() != Object[].class)
	                elementData = Arrays.copyOf(elementData, size, Object[].class);
	        } else {
	            // replace with empty array.
	            this.elementData = EMPTY_ELEMENTDATA;
	        }
	    }
	    
	    /**
	     * Increases the capacity of this <tt>ArrayList</tt> instance, if
	     * necessary, to ensure that it can hold at least the number of elements
	     * specified by the minimum capacity argument.
	     *
	     * @param   minCapacity   the desired minimum capacity
	     */
	    public void ensureCapacity(int minCapacity) {
	        int minExpand = (elementData != DEFAULTCAPACITY_EMPTY_ELEMENTDATA)
	            // any size if not default element table
	            ? 0
	            // larger than default for default empty table. It's already
	            // supposed to be at default size.
	            : DEFAULT_CAPACITY;
	        System.out.println("minExpand:"+minExpand+"  minCapacity:"+minCapacity);
	        if (minCapacity > minExpand) {
	            ensureExplicitCapacity(minCapacity);
	        }
	    }
	 
	 //一系列添加方法
	 /**
	  * 在指定位置处插入集合的元素
	  * @param index
	  * @param c
	  * @return
	  * 1.用rangeCheckForAdd方法检查该位置的合理性
	  * --只有在集合中出现索引都要进行该步骤
	  * 2.将要添加的集合转换为数组
	  * 3.检查当前集合是否可以容纳新添加的元素
	  * 4.拷贝原来的元素到合理的位置
	  * 5.拷贝要插入数组的元素
	  * 6.改变当前集合的元素个数
	  * 
	  */
	 public boolean addAll(int index, Collection<? extends E> c) {
		 //判断索引是否越界
	        rangeCheckForAdd(index);

	        Object[] a = c.toArray();
	        int numNew = a.length;
	        ensureCapacityInternal(size + numNew);  // Increments modCount

	        int numMoved = size - index;
	        if (numMoved > 0)
	            System.arraycopy(elementData, index, elementData, index + numNew,
	                             numMoved);

	        System.arraycopy(a, 0, elementData, index, numNew);
	        size += numNew;
	        return numNew != 0;
	    }
	 /**
	     * A version of rangeCheck used by add and addAll.
	     * 判断数组的索引是否在0~size之间
	     */
	    private void rangeCheckForAdd(int index) {
	        if (index > size || index < 0)
	            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
	    }
	    /**
	     * Constructs an IndexOutOfBoundsException detail message.
	     * Of the many possible refactorings of the error handling code,
	     * this "outlining" performs best with both server and client VMs.
	     */
	    private String outOfBoundsMsg(int index) {
	        return "Index: "+index+", Size: "+size;
	    }
	    
	 /**
	  * 
	  * @param c
	  * @return
	  * 利用Collection<? extends E>
	  * 已经限制要添加的集合元素只能是当前元素类型的本身或子类
	  * 
	  * 1.首先将要添加的集合转换为数组
	  * 2.检查当前集合是否可以容纳新添加的元素
	  * 3.利用System类的arraycopy方法将要添加的新数组拷贝到原数组中
	  * 4.改变当前集合的元素个数
	  */
	 public boolean addAll(Collection<? extends E> c) {
	        Object[] a = c.toArray();
	        int numNew = a.length;
	        ensureCapacityInternal(size + numNew);  // Increments modCount
	        System.arraycopy(a, 0, elementData, size, numNew);
	        size += numNew;
	        return numNew != 0;
	    }
	
	 /**
     * Appends the specified element to the end of this list.
     *
     * @param e element to be appended to this list
     * @return <tt>true</tt> (as specified by {@link Collection#add})
     * 
     * 1.在添加元素之前,检查可容纳的容器是否可以容纳
     * 
     * 2.添加元素
     */
    public boolean add(E e) {
    	//确保内部容量
        ensureCapacityInternal(size + 1);  // Increments modCount!!
        elementData[size++] = e;
        System.out.println(size);
        return true;
    }
    /**
     * 确保内部容量
     * @param minCapacity  当前元素数量
     */
    private void ensureCapacityInternal(int minCapacity) {
    	//判断是否为空对象数组
        if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
        	System.out.println("到此一游!");
        	System.out.println("minCapacity:"+minCapacity);
            minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);        
        }
        //确保明确的容量
        ensureExplicitCapacity(minCapacity);
    }
    /**
     * 确保明确的容量
     * @param minCapacity  当前元素数量
     * elementData.length  当前可容纳元素的数量
     */
    private void ensureExplicitCapacity(int minCapacity) {
        modCount++;

        // overflow-conscious code
        if (minCapacity - elementData.length > 0){
        	System.out.println("elementData:"+elementData.length);
        	System.out.println("minCapacity:"+minCapacity);
        	grow(minCapacity);
        }
            
    }
    /**
     * Increases the capacity to ensure that it can hold at least the
     * number of elements specified by the minimum capacity argument.
     * 增加容量--增加原来容量的一半
     * @param minCapacity the desired minimum capacity
     */
    private void grow(int minCapacity) {
        // overflow-conscious code
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        System.out.println("oldCapacity >> 1:"+(oldCapacity >> 1));
        if (newCapacity - minCapacity < 0)
            newCapacity = minCapacity;
        if (newCapacity - MAX_ARRAY_SIZE > 0)
            newCapacity = hugeCapacity(minCapacity);
        // minCapacity is usually close to size, so this is a win:
        elementData = Arrays.copyOf(elementData, newCapacity);
    }
    
    private static int hugeCapacity(int minCapacity) {
    	//溢出
        if (minCapacity < 0) // overflow
            throw new OutOfMemoryError();
        return (minCapacity > MAX_ARRAY_SIZE) ?
            Integer.MAX_VALUE :
            MAX_ARRAY_SIZE;
    }
    
    //一系列移除方法
    /**
     * Removes the element at the specified position in this list.
     * Shifts any subsequent elements to the left (subtracts one from their
     * indices).
     *
     * @param index the index of the element to be removed
     * @return the element that was removed from the list
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public E remove(int index) {
        rangeCheck(index);

        modCount++;
        E oldValue = elementData(index);

        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(elementData, index+1, elementData, index,
                             numMoved);
        elementData[--size] = null; // clear to let GC do its work

        return oldValue;
    }
    /**
     * Checks if the given index is in range.  If not, throws an appropriate
     * runtime exception.  This method does *not* check if the index is
     * negative: It is always used immediately prior to an array access,
     * which throws an ArrayIndexOutOfBoundsException if index is negative.
     */
    private void rangeCheck(int index) {
        if (index >= size)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }
    @SuppressWarnings("unchecked")
    E elementData(int index) {
        return (E) elementData[index];
    }
    public Object[] toArray() {
        return Arrays.copyOf(elementData, size);
    }
    public static void main(String[] args) {
		ArrayListTest1<Integer> test=new ArrayListTest1<>();
		
		test.ensureCapacity(12);
		System.out.println(test.elementData.length);
		System.out.println(test.toArray().length);
		/*int count=0;
		for(int i=0;i<10;i++){
			test.add(count++);
			System.out.println();
		}
		
		for(int i=0;i<10;i++){
			test.add(count++);
			System.out.println();
		}
		ArrayListTest1<String> a=new ArrayListTest1<>();
		
		System.out.println(a.elementData.getClass());  //class [Ljava.lang.Object;
		System.out.println(Object[].class);  //class [Ljava.lang.Object;
		
		System.out.println(DEFAULTCAPACITY_EMPTY_ELEMENTDATA.length);//0
*/		
	}
}
