package com.seatrend.utilsdk.utils;

import java.text.DecimalFormat;

public class NumberUtils {
    /**
     *
     * @param pi  数值
     * @param format  格式 小数点一位 "0.0" 同理小数点5位 "0.00000"
     * @return string 结果
     */
    public static String getFormatNum(double pi,String format){
        DecimalFormat df = new DecimalFormat(format);

        return df.format(pi);
    }
}
