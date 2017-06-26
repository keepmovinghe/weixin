package com.keep.service;

import com.keep.message.resp.TextMessage;
import com.keep.utils.MessageUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * 核心服务类
 * Created by hdb on 2017/6/26.
 */
public class CoreService {

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

            System.out.println("--------------------------");
            System.out.println("fromUserName:"+fromUserName);
            System.out.println("toUserName:"+toUserName);
            System.out.println("msgType:"+msgType);
            System.out.println("--------------------------");

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
                    break;
                // 事件推送
                case MessageUtil.REQ_MESSAGE_TYPE_EVENT:
                    // 事件类型
                    String eventType = requestMap.get("Event");

                    // 关注
                    if(MessageUtil.EVENT_TYPE_SUBSCRIBE.equals(eventType)){
                        respContent = "感谢您的关注！";
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

                    }

                // 图片消息
                case MessageUtil.REQ_MESSAGE_TYPE_IMAGE:
                    respContent = "您发送的是图片消息！";
                    break;
                // 链接消息
                case MessageUtil.REQ_MESSAGE_TYPE_LINK:
                    respContent = "您发送的是链接消息！";
                    break;
                // 地理位置消息
                case MessageUtil.REQ_MESSAGE_TYPE_LOCATION:
                    respContent = "您发送的是地理位置消息！";
                    break;
                // 视频消息
                case MessageUtil.REQ_MESSAGE_TYPE_VIDEO:
                    respContent = "您发送的是视频消息！";
                    break;
                // 语音消息
                case MessageUtil.REQ_MESSAGE_TYPE_VOICE:
                    respContent = "您发送的是语音消息！";
                    break;
            }

            // 设置文本消息的内容
            textMessage.setContent(respContent);
            respXml = MessageUtil.messageToXml(textMessage);
        }catch (Exception e){
            e.printStackTrace();
        }

        return respXml;
    }
}
