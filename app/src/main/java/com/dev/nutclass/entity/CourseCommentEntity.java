package com.dev.nutclass.entity;

import android.text.SpannableString;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/22.
 */
public class CourseCommentEntity extends BaseCardEntity {
    private List<CourseCommentCardEntity> courseCommentCardEntityList;

    public CourseCommentEntity(JSONArray jsonArray) {
        setCardType(BaseCardEntity.CARD_TYPE_CORUSE_COMMENT_VIEW);
        List<CourseCommentCardEntity> list = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.optJSONObject(i);
            CourseCommentCardEntity entity = new CourseCommentCardEntity(jsonObject);
            list.add(entity);
        }
        setCourseCommentCardEntityList(list);
    }

    public List<CourseCommentCardEntity> getCourseCommentCardEntityList() {
        return courseCommentCardEntityList;
    }

    public void setCourseCommentCardEntityList(List<CourseCommentCardEntity> courseCommentCardEntityList) {
        this.courseCommentCardEntityList = courseCommentCardEntityList;
    }


}
