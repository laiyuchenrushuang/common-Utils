package com.seatrend.utilsdk.thread;


import androidx.annotation.NonNull;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;


/**
 * 优先加载器
 *
 * Created by ly on 2020/3/12 14:43
 */
public class PriorityExecutor extends ThreadPoolExecutor {

    private static final AtomicLong SEQ_SEED = new AtomicLong();//主要获取添加任务

    /**
     * 创建线程工厂
     */
    private static final ThreadFactory sThreadFactory = new ThreadFactory() {
        private final AtomicInteger mCount = new AtomicInteger(1);

        @Override
        public Thread newThread(@NonNull Runnable runnable) {
            return new Thread(runnable, "download#" + mCount.getAndIncrement());
        }
    };

    /**
     * 默认工作线程数5
     *
     * @param fifo 优先级相同时, 等待队列的是否优先执行先加入的任务.
     */
    public PriorityExecutor(boolean fifo) {
        this(ThreadConstant.CORE_POOL_SIZE, fifo);
    }

    /**
     * @param poolSize 工作线程数
     * @param fifo     优先级相同时, 等待队列的是否优先执行先加入的任务.
     */
    public PriorityExecutor(int poolSize, boolean fifo) {
        this(poolSize, ThreadConstant.MAXIMUM_POOL_SIZE, ThreadConstant.KEEP_ALIVE, TimeUnit.SECONDS, new PriorityBlockingQueue<>(ThreadConstant.MAXIMUM_POOL_SIZE, fifo ? Priority.FIFO : Priority.LIFO), sThreadFactory);
    }

    public PriorityExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }

    /**
     * 提交任务
     * @param runnable
     */
    @Override
    public void execute(Runnable runnable) {
        if (runnable instanceof PriorityRunnable) {
            ((PriorityRunnable) runnable).SEQ = SEQ_SEED.getAndIncrement();
        }
        super.execute(runnable);
    }
}
