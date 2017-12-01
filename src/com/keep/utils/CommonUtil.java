package com.keep.utils;

import com.keep.pojo.SNSUserInfo;
import com.keep.pojo.Token;
import com.keep.pojo.WeixinOauth2Token;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.chainsaw.Main;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.servlet.jsp.tagext.TryCatchFinally;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.nio.file.Watchable;
import java.security.SecureRandom;
import java.util.List;

/**
 *
 * @author chenxh
 * @date 2017/6/27
 */
public class CommonUtil {
    private static Logger logger = LogManager.getLogger(CommonUtil.class);

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
        return getToken(Constant.APPID,Constant.APPSECRET).getAccessToken();
    }
    /**
     * 从微信服务器获取接口访问凭证
     */
    private static Token getToken(String appid, String appsecret){
        Token token = null;
        String requestUrl = Constant.TOKEN_URL.replace("APPID",appid).replace("APPSECRET",appsecret);
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

    /**
     * 获取网页授权凭证
     * @param code
     * @return WeixinOauth2Token
     */
    public static WeixinOauth2Token getOauth2AccessToken(String code){
        WeixinOauth2Token wat = null;
        // 获取网页授权凭证
        String requestUrl = Constant.OAUTH2_TOKEN_URL.replace("APPID",Constant.APPID).replace("SECRET",Constant.APPSECRET).replace("CODE",code);
        wat = getWeixinOauth2Token(requestUrl,"获取网页授权凭证失败");
        return wat;
    }

    /**
     *  刷新网页授权凭证
     * @param refreshToken
     * @return WeixinOauth2Token
     */
    public static WeixinOauth2Token refreshOauth2AcccessToken(String refreshToken){
        WeixinOauth2Token wat = null;
        String requestUrl = Constant.REFRESH_OAUTH2_TOKEN_URL.replace("APPID",Constant.APPID).replace("REFRESH_TOKEN",refreshToken);
        // 刷新网页授权凭证
        wat = getWeixinOauth2Token(requestUrl,"刷新网页授权凭证失败");
        return wat;
    }

    /**
     * 请求 access_token
     * @param requestUrl
     * @param printMsg
     * @return
     */
    private static WeixinOauth2Token getWeixinOauth2Token(String requestUrl, String printMsg) {
        WeixinOauth2Token wat = null;
        JSONObject jsonObject = httpsRequest(requestUrl,"GET",null);
        if(null != jsonObject){
            try {
                wat = new WeixinOauth2Token();
                wat.setAccessToken(jsonObject.getString("access_token"));
                wat.setExpiresIn(jsonObject.getInt("expires_in"));
                wat.setRefreshToken(jsonObject.getString("refresh_token"));
                wat.setOpenId(jsonObject.getString("open_id"));
                wat.setScope(jsonObject.getString("scope"));
            }catch (Exception e){
                wat = null;
                int errorCode = jsonObject.getInt("errcode");
                String errorMsg = jsonObject.getString("errmsg");
                logger.info(printMsg + " errcode:{"+errorCode+"} errmsg:{"+errorMsg+"}");
            }
        }
        return wat;
    }

    /**
     *  通过网页授权获取用户信息
     * @param accessToken 网页授权接口调用凭证
     * @param openId 用户标识
     * @return SNSUserInfo
     */
    public static SNSUserInfo getSNSUserInfo(String accessToken, String openId){
        SNSUserInfo snsUserInfo = null;
        String requestUrl = Constant.SNS_USERINFO_URL.replace("ACCESS_TOKEN",accessToken).replace("OPENID",openId);
        JSONObject jsonObject = httpsRequest(requestUrl,"GET",null);
        if(null != jsonObject){
            try {
                snsUserInfo = new SNSUserInfo();
                snsUserInfo.setOpenId(jsonObject.getString("openid"));
                snsUserInfo.setNickName(jsonObject.getString("nickname"));
                snsUserInfo.setSex(jsonObject.getInt("sex"));
                snsUserInfo.setCountry(jsonObject.getString("country"));
                snsUserInfo.setProvince(jsonObject.getString("province"));
                snsUserInfo.setCity(jsonObject.getString("city"));
                snsUserInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));
                snsUserInfo.setPrivilegeList(JSONArray.toList(jsonObject.getJSONArray("privilege"),List.class));
            }catch (Exception e){
                snsUserInfo = null;
                int errorCode = jsonObject.getInt("errcode");
                String errorMsg = jsonObject.getString("errmsg");
                logger.info("获取用户信息失败 errcode:{"+errorCode+"} errmsg:{"+errorMsg+"}");
            }
        }
        return snsUserInfo;
    }
}
