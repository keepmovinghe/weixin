package com.keep.menu;

/**
 * click 类型按钮
 *
 * @author chenxh
 * @date 2017/6/27
 */
public class ClickButton extends Button {
    // 类型
    private String type;
    // key 值
    private String key;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
