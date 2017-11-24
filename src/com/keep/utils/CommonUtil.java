package com.keep.utils;

import com.keep.pojo.Token;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.security.SecureRandom;

/**
 *
 * @author chenxh
 * @date 2017/6/27
 */
public class CommonUtil {
    private static Logger logger = LogManager.getLogger(CommonUtil.class);
    private static final String APPID = "";
    private static final String APPSECRET = "";
    // 获取凭证地址
    public static final String TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    /**
     * 发送 http 请求
     * @param requestUrl
     * @param requestMethod
     * @param outputStr
     * @return
     */
    public static JSONObject httpsRequest(String requestUrl, String requestMethod, String outputStr){
        JSONObject jsonObject = null;
        try {
            // 创建 SSLContext 对象，并使用指定的信任管理器初始化
            TrustManager[] tm = {new MyX509TrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL","SunJSSE");
            sslContext.init(null,tm,new SecureRandom());
            // 从上述 SSLContext 对象中得到 SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(requestUrl);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setSSLSocketFactory(ssf);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(true);
            // 设置请求方式（get/post）
            conn.setRequestMethod(requestMethod);
            // 当 outputStr 不为空时，向输出流写入数据
            if(null != outputStr){
                OutputStream outputStream = conn.getOutputStream();
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }
            // 从输入流读取返回内容
            InputStream inputStream = conn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            StringBuffer stringBuffer = new StringBuffer();
            while ((str = bufferedReader.readLine()) != null){
                stringBuffer.append(str);
            }
            // 释放资源
            bufferedReader.close();
            inputStream.close();
            inputStream.close();
            inputStream = null;
            conn.disconnect();
            jsonObject = JSONObject.fromObject(stringBuffer.toString());

        }catch (ConnectException ce){
            logger.error("连接超时：{}",ce);
        }catch (Exception e){
            logger.error("https请求异常：{}",e);
        }

        return jsonObject;
    }

    /**
     * 获取接口访问凭证，如果本地 token 失效，从微信服务器获取
     * @return
     */
    public static String getToken(){
        return getToken(APPID,APPSECRET).getAccessToken();
    }
    /**
     * 从微信服务器获取接口访问凭证
     */
    private static Token getToken(String appid, String appsecret){
        Token token = null;
        String requestUrl = TOKEN_URL.replace("APPID",appid).replace("APPSECRET",appsecret);
        // 发起 GET 请求获取凭证
        JSONObject jsonObject = httpsRequest(requestUrl,"GET", null);
        if(null != jsonObject){
            try {
                token = new Token();
                token.setAccessToken(jsonObject.getString("access_token"));
                token.setExpiresIn(jsonObject.getInt("expires_in"));
            }catch (JSONException e){
                token = null;
                logger.error("获取 token 失败 errcode:{"+jsonObject.getInt("errcode")+"} errmsg{"+jsonObject.getString("errmsg")+"}");
            }
        }
        return token;
    }
}
