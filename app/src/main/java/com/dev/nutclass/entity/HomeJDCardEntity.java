package com.dev.nutclass.entity;

import com.sina.weibo.sdk.constant.WBConstants;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Administrator on 2017/2/23.
 */
public class HomeJDCardEntity extends BannerCardEntity {
    private String imgUrl;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public HomeJDCardEntity(JSONArray jsonArray) {
        setCardType(BaseCardEntity.CARD_TYPE_JD_CARD_VIEW);
        if(jsonArray!=null&&jsonArray.length()>0){
            for (int i = 0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.optJSONObject(i);
                setImgUrl(jsonObject.optString("image_url"));
            }
        }
    }

}
