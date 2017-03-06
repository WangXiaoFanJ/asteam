package com.dev.nutclass.activity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dev.nutclass.ApplicationConfig;
import com.dev.nutclass.R;
import com.dev.nutclass.constants.Const;
import com.dev.nutclass.constants.UrlConst;
import com.dev.nutclass.entity.UserInfoEntity;
import com.dev.nutclass.network.OkHttpClientManager;
import com.dev.nutclass.utils.DialogUtils;
import com.dev.nutclass.utils.GlideUtils;
import com.dev.nutclass.utils.LogUtil;
import com.dev.nutclass.utils.MyUtil;
import com.dev.nutclass.utils.SharedPrefUtil;
import com.dev.nutclass.view.MyPopupWindow;
import com.foamtrace.photopicker.PhotoPickerActivity;
import com.foamtrace.photopicker.PhotoPreviewActivity;
import com.foamtrace.photopicker.SelectModel;
import com.foamtrace.photopicker.intent.PhotoPickerIntent;
import com.squareup.okhttp.Request;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UserInfoActivity extends BaseActivity implements View.OnClickListener{
    private Context mContext;
    private static  final String TAG = "UserInfoActivity";
    private static final int REQUEST_CAMERA_CODE = 11;
    private static final int REQUEST_PREVIEW_CODE = 22;
    private RelativeLayout babyBirthLayout;
    private RelativeLayout babyGenderLayout;
    private RelativeLayout headPortraitLayout;
    private RelativeLayout modifyPhoneLayout;
    private TextView babyBirthTv;
    private TextView genderTv;
    private ImageView hearPortraitIv;
    private MyPopupWindow popWindow;
    private TextView userName;
    private Button loginBtn;
    private String userId;
    private RelativeLayout userNameRL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        mContext = UserInfoActivity.this;
        initView();
        initListener();
        initData();
        registerReceiver();
    }

    private void initData() {
        GlideUtils.loadImageView(mContext,"",hearPortraitIv,0);
        if(SharedPrefUtil.getInstance().isLogin()){
            UserInfoEntity entity = SharedPrefUtil.getInstance().getSession();
            GlideUtils.loadImageView(mContext,entity.getHeadIcon(),hearPortraitIv,0);
            userName.setText(entity.getUserName());
            babyBirthTv.setText(entity.getBabyBirth());
            genderTv.setText(entity.getBabySex());
           userId =  entity.getUserId();
        }
    }


    private void initView() {
        babyBirthLayout = (RelativeLayout) findViewById(R.id.rl_baby_birth);
        babyBirthTv = (TextView) findViewById(R.id.tv_baby_birth);
        babyGenderLayout = (RelativeLayout) findViewById(R.id.rl_baby_gender);
        headPortraitLayout = (RelativeLayout) findViewById(R.id.rl_change_head_image);
        modifyPhoneLayout = (RelativeLayout) findViewById(R.id.rl_phone_modify);
        genderTv = (TextView) findViewById(R.id.tv_gender);
        hearPortraitIv = (ImageView) findViewById(R.id.iv_head_portrait);
        userName = (TextView) findViewById(R.id.tv_user_name);
        loginBtn = (Button) findViewById(R.id.btn_login);
        userNameRL = (RelativeLayout) findViewById(R.id.rl_user_name);
    }
    private void initListener() {
        babyBirthLayout.setOnClickListener(this);
        babyGenderLayout.setOnClickListener(this);
        headPortraitLayout.setOnClickListener(this);
        modifyPhoneLayout.setOnClickListener(this);
        loginBtn.setOnClickListener(this);
        userNameRL.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            switch (requestCode) {
                // 选择照片
                case REQUEST_CAMERA_CODE:
                    GlideUtils.loadImageView(mContext,data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT).get(0),
                           hearPortraitIv,0);
                    LogUtil.d("===","paths:"+data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT).get(0));
//                    loadAdpater(data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT));
                    break;
                // 预览
                case REQUEST_PREVIEW_CODE:
                    GlideUtils.loadImageView(mContext,data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT).get(0),
                            hearPortraitIv,0);
//                    GlideUtils.loadImageView(mContext,data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT).get(0),hearPortraitIv);
//                    loadAdpater(data.getStringArrayListExtra(PhotoPreviewActivity.EXTRA_RESULT));
                    break;
            }
        }
    }

    @Override
    public void onClick(View v) {
        if(v == babyBirthLayout){
            new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear,
                                      int dayOfMonth) {
// TODO Auto-generated method stub
//                        setTitle(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                    String babyBirth =year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
//                    babyBirthTv.setText(babyBirth);
                    MyUtil.reqChangeUserInfoURL(mContext,userId,Const.BABY_BITTHDAY,babyBirth);
                }
            }, 2000, 1, 1).show();
        }else if(v==babyGenderLayout){
            showPopupWidow(babyGenderLayout);
        }else if (v==headPortraitLayout){
            showPopupWidow(headPortraitLayout);
        }else if (v==modifyPhoneLayout){
            startActivity(new Intent(mContext,ModifyPhoneActivity.class));
        }else  if (v==loginBtn){
            SharedPrefUtil.getInstance().setToken("");
            SharedPrefUtil.getInstance().setUserSession("");
            SharedPrefUtil.getInstance().setMobile("");
            Intent intent = new Intent();
            intent.setPackage(ApplicationConfig.getInstance().getPackageName());
            intent.setAction(Const.ACTION_BROADCAST_EXIT_SUCC);
            sendBroadcast(intent);
            finish();
        }else if (v==userNameRL){
            Intent intent = new Intent(mContext,EditUserInfoActivity.class);
            intent.putExtra(Const.TYPE_USER_ID,userId);
            startActivity(intent);
        }
    }



    private void showPopupWidow(View v) {
        View rootView = null;
        rootView =LayoutInflater.from(mContext).inflate(R.layout.view_baby_gender_popup,null);
        TextView manGenderTv = (TextView) rootView.findViewById(R.id.tv_gender_man);
        TextView womanGenderTv = (TextView) rootView.findViewById(R.id.tv_gender_woman);
        TextView cancelTv = (TextView) rootView.findViewById(R.id.tv_cancel);

        if(v==babyGenderLayout){
            manGenderTv.setText("男");
            womanGenderTv.setText("女");
            manGenderTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    genderTv.setText("男");
                    MyUtil.reqChangeUserInfoURL(mContext,userId,Const.BABY_SEX,"男");
                    popWindow.dismiss();
                }
            });
            womanGenderTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    genderTv.setText("女");
                    MyUtil.reqChangeUserInfoURL(mContext,userId,Const.BABY_SEX,"女");
                    popWindow.dismiss();
                }
            });
        }else if(v == headPortraitLayout){
            manGenderTv.setText("拍照");
            womanGenderTv.setText("图库相册");
            manGenderTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PhotoPickerIntent intent = new PhotoPickerIntent(mContext);
                    intent.setSelectModel(SelectModel.SINGLE);
                    intent.setShowCarema(true);
//                intent.setImageConfig(null);
                    startActivityForResult(intent, REQUEST_CAMERA_CODE);
                    popWindow.dismiss();
                }
            });
            womanGenderTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PhotoPickerIntent intent = new PhotoPickerIntent(mContext);
                    intent.setSelectModel(SelectModel.SINGLE);
                    intent.setShowCarema(true);
//                  intent.setImageConfig(null);
                    startActivityForResult(intent, REQUEST_CAMERA_CODE);
                    popWindow.dismiss();
                }
            });
        }

        cancelTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popWindow.dismiss();
            }
        });
        popWindow= new MyPopupWindow(mContext,rootView);
        popWindow.setAnimationStyle(R.style.Anim_Menu_Bottombar);
        popWindow.showAtLocation(babyBirthLayout, Gravity.BOTTOM,0,0);

    }

    @Override
    public void updateUserInfo(boolean isBackGround) {
        super.updateUserInfo(isBackGround);

        UserInfoEntity entity = SharedPrefUtil.getInstance().getSession();
        userName.setText(entity.getUserName());
        babyBirthTv.setText(entity.getBabyBirth());
        genderTv.setText(entity.getBabySex());
    }

    @Override
    public void exitLogin(boolean isExit) {
        super.exitLogin(isExit);
//        userName.setText("");
//        babyBirthTv.setText("");
//        genderTv.setText("");
    }
}
