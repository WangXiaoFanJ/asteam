package com.dev.nutclass.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dev.nutclass.R;
import com.dev.nutclass.view.MyPopupWindow;
import com.dev.nutclass.view.StarCommentLayout;
import com.foamtrace.photopicker.ImageCaptureManager;
import com.foamtrace.photopicker.PhotoPickerActivity;
import com.foamtrace.photopicker.PhotoPreviewActivity;
import com.foamtrace.photopicker.SelectModel;
import com.foamtrace.photopicker.intent.PhotoPickerIntent;

import org.json.JSONArray;

import java.io.File;
import java.util.ArrayList;

public class WriteCommentActivity extends BaseActivity implements View.OnClickListener {
    private Context mContext;
    private ImageView cameraIv;
    private static final int REQUEST_CAMERA_CODE = 11;
    private static final int REQUEST_PREVIEW_CODE = 22;
    private ArrayList<String> imagePaths = null;
    private StarCommentLayout starLayout01;
    private StarCommentLayout starLayout02;
    private StarCommentLayout starLayout03;
    private TextView likeDegree01;
    private TextView likeDegree02;
    private TextView likeDegree03;
    private MyPopupWindow popWindow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_comment);
        mContext = WriteCommentActivity.this;
        initView();
    }

    private void initView() {
        cameraIv = (ImageView) findViewById(R.id.iv_camera);
        starLayout01 = (StarCommentLayout) findViewById(R.id.star_comment_01);
        starLayout02 = (StarCommentLayout) findViewById(R.id.star_comment_02);
        starLayout03 = (StarCommentLayout) findViewById(R.id.star_comment_03);
        likeDegree01 = (TextView) findViewById(R.id.tv_like_degree_01);
        likeDegree02 = (TextView) findViewById(R.id.tv_like_degree_02);
        likeDegree03 = (TextView) findViewById(R.id.tv_like_degree_03);

        starLayout01.setOnClickChildView(new StarCommentLayout.OnClickChildView() {
            @Override
            public void onClickChild(int i) {
                setLikeDegreeText(i,likeDegree01);
            }
        });
        starLayout02.setOnClickChildView(new StarCommentLayout.OnClickChildView() {
            @Override
            public void onClickChild(int i) {
                setLikeDegreeText(i,likeDegree02);
            }
        });
        starLayout03.setOnClickChildView(new StarCommentLayout.OnClickChildView() {
            @Override
            public void onClickChild(int i) {
                setLikeDegreeText(i,likeDegree03);
            }
        });
        cameraIv.setOnClickListener(this);
    }

    public void setLikeDegreeText(int i,TextView textView){
        if(i==4){
            textView.setVisibility(View.VISIBLE);
            textView.setText("非常好");
        }else if(i==2||i==3){
            textView.setVisibility(View.VISIBLE);
            textView.setText("好");
        }else{
            textView.setVisibility(View.VISIBLE);
            textView.setText("差");
        }
    }
    @Override
    public void onClick(View v) {
        if(v==cameraIv){
            showPopupWidow();
        }
    }
    private void showPopupWidow() {
        View rootView = null;
        rootView = LayoutInflater.from(mContext).inflate(R.layout.view_baby_gender_popup,null);
        TextView manGenderTv = (TextView) rootView.findViewById(R.id.tv_gender_man);
        TextView womanGenderTv = (TextView) rootView.findViewById(R.id.tv_gender_woman);
        TextView cancelTv = (TextView) rootView.findViewById(R.id.tv_cancel);
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
                intent.setSelectModel(SelectModel.MULTI);
                intent.setShowCarema(true);
//                  intent.setImageConfig(null);
                intent.setMaxTotal(9); // 最多选择照片数量，默认为9
                intent.setSelectedPaths(imagePaths); // 已选中的照片地址， 用于回显选中状态
                startActivityForResult(intent, REQUEST_CAMERA_CODE);
                popWindow.dismiss();
            }
        });
        cancelTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popWindow.dismiss();
            }
        });
        popWindow= new MyPopupWindow(mContext,rootView);
        popWindow.setAnimationStyle(R.style.Anim_Menu_Bottombar);
        popWindow.showAtLocation(cameraIv, Gravity.BOTTOM,0,0);
    }

}
