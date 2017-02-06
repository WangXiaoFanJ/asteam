package com.dev.nutclass.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.dev.nutclass.ApplicationConfig;

/**
 * Created by Administrator on 2017/1/16.
 */
public class SharedPrefUtil {
    public static final String TAG = "SharedPrefUtil";
    public static final String PREFER_NAME = "com.dev.nutclass";
    private static SharedPrefUtil instance;
    private static final String statusBarHeight = "status_bar_height";// token
    private SharedPreferences mPrefer;
    private SharedPrefUtil() {
        mPrefer = ApplicationConfig.getInstance().getBaseContext()
                .getSharedPreferences(PREFER_NAME, Context.MODE_PRIVATE);
    }
    public static synchronized final SharedPrefUtil getInstance() {
        if (instance == null) {
            instance = new SharedPrefUtil();
        }
        return instance;
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

}
