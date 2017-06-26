package com.keep.servlet;

import com.keep.service.CoreService;
import com.keep.utils.SignUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * Created by hdb on 2017/6/21.
 */
public class CoreServlet extends HttpServlet {
    private static final long serialVersionUID = 7700001531328067224L;

    private static final Logger logger  = LogManager.getLogger(CoreServlet.class);
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.debug("请求参数："+request);
        // 微信加密签名
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echostr = request.getParameter("echostr");

        System.out.println("-------------------");
        System.out.println("signature:"+signature);
        System.out.println("timestamp:"+timestamp);
        System.out.println("nonce:"+nonce);
        System.out.println("echostr:"+echostr);
        System.out.println("-------------------");
        // 若请求校验成功，则原样返回 echostr,表示接入成功，否则接入失败
        PrintWriter out = response.getWriter();
        if(SignUtil.checkSignature(signature,timestamp,nonce)){
            out.print(echostr);
        }
        if(out != null) {
            out.close();
            out = null;
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 将请求、响应编码均设置为UTF-8（防止中文乱码）
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // 接收参数：微信加密签名、时间戳、随机数
        String signature = request.getParameter("signature");
        String timestemp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");

        PrintWriter out = response.getWriter();
        // 请求校验
        if(SignUtil.checkSignature(signature,timestemp,nonce)){
            // 调用核心服务类接收处理请求
            String respXml = CoreService.processRequest(request);
            out.write(respXml);
            //TODO 消息的接收处理响应
        }

        if(out != null){
            out.close();
            out = null;
        }
    }
}
