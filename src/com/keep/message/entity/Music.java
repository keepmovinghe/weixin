package com.keep.message.entity;

/**
 * 音乐类
 *
 * @author chenxh
 * @date 2017/6/23
 */
public class Music {
    // 音乐标题
    private String Title;
    //  描述
    private String Description;
    // 音乐链接地址
    private String MusicUrl;
    // 高质量音乐链接，Wi-Fi环境优先使用该链接播放音乐
    private String HQMusicUrl;
    // 缩略图的媒体 ID 通过上传多媒体文件得到ID
    private String ThumbMediaId;

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

    public String getMusicUrl() {
        return MusicUrl;
    }

    public void setMusicUrl(String musicUrl) {
        MusicUrl = musicUrl;
    }

    public String getHQMusicUrl() {
        return HQMusicUrl;
    }

    public void setHQMusicUrl(String HQMusicUrl) {
        this.HQMusicUrl = HQMusicUrl;
    }

    public String getThumbMediaId() {
        return ThumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        ThumbMediaId = thumbMediaId;
    }
}
