package com.dev.nutclass.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.dev.nutclass.constants.Const;
import com.dev.nutclass.utils.LogUtil;

/**
 * Created by Administrator on 2016/12/28.
 */
public abstract class BaseFragment extends Fragment {
    private static final String TAG = "BaseFragment";
    private BroadcastReceiver receiver;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public void registerReceiver(String[] filers){
        unRegisterReceiver();
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction()
                        .equals(Const.ACTION_BROADCAST_LOGIN_SUCC)) {
                    updateUserInfo(true);
                    LogUtil.i(TAG, "send success log");
                }else  if (intent.getAction().equals(Const.ACTION_BROADCAST_UPDATA_USER_INFO)){
                    updateUserInfo(true);
                }else if(intent.getAction().equals(Const.ACTION_BROADCAST_EXIT_SUCC)){
                    exitLogin(true);
                }
            }
        };
        if (filers != null && filers.length > 0) {
            IntentFilter filter = new IntentFilter();
            for (int i = 0; i < filers.length; i++) {
                String action = filers[i];
                LogUtil.i(TAG, "registerReceiver:action" + action);
                filter.addAction(action);
            }
        getActivity().registerReceiver(receiver,filter);
    }
    }

    // 更新用户信息
    public void updateUserInfo(boolean isBackGround) {

    }
    //退出登录
    public void exitLogin(boolean isExitLogin){

    }

    public void unRegisterReceiver(){
        if(receiver!=null){
            getActivity().unregisterReceiver(receiver);
        }
        receiver =null;
    }
}
