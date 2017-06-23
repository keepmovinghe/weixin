package com.keep.message.resp;

import com.keep.message.entity.Video;

/**
 * 回复视频消息类
 * Created by hdb on 2017/6/23.
 */
public class VideoMessage extends BaseMessage {
    // 视频
    private Video Video;

    public com.keep.message.entity.Video getVideo() {
        return Video;
    }

    public void setVideo(com.keep.message.entity.Video video) {
        Video = video;
    }
}
