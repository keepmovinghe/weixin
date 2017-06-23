package com.keep.message.req;

/**
 * 视频消息类
 * Created by hdb on 2017/6/23.
 */
public class VideoMessage extends BaseMessage {
    // 视频消息媒体 ID
    private String MediaId;
    // 视频消息缩略图的媒体 ID
    private String ThumbMediaId;

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    public String getThumbMediaId(){
        return ThumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId){
        ThumbMediaId = thumbMediaId;
    }
}
