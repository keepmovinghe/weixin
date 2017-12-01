package com.keep.servlet;

import com.keep.pojo.SNSUserInfo;
import com.keep.pojo.WeixinOauth2Token;
import com.keep.utils.CommonUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.omg.CORBA.Request;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author chenxh
 * @date 2017/11/30
 */
public class OAuthServlet extends HttpServlet {
    private static final long serialVersionUID = 4549782330923792514L;

    private static Logger logger = LogManager.getLogger(OAuthServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        // 用户同意授权后，获得 code
        String code = req.getParameter("code");
        logger.info("code : {"+code+"}");
        // 用户同意授权 （code 等于 "authdeny” 表示用户不同意授权，否则表示同意授权）
        if(!"authdeny".equals(code)){
            // 获取网页授权 access_token
            WeixinOauth2Token weixinOauth2Token = CommonUtil.getOauth2AccessToken(code);
            // 网页授权接口访问凭证
            String accessToken = weixinOauth2Token.getAccessToken();
            // 用户标识
            String openId = weixinOauth2Token.getOpenId();
            // 获取用户信息
            SNSUserInfo snsUserInfo = CommonUtil.getSNSUserInfo(accessToken,openId);

            // 设置要传递的参数
            req.setAttribute("snsUserInfo",snsUserInfo);
        }
        req.getRequestDispatcher("index.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
