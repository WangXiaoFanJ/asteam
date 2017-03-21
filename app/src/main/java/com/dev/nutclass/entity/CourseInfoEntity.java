package com.dev.nutclass.entity;

import com.dev.nutclass.utils.LogUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/20.
 */
public class CourseInfoEntity extends BaseCardEntity {
    private List<KbkServiceBean> kbkServiceList;
    private PromotionInfoBean promotionInfoBean;
    private GiftInfoBean giftInfoBean;
    private List<GoodsAttrBean> goodsAttrBeanList;
    private SchoolInfoBean schoolInfoBean;
    private String goodsId;
    private String goodsLink;
    public CourseInfoEntity(JSONObject jsonObject) {
        setCardType(BaseCardEntity.CARD_TYPE_COURSE_INFO);
        optJsonObj(jsonObject);
    }

    public String getGoodsLink() {
        return goodsLink;
    }

    public void setGoodsLink(String goodsLink) {
        this.goodsLink = goodsLink;
    }

    private void optJsonObj(JSONObject jsonObject) {
        setGoodsLink(jsonObject.optString("goods_link"));
        //课比课服务
        JSONArray jsonArray = jsonObject.optJSONArray("has_service");
        List<KbkServiceBean> list = new ArrayList<>();
        if (jsonArray != null && jsonArray.length() > 0) {
            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    JSONObject jsonObj = jsonArray.getJSONObject(i);
                    KbkServiceBean kbkServiceBean = new KbkServiceBean();
                    kbkServiceBean.optObj(jsonObj);
                    list.add(kbkServiceBean);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        LogUtil.d("===", "list:" + list.size());
        setKbkServiceList(list);
        //课比课促销信息
        JSONObject jsonObj = jsonObject.optJSONObject("promotion_info");

        PromotionInfoBean promotionInfo = new PromotionInfoBean();
        promotionInfo.optObj(jsonObj);
        setPromotionInfoBean(promotionInfo);

        //课比课礼物信息
        JSONObject jsonObj02 = jsonObject.optJSONObject("gift_info");
        GiftInfoBean giftBean = new GiftInfoBean();
        giftBean.optJsonObj(jsonObj02);
        setGiftInfoBean(giftBean);
        //课比课课时
        List<GoodsAttrBean> list02 = new ArrayList<>();
        try {
            JSONObject jsonObject1 = jsonObject.optJSONObject("goods_attr");
            String goodImage = jsonObject1.optString("goods_image");
            JSONArray jsonArray02 = jsonObject1.optJSONArray("data");
            for (int i = 0; i < jsonArray02.length(); i++) {
                JSONObject jsonObj03 = jsonArray02.optJSONObject(i);
                GoodsAttrBean goodsBean = new GoodsAttrBean();
                goodsBean.optJson(jsonObj03);
                list02.add(goodsBean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        setGoodsAttrBeanList(list02);

        //课比课信息
        JSONObject jsonObj03 = jsonObject.optJSONObject("school_info");
        SchoolInfoBean schoolBean = new SchoolInfoBean();
        schoolBean.optJson(jsonObj03);
        setSchoolInfoBean(schoolBean);

    }

    public List<KbkServiceBean> getKbkServiceList() {
        return kbkServiceList;
    }

    public void setKbkServiceList(List<KbkServiceBean> kbkServiceList) {
        this.kbkServiceList = kbkServiceList;
    }

    public PromotionInfoBean getPromotionInfoBean() {
        return promotionInfoBean;
    }

    public void setPromotionInfoBean(PromotionInfoBean promotionInfoBean) {
        this.promotionInfoBean = promotionInfoBean;
    }

    public GiftInfoBean getGiftInfoBean() {
        return giftInfoBean;
    }

    public void setGiftInfoBean(GiftInfoBean giftInfoBean) {
        this.giftInfoBean = giftInfoBean;
    }

    public List<GoodsAttrBean> getGoodsAttrBeanList() {
        return goodsAttrBeanList;
    }

    public void setGoodsAttrBeanList(List<GoodsAttrBean> goodsAttrBeanList) {
        this.goodsAttrBeanList = goodsAttrBeanList;
    }

    public SchoolInfoBean getSchoolInfoBean() {
        return schoolInfoBean;
    }

    public void setSchoolInfoBean(SchoolInfoBean schoolInfoBean) {
        this.schoolInfoBean = schoolInfoBean;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public class KbkServiceBean {
        private String serviceInfo;
        private String serviceName;

        public String getServiceInfo() {
            return serviceInfo;
        }

        public void setServiceInfo(String serviceInfo) {
            this.serviceInfo = serviceInfo;
        }

        public void optObj(JSONObject jsonObject) {
            setServiceInfo(jsonObject.optString("dict_name"));
            setServiceName(jsonObject.optString("dict_content"));
        }

        public String getServiceName() {
            return serviceName;
        }

        public void setServiceName(String serviceName) {
            this.serviceName = serviceName;
        }
    }

    public class PromotionInfoBean {
        private String promotionImg;
        private String promontionInfo;
        private String contentImg;
        private String contentInfo;

        public void optObj(JSONObject jsonobject) {
            setPromontionInfo(jsonobject.optString("info"));
            setPromotionImg(jsonobject.optString("img"));
            setContentImg(jsonobject.optString("content1"));
            setContentInfo(jsonobject.optString("content"));
        }

        public String getPromotionImg() {
            return promotionImg;
        }

        public void setPromotionImg(String promotionImg) {
            this.promotionImg = promotionImg;
        }

        public String getPromontionInfo() {
            return promontionInfo;
        }

        public void setPromontionInfo(String promontionInfo) {
            this.promontionInfo = promontionInfo;
        }

        public String getContentImg() {
            return contentImg;
        }

        public void setContentImg(String contentImg) {
            this.contentImg = contentImg;
        }

        public String getContentInfo() {
            return contentInfo;
        }

        public void setContentInfo(String contentInfo) {
            this.contentInfo = contentInfo;
        }
    }

    public class GiftInfoBean {
        private String giftImg;
        private String giftInfo;
        private String contentImg;
        private String contentInfo;

        public void optJsonObj(JSONObject jsonObj) {
            setGiftImg(jsonObj.optString("img"));
            setGiftInfo(jsonObj.optString("info"));
            setContentImg(jsonObj.optString("content1"));
            setContentInfo(jsonObj.optString("content"));
        }

        public String getGiftImg() {
            return giftImg;
        }

        public void setGiftImg(String giftImg) {
            this.giftImg = giftImg;
        }

        public String getGiftInfo() {
            return giftInfo;
        }

        public void setGiftInfo(String giftInfo) {
            this.giftInfo = giftInfo;
        }

        public String getContentImg() {
            return contentImg;
        }

        public void setContentImg(String contentImg) {
            this.contentImg = contentImg;
        }

        public String getContentInfo() {
            return contentInfo;
        }

        public void setContentInfo(String contentInfo) {
            this.contentInfo = contentInfo;
        }
    }

    public class GoodsAttrBean {
        private String goodsAttr;
        private String attrId;
        private String goodsId;
        private String kbkMoney;
        private String shopMoney;
        public void optJson(JSONObject jsonObj) {
            setAttrId(jsonObj.optString("attr_id"));
            setGoodsId(jsonObj.optString("goods_id"));
            setKbkMoney(jsonObj.optString("kbk_money"));
            setShopMoney(jsonObj.optString("shop_money"));
            setGoodsAttr(jsonObj.optString("goods_attr_value"));
        }

        public String getAttrId() {
            return attrId;
        }

        public void setAttrId(String attrId) {
            this.attrId = attrId;
        }

        public String getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(String goodsId) {
            this.goodsId = goodsId;
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

        public String getGoodsAttr() {
            return goodsAttr;
        }

        public void setGoodsAttr(String goodsAttr) {
            this.goodsAttr = goodsAttr;
        }
    }

    public class SchoolInfoBean {
        private String schoolId;
        private String schoolName;
        private String schoolAddr;
        private String distance;
        private String schoolTel;

        public void optJson(JSONObject jsonObj) {
            setSchoolId(jsonObj.optString("school_id"));
            setSchoolName(jsonObj.optString("school_name"));
            setSchoolAddr(jsonObj.optString("school_addr"));
            setDistance(jsonObj.optString("school_distance"));
            setSchoolTel(jsonObj.optString("school_tel"));
        }

        public String getSchoolId() {
            return schoolId;
        }

        public void setSchoolId(String schoolId) {
            this.schoolId = schoolId;
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
    }
}
