package com.dev.nutclass.entity;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by Administrator on 2017/2/18.
 */
public class UserOrderDetailEntity extends BaseCardEntity {
    private String goodsId;
    private String goodsName;
    private String goodsImage;
    private String shopCircleName;
    private String cateName;
    private String schoolName;
    private String schoolAddr;
    private String goodsAttr;
    private String goodsPrice;
    private String promotionInfo;
    private List couponList;
    private String couponInfo;
    private String waitPayMoney;
    private String userPhone;


    /***/
    private String orderId;
    private String kbkMoney;
    private String distance;
    private String schoolTel;
    private String shopMoney;
    private String orderNumber;
    private String orderTime;

    public UserOrderDetailEntity(JSONObject jsonObject,int type) {
        optJsonObject2(jsonObject);
    }

    public UserOrderDetailEntity(JSONObject jsonObject) {
        optJsonObject(jsonObject);
    }

    private void optJsonObject(JSONObject jsonObject) {
       setGoodsId(jsonObject.optString("goods_id"));
        setGoodsName(jsonObject.optString("goods_title"));
        setGoodsImage(jsonObject.optString("goods_image"));
        setShopCircleName(jsonObject.optString("shop_circle_name"));
        setCateName(jsonObject.optString("cate_name"));
        setSchoolName(jsonObject.optString("school_name"));
        setSchoolAddr(jsonObject.optString("address_detail"));
        setGoodsAttr(jsonObject.optString("goods_attr"));
        setGoodsPrice(jsonObject.optString("goods_price"));
        setPromotionInfo(jsonObject.optString("preferential_coupon"));
        setCouponInfo(jsonObject.optString("preferential_info"));
//        setCouponList(jsonObject.optJSONArray("preferential_list"));
        setWaitPayMoney(jsonObject.optString("goods_pay_price"));
        setUserPhone(jsonObject.optString("user_phone"));

    }
    private void optJsonObject2(JSONObject jsonObject) {
        setGoodsId(jsonObject.optString("goods_id"));
        setGoodsName(jsonObject.optString("goods_name"));
        setGoodsImage(jsonObject.optString("goods_image"));
        setShopCircleName(jsonObject.optString("shop_circle_name"));
        setCateName(jsonObject.optString("cate_name"));
        setSchoolName(jsonObject.optString("school_name"));
        setSchoolAddr(jsonObject.optString("address_detail"));
        setGoodsAttr(jsonObject.optString("goods_attr"));
        setPromotionInfo(jsonObject.optString("preferential_coupon"));
        setCouponInfo(jsonObject.optString("preferential_info"));
//        setCouponList(jsonObject.optJSONArray("preferential_list"));
        setWaitPayMoney(jsonObject.optString("goods_pay_price"));
        setUserPhone(jsonObject.optString("user_phone"));
        setOrderId(jsonObject.optString("order_id"));
        setKbkMoney(jsonObject.optString("kbk_money"));
        setDistance(jsonObject.optString("distance"));
        setSchoolTel(jsonObject.optString("school_tel"));
        setShopMoney(jsonObject.optString("shop_money"));
        setOrderNumber(jsonObject.optString("order_sn"));
        setOrderTime(jsonObject.optString("add_time"));
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

    public String getGoodsImage() {
        return goodsImage;
    }

    public void setGoodsImage(String goodsImage) {
        this.goodsImage = goodsImage;
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

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getSchoolAddr() {
        return schoolAddr;
    }

    public void setSchoolAddr(String schoolAddr) {
        this.schoolAddr = schoolAddr;
    }

    public String getGoodsAttr() {
        return goodsAttr;
    }

    public void setGoodsAttr(String goodsAttr) {
        this.goodsAttr = goodsAttr;
    }

    public String getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(String goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getPromotionInfo() {
        return promotionInfo;
    }

    public void setPromotionInfo(String promotionInfo) {
        this.promotionInfo = promotionInfo;
    }

    public String getCouponInfo() {
        return couponInfo;
    }

    public void setCouponInfo(String couponInfo) {
        this.couponInfo = couponInfo;
    }

    public String getWaitPayMoney() {
        return waitPayMoney;
    }

    public void setWaitPayMoney(String waitPayMoney) {
        this.waitPayMoney = waitPayMoney;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public List getCouponList() {
        return couponList;
    }

    public void setCouponList(List couponList) {
        this.couponList = couponList;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getKbkMoney() {
        return kbkMoney;
    }

    public void setKbkMoney(String kbkMoney) {
        this.kbkMoney = kbkMoney;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getSchoolTel() {
        return schoolTel;
    }

    public void setSchoolTel(String schoolTel) {
        this.schoolTel = schoolTel;
    }

    public String getShopMoney() {
        return shopMoney;
    }

    public void setShopMoney(String shopMoney) {
        this.shopMoney = shopMoney;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }
}
