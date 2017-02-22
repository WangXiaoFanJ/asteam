package com.dev.nutclass.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dev.nutclass.R;
import com.dev.nutclass.entity.CourserDetailHeadEntity;

/**
 * Created by Administrator on 2017/1/10.
 */
public class CourseInfoHeadView extends RelativeLayout {
    private static final String TAG = "CourseInfoHeadView";
    private Context mContext;
    private TextView goodsNameTv;
    private TextView promotionInfoTv;
    private TextView kbkMoneyTv;
    private TextView shopMoneyTv;
    private TextView fitAgeTv;
    private TextView interestedNumTv;

    public CourseInfoHeadView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public CourseInfoHeadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();

    }

    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.view_course_info_head, this);
        goodsNameTv = (TextView) this.findViewById(R.id.tv_goods_name);
        promotionInfoTv = (TextView) this.findViewById(R.id.tv_promotion_info);
        kbkMoneyTv = (TextView) this.findViewById(R.id.tv_kbk_money);
        shopMoneyTv = (TextView) this.findViewById(R.id.tv_shop_money);
        fitAgeTv = (TextView) this.findViewById(R.id.tv_fit_age);
        interestedNumTv = (TextView) this.findViewById(R.id.tv_interest_num);
    }
    public void updateView(CourserDetailHeadEntity entity){
        goodsNameTv.setText(entity.getGoodsName());
        promotionInfoTv.setText(entity.getPromotionInfo());
        kbkMoneyTv.setText(entity.getKbkMoney());
        shopMoneyTv.setText(entity.getShopMoney());
        fitAgeTv.setText(entity.getFitAge());
        interestedNumTv.setText(entity.getInterestedNum());
    }
}
