package com.keep.message.req;

/**
 * 链接消息类
 * Created by hdb on 2017/6/23.
 */
public class LinkMessage extends BaseMessage {
    // 消息标题
    private String Title;
    // 消息描述
    private String Description;
    // 消息链接
    private String Url;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }
}