package com.seatrend.utilsdk.httpserver.presenter;

import android.content.Context;

import com.seatrend.utilsdk.httpserver.HttpService;
import com.seatrend.utilsdk.httpserver.entity.CommonProgress;
import com.seatrend.utilsdk.httpserver.entity.CommonResponse;
import com.seatrend.utilsdk.httpserver.model.NormalModule;
import com.seatrend.utilsdk.httpserver.view.NormalView;
import com.seatrend.utilsdk.utils.LoadingDialog;

import java.io.File;
import java.util.HashMap;
import java.util.List;
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

        LoadingDialog.getInstance().showLoadDialog(context);

        if(map == null){
            map = new HashMap<>();
        }
        mNormalModule.doWork(context, map, url,flag);
    }

    @Override
    public void doNetworkTask2(Context context, Map<String, Integer> map, String url, String flag) {
        LoadingDialog.getInstance().showLoadDialog(context);

        if(map == null){
            map = new HashMap<>();
        }
        mNormalModule.doWork2(context, map, url,flag);
    }


    @Override
    public void doNetworkTask3(Context context, Map<String, Object> map, String url, String flag) {
        LoadingDialog.getInstance().showLoadDialog(context);

        if(map == null){
            map = new HashMap<>();
        }
        mNormalModule.doWork3(context, map, url,flag);
    }

    @Override
    public void doJsonPost(Context context, String json, String url) {
        LoadingDialog.getInstance().showLoadDialog(context);

        HttpService.getInstance(context).getDataFromServerByJson(json, url, mNormalModule);
    }

    @Override
    public void requestResults(CommonResponse commonResponse, boolean isOk) {
        LoadingDialog.getInstance().dismissLoadDialog();

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
        if(map == null){
            map = new HashMap<>();
        }
        mNormalModule.downLoadFile(context,map,url,file);
    }

    /**
     * 批量上传文件
     * @param context
     * @param url
     * @param files
     * @param map  传其他参数专用
     */
    public void uploadFiles(Context context,String url, List<File> files,Map<String,Object> map){
        LoadingDialog.getInstance().showLoadDialog(context);
        HttpService.getInstance(context).uploadFileToServer(url,files,map,mNormalModule);
    }
}
