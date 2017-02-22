package com.dev.nutclass.entity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/22.
 */
public class SchoolRecommentForUEntity extends BaseCardEntity {
    private String bannerImg;
    private List<SchoolCardEntity> schoolCardEntityList;

    public SchoolRecommentForUEntity(String bannerImg,JSONArray jsonArray) {
        setCardType(BaseCardEntity.CARD_TYPE_SCHOOL_RECOMMEND_VIEW);
        setBannerImg(bannerImg);
        List<SchoolCardEntity> lists = new ArrayList<>();
        for(int i=0;i<jsonArray.length();i++){
            JSONObject jsonObject = jsonArray.optJSONObject(i);
            SchoolCardEntity entity = new SchoolCardEntity(jsonObject);
            lists.add(entity);
        }
        setSchoolCardEntityList(lists);

    }

    public List<SchoolCardEntity> getSchoolCardEntityList() {
        return schoolCardEntityList;
    }

    public void setSchoolCardEntityList(List<SchoolCardEntity> schoolCardEntityList) {
        this.schoolCardEntityList = schoolCardEntityList;
    }

    public String getBannerImg() {
        return bannerImg;
    }

    public void setBannerImg(String bannerImg) {
        this.bannerImg = bannerImg;
    }
}
