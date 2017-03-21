package com.dev.nutclass.entity;

import org.json.JSONObject;

/**
 * Created by Administrator on 2017/3/6.
 */
public class ImageEntityNew extends BaseCardEntity {
    private String id;
    private String adId;
    private String mainImage;

    public ImageEntityNew(JSONObject jsonObject) {
        setMainImage(jsonObject.optString("image_path"));
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }
}
