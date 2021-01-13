package com.seatrend.utilsdk.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;


@TargetApi(Build.VERSION_CODES.KITKAT)
public class SharedPreferencesUtils {

    /**
     * 获取服务地址
     * @return
     */
    public static String getNetworkAddress(Context context){
        return   context.getSharedPreferences(Constants.SETTING, Context.MODE_PRIVATE)
                .getString("key","http://www.joyseevip.com/fate");
//                .getString("key","http://192.168.0.218:8080/scrapCarSystem/");
    }

    /**
     * 设置服务地址
     * @param network
     */
    public static void setNetworkAddress(Context context,String network){
        context.getSharedPreferences(Constants.SETTING, Context.MODE_PRIVATE)
                .edit().putString("key",network).apply();
    }

    /**
     * 设置time
     * @param network
     */
    public static void setTime(Context context,String network){
        context.getSharedPreferences(Constants.SETTING, Context.MODE_PRIVATE)
                .edit().putString("time",network).apply();
    }
    /**
     * 获取time
     * @return  当前的时间戳
     */
    public static String getTime(Context context){
        return   context.getSharedPreferences(Constants.SETTING, Context.MODE_PRIVATE)
                .getString("time",StringUtils.longToStringData(System.currentTimeMillis()));
    }
}
