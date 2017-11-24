package com.keep.main;

import com.keep.message.entity.Article;
import com.keep.message.entity.Music;
import com.keep.message.resp.NewsMessage;
import com.keep.utils.CommonUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * 微客服管理类（向关注者发送消息）
 * @author chenxh
 * @date 2017/11/1
 */
public class SendMessageManager {

    // 日志类
    private static Logger logger = LogManager.getLogger(SendMessageManager.class);
    // 客服接口请求地址
    private static final String SEND_MESSAGE_REQUEST_URL = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";

    public static boolean sendCustomMessage(String accessToken, String jsonMsg){
        logger.info("消息内容：{"+jsonMsg+"}");
        boolean result = false;
        // 发送客服消息
        JSONObject jsonObject = CommonUtil.httpsRequest(SEND_MESSAGE_REQUEST_URL.replace("ACCESS_TOKEN",accessToken),"POST",jsonMsg);
        if(null != jsonObject){
            int errorCode = jsonObject.getInt("errcode");
            String errorMsg = jsonObject.getString("errmsg");
            if(0 == errorCode){
                result = true;
                logger.info("客服消息发送成功  errcode:{"+errorCode+"} errmsg:{"+errorMsg+"}");
            }else{
                logger.info("客服消息发送失败 errcode:{"+errorCode+"} errmsg:{"+errorMsg+"}");
            }
        }
        return result;
    }
    /**
     * 组装文本客服消息
     * @param openId 消息发送对象
     * @param content 文本消息内容
     * @return
     */
    public static String makeTextCustomMessage(String openId, String content){
        // 对消息中的双引号进行转义
        content = content.replace("\"","\\\"");
        String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"text\",\"text\":{\"content\":\"%s\"}}";
        return String.format(jsonMsg,openId,content);
    }

    /**
     * 组装图片客服消息
     * @param openId 消息发送对象
     * @param mediaId 媒体文件 ID
     * @return
     */
    public static String makeImageCustomMessage(String openId, String mediaId){
        String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"image\",\"image\":{\"media_id\":\"%s\"}}";
        return String.format(jsonMsg,openId,mediaId);
    }

    /**
     * 组装语音客服消息
     * @param openId 消息发送对象
     * @param mediaId 媒体文件 ID
     * @return
     */
    public static String makeVoiceCustomMessage(String openId, String mediaId){
        String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"voice\",\"voice\":{\"media_id\":\"%s\"}}";
        return String.format(jsonMsg,openId,mediaId);
    }

    /**
     * 组装视频客服消息
     * @param openId 消息发送对象
     * @param mediaId 媒体文件 ID
     * @param thumbMediaId 视频消息缩略图的媒体 ID
     * @return
     */
    public static String makeVideoCustomMessage(String openId, String mediaId, String thumbMediaId){
        String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"video\",\"video\":{\"media_id\":\"%s\",\"thumb_media_id\":\"%s\"}}";
        return String.format(jsonMsg,openId,mediaId,thumbMediaId);
    }

    /**
     * 组装音乐客服消息
     * @param openId 消息发送对象
     * @param music 音乐对象
     * @return
     */
    public static String makeMusicCustomMessage(String openId, Music music){
        String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"music\",\"music\":%s}";
        jsonMsg = String.format(jsonMsg,openId, JSONObject.fromObject(music).toString());
        jsonMsg.replace("ThumbMediaId","thumb_media_id");
        return jsonMsg;
    }

    /**
     * 组装图文客服消息
     * @param openId
     * @param articleList
     * @return
     */
    public static String makeNewsCustomMessage(String openId, List<Article> articleList){
        String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"news\",\"news\":{\"article\":%s}}";
        jsonMsg = String.format(jsonMsg,openId, JSONArray.fromObject(articleList).toString().replace("\"","\\\""));
        // 将 json 中的 PicUrl 替换成 pic_url
        jsonMsg.replace("PicUrl","pic_url");
        return  jsonMsg;
    }

    public static void main(String[] args){

        String accessToken = CommonUtil.getToken();
        String jsonMsg = makeTextCustomMessage("oEQfovz6XhPMdKHz6H4yJ6iZz-aA","测试发送客服消息接口！");
        sendCustomMessage(accessToken,jsonMsg);
    }
}
