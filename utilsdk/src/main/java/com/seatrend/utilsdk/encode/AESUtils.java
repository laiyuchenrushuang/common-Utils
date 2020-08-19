package com.seatrend.utilsdk.encode;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by ly on 2020/3/23 17:36
 * <p>
 * Copyright is owned by chengdu haicheng technology
 * co., LTD. The code is only for learning and sharing.
 * It is forbidden to make profits by spreading the code.
 */
public class AESUtils {

    //encrypt
    // decrypt

    /**
     * 算法/模式/补码方式
     */
    private static final String ALGORITHM = "AES/ECB/PKCS5Padding";
    private static final String AES_KEY = "abcd1234abcd1234";

    public static String encrypt(String content){
        try {

            //获得密码的字节数组
            byte[] raw = AES_KEY.getBytes();
            //根据密码生成AES密钥
            SecretKeySpec skey = new SecretKeySpec(raw, "AES");
            //根据指定算法ALGORITHM自成密码器
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            //初始化密码器，第一个参数为加密(ENCRYPT_MODE)或者解密(DECRYPT_MODE)操作，第二个参数为生成的AES密钥
            cipher.init(Cipher.ENCRYPT_MODE, skey);
            //获取加密内容的字节数组(设置为utf-8)不然内容中如果有中文和英文混合中文就会解密为乱码
            byte[] contentByte = content.getBytes("utf-8");
            //密码器加密数据
            byte[] contentEncode = cipher.doFinal(contentByte);
            //将加密后的数据转换为字符串返回
            return parseByte2HexStr(contentEncode);
        }catch (Exception e){
            e.printStackTrace();

        }
        return null;
    }


    public static String decrypt(String content){
        try {
            content = content.replace("\"", "");
            //获得密码的字节数组
            byte[] raw = AES_KEY.getBytes();
            //根据密码生成AES密钥
            SecretKeySpec skey = new SecretKeySpec(raw, "AES");
            //根据指定算法ALGORITHM自成密码器
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            //初始化密码器，第一个参数为加密(ENCRYPT_MODE)或者解密(DECRYPT_MODE)操作，第二个参数为生成的AES密钥
            cipher.init(Cipher.DECRYPT_MODE, skey);
            //把密文字符串转回密文字节数组
            //byte [] encode_content = Base64.decode(content,Base64.DEFAULT);
            byte [] encode_content = parseHexStr2Byte(content);
            //密码器解密数据
            byte [] byte_content = cipher.doFinal(encode_content);
            //将解密后的数据转换为字符串返回
            return new String(byte_content,"utf-8");
        }catch (Exception e){
            e.printStackTrace();

        }
        return null;
    }


    /**将二进制转换成16进制
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**将16进制转换为二进制
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length()/2];
        for (int i = 0;i< hexStr.length()/2; i++) {
            int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);
            int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }
}

