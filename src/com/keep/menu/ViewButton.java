package com.keep.menu;

/**
 * view 类型按钮
 * Created by hdb on 2017/6/27.
 */
public class ViewButton extends Button {
    // 类型
    private String type;
    // url
    private String url;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
