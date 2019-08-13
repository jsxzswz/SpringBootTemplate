package com.swz.threadpool;

import java.text.SimpleDateFormat;
import java.util.concurrent.*;

/**
 * @Package: com.swz.threadpool
 * @Description: 使用Callable+FutureTask获取执行结果
 * @author: swz
 * @date: 2019/5/10 15:17
 */
public class CallableTest {

    final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * IO密集型任务  = 一般为2*CPU核心数（常出现于线程中：数据库数据交互、文件上传下载、网络数据传输等等）
     * CPU密集型任务 = 一般为CPU核心数+1（常出现于线程中：复杂算法）
     * 混合型任务  = 视机器配置和复杂度自测而定
     */
    private static int corePoolSize = Runtime.getRuntime().availableProcessors();
    /**
     * public ThreadPoolExecutor(int corePoolSize,int maximumPoolSize,long keepAliveTime,
     * TimeUnit unit,BlockingQueue<Runnable> workQueue)
     * corePoolSize用于指定核心线程数量
     * maximumPoolSize指定最大线程数
     * keepAliveTime和TimeUnit指定线程空闲后的最大存活时间
     * workQueue则是线程池的缓冲队列,还未执行的线程会在队列中等待
     * 监控队列长度，确保队列有界
     * 不当的线程池大小会使得处理速度变慢，稳定性下降，并且导致内存泄露。如果配置的线程过少，则队列会持续变大，消耗过多内存。
     * 而过多的线程又会 由于频繁的上下文切换导致整个系统的速度变缓——殊途而同归。队列的长度至关重要，它必须得是有界的，这样如果线程池不堪重负了它可以暂时拒绝掉新的请求。
     * ExecutorService 默认的实现是一个无界的 LinkedBlockingQueue。
     */
    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, corePoolSize + 1, 10l, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(1000));

    public static void main(String[] args) throws InterruptedException {
        //创建FutureTask
        FutureTask<Integer> futureTask = new FutureTask<>(new Stats());
        executor.submit(futureTask);
        executor.shutdown();
        try {
            Thread.sleep(1);
            System.out.println("主线程在执行其他任务");
            if (futureTask.get() != null) {
                //输出获取到的结果
                System.out.println("futureTask.get()-->" + futureTask.get());
            } else {
                //输出获取到的结果
                System.out.println("futureTask.get()未获取到结果");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("主线程执行完成");
    }

    static class Stats implements Callable<Integer> {
        private int sum = 1000;

        @Override
        public Integer call() throws Exception {
            System.out.println("Callable子线程开始计算啦！");
            Thread.sleep(2000);

            for (int i = 0; i < sum; i++) {
                sum--;
                System.out.println("正在输出值：" + i);
            }
            System.out.println("Callable子线程计算结束！");
            return sum;
        }
    }
}
