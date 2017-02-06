package com.dev.nutclass;

import android.app.Application;
import android.content.res.Configuration;

import com.dev.nutclass.utils.LogUtil;
import com.dev.nutclass.utils.SnsUtil;
import com.kepler.jd.Listener.AsyncInitListener;
import com.kepler.jd.login.KeplerApiManager;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

/**
 * Created by Administrator on 2016/12/27.
 */
public class MyApplication extends Application {
    public static final String TAG = "MyApplication";
    @Override
    public void onCreate() {
        super.onCreate();
        UMShareAPI.get(this);
        //友盟分享各平台配置
        PlatformConfig.setWeixin("wxf4451af60a7f3501", "ba438da1dabe212a10f41f25c26692b1");
        PlatformConfig.setSinaWeibo("4059899234", "48a6b4a2f61de6bc02ad5e8083a8a220");
        PlatformConfig.setQQZone("1105164001",
                "gADdUXVvwRQEIknK");
        //新浪微博的回调地址
        Config.REDIRECT_URL="http://sns.whalecloud.com/sina2/callback";
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
