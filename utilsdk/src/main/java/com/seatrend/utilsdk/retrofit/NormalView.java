package com.seatrend.utilsdk.retrofit;


import com.seatrend.utilsdk.retrofit.base.ConmmonResponse;

/**
 * Created by ly on 2020/6/30 17:21
 */
public interface NormalView {
    void netWorkTaskSuccess(ConmmonResponse commonResponse);
    void netWorkTaskfailed(ConmmonResponse commonResponse);
}
