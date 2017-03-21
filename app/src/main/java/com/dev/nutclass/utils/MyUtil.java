package com.dev.nutclass.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.dev.nutclass.ApplicationConfig;
import com.dev.nutclass.constants.Const;
import com.dev.nutclass.constants.UrlConst;
import com.dev.nutclass.entity.UserInfoEntity;
import com.dev.nutclass.network.OkHttpClientManager;
import com.squareup.okhttp.Request;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/3/1.
 */
public class MyUtil {
    private static final String TAG = "MyUtil";
    //存储Activity的List
    public static List<Activity> activities = new ArrayList<Activity>();

    public static boolean isMobileNO(String mobiles) {
        // Pattern p =
        // Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Pattern p = Pattern
                .compile("^((13[0-9])|(14[0-9])|(15[^4,\\D])|(17[0-9])|(18[0-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        LogUtil.i(TAG, "mobile=" + mobiles + ",matche=" + m.matches());
        return m.matches();
    }

    //添加Activity
    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    //移出Activity
    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    //销毁所有Activity
    public static void finishAll() {
        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

    /**
     * 请求验证码
     */
    public static void reqVerifyCode(final String mobile, final Context context, String tts) {
        String url = UrlConst.REQ_VERIFY_CODE_URL;
        Map<String, String> parmas = new HashMap<>();
        parmas.put("mobile", mobile);
        parmas.put("tts", tts);
        OkHttpClientManager.postAsyn(url, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
                LogUtil.d(TAG, "error:" + e.getMessage());
            }

            @Override
            public void onResponse(String response) {
                LogUtil.d(TAG, "response:" + response);
                DialogUtils.showToast(context, "发送成功");
            }
        }, parmas);
    }

    /**
     * 修改用户信息
     */
    public static void reqChangeUserInfoURL(final Context mContext, String userId, final String stringType, final String editInfo) {
        Map<String, String> map = new HashMap<>();
        map.put("userId", userId);
        map.put(stringType, editInfo);
        LogUtil.d("===", "userId" + userId + "userName" + editInfo);
        OkHttpClientManager.postAsyn(UrlConst.EDIT_USER_INFO_URL, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
                LogUtil.d(TAG, "error:" + e.getMessage());
            }

            @Override
            public void onResponse(String response) {
                LogUtil.d(TAG, "response:" + response);
                try {
                    JSONObject jsonObj = new JSONObject(response);
                    String status = jsonObj.optString("status");
                    if (status.equals("1")) {
                        UserInfoEntity entity = SharedPrefUtil.getInstance().getSession();
                        if (stringType.equals(Const.BABY_BITTHDAY)) {
                            entity.setBabyBirth(editInfo);
                        } else if (stringType.equals(Const.BABY_SEX)) {
                            entity.setBabySex(editInfo);
                        } else if (stringType.equals(Const.USER_NAME)) {
                            entity.setUserName(editInfo);
                            ((Activity) mContext).finish();
                        }else if (stringType.equals(Const.HEAD_IMAGE)){
                            JSONObject jsonObject = jsonObj.optJSONObject("data");
                            String headImage = jsonObject.optString("headerIconUrl");
                            LogUtil.d("===","headImage:"+headImage);
                            entity.setHeadIcon(headImage);
                        }
                        SharedPrefUtil.getInstance().setUserSession(entity.buildJsonObject().toString());
                        Intent intent = new Intent();
                        intent.setPackage(ApplicationConfig
                                .getInstance().getPackageName());
                        intent.setAction(Const.ACTION_BROADCAST_UPDATA_USER_INFO);
                        mContext.sendBroadcast(intent);
                        if (stringType.equals(Const.USER_NAME)) {
                            ((Activity) mContext).finish();
                        }
                    }
                    DialogUtils.showToast(mContext, jsonObj.optString("message"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }, map);
    }

}
