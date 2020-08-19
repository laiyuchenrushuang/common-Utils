package com.seatrend.utilsdk.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import com.seatrend.utilsdk.R;

/**
 * Created by ly on 2020/7/27 15:08
 */
public class ProgressUtils {
    private static AlertDialog dialog = null;
    private static View view;


    /**
     * 加载进度，默认进度条颜色：深灰色
     * @param context 上下文对象
     * @param loadInfoHints 加载进度提示内容
     */
    public static void showProgressBar(Context context, String loadInfoHints){
        view = LayoutInflater.from(context).inflate(R.layout.custom_progress_bar_view, null);
        TextView tv_load_progress_hint = view.findViewById(R.id.loading_title);
        // 设置加载进度提示内容
        if (!TextUtils.isEmpty(loadInfoHints)){
            tv_load_progress_hint.setText(loadInfoHints);
        }else {
            tv_load_progress_hint.setText("努力加载中...");
        }

        showDialog(context);// 创建对话框展示自定义进度条
    }

    /**
     * 显示自定义进度对话框
     * @param context
     */
    private static void showDialog(Context context) {

        dialog = new AlertDialog.Builder(context)
                .setView(view)
                .setCancelable(false)
                .create();

        dialog.show();
//
        WindowManager.LayoutParams  lp= dialog.getWindow().getAttributes();
        lp.width= 450;//定义宽度
        lp.height= WindowManager.LayoutParams.WRAP_CONTENT;//定义宽度
        dialog.getWindow().setAttributes(lp);
    }

    /**
     * 进度框消失
     */
    public static void dissmissProgressBar(){
        if (dialog != null){
            dialog.dismiss();
            dialog = null;
        }
    }
}
