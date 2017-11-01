package com.keep.message.req;

/**
 * 图片消息类
 *
 * @author chenxh
 * @date 2017/6/23
 */
public class ImageMessage extends BaseMessage {
    // 图片链接
    private String PicUrl;

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }
}
