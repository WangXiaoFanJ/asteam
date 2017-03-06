package com.dev.nutclass.activity;

import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.dev.nutclass.ApplicationConfig;
import com.dev.nutclass.R;
import com.dev.nutclass.constants.Const;
import com.dev.nutclass.constants.UrlConst;
import com.dev.nutclass.entity.UserInfoEntity;
import com.dev.nutclass.network.OkHttpClientManager;
import com.dev.nutclass.utils.DialogUtils;
import com.dev.nutclass.utils.LogUtil;
import com.dev.nutclass.utils.MyUtil;
import com.dev.nutclass.utils.SharedPrefUtil;
import com.squareup.okhttp.Request;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class EditUserInfoActivity extends BaseActivity implements View.OnClickListener{
    private static final String TAG = "EditUserInfoActivity";
    private Context mContext;
    private EditText nickEdit;
    private TextView confirmTv;
    private ImageView backIv;
    private String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_info);
        mContext = EditUserInfoActivity.this;
        initView();
        userId = getIntent().getStringExtra(Const.TYPE_USER_ID);
    }

    private void initView() {
        nickEdit = (EditText) findViewById(R.id.edit_user_nick);
        confirmTv = (TextView) findViewById(R.id.tv_confirm);
        backIv = (ImageView) findViewById(R.id.iv_back);
        confirmTv.setOnClickListener(this);
        backIv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_confirm:
                final String userNick = nickEdit.getText().toString().trim();
                if(userNick.equals("")){
                    DialogUtils.showToast(mContext,"用户昵称不能为空");
                }else{
                    MyUtil.reqChangeUserInfoURL(mContext,userId,Const.USER_NAME,userNick);
//                    Map<String,String> map = new HashMap<>();
//                    map.put("userId",userId);
//                    map.put("user_name",userNick);
//                    LogUtil.d("===","userId"+userId+"userName"+userNick);
//                    OkHttpClientManager.postAsyn(UrlConst.EDIT_USER_INFO_URL, new OkHttpClientManager.ResultCallback<String>() {
//                        @Override
//                        public void onError(Request request, Exception e) {
//                            LogUtil.d(TAG,"error:"+e.getMessage());
//                        }
//
//                        @Override
//                        public void onResponse(String response) {
//                            LogUtil.d(TAG,"response:"+response);
//                            try {
//                                JSONObject jsonObj = new JSONObject(response);
//                                String status = jsonObj.optString("status");
//                                if(status.equals("1")){
//                                    UserInfoEntity entity = SharedPrefUtil.getInstance().getSession();
//                                    entity.setUserName(userNick);
//                                    SharedPrefUtil.getInstance().setUserSession(entity.buildJsonObject().toString());
//                                    Intent intent = new Intent();
//                                    intent.setPackage(ApplicationConfig
//                                            .getInstance().getPackageName());
//                                    intent.setAction(Const.ACTION_BROADCAST_UPDATA_USER_INFO);
//                                    sendBroadcast(intent);
//                                    finish();
//                                }
//                                DialogUtils.showToast(mContext,jsonObj.optString("message"));
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        }
//
//                    },map);
            }
                break;
        }
    }
}
