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

import com.dev.nutclass.R;
import com.dev.nutclass.constants.Const;
import com.dev.nutclass.constants.UrlConst;
import com.dev.nutclass.entity.SimpleEntity;
import com.dev.nutclass.network.OkHttpClientManager;
import com.dev.nutclass.parser.SimpleParser;
import com.dev.nutclass.utils.DialogUtils;
import com.dev.nutclass.utils.LogUtil;
import com.dev.nutclass.utils.MyCountTimer;
import com.dev.nutclass.utils.MyUtil;
import com.squareup.okhttp.Request;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

public class ChangePwdActivity01 extends BaseActivity implements View.OnClickListener {
    private Context mContext;
    private EditText phoneEdit,verifyEdit;
    private TextView reqVerifyTv,confirmTv,voiceVerifyTv;
    private String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pwd01);
        mContext = ChangePwdActivity01.this;
        MyUtil.addActivity((Activity) mContext);
        initView();
        userId = getIntent().getStringExtra(Const.TYPE_USER_ID);
    }

    private void initView() {
        phoneEdit = (EditText) findViewById(R.id.edit_phone_02);
        verifyEdit = (EditText) findViewById(R.id.edit_verify_code);
        reqVerifyTv = (TextView) findViewById(R.id.tv_req_verify);
        confirmTv = (TextView) findViewById(R.id.tv_login_02);
        voiceVerifyTv = (TextView) findViewById(R.id.tv_req_voice_verify);

        reqVerifyTv.setOnClickListener(this);
        confirmTv.setOnClickListener(this);
        voiceVerifyTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_req_verify:
                reqVerifyCode();
                break;
            case R.id.tv_login_02:
                String verifyCode = verifyEdit.getText().toString().trim();
                if(TextUtils.isEmpty(verifyCode)){
                    DialogUtils.showToast(mContext,"验证码不能为空");
                }else{
                    checkVerifyCode(verifyCode);
                }
                break;
            case R.id.tv_req_voice_verify:
                reqVoiceVerifyCode();
                break;
        }
    }
    /** 检查验证码是否正确*/
    private void checkVerifyCode(String verifyCode) {
        String url = UrlConst.CHECK_VERIFY_CODE_URL;
        Map<String,String> parmas = new HashMap<>();
        parmas.put("verCode",verifyCode);
        parmas.put("user_phone",phoneEdit.getText().toString());
        OkHttpClientManager.postAsyn(url, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
                LogUtil.d(TAG,"error:"+e.getMessage());
            }

            @Override
            public void onResponse(String response) {
                LogUtil.d(TAG,"response:"+response);
                SimpleParser parser = new SimpleParser();
                try {
                    SimpleEntity entity = (SimpleEntity) parser.parse(response);
//                    DialogUtils.showToast(mContext,entity.getData());
                    if(entity.getStatus().equals("1")){
                        Intent intent = new Intent(mContext,ChangePwdActivity02.class);
                        Bundle bundle = new Bundle();
                        bundle.putString(Const.TYPE_USER_ID,userId);
                        bundle.putString(Const.TYPE_USER_PHONE,phoneEdit.getText().toString());
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },parmas);
    }

    /**请求短信验证码*/
    private void reqVerifyCode() {
        String mobile = phoneEdit.getText().toString().trim();
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
        String mobile = phoneEdit.getText().toString().trim();
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
}
