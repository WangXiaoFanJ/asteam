package com.dev.nutclass.entity;

import org.json.JSONObject;

/**
 * Created by Administrator on 2017/2/14.
 */
public class SchoolToCourseCardEntity extends BaseCardEntity {
    private String goodsId;
    private String goodsName;
    private String kbkMoney;
    private String shopMoney;
    private String goodsImage;
    private String goodsType;
    private String isPromotion;
    private String isHot;

    public SchoolToCourseCardEntity(JSONObject jsonObject) {
        optJsonObject(jsonObject);
    }

    public SchoolToCourseCardEntity(int type, JSONObject jsonObject) {
        setCardType(type);
        optJsonObject(jsonObject);
    }

    private void optJsonObject(JSONObject jsonObject) {
        setGoodsId(jsonObject.optString("goods_id"));
        setGoodsImage(jsonObject.optString("goods_image"));
        setGoodsName(jsonObject.optString("goods_name"));
        setKbkMoney(jsonObject.optString("kbk_money"));
        setShopMoney(jsonObject.optString("shop_money"));
        setIsPromotion(jsonObject.optString("promotion"));
        setIsHot(jsonObject.optString("hot"));
        setGoodsType(jsonObject.optString("goods_type"));
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

    public String getGoodsImage() {
        return goodsImage;
    }

    public void setGoodsImage(String goodsImage) {
        this.goodsImage = goodsImage;
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

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }
}
