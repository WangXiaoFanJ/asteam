package com.dev.nutclass.entity;

import org.json.JSONObject;

/**
 * Created by Administrator on 2017/2/20.
 */
public class CourserDetailHeadEntity extends BaseCardEntity {
    private String goodsId;
    private String goodsName;
    private String promotionInfo;
    private String kbkMoney;
    private String ShopMoney;
    private String fitAge;
    private String interestedNum;
    private String promotionIconStr;
    public CourserDetailHeadEntity(JSONObject jsonObject) {
        setCardType(BaseCardEntity.CARD_TYPE_COURSE_INFO_HEAD_VIEW);
        optJsonObj(jsonObject);
    }

    private void optJsonObj(JSONObject jsonObject) {
        setGoodsId(jsonObject.optString("goods_id"));
        setGoodsName(jsonObject.optString("goods_name"));
        setPromotionInfo(jsonObject.optString("activity_info"));
        setKbkMoney(jsonObject.optString("kbk_money"));
        setShopMoney(jsonObject.optString("shop_money"));
        setFitAge(jsonObject.optString("fit_age"));
        setInterestedNum(jsonObject.optString("interested"));
        setPromotionIconStr(jsonObject.optString("activity_keywords"));
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getPromotionInfo() {
        return promotionInfo;
    }

    public void setPromotionInfo(String promotionInfo) {
        this.promotionInfo = promotionInfo;
    }

    public String getKbkMoney() {
        return kbkMoney;
    }

    public void setKbkMoney(String kbkMoney) {
        this.kbkMoney = kbkMoney;
    }

    public String getShopMoney() {
        return ShopMoney;
    }

    public void setShopMoney(String shopMoney) {
        ShopMoney = shopMoney;
    }

    public String getFitAge() {
        return fitAge;
    }

    public void setFitAge(String fitAge) {
        this.fitAge = fitAge;
    }

    public String getInterestedNum() {
        return interestedNum;
    }

    public void setInterestedNum(String interestedNum) {
        this.interestedNum = interestedNum;
    }

    public String getPromotionIconStr() {
        return promotionIconStr;
    }

    public void setPromotionIconStr(String promotionIconStr) {
        this.promotionIconStr = promotionIconStr;
    }
}
