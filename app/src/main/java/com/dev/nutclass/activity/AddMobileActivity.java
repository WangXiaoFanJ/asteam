package com.dev.nutclass.activity;

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
import com.dev.nutclass.parser.UserInfoparser;
import com.dev.nutclass.utils.DialogUtils;
import com.dev.nutclass.utils.LogUtil;
import com.dev.nutclass.utils.MyCountTimer;
import com.dev.nutclass.utils.MyUtil;
import com.squareup.okhttp.Request;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AddMobileActivity extends BaseActivity implements View.OnClickListener{
    private static final String TAG = "AddMobileActivity";
    private Context mContext;
    private EditText phoneEdit;
    private EditText verifyCodeEdit;
    private TextView timerTv;
    private TextView confirmTv;
    private String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mobile);
        mContext = AddMobileActivity.this;
        MyUtil.addActivity(AddMobileActivity.this);
        userId = getIntent().getStringExtra(Const.TYPE_USER_ID);
        iniView();
    }

    private void iniView() {
        phoneEdit = (EditText) findViewById(R.id.edit_phone);
        verifyCodeEdit = (EditText) findViewById(R.id.edit_verify_code);
        timerTv = (TextView) findViewById(R.id.tv_req_verify);
        confirmTv = (TextView) findViewById(R.id.tv_confirm);

        timerTv.setOnClickListener(this);
        confirmTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_req_verify:
                String mobile = phoneEdit.getText().toString().trim();
                if (TextUtils.isEmpty(mobile)) {
                    DialogUtils.showToast(mContext, "手机号不能为空");
                } else {
                    if (!MyUtil.isMobileNO(mobile)) {
                        DialogUtils.showToast(mContext, "请输入正确的手机号");
                    } else {
                        MyUtil.reqVerifyCode(mobile,mContext,"0");
                        MyCountTimer myCount = new MyCountTimer(60 * 1000, 1000, timerTv);
                        myCount.start();
                    }
                }
                break;
            case R.id.tv_confirm:
                confirm();
                break;
        }
    }

    private void confirm() {
        String url = UrlConst.BIND_PHONE_URL;
        String phone = phoneEdit.getText().toString().trim();
        String verifyCode = verifyCodeEdit.getText().toString().trim();
        Map<String, String> params = new HashMap<>();
        params.put("userId",userId);
        params.put("user_phone", phone);
        params.put("verCode", verifyCode);
        if (MyUtil.isMobileNO(phone) && verifyCode != null) {
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
                        JSONObject jsonObject = new JSONObject(response);
                        JsonResult<BaseCardEntity> result = (JsonResult<BaseCardEntity>) parser
                                .parse(response);
                        if (result.getErrorCode() == UrlConst.SUCCESS_CODE) {
                            DialogUtils.showToast(mContext, jsonObject.optString("message"));
                            Intent intent = new Intent();
                            intent.setPackage(ApplicationConfig.getInstance().getPackageName());
                            intent.setAction(Const.ACTION_BROADCAST_LOGIN_SUCC);
                            sendBroadcast(intent);
                            MyUtil.finishAll();
                        }else {
                            DialogUtils.showToast(mContext, jsonObject.optString("message"));
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }, params);
        } else {
            DialogUtils.showToast(mContext, "请检查输入的手机号或验证码");
        }
    }

}
