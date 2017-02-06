package com.dev.nutclass.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/12/30.
 */
public class CourseCardEntity extends BaseCardEntity {
    private String name;
    private String id;
    private List<ImageEntity> imageEntities;
    private List<String> courseTimeList;
    public CourseCardEntity() {
        setCardType(BaseCardEntity.CARD_TYPE_COURSE_INFO);
        setName("11111");
        setId("0");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public List<ImageEntity> getImageEntities() {
        return imageEntities;
    }

    public void setImageEntities(List<ImageEntity> imageEntities) {
        this.imageEntities = imageEntities;
    }

    public List<String> getCourseTimeList() {
        return courseTimeList;
    }

    public void setCourseTimeList(List<String> courseTimeList) {
        this.courseTimeList = courseTimeList;
    }
}
