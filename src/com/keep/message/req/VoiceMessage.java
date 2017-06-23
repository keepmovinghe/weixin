package com.keep.message.req;

/**
 * 语音消息类
 * Created by hdb on 2017/6/23.
 */
public class VoiceMessage {
    // 媒体 ID
    private String MediaId;
    // 语音格式（amr)
    private String Formate;
    // 语音识别结果，UTF8编码
    private String Recognition;

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    public String getFormate() {
        return Formate;
    }

    public void setFormate(String formate) {
        Formate = formate;
    }

    public String getRecognition() {
        return Recognition;
    }

    public void setRecognition(String recognition) {
        Recognition = recognition;
    }
}
