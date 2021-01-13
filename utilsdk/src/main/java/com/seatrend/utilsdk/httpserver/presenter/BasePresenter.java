package com.seatrend.utilsdk.httpserver.presenter;

import android.content.Context;

import com.seatrend.utilsdk.httpserver.common.BaseView;
import com.seatrend.utilsdk.httpserver.entity.CommonResponse;

import java.util.Map;

public abstract class BasePresenter {
    public BaseView mView;
    public BasePresenter(BaseView bv){

        this.mView = bv;
    }
    public abstract void doNetworkTask(Context context,Map<String, String> map, String url,String flag);
    public abstract void doJsonPost(Context context,String json, String url);
    public abstract void requestResults(CommonResponse commonResponse, boolean isOk);
}
