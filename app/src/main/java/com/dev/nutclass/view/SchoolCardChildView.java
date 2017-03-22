package com.dev.nutclass.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dev.nutclass.R;
import com.dev.nutclass.entity.SchoolCardEntity;
import com.dev.nutclass.entity.SimpleEntity;
import com.dev.nutclass.utils.GlideUtils;

/**
 * Created by Administrator on 2017/3/6.
 */
public class SchoolCardChildView extends RelativeLayout {
    private Context mContext;
    private TextView goodNameTv01,kbkMoneyTv01,shopMoneyTv01;
    private ImageView promotionIv,hotIv,iconMoney;
    public SchoolCardChildView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public SchoolCardChildView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.view_child_school_card,this);
        goodNameTv01 = (TextView) this.findViewById(R.id.tv_goods_name);
        kbkMoneyTv01 = (TextView) this.findViewById(R.id.tv_kbk_money);
        shopMoneyTv01 = (TextView) this.findViewById(R.id.tv_shop_money);
        promotionIv = (ImageView) this.findViewById(R.id.iv_icon_promotion);
        hotIv = (ImageView) this.findViewById(R.id.iv_icon_hot);
        iconMoney = (ImageView) this.findViewById(R.id.icon_money);
    }
    public void updateView(SchoolCardEntity.SimpleCourseEntity entity){
        goodNameTv01.setText(entity.getGoodsName());
        kbkMoneyTv01.setText(entity.getKbkMoney());
        shopMoneyTv01.setText(entity.getShopMoney());
        if(!TextUtils.isEmpty(entity.getPromotionIcon())){
            promotionIv.setVisibility(VISIBLE);
            GlideUtils.loadImageView(mContext,entity.getPromotionIcon(),promotionIv);
        }
        if(!TextUtils.isEmpty(entity.getHotIcon())){
            hotIv.setVisibility(VISIBLE);
            GlideUtils.loadImageView(mContext,entity.getHotIcon(),hotIv);
        }
        GlideUtils.loadImageView(mContext,entity.getIconMoney(),iconMoney);
    }
}
