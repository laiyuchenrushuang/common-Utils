package com.seatrend.utilsdk.httpserver.model;

import android.content.Context;

import com.seatrend.utilsdk.httpserver.HttpService;
import com.seatrend.utilsdk.httpserver.common.Constants;
import com.seatrend.utilsdk.httpserver.entity.BaseModule;
import com.seatrend.utilsdk.httpserver.entity.CommonProgress;
import com.seatrend.utilsdk.httpserver.entity.CommonResponse;
import com.seatrend.utilsdk.httpserver.presenter.NormalPresenter;

import java.io.File;
import java.util.Map;

public class NormalModule extends BaseModule {
    private NormalPresenter mNormalPresenter;

    public NormalModule(NormalPresenter np){
        super();
        this.mNormalPresenter = np;
    }

    @Override
    public void doWork(Context context,Map<String, String> map, String url,String flag) {

        HttpService.getInstance(context).getDataFromServer(map,url, flag, this);
    }

    @Override
    public void doWorkResults(CommonResponse commonResponse, boolean isSuccess) {
        mNormalPresenter.requestResults(commonResponse,isSuccess);
    }

    @Override
    public void downloadProgress(CommonProgress commonProgress) {
        mNormalPresenter.downloadProgress(commonProgress);
    }

    public void downLoadFile(Context context,Map<String,String> map, String url, File file){
        HttpService.getInstance(context).downLoadFileFromServer(map,file,url,Constants.Companion.GET,this);
    }
}
