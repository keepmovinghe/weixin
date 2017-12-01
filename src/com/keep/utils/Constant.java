package com.keep.utils;

/**
 *
 * @author chenxh
 * @date 2017/6/28
 */
public class Constant {
    // 公众号唯一标识
    public static final String APPID = "";
    // 密钥
    public static final String APPSECRET = "";
    // Token,与开发模式接口配置的Token保持一致
    public static final String TOKEN = "keepToken";

    // 菜单创建 URL POST
    public static final String MENU_CREATE_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
    // 菜单查询 URL GET
    public static final String MENU_GET_URL = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";
    // 菜单删除 URL GET
    public static final String MENU_DELETE_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
    // 获取凭证地址
    public static final String TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?" +
            "grant_type=client_credential&appid=APPID&secret=APPSECRET";
    // 获取网页授权地址
    public static final String OAUTH2_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?" +
            "appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
    // 刷新网页授权地址
    public static final String REFRESH_OAUTH2_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/refresh_token?" +
            "appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";
    // 通过网页授权获取用户信息地址
    public static final String SNS_USERINFO_URL = "https://api.weixin.qq.com/sns/userinfo?" +
            "access_token=ACCESS_TOKEN&openid=OPENID";
    // Oauth2 网页授权
    public static final String OAUTH2_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?" +
            "appid=APPID&redirect_uri=http%3A%2F%2Fkeepmoving.vicp.io%2FHelloWorldArchive%2FoauthServlet" +
            "&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";




    // 开源中国
    public static final String MENU_KEY_OSCHINA = "oschina";
    // iteye
    public static final String MENU_KEY_ITEYE = "iteye";
    //
}
