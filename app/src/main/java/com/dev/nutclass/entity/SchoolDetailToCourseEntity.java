package com.dev.nutclass.entity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/22.
 */
public class SchoolDetailToCourseEntity extends BaseCardEntity {
    private String icon;
    private String info;
    private String moreString;
    private String moreLink;
    private List<SchoolToCourseCardEntity> courseEntityLists;

    public SchoolDetailToCourseEntity(JSONObject jsonObject) {
        setCardType(BaseCardEntity.CARD_TYPE_SCHOOL_INFO_COURSE_VIEW);
        optJsonObj(jsonObject);
    }

    private void optJsonObj(JSONObject jsonObject) {
        setIcon(jsonObject.optString("icon"));
        setInfo(jsonObject.optString("info"));
        setMoreString(jsonObject.optString("more"));
        setMoreLink(jsonObject.optString("more_link"));
        JSONArray jsonArray = jsonObject.optJSONArray("list");
        List<SchoolToCourseCardEntity> lists = new ArrayList<>();
        for(int i=0;i<jsonArray.length();i++){
            JSONObject jsonObj = jsonArray.optJSONObject(i);
            SchoolToCourseCardEntity schoolToCourseCardEntity = new SchoolToCourseCardEntity(jsonObj);
            lists.add(schoolToCourseCardEntity);
        }
        setCourseEntityLists(lists);
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getMoreString() {
        return moreString;
    }

    public void setMoreString(String moreString) {
        this.moreString = moreString;
    }

    public String getMoreLink() {
        return moreLink;
    }

    public void setMoreLink(String moreLink) {
        this.moreLink = moreLink;
    }

    public List<SchoolToCourseCardEntity> getCourseEntityLists() {
        return courseEntityLists;
    }

    public void setCourseEntityLists(List<SchoolToCourseCardEntity> courseEntityLists) {
        this.courseEntityLists = courseEntityLists;
    }
    //    public class CourseEntityData{
//        private String goodsName;
//        private String goodsImage;
//        private String kbkMoney;
//        private String goodsType;
//
//        public CourseEntityData(JSONObject jsonObj) {
//            optJsonObject(jsonObj);
//        }
//
//        private void optJsonObject(JSONObject jsonObj) {
//            setGoodsName(jsonObj.optString("goods_name"));
//            setGoodsImage(jsonObj.optString("goods_image"));
//            setKbkMoney(jsonObj.optString("kbk_money"));
//            setGoodsType(jsonObj.optString("goods_type"));
//        }
//        public String getGoodsName() {
//            return goodsName;
//        }
//
//        public void setGoodsName(String goodsName) {
//            this.goodsName = goodsName;
//        }
//
//        public String getGoodsImage() {
//            return goodsImage;
//        }
//
//        public void setGoodsImage(String goodsImage) {
//            this.goodsImage = goodsImage;
//        }
//
//        public String getKbkMoney() {
//            return kbkMoney;
//        }
//
//        public void setKbkMoney(String kbkMoney) {
//            this.kbkMoney = kbkMoney;
//        }
//
//        public String getGoodsType() {
//            return goodsType;
//        }
//
//        public void setGoodsType(String goodsType) {
//            this.goodsType = goodsType;
//        }
//    }

}
