package com.dev.nutclass;

import android.app.Application;
import android.content.res.Configuration;
import android.util.Log;

import com.dev.nutclass.network.OkHttpClientManager;
import com.dev.nutclass.utils.LogUtil;
import com.dev.nutclass.utils.SharedPrefUtil;
import com.dev.nutclass.utils.SnsUtil;
import com.kepler.jd.Listener.AsyncInitListener;
import com.kepler.jd.login.KeplerApiManager;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import java.io.IOException;

/**
 * Created by Administrator on 2016/12/27.
 */
public class MyApplication extends Application {
    public static final String TAG = "MyApplication";
    private  String device_token = "";
    @Override
    public void onCreate() {
        super.onCreate();
        //友盟推送
        PushAgent mPushAgent = PushAgent.getInstance(this);
//注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {
            @Override
            public void onSuccess(String deviceToken) {
                //注册成功会返回device token
                device_token = deviceToken;
                Log.d("===","device_token:"+device_token);
                SharedPrefUtil.getInstance().setDeviceToken(device_token);
            }
            @Override
            public void onFailure(String s, String s1) {
                Log.d("===","==="+s1);
            }
        });

        UMShareAPI.get(this);
        //友盟分享各平台配置
        PlatformConfig.setWeixin("wxf4451af60a7f3501", "ba438da1dabe212a10f41f25c26692b1");
        PlatformConfig.setSinaWeibo("4059899234", "48a6b4a2f61de6bc02ad5e8083a8a220","http://sns.whalecloud.com/sina2/callback");
        PlatformConfig.setQQZone("1105164001",
                "gADdUXVvwRQEIknK");
//        新浪微博的回调地址
//        Config.REDIRECT_URL="http://sns.whalecloud.com/sina2/callback";
        /**
         * jd开普勒
         * */
        KeplerApiManager.asyncInitSdk(this, SnsUtil.KJD_APP_KEY ,SnsUtil.KJD_APP_SECRET,new AsyncInitListener() {
            @Override
            public void onSuccess() {
                LogUtil.d(TAG, "kjd_success");
            }
            @Override
            public void onFailure() {
                LogUtil.d(TAG, "kjd_failure");
            }});
        ApplicationConfig.getInstance().initConfig(this);

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LogUtil.i(TAG, "onConfigurationChanged");
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        LogUtil.i(TAG, "onLowMemory");
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        LogUtil.i(TAG, "onTerminate");
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        LogUtil.i(TAG, "onTrimMemory");
    }
}
