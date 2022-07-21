package com.lhh.lnstagram.filehttps;

import java.io.Serializable;

/**
 * 微软云存储RequestBody封装
 */
public class AzureRequestBody implements Serializable {

    private int code;
    private String md5;
    private String body;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }
}
