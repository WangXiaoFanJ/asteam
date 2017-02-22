package com.dev.nutclass.entity;

import org.json.JSONObject;

/**
 * Created by Administrator on 2017/2/22.
 */
public class SchoolIntroduceEntity extends BaseCardEntity {
    private String brandIntroductName;
    private String introductLink;

    public SchoolIntroduceEntity(JSONObject jsonObject) {
        setCardType(BaseCardEntity.CARD_TYPE_SCHOOL_DETAIL_INTRODUCE_VIEW);
        optJsonObj(jsonObject);
    }

    private void optJsonObj(JSONObject jsonObject) {
        setBrandIntroductName(jsonObject.optString("brandIntroductName"));
        setIntroductLink(jsonObject.optString("brandIntroductLink"));
    }

    public String getBrandIntroductName() {
        return brandIntroductName;
    }

    public void setBrandIntroductName(String brandIntroductName) {
        this.brandIntroductName = brandIntroductName;
    }

    public String getIntroductLink() {
        return introductLink;
    }

    public void setIntroductLink(String introductLink) {
        this.introductLink = introductLink;
    }
}
