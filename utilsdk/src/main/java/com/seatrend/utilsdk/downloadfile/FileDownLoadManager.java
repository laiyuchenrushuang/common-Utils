package com.seatrend.utilsdk.downloadfile;

import android.content.Context;

/**
 * Created by ly on 2021/1/19 16:11
 *
 * https://blog.csdn.net/wangwangli6/article/details/63255702
 *
 * https://www.jianshu.com/p/e113fe2aebd6
 *
 */
public class FileDownLoadManager {
    private FileDownLoadManager incetance;
    private Context mContext;

    public FileDownLoadManager(Context context) {
        this.mContext = context;
    }

    public FileDownLoadManager with(Context context) {
        if (incetance == null) {
            synchronized (FileDownLoadManager.class) {
                if (incetance == null) {
                    incetance = new FileDownLoadManager(context);
                }
            }
        }
        return incetance;
    }
}
