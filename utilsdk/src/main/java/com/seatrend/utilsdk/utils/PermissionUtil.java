package com.seatrend.utilsdk.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import androidx.core.app.ActivityCompat;

import java.util.ArrayList;

/**
 * Created by ly on 2020/5/22 16:39
 */
public class PermissionUtil {

    /**
     *
     * 主要针对单独的activity 才申请对应的permission 也是一种检查
     *
     * @param context     activity的上下文
     * @param back        都有执行的回调
     * @param permissions Manifest.permission的字符串数组
     */

    public static Boolean checkPermissions(Activity context, GetPermission back, String... permissions) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            ArrayList<String> permission = new ArrayList<String>();
            for (String db : permissions) {
                if (context.checkSelfPermission(db) != PackageManager.PERMISSION_GRANTED) {
                    permission.add(db);
                }
            }

            if (permission.size() > 0) {
                ActivityCompat.requestPermissions(context, permissions, 1);
                return false;
            } else {
                back.OnGetPermissionCallback();
                return true;
            }
        }
        return true; //版本低于M,请求就不行了
    }

    //无权限申请的接口回调
   public interface GetPermission {
        void OnGetPermissionCallback();
    }
}
