package com.seatrend.utilsdk.utils;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by ly on 2019/11/11 11:56
 * <p>
 * Copyright is owned by chengdu haicheng technology
 * co., LTD. The code is only for learning and sharing.
 * It is forbidden to make profits by spreading the code.
 */
public class LogUtil {

    private static LogUtil mLogUtil = null;

    public static LogUtil getInstance() {
        if (mLogUtil == null) {
            synchronized (LogUtil.class) {
                if (mLogUtil == null) {
                    mLogUtil = new LogUtil();
                }
            }
        }
        return mLogUtil;
    }

    public void d(String msg) {
        Log.d("[lylog]", msg);
    }


    /**
     * //文件的日志
     *
     * @param directoryName    目录名字
     * @param correctFileName  文件file 的名字
     * @param className        类名
     * @param methedName       方法名
     * @param whatCorrect      收集的内容
     * @param isApend          是否追加
     */
    public void correctLogMsg(String directoryName, String correctFileName, String className, String methedName, String whatCorrect, boolean isApend) {
        try {
            // 在SD卡目录下创建文件
            File file = new File(Environment.getExternalStorageDirectory(), directoryName);
            File childFile = new File(file, correctFileName+".txt");
            if (!file.exists()) {
                file.mkdir();
            }
            if(!childFile.exists()){
                childFile.createNewFile();
            }

            // 在SD卡目录下的文件，写入内容

            FileWriter fw = new FileWriter(childFile, isApend);
            fw.write("System time : "+StringUtils.longToStringData(System.currentTimeMillis())+"  Class name : "+className +"   Methed name :    "+methedName+" \n");
            fw.write("[CONTENT] ===>> "+whatCorrect +"\n");
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
