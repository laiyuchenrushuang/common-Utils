package com.seatrend.utilsdk.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by ly on 2021/1/12 9:42
 */
public class TimeLimmitUtils {

    private static String LIMMIT_TIME = "2021-04-12 11:20:08";

    /**
     * 注意 网络请求需要在子线程中请求
     *
     * @param context
     * @param localTimeLimmit  如果网络请求失败，是否本地时间验证
     */
    public static void getNetTime(final Activity context, boolean localTimeLimmit) {
        URL url = null;//取得资源对象
        try {
            url = new URL("http://www.baidu.com");
            //url = new URL("http://www.ntsc.ac.cn");//中国科学院国家授时中心
            //url = new URL("http://www.bjtime.cn");
            URLConnection uc = url.openConnection();//生成连接对象
            uc.connect(); //发出连接
            long ld = uc.getDate(); //取得网站日期时间
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(ld);
            final String format = formatter.format(calendar.getTime());
            context.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    long one = StringUtils.date2Stamp(LIMMIT_TIME);

                    long two = StringUtils.date2Stamp(format);
                    SharedPreferencesUtils.setTime(context,format);//缓存

                    if (two >= one) {
                        Toast.makeText(context, "当前软件试用期已经到==", Toast.LENGTH_SHORT).show();
                        if(context !=null){
                            context.finish();

                        }else {
                            AppUtils.closeAppProcess();
                        }
                    }
                }
            });
        } catch (Exception e) {

            LogUtil.getInstance(null).d("  "+e.getMessage());
            if(!localTimeLimmit){
                Looper.prepare();
                Toast.makeText(context, "当前手机网络有故障", Toast.LENGTH_SHORT).show();
                Looper.loop();
                if(context !=null){
                    context.finish();
                }else {
                    AppUtils.closeAppProcess();
                }
            }else {
                localTimeLimmit(context);
            }
        }
    }

    private static void localTimeLimmit(Activity context) {
        Looper.prepare();
        String currentTimeStr = SharedPreferencesUtils.getTime(context);//当前时间  这个初始就是当前时间
        long currentTimeLong = StringUtils.date2Stamp(currentTimeStr);//当前long
        long limmitTimeLong = StringUtils.date2Stamp(LIMMIT_TIME);//限制long
        if(currentTimeLong >= limmitTimeLong){
            SharedPreferencesUtils.setTime(context,StringUtils.longToStringData(System.currentTimeMillis()));//缓存
            Toast.makeText(context, "当前软件试用期已经到>>", Toast.LENGTH_SHORT).show();
            context.finish();
        }else {
            SharedPreferencesUtils.setTime(context,StringUtils.longToStringData(System.currentTimeMillis()));//缓存
        }
        Looper.loop();
    }

}
