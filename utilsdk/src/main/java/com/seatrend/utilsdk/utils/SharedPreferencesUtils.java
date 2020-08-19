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
                .getString("key","");
    }

    /**
     * 设置服务地址
     * @param network
     */
    public static void setNetworkAddress(Context context,String network){
        context.getSharedPreferences(Constants.SETTING, Context.MODE_PRIVATE)
                .edit().putString("key",network).apply();
    }


}
