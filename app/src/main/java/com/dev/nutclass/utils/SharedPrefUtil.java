package com.dev.nutclass.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.dev.nutclass.ApplicationConfig;
import com.dev.nutclass.MyApplication;
import com.dev.nutclass.entity.UserInfoEntity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2017/1/16.
 */
public class SharedPrefUtil {
    public static final String TAG = "SharedPrefUtil";
    public static final String PREFER_NAME = "com.dev.nutclass";
    private static final String KEY_MOBILE = "key_mobile";// 用户手机号
    private static final String KEY_TOKEN = "key_token";// token
    private static final String KEY_SESSION = "key_session";// 登陆用户的信息，json结构
    private static SharedPrefUtil instance;
    private static final String statusBarHeight = "status_bar_height";// token
    private SharedPreferences mPrefer;
    private SharedPrefUtil() {
        mPrefer = ApplicationConfig.getInstance().getBaseContext()
                .getSharedPreferences(PREFER_NAME,Context.MODE_PRIVATE);
    }
    public static synchronized final SharedPrefUtil getInstance() {
        if (instance == null) {
            instance = new SharedPrefUtil();
        }
        return instance;
    }


    public boolean isLogin() {
        if (TextUtil.isNotNull(getToken())) {
            return true;
        }
        return false;
    }
    /**
     * 获取加密后的base64的token
     */
    public String getToken() {
        String token = getString(KEY_TOKEN);
        return token;
    }

    /**
     * 1、在获取验证码的时候生成一个不完整token 2、在登陆成功的地方设置token
     */
    public void setToken(String value) {
        setString(KEY_TOKEN, value);
    }

    public String getString(String key) {
        return mPrefer.getString(key, "");
    }
    public void setString(String key, String value) {
        SharedPreferences.Editor editor = mPrefer.edit();
        editor.putString(key, value);
        editor.commit();
    }
    public int getInt(String key){
        return  mPrefer.getInt(key,0);
    }
    public void setInt(String key,int value){
        SharedPreferences.Editor editor = mPrefer.edit();
        editor.putInt(key,value);
        editor.commit();
    }
    public void setDeviceToken(String deviceToken){
        SharedPreferences.Editor editor = mPrefer.edit();
        editor.putString("device_token",deviceToken);
        editor.commit();
    }
    public String getDeviceToken(){
        return mPrefer.getString("device_token","");
    }
    public void setMobile(String mobile) {
        setString(KEY_MOBILE, mobile);
    }

    public String getMobile() {
        return getString(KEY_MOBILE);
    }
    // LoginSession
    public void setUserSession(String userJson) {
        setString(KEY_SESSION, userJson);
    }

    public UserInfoEntity getSession() {
        String userJson = getString(KEY_SESSION);
        if (TextUtil.isNotNull(userJson)) {
            JSONObject json;
            try {
                json = new JSONObject(userJson);
                if (json != null) {
                    UserInfoEntity entity = new UserInfoEntity(json);
                    return entity;
                }
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return null;
    }
}
