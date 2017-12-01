package com.keep.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by chenxh on 2017/11/30.
 */
public class EncodeUrl {

    public static String urlEncodUTF8(String sourde){
        String result = sourde;
        try {
            result = URLEncoder.encode(sourde,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args){
        // 编码
        // http%3A%2F%2Fkeepmoving.vicp.io%2FHelloWorldArchive%2FoauthServlet
        String oauthUrl = "http://keepmoving.vicp.io/HelloWorldArchive/oauthServlet";
        System.out.print(urlEncodUTF8(oauthUrl));
    }
}
