package com.dev.nutclass.entity;

import org.json.JSONObject;

/**
 * Created by Administrator on 2017/3/14.
 */
public class ShareInfoEntity {
    private String title;
    private String context;
    private String image;
    private String url;

    public ShareInfoEntity(JSONObject jsonObject) {
        setTitle(jsonObject.optString("title"));
        setContext(jsonObject.optString("context"));
        setImage(jsonObject.optString("image"));
        setUrl(jsonObject.optString("url"));
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
