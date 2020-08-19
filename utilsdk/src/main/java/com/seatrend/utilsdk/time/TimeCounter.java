package com.seatrend.utilsdk.time;

import com.seatrend.utilsdk.thread.ThreadPoolManager;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ly on 2020/8/11 9:36
 */
public class TimeCounter {

    private volatile long topTime = 60 * 1000;
    private long stepTime = 1000; //1s  默认
    private boolean offOn = true; //倒计时开关   true 开启  false 关闭

    private static TimeCounter instance = null;

    private static Timer timer = null;
    private static MyTimerTask timerTask = null;

    public static TimeCounter getInstance() {
        if (instance == null)
            synchronized (TimeCounter.class) {
                if (instance == null) {
                    instance = new TimeCounter();
                }
            }
        return instance;
    }

    //倒计时周期
    public TimeCounter setTopTime(long time) {
        this.topTime = time;
        return this;
    }

    //倒计时周期
    public long getTopTime() {
        return this.topTime;
    }

    //倒计时步长
    public TimeCounter setStepTime(long time) {
        this.stepTime = time;
        return this;
    }

    public void run(TimeCallBack listener) {
//
//        topTime = 60 * 1000;
//        timer = new Timer();
//        timerTask = new MyTimerTask(listener);
//        timer.schedule(timerTask, 0, stepTime);
    }

    private class MyTimerTask extends TimerTask {

        TimeCallBack listener;

        public MyTimerTask(TimeCallBack listener) {
            this.listener = listener;
        }

        @Override
        public void run() {
            if (topTime > 0) {
                topTime = topTime - stepTime;
                listener.countTimeBack(topTime);
            } else {
                listener.TimeOutBack();
                cancel();
            }
        }
    }

    ;

    // true 开启  false 关闭
    public TimeCounter offOn(boolean offOn) {
        this.offOn = offOn;
        return this;
    }

    public void cancel() {
        if (timerTask != null) {
            timerTask.cancel();
            timerTask = null;
        }
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        System.out.println("lylog  Cancel");
    }

    public interface TimeCallBack {
        //计时器按步长的时间返回
        void countTimeBack(long thisTime);

        //时间超时返回
        void TimeOutBack();

        //ERROR
//        void ErrorBack(String errorStr);
    }

    private void sleep(long time) throws InterruptedException {
        Thread.sleep(time);
    }

    //步长时间大于时间周期 这就尴尬了
    public class TimeIsSmallException extends Exception {
        TimeIsSmallException(String s) {
            super(s);
        }
    }
}
