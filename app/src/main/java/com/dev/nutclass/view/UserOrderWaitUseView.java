package com.dev.nutclass.view;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dev.nutclass.R;
import com.dev.nutclass.activity.OrderInfoActivity;
import com.dev.nutclass.entity.UserOrdeCardEntity;

/**
 * Created by Administrator on 2017/2/8.
 */
public class UserOrderWaitUseView extends RelativeLayout implements View.OnClickListener {
    private Context mContext;
    private TextView applyBackMoneyTv;
    private TextView verifyTv;
    private LinearLayout cardViewLayout;

    private UserOrderCardView cardView;
    private LinearLayout containLayout;
    private TextView goodsType;
    public UserOrderWaitUseView(Context context) {
        super(context);
        mContext = context;
        initView();
    }



    public UserOrderWaitUseView(Context context, AttributeSet attrs) {
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


        applyBackMoneyTv = (TextView) this.findViewById(R.id.tv_right_01);
        verifyTv = (TextView) this.findViewById(R.id.tv_right_02);
        cardViewLayout = (LinearLayout) this.findViewById(R.id.ll_card_view);
        applyBackMoneyTv.setText("申请退款");
        applyBackMoneyTv.setTextColor(getResources().getColor(R.color.color_a8a8a8));
        applyBackMoneyTv.setBackgroundResource(R.drawable.shape_gray_btn_radius);
        verifyTv.setText("验证");
        cardViewLayout.setOnClickListener(this);
    }
    public void updateView(UserOrdeCardEntity entity){
        goodsType.setText(entity.getGoodsType());
        cardView.updataView(entity);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_card_view:
            mContext.startActivity(new Intent(mContext, OrderInfoActivity.class));
                break;
        }
    }
}
