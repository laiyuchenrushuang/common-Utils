package com.seatrend.utilsdk.manager;

import android.app.Activity;

import java.util.List;
import java.util.Stack;

/**
 * desc:AppManager Activity栈管理,这是一个单例[Stack  管理  1. 获取元素方便  2. 后进先出]
 * 注意一个关系,finish 一个activity 会自动触发 stack remove
 */
public class AppManager {
    private static AppManager instance;
    private static Stack<Activity> activityStack;

    public static AppManager getInstance() {
        if (instance == null) {
            //懒汉双层锁,保证线程安全
            synchronized (AppManager.class) {
                if (instance == null) {
                    instance = new AppManager();
                }
            }
        }
        return instance;
    }

    /**
     * 添加Activity到stack中
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<>();
        }
        //处理重复启用的activity
        if (activityStack.contains(activity)) {
            activityStack.remove(activity);
        }
        activityStack.add(activity);
    }

    /**
     * 移除指定的Activity
     *
     * @param activity 指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
        }
    }

    /**
     * 移除当前的Activity
     */
    public void finishActivity() {
        if (null != activityStack && null != activityStack.lastElement()) {
            finishActivity(activityStack.lastElement());
        }
    }

    /**
     * 移除指定Class所对应的Activity
     */
    public void finishActivity(Class<?> cls) {
        /*Stack<Activity> activitys = new Stack<Activity>();
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                activitys.add(activity);
            }
        }

        for (Activity activity : activitys) {
            finishActivity(activity);
        }*/
        for (int i = 0; i < activityStack.size(); i++) {
            Activity activity = activityStack.get(i);
            if (activity.getClass().equals(cls)) {
                activity.finish();
            }
        }
    }

    /**
     * 获取stack中当前的Activity
     */
    public Activity currentActivity() {
        if (null != activityStack && activityStack.size() != 0 && null != activityStack.lastElement()) {
            return activityStack.lastElement();
        }
        return null;
    }

    /**
     * 移除所有的Activity
     */
    public void finishAllActivity() {
        if (activityStack == null)
            return;

        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    public void retainActivity(List<Class<?>> list) {

        for (int i = 0; i < activityStack.size(); i++) {
            Activity activity = activityStack.get(i);
            if (!list.contains(activity.getClass())) {
                activityStack.remove(i);
                activity.finish();
            }
        }

    }

    public int getActivityNumber() {
        return activityStack.size();
    }

    public List<Activity> getActivityStack() {
        return activityStack;
    }


    //除了指定一个activity 其他都关闭
    public void finishToOne(Class act) {
        int size = activityStack.size();
        for (int i = 0; i < size; i++) {
            Activity activity = activityStack.get(i);
            if (!activity.isFinishing()) {
                if (activity.getClass() == act) {
                    continue;
                } else {
                    activity.finish();
                }
            }
        }
    }

    /**
     * 如果显示重复活动。所以无法重启
     *
     * @param strClass 当前活动界面的名字
     * @return true  当前有界面在堆栈中
     * <p>
     * false  当前堆栈中此活动界面
     */
    public boolean repeatActivity(String strClass) {
        for (Activity activity : activityStack) {
            if (activity.getClass().getName().equals(strClass)) {
                return true;
            }
        }
        return false;
    }
}
