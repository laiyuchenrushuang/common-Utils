package com.seatrend.utilsdk.utils;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.util.DisplayMetrics;

import androidx.core.content.FileProvider;

import com.seatrend.utilsdk.R;

import java.io.File;

/**
 * Created by ly on 2020/3/26 13:59
 */
public class AppUtils {
    public final static String WIDTH = "width";

    public final static String HEIGHT = "height";

    /**
     * px转dp
     *
     * @param context The context
     * @param px      the pixel value
     * @return value in dp
     */
    public static int pxToDp(Context context, float px) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return (int) ((px / displayMetrics.density) + 0.5f);
    }

    /**
     * dp转px
     *
     * @param context
     * @param dp
     * @return
     */
    public static int dpToPx(Context context, float dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return (int) ((dp * displayMetrics.density) + 0.5f);
    }

    /**
     * 获取状态栏高度
     *
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen",
                "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * 获取应用版本名
     *
     * @return 成功返回版本名， 失败返回null
     */
    public static String getVersionName(Context context) {
        PackageInfo packageInfo = getPackageInfo(context);
        if (packageInfo != null) {
            return packageInfo.versionName;
        }

        return null;
    }

    /**
     * @param context 上下文信息
     * @return 获取包信息
     * getPackageName()是当前类的包名，0代表获取版本信息
     */
    public static PackageInfo getPackageInfo(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            return packageManager.getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void installApp(Context context, String filePath) {
        File apkFile = new File(filePath);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(
                    context
                    , context.getString(R.string.authority)
                    , apkFile);
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
        }
        context.startActivity(intent);
    }

    //看设备是否支持NFC
    public static String getSystemProperty() {
        try {
            Class build = Class.forName("android.os.Build");
            String customName = (String) build.getDeclaredField("PWV_CUSTOM_CUSTOM").get(null);
            return customName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 清除缓存数据
     * @param context
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static void clearAppData(Context context){
        ActivityManager manager = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
        if (manager != null) {
            manager.clearApplicationUserData();
        }

    }

    /**
     * 判断当前应用是否是debug状态
     */

    public static boolean isApkInDebug(Context context) {
        try {
            ApplicationInfo info = context.getApplicationInfo();
            return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 是否是终端PDA
     * @return
     */
    public static boolean HCPDA(){
        if ("HC".equals(getSystemProperty())){
            return true;
        }
        return false;
    }

    /**
     * 关闭当前进程
     *
     */
    public static void closeAppProcess(){
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
