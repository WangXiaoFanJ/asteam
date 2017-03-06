package com.dev.nutclass.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
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
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterToCodeActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG="RegisterToCodeActivity";
    private Context mContext;
    private TextView confirmTv;
    private TextView timerTv;
    private String mobile;
    private EditText verifyCodeEdit;
    private TextView hintMobileTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_to_code);
        mContext = RegisterToCodeActivity.this;
        MyUtil.addActivity((Activity) mContext);
        initView();
        initIntent();
        MyUtil.reqVerifyCode(mobile,mContext,"0");
        MyCountTimer myCount = new MyCountTimer(60*1000,1000,timerTv);
        myCount.start();
    }

    private void initIntent() {
        mobile = getIntent().getStringExtra(Const.TYPE_REGISTER_PHONE);
        hintMobileTv.setText("验证码已发送到手机号"+mobile);

    }

    private void initView() {
        confirmTv = (TextView) findViewById(R.id.tv_confirm);
        timerTv = (TextView) findViewById(R.id.tv_timer);
        verifyCodeEdit = (EditText) findViewById(R.id.edit_verify_code);
        hintMobileTv = (TextView) findViewById(R.id.tv_hint_mobile);
        confirmTv.setOnClickListener(this);
        timerTv.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_confirm:
                String verifyCode = verifyCodeEdit.getText().toString().trim();
                if(TextUtils.isEmpty(verifyCode)){
                    DialogUtils.showToast(mContext,"验证码不成为空");
                }else{
                    checkVerifyCode(verifyCode);
                }
//                startActivity(new Intent(mContext,RegisterActivityToSetPwd.class));
                break;
            case R.id.tv_timer:
                MyUtil.reqVerifyCode(mobile,mContext,"0");
//                MyCount myCount = new MyCount(30*1000,1000);
                MyCountTimer myCount = new MyCountTimer(60*1000,1000,timerTv);
                myCount.start();
                break;
        }
    }

    private void checkVerifyCode(String verifyCode) {
        String url = UrlConst.CHECK_VERIFY_CODE_URL;
        Map<String,String> parmas = new HashMap<>();
        parmas.put("verCode",verifyCode);
        parmas.put("user_phone",mobile);
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
                    DialogUtils.showToast(mContext,entity.getData());
                    if(entity.getStatus().equals("1")){
                        Intent intent = new Intent(mContext,RegisterActivityToSetPwd.class);
                        intent.putExtra(Const.TYPE_REGISTER_PHONE,mobile);
                        startActivity(intent);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },parmas);
    }


}
