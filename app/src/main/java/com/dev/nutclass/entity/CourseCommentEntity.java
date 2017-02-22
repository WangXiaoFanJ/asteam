package com.dev.nutclass.entity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/22.
 */
public class CourseCommentEntity extends BaseCardEntity {
    private List<CourseCommentCardEntity> courseCommentCardEntityList;
    public CourseCommentEntity(JSONArray jsonArray) {
        setCardType(BaseCardEntity.CARD_TYPE_CORUSE_COMMENT_VIEW);
        List<CourseCommentCardEntity> list =new ArrayList<>();
        for(int i=0;i<jsonArray.length();i++){
            JSONObject jsonObject = jsonArray.optJSONObject(i);
            CourseCommentCardEntity entity = new CourseCommentCardEntity(jsonObject);
            list.add(entity);
        }
        setCourseCommentCardEntityList(list);
    }

    public List<CourseCommentCardEntity> getCourseCommentCardEntityList() {
        return courseCommentCardEntityList;
    }

    public void setCourseCommentCardEntityList(List<CourseCommentCardEntity> courseCommentCardEntityList) {
        this.courseCommentCardEntityList = courseCommentCardEntityList;
    }

    public class CourseCommentCardEntity{
        private String userId;
        private String commentId;
        private String userName;
        private String userImg;
        private String commentTime;
        private String commentInfo;
        private List<String> commentImgList;
        private String browseNum;
        private String fabulousNum;
        private String commentNum;
        public CourseCommentCardEntity(JSONObject jsonObject) {
            setUserId(jsonObject.optString("userId"));
            setCommentId(jsonObject.optString("commentId"));
            setUserName(jsonObject.optString("userName"));
            setUserImg(jsonObject.optString("userImg"));
            setCommentTime(jsonObject.optString("userCommentDate"));
            setCommentInfo(jsonObject.optString("userCommentInfo"));

            JSONArray jsonArray = jsonObject.optJSONArray("userCommentImg");
            List<String> stringList = new ArrayList<>();
            for(int i = 0;i<jsonArray.length();i++){
                String imgString = jsonArray.optString(i);
                stringList.add(imgString);
            }
            setCommentImgList(stringList);
            setBrowseNum(jsonObject.optString("browseNum"));
            setFabulousNum(jsonObject.optString("fabulousNum"));
            setCommentNum(jsonObject.optString("commentNum"));
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

        public List<String> getCommentImgList() {
            return commentImgList;
        }

        public void setCommentImgList(List<String> commentImgList) {
            this.commentImgList = commentImgList;
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
    }
}
