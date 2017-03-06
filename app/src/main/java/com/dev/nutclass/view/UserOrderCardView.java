package com.dev.nutclass.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dev.nutclass.R;
import com.dev.nutclass.entity.BaseCardEntity;
import com.dev.nutclass.entity.UserOrdeCardEntity;
import com.dev.nutclass.utils.GlideUtils;

/**
 * Created by Administrator on 2017/2/17.
 */
public class UserOrderCardView extends RelativeLayout {
    private  Context mContext;
    private TextView goodsNameTv;
    private TextView schoolNameTv;
    private TextView kbkMoney;
    private TextView goodsAttrTv;
    private ImageView goodsImageIv;
    private ImageView iconIv;
    public UserOrderCardView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public UserOrderCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.view_card_user_order,this);
        goodsNameTv = (TextView) this.findViewById(R.id.tv_goods_name);
        schoolNameTv = (TextView) this.findViewById(R.id.tv_school_name);
        kbkMoney = (TextView) this.findViewById(R.id.tv_kbk_money);
        goodsAttrTv = (TextView) this.findViewById(R.id.tv_goods_attr);

        goodsImageIv = (ImageView) this.findViewById(R.id.iv_goods_image);
        iconIv = (ImageView) this.findViewById(R.id.iv_icon_left_top);
    }
    public void updataView(UserOrdeCardEntity entity){
        goodsNameTv.setText(entity.getGoodsName());
        schoolNameTv.setText("校区："+entity.getSchoolName());
        kbkMoney.setText("￥"+entity.getKbkMoney());
        goodsAttrTv.setText(entity.getGoodsAttr());
        GlideUtils.loadImageView(mContext,entity.getGoodsImage(),goodsImageIv);
        GlideUtils.loadImageView(mContext,entity.getIconIv(),iconIv);
    }
    public void updataView(UserOrdeCardEntity entity,int type){
        goodsNameTv.setText(entity.getGoodsName());
        schoolNameTv.setText("校区："+entity.getSchoolName());
        kbkMoney.setVisibility(View.GONE);
        goodsAttrTv.setText("预约时间:"+entity.getAppointmentTime());
        GlideUtils.loadImageView(mContext,entity.getGoodsImage(),goodsImageIv);
        GlideUtils.loadImageView(mContext,entity.getIconIv(),iconIv);
    }
}
