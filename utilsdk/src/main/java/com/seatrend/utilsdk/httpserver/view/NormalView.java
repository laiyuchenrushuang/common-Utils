package com.seatrend.utilsdk.httpserver.view;

import com.seatrend.utilsdk.httpserver.common.BaseView;
import com.seatrend.utilsdk.httpserver.entity.CommonProgress;
import com.seatrend.utilsdk.httpserver.entity.CommonResponse;

public interface NormalView extends BaseView {
    void netWorkTaskSuccess(CommonResponse commonResponse);
    void netWorkTaskfailed(CommonResponse commonResponse);
    void downloadProgress(CommonProgress commonProgress);
}
