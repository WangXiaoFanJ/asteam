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
    private String giftTImg;
    private String giftTInfo;
    private GiftData giftData;
    private String giftImgPointer;
    private String iconMoney;
    public CourseListCardEntity(int type,JSONObject jsonObject) {
        //收藏课程解析
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
        setGiftTImg(jsonObject.optString("gift_t_img"));
        setGiftTInfo(jsonObject.optString("gift_t_info"));
        setGiftImgPointer(jsonObject.optString("gift_img"));
        setIconMoney(jsonObject.optString("icon_money"));
        JSONObject jsonObject1 = jsonObject.optJSONObject("gift_data");
        if(jsonObject1!=null){
            GiftData giftData = new GiftData(jsonObject1);
            setGiftData(giftData);
        }
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

    public String getGiftTImg() {
        return giftTImg;
    }

    public void setGiftTImg(String giftTImg) {
        this.giftTImg = giftTImg;
    }

    public String getGiftTInfo() {
        return giftTInfo;
    }

    public void setGiftTInfo(String giftTInfo) {
        this.giftTInfo = giftTInfo;
    }

    public String getIconMoney() {
        return iconMoney;
    }

    public void setIconMoney(String iconMoney) {
        this.iconMoney = iconMoney;
    }

    public GiftData getGiftData() {
        return giftData;
    }

    public void setGiftData(GiftData giftData) {
        this.giftData = giftData;
    }

    public String getGiftImgPointer() {
        return giftImgPointer;
    }

    public void setGiftImgPointer(String giftImgPointer) {
        this.giftImgPointer = giftImgPointer;
    }

   public class GiftData{
        private String img;
        private String info;

        public GiftData(JSONObject jsonObject) {
            setImg(jsonObject.optString("img"));
            setInfo(jsonObject.optString("info"));
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }
    }
}
