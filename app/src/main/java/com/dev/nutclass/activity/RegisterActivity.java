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
import com.dev.nutclass.utils.DialogUtils;
import com.dev.nutclass.utils.MyUtil;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {
    private TextView confirmTv;
    private Context mContext;
    private EditText phoneEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mContext = RegisterActivity.this;
        initView();
        initListener();
        MyUtil.addActivity((Activity) mContext);
    }


    private void initView() {
        confirmTv = (TextView) findViewById(R.id.tv_confirm);
        phoneEdit = (EditText) findViewById(R.id.edit_phone);

    }

    private void initListener() {
        confirmTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_confirm:
                String mobile =phoneEdit.getText().toString().trim();
                if(TextUtils.isEmpty(mobile)){
                    DialogUtils.showToast(mContext,"手机号不能为空");
                }else{
                    if(!MyUtil.isMobileNO(mobile)){
                        DialogUtils.showToast(mContext,"请输入正确的手机号");
                    }else{
                        Intent intent = new Intent(mContext,RegisterToCodeActivity.class);
                        intent.putExtra(Const.TYPE_REGISTER_PHONE,mobile);
                        startActivity(intent);
                    }
                }

                break;
        }
    }

}
