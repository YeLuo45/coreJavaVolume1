package com.yeluo.mvn.ch2;

import java.util.ConcurrentModificationException;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Consumer;
/**
 * 
 * @author YeLuo
 * @function 测试LinkedList$ListItr
 * 属于ListItr外部类LinkedList的属性或方法--用LL做标记
 */

public class ListItrTest<E> implements ListIterator<E>{
	  private Node<E> lastReturned;  //最新的返回结点
      private Node<E> next;   	//下个结点
      //
      private int nextIndex;  	//下一个索引(LL)
      private int modCount;   	//记录结构性修改的次数(LL)
      private int size;			//集合的元素个数(LL)
      
      private int expectedModCount = modCount;  //用来判断迭代过程中是否有其他类的对象修改的变量
      
      /**
       * Pointer to first node.(LL)
       * Invariant: (first == null && last == null) ||
       *            (first.prev == null && first.item != null)
       */
      transient Node<E> first;

      /**
       * Pointer to last node.(LL)
       * Invariant: (first == null && last == null) ||
       *            (last.next == null && last.item != null)
       */
      transient Node<E> last;
      
      public ListItrTest(int index) {
          // assert isPositionIndex(index);
          next = (index == size) ? null : node(index);
          nextIndex = index;
      }

      /**
       * Links e as first element.(LL)
       * 将该元素作为第一结点
       */
      private void linkFirst(E e) {
          final Node<E> f = first;
          final Node<E> newNode = new Node<>(null, e, f);
          first = newNode;
          if (f == null)
              last = newNode;
          else
              f.prev = newNode;
          size++;
          modCount++;
      }

      /**
       * Links e as last element.(LL)
       * 把元素添加到最后一个位置的结点
       */
      void linkLast(E e) {
          final Node<E> l = last;
          final Node<E> newNode = new Node<>(l, e, null);
          last = newNode;
          if (l == null)
              first = newNode;
          else
              l.next = newNode;
          size++;
          modCount++;
      }

      /**
       * Inserts element e before non-null Node succ.(LL)
       * 在succ结点之前插入一个新的结点
       */
      void linkBefore(E e, Node<E> succ) {
          // assert succ != null;
          final Node<E> pred = succ.prev;
          final Node<E> newNode = new Node<>(pred, e, succ);
          succ.prev = newNode;
          if (pred == null)
              first = newNode;
          else
              pred.next = newNode;
          size++;
          modCount++;
      }

      /**
       * Unlinks non-null first node f.(LL)
       * 去除第一个结点
       */
      private E unlinkFirst(Node<E> f) {
          // assert f == first && f != null;
          final E element = f.item;
          final Node<E> next = f.next;
          f.item = null;
          f.next = null; // help GC
          first = next;
          //当集合中只有一个结点
          if (next == null)
              last = null;
          else
              next.prev = null;
          size--;
          modCount++;
          return element;
      }

      /**
       * Unlinks non-null last node l.(LL)
       * 去除最后一个结点
       */
      private E unlinkLast(Node<E> l) {
          // assert l == last && l != null;
          final E element = l.item;
          final Node<E> prev = l.prev;
          l.item = null;
          l.prev = null; // help GC
          last = prev;
          //当集合中只有一个结点
          if (prev == null)
              first = null;
          else
              prev.next = null;
          size--;
          modCount++;
          return element;
      }

      /**
       * Unlinks non-null node x.(LL)
       * 去除一个非空结点
       * 结点的可能的位置:
       * 	1.第一个结点
       * 	2.中间结点
       * 	3.最后一个结点
       */
      E unlink(Node<E> x) {
          // assert x != null;
          final E element = x.item;
          final Node<E> next = x.next;
          final Node<E> prev = x.prev;
          //当x是第一结点时
          if (prev == null) {
              first = next;
          } 
          //当x不是第一结点
          else {
              prev.next = next;
              x.prev = null;   //清空x的前驱结点记录
          }
          //当x是最后一个结点
          if (next == null) {
              last = prev;
          } 
          //当x不是最后一个结点
          else {
              next.prev = prev;
              x.next = null;   //清空x的后缀结点记录
          }
          
          //清空x的数据
          x.item = null;
          size--;
          modCount++;
          return element;
      }
      
      /**
       * Returns the (non-null) Node at the specified element index.
       * 返回指定索引的结点
       */ 
      Node<E> node(int index) {
          // assert isElementIndex(index);
    	  
    	  //当索引在集合的前半部分时
          if (index < (size >> 1)) {
              Node<E> x = first;
              for (int i = 0; i < index; i++)
                  x = x.next;
              return x;
          } 
          //当索引在集合的后半部分时
          else {
              Node<E> x = last;
              for (int i = size - 1; i > index; i--)
                  x = x.prev;
              return x;
          }
      }
      /**
       * 判断后面还有没有元素
       */
      public boolean hasNext() {
          return nextIndex < size;
      }
      
      /**
       * 给lastReturned赋值,可以认为是返回处于lastReturned和next之间的元素
       * 对nextIndex进行加一操作
       * @return   
       */
      public E next() {
          checkForComodification();
          //当后面没有元素时
          if (!hasNext())
              throw new NoSuchElementException();

          lastReturned = next;
          next = next.next;
          nextIndex++;
          return lastReturned.item;
      }
      /**
       * 判断前面还有没有元素
       */
      public boolean hasPrevious() {
          return nextIndex > 0;
      }

      /**
       * 给lastReturned赋值,可以认为是返回处于lastReturned和next之间的元素
       * 对nextIndex进行减一操作
       * @return   
       */
      public E previous() {
          checkForComodification();
          if (!hasPrevious())
              throw new NoSuchElementException();
          
          //lastReturned 与 next指向同一块地址
          lastReturned = next = (next == null) ? last : next.prev;
          nextIndex--;
          return lastReturned.item;
      }
      /**
       * 返回next指向的地址中的元素在链表的位置
       */
      public int nextIndex() {
          return nextIndex;
      }
      /**
       * 返回next指向的地址中的元素在链表的位置的前一个位置
       */
      public int previousIndex() {
          return nextIndex - 1;
      }

      /**
       * 移除结点(元素)--结构性修改
       * 并清空lastReturned
       * 注意:lastReturned必须不为null
       * 所以remove方法只能在调用next方法、previous方法或forEachRemaining方法之后，才能用
       */
      public void remove() {
          checkForComodification();
          if (lastReturned == null)
              throw new IllegalStateException();

          Node<E> lastNext = lastReturned.next;
          unlink(lastReturned);
          //当调用了previous方法之后时
          if (next == lastReturned)
              next = lastNext;
          else
              nextIndex--;
          //清空lastReturned
          lastReturned = null;
          expectedModCount++;
      }
      /**
       * 注意:lastReturned必须不为null
       * 所以remove方法只能在调用next方法、previous方法或forEachRemaining方法之后，才能用 
       */
      public void set(E e) {
          if (lastReturned == null)
              throw new IllegalStateException();
          checkForComodification();
          lastReturned.item = e;
      }
      /**
       * 添加结点(元素)--结构性修改
       * 并清空lastReturned
       * 对nextIndex进行加一操作
       */
      public void add(E e) {
          checkForComodification();
          lastReturned = null;
          //当next为null时,把元素添加到最后一个位置的结点
          if (next == null)
        	  linkLast(e);
          //当next不为null时,在next指向结点之前插入一个新的结点
          else
              linkBefore(e, next);
          nextIndex++;
          expectedModCount++;
      }
      /**
       * 这是Java 8为Iterator新增的默认方法，该方法可使用Lambda表达式来遍历集合元素。
       */
      public void forEachRemaining(Consumer<? super E> action) {
          Objects.requireNonNull(action);
          while (modCount == expectedModCount && nextIndex < size) {
              action.accept(next.item);
              lastReturned = next;
              next = next.next;
              nextIndex++;
          }
          checkForComodification();
      }
      /**
       * 检查是否被其他类的对象在结构上进行修改
       */
      final void checkForComodification() {
          if (modCount != expectedModCount)
              throw new ConcurrentModificationException();
      }
     
      /**
       * 定义一个结点(LL)
       * @author YeLuo
       * @function
       * @param <E>
       */
      private static class Node<E> {
          E item;			//当前结点的值
          Node<E> next;   	//后缀结点
          Node<E> prev;   	//前驱结点

        @SuppressWarnings("unused")
        //创建当前结点对象
		Node(Node<E> prev, E element, Node<E> next) {
              this.item = element;
              this.next = next;
              this.prev = prev;
          }
      }

  }

  