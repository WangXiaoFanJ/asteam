package com.dev.nutclass.entity;

import org.json.JSONObject;

/**
 * Created by Administrator on 2017/2/22.
 */
public class SchoolDetailMerchInfoEntity extends BaseCardEntity {
    private String title;
    private String merchantDate;

    public SchoolDetailMerchInfoEntity(JSONObject jsonObject) {
        setCardType(BaseCardEntity.CARD_TYPE_SCHOOL_INFO_001_View);
        optJson(jsonObject);
    }

    private void optJson(JSONObject jsonObject) {
        setTitle(jsonObject.optString("title"));
        setMerchantDate(jsonObject.optString("merchant_date"));
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMerchantDate() {
        return merchantDate;
    }

    public void setMerchantDate(String merchantDate) {
        this.merchantDate = merchantDate;
    }
}
