package com.dev.nutclass.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/12/29.
 */
public class BaseCardEntity {
    public static final int CARD_TYPE_BANNER_COURSE_INFO=2001;
    public static final int CARD_TYPE_COURSE_INFO=1008;

    public static final int CARD_TYPE_JD_CARD_VIEW=1003;
    public static final int CARD_TYPE_SHCOOL_CARD_VIEW=1005;
    public static final int CARD_TYPE_COURSE_CARD_VIEW=1006;
    /**
     * 校区详情页view
     * */
    public static final int CARD_TYPE_SCHOOL_INFO_HEAD_NAME=1015;
    public static final int CARD_TYPE_SCHOOL_INFO_COURSE_VIEW=1016;
    public static final int CARD_TYPE_SCHOOL_INFO_001_View=1017;

    public static final int CARD_TYPE_AMUSE_PARK_VIEW=1022;
    public static final int CARD_TYPE_SCHOOL_RECOMMEND_VIEW=1023;
    /**
     * 校区详情页和课程详情页通用view
     * */
    public static final int CARD_TYPE_SCHOOL_FEATURE_VIEW=1010;

    /***
     * 课程详情页游乐场cardview
     */
    public static final int CARD_TYPE_COURSE_INFO_HEAD_VIEW=1007;
    public static final int CARD_TYPE_NEARBY_AMUSE_PARK_VIEW=1013;
    public static final int CARD_TYPE_COURSE_RECOMMEND_CARD_VIEW = 1014;

    /**
     * 个人订单页面
     * */
    public static final  int CARD_TYPE_USER_ORDER_VIEW=1024;
    private int cardType = 0;
    private List<ImageEntity> list;

    public List<ImageEntity> getList() {
        return list;
    }

    public void setList(List<ImageEntity> list) {
        this.list = list;
    }

    public int getCardType() {
        return cardType;
    }

    public void setCardType(int cardType) {
        this.cardType = cardType;
    }

    //jd 双Card
    public static final int CARD_TYPE_JD_DOUBLE_CARD=212;
    //京东Banner
    public static final int CARD_TYPE_JD_BANNER=210;
    //jd筛选card
    public static final int CARD_TYPE_JD_CAT_CARD=213;
    public static final int CARD_TYPE_SINGLE_ITEM=8;
    public static final int CARD_TYPE_BANNER=2;
    public static final int CARD_TYPE_BRAND=106;
}
