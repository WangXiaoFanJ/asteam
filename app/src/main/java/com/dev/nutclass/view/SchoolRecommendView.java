package com.dev.nutclass.view;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.dev.nutclass.R;
import com.dev.nutclass.activity.SchoolInfoActivity;

/**
 * Created by Administrator on 2017/1/9.
 */
public class SchoolRecommendView extends RelativeLayout {
    private static final String TAG = "SchoolRecommendView";
    private Context mContext;
    private LinearLayout containerLayout;
    public SchoolRecommendView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public SchoolRecommendView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.view_school_recommend_view,this);
        containerLayout = (LinearLayout) this.findViewById(R.id.ll_container);

        for(int i = 0;i<2;i++){
            LinearLayout layout = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.view_school_card,null);
            LinearLayout container = (LinearLayout) layout.findViewById(R.id.ll_container);
            LinearLayout layout02 = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.view_amusement_park_card,null);
            layout02.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mContext.startActivity(new Intent(mContext, SchoolInfoActivity.class));
                }
            });
            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            container.addView(layout02,param);
            containerLayout.addView(layout,param);
        }
    }
}
