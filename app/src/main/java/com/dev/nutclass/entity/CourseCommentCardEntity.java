package com.dev.nutclass.entity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/11.
 */
public class CourseCommentCardEntity {
    private String userId;
    private String commentId;
    private String isPraise;
    private String goodsId;
    private String userName;
    private String userImg;
    private String commentTime;
    private String commentInfo;
    private String browseNum;
    private String fabulousNum;
    private String commentNum;
    private List<ImageDatas> imageLists;

    public CourseCommentCardEntity(JSONObject jsonObject) {
        setUserId(jsonObject.optString("userId"));
        setCommentId(jsonObject.optString("commentId"));
        setUserName(jsonObject.optString("userName"));
        setUserImg(jsonObject.optString("userImg"));
        setCommentTime(jsonObject.optString("userCommentDate"));
        setCommentInfo(jsonObject.optString("userCommentInfo"));
        setIsPraise(jsonObject.optString("is_praise"));
        JSONArray jsonArray = jsonObject.optJSONArray("userCommentImg");
        List<String> stringList = new ArrayList<>();
        if (jsonArray != null && jsonArray.length() > 0) {
            List<ImageDatas> list = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                ImageDatas data = new ImageDatas(jsonObject1);
                list.add(data);
            }
            setImageLists(list);
        }
        setBrowseNum(jsonObject.optString("browseNum"));
        setFabulousNum(jsonObject.optString("praise_num"));
        setCommentNum(jsonObject.optString("commentNum"));
        setGoodsId(jsonObject.optString("goodsId"));
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public List<ImageDatas> getImageLists() {
        return imageLists;
    }

    public void setImageLists(List<ImageDatas> imageLists) {
        this.imageLists = imageLists;
    }

    public class ImageDatas {
        String id;
        String imagePath;

        public ImageDatas(JSONObject jsonObject) {
            setId(jsonObject.optString("id"));
            setImagePath(jsonObject.optString("image_path"));
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImagePath() {
            return imagePath;
        }

        public void setImagePath(String imagePath) {
            this.imagePath = imagePath;
        }
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }

    public String getCommentInfo() {
        return commentInfo;
    }

    public void setCommentInfo(String commentInfo) {
        this.commentInfo = commentInfo;
    }

    public String getBrowseNum() {
        return browseNum;
    }

    public void setBrowseNum(String browseNum) {
        this.browseNum = browseNum;
    }

    public String getFabulousNum() {
        return fabulousNum;
    }

    public void setFabulousNum(String fabulousNum) {
        this.fabulousNum = fabulousNum;
    }

    public String getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(String commentNum) {
        this.commentNum = commentNum;
    }

    public String getIsPraise() {
        return isPraise;
    }

    public void setIsPraise(String isPraise) {
        this.isPraise = isPraise;
    }
}
