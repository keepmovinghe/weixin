package com.keep.servlet;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.pattern.PropertiesPatternConverter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author chenxh
 * @date 2017/6/27
 */
@WebServlet("/lo4jInitServlet")
public class Log4jInitServlet extends HttpServlet {
    private static final long serialVersionUID = 8377068660329147202L;

    public Log4jInitServlet(){
        super();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("Log4jInitServlet 正在初始化 log4j 日志设置信息");
        String log4jLocation = config.getInitParameter("log4j-properties-location");

        ServletContext sc = config.getServletContext();
        if(log4jLocation == null){
            System.err.println("*** 没有log4j-properties-location 初始化的文件，使用BasicConfiguration初始化");
            BasicConfigurator.configure();
        }else{
            String webAppPath = sc.getRealPath("/");
            String log4jProp = webAppPath + log4jLocation;
            File file = new File(log4jProp);
            if(file.exists()){
                System.out.println("使用："+log4jProp+"初始化日志设置信息");
                PropertyConfigurator.configure(log4jProp);
            }else{
                System.out.println("*** "+log4jProp+"文件没有找到，使用BasicConfiguration初始化");
                BasicConfigurator.configure();
            }
        }
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);
    }
}
