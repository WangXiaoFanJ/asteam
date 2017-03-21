package com.dev.nutclass.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.dev.nutclass.ApplicationConfig;
import com.dev.nutclass.R;
import com.dev.nutclass.constants.Const;
import com.dev.nutclass.constants.UrlConst;
import com.dev.nutclass.entity.BaseCardEntity;
import com.dev.nutclass.entity.JsonResult;
import com.dev.nutclass.network.OkHttpClientManager;
import com.dev.nutclass.parser.SimpleParser;
import com.dev.nutclass.parser.UserInfoparser;
import com.dev.nutclass.utils.DialogUtils;
import com.dev.nutclass.utils.LogUtil;
import com.dev.nutclass.utils.MyUtil;
import com.dev.nutclass.view.TitleBar;
import com.squareup.okhttp.Request;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivityToSetPwd extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "RegisterActivityToSetPwd";
    private Context mContext;
    private EditText pwdEdit01;
    private EditText pwdEdit02;
    private TextView confirmTv;
    private String mobile;
    private TitleBar titleBar;
    private int fromType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_activity_to_set_pwd);
        mContext = RegisterActivityToSetPwd.this;
        MyUtil.addActivity((Activity) mContext);
        initView();
        initIntent();
    }

    private void initView() {
        pwdEdit01 = (EditText) findViewById(R.id.edit_pwd_01);
        pwdEdit02 = (EditText) findViewById(R.id.edit_pwd_02);
        confirmTv = (TextView) findViewById(R.id.tv_confirm);
        titleBar = (TitleBar) findViewById(R.id.title_bar);
        confirmTv.setOnClickListener(this);
    }

    private void initIntent() {
//        mobile = getIntent().getStringExtra(Const.TYPE_REGISTER_PHONE);
        Bundle bundle = getIntent().getExtras();
        mobile = bundle.getString(Const.TYPE_REGISTER_PHONE);
        fromType = bundle.getInt(Const.TYPE_FORGET_PWD);
        if(fromType==1){
            titleBar.setMiddleTextNew(9);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_confirm:
                String url = "";
                if(fromType==1){
                    url = UrlConst.CHANGE_PWD_URL;
                }else{
                    url = UrlConst.SET_PWD_URL;
                }

                String pwd01 = pwdEdit01.getText().toString().trim();
                String pwd02 = pwdEdit02.getText().toString().trim();
                Map<String ,String> parmas = new HashMap<>();
                parmas.put("user_phone",mobile);
                parmas.put("password",pwd01);
                if(pwd01.length()>=6 &&pwd02.length()>=6 &&pwd01.equals(pwd02)){
                    OkHttpClientManager.postAsyn(url, new OkHttpClientManager.ResultCallback<String>() {
                        @Override
                        public void onError(Request request, Exception e) {
                            LogUtil.d(TAG,"error:"+e.getMessage());
                        }

                        @Override
                        public void onResponse(String response) {
                            LogUtil.d(TAG,"response:"+response);
                            UserInfoparser parser = new UserInfoparser();
                            try {
                                JsonResult<BaseCardEntity> result = (JsonResult<BaseCardEntity>) parser
                                        .parse(response);
                                JSONObject jsonObject = new JSONObject(response);

                                if (result.getErrorCode() == UrlConst.SUCCESS_CODE) {
                                    if(fromType==1){
                                        DialogUtils.showToast(mContext, jsonObject.optString("message"));
                                    }else{
                                        DialogUtils.showToast(mContext, jsonObject.optString("message"));
                                        Intent intent = new Intent();
                                        intent.setPackage(ApplicationConfig.getInstance().getPackageName());
                                        intent.setAction(Const.ACTION_BROADCAST_LOGIN_SUCC);
                                        sendBroadcast(intent);
                                    }
                                    MyUtil.finishAll();
                                }else{
                                    DialogUtils.showToast(mContext, jsonObject.optString("message"));
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    },parmas);
                }
                break;
        }
    }
}
