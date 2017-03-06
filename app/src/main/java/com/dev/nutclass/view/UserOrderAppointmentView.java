package com.dev.nutclass.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dev.nutclass.R;
import com.dev.nutclass.entity.UserOrdeCardEntity;

/**
 * Created by Administrator on 2017/3/4.
 */
public class UserOrderAppointmentView extends RelativeLayout {
    private Context mContext;
    private TextView right01Tv;
    private TextView right02Tv;
    private TextView goodsType;
    private UserOrderCardView cardView;
    private LinearLayout containLayout;
    public UserOrderAppointmentView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public UserOrderAppointmentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.view_user_order,this);
        containLayout = (LinearLayout) this.findViewById(R.id.ll_container);
        cardView = new UserOrderCardView(mContext);
        containLayout.addView(cardView);
        goodsType = (TextView) this.findViewById(R.id.tv_goods_type);
        right01Tv = (TextView) this.findViewById(R.id.tv_right_01);
        right02Tv = (TextView) this.findViewById(R.id.tv_right_02);
        right01Tv.setText("删除订单");
        right02Tv.setVisibility(View.GONE);
    }
    public void updateView(UserOrdeCardEntity entity){
        goodsType.setText(entity.getGoodsType());
        //预约
        cardView.updataView(entity,1);
    }
}
