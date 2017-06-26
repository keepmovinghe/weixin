package com.keep.utils;

import com.keep.message.resp.*;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 消息处理工具类
 * Created by hdb on 2017/6/26.
 */
public class MessageUtil {
    // 请求消息类型：文本
    public static final String REQ_MESSAGE_TYPE_TEXT = "text";
    // 请求消息类型：图片
    public static final String REQ_MESSAGE_TYPE_IMAGE = "image";
    // 请求消息类型：语音
    public static final String REQ_MESSAGE_TYPE_VOICE = "voice";
    // 请求消息类型：视频
    public static final String REQ_MESSAGE_TYPE_VIDEO = "video";
    // 请求消息类型：地理位置
    public static final String REQ_MESSAGE_TYPE_LOCATION = "location";
    // 请求消息类型：链接
    public static final String REQ_MESSAGE_TYPE_LINK = "link";
    // 请求消息类型：事件推送
    public static final String REQ_MESSAGE_TYPE_EVENT = "event";

    // 事件类型：subscribe（订阅）
    public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";
    // 事件类型：unsubscribe（消息订阅）
    public static final String EVENT_TYPE_UNSUBSCRIBE = "UNSUBSCRIBE";
    // 事件类型：scan（关注用户扫描带参数的二维码）
    public static final String EVENT_TYPE_SCAN = "scan";
    // 事件类型：location
    public static final String EVENT_TYPE_LOCATION = "location";
    // 事件类型：click（自定义菜单）
    public static final String EVENT_TYPE_CLICK = "click";

    // 响应消息类型：文本
    public static final String RESP_MESSAGE_TYPE_TEXT = "text";
    // 响应消息类型：图片
    public static final String RESP_MESSAGE_TYPE_IMAGE = "image";
    // 响应消息类型：语音
    public static final String RESP_MESSAGE_TYPE_VOICE = "voice";
    // 响应消息类型：视频
    public static final String RESP_MESSAGE_TYPE_VIDEO = "video";
    // 响应消息类型：图文
    public static final String RESP_MESSAGE_TYPE_NEWS = "news";
    // 响应消息类型：音乐
    public static final String RESP_MESSAGE_TYPE_MUSIC = "music";

    /**
     * 解析微信发来的请求（xml）
     * @param request
     * @return
     * @throws IOException
     * @throws DocumentException
     */
    public static Map<String, String> paresXml(HttpServletRequest request) throws IOException,DocumentException{
        // 将解析结果存储在HashMap中
        Map<String, String> map = new HashMap<>();

        // 从 request 中获得输入流
        InputStream inputStream = request.getInputStream();
        // 读取输入流
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        // 得到 XML 根元素
        Element root = document.getRootElement();
        // 得到根元素的所有子节点
        List<Element> elementList = root.elements();
        // 遍历所有子节点
        for(Element e : elementList){
            map.put(e.getName(),e.getText());
        }

        // 关闭释放资源
        if(inputStream != null){
            inputStream.close();
            inputStream = null;
        }

        return map;
    }

    /**
     * 扩展 xstream 使其支持 CDATA
     */
    private static XStream xStream = new XStream(new XppDriver(){
        public HierarchicalStreamWriter createWriter(Writer out){
            return new PrettyPrintWriter(out){
                // 对所有 XML 节点的转换都增加 CDATA 标记
                boolean cdata = true;

                public void starNode(String name,Class clazz){
                    super.startNode(name,clazz);
                }

                protected void writeText(QuickWriter writer, String text){
                    if(cdata){
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    }else{
                        writer.write(text);
                    }
                }
            };
        }
    });

    /**
     * 文本消息对象转换成 XML
     * @param testMessage
     * @return
     */
    public static String textMessageToXml(TextMessage testMessage){
        xStream.alias("xml",testMessage.getClass());
        return xStream.toXML(testMessage);
    }

    /**
     * 图片消息对象转换成 XML
     * @param imageMessage
     * @return
     */
    public static String imageMessageToXml(ImageMessage imageMessage){
        xStream.alias("xml",imageMessage.getClass());
        return xStream.toXML(imageMessage);
    }

    /**
     * 音频消息对象转换成 XML
     * @param voiceMessage
     * @return
     */
    public static String voiceMessageToXml(VoiceMessage voiceMessage){
        xStream.alias("xml",voiceMessage.getClass());
        return xStream.toXML(voiceMessage);
    }

    /**
     * 视频消息对象转换成 XML
     * @param videoMessage
     * @return
     */
    public static String videoMessageToXml(VideoMessage videoMessage){
        xStream.alias("xml",videoMessage.getClass());
        return xStream.toXML(videoMessage);
    }

    /**
     * 音乐消息对象转换成 XML
     * @param musicMessage
     * @return
     */
    public static String musicMessageToXml(MusicMessage musicMessage){
        xStream.alias("xml",musicMessage.getClass());
        return xStream.toXML(musicMessage);
    }

    /**
     * 图文消息对象转换成 XML
     * @param newsMessage
     * @return
     */
    public static String newsMessageToXml(NewsMessage newsMessage){
        xStream.alias("xml",newsMessage.getClass());
        return xStream.toXML(newsMessage);
    }

    /**
     * 将消息对象转换为 XML
     * @param message
     * @return
     */
    public static String messageToXml(BaseMessage message){
        xStream.alias("xml",message.getClass());
        return xStream.toXML(message);
    }
}
