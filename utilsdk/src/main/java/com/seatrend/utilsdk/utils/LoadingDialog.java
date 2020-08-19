package com.seatrend.utilsdk.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.seatrend.utilsdk.R;


/**
 * Created by seatrend on 2018/8/28.
 */


public class LoadingDialog {


    private static LoadingDialog mLoadingDialog;

    private LoadingDialog() {
    }

    private Dialog mDialog;

    public static LoadingDialog getInstance() {
        if (mLoadingDialog == null) {
            mLoadingDialog = new LoadingDialog();
        }
        return mLoadingDialog;
    }


    public void showLoadDialog(Context context) {
        if (mDialog != null) {
            mDialog.dismiss();
        }

        try {
            mDialog = new Dialog(context);
            View view = LayoutInflater.from(context).inflate(R.layout.dialog_loading_animation, null);
            mDialog.setContentView(view);
            mDialog.setCanceledOnTouchOutside(false);
//        //设置界面必须代码同步成功，返回键不会让dialog消失?
//        if(context instanceof SettingActivity){
//            mDialog.setCancelable(false);
//        }
            mDialog.show();
            mDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {

                }
            });
        } catch (Exception e) {
            Looper.prepare();
            mDialog = new Dialog(context);
            View view = LayoutInflater.from(context).inflate(R.layout.dialog_loading_animation, null);
            mDialog.setContentView(view);
            mDialog.setCanceledOnTouchOutside(false);
//        //设置界面必须代码同步成功，返回键不会让dialog消失?
//        if(context instanceof SettingActivity){
//            mDialog.setCancelable(false);
//        }
            mDialog.show();
            mDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {

                }
            });
            Looper.loop();
        }
    }

//    public  void showLoadDialog(Context context){
//        mDialog = new Dialog(context);
//        View view = LayoutInflater.from(context).inflate(R.layout.dialog_loading, null);
//        mDialog.setContentView(view);
//        mDialog.setCanceledOnTouchOutside(false);
//
//        ImageView ivBall=view.findViewById(R.id.iv_ball);
//        ImageView ivBicycle=view.findViewById(R.id.iv_bicycle);
//        RotateAnimation  rotateAnimation = new RotateAnimation(0,
//                360,Animation.RELATIVE_TO_SELF,0.5f, Animation.RELATIVE_TO_SELF,0.5f);
//        rotateAnimation.setDuration(1000);
//        rotateAnimation.setFillAfter(true);
//        rotateAnimation.setRepeatMode(Animation.RESTART);
//        rotateAnimation.setInterpolator(new LinearInterpolator());
//        rotateAnimation.setRepeatCount(-1);
//        ivBall.setAnimation(rotateAnimation);
//
//
//        TranslateAnimation translateAni = new TranslateAnimation(
//                Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF,
//                0, Animation.RELATIVE_TO_SELF, 0,
//                Animation.RELATIVE_TO_SELF, 0.2f);
//
//        translateAni.setDuration(1000);
//        translateAni.setRepeatCount(-1);
//        // 设置动画模式（Animation.REVERSE设置循环反转播放动画,Animation.RESTART每次都从头开始）
//        translateAni.setRepeatMode(Animation.REVERSE);
//        ivBicycle.startAnimation(translateAni);
//
//        mDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
//            @Override
//            public void onDismiss(DialogInterface dialog) {
//
//            }
//        });
//        mDialog.show();
//
//    }

    public void showTipDialog(Context context, String tipsMsg, final OnClickListener listener) {
        if (mDialog != null) {
            mDialog.dismiss();
        }

        try {
            mDialog = new Dialog(context);
            mDialog.setContentView(R.layout.dialog_tip);
            mDialog.setCanceledOnTouchOutside(true);
            TextView tvMsg = mDialog.findViewById(R.id.tv_msg);
            Button btnOk = mDialog.findViewById(R.id.btn_ok);
            Button btnNo = mDialog.findViewById(R.id.btn_no);
            tvMsg.setText(tipsMsg);
            btnNo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDialog.dismiss();
                    listener.btnCancelClick();
                }
            });
            btnOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDialog.dismiss();
                    listener.btnOkClick();
                }
            });
            mDialog.show();
        } catch (Exception e) {
            Toast.makeText(context, "showErrorDialog has Exception", Toast.LENGTH_SHORT)
                    .show();
        }
    }

    public void showErrorDialog(Context context, String tipsMsg) {

        try {
            mDialog = new Dialog(context);
            mDialog.setContentView(R.layout.dialog_error);
            mDialog.setCanceledOnTouchOutside(true);
            TextView tvMsg = mDialog.findViewById(R.id.tv_msg);
            Button btnOk = mDialog.findViewById(R.id.btn_ok);
            tvMsg.setText(tipsMsg);
            btnOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDialog.dismiss();
                }
            });
            mDialog.show();
        } catch (Exception e) {
            try {
                Looper.prepare();
                mDialog = new Dialog(context);
                mDialog.setContentView(R.layout.dialog_error);
                mDialog.setCanceledOnTouchOutside(true);
                TextView tvMsg = mDialog.findViewById(R.id.tv_msg);
                Button btnOk = mDialog.findViewById(R.id.btn_ok);
                tvMsg.setText(tipsMsg);
                btnOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mDialog.dismiss();
                    }
                });
                mDialog.show();
                Looper.loop();
            } catch (Exception e1) {
                ToastUtil.show(context,"showErrorDialog Exception");
            }
        }

    }


    public void dismissLoadDialog() {
        try {
            if (mDialog != null) {
                mDialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
            mDialog = null;
        }
    }

    public boolean dialogShowing() {
        try {
            if (mDialog != null && mDialog.isShowing()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public interface OnClickListener {
        void btnOkClick();

        void btnCancelClick();
    }
}
