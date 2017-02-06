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
public class SchoolCardView extends RelativeLayout {
    private static  final String TAG = "SchoolCardView";
    private Context mContext;
    private LinearLayout containerLayout;
    public SchoolCardView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public SchoolCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext =context;
        initView();
    }

    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.view_school_card,this);
        containerLayout = (LinearLayout) this.findViewById(R.id.ll_container);
        LinearLayout layout = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.view_amusement_park_card,null);
        layout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext, SchoolInfoActivity.class));
            }
        });
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        containerLayout.addView(layout,param);
    }
}
