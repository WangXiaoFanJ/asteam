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
import com.dev.nutclass.constants.Const;
import com.dev.nutclass.entity.UserOrdeCardEntity;

/**
 * Created by Administrator on 2017/2/8.
 */
public class UserOrderBackMoneyView extends RelativeLayout implements View.OnClickListener {
    private Context mContext;
    private LinearLayout rightLayout;
    private TextView backMoneyStrTv;
    private LinearLayout cardViewLayout;

    private UserOrderCardView cardView;
    private LinearLayout containLayout;
    private TextView goodsType;
    private String orderId;
    public UserOrderBackMoneyView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public UserOrderBackMoneyView(Context context, AttributeSet attrs) {
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


        rightLayout = (LinearLayout) this.findViewById(R.id.ll_right);
        backMoneyStrTv = (TextView) this.findViewById(R.id.tv_back_money_str);
        cardViewLayout = (LinearLayout) this.findViewById(R.id.ll_card_view);
        rightLayout.setVisibility(View.GONE);
        backMoneyStrTv.setVisibility(View.VISIBLE);
        cardViewLayout.setOnClickListener(this);
    }

    public void updateView(UserOrdeCardEntity entity){
        goodsType.setText(entity.getGoodsType());
        cardView.updataView(entity);
        orderId = entity.getOrderId();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_card_view:
                mContext.startActivity(new Intent(mContext, OrderInfoActivity.class)
                        .putExtra(Const.ORDER_ID,orderId));
                break;
        }
    }
}
