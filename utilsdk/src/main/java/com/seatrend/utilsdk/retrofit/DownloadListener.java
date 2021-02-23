package com.seatrend.utilsdk.retrofit;

/**
 * Created by ly on 2020/6/30 16:31
 */
public interface DownloadListener {
    void onStart();//下载开始

    void onProgress(int progress);//下载进度

    void onFinish(String path);//下载完成

    void onFail(String errorInfo);//下载失败
}
