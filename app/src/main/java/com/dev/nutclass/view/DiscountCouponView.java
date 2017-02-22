package com.dev.nutclass.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dev.nutclass.R;
import com.dev.nutclass.entity.DiscountCouponEntity;

/**
 * Created by Administrator on 2017/2/8.
 */
public class DiscountCouponView extends RelativeLayout {
    private Context mContext;
    private TextView couponMoney;
    private TextView couponName;
    private TextView couponInfo;
    private TextView couponTime;
    private TextView couponLimit;
    private LinearLayout couponBgLayout;
    public DiscountCouponView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public DiscountCouponView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.view_discount_coupon,this);
        couponMoney = (TextView) this.findViewById(R.id.tv_coupon_money);
        couponInfo = (TextView) this.findViewById(R.id.tv_coupon_info);
        couponName = (TextView) this.findViewById(R.id.tv_coupon_name);
        couponTime = (TextView) this.findViewById(R.id.tv_coupon_time);
        couponLimit = (TextView) this.findViewById(R.id.tv_coupon_limit);
        couponBgLayout = (LinearLayout) this.findViewById(R.id.ll_coupon_bg);
    }
    public void updateView(DiscountCouponEntity entity){
        int couponType = Integer.parseInt(entity.getCouponType());
        switch (couponType){
            case 1:
                couponBgLayout.setBackgroundColor(getResources().getColor(R.color.color_f75250));
                break;
            case 2:
                couponBgLayout.setBackgroundColor(getResources().getColor(R.color.color_a8a8a8));
                break;
            case 3:
                couponBgLayout.setBackgroundColor(getResources().getColor(R.color.color_a8a8a8));
                break;
        }
        couponMoney.setText(entity.getCouponMoney());
        couponInfo.setText(entity.getCouponInfo());
        couponName.setText(entity.getCouponName());
        couponTime.setText(entity.getCouponTime());
        couponLimit.setText(entity.getCouponLimit());
    }
}
