package com.dev.nutclass;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;

import com.dev.nutclass.utils.LogUtil;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2016/12/28.
 */
public class ApplicationConfig {
    private static final String TAG = "ApplicationConfig";
    private String packageName;
    private String versionName;
    private int versionCode;
    private MyApplication application;
    private Context context;

    private static ApplicationConfig instance = null;
    private static Lock sLock = new ReentrantLock();
    private ArrayList<Activity> mActivities = new ArrayList<>();



    public static ApplicationConfig getInstance(){
        if(instance==null) {
            try {
                sLock.lock();
                if (instance == null) {
                    instance = new ApplicationConfig();
                }
            } finally {
                sLock.unlock();
            }
        }
        return instance;
    }
    public void addActivity(Activity activity){
        if(!mActivities.contains(activity)){
            mActivities.add(activity);
        }
    }
    public void removeActivity(Activity activity){
        if(mActivities.contains(activity)){
            mActivities.remove(activity);
        }
    }
    public void clearActivitys(ArrayList<Activity> mActivities){
        for(int i = 0;i<mActivities.size();i++){
            Activity activity = mActivities.get(i);
            LogUtil.i(TAG,"finish activity="+activity.getClass().getSimpleName());
            if(activity!=null && !activity.isFinishing()){
                activity.finish();
            }
        }
        mActivities.clear();
    }
    public void initConfig(MyApplication application){
            synchronized (sLock){
                this.application = application;
                context = application.getBaseContext();
                packageName = context.getPackageName();
                versionCode = getVersionCode(context);
                versionName = getVersionName(context);
                try {
                    sLock.notifyAll();
                } catch (Exception e) {
                }
            }
    }
    public void updateConfig(MyApplication application) {
        initConfig(application);
    }
    public int getVersionCode(Context context){
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(),0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return -1;
        }
    }
    public String getVersionName(Context context){
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(),0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "1.0";
        }
    }

    public Context getBaseContext() {
        return this.context;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getVersionname() {
        return versionName;
    }

    public void setVersionname(String versionname) {
        this.versionName = versionname;
    }
}
