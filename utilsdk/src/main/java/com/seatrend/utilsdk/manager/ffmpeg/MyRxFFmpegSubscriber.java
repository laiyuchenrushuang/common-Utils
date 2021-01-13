package com.seatrend.utilsdk.manager.ffmpeg;

import com.seatrend.utilsdk.ui.common.BaseFragment;

import java.lang.ref.WeakReference;

import io.microshow.rxffmpeg.RxFFmpegSubscriber;

/**
 * Created by ly on 2021/1/8 14:09
 */
public class MyRxFFmpegSubscriber extends RxFFmpegSubscriber {

    private CallBack mCallBack;

    public MyRxFFmpegSubscriber(CallBack cb) {
        this.mCallBack = cb;
    }

    @Override
    public void onFinish() {
        mCallBack.onFinish();
        this.dispose();
    }

    @Override
    public void onProgress(int progress, long progressTime) {

        mCallBack.onProgress(progress, progressTime);
    }

    @Override
    public void onCancel() {
        mCallBack.onCancel();
    }

    @Override
    public void onError(String message) {
        mCallBack.onError(message);
    }

  public  interface CallBack {
        void onProgress(int progress, long progressTime);

        void onError(String message);

        void onFinish();

        void onCancel();
    }
}
