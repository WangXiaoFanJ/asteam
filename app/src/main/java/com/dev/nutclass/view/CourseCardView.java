package com.dev.nutclass.view;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dev.nutclass.R;
import com.dev.nutclass.activity.CourseInfoActivity;
import com.dev.nutclass.constants.Const;
import com.dev.nutclass.entity.CourseListCardEntity;
import com.dev.nutclass.utils.GlideUtils;
import com.dev.nutclass.utils.LogUtil;
import com.dev.nutclass.utils.TextUtil;

/**
 * Created by Administrator on 2017/1/9.
 */
public class CourseCardView extends RelativeLayout implements View.OnClickListener {
    private static final String TAG = "CourseCardView";
    private Context mContext;
    private TextView schoolNameTv;
    private TextView goodsNameTv;
    private ImageView schoolImageTv;
    private ImageView isPromtionIv;
    private ImageView isHotIv;
    private TextView shopCircleNameTv;
    private TextView cateNameTv;
    private TextView gpsCnTv;
    private TextView kbkMoneyTv;
    private TextView shopMoneyTv;
    //    private ImageView
    private LinearLayout cardViewLayout;
    private String goodsId;
    private LinearLayout giftLayout;
    private LinearLayout giftLayout02;
    private ImageView giftIv01,giftIcon01,giftIv02;
    private TextView  giftInfoTv01,giftInfoTv02;
    public CourseCardView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public CourseCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.view_course_card, this);
        schoolNameTv = (TextView) this.findViewById(R.id.tv_school_name);
        goodsNameTv = (TextView) this.findViewById(R.id.tv_goods_name);
        schoolImageTv = (ImageView) this.findViewById(R.id.iv_school_image);
        isPromtionIv = (ImageView) this.findViewById(R.id.iv_icon_promotion);
        isHotIv = (ImageView) this.findViewById(R.id.iv_icon_hot);
        shopCircleNameTv = (TextView) this.findViewById(R.id.tv_shop_circle);
        cateNameTv = (TextView) this.findViewById(R.id.tv_cate_name);
        gpsCnTv = (TextView) this.findViewById(R.id.tv_gps_cn);
        kbkMoneyTv = (TextView) this.findViewById(R.id.tv_kbk_money);
        shopMoneyTv = (TextView) this.findViewById(R.id.tv_shop_money);
        cardViewLayout = (LinearLayout) this.findViewById(R.id.ll_root_view);
        giftLayout = (LinearLayout) this.findViewById(R.id.ll_gift_01);
        giftLayout02 = (LinearLayout) this.findViewById(R.id.ll_gift_02);
        giftIcon01 = (ImageView) this.findViewById(R.id.iv_gift_img_icon);
        giftIv01 = (ImageView) this.findViewById(R.id.iv_gift_img);
        giftIv02 = (ImageView) this.findViewById(R.id.iv_gift_img_02);
        giftInfoTv01 = (TextView) this.findViewById(R.id.tv_gift_info);
        giftInfoTv02 = (TextView) this.findViewById(R.id.tv_gift_info_02);
        cardViewLayout.setOnClickListener(this);
    }

    public void updateView(CourseListCardEntity entity) {
        goodsId = entity.getGoodsId();
        schoolNameTv.setText(entity.getSchoolName());
        goodsNameTv.setText(entity.getGoodsName());
        GlideUtils.loadImageView(mContext,entity.getGoodsImage(),schoolImageTv);
        if(entity.getIsPromotion().equals("1")){
            isPromtionIv.setVisibility(View.VISIBLE);
        }
        if(entity.getIsHot().equals("1")){
         isHotIv.setVisibility(View.VISIBLE);
        }
        shopCircleNameTv.setText(entity.getShopCircleName());
        cateNameTv.setText(entity.getCateName());
        gpsCnTv.setText(entity.getGpsCn());
        kbkMoneyTv.setText(entity.getKbkMoney());
        shopMoneyTv.setText(entity.getShopMoney());
        if(!TextUtils.isEmpty(entity.getGiftImgPointer())){
            LogUtil.d(TAG,"========");
            giftLayout.setVisibility(VISIBLE);
            GlideUtils.loadImageView(mContext,entity.getGiftImgPointer(),giftIcon01);
            GlideUtils.loadImageView(mContext,entity.getGiftData().getImg(),giftIv01);
            giftInfoTv01.setText(entity.getGiftData().getInfo());
        }
        LogUtil.d(TAG,"giftPgiftImg:"+entity.getGiftTImg());
        if(!TextUtils.isEmpty(entity.getGiftTImg())){
            giftLayout02.setVisibility(VISIBLE);
            GlideUtils.loadImageView(mContext,entity.getGiftTImg(),giftIv02);
            giftInfoTv02.setText(entity.getGiftTInfo());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_root_view:
//                mContext.startActivity(new Intent(mContext,CourseInfoActivity.class));
                Intent intent = new Intent(mContext,CourseInfoActivity.class);
                intent.putExtra(Const.GOODS_ID,goodsId);
                mContext.startActivity(intent);
                break;
        }
    }
}
