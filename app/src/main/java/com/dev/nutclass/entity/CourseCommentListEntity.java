package com.dev.nutclass.entity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/11.
 */
public class CourseCommentListEntity extends BaseCardEntity {
    private CourseCommentCardEntity courseCommentCardEntity;
    private List<CommentReplyEntity> replyList;
    private GoodsInfo goodsInfo;
    public CourseCommentListEntity(JSONObject jsonObject) {
        setCardType(BaseCardEntity.CARD_TYPE_COURSE_COMMENT_LIST_VIEW);
        optJson(jsonObject);
    }

    private void optJson(JSONObject jsonObject) {
        JSONObject jsonObj = jsonObject.optJSONObject("user_data");
        CourseCommentCardEntity commentCardEntity = new CourseCommentCardEntity(jsonObj);
        setCourseCommentCardEntity(commentCardEntity);
        JSONObject jsonObject1 = jsonObject.optJSONObject("goods_list");
        if(jsonObject1!=null){
            GoodsInfo goodsInfo = new GoodsInfo(jsonObject1);
            setGoodsInfo(goodsInfo);
        }
        JSONArray jsonArray = jsonObject.optJSONArray("replyInfo");
        if(jsonArray!=null&&jsonArray.length()>0){
            List<CommentReplyEntity> list = new ArrayList<>();
            for(int i =0;i<jsonArray.length();i++){
                JSONObject jsonObject02 = jsonArray.optJSONObject(i);
                CommentReplyEntity entity = new CommentReplyEntity(jsonObject02);
                list.add(entity);
            }
            setReplyList(list);
        }
    }

    public CourseCommentCardEntity getCourseCommentCardEntity() {
        return courseCommentCardEntity;
    }

    public void setCourseCommentCardEntity(CourseCommentCardEntity courseCommentCardEntity) {
        this.courseCommentCardEntity = courseCommentCardEntity;
    }

    public List<CommentReplyEntity> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<CommentReplyEntity> replyList) {
        this.replyList = replyList;
    }

    public GoodsInfo getGoodsInfo() {
        return goodsInfo;
    }

    public void setGoodsInfo(GoodsInfo goodsInfo) {
        this.goodsInfo = goodsInfo;
    }

    public class CommentReplyEntity{
        private String userId;
        private String commentId;
        private String goodsId;
        private String userName;
        private String replyUserName;
        private String commentDate;
        private String commentInfo;
        public CommentReplyEntity(JSONObject jsonObject) {
            setUserId(jsonObject.optString("userId"));
            setCommentId(jsonObject.optString("commentId"));
            setGoodsId(jsonObject.optString("goodsId"));
            setUserName(jsonObject.optString("userName"));
            setReplyUserName(jsonObject.optString("ReplyUserName"));
            setCommentDate(jsonObject.optString("userCommentDate"));
            setComentInfo(jsonObject.optString("userCommentInfo"));
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getCommentId() {
            return commentId;
        }

        public void setCommentId(String commentId) {
            this.commentId = commentId;
        }

        public String getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(String goodsId) {
            this.goodsId = goodsId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getReplyUserName() {
            return replyUserName;
        }

        public void setReplyUserName(String replyUserName) {
            this.replyUserName = replyUserName;
        }

        public String getCommentDate() {
            return commentDate;
        }

        public void setCommentDate(String commentDate) {
            this.commentDate = commentDate;
        }

        public String getComentInfo() {
            return commentInfo;
        }

        public void setComentInfo(String commentInfo) {
            this.commentInfo = commentInfo;
        }
    }
    public class GoodsInfo{
        private String goodsId;
        private String goodsName;
        private String goodsImage;
        private String kbkMoney;
        private String catename;
        private String shopCircleName;

        public GoodsInfo(JSONObject jsonObject) {
           setGoodsId(jsonObject.optString("goods_id"));
            setGoodsName(jsonObject.optString("goods_name"));
            setGoodsImage(jsonObject.optString("goods_image"));
            setKbkMoney(jsonObject.optString("kbk_money"));
            setCatename(jsonObject.optString("cate_name"));
            setShopCircleName(jsonObject.optString("shop_circle_name"));
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

        public String getKbkMoney() {
            return kbkMoney;
        }

        public void setKbkMoney(String kbkMoney) {
            this.kbkMoney = kbkMoney;
        }

        public String getCatename() {
            return catename;
        }

        public void setCatename(String catename) {
            this.catename = catename;
        }

        public String getShopCircleName() {
            return shopCircleName;
        }

        public void setShopCircleName(String shopCircleName) {
            this.shopCircleName = shopCircleName;
        }
    }
}
