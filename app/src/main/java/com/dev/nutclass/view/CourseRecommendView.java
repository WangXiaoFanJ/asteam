package com.dev.nutclass.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.dev.nutclass.R;
import com.dev.nutclass.entity.CourseRecommendForUEntity;
import com.dev.nutclass.utils.GlideUtils;

/**
 * Created by Administrator on 2017/1/9.
 */
public class CourseRecommendView extends RelativeLayout {
    private static  final String TAG = "CourseReCommendView";
    private Context mContext;
    private LinearLayout containerLayout;
    private ImageView recommendBg;
    public CourseRecommendView(Context context) {
        super(context);
        mContext = context;
        initView();
    }


    public CourseRecommendView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }
    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.view_course_recommend_view,this);
        containerLayout = (LinearLayout) this.findViewById(R.id.ll_container);
        recommendBg = (ImageView) this.findViewById(R.id.iv_recommend_bg);
//        for(int i = 0;i<2;i++){
//            LinearLayout layout = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.view_amusement_park_card,null);
//            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
//            containerLayout.addView(layout,param);
//        }
//        containerLayout.addView(layout,param);
    }
    public void updateView(CourseRecommendForUEntity entity){
        GlideUtils.loadImageView(mContext,entity.getBannerImg(),recommendBg);
        for(int i = 0;i<entity.getList().size();i++){
            CourseCardView cardView = new CourseCardView(mContext);
            cardView.updateView(entity.getList().get(i));
            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            containerLayout.addView(cardView,param);
        }
    }

}
