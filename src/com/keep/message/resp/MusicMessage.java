package com.keep.message.resp;

import com.keep.message.entity.Music;

/**
 * 回复音乐消息类
 * Created by hdb on 2017/6/23.
 */
public class MusicMessage extends BaseMessage {
    // 音乐
    private Music Music;

    public com.keep.message.entity.Music getMusic() {
        return Music;
    }

    public void setMusic(com.keep.message.entity.Music music) {
        Music = music;
    }
}
