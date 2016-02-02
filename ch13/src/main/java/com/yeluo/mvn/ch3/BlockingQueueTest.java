package com.yeluo.mvn.ch3;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 
 * @author YeLuo
 * @function
 */
/* BlockingQueue接口
 * 1. 支持两个附加操作的 Queue，这两个操作是：获取元素时等待队列变为非空，以及存储元素时等待空间变得可用。 
 * 
 * 2.BlockingQueue 方法以四种形式出现，对于不能立即满足但可能在将来某一时刻可以满足的操作，
 * 这四种形式的处理方式不同：第一种是抛出一个异常，第二种是返回一个特殊值（null 或 false，具体取决于操作），
 * 第三种是在操作可以成功前，无限期地阻塞当前线程，第四种是在放弃前只在给定的最大时间限制内阻塞。下表中总结了这些方法：
 *  	抛出异常 		特殊值 		阻塞 		超时 
 * 插入  	add(e) 		offer(e) 	put(e) 	offer(e, time, unit) 
 * 移除 	remove() 	poll() 		take() 	poll(time, unit) 
 * 检查 	element() 	peek() 		不可用 	不可用 
 * 
 * 3.BlockingQueue 不接受 null 元素。试图 add、put 或 offer 一个 null 元素时，
 * 某些实现会抛出 NullPointerException。null 被用作指示 poll 操作失败的警戒值。 
 * 
 * 4.BlockingQueue 可以是限定容量的。它在任意给定时间都可以有一个 remainingCapacity，
 * 超出此容量，便无法无阻塞地 put 附加元素。没有任何内部容量约束的 BlockingQueue 
 * 总是报告 Integer.MAX_VALUE 的剩余容量。 
 * 
 * 5.BlockingQueue 实现主要用于生产者-使用者队列，但它另外还支持 Collection 接口。
 * 因此，举例来说，使用 remove(x) 从队列中移除任意一个元素是有可能的。
 * 然而，这种操作通常不 会有效执行，只能有计划地偶尔使用，比如在取消排队信息时。 
 * 
 * 6.BlockingQueue 实现是线程安全的。所有排队方法都可以使用内部锁或其他形式的并发控制来自动达到它们的目的。
 * 然而，大量的 Collection 操作（addAll、containsAll、retainAll 和 removeAll）没有 必要自动执行，
 * 除非在实现中特别说明。因此，举例来说，在只添加了 c 中的一些元素后，addAll(c) 有可能失败（抛出一个异常）。 
 * 
 * 7.BlockingQueue 实质上不 支持使用任何一种“close”或“shutdown”操作来指示不再添加任何项。
 * 这种功能的需求和使用有依赖于实现的倾向。例如，一种常用的策略是：对于生产者，
 * 插入特殊的 end-of-stream 或 poison 对象，并根据使用者获取这些对象的时间来对它们进行解释。 
 * 
 * 下面的实例展示了如何使用阻塞队列来控制线程集。
 * 程序在一个目录及它的所有子目录下搜索所有文件，
 * 打印出包含指定关键字的文件列表。
 * 从下面实例可以看出，使用阻塞队列两个显著的好处就是：
 * 多线程操作共同的队列时不需要额外的同步，
 * 另外就是队列会自动平衡负载，
 * 即那边（生产与消费两边）处理快了就会被阻塞掉，
 * 从而减少两边的处理速度差距。
 * 下面是具体实现：
 */
public class BlockingQueueTest {  
    public static void main(String[] args) {  
        Scanner in = new Scanner(System.in);  
        System.out.println("Enter base directory (e.g. /usr/local/jdk5.0/src): ");  
        String directory = in.nextLine();  
        System.out.println("Enter keyword (e.g. volatile): ");  
        String keyword = in.nextLine();  
  
        final int FILE_QUEUE_SIZE = 10;// 阻塞队列大小  
        final int SEARCH_THREADS = 100;// 关键字搜索线程个数  
  
        // 基于ArrayBlockingQueue的阻塞队列  
        BlockingQueue<File> queue = new ArrayBlockingQueue<File>(  
                FILE_QUEUE_SIZE);  
  
        //只启动一个线程来搜索目录  
        FileEnumerationTask enumerator = new FileEnumerationTask(queue,  
                new File(directory));  
        new Thread(enumerator).start();  
          
        //启动100个线程用来在文件中搜索指定的关键字  
        for (int i = 1; i <= SEARCH_THREADS; i++)  
            new Thread(new SearchTask(queue, keyword)).start();  
    }  
}  
class FileEnumerationTask implements Runnable {  
    //哑元文件对象，放在阻塞队列最后，用来标示文件已被遍历完  
    public static File DUMMY = new File("");  
  
    private BlockingQueue<File> queue;  
    private File startingDirectory;  
  
    public FileEnumerationTask(BlockingQueue<File> queue, File startingDirectory) {  
        this.queue = queue;  
        this.startingDirectory = startingDirectory;  
    }  
  
    public void run() {  
        try {  
            enumerate(startingDirectory);  
            queue.put(DUMMY);//执行到这里说明指定的目录下文件已被遍历完  
        } catch (InterruptedException e) {  
        	e.printStackTrace();
        }  
    }  
  
    // 将指定目录下的所有文件以File对象的形式放入阻塞队列中  
    public void enumerate(File directory) throws InterruptedException {  
        File[] files = directory.listFiles();  
        for (File file : files) {  
            if (file.isDirectory())  
                enumerate(file);  
            else  
                //将元素放入队尾，如果队列满，则阻塞  
                queue.put(file);  
        }  
    }  
}  
class SearchTask implements Runnable {  
    private BlockingQueue<File> queue;  
    private String keyword;  
  
    public SearchTask(BlockingQueue<File> queue, String keyword) {  
        this.queue = queue;  
        this.keyword = keyword;  
    }  
  
    public void run() {  
        try {  
            boolean done = false;  
            while (!done) {  
                //取出队首元素，如果队列为空，则阻塞  
                File file = queue.take();  
                if (file == FileEnumerationTask.DUMMY) {  
                    //取出来后重新放入，好让其他线程读到它时也很快的结束  
                    queue.put(file);  
                    done = true;  
                } else  
                    search(file);  
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (InterruptedException e) {  
        }  
    }  
    public void search(File file) throws IOException {  
        Scanner in = new Scanner(new FileInputStream(file));  
        int lineNumber = 0;  
        while (in.hasNextLine()) {  
            lineNumber++;  
            String line = in.nextLine();  
            if (line.contains(keyword))  
                System.out.printf("%s:%d:%s%n", file.getPath(), lineNumber,  
                        line);  
        }  
        in.close();  
    }  
}  
