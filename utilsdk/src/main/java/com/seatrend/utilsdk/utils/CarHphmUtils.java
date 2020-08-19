package com.seatrend.utilsdk.utils;

import android.text.method.ReplacementTransformationMethod;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ly on 2019/8/8 13:31
 * <p>
 * Copyright is owned by chengdu haicheng technology
 * co., LTD. The code is only for learning and sharing.
 * It is forbidden to make profits by spreading the code.
 */
public class CarHphmUtils {
    static String hpStr = "^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}$";

    //判断是否是号牌号码
    public static boolean macherString(String matherString) {
        Pattern pattern = Pattern.compile(hpStr);
        Matcher matcher = pattern.matcher(matherString);
        return matcher.matches();
    }


    /**
     * 获取省份的简称
     * @return
     */
    public static ArrayList<String> getProvince(){
        ArrayList list =  new ArrayList();
//        String[] jc={"京","津","冀","晋","蒙","辽","吉","黑","沪","苏","浙","皖","闽","赣","鲁",
//                "豫","鄂","湘","粤","桂","琼","渝","川","黔","滇","藏","陕","甘","青","宁","新","台","港","澳"};
        String[] jc = {"川","新"};
        for(String db : jc){
            list.add(db);
        }
        return list;
    }

    /**
     * 获取车牌A-Z
     * @return
     */
    public static ArrayList<String> getA_Z(){
        ArrayList list =  new ArrayList();
        String[] jc={"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        for(String db : jc){
            list.add(db);
        }
        return list;
    }

    //原本输入的小写字母
    public static class TransInformation extends ReplacementTransformationMethod {
        /**
         * 原本输入的小写字母
         */
        @Override
        protected char[] getOriginal() {
            return new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        }

        /**
         * 替代为大写字母
         */
        @Override
        protected char[] getReplacement() {
            return new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        }
    }
}
