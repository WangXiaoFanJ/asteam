package com.dev.nutclass.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dev.nutclass.R;
import com.dev.nutclass.utils.GlideUtils;

/**
 * Created by Administrator on 2016/12/29.
 */
public class BigImgView extends LinearLayout {
    private Context mContext;
    private ImageView iv;
    public BigImgView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public BigImgView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    private void initView() {
      LayoutInflater.from(mContext).inflate(R.layout.view_pager_img,this);
        iv = (ImageView) this.findViewById(R.id.iv_big_img);
    }
    public void updateView(String string){
//            tv.setText(string);
//        Glide.with(mContext).load(string).into(iv);
        GlideUtils.clearMemory(mContext);
        GlideUtils.loadImageView(mContext,string,iv);
    }

}
