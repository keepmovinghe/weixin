package com.keep.message.req;

/**
 * 小视频消息类
 * @author chenxh
 * @date 2017/11/2
 */
public class ShortvideoMessage extends BaseMessage {

    // 视频消息媒体id，可以调用多媒体文件下载接口拉取数据。
    private String MediaId;
    // 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。
    private String ThumbMediaId;

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    public String getThumbMediaId() {
        return ThumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        ThumbMediaId = thumbMediaId;
    }
}
