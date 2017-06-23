package com.keep.message.resp;

import com.keep.message.entity.Voice;

/**
 * 回复语音消息类
 * Created by hdb on 2017/6/23.
 */
public class VoiceMessage extends BaseMessage {
    // 语音
    private Voice Voice;

    public com.keep.message.entity.Voice getVoice() {
        return Voice;
    }

    public void setVoice(com.keep.message.entity.Voice voice) {
        Voice = voice;
    }
}
