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
import com.dev.nutclass.entity.SchoolDetailToCourseEntity;
import com.dev.nutclass.entity.SchoolToCourseCardEntity;
import com.dev.nutclass.utils.GlideUtils;

/**
 * Created by Administrator on 2017/2/14.
 */
public class SchoolCommonCourseView extends RelativeLayout {
    private static final String TAG = "SchoolCommonCourseView";
    private Context mContext;
    private TextView courseNameTv;
    private TextView kbkMoneyTv;
    private TextView shopMoneyTv;
    private ImageView schoolImageIv;
    private ImageView promotionIconIv;
    private ImageView hotIconTv;
    public SchoolCommonCourseView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public SchoolCommonCourseView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    private void initView() {
       LayoutInflater.from(mContext).inflate(R.layout.view_school_common_course_card,this);
        courseNameTv = (TextView) this.findViewById(R.id.tv_course_name);
        kbkMoneyTv = (TextView) this.findViewById(R.id.tv_kbk_money);
        shopMoneyTv = (TextView) this.findViewById(R.id.tv_shop_money);
        schoolImageIv = (ImageView) this.findViewById(R.id.iv_school_image);
        promotionIconIv = (ImageView) this.findViewById(R.id.iv_icon_promotion);
        hotIconTv = (ImageView) this.findViewById(R.id.iv_icon_hot);
    }
    public void updateView(SchoolToCourseCardEntity entity){
        GlideUtils.loadImageView(mContext,entity.getGoodsImage(),schoolImageIv);
        kbkMoneyTv.setText(entity.getKbkMoney());
        courseNameTv.setText(entity.getGoodsName());

        if(entity.getShopMoney().equals("")){
            shopMoneyTv.setText(entity.getGoodsType());
            shopMoneyTv.setTextColor(getResources().getColor(R.color.color_f75250));
        }
        if(entity.getGoodsType().equals("")){
            shopMoneyTv.setText(entity.getShopMoney());
        }
        if(entity.getIsHot().equals("1")){
            hotIconTv.setVisibility(View.VISIBLE);
        }
        if(entity.getIsPromotion().equals("1")){
            promotionIconIv.setVisibility(View.VISIBLE);
        }
    }

}
