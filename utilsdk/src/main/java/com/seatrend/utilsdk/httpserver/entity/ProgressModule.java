package com.seatrend.utilsdk.httpserver.entity;

public abstract class ProgressModule extends BaseModule {
    public abstract void downloadProgress(CommonProgress commonProgress);
}
