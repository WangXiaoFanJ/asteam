package com.dev.nutclass.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/30.
 */
public class ClassifyEntity extends BaseCardEntity {
    private String []  lists= {"早教幼儿","语言培训","素质教育","升学辅导","早教幼儿"};
    private List<String> titleLists = new ArrayList<>();
    private CourseCardEntity cardEntity;
    public ClassifyEntity() {
        setCardType(2);
        for(int i = 0;i<lists.length;i++){
            titleLists.add(lists[i]);
        }
        setTitleList(titleLists);
        cardEntity = new CourseCardEntity();
    }

    private List<String> titleList;

    public List<String> getTitleList() {
        return titleList;
    }

    public void setTitleList(List<String> titleList) {
        this.titleList = titleList;
    }

    public CourseCardEntity getCardEntity() {
        return cardEntity;
    }

    public void setCardEntity(CourseCardEntity cardEntity) {
        this.cardEntity = cardEntity;
    }
}
