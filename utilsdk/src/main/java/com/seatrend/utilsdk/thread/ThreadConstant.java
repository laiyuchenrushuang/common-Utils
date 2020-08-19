package com.seatrend.utilsdk.thread;

import java.util.concurrent.TimeUnit;

/**
 * Created by ly on 2020/7/27 16:46
 */
public class ThreadConstant {
    static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    static final int CORE_POOL_SIZE = Math.max(2, Math.min(CPU_COUNT - 1, 4));
    static final int MAXIMUM_POOL_SIZE = CPU_COUNT * 2 + 1;//最大线程池队列大小
    static final int KEEP_ALIVE = 1;//保持存活时间，当线程数大于corePoolSize的空闲线程能保持的最大时间。
    static final TimeUnit SECONDS = TimeUnit.SECONDS;
    static final TimeUnit MINUTES = TimeUnit.MINUTES;
    static final TimeUnit HOURS = TimeUnit.HOURS;
    static final TimeUnit DAYS = TimeUnit.DAYS;
}
