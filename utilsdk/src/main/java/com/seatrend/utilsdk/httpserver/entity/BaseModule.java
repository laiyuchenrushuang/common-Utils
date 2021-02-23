package com.seatrend.utilsdk.httpserver.entity;

import android.content.Context;

import java.util.Map;

public abstract class BaseModule {
    public abstract void doWork(Context context,Map<String, String> map, String url,String flag);
    public abstract void doWork2(Context context,Map<String, Integer> map, String url,String flag);
    public abstract void doWork3(Context context,Map<String, Object> map, String url,String flag);
    public abstract void doWorkResults(CommonResponse commonResponse,boolean isSuccess);
    public abstract void downloadProgress(CommonProgress commonResponse);
}
