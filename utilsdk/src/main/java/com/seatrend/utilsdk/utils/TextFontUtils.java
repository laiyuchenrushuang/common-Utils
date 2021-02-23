package com.seatrend.utilsdk.utils;

/**
 * Created by ly on 2021/1/5 14:48
 */

import android.content.Context;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.text.Html;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.seatrend.utilsdk.R;

/**
 * <p>
 * 字体字符串工具类
 * </P>
 */
public class TextFontUtils {

    /**
     * 高亮字体的颜色
     */
    public static String HIGHLIGHT_COLOR = "#0fc264";

    /**
     * 使指定的字符串显示不同的颜色
     *
     * @param regexStr  高亮字符串
     * @param targetStr 原字符串
     * @param textView  文本框
     */
    public static void setHighlightFont(String regexStr, String targetStr, TextView textView) {
        targetStr = targetStr.replaceAll(regexStr, "<font color='" + HIGHLIGHT_COLOR + "'>" + regexStr + "</font>");
        textView.setText(Html.fromHtml(targetStr));
    }

    /**
     * TextView 字体渐变
     *
     * @param textView   文本框
     * @param startColor 起始颜色
     * @param endColor   终止颜色
     */
    public static void setGradientFont(TextView textView, String startColor, String endColor) {
        // Shader.TileMode.CLAMP：如果着色器超出原始边界范围，会复制边缘颜色
        LinearGradient gradient = new LinearGradient(0, 0, textView.getTextSize(),
                0,
                Color.parseColor(startColor), Color.parseColor(endColor),
                Shader.TileMode.CLAMP);

        textView.getPaint().setShader(gradient);
        // 直接调用invalidate()方法，请求重新draw()，但只会绘制调用者本身
        textView.invalidate();
    }

    /**
     * 为文字增加下划线
     *
     * @param text
     */
    public static void addUnderLine(Context context, TextView text) {
        Paint p = text.getPaint();
        p.setFlags(Paint.UNDERLINE_TEXT_FLAG);
        p.setColor(ContextCompat.getColor(context, R.color.black));
    }
}
