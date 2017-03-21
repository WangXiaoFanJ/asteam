package com.dev.nutclass.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dev.nutclass.R;
import com.dev.nutclass.entity.CourseListCardEntity;
import com.dev.nutclass.utils.GlideUtils;

/**
 * Created by Administrator on 2017/1/9.
 */
public class AmusementParkCardView extends RelativeLayout {
   private static final String TAG = "AmusementParkCardView";
    private Context mContext;
    private TextView goodsNameTv,shopCircleTv,cateNameTv,gpsCnTv,kbkMoneyTv
            ,shopMoneyTv;
    private ImageView iconIv,promotionIv,hotIv;
    private CheckBox checkBoxIv;
    public AmusementParkCardView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public AmusementParkCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.view_amusement_park_card,this);
        goodsNameTv = (TextView) this.findViewById(R.id.tv_goods_name);
        kbkMoneyTv = (TextView) this.findViewById(R.id.tv_kbk_money);
        shopCircleTv = (TextView) this.findViewById(R.id.tv_shop_circle);
        cateNameTv = (TextView) this.findViewById(R.id.tv_cate_name);
        shopMoneyTv = (TextView) this.findViewById(R.id.tv_shop_money);
        checkBoxIv = (CheckBox) this.findViewById(R.id.iv_check_box);
        iconIv = (ImageView) this.findViewById(R.id.iv_school_image);
        hotIv = (ImageView) this.findViewById(R.id.iv_icon_hot);
        promotionIv = (ImageView) this.findViewById(R.id.iv_icon_promotion);
    }
    public void updateView(CourseListCardEntity entity,boolean isEdit){
        if(isEdit){
            checkBoxIv.setVisibility(View.VISIBLE);
        }else{
            checkBoxIv.setVisibility(View.GONE);
        }
        goodsNameTv.setText(entity.getGoodsName());
        kbkMoneyTv.setText(entity.getKbkMoney());
        shopCircleTv.setText(entity.getShopCircleName());
        cateNameTv.setText(entity.getCateName());
        shopMoneyTv.setText(entity.getShopMoney());
        GlideUtils.loadImageView(mContext,entity.getGoodsImage(),iconIv);
        if(entity.getIsHot().equals("1")){
            hotIv.setVisibility(View.VISIBLE);
        }
        if(entity.getIsPromotion().equals("1")){
            hotIv.setVisibility(View.VISIBLE);
        }
    }
}
