package com.dev.nutclass.entity;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/30.
 */
public class ClassifyHomeCourseEntity extends BaseCardEntity {
    private List<CourseListCardEntity> courseCardLists;
    public ClassifyHomeCourseEntity(JSONArray jsonArray) {
        setCardType(BaseCardEntity.CARD_TYPE_HOME_COURSE_CLASSIFY);
        List<CourseListCardEntity> list = new ArrayList<>();
        for(int i=0;i<jsonArray.length();i++){
            JSONObject jsonObject = jsonArray.optJSONObject(i);
            CourseListCardEntity entity = new CourseListCardEntity(jsonObject);
            list.add(entity);
        }
        setCourseCardLists(list);
    }

    public List<CourseListCardEntity> getCourseCardLists() {
        return courseCardLists;
    }

    public void setCourseCardLists(List<CourseListCardEntity> courseCardLists) {
        this.courseCardLists = courseCardLists;
    }
}
