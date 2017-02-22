package com.dev.nutclass.view;

import android.content.Context;
import android.content.Intent;
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
    private TextView goodNameTv01;
    private TextView goodNameTv02;
    private TextView kbkMoneyTv01;
    private TextView kbkMoneyTv02;
    private TextView shopMoneyTv01;
    private TextView shopMoneyTv02;
    private ImageView promotionIconIv;
    private ImageView hotIconTv;
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
        goodNameTv01 = (TextView) this.findViewById(R.id.tv_good_name_01);
        goodNameTv02 = (TextView) this.findViewById(R.id.tv_good_name_02);
        kbkMoneyTv01 = (TextView) this.findViewById(R.id.tv_kbk_money);
        kbkMoneyTv02 = (TextView) this.findViewById(R.id.tv_kbk_money_02);
        shopMoneyTv01 = (TextView) this.findViewById(R.id.tv_shop_money);
        shopMoneyTv02 = (TextView) this.findViewById(R.id.tv_shop_money_02);
        headViewLayout = (LinearLayout) this.findViewById(R.id.ll_root_view);

        headImageView = (ImageView) this.findViewById(R.id.iv_school_image);
     schoolNameTv = (TextView) this.findViewById(R.id.tv_goods_name);
        shopCircleNameTv = (TextView) this.findViewById(R.id.tv_shop_circle);
        cateNameTv = (TextView) this.findViewById(R.id.tv_cate_name);
        gpsCnTv = (TextView) this.findViewById(R.id.tv_gps_cn);
       interestNumTv = (TextView) this.findViewById(R.id.tv_interest_num);

        promotionIconIv = (ImageView) this.findViewById(R.id.iv_icon_promotion);
        hotIconTv = (ImageView) this.findViewById(R.id.iv_icon_hot);
        headViewLayout.setOnClickListener(this);
    }
    public void updateView(SchoolCardEntity entity){
//        goodNameTv01.setText(entity.get);
        List<SchoolCardEntity.SimpleCourseEntity> list = new ArrayList<>();
        list.addAll(entity.getGoodList());
        Log.d("===","list"+list.get(0).getGoodsName());
        goodNameTv01.setText(list.get(0).getGoodsName());
        goodNameTv02.setText(list.get(1).getGoodsName());
        kbkMoneyTv01.setText(list.get(0).getKbkMoney());
        kbkMoneyTv02.setText(list.get(1).getKbkMoney());
        shopMoneyTv01.setText(list.get(0).getShopMoney());
        shopMoneyTv02.setText(list.get(1).getShopMoney());

        GlideUtils.loadImageView(mContext,entity.getSchoolImage(),headImageView);
        schoolNameTv.setText(entity.getSchoolName());
        shopCircleNameTv.setText(entity.getShop_circle_text());
        cateNameTv.setText(entity.getSchoolCateName());
        gpsCnTv.setText(entity.getGpsCn());
        interestNumTv.setText(entity.getInterestNum());

        if(entity.getIsHot().equals("1")){
            hotIconTv.setVisibility(View.VISIBLE);
        }
        if(entity.getIsPromotion().equals("1")){
            promotionIconIv.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_root_view:
            mContext.startActivity(new Intent(mContext, SchoolInfoActivity.class));
                break;
        }
    }
}
