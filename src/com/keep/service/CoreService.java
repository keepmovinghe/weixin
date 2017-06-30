package com.keep.service;

import com.keep.message.entity.Article;
import com.keep.message.resp.NewsMessage;
import com.keep.message.resp.TextMessage;
import com.keep.utils.Constant;
import com.keep.utils.MessageUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.lf5.viewer.categoryexplorer.TreeModelAdapter;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 核心服务类
 * Created by hdb on 2017/6/26.
 */
public class CoreService {

    private static Logger logger = LogManager.getLogger(CoreService.class);

    public static String processRequest(HttpServletRequest request){
        // XML 格式的消息数据
        String respXml = null;
        // 默认返回的文本消息内容
        String respContent = "未知的消息类型！";
        try{
            // 调用 paresXml 方法解析请求消息
            Map<String,String> requestMap = MessageUtil.paresXml(request);
            // 发送方账号
            String fromUserName = requestMap.get("FromUserName");
            // 开发者微信号
            String toUserName = requestMap.get("ToUserName");
            // 消息类型
            String msgType = requestMap.get("MsgType");

            logger.info("fromUserName:"+fromUserName);
            logger.info("toUserName:"+toUserName);
            logger.info("msgType:"+msgType);

            // 回复文本消息
            TextMessage textMessage = new TextMessage();
            textMessage.setToUserName(fromUserName);
            textMessage.setFromUserName(toUserName);
            textMessage.setCreateTime(new Date().getTime());
            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);

            // 文本消息
           /* if(MessageUtil.REQ_MESSAGE_TYPE_TEXT.equals(msgType)){
                respContent = "您发送的是文本消息！";
            }*/
            // 消息类型
            switch (msgType){
                // 文本消息
                case MessageUtil.REQ_MESSAGE_TYPE_TEXT:
                    respContent = "您发送的是文本消息！";
                    // 设置文本消息的内容
                    textMessage.setContent(respContent);
                    respXml = MessageUtil.messageToXml(textMessage);
                    break;
                // 事件推送
                case MessageUtil.REQ_MESSAGE_TYPE_EVENT:
                    // 事件类型
                    String eventType = requestMap.get("Event");
                    logger.info("enentType: {"+eventType+"}");
                    // 关注
                    if(MessageUtil.EVENT_TYPE_SUBSCRIBE.equals(eventType)){
                        respContent = "感谢您的关注！";
                        // 设置文本消息的内容
                        textMessage.setContent(respContent);
                        respXml = MessageUtil.messageToXml(textMessage);
                    }
                    // 取消关注
                    else if(MessageUtil.EVENT_TYPE_UNSUBSCRIBE.equals(eventType)){

                    }
                    // 上报地理位置信息
                    else if(MessageUtil.EVENT_TYPE_LOCATION.equals(eventType)){

                    }
                    // 扫描带参数的二维码
                    else if(MessageUtil.EVENT_TYPE_SCAN.equals(eventType)){
                        // TODO 处理扫描带参数的二维码事件
                    }
                    // 自定义菜单
                    else if(MessageUtil.EVENT_TYPE_CLICK.equals(eventType)){
                        // 事件 KEY 值，与创建菜单时的 key 值对应
                        String key = requestMap.get("EventKey");
                        logger.info("menuKey: {"+key+"}");
                        // 根据 key 值判断用户点击的按钮
                        if(Constant.MENU_KEY_OSCHINA.equals(key)){
                            Article article = new Article();
                            article.setTitle("开源中国");
                            article.setDescription("开源中国成立于2008 年 8 月。");
                            article.setUrl("http://m.oschina.net");
                            article.setPicUrl("https://www.oschina.net/build/oschina/components/imgs/oschina.svg?t=1484580008000");
                            List<Article> articleList = new ArrayList<>();
                            articleList.add(article);
                            // 创建图文消息
                            NewsMessage newsMessage = new NewsMessage();
                            newsMessage.setFromUserName(toUserName);
                            newsMessage.setToUserName(fromUserName);
                            newsMessage.setArticleCount(articleList.size());
                            newsMessage.setArticles(articleList);
                            newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
                            newsMessage.setCreateTime(new Date().getTime());

                            respXml = MessageUtil.newsMessageToXml(newsMessage);
                            logger.info("respXml:{"+respXml+"}");
                            break;
                        }else if(Constant.MENU_KEY_ITEYE.equals(key)){
                            respContent = "ITeye 即创办于 2003 年 9 月的 JavaEye，从最初以的讨论 Java 技术为主的技术论坛，已经逐渐发展成为涵盖整个软件开发领域的综合性网站。\n\nhttp://www.iteye.com";
                            // 设置文本消息的内容
                            textMessage.setContent(respContent);
                            respXml = MessageUtil.messageToXml(textMessage);
                        }
                    }
                    break;
                // 图片消息
                case MessageUtil.REQ_MESSAGE_TYPE_IMAGE:
                    respContent = "您发送的是图片消息！";
                    // 设置文本消息的内容
                    textMessage.setContent(respContent);
                    respXml = MessageUtil.messageToXml(textMessage);
                    break;
                // 链接消息
                case MessageUtil.REQ_MESSAGE_TYPE_LINK:
                    respContent = "您发送的是链接消息！";
                    // 设置文本消息的内容
                    textMessage.setContent(respContent);
                    respXml = MessageUtil.messageToXml(textMessage);
                    break;
                // 地理位置消息
                case MessageUtil.REQ_MESSAGE_TYPE_LOCATION:
                    respContent = "您发送的是地理位置消息！";
                    // 设置文本消息的内容
                    textMessage.setContent(respContent);
                    respXml = MessageUtil.messageToXml(textMessage);
                    break;
                // 视频消息
                case MessageUtil.REQ_MESSAGE_TYPE_VIDEO:
                    respContent = "您发送的是视频消息！";
                    // 设置文本消息的内容
                    textMessage.setContent(respContent);
                    respXml = MessageUtil.messageToXml(textMessage);
                    break;
                // 语音消息
                case MessageUtil.REQ_MESSAGE_TYPE_VOICE:
                    respContent = "您发送的是语音消息！";
                    // 设置文本消息的内容
                    textMessage.setContent(respContent);
                    respXml = MessageUtil.messageToXml(textMessage);
                    break;
            }
        }catch (Exception e){
            logger.error("{}",e);
        }

        return respXml;
    }
}
