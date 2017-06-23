package com.keep.message.event;

/**
 * 自定义菜单类
 * Created by hdb on 2017/6/23.
 */
public class MenuEvent extends BaseEvent {
    // 事件 KEY 值，与自定义菜单接口中 KEY 值对应
    private String EventKey;

    public String getEventKey() {
        return EventKey;
    }

    public void setEventKey(String eventKey) {
        EventKey = eventKey;
    }
}
