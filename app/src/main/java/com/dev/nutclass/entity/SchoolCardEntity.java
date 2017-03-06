package com.dev.nutclass.entity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/13.
 */
public class SchoolCardEntity extends BaseCardEntity {
    private String schoolId;
    private String shop_circle_text;
    private String schoolImage;
    private String isPromotion;
    private String isHot;
    private String schoolName;
    private String schoolCateName;
    private String gpsCn;
    private String gpsLongitude;
    private String gpsLatitude;
    private String interestNum;
    private List goodList;

    public SchoolCardEntity(JSONObject jsonObject) {
        optJsonObject(jsonObject);
    }

    public SchoolCardEntity(int type, JSONObject jsonObject) {
        setCardType(type);
        optJsonObject(jsonObject);
    }

    private void optJsonObject(JSONObject jsonObject01) {

        setSchoolId(jsonObject01.optString("school_id"));
        setShop_circle_text(jsonObject01.optString("shop_circle_name"));
        setSchoolImage(jsonObject01.optString("school_image"));
        setIsPromotion(jsonObject01.optString("promotion"));
        setIsHot(jsonObject01.optString("hot"));
        setSchoolName(jsonObject01.optString("school_name"));
        setSchoolCateName(jsonObject01.optString("cate_name"));
        setGpsCn(jsonObject01.optString("gps_cn"));
        setGpsLongitude(jsonObject01.optString("gps_x"));
        setGpsLatitude(jsonObject01.optString("gps_y"));
        setInterestNum(jsonObject01.optString("browseNum"));
        JSONArray jsonArray = jsonObject01.optJSONArray("list");
        List<SimpleCourseEntity> list = new ArrayList<>();
        if(jsonArray!=null&&jsonArray.length()>0) {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject02 = jsonArray.optJSONObject(i);
                SimpleCourseEntity entity = new SimpleCourseEntity();
                entity.optObject(jsonObject02);
                list.add(entity);
            }
            setGoodList(list);
        }

    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getShop_circle_text() {
        return shop_circle_text;
    }

    public void setShop_circle_text(String shop_circle_text) {
        this.shop_circle_text = shop_circle_text;
    }

    public String getSchoolImage() {
        return schoolImage;
    }

    public void setSchoolImage(String schoolImage) {
        this.schoolImage = schoolImage;
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

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getSchoolCateName() {
        return schoolCateName;
    }

    public void setSchoolCateName(String schoolCateName) {
        this.schoolCateName = schoolCateName;
    }

    public String getGpsCn() {
        return gpsCn;
    }

    public void setGpsCn(String gpsCn) {
        this.gpsCn = gpsCn;
    }

    public String getGpsLongitude() {
        return gpsLongitude;
    }

    public void setGpsLongitude(String gpsLongitude) {
        this.gpsLongitude = gpsLongitude;
    }

    public String getGpsLatitude() {
        return gpsLatitude;
    }

    public void setGpsLatitude(String gpsLatitude) {
        this.gpsLatitude = gpsLatitude;
    }

    public String getInterestNum() {
        return interestNum;
    }

    public void setInterestNum(String interestNum) {
        this.interestNum = interestNum;
    }

    public List getGoodList() {
        return goodList;
    }

    public void setGoodList(List goodList) {
        this.goodList = goodList;
    }
    public class SimpleCourseEntity{
        private String goodId;
        private String goodsName;
        private String kbkMoney;
        private String shopMoney;

        public String getGoodId() {
            return goodId;
        }

        public void setGoodId(String goodId) {
            this.goodId = goodId;
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
        public void optObject(JSONObject jsonObject){
            setGoodId(jsonObject.optString("goods_id"));
            setGoodsName(jsonObject.optString("goods_name"));
            setKbkMoney(jsonObject.optString("kbk_money"));
            setShopMoney(jsonObject.optString("shop_money"));
        }
    }
}
