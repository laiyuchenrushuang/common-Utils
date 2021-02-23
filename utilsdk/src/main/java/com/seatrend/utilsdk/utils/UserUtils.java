package com.seatrend.utilsdk.utils;

import java.util.regex.Pattern;

//2. 帐号规则 4 - 12 位字母、数字
//3. 密码规则 6 - 20 位字母、数字
//4. 姓名规则 2 - 5 位中文
//5. 手机号码规则 11 位有效手机号
//6. 验证码规则数字即可
public class UserUtils {

    /**
     * 包含大小写字母及数字且
     * 是否包含
     * @param min  最低多少
     * @param max  最大多少
     * @param str
     * @return
     */
    public static boolean isLetterAndDigit(String str,int min,int max) {
        boolean isDigit = false;//定义一个boolean值，用来表示是否包含数字
        boolean isLetter = false;//定义一个boolean值，用来表示是否包含字母
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {   //用char包装类中的判断数字的方法判断每一个字符
                isDigit = true;
            } else if (Character.isLetter(str.charAt(i))) {  //用char包装类中的判断字母的方法判断每一个字符
                isLetter = true;
            }
        }
        String regex = "^[a-zA-Z0-9]{"+min+","+max+"}$";
        boolean isRight = isDigit && isLetter && str.matches(regex);
        return isRight;
    }

    /**
     * 是否包含大小写字母及数字或
     * @param str
     * @param min  最低多少
     * @param max  最大多少
     * @return
     */
    public static boolean isLetterOrDigit(String str,int min,int max) {
        String regex = "^[a-zA-Z0-9]{"+min+","+max+"}$";
        return str.matches(regex);
    }

    /**
     * 包含中文个数的范围  [] 关系
     * @param str
     * @param min
     * @param max
     * @return
     */
    public static boolean isContaintChinese(String str,int min,int max){
//        String regex1 ="[a-zA-Z]|[0-9]";
//        if(str.matches(regex1)){
//            return false;
//        }
        String regex = "[\u4e00-\u9fa5]{"+min+","+max+"}$";
        return str.matches(regex);
    }

    /**
     * 判断是否是11位的手机号
     * @param str
     * @return
     */
    public static boolean isPhoneNum(String str){
        String regex = "\\d{11}";
        return str.matches(regex);
    }

    /**
     * 判断验证码 长度 4位 6位
     * @param str
     * @return
     */
    public static  boolean isVerifyCode(String str){
        String regex4 = "\\d{4}";
        String regex6 = "\\d{6}";
        return str.matches(regex4)||str.matches(regex6);
    }
}
