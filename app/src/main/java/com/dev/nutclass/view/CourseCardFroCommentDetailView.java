package com.dev.nutclass.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dev.nutclass.R;
import com.dev.nutclass.entity.CourseCommentListEntity;
import com.dev.nutclass.utils.GlideUtils;

/**
 * Created by Administrator on 2017/3/13.
 */
public class CourseCardFroCommentDetailView extends RelativeLayout {
    private Context mContext;
    private static final String TAG = "CourseCardFroCommentDetail";
    private ImageView goodsImage;
    private TextView kbkMoneyTv,goodsNameTv,shopCircleTv,cateNameTv;
    public CourseCardFroCommentDetailView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public CourseCardFroCommentDetailView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext =context;
        initView();
    }

    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.view_card_course_from_comment,this);
        kbkMoneyTv = (TextView) this.findViewById(R.id.tv_kbk_money);
        goodsNameTv = (TextView) this.findViewById(R.id.tv_goods_name);
        shopCircleTv = (TextView) this.findViewById(R.id.tv_shop_circle);
        cateNameTv = (TextView) this.findViewById(R.id.tv_cate_name);
        goodsImage = (ImageView) this.findViewById(R.id.iv_goods_image);
    }
    public void updateView(CourseCommentListEntity.GoodsInfo entity){
        kbkMoneyTv.setText(entity.getKbkMoney());
        goodsNameTv.setText(entity.getGoodsName());
        shopCircleTv.setText(entity.getShopCircleName());
        cateNameTv.setText(entity.getCatename());
        GlideUtils.loadImageView(mContext,entity.getGoodsImage(),goodsImage);
    }
}
