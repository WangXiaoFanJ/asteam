package com.dev.nutclass.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.dev.nutclass.constants.Const;
import com.dev.nutclass.utils.LogUtil;
import com.umeng.message.PushAgent;

/**
 * Created by Administrator on 2016/12/27.
 */
public abstract class BaseActivity extends FragmentActivity{
    /** 日志输出标志 **/
    protected final String TAG = this.getClass().getSimpleName();
    private BroadcastReceiver mReceiver;
    public void unregisterReceiver(){
        if(mReceiver!=null){
            unregisterReceiver(mReceiver);
        }
        mReceiver=null;
    }

    public void registerReceiver(){
        String[] filters = {Const.ACTION_BROADCAST_LOGIN_SUCC,
                Const.ACTION_BROADCAST_UPDATA_USER_INFO,
                Const.ACTION_BROADCAST_EXIT_SUCC};
        registerReceiver(filters);
    }
    public void registerReceiver(final String[] filters){
        unregisterReceiver();
        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
             if(intent.getAction().equals(Const.ACTION_BROADCAST_LOGIN_SUCC)){
                 updateUserInfo(true);
             }else if(intent.getAction().equals(Const.ACTION_BROADCAST_UPDATA_USER_INFO)){
                 updateUserInfo(true);
             }else if (intent.getAction().equals(Const.ACTION_BROADCAST_EXIT_SUCC)){
                 exitLogin(true);
             }
            }
        };
        if(filters!=null&&filters.length>0){
            IntentFilter filter = new IntentFilter();
            for (int i=0;i<filters.length;i++){
                String action = filters[i];
                LogUtil.i(TAG,"registerReceiver:action"+action);
                filter.addAction(action);
            }
            registerReceiver(mReceiver,filter);
        }
    }

    // 更新用户信息
    public void updateUserInfo(boolean isBackGround) {

    }
    //退出登录
    public void exitLogin(boolean isExit){

    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        PushAgent.getInstance(this).onAppStart();
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart()");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy()");
    }

}
