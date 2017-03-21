package com.dev.nutclass.entity;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/30.
 */
public class ClassifyHomeCourseEntity extends BaseCardEntity {
    private List<GoodsCateBean> goodCateLists;
    private List<CourseListCardEntity> courseCardLists;
    public ClassifyHomeCourseEntity(JSONObject jsonObject) {
        setCardType(BaseCardEntity.CARD_TYPE_HOME_COURSE_CLASSIFY);
        optJson(jsonObject);
    }

    private void optJson(JSONObject jsonObject) {
        JSONArray cateArray = jsonObject.optJSONArray("goods_type");
        List<GoodsCateBean> list = new ArrayList<>();
        for(int i = 0;i<cateArray.length();i++){
            JSONObject jsonObj = cateArray.optJSONObject(i);
            GoodsCateBean cateBean = new GoodsCateBean(jsonObj);
            list.add(cateBean);
        }
        setGoodCateLists(list);

        JSONArray dataArray = jsonObject.optJSONArray("goods_data");
        if(dataArray!=null&&dataArray.length()>0){
            List<CourseListCardEntity> dataList = new ArrayList<>();
            for(int i = 0;i<dataArray.length();i++){
                JSONObject jsonObj = dataArray.optJSONObject(i);
                CourseListCardEntity entity = new CourseListCardEntity(jsonObj);
                dataList.add(entity);
            }
            setCourseCardLists(dataList);
        }
    }

    public List<GoodsCateBean> getGoodCateLists() {
        return goodCateLists;
    }

    public void setGoodCateLists(List<GoodsCateBean> goodCateLists) {
        this.goodCateLists = goodCateLists;
    }

    public List<CourseListCardEntity> getCourseCardLists() {
        return courseCardLists;
    }

    public void setCourseCardLists(List<CourseListCardEntity> courseCardLists) {
        this.courseCardLists = courseCardLists;
    }

    public class GoodsCateBean{
        private String id;
        private String cateName;
        public GoodsCateBean(JSONObject jsonObj) {
            setId(jsonObj.optString("id"));
            setCateName(jsonObj.optString("catename"));
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCateName() {
            return cateName;
        }

        public void setCateName(String cateName) {
            this.cateName = cateName;
        }
    }

}
