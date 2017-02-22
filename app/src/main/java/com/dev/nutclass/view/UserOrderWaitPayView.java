package com.dev.nutclass.view;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dev.nutclass.R;
import com.dev.nutclass.activity.WaitPayOrderInfoActivity;
import com.dev.nutclass.entity.UserOrdeCardEntity;
import com.dev.nutclass.utils.GlideUtils;

/**
 * Created by Administrator on 2017/2/7.
 */
public class UserOrderWaitPayView extends RelativeLayout implements View.OnClickListener {
    private Context mContext;
    private TextView showMoreTv;
    private TextView payTv;
    private LinearLayout cardViewLayout;
    private UserOrderCardView cardView;
    private LinearLayout containLayout;
    private TextView goodsType;
    public UserOrderWaitPayView(Context context) {
        super(context);
        mContext = context;
        initView();
    }


    public UserOrderWaitPayView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.view_user_order,this);
        containLayout = (LinearLayout) this.findViewById(R.id.ll_container);
        cardView = new UserOrderCardView(mContext);
        containLayout.addView(cardView);

        showMoreTv = (TextView) this.findViewById(R.id.tv_right_01);
        payTv = (TextView) this.findViewById(R.id.tv_right_02);
        goodsType = (TextView) this.findViewById(R.id.tv_goods_type);
        cardViewLayout = (LinearLayout) this.findViewById(R.id.ll_card_view);
        showMoreTv.setText("更多");
        showMoreTv.setTextColor(getResources().getColor(R.color.color_333333));
        showMoreTv.setBackgroundResource(R.drawable.shape_black_btn_radius);
        payTv.setText("立即支付");
        showMoreTv.setOnClickListener(this);
        payTv.setOnClickListener(this);
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
            mContext.startActivity(new Intent(mContext, WaitPayOrderInfoActivity.class));
                break;
        }
    }
}
