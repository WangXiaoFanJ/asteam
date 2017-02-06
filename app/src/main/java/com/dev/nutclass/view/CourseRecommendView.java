package com.dev.nutclass.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.dev.nutclass.R;

/**
 * Created by Administrator on 2017/1/9.
 */
public class CourseRecommendView extends RelativeLayout {
    private static  final String TAG = "CourseReCommendView";
    private Context mContext;
    private LinearLayout containerLayout;
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
        for(int i = 0;i<2;i++){
            LinearLayout layout = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.view_amusement_park_card,null);
            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            containerLayout.addView(layout,param);
        }
//        containerLayout.addView(layout,param);
    }

}
