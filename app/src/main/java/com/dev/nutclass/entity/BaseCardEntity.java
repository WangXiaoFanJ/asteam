package com.dev.nutclass.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/12/29.
 */
public class BaseCardEntity  {
    public static final int CARD_TYPE_COURSE_INFO=1008;

    public static final int CARD_TYPE_BANNER_VIEW =1001;
    public static final int CARD_TYPE_HOME_ICON_VIEW=1002;

    public static final int CARD_TYPE_JD_CARD_VIEW=1003;
    public static final int CARD_TYPE_HOME_COURSE_CLASSIFY=1004;
    public static final int CARD_TYPE_SHCOOL_CARD_VIEW=1005;
    public static final int CARD_TYPE_COURSE_CARD_VIEW=1006;
    /**
     * 校区详情页view
     * */
    public static final int CARD_TYPE_SCHOOL_INFO_HEAD_NAME=1015;
    public static final int CARD_TYPE_SCHOOL_INFO_COURSE_VIEW=1016;
    public static final int CARD_TYPE_SCHOOL_INFO_001_View=1017;
    public static final int CARD_TYPE_SCHOOL_DETAIL_INTRODUCE_VIEW = 1018;


    public static final int CARD_TYPE_AMUSE_PARK_VIEW=1022;
    public static final int CARD_TYPE_SCHOOL_RECOMMEND_VIEW=1027;

    /**其他校区view*/
    public static final int CARD_TYPE_OTHER_SCHOOL=1029;
    /**
     * 校区正课
     */
    public static final int CARD_TYPE_SCHOOL_COMMON_COURSE_VIEW =1021;
    /**
     * 校区详情页和课程详情页通用view
     * */
    public static final int CARD_TYPE_SCHOOL_FEATURE_VIEW=1010;

    /**校区详情页评论*/
    public static final int CARD_TYPE_CORUSE_COMMENT_VIEW=1011;
    /***
     * 课程详情页游乐场cardview
     */
    public static final int CARD_TYPE_COURSE_INFO_HEAD_VIEW=1007;
    public static final int CARD_TYPE_NEARBY_AMUSE_PARK_VIEW=1013;
    public static final int CARD_TYPE_COURSE_RECOMMEND_CARD_VIEW = 1014;

    /**
     * 个人订单页面
     * */
    public static final  int CARD_TYPE_USER_ORDER_VIEW=1025;
    public static final  int CARD_TYPE_USER_ORDER_WAIT_PAY_VIEW=1023;
    public static final  int CARD_TYPE_USER_ORDER_WAIT_USE_VIEW=1024;
    public static final  int CARD_TYPE_USER_ORDER_AFTER_SALE_VIEW=1026;
    public static final  int CARD_TYPE_DISCOUNT_COUPON_VIEW=1028;
    public static final int CARD_TYPE_USER_ORDER_APPOINTMENT =1031;
    public static final int CARD_TYPE_USER_ORDER_COMPLETED = 1030;



    /** 未与后台交互的cardtype*/

    public static final int CARD_TYPE_COLLECT_COURSE_VIEW=2001;
    public static final int CARD_TYPE_COLLECT_SCHOOL_VIEW=2002;
    private int cardType = 0;

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
