package com.seatrend.utilsdk.httpserver.presenter;

import android.content.Context;

import com.seatrend.utilsdk.httpserver.HttpService;
import com.seatrend.utilsdk.httpserver.entity.CommonProgress;
import com.seatrend.utilsdk.httpserver.entity.CommonResponse;
import com.seatrend.utilsdk.httpserver.model.NormalModule;
import com.seatrend.utilsdk.httpserver.view.NormalView;

import java.io.File;
import java.util.Map;

public class NormalPresenter extends BasePresenter {

    private NormalModule mNormalModule;
    private NormalView mNormalView;

    public NormalPresenter(NormalView normalView) {
        super(normalView);
        mNormalModule = new NormalModule(this);
        mNormalView = normalView;
    }

    /**
     *
     * @param context
     * @param map
     * @param url
     * @param flag  Constants.Companion.GET  Constants.Companion.POST
     */
    @Override
    public void doNetworkTask(Context context, Map<String, String> map, String url,String flag) {
        mNormalModule.doWork(context, map, url,flag);
    }

    @Override
    public void doJsonPost(Context context, String json, String url) {
        HttpService.getInstance(context).getDataFromServerByJson(json, url, mNormalModule);
    }

    @Override
    public void requestResults(CommonResponse commonResponse, boolean isOk) {

        if (isOk) {
            mNormalView.netWorkTaskSuccess(commonResponse);
        } else {
            mNormalView.netWorkTaskfailed(commonResponse);
        }
    }

    public void downloadProgress(CommonProgress commonProgress) {
        mNormalView.downloadProgress(commonProgress);
    }

    public void downLoadFile(Context context,Map<String,String> map, String url, File file){
        mNormalModule.downLoadFile(context,map,url,file);
    }
}
