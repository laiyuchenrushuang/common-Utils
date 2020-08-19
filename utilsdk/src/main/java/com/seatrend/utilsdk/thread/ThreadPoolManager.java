package com.seatrend.utilsdk.thread;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程工具类
 * <p>
 * 【Runnable】
 * timer 定时线程 开放接口（cancel） 【独立】 newScheduledThreadPool
 * <p>
 * 线程池的其他接口，适合其他接口，但是不适合cancel【独立】 threadPool
 * <p>
 * 【Thread】
 * 为了扩展Thread 的操作 这里单独搞Thread实现
 * <p>
 * Created by ly on 2020/3/9 14:05
 */
public class ThreadPoolManager {

    private boolean fifo = true;//FIFO 是否 先进先出   LIFO 后进先出
    private static ThreadPoolManager instance = null;

    public static ThreadPoolManager getInstance() {
        if (instance == null)
            synchronized (ThreadPoolManager.class) {
                if (instance == null)
                    instance = new ThreadPoolManager();
            }
        return instance;
    }

    private ThreadPoolManager setQueueMode(boolean flag) {
        this.fifo = flag;
        return this;
    }

    /**
     * 设置核心线程池大小
     */
    private ThreadPoolManager setCorePoolSize(int corePoolSize) {
        threadPool.setCorePoolSize(corePoolSize);
        return this;
    }

    // 线程池
    private ThreadPoolExecutor threadPool = new PriorityExecutor(fifo);

    //timer 控制器
    private Timer timer;

    /**
     * 从线程池中抽取线程，执行指定的Runnable对象
     *
     * @param runnable
     */
    public void execute(Runnable runnable) {
        threadPool.execute(runnable);
    }

    /**
     * 判断当前线程池是否繁忙
     *
     * @return
     */
    private boolean isBusy() {
        return threadPool.getActiveCount() >= threadPool.getCorePoolSize();
    }

    /**
     * 關閉線程池
     */
    private void showdown() {
        threadPool.shutdown();
    }

    /**
     * 立即關閉線程池
     */
    private void showdownNow() {
        threadPool.shutdownNow();
    }


    /**
     * 保持线程活动时间
     */
    private ThreadPoolManager setKeepAliveTime(Long time, TimeUnit unit) {
        threadPool.setKeepAliveTime(time, unit);
        return this;
    }

    /**
     * 线程池大小
     */
    private ThreadPoolManager setMaximumPoolSize(int maximumPoolSize) {
        threadPool.setMaximumPoolSize(maximumPoolSize);
        return this;
    }

    /**
     * 開啟線程池（重新，构造建议不选这个）
     */
    private void open() {
        threadPool = new PriorityExecutor(fifo);
    }

    /**
     * 定时任务（安全?）
     * <p>
     * runable 线程
     * delay 延迟多久后执行
     * period 执行周期
     */

    public void schedule(final Runnable runnable, final long delay, final Long period) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                if (delay > 0) {
                    try {
                        Thread.sleep(delay);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                while (true) {
                    threadPool.execute(runnable);
                    if (period > 0) {
                        try {
                            Thread.sleep(period);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }

    /**
     * 定时安全任务池
     * <p>
     * runable 线程
     * delay 延迟多久后执行
     * period 执行周期(轮询)
     */
    public void createSchedulePool(final Runnable runnable, final long delay, final Long period) {
        if(timer ==null){
            timer = new Timer();
        }
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(ThreadConstant.CORE_POOL_SIZE);
                newScheduledThreadPool.schedule(runnable, 0, TimeUnit.SECONDS);
            }
        }, delay, period);
    }


    /**
     * 定时安全任务池
     * <p>
     * runable 线程
     * delay 延迟多久后执行
     */
    private void createSchedulePool(final Runnable runnable, final long delay) {

        if(timer ==null){
            timer = new Timer();
        }
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(ThreadConstant.CORE_POOL_SIZE);
                newScheduledThreadPool.schedule(runnable, 0, TimeUnit.SECONDS);
            }
        }, delay);
    }

    /**
     * timer 取消
     */
    public void cancel() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }
}
