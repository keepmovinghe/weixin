package com.keep.message.req;

/**
 * 文本消息类
 * Created by hdb on 2017/6/23.
 */
public class TextMessage extends BaseMessage {
    // 消息内容
    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
