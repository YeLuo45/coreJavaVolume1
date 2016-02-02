package com.yeluo.mvn.ch0;

import java.io.PrintStream;
import java.util.Arrays;

import sun.misc.VM;
/**
 * 
 * @author YeLuo
 * @function
 */
/**
 * public class ThreadGroup
 * extends Object
 * implements Thread.UncaughtExceptionHandler
 * 1.线程组表示一个线程的集合。此外，线程组也可以包含其他线程组。
 * 线程组构成一棵树，在树中，除了初始线程组外，每个线程组都有一个父线程组。 
 * 
 * 2.每个线程产生时,都会被归入某个线程组(Java中每个线程都是属于某个线程组),
 * 视线程是在那个线程组中产生而定.如果没有指定,则归入产生该子线程的线程的线程组中.
 * (如在main中初始化一个线程,未指定线程组,则线程所属线程组为main)
 * 
 * 3.线程一旦归入某个组就无法更换组.
 * 
 * 4.main线程组的parent是system线程组,而system线程组的parent为null.
 * (参考ThreadGroup的私有构造方法).也就是说初始线程组为system.以system/main衍生出一颗树
 * 
 * 5.其activeCount/enumerate方法均为不精确的统计,建议仅用于信息目的
 * 
 * 6.可通过enumerate获得活动线程的引用并对其进行操作
 * 
 * 7.允许线程访问有关自己的线程组的信息{@link Thread#getThreadGroup()},
 * 但是不允许它访问有关其线程组的父线程组或其他任何线程组的信息
 * 
 * 8.线程组的某些方法,将对线程组内的所有子组的所有线程执行,如{@link ThreadGroup#interrupt()}
 * 
 * 9.public class ThreadGroup implements Thread.UncaughtExceptionHandler,
 * 即其实现了UncaughtExceptionHandler方法.
 * 即
 *     ->当Thread因未捕获的异常而突然中止时,调用处理程序的接口.
 *     当某一线程因捕获的异常而即将中止时,JVM将使用UncaughtExceptionHandler查询该线程以获得其
 *     UncaughtExceptionHandler的线程并调用处理程序的uncaughtException方法,将线程和异常作为参数传递.
 *     如果某一线程为明确设置其UncaughtExceptionHandler,则将它的ThreadGroup对象作为UncaughtExceptionHandler.
 *     如果ThreadGroup对象对处理异常没有特殊要求,
 *     可以将调用转发给 {@link Thread#getDefaultUncaughtExceptionHandler()}
 * 
 * 10."线程是独立执行的代码片断，线程的问题应该由线程自己来解决，而不要委托到外部".基于这样的设计理念，
 * 在Java中，线程方法的异常（无论是checked还是unchecked exception），
 * 都应该在线程代码边界之内（run方法内）进行try catch并处理掉.换句话说，我们不能捕获从线程中逃逸的异常
 * 
 * 11.参考{@link Thread#dispatchUncaughtException}，该方法是一个私有方法，在异常逃逸时调用.
 * 判断线程自身是否设置了uncaughtExceptionHandler.
 * 如果没有则直接返回group，即自己的所在的线程组,而线程组实现了UncaughtExceptionHandler接口.
 * {@link Thread#getUncaughtExceptionHandler()}
 */
public class ThreadGroupTest implements ThreadTest.UncaughtExceptionHandler {
    private final ThreadGroupTest parent;
    String name;
    int maxPriority;
    boolean destroyed;
    boolean daemon;
    boolean vmAllowSuspension;

    int nUnstartedThreads = 0;
    int nthreads;
    ThreadTest[] threads;

    int ngroups;
    ThreadGroupTest groups[];

    /**
     * 创建一个空的线程组,即该线程组没有任何线程组.
     * 这个构造方法被用于创建系统线程组--初始线程组.
     * Creates an empty Thread group that is not in any Thread group.
     * This method is used to create the system Thread group.
     */
    private ThreadGroupTest() {     // called from C code
        this.name = "system";
        this.maxPriority = ThreadTest.MAX_PRIORITY;
        this.parent = null;
    }

    /**
     * 构造一个新线程组。新线程组的父线程组是目前正在运行线程的线程组。 
     * 不使用任何参数调用父线程组的 checkAccess 方法；这可能导致一个安全性异常。
     * 
     * Constructs a new thread group. The parent of this new group is
     * the thread group of the currently running thread.
     * <p>
     * The <code>checkAccess</code> method of the parent thread group is
     * called with no arguments; this may result in a security exception.
     *
     * @param   name   the name of the new thread group.
     * @exception  SecurityException  if the current thread cannot create a
     *               thread in the specified thread group.
     * @see     java.lang.ThreadGroup#checkAccess()
     * @since   JDK1.0
     */
    public ThreadGroupTest(String name) {
        this(ThreadTest.currentThread().getThreadGroup(), name);
    }

    /**
     * 构造一个新线程组.新线程组的父线程组是指定的线程组.
     * 不使用任何参数调用父线程组的 checkAccess 方法；这可能导致一个安全性异常。
     * 
     * Creates a new thread group. The parent of this new group is the
     * specified thread group.
     * <p>
     * The <code>checkAccess</code> method of the parent thread group is
     * called with no arguments; this may result in a security exception.
     *
     * @param     parent   the parent thread group.
     * @param     name     the name of the new thread group.
     * @exception  NullPointerException  if the thread group argument is
     *               <code>null</code>.
     * @exception  SecurityException  if the current thread cannot create a
     *               thread in the specified thread group.
     * @see     java.lang.SecurityException
     * @see     java.lang.ThreadGroup#checkAccess()
     * @since   JDK1.0
     */
    public ThreadGroupTest(ThreadGroupTest parent, String name) {
        this(checkParentAccess(parent), parent, name);
    }

    /**
     * 根据指定的线程名设定为线程组名,
     * 为该线程组指定父线程组，
     * 该线程组的最大优先级、是否为守护线程、是否允许虚拟机暂停和父线程组样
     * @param unused
     * @param parent  父线程组
     * @param name	     线程组名
     */
    private ThreadGroupTest(Void unused, ThreadGroupTest parent, String name) {
        this.name = name;
        this.maxPriority = parent.maxPriority;
        this.daemon = parent.daemon;
        this.vmAllowSuspension = parent.vmAllowSuspension;
        this.parent = parent;
        parent.add(this);
    }

    /*
     * @throws  NullPointerException  if the parent argument is {@code null}
     * @throws  SecurityException     if the current thread cannot create a
     *                                thread in the specified thread group.
     */
    private static Void checkParentAccess(ThreadGroupTest parent2) {
        parent2.checkAccess();
        return null;
    }

    /**
     * 返回该线程组的名字
     * Returns the name of this thread group.
     *
     * @return  the name of this thread group.
     * @since   JDK1.0
     */
    public final String getName() {
        return name;
    }

    /**
     * 返回该线程组的父线程组
     * 首先，如果父线程组不为 null，则不使用任何参数直接调用父线程组的 checkAccess 方法；
     * 这可能导致一个安全性异常。
     * 
     * Returns the parent of this thread group.
     * <p>
     * First, if the parent is not <code>null</code>, the
     * <code>checkAccess</code> method of the parent thread group is
     * called with no arguments; this may result in a security exception.
     *
     * @return  the parent of this thread group. The top-level thread group
     *          is the only thread group whose parent is <code>null</code>.
     * @exception  SecurityException  if the current thread cannot modify
     *               this thread group.
     * @see        java.lang.ThreadGroup#checkAccess()
     * @see        java.lang.SecurityException
     * @see        java.lang.RuntimePermission
     * @since   JDK1.0
     */
    public final ThreadGroupTest getParent() {
        if (parent != null)
            parent.checkAccess();
        return parent;
    }

    /**
     * 返回此线程组的最高优先级。作为此线程组一部分的线程不能拥有比最高优先级更高的优先级。 
     * 
     * Returns the maximum priority of this thread group. Threads that are
     * part of this group cannot have a higher priority than the maximum
     * priority.
     *
     * @return  the maximum priority that a thread in this thread group
     *          can have.
     * @see     #setMaxPriority
     * @since   JDK1.0
     */
    public final int getMaxPriority() {
        return maxPriority;
    }

    /**测试此线程组是否为一个后台程序线程组。
     * 在停止后台程序线程组的最后一个线程或销毁其最后一个线程组时，自动销毁这个后台程序线程组
     * Tests if this thread group is a daemon thread group. A
     * daemon thread group is automatically destroyed when its last
     * thread is stopped or its last thread group is destroyed.
     *
     * @return  <code>true</code> if this thread group is a daemon thread group;
     *          <code>false</code> otherwise.
     * @since   JDK1.0
     */
    public final boolean isDaemon() {
        return daemon;
    }

    /**
     * 测试此线程组是否已经被销毁
     * Tests if this thread group has been destroyed.
     *
     * @return  true if this object is destroyed
     * @since   JDK1.1
     */
    public synchronized boolean isDestroyed() {
        return destroyed;
    }

    /**
     * 改变该线程组的后台程序状态
     * 首先，不使用任何参数调用此线程组的 checkAccess 方法；这可能导致一个安全性异常。 
     * 在停止后台程序线程组的最后一个线程或销毁其最后一个线程组时，自动销毁此后台程序线程组。
     *  
     * Changes the daemon status of this thread group.
     * <p>
     * First, the <code>checkAccess</code> method of this thread group is
     * called with no arguments; this may result in a security exception.
     * <p>
     * A daemon thread group is automatically destroyed when its last
     * thread is stopped or its last thread group is destroyed.
     *
     * @param      daemon   if <code>true</code>, marks this thread group as
     *                      a daemon thread group; otherwise, marks this
     *                      thread group as normal.
     * @exception  SecurityException  if the current thread cannot modify
     *               this thread group.
     * @see        java.lang.SecurityException
     * @see        java.lang.ThreadGroup#checkAccess()
     * @since      JDK1.0
     */
    public final void setDaemon(boolean daemon) {
        checkAccess();
        this.daemon = daemon;
    }

    /**
     * 设置线程组的最高优先级。线程组中已有较高优先级的线程不受影响。
     * 
     * 首先，不使用任何参数调用此线程组的 checkAccess 方法；这可能导致一个安全性异常。
     *  
     * 如果 pri 参数小于 Thread.MIN_PRIORITY 或大于 Thread.MAX_PRIORITY，则线程组的最高优先级保持不变。 
     * 
     * 否则，此 ThreadGroup 对象的优先级被设置为比指定的 pri 参数更小，
     * 所允许的最高优先级是此线程组的父线程组的优先级。
     * （如果此线程组是系统线程组，没有父线程组，那么只需将最高优先级设置为 pri 即可。）
     * 然后使用 pri 作为此方法的参数，以递归的方式对属于此线程组的每个线程组调用此方法。
     *  
     * Sets the maximum priority of the group. Threads in the thread
     * group that already have a higher priority are not affected.
     * <p>
     * First, the <code>checkAccess</code> method of this thread group is
     * called with no arguments; this may result in a security exception.
     * <p>
     * If the <code>pri</code> argument is less than
     * {@link Thread#MIN_PRIORITY} or greater than
     * {@link Thread#MAX_PRIORITY}, the maximum priority of the group
     * remains unchanged.
     * <p>
     * Otherwise, the priority of this ThreadGroup object is set to the
     * smaller of the specified <code>pri</code> and the maximum permitted
     * priority of the parent of this thread group. (If this thread group
     * is the system thread group, which has no parent, then its maximum
     * priority is simply set to <code>pri</code>.) Then this method is
     * called recursively, with <code>pri</code> as its argument, for
     * every thread group that belongs to this thread group.
     *
     * @param      pri   the new priority of the thread group.
     * @exception  SecurityException  if the current thread cannot modify
     *               this thread group.
     * @see        #getMaxPriority
     * @see        java.lang.SecurityException
     * @see        java.lang.ThreadGroup#checkAccess()
     * @since      JDK1.0
     */
    public final void setMaxPriority(int pri) {
        int ngroupsSnapshot;
        ThreadGroupTest[] groupsSnapshot;
        synchronized (this) {
            checkAccess();
            if (pri < ThreadTest.MIN_PRIORITY || pri > ThreadTest.MAX_PRIORITY) {
                return;
            }
            maxPriority = (parent != null) ? Math.min(pri, parent.maxPriority) : pri;
            ngroupsSnapshot = ngroups;
            if (groups != null) {
                groupsSnapshot = Arrays.copyOf(groups, ngroupsSnapshot);
            } else {
                groupsSnapshot = null;
            }
        }
        for (int i = 0 ; i < ngroupsSnapshot ; i++) {
            groupsSnapshot[i].setMaxPriority(pri);
        }
    }

    /**
     * Tests if this thread group is either the thread group
     * argument or one of its ancestor thread groups.
     *
     * @param   g   a thread group.
     * @return  <code>true</code> if this thread group is the thread group
     *          argument or one of its ancestor thread groups;
     *          <code>false</code> otherwise.
     * @since   JDK1.0
     */
    public final boolean parentOf(ThreadGroupTest g) {
        for (; g != null ; g = g.parent) {
            if (g == this) {
                return true;
            }
        }
        return false;
    }

    /**
     * Determines if the currently running thread has permission to
     * modify this thread group.
     * <p>
     * If there is a security manager, its <code>checkAccess</code> method
     * is called with this thread group as its argument. This may result
     * in throwing a <code>SecurityException</code>.
     *
     * @exception  SecurityException  if the current thread is not allowed to
     *               access this thread group.
     * @see        java.lang.SecurityManager#checkAccess(java.lang.ThreadGroup)
     * @since      JDK1.0
     */
    public final void checkAccess() {
        SecurityManager security = System.getSecurityManager();
        if (security != null) {
            /*security.checkAccess(this);*/
        }
    }

    /**
     * Returns an estimate of the number of active threads in this thread
     * group and its subgroups. Recursively iterates over all subgroups in
     * this thread group.
     *
     * <p> The value returned is only an estimate because the number of
     * threads may change dynamically while this method traverses internal
     * data structures, and might be affected by the presence of certain
     * system threads. This method is intended primarily for debugging
     * and monitoring purposes.
     *
     * @return  an estimate of the number of active threads in this thread
     *          group and in any other thread group that has this thread
     *          group as an ancestor
     *
     * @since   JDK1.0
     */
    public int activeCount() {
        int result;
        // Snapshot sub-group data so we don't hold this lock
        // while our children are computing.
        int ngroupsSnapshot;
        ThreadGroupTest[] groupsSnapshot;
        synchronized (this) {
            if (destroyed) {
                return 0;
            }
            result = nthreads;
            ngroupsSnapshot = ngroups;
            if (groups != null) {
                groupsSnapshot = Arrays.copyOf(groups, ngroupsSnapshot);
            } else {
                groupsSnapshot = null;
            }
        }
        for (int i = 0 ; i < ngroupsSnapshot ; i++) {
            result += groupsSnapshot[i].activeCount();
        }
        return result;
    }

    /**
     * Copies into the specified array every active thread in this
     * thread group and its subgroups.
     *
     * <p> An invocation of this method behaves in exactly the same
     * way as the invocation
     *
     * <blockquote>
     * {@linkplain #enumerate(Thread[], boolean) enumerate}{@code (list, true)}
     * </blockquote>
     *
     * @param  list
     *         an array into which to put the list of threads
     *
     * @return  the number of threads put into the array
     *
     * @throws  SecurityException
     *          if {@linkplain #checkAccess checkAccess} determines that
     *          the current thread cannot access this thread group
     *
     * @since   JDK1.0
     */
    public int enumerate(ThreadTest list[]) {
        checkAccess();
        return enumerate(list, 0, true);
    }

    /**
     * Copies into the specified array every active thread in this
     * thread group. If {@code recurse} is {@code true},
     * this method recursively enumerates all subgroups of this
     * thread group and references to every active thread in these
     * subgroups are also included. If the array is too short to
     * hold all the threads, the extra threads are silently ignored.
     *
     * <p> An application might use the {@linkplain #activeCount activeCount}
     * method to get an estimate of how big the array should be, however
     * <i>if the array is too short to hold all the threads, the extra threads
     * are silently ignored.</i>  If it is critical to obtain every active
     * thread in this thread group, the caller should verify that the returned
     * int value is strictly less than the length of {@code list}.
     *
     * <p> Due to the inherent race condition in this method, it is recommended
     * that the method only be used for debugging and monitoring purposes.
     *
     * @param  list
     *         an array into which to put the list of threads
     *
     * @param  recurse
     *         if {@code true}, recursively enumerate all subgroups of this
     *         thread group
     *
     * @return  the number of threads put into the array
     *
     * @throws  SecurityException
     *          if {@linkplain #checkAccess checkAccess} determines that
     *          the current thread cannot access this thread group
     *
     * @since   JDK1.0
     */
    public int enumerate(ThreadTest list[], boolean recurse) {
        checkAccess();
        return enumerate(list, 0, recurse);
    }

    private int enumerate(ThreadTest list[], int n, boolean recurse) {
        int ngroupsSnapshot = 0;
        ThreadGroupTest[] groupsSnapshot = null;
        synchronized (this) {
            if (destroyed) {
                return 0;
            }
            int nt = nthreads;
            if (nt > list.length - n) {
                nt = list.length - n;
            }
            for (int i = 0; i < nt; i++) {
                if (threads[i].isAlive()) {
                    list[n++] = threads[i];
                }
            }
            if (recurse) {
                ngroupsSnapshot = ngroups;
                if (groups != null) {
                    groupsSnapshot = Arrays.copyOf(groups, ngroupsSnapshot);
                } else {
                    groupsSnapshot = null;
                }
            }
        }
        if (recurse) {
            for (int i = 0 ; i < ngroupsSnapshot ; i++) {
                n = groupsSnapshot[i].enumerate(list, n, true);
            }
        }
        return n;
    }

    /**
     * Returns an estimate of the number of active groups in this
     * thread group and its subgroups. Recursively iterates over
     * all subgroups in this thread group.
     *
     * <p> The value returned is only an estimate because the number of
     * thread groups may change dynamically while this method traverses
     * internal data structures. This method is intended primarily for
     * debugging and monitoring purposes.
     *
     * @return  the number of active thread groups with this thread group as
     *          an ancestor
     *
     * @since   JDK1.0
     */
    public int activeGroupCount() {
        int ngroupsSnapshot;
        ThreadGroupTest[] groupsSnapshot;
        synchronized (this) {
            if (destroyed) {
                return 0;
            }
            ngroupsSnapshot = ngroups;
            if (groups != null) {
                groupsSnapshot = Arrays.copyOf(groups, ngroupsSnapshot);
            } else {
                groupsSnapshot = null;
            }
        }
        int n = ngroupsSnapshot;
        for (int i = 0 ; i < ngroupsSnapshot ; i++) {
            n += groupsSnapshot[i].activeGroupCount();
        }
        return n;
    }

    /**
     * Copies into the specified array references to every active
     * subgroup in this thread group and its subgroups.
     *
     * <p> An invocation of this method behaves in exactly the same
     * way as the invocation
     *
     * <blockquote>
     * {@linkplain #enumerate(ThreadGroup[], boolean) enumerate}{@code (list, true)}
     * </blockquote>
     *
     * @param  list
     *         an array into which to put the list of thread groups
     *
     * @return  the number of thread groups put into the array
     *
     * @throws  SecurityException
     *          if {@linkplain #checkAccess checkAccess} determines that
     *          the current thread cannot access this thread group
     *
     * @since   JDK1.0
     */
    public int enumerate(ThreadGroupTest list[]) {
        checkAccess();
        return enumerate(list, 0, true);
    }

    /**
     * Copies into the specified array references to every active
     * subgroup in this thread group. If {@code recurse} is
     * {@code true}, this method recursively enumerates all subgroups of this
     * thread group and references to every active thread group in these
     * subgroups are also included.
     *
     * <p> An application might use the
     * {@linkplain #activeGroupCount activeGroupCount} method to
     * get an estimate of how big the array should be, however <i>if the
     * array is too short to hold all the thread groups, the extra thread
     * groups are silently ignored.</i>  If it is critical to obtain every
     * active subgroup in this thread group, the caller should verify that
     * the returned int value is strictly less than the length of
     * {@code list}.
     *
     * <p> Due to the inherent race condition in this method, it is recommended
     * that the method only be used for debugging and monitoring purposes.
     *
     * @param  list
     *         an array into which to put the list of thread groups
     *
     * @param  recurse
     *         if {@code true}, recursively enumerate all subgroups
     *
     * @return  the number of thread groups put into the array
     *
     * @throws  SecurityException
     *          if {@linkplain #checkAccess checkAccess} determines that
     *          the current thread cannot access this thread group
     *
     * @since   JDK1.0
     */
    public int enumerate(ThreadGroupTest list[], boolean recurse) {
        checkAccess();
        return enumerate(list, 0, recurse);
    }

    private int enumerate(ThreadGroupTest list[], int n, boolean recurse) {
        int ngroupsSnapshot = 0;
        ThreadGroupTest[] groupsSnapshot = null;
        synchronized (this) {
            if (destroyed) {
                return 0;
            }
            int ng = ngroups;
            if (ng > list.length - n) {
                ng = list.length - n;
            }
            if (ng > 0) {
                System.arraycopy(groups, 0, list, n, ng);
                n += ng;
            }
            if (recurse) {
                ngroupsSnapshot = ngroups;
                if (groups != null) {
                    groupsSnapshot = Arrays.copyOf(groups, ngroupsSnapshot);
                } else {
                    groupsSnapshot = null;
                }
            }
        }
        if (recurse) {
            for (int i = 0 ; i < ngroupsSnapshot ; i++) {
                n = groupsSnapshot[i].enumerate(list, n, true);
            }
        }
        return n;
    }

    /**
     * Stops all threads in this thread group.
     * <p>
     * First, the <code>checkAccess</code> method of this thread group is
     * called with no arguments; this may result in a security exception.
     * <p>
     * This method then calls the <code>stop</code> method on all the
     * threads in this thread group and in all of its subgroups.
     *
     * @exception  SecurityException  if the current thread is not allowed
     *               to access this thread group or any of the threads in
     *               the thread group.
     * @see        java.lang.SecurityException
     * @see        java.lang.Thread#stop()
     * @see        java.lang.ThreadGroup#checkAccess()
     * @since      JDK1.0
     * @deprecated    This method is inherently unsafe.  See
     *     {@link Thread#stop} for details.
     */
    @Deprecated
    public final void stop() {
        if (stopOrSuspend(false))
            ThreadTest.currentThread().stop();
    }

    /**
     * Interrupts all threads in this thread group.
     * <p>
     * First, the <code>checkAccess</code> method of this thread group is
     * called with no arguments; this may result in a security exception.
     * <p>
     * This method then calls the <code>interrupt</code> method on all the
     * threads in this thread group and in all of its subgroups.
     *
     * @exception  SecurityException  if the current thread is not allowed
     *               to access this thread group or any of the threads in
     *               the thread group.
     * @see        java.lang.Thread#interrupt()
     * @see        java.lang.SecurityException
     * @see        java.lang.ThreadGroup#checkAccess()
     * @since      1.2
     */
    public final void interrupt() {
        int ngroupsSnapshot;
        ThreadGroupTest[] groupsSnapshot;
        synchronized (this) {
            checkAccess();
            for (int i = 0 ; i < nthreads ; i++) {
                threads[i].interrupt();
            }
            ngroupsSnapshot = ngroups;
            if (groups != null) {
                groupsSnapshot = Arrays.copyOf(groups, ngroupsSnapshot);
            } else {
                groupsSnapshot = null;
            }
        }
        for (int i = 0 ; i < ngroupsSnapshot ; i++) {
            groupsSnapshot[i].interrupt();
        }
    }

    /**
     * Suspends all threads in this thread group.
     * <p>
     * First, the <code>checkAccess</code> method of this thread group is
     * called with no arguments; this may result in a security exception.
     * <p>
     * This method then calls the <code>suspend</code> method on all the
     * threads in this thread group and in all of its subgroups.
     *
     * @exception  SecurityException  if the current thread is not allowed
     *               to access this thread group or any of the threads in
     *               the thread group.
     * @see        java.lang.Thread#suspend()
     * @see        java.lang.SecurityException
     * @see        java.lang.ThreadGroup#checkAccess()
     * @since      JDK1.0
     * @deprecated    This method is inherently deadlock-prone.  See
     *     {@link Thread#suspend} for details.
     */
    @Deprecated
    @SuppressWarnings("deprecation")
    public final void suspend() {
        if (stopOrSuspend(true))
            ThreadTest.currentThread().suspend();
    }

    /**
     * Helper method: recursively stops or suspends (as directed by the
     * boolean argument) all of the threads in this thread group and its
     * subgroups, except the current thread.  This method returns true
     * if (and only if) the current thread is found to be in this thread
     * group or one of its subgroups.
     */
    @SuppressWarnings("deprecation")
    private boolean stopOrSuspend(boolean suspend) {
        boolean suicide = false;
        ThreadTest us = ThreadTest.currentThread();
        int ngroupsSnapshot;
        ThreadGroupTest[] groupsSnapshot = null;
        synchronized (this) {
            checkAccess();
            for (int i = 0 ; i < nthreads ; i++) {
                if (threads[i]==us)
                    suicide = true;
                else if (suspend)
                    threads[i].suspend();
                else
                    threads[i].stop();
            }

            ngroupsSnapshot = ngroups;
            if (groups != null) {
                groupsSnapshot = Arrays.copyOf(groups, ngroupsSnapshot);
            }
        }
        for (int i = 0 ; i < ngroupsSnapshot ; i++)
            suicide = groupsSnapshot[i].stopOrSuspend(suspend) || suicide;

        return suicide;
    }

    /**
     * Resumes all threads in this thread group.
     * <p>
     * First, the <code>checkAccess</code> method of this thread group is
     * called with no arguments; this may result in a security exception.
     * <p>
     * This method then calls the <code>resume</code> method on all the
     * threads in this thread group and in all of its sub groups.
     *
     * @exception  SecurityException  if the current thread is not allowed to
     *               access this thread group or any of the threads in the
     *               thread group.
     * @see        java.lang.SecurityException
     * @see        java.lang.Thread#resume()
     * @see        java.lang.ThreadGroup#checkAccess()
     * @since      JDK1.0
     * @deprecated    This method is used solely in conjunction with
     *      <tt>Thread.suspend</tt> and <tt>ThreadGroup.suspend</tt>,
     *       both of which have been deprecated, as they are inherently
     *       deadlock-prone.  See {@link Thread#suspend} for details.
     */
    @Deprecated
    @SuppressWarnings("deprecation")
    public final void resume() {
        int ngroupsSnapshot;
        ThreadGroupTest[] groupsSnapshot;
        synchronized (this) {
            checkAccess();
            for (int i = 0 ; i < nthreads ; i++) {
                threads[i].resume();
            }
            ngroupsSnapshot = ngroups;
            if (groups != null) {
                groupsSnapshot = Arrays.copyOf(groups, ngroupsSnapshot);
            } else {
                groupsSnapshot = null;
            }
        }
        for (int i = 0 ; i < ngroupsSnapshot ; i++) {
            groupsSnapshot[i].resume();
        }
    }

    /**
     * Destroys this thread group and all of its subgroups. This thread
     * group must be empty, indicating that all threads that had been in
     * this thread group have since stopped.
     * <p>
     * First, the <code>checkAccess</code> method of this thread group is
     * called with no arguments; this may result in a security exception.
     *
     * @exception  IllegalThreadStateException  if the thread group is not
     *               empty or if the thread group has already been destroyed.
     * @exception  SecurityException  if the current thread cannot modify this
     *               thread group.
     * @see        java.lang.ThreadGroup#checkAccess()
     * @since      JDK1.0
     */
    public final void destroy() {
        int ngroupsSnapshot;
        ThreadGroupTest[] groupsSnapshot;
        synchronized (this) {
            checkAccess();
            if (destroyed || (nthreads > 0)) {
                throw new IllegalThreadStateException();
            }
            ngroupsSnapshot = ngroups;
            if (groups != null) {
                groupsSnapshot = Arrays.copyOf(groups, ngroupsSnapshot);
            } else {
                groupsSnapshot = null;
            }
            if (parent != null) {
                destroyed = true;
                ngroups = 0;
                groups = null;
                nthreads = 0;
                threads = null;
            }
        }
        for (int i = 0 ; i < ngroupsSnapshot ; i += 1) {
            groupsSnapshot[i].destroy();
        }
        if (parent != null) {
            parent.remove(this);
        }
    }

    /**
     * Adds the specified Thread group to this group.
     * @param g the specified Thread group to be added
     * @exception IllegalThreadStateException If the Thread group has been destroyed.
     */
    private final void add(ThreadGroupTest g){
        synchronized (this) {
            if (destroyed) {
                throw new IllegalThreadStateException();
            }
            if (groups == null) {
                groups = new ThreadGroupTest[4];
            } else if (ngroups == groups.length) {
                groups = Arrays.copyOf(groups, ngroups * 2);
            }
            groups[ngroups] = g;

            // This is done last so it doesn't matter in case the
            // thread is killed
            ngroups++;
        }
    }

    /**
     * Removes the specified Thread group from this group.
     * @param g the Thread group to be removed
     * @return if this Thread has already been destroyed.
     */
    private void remove(ThreadGroupTest g) {
        synchronized (this) {
            if (destroyed) {
                return;
            }
            for (int i = 0 ; i < ngroups ; i++) {
                if (groups[i] == g) {
                    ngroups -= 1;
                    System.arraycopy(groups, i + 1, groups, i, ngroups - i);
                    // Zap dangling reference to the dead group so that
                    // the garbage collector will collect it.
                    groups[ngroups] = null;
                    break;
                }
            }
            if (nthreads == 0) {
                notifyAll();
            }
            if (daemon && (nthreads == 0) &&
                (nUnstartedThreads == 0) && (ngroups == 0))
            {
                destroy();
            }
        }
    }


    /**
     * Increments the count of unstarted threads in the thread group.
     * Unstarted threads are not added to the thread group so that they
     * can be collected if they are never started, but they must be
     * counted so that daemon thread groups with unstarted threads in
     * them are not destroyed.
     */
    void addUnstarted() {
        synchronized(this) {
            if (destroyed) {
                throw new IllegalThreadStateException();
            }
            nUnstartedThreads++;
        }
    }

    /**
     * 添加指定的线程到该线程组中.
     * 
     * 注：此方法是从库代码和虚拟机调用的。
     * 当从虚拟机添加某些系统线程到系统线程组,它被调用。
     * Adds the specified thread to this thread group.
     *
     * <p> Note: This method is called from both library code
     * and the Virtual Machine. It is called from VM to add
     * certain system threads to the system thread group.
     *
     * @param  t
     *         the Thread to be added
     *
     * @throws  IllegalThreadStateException
     *          if the Thread group has been destroyed
     */
    void add(ThreadTest t) {
    	//synchronized代码块
        synchronized (this) {
        	//destroyed默认false
            if (destroyed) {
                throw new IllegalThreadStateException();
            }
            //threads默认null
            if (threads == null) {
                threads = new ThreadTest[4];
            } //nthreads默认是0
            else if (nthreads == threads.length) {
                threads = Arrays.copyOf(threads, nthreads * 2);
            }
            threads[nthreads] = t;

            // This is done last so it doesn't matter in case the
            // thread is killed
            nthreads++;

            /*
             * 未开始的线程计数
             * 线程是现在线程组的正式成员，
             * 即使它可能或不可能，还是已经开始。
             * 这将防止线程组被破坏所以未开始线程计数减少。
             */         
            // The thread is now a fully fledged member of the group, even
            // though it may, or may not, have been started yet. It will prevent
            // the group from being destroyed so the unstarted Threads count is
            // decremented.
            nUnstartedThreads--;
        }
    }

    /**
     * Notifies the group that the thread {@code t} has failed
     * an attempt to start.
     *
     * <p> The state of this thread group is rolled back as if the
     * attempt to start the thread has never occurred. The thread is again
     * considered an unstarted member of the thread group, and a subsequent
     * attempt to start the thread is permitted.
     *
     * @param  t
     *         the Thread whose start method was invoked
     */
    void threadStartFailed(ThreadTest t) {
        synchronized(this) {
            remove(t);
            nUnstartedThreads++;
        }
    }

    /**
     * Notifies the group that the thread {@code t} has terminated.
     *
     * <p> Destroy the group if all of the following conditions are
     * true: this is a daemon thread group; there are no more alive
     * or unstarted threads in the group; there are no subgroups in
     * this thread group.
     *
     * @param  t
     *         the Thread that has terminated
     */
    void threadTerminated(ThreadTest t) {
        synchronized (this) {
            remove(t);

            if (nthreads == 0) {
                notifyAll();
            }
            if (daemon && (nthreads == 0) &&
                (nUnstartedThreads == 0) && (ngroups == 0))
            {
                destroy();
            }
        }
    }

    /**
     * Removes the specified Thread from this group. Invoking this method
     * on a thread group that has been destroyed has no effect.
     *
     * @param  t
     *         the Thread to be removed
     */
    private void remove(ThreadTest t) {
        synchronized (this) {
            if (destroyed) {
                return;
            }
            for (int i = 0 ; i < nthreads ; i++) {
                if (threads[i] == t) {
                    System.arraycopy(threads, i + 1, threads, i, --nthreads - i);
                    // Zap dangling reference to the dead thread so that
                    // the garbage collector will collect it.
                    threads[nthreads] = null;
                    break;
                }
            }
        }
    }

    /**
     * Prints information about this thread group to the standard
     * output. This method is useful only for debugging.
     *
     * @since   JDK1.0
     */
    public void list() {
        list(System.out, 0);
    }
    void list(PrintStream out, int indent) {
        int ngroupsSnapshot;
        ThreadGroupTest[] groupsSnapshot;
        synchronized (this) {
            for (int j = 0 ; j < indent ; j++) {
                out.print(" ");
            }
            out.println(this);
            indent += 4;
            for (int i = 0 ; i < nthreads ; i++) {
                for (int j = 0 ; j < indent ; j++) {
                    out.print(" ");
                }
                out.println(threads[i]);
            }
            ngroupsSnapshot = ngroups;
            if (groups != null) {
                groupsSnapshot = Arrays.copyOf(groups, ngroupsSnapshot);
            } else {
                groupsSnapshot = null;
            }
        }
        for (int i = 0 ; i < ngroupsSnapshot ; i++) {
            groupsSnapshot[i].list(out, indent);
        }
    }

    /**
     * Called by the Java Virtual Machine when a thread in this
     * thread group stops because of an uncaught exception, and the thread
     * does not have a specific {@link Thread.UncaughtExceptionHandler}
     * installed.
     * <p>
     * The <code>uncaughtException</code> method of
     * <code>ThreadGroup</code> does the following:
     * <ul>
     * <li>If this thread group has a parent thread group, the
     *     <code>uncaughtException</code> method of that parent is called
     *     with the same two arguments.
     * <li>Otherwise, this method checks to see if there is a
     *     {@linkplain Thread#getDefaultUncaughtExceptionHandler default
     *     uncaught exception handler} installed, and if so, its
     *     <code>uncaughtException</code> method is called with the same
     *     two arguments.
     * <li>Otherwise, this method determines if the <code>Throwable</code>
     *     argument is an instance of {@link ThreadDeath}. If so, nothing
     *     special is done. Otherwise, a message containing the
     *     thread's name, as returned from the thread's {@link
     *     Thread#getName getName} method, and a stack backtrace,
     *     using the <code>Throwable</code>'s {@link
     *     Throwable#printStackTrace printStackTrace} method, is
     *     printed to the {@linkplain System#err standard error stream}.
     * </ul>
     * <p>
     * Applications can override this method in subclasses of
     * <code>ThreadGroup</code> to provide alternative handling of
     * uncaught exceptions.
     *
     * @param   t   the thread that is about to exit.
     * @param   e   the uncaught exception.
     * @since   JDK1.0
     */
    public void uncaughtException(ThreadTest t, Throwable e) {
        if (parent != null) {
            parent.uncaughtException(t, e);
        } else {
            ThreadTest.UncaughtExceptionHandler ueh =
                ThreadTest.getDefaultUncaughtExceptionHandler();
            if (ueh != null) {
                ueh.uncaughtException(t, e);
            } else if (!(e instanceof ThreadDeath)) {
                System.err.print("Exception in thread \""
                                 + t.getName() + "\" ");
                e.printStackTrace(System.err);
            }
        }
    }

    /**
     * Used by VM to control lowmem implicit suspension.
     *
     * @param b boolean to allow or disallow suspension
     * @return true on success
     * @since   JDK1.1
     * @deprecated The definition of this call depends on {@link #suspend},
     *             which is deprecated.  Further, the behavior of this call
     *             was never specified.
     */
    @Deprecated
    public boolean allowThreadSuspension(boolean b) {
        this.vmAllowSuspension = b;
        if (!b) {
            VM.unsuspendSomeThreads();
        }
        return true;
    }

    /**
     * Returns a string representation of this Thread group.
     *
     * @return  a string representation of this thread group.
     * @since   JDK1.0
     */
    public String toString() {
        return getClass().getName() + "[name=" + getName() + ",maxpri=" + maxPriority + "]";
    }
}
