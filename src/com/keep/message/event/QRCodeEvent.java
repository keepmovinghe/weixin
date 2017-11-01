package com.keep.message.event;


/**
 * 扫描带参数二维码事件
 *
 * @author chenxh
 * @date 2017/6/23
 */
public class QRCodeEvent extends BaseEvent {
    // 事件 KEY 值
    private String EventKey;
    // 用于换取二维码图片
    private String Tickey;

    public String getEventKey() {
        return EventKey;
    }

    public void setEventKey(String eventKey) {
        EventKey = eventKey;
    }

    public String getTickey() {
        return Tickey;
    }

    public void setTickey(String tickey) {
        Tickey = tickey;
    }
}
