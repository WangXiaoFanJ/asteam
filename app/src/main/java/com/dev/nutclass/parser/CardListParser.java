package com.dev.nutclass.parser;

import com.dev.nutclass.constants.UrlConst;
import com.dev.nutclass.entity.BannerCardEntity;
import com.dev.nutclass.entity.BaseCardEntity;
import com.dev.nutclass.entity.CourseCommentEntity;
import com.dev.nutclass.entity.CourseInfoEntity;
import com.dev.nutclass.entity.CourseListCardEntity;
import com.dev.nutclass.entity.CourserDetailHeadEntity;
import com.dev.nutclass.entity.DiscountCouponEntity;
import com.dev.nutclass.entity.JsonDataList;
import com.dev.nutclass.entity.SchoolCardEntity;
import com.dev.nutclass.entity.CourseRecommendForUEntity;
import com.dev.nutclass.entity.SchoolDetailHeadEntity;
import com.dev.nutclass.entity.SchoolDetailMerchInfoEntity;
import com.dev.nutclass.entity.SchoolDetailToCourseEntity;
import com.dev.nutclass.entity.SchoolIntroduceEntity;
import com.dev.nutclass.entity.SchoolRecommentForUEntity;
import com.dev.nutclass.entity.SchoolToCourseCardEntity;
import com.dev.nutclass.entity.ServiceFeatureEntity;
import com.dev.nutclass.entity.UserOrdeCardEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/2/13.
 */
public class CardListParser extends BaseParser<BaseCardEntity> {

    @Override
    public Object parse(String jsonString) throws JSONException {
        JsonDataList<BaseCardEntity> retObj = new JsonDataList<BaseCardEntity>();
        ArrayList<BaseCardEntity> list = new ArrayList<BaseCardEntity>();

        JSONObject jsonObject = new JSONObject(jsonString);
        JSONObject dataObject = jsonObject.optJSONObject("data");
        JSONArray cardListArray = dataObject.optJSONArray("list");
        if (cardListArray != null && cardListArray.length() > 0) {
            for (int i = 0; i < cardListArray.length(); i++) {
                JSONObject jsonObject1 = cardListArray.optJSONObject(i);
                if (jsonObject1 == null) {
                    continue;
                }
                int cardType = Integer.parseInt(jsonObject1.optString("card_type"));
                BaseCardEntity entity = null;
                JSONObject jsonObJ = null;
                switch (cardType) {
                    case BaseCardEntity.CARD_TYPE_SHCOOL_CARD_VIEW:
                        jsonObJ = jsonObject1.optJSONObject("card_data");
                        entity = new SchoolCardEntity(cardType, jsonObJ);
                        break;
                    case BaseCardEntity.CARD_TYPE_SCHOOL_COMMON_COURSE_VIEW:
                        jsonObJ = jsonObject1.optJSONObject("card_data");
                        entity = new SchoolToCourseCardEntity(cardType, jsonObJ);
                        break;
                    case BaseCardEntity.CARD_TYPE_USER_ORDER_WAIT_PAY_VIEW:
                    case BaseCardEntity.CARD_TYPE_USER_ORDER_VIEW:
                    case BaseCardEntity.CARD_TYPE_USER_ORDER_WAIT_USE_VIEW:
                    case BaseCardEntity.CARD_TYPE_USER_ORDER_AFTER_SALE_VIEW:
                        jsonObJ = jsonObject1.optJSONObject("card_data");
                        entity = new UserOrdeCardEntity(cardType, jsonObJ);
                        break;
                    case BaseCardEntity.CARD_TYPE_DISCOUNT_COUPON_VIEW:
                        jsonObJ = jsonObject1.optJSONObject("card_data");
                        entity = new DiscountCouponEntity(cardType, jsonObJ);
                        break;
                    case BaseCardEntity.CARD_TYPE_COURSE_CARD_VIEW:
                        jsonObJ = jsonObject1.optJSONObject("card_data");
                        entity = new CourseListCardEntity(jsonObJ);
                        break;
                    case BaseCardEntity.CARD_TYPE_COURSE_INFO_HEAD_VIEW:
                        jsonObJ = jsonObject1.optJSONObject("card_data");
                        entity = new CourserDetailHeadEntity(jsonObJ);
                        break;
                    case BaseCardEntity.CARD_TYPE_COURSE_INFO:
                        jsonObJ = jsonObject1.optJSONObject("card_data");
                        entity = new CourseInfoEntity(jsonObJ);
                        break;
                    case BaseCardEntity.CARD_TYPE_SCHOOL_FEATURE_VIEW:
                        jsonObJ = jsonObject1.optJSONObject("card_data");
                        entity = new ServiceFeatureEntity(jsonObJ);
                        break;
                    case BaseCardEntity.CARD_TYPE_COURSE_RECOMMEND_CARD_VIEW:
                        JSONArray jsonArray = jsonObject1.optJSONArray("card_data");
                        String bannerImg = jsonObject1.optString("banner_img");
                        entity = new CourseRecommendForUEntity(jsonArray,bannerImg);
                        break;
                    case BaseCardEntity.CARD_TYPE_BANNER_VIEW:
                        jsonObJ = jsonObject1.optJSONObject("card_data");
                        entity = new BannerCardEntity(jsonObJ);
                        break;
                    case BaseCardEntity.CARD_TYPE_SCHOOL_INFO_HEAD_NAME:
                        jsonObJ = jsonObject1.optJSONObject("card_data");
                        entity = new SchoolDetailHeadEntity(jsonObJ);
                        break;
                    case BaseCardEntity.CARD_TYPE_SCHOOL_INFO_COURSE_VIEW:
                        jsonObJ = jsonObject1.optJSONObject("card_data");
                        entity = new SchoolDetailToCourseEntity(jsonObJ);
                        break;
                    case BaseCardEntity.CARD_TYPE_SCHOOL_INFO_001_View:
                        jsonObJ = jsonObject1.optJSONObject("card_data");
                        entity = new SchoolDetailMerchInfoEntity(jsonObJ);
                        break;
//                    校区详情页—机构介绍(1018)
                    case BaseCardEntity.CARD_TYPE_SCHOOL_DETAIL_INTRODUCE_VIEW:
                        jsonObJ = jsonObject1.optJSONObject("card_data");
                        entity = new SchoolIntroduceEntity(jsonObJ);
                        break;
                    //校区详情页-为您推荐（1027）
                    case BaseCardEntity.CARD_TYPE_SCHOOL_RECOMMEND_VIEW:
                        JSONArray jsonArray1 = jsonObject1.optJSONArray("card_data");
                        String bannerImg1 = jsonObject1.optString("banner_img");
                        entity = new SchoolRecommentForUEntity(bannerImg1,jsonArray1);
                        break;
                    //课程/校区详情页-课程评论（1011）
                    case BaseCardEntity.CARD_TYPE_CORUSE_COMMENT_VIEW:
                        JSONArray jsonArray2 = jsonObject1.optJSONArray("card_data");
                        entity =new CourseCommentEntity(jsonArray2);
                    default:
                        break;
                }
                list.add(entity);
            }
        }
        retObj.setErrorCode(UrlConst.SUCCESS_CODE);
        retObj.setList(list);
        return retObj;
    }
}
