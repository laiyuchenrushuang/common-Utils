package com.seatrend.utilsdk.thread;

/**
 * 优先线程元素
 */
public class PriorityRunnable implements Runnable {

    public final Priority.Level priority;//任务优先级
    private final Runnable runnable;//任务真正执行者
    /*package*/public long SEQ;//任务唯一标示

    public PriorityRunnable(Priority.Level priority, Runnable runnable) {
        this.priority = priority == null ? Priority.Level.NORMAL : priority;
        this.runnable = runnable;
    }

    @Override
    public final void run() {
        this.runnable.run();
    }
}
