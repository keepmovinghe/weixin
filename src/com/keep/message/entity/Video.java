package com.keep.message.entity;

/**
 * 视频类
 *
 * @author chenxh
 * @date 2017/6/23
 */
public class Video {
    // 媒体文件 ID
    private String MediaId;
    // 缩略图的媒体 ID
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
