package com.dev.nutclass.entity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2017/3/1.
 */
public class UserInfoEntity extends BaseCardEntity {
    private String userId;
    private String userName;
    private String userPhone;
    private String headIcon;
    private String token;
    private int userType;
    private String babyBirth;
    private String babySex;
    public UserInfoEntity() {
    }

    public UserInfoEntity(JSONObject jsonObject) {
        optJsonObj(jsonObject);
    }

    private void optJsonObj(JSONObject jsonObject) {
        setUserId(jsonObject.optString("user_id"));
        setUserName(jsonObject.optString("user_name"));
        setUserPhone(jsonObject.optString("user_phone"));
        setHeadIcon(jsonObject.optString("headerIconUrl"));
        setToken(jsonObject.optString("token"));
        setBabyBirth(jsonObject.optString("baby_birthday"));
        setBabySex(jsonObject.optString("baby_sex"));
    }
    public JSONObject buildJsonObject() throws JSONException {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("user_id", userId);
        jsonObject.put("user_name", userName);
//		jsonObject.put("mobile", SharedPrefUtil.getInstance().getMobile());
        jsonObject.put("headerIconUrl", headIcon);
        jsonObject.put("baby_birthday",babyBirth);
        jsonObject.put("baby_sex",babySex);
        return jsonObject;
    }

    public String getBabyBirth() {
        return babyBirth;
    }

    public void setBabyBirth(String babyBirth) {
        this.babyBirth = babyBirth;
    }

    public String getBabySex() {
        return babySex;
    }

    public void setBabySex(String babySex) {
        this.babySex = babySex;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getHeadIcon() {
        return headIcon;
    }

    public void setHeadIcon(String headIcon) {
        this.headIcon = headIcon;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }
}
