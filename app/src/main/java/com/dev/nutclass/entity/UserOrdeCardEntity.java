package com.dev.nutclass.entity;

import org.json.JSONObject;

/**
 * Created by Administrator on 2017/2/17.
 */
public class UserOrdeCardEntity extends BaseCardEntity {
    private String goodsId;
    private String goodsName;
    private String schoolName;
    private String kbkMoney;
    private String shopMoney;
    private String goodsImage;
    private String goodsAttr;
    private String goodsType;
    private String statusInfo;

    public UserOrdeCardEntity(int type,JSONObject jsonObject) {
        setCardType(type);
        optJsonObj(jsonObject);
    }

    private void optJsonObj(JSONObject jsonObject) {
        setGoodsId(jsonObject.optString("goods_id"));
        setGoodsName(jsonObject.optString("goods_name"));
        setSchoolName(jsonObject.optString("school_name"));
        setKbkMoney(jsonObject.optString("kbk_money"));
        setShopMoney(jsonObject.optString("shop_money"));
        setGoodsImage(jsonObject.optString("goods_image"));
        setGoodsAttr(jsonObject.optString("goods_attr"));
        setGoodsType(jsonObject.optString("is_goods_type"));
        setStatusInfo(jsonObject.optString("status_info"));
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

    public String getGoodsAttr() {
        return goodsAttr;
    }

    public void setGoodsAttr(String goodsAttr) {
        this.goodsAttr = goodsAttr;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public String getStatusInfo() {
        return statusInfo;
    }

    public void setStatusInfo(String statusInfo) {
        this.statusInfo = statusInfo;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
}
