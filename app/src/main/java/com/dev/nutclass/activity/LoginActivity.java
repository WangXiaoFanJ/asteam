package com.dev.nutclass.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dev.nutclass.ApplicationConfig;
import com.dev.nutclass.R;
import com.dev.nutclass.constants.Const;
import com.dev.nutclass.constants.UrlConst;
import com.dev.nutclass.entity.BaseCardEntity;
import com.dev.nutclass.entity.JsonResult;
import com.dev.nutclass.entity.UserInfoEntity;
import com.dev.nutclass.fragment.BaseFragment;
import com.dev.nutclass.network.OkHttpClientManager;
import com.dev.nutclass.parser.UserInfoparser;
import com.dev.nutclass.utils.DialogUtils;
import com.dev.nutclass.utils.LogUtil;
import com.dev.nutclass.utils.MyCountTimer;
import com.dev.nutclass.utils.MyUtil;
import com.dev.nutclass.utils.SharedPrefUtil;
import com.dev.nutclass.utils.TextUtil;
import com.dev.nutclass.view.TitleBar;
import com.squareup.okhttp.Request;
import com.umeng.socialize.Config;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.utils.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "LoginActivity";
    private Context mContext;
    private UMShareAPI mShareAPI;
    private TabLayout tabLayout;
    private LinearLayout phoneContainerLayout;
    private LinearLayout accountContainerLayout;
    private TitleBar titleBar;
    private LinearLayout qqLayout;
    private LinearLayout weixinLayout;
    private LinearLayout weiboLayout;
    private TextView loginTv;
    private TextView loginTv02;
    private EditText phone02Edit;
    private EditText verifyCodeEdit;
    private EditText phoneEdit;
    private EditText pwdEdit;
    private TextView reqVerifyTv;
    private TextView reqVoiceVerifyTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mContext = LoginActivity.this;
        MyUtil.addActivity((Activity) mContext);
        initView();
        initListener();
        initData();
    }

    private void initView() {
        titleBar = (TitleBar) findViewById(R.id.title_bar);
        qqLayout = (LinearLayout) findViewById(R.id.ll_qq);
        weixinLayout = (LinearLayout) findViewById(R.id.ll_weixin);
        weiboLayout = (LinearLayout) findViewById(R.id.ll_weibo);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        loginTv = (TextView) findViewById(R.id.tv_login);
        phoneEdit = (EditText) findViewById(R.id.edit_phone);
        pwdEdit = (EditText) findViewById(R.id.edit_pwd);
        phoneContainerLayout = (LinearLayout) findViewById(R.id.ll_container_phone);
        accountContainerLayout = (LinearLayout) findViewById(R.id.ll_container_account);
        loginTv02 = (TextView) findViewById(R.id.tv_login_02);
        verifyCodeEdit = (EditText) findViewById(R.id.edit_verify_code);
        phone02Edit = (EditText) findViewById(R.id.edit_phone_02);
        reqVerifyTv = (TextView) findViewById(R.id.tv_req_verify);
        reqVoiceVerifyTv = (TextView) findViewById(R.id.tv_req_voice_verify);
    }

    private void initListener() {
        qqLayout.setOnClickListener(this);
        weixinLayout.setOnClickListener(this);
        weiboLayout.setOnClickListener(this);
        loginTv.setOnClickListener(this);
        loginTv02.setOnClickListener(this);
        reqVerifyTv.setOnClickListener(this);
        reqVoiceVerifyTv.setOnClickListener(this);
        titleBar.setBarClickListener(new TitleBar.BarClickListener() {
            @Override
            public boolean onClickRight1() {
                startActivity(new Intent(mContext, RegisterActivity.class));
                return false;
            }
        });
    }

    private void initData() {
        tabLayout.addTab(tabLayout.newTab().setText("账号密码登录"));
        tabLayout.addTab(tabLayout.newTab().setText("手机号快捷登录"));
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    accountContainerLayout.setVisibility(View.VISIBLE);
                    phoneContainerLayout.setVisibility(View.GONE);
                } else if (tab.getPosition() == 1) {
                    accountContainerLayout.setVisibility(View.GONE);
                    phoneContainerLayout.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_qq:
                reqThirdLogin(SHARE_MEDIA.QQ);
                break;
            case R.id.ll_weixin:
                reqThirdLogin(SHARE_MEDIA.WEIXIN);
                break;
            case R.id.ll_weibo:
                reqThirdLogin(SHARE_MEDIA.SINA);
                break;
            case R.id.tv_login:
                reqAccoutLogin();
                break;
            case R.id.tv_login_02:
                reqSpeedLogin();
                break;
            case R.id.tv_req_verify:
                reqVerifyCode();
                break;
            case R.id.tv_req_voice_verify:
                reqVoiceVerifyCode();
                break;
        }
    }

    /**请求短信验证码*/
    private void reqVerifyCode() {
        String mobile = phone02Edit.getText().toString().trim();
        if (TextUtils.isEmpty(mobile)) {
            DialogUtils.showToast(mContext, "手机号不能为空");
        } else {
            if (!MyUtil.isMobileNO(mobile)) {
                DialogUtils.showToast(mContext, "请输入正确的手机号");
            } else {
                MyUtil.reqVerifyCode(mobile,mContext,"0");
                MyCountTimer myCount = new MyCountTimer(60 * 1000, 1000, reqVerifyTv);
                myCount.start();
            }
        }
    }

    /** 请求语言验证码*/
    private void reqVoiceVerifyCode() {
        String mobile = phone02Edit.getText().toString().trim();
        if (TextUtils.isEmpty(mobile)) {
            DialogUtils.showToast(mContext, "手机号不能为空");
        } else {
            if (!MyUtil.isMobileNO(mobile)) {
                DialogUtils.showToast(mContext, "请输入正确的手机号");
            } else {
                MyUtil.reqVerifyCode(mobile,mContext,"1");
            }
        }
    }

    /**
     * 短信快捷登录
     */
    private void reqSpeedLogin() {
        String url = UrlConst.ACCOUNT_PWS_LOGIN_URL;
        String phone = phone02Edit.getText().toString().trim();
        String verifyCode = verifyCodeEdit.getText().toString().trim();
        Map<String, String> params = new HashMap<>();
        params.put("user_phone", phone);
        params.put("verCode", verifyCode);
        if (MyUtil.isMobileNO(phone) && verifyCode != null) {
            reqLogin(url, params);
        } else {
            DialogUtils.showToast(mContext, "账号或验证码错误");
        }

    }

    /**
     * 账号密码登录
     */
    private void reqAccoutLogin() {
        String url = UrlConst.ACCOUNT_PWS_LOGIN_URL;
        String phone = phoneEdit.getText().toString().trim();
        String pwd = pwdEdit.getText().toString().trim();
        Map<String, String> params = new HashMap<>();
        params.put("user_phone", phone);
        params.put("password", pwd);
        if (MyUtil.isMobileNO(phone) && pwd != null) {
            reqLogin(url, params);
        } else {
            DialogUtils.showToast(mContext, "账号或密码错误");
        }
    }

    /**
     * 请求登录接口
     */
    private void reqLogin(String url, Map<String, String> params) {
        OkHttpClientManager.postAsyn(url, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
                LogUtil.d(TAG, "error:" + e.getMessage());
            }

            @Override
            public void onResponse(String response) {
                LogUtil.d(TAG, "response:" + response);
                UserInfoparser parser = new UserInfoparser();
                try {
                    JsonResult<BaseCardEntity> result = (JsonResult<BaseCardEntity>) parser
                            .parse(response);
                    JSONObject jsonObj = new JSONObject(response);
                    String message = jsonObj.optString("message");
                    if (result.getErrorCode() == UrlConst.SUCCESS_CODE) {
                        DialogUtils.showToast(mContext, message);
                        Intent intent = new Intent();
                        intent.setPackage(ApplicationConfig.getInstance().getPackageName());
                        intent.setAction(Const.ACTION_BROADCAST_LOGIN_SUCC);
                        sendBroadcast(intent);
                        finish();
                    }else {
                        DialogUtils.showToast(mContext, message);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }, params);
    }

    /**
     * 请求三方登录
     */
    private void reqThirdLogin(final SHARE_MEDIA platform) {
        com.umeng.socialize.utils.Log.LOG = true;
        Config.DEBUG = true;
        mShareAPI = UMShareAPI.get(this);
        getUserInfo(platform);
    }

    /**
     * 三方登录获取用户信息
     */
    private void getUserInfo(final SHARE_MEDIA platform) {
        mShareAPI.getPlatformInfo(this, platform, new UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {

            }

            @Override
            public void onComplete(SHARE_MEDIA share_media, int status, Map<String, String> map) {
//                Toast.makeText(mContext, "", Toast.LENGTH_SHORT).show();
                if (status == 200) {

                }
                if (map != null) {
                    StringBuilder sb = new StringBuilder();
                    Set<String> keys = map.keySet();
                    for (String key : keys) {
                        sb.append(key + "=" + map.get(key).toString() + ";");
                    }
                    sb.append("\r\n");
                    Toast.makeText(getApplicationContext(), "sb" + sb.toString(), Toast.LENGTH_LONG).show();
                  Log.d("===","sb:"+sb.toString());
                    thirdLogin2UserEntity(platform, map);
                }
            }

            @Override
            public void onError(SHARE_MEDIA share_media, int status, Throwable throwable) {
//                Toast.makeText(mContext, "error", Toast.LENGTH_SHORT).show();
                Log.d("===","error");
            }

            @Override
            public void onCancel(SHARE_MEDIA share_media, int status) {
//                Toast.makeText(mContext, "cancel" + status, Toast.LENGTH_SHORT).show();
                Log.d("===","cancle");
            }
        });
    }

    /**
     * 三方登录信息Entity
     */
    private void thirdLogin2UserEntity(SHARE_MEDIA platform, Map<String, String> map) {
        int USER_TYPE_WEIBO = 2;
        int USER_TYPE_WEIXIN = 3;
        int USER_TYPE_QQ = 1;
        UserInfoEntity entity = new UserInfoEntity();
        if (platform == SHARE_MEDIA.QQ) {
            entity.setUserType(USER_TYPE_QQ);
            entity.setUserId(map.get("uid"));
            entity.setHeadIcon(map.get("iconurl"));
            entity.setUserName(map.get("name"));
        } else if (platform == SHARE_MEDIA.WEIXIN) {
            entity.setUserType(USER_TYPE_WEIXIN);
            entity.setUserId(map.get("uid"));
            entity.setHeadIcon(map.get("iconurl"));
            entity.setUserName(map.get("name"));
        } else if (platform == SHARE_MEDIA.SINA) {
            entity.setUserType(USER_TYPE_WEIBO);
            entity.setUserId(map.get("uid"));
            entity.setHeadIcon(map.get("iconurl"));
            entity.setUserName(map.get("name"));
        }
        // 判断是否是注册用户
        reqIsThirdLogin(platform, entity);
    }

    private void reqIsThirdLogin(SHARE_MEDIA platform, final UserInfoEntity entity) {
        String device_token = SharedPrefUtil.getInstance().getDeviceToken();
        String mobile_type = "2";
        String url = UrlConst.THIRD_LOGIN_URL;
        Map<String,String> map = new HashMap<>();
        LogUtil.d("===","openid:"+entity.getUserId()+
        "username:"+entity.getUserName()+
        "headimgurl:"+entity.getHeadIcon()+
        "device_token:"+device_token+
        "mobile_type:"+mobile_type
        +"login_type:"+String.valueOf(entity.getUserType()));
        map.put("openid",entity.getUserId());
        map.put("username",entity.getUserName());
        map.put("headimgurl",entity.getHeadIcon());
        map.put("device_token",device_token);
        map.put("mobile_type",mobile_type);
        map.put("login_type",String.valueOf(entity.getUserType()));
        OkHttpClientManager.postAsyn(url, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
                LogUtil.d(TAG, "error e=" + e.getMessage());
            }

            @Override
            public void onResponse(String response) {
                LogUtil.d(TAG, "response=" + response);
                UserInfoparser parser = new UserInfoparser();
                try {
                    JsonResult<BaseCardEntity> result = (JsonResult<BaseCardEntity>) parser
                            .parse(response);
                    JSONObject jsonObj = new JSONObject(response);
                    JSONObject jsonObj02 = jsonObj.optJSONObject("data");
                    String mobile = jsonObj02.optString("user_phone");
                    String userId = jsonObj02.optString("user_id");
                    Log.d("===","mobile:"+mobile);
                    if (result.getErrorCode() == UrlConst.SUCCESS_CODE) {
                        if (TextUtil.isNotNull(mobile)) {
                            DialogUtils.showToast(mContext, "登陆成功");
                            Intent intent = new Intent();
                            intent.setPackage(ApplicationConfig
                                    .getInstance().getPackageName());
                            intent.setAction(Const.ACTION_BROADCAST_LOGIN_SUCC);
                            sendBroadcast(intent);
                        } else {
                            Intent intent = new Intent();
                            intent.setClass(mContext, AddMobileActivity.class);
                            intent.putExtra(Const.TYPE_USER_ID,userId);
                            startActivity(intent);
//                            intent.putExtra(Const.KEY_ID, SharedPrefUtil
//                                    .getInstance().getUid());
//                            startActivityForResult(intent, REQ_PHONE_LOGIN);
                            SharedPrefUtil.getInstance().setUserSession("");
                            SharedPrefUtil.getInstance().setToken("");
                            SharedPrefUtil.getInstance().setMobile("");
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        },map);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UMShareAPI.get(this).release();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode,resultCode,data);//完成回调
    }
}
