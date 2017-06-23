package com.keep.message.resp;

/**
 * 回复文本消息类
 * Created by hdb on 2017/6/23.
 */
public class TextMessage extends BaseMessage {
    // 回复的消息内容
    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
