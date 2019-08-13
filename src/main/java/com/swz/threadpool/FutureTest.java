package com.swz.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @Package: com.swz.threadpool
 * @Description: FutureTask使用
 * @author: swz
 * @date: 2019/5/10 16:51
 */
public class FutureTest {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        new FutureTest().exec();
    }

    void exec() throws InterruptedException, ExecutionException {
        //进行异步任务列表
        List<FutureTask<Integer>> futureTasks = new ArrayList<>();
        //线程池 初始化线程 和JDBC连接池是一个意思 实现重用
        ExecutorService executor = Executors.newCachedThreadPool();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            //创建一个异步任务
            FutureTask<Integer> futureTask = new FutureTask<>(new Stats());
            futureTasks.add(futureTask);
            //提交异步任务到线程池，让线程池管理任务。
            //由于是异步并行任务，所以这里并不会阻塞
            executor.submit(futureTask);
        }
        int count = 0;
        for (FutureTask<Integer> futureTask : futureTasks) {
            //futureTask.get(); //得到我们想要的结果
            //该方法有一个重载get(long timeout, TimeUnit unit) 第一个参数为最大等待时间，第二个为时间的单位
            count += futureTask.get();
        }
        long end = System.currentTimeMillis();
        System.out.println("线程池的任务全部完成:结果为:" + count + "，main线程关闭，进行线程的清理");
        System.out.println("使用时间：" + (end - start) + "ms");
        //清理线程池
        executor.shutdown();
    }

    static class Stats implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            Integer res = new Random().nextInt(100);
            Thread.sleep(1000);
            System.out.println("任务执行:获取到结果 :" + res);
            return res;
        }
    }

}
