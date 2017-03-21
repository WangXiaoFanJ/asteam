package com.dev.nutclass.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.dev.nutclass.R;
import com.dev.nutclass.constants.Const;
import com.dev.nutclass.constants.UrlConst;
import com.dev.nutclass.entity.SimpleEntity;
import com.dev.nutclass.network.OkHttpClientManager;
import com.dev.nutclass.utils.DialogUtils;
import com.dev.nutclass.utils.LogUtil;
import com.dev.nutclass.utils.MyUtil;
import com.squareup.okhttp.Request;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ChangePwdActivity02 extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "ChangePwdActivity02";
    private Context mContext;
    private EditText pwdEdit02, pwdEdit01;
    private TextView confirmTv;
    private String userId,userPhone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pwd02);
        mContext = ChangePwdActivity02.this;
        MyUtil.addActivity((Activity) mContext);
        initView();
        Bundle bundle = getIntent().getExtras();
        userId = bundle.getString(Const.TYPE_USER_ID);
        userPhone = bundle.getString(Const.TYPE_USER_PHONE);
    }

    private void initView() {
        pwdEdit01 = (EditText) findViewById(R.id.edit_pwd_01);
        pwdEdit02 = (EditText) findViewById(R.id.edit_pwd_02);
        confirmTv = (TextView) findViewById(R.id.tv_confirm);
        confirmTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_confirm:
                changePwd();
                break;
        }
    }

    private void changePwd() {
        String url = UrlConst.CHANGE_PWD_URL;
        String pwd1 = pwdEdit01.getText().toString().trim();
        String pwd2 = pwdEdit02.getText().toString().trim();
        if (pwd1 != null && pwd2 != null) {
            if (pwd1.equals(pwd2)) {
                final Map<String,String> map = new HashMap<>();
                map.put("password",pwd1);
                map.put("new_password",pwd2);
                map.put("user_phone",userPhone);
                map.put("userId",userId);
                LogUtil.d(TAG,"pwd:"+pwd1+"new_pwd:"+pwd2+"phone:"+userPhone+"Id:"+userId);
                OkHttpClientManager.postAsyn(url, new OkHttpClientManager.ResultCallback<String>() {
                    @Override
                    public void onError(Request request, Exception e) {
                        LogUtil.d(TAG,"error:"+e.getMessage());
                    }

                    @Override
                    public void onResponse(String response) {
                        LogUtil.d(TAG,"response:"+response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            SimpleEntity entity = new SimpleEntity(jsonObject);
                            if(entity.getStatus().equals("1")){
                                DialogUtils.showToast(mContext,entity.getMessage());
                                MyUtil.finishAll();
                            }else{
                                DialogUtils.showToast(mContext,entity.getMessage());
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                },map);
            } else {
                DialogUtils.showToast(mContext, "账号密码不一致");
            }
        }
    }
}
