package com.keep.pojo;

/**
 * 凭证类
 * Created by hdb on 2017/6/27.
 */
public class Token {
    // 接口访问凭证
    private String accessToken;
    // 凭证有效期
    private int expiresIn;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }
}
