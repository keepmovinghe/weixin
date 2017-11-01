package com.keep.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * 请求校验工具类
 *
 * @author chenxh
 * @date 2017/6/21
 */
public class SignUtil {
    // Token,与开发模式接口配置的Token保持一致
    private static String token = "keepToken";

    /**
     * 校验签名
     * @param signature 微信加密签名
     * @param timestamp 时间戳
     * @param nonce 随机数
     * @return
     */
    public static boolean checkSignature(String signature, String timestamp, String nonce){
        boolean result = false;
        // 对 token、timestamp、nonce 按字典排序
        String[] paramArr = new String[]{token,timestamp,nonce};
        Arrays.sort(paramArr);
        // 将排序后的结果拼成一个字符串
        String content = paramArr[0].concat(paramArr[1]).concat(paramArr[2]);
        String ciphertext = null;
        try {
            // 对拼接后的字符串进行sha1加密， 可使用 MessageDiegst 类来实现
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            // 对 content 加密
            byte[] digest = md.digest(content.getBytes());
            // 将加密后的字节数组转换成字符串
            ciphertext = byteToStr(digest);
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        // 将字符串 ciphertext 与 signatrue 比较，如果相等则是微信服务器返回
        return ciphertext != null ? ciphertext.equals(signature.toUpperCase()) : false;
    }

    /**
     * 将字节数组转换为十六进制字符串
     * @param bytearray
     * @return
     */
    private static String byteToStr(byte[] bytearray){
        String strDigest = "";
        for(int i=0;i<bytearray.length;i++){
            strDigest += byteToHexStr(bytearray[i]);
        }
        return strDigest;
    }

    /**
     * 将字节转换为十六进制字符串
     * @param ib
     * @return
     */
    private static String byteToHexStr(byte ib){
        char[] Digit = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        char[] ob = new char[2];
        ob[0] = Digit[(ib>>>4) & 0X0F];
        ob[1] = Digit[ib & 0X0F];

        String s = new String(ob);
        return s;
    }
}