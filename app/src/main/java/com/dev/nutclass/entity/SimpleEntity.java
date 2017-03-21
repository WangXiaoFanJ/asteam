package com.dev.nutclass.entity;

import org.json.JSONObject;

/**
 * Created by Administrator on 2017/3/1.
 */
public class SimpleEntity extends BaseCardEntity {
    private String status;
    private String data;
    private String message;
    public SimpleEntity(JSONObject jsonObject) {
        setStatus(jsonObject.optString("status"));
        setData(jsonObject.optString("data"));
        setMessage(jsonObject.optString("message"));
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
