package com.dev.nutclass.entity;

import org.json.JSONObject;

/**
 * Created by Administrator on 2017/2/20.
 */
public class CourseListCardEntity extends BaseCardEntity {
    private String goodsId;
    private String goodsImage;
    private String goodsName;
    private String shopCircleName;
    private String cateName;
    private String gpsCn;
    private String isPromotion;
    private String isHot;
    private String kbkMoney;
    private String shopMoney;
    private String schoolId;
    private String schoolName;
    private String gift_img;
    private String gift_img2;
    private String gift_info;

    public CourseListCardEntity(int type,JSONObject jsonObject) {
        setCardType(BaseCardEntity.CARD_TYPE_COLLECT_COURSE_VIEW);
        optJsonObj(jsonObject);
    }

    public CourseListCardEntity(JSONObject jsonObject) {
        setCardType(BaseCardEntity.CARD_TYPE_COURSE_CARD_VIEW);
        optJsonObj(jsonObject);
    }

    private void optJsonObj(JSONObject jsonObject) {
        setGoodsId(jsonObject.optString("goods_id"));
        setGoodsImage(jsonObject.optString("goods_image"));
        setGoodsName(jsonObject.optString("goods_name"));
        setShopCircleName(jsonObject.optString("shop_circle_name"));
        setCateName(jsonObject.optString("cate_name"));
        setGpsCn(jsonObject.optString("gps_cn"));
        setIsPromotion(jsonObject.optString("promotion"));
        setIsHot(jsonObject.optString("hot"));
        setKbkMoney(jsonObject.optString("kbk_money"));
        setShopMoney(jsonObject.optString("shop_money"));
        setSchoolId(jsonObject.optString("school_id"));
        setSchoolName(jsonObject.optString("school_name"));
        setGift_img(jsonObject.optString("gift_img"));
        setGift_img2(jsonObject.optString("gift_img2"));
        setGift_info(jsonObject.optString("gift_info"));
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsImage() {
        return goodsImage;
    }

    public void setGoodsImage(String goodsImage) {
        this.goodsImage = goodsImage;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getShopCircleName() {
        return shopCircleName;
    }

    public void setShopCircleName(String shopCircleName) {
        this.shopCircleName = shopCircleName;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public String getGpsCn() {
        return gpsCn;
    }

    public void setGpsCn(String gpsCn) {
        this.gpsCn = gpsCn;
    }

    public String getIsPromotion() {
        return isPromotion;
    }

    public void setIsPromotion(String isPromotion) {
        this.isPromotion = isPromotion;
    }

    public String getIsHot() {
        return isHot;
    }

    public void setIsHot(String isHot) {
        this.isHot = isHot;
    }

    public String getKbkMoney() {
        return kbkMoney;
    }

    public void setKbkMoney(String kbkMoney) {
        this.kbkMoney = kbkMoney;
    }

    public String getShopMoney() {
        return shopMoney;
    }

    public void setShopMoney(String shopMoney) {
        this.shopMoney = shopMoney;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getGift_img() {
        return gift_img;
    }

    public void setGift_img(String gift_img) {
        this.gift_img = gift_img;
    }

    public String getGift_img2() {
        return gift_img2;
    }

    public void setGift_img2(String gift_img2) {
        this.gift_img2 = gift_img2;
    }

    public String getGift_info() {
        return gift_info;
    }

    public void setGift_info(String gift_info) {
        this.gift_info = gift_info;
    }
}
