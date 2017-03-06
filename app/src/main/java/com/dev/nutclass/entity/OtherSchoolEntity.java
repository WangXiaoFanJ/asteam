package com.dev.nutclass.entity;

import org.json.JSONObject;

/**
 * Created by Administrator on 2017/2/27.
 */
public class OtherSchoolEntity extends BaseCardEntity {
    private String schoolName;
    private String schoolAddr;
    private String gpsCn;
    private String isLateLy;
    private String schoolTel;

    public OtherSchoolEntity(JSONObject jsonObject) {
        setCardType(BaseCardEntity.CARD_TYPE_OTHER_SCHOOL);
        optJsonObj(jsonObject);
    }

    private void optJsonObj(JSONObject jsonObject) {
        setSchoolName(jsonObject.optString("school_name"));
        setSchoolAddr(jsonObject.optString("address_detail"));
        setGpsCn(jsonObject.optString("gps_cn"));
        setIsLateLy(jsonObject.optString("school_distance_info"));
        setSchoolTel(jsonObject.optString("school_tel"));
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getSchoolAddr() {
        return schoolAddr;
    }

    public void setSchoolAddr(String schoolAddr) {
        this.schoolAddr = schoolAddr;
    }

    public String getGpsCn() {
        return gpsCn;
    }

    public void setGpsCn(String gpsCn) {
        this.gpsCn = gpsCn;
    }

    public String getIsLateLy() {
        return isLateLy;
    }

    public void setIsLateLy(String isLateLy) {
        this.isLateLy = isLateLy;
    }

    public String getSchoolTel() {
        return schoolTel;
    }

    public void setSchoolTel(String schoolTel) {
        this.schoolTel = schoolTel;
    }
}
