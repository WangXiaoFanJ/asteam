package com.dev.nutclass.entity;

import com.dev.nutclass.utils.LogUtil;
import com.dev.nutclass.view.CourseInfoView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/20.
 */
public class CourseRecommendForUEntity extends BaseCardEntity{
    private List<CourseListCardEntity> list;
    private String bannerImg;
    public CourseRecommendForUEntity(JSONArray jsonArray,String bannerImg) {
        setCardType(BaseCardEntity.CARD_TYPE_COURSE_RECOMMEND_CARD_VIEW);
        optJsonObj(jsonArray);
        setBannerImg(bannerImg);
    }

    private void optJsonObj(JSONArray jsonArray) {
        List<CourseListCardEntity> list = new ArrayList<>();
        for(int i = 0;i<jsonArray.length();i++){
            JSONObject jsonObj = jsonArray.optJSONObject(i);
            CourseListCardEntity entity = new CourseListCardEntity(jsonObj);
            list.add(entity);
            LogUtil.d("===","entity:"+entity.getIsHot()+entity.getGift_info());
        }
        setList(list);
    }

    public String getBannerImg() {
        return bannerImg;
    }

    public void setBannerImg(String bannerImg) {
        this.bannerImg = bannerImg;
    }

    public List<CourseListCardEntity> getList() {
        return list;
    }

    public void setList(List<CourseListCardEntity> list) {
        this.list = list;
    }
}
