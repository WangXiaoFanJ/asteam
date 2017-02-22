package com.dev.nutclass.entity;

import com.sina.weibo.sdk.api.share.Base;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/21.
 */
public class SchoolDetailHeadEntity extends BaseCardEntity {
    private String schoolName;
    private String cateName;
    private  String interestedNum;
    private  String commentNum;
    private List<String> schoolImageList;
    private String schoolAddr;
    private String distance;
    private String isNearby;
    private String schoolTel;

    public SchoolDetailHeadEntity(JSONObject jsonObject) {
        setCardType(BaseCardEntity.CARD_TYPE_SCHOOL_INFO_HEAD_NAME);
        optJsonObj(jsonObject);
    }

    private void optJsonObj(JSONObject jsonObject) {
        setSchoolName(jsonObject.optString("school_name"));
        setCateName(jsonObject.optString("cate_name"));
        setInterestedNum(jsonObject.optString("browseNum"));
        setCommentNum(jsonObject.optString("contentNum"));
        setSchoolAddr(jsonObject.optString("address_detail"));
        setDistance(jsonObject.optString("school_distance"));
        setIsNearby(jsonObject.optString("school_distance_info"));
        setSchoolTel(jsonObject.optString("school_tel"));
        JSONArray jsonArray = jsonObject.optJSONArray("school_image");
        List<String > imageList = new ArrayList<>();
        for(int i = 0;i<jsonArray.length();i++){
            imageList.add(jsonArray.optString(i));
        }
        setSchoolImageList(imageList);
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public String getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(String commentNum) {
        this.commentNum = commentNum;
    }

    public String getInterestedNum() {
        return interestedNum;
    }

    public void setInterestedNum(String interestedNum) {
        this.interestedNum = interestedNum;
    }

    public List<String> getSchoolImageList() {
        return schoolImageList;
    }

    public void setSchoolImageList(List<String> schoolImageList) {
        this.schoolImageList = schoolImageList;
    }

    public String getSchoolAddr() {
        return schoolAddr;
    }

    public void setSchoolAddr(String schoolAddr) {
        this.schoolAddr = schoolAddr;
    }

    public String getIsNearby() {
        return isNearby;
    }

    public void setIsNearby(String isNearby) {
        this.isNearby = isNearby;
    }

    public String getSchoolTel() {
        return schoolTel;
    }

    public void setSchoolTel(String schoolTel) {
        this.schoolTel = schoolTel;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
