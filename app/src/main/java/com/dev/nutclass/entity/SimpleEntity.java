package com.dev.nutclass.entity;

import org.json.JSONObject;

/**
 * Created by Administrator on 2017/3/1.
 */
public class SimpleEntity extends BaseCardEntity {
    private String status;
    private String data;

    public SimpleEntity(JSONObject jsonObject) {
        setStatus(jsonObject.optString("status"));
        setData(jsonObject.optString("data"));
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
}
