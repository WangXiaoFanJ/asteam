package com.dev.nutclass.view;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dev.nutclass.R;
import com.dev.nutclass.activity.SchoolInfoActivity;
import com.dev.nutclass.constants.Const;
import com.dev.nutclass.entity.SchoolCardEntity;
import com.dev.nutclass.network.ImageUtils;
import com.dev.nutclass.utils.GlideUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/1/9.
 */
public class SchoolCardView extends RelativeLayout implements View.OnClickListener {
    private static  final String TAG = "SchoolCardView";
    private Context mContext;
    private LinearLayout headViewLayout;
    private ImageView headImageView;
    private TextView schoolNameTv;
    private TextView shopCircleNameTv;
    private TextView cateNameTv;
    private TextView gpsCnTv;
    private TextView interestNumTv;
    private ImageView promotionIconIv;
    private ImageView hotIconTv;
    private LinearLayout containerLayout;
    private String schoolId;
    private ImageView iconMoneyIv;
    public SchoolCardView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public SchoolCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext =context;
        initView();
    }

    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.view_school_card,this);
        headViewLayout = (LinearLayout) this.findViewById(R.id.ll_root_view);
        headImageView = (ImageView) this.findViewById(R.id.iv_school_image);
     schoolNameTv = (TextView) this.findViewById(R.id.tv_goods_name);
        shopCircleNameTv = (TextView) this.findViewById(R.id.tv_shop_circle);
        cateNameTv = (TextView) this.findViewById(R.id.tv_cate_name);
        gpsCnTv = (TextView) this.findViewById(R.id.tv_gps_cn);
       interestNumTv = (TextView) this.findViewById(R.id.tv_interest_num);
        iconMoneyIv = (ImageView) this.findViewById(R.id.iv_icon_money);
        promotionIconIv = (ImageView) this.findViewById(R.id.iv_icon_promotion);
        hotIconTv = (ImageView) this.findViewById(R.id.iv_icon_hot);
        containerLayout = (LinearLayout) this.findViewById(R.id.ll_container);
        headViewLayout.setOnClickListener(this);
    }
    public void updateView(SchoolCardEntity entity){
//        goodNameTv01.setText(entity.get);
        schoolId = entity.getSchoolId();
        GlideUtils.loadImageView(mContext,entity.getSchoolImage(),headImageView);
        schoolNameTv.setText(entity.getSchoolName());
        shopCircleNameTv.setText(entity.getShop_circle_text());
        cateNameTv.setText(entity.getSchoolCateName());
        gpsCnTv.setText(entity.getGpsCn());
        interestNumTv.setText(entity.getInterestNum());
        GlideUtils.loadImageView(mContext,entity.getIconMoney(),iconMoneyIv);
        if(!TextUtils.isEmpty(entity.getIsHot())){
            hotIconTv.setVisibility(View.VISIBLE);
            GlideUtils.loadImageView(mContext,entity.getIsHot(),hotIconTv);
        }
        if(!TextUtils.isEmpty(entity.getIsPromotion())){
            promotionIconIv.setVisibility(View.VISIBLE);
            GlideUtils.loadImageView(mContext,entity.getIsPromotion(),promotionIconIv);
        }
        if(entity.getGoodList()!=null&&entity.getGoodList().size()>0){
            for(int i=0;i<entity.getGoodList().size();i++){
                SchoolCardChildView schoolCardChildView = new SchoolCardChildView(mContext);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                schoolCardChildView.updateView(entity.getGoodList().get(i));
                containerLayout.addView(schoolCardChildView,params);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_root_view:
                Intent intent = new Intent(mContext,SchoolInfoActivity.class);
                intent.putExtra(Const.SCHOOL_ID,schoolId);
                mContext.startActivity(intent);
                break;
        }
    }
}
