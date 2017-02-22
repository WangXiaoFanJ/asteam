package com.dev.nutclass.entity;

import org.json.JSONObject;

/**
 * Created by Administrator on 2017/2/17.
 */
public class DiscountCouponEntity extends BaseCardEntity {
    private String couponId;
    private String couponMoney;
    private String couponInfo;
    private String couponName;
    private String couponTime;
    private String couponLimit;
    private String couponType;

    public DiscountCouponEntity(int type, JSONObject jsonObject) {
        setCardType(type);
        optJsonObject(jsonObject);
    }

    private void optJsonObject(JSONObject jsonObj) {
        setCouponId(jsonObj.optString("coupon_id"));
        setCouponMoney(jsonObj.optString("coupon_money"));
        setCouponInfo(jsonObj.optString("coupon_info"));
        setCouponName(jsonObj.optString("coupon_name"));
        setCouponTime(jsonObj.optString("coupon_time"));
        setCouponLimit(jsonObj.optString("coupon_limit"));
        setCouponType(jsonObj.optString("couponType"));
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public String getCouponMoney() {
        return couponMoney;
    }

    public void setCouponMoney(String couponMoney) {
        this.couponMoney = couponMoney;
    }

    public String getCouponInfo() {
        return couponInfo;
    }

    public void setCouponInfo(String couponInfo) {
        this.couponInfo = couponInfo;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public String getCouponTime() {
        return couponTime;
    }

    public void setCouponTime(String couponTime) {
        this.couponTime = couponTime;
    }

    public String getCouponLimit() {
        return couponLimit;
    }

    public void setCouponLimit(String couponLimit) {
        this.couponLimit = couponLimit;
    }

    public String getCouponType() {
        return couponType;
    }

    public void setCouponType(String couponType) {
        this.couponType = couponType;
    }
}
