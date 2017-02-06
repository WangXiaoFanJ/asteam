package com.dev.nutclass.view;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.dev.nutclass.R;
import com.dev.nutclass.activity.PublicListActivity;

/**
 * Created by Administrator on 2017/1/9.
 */
public class SchoolInfoCourseListView extends RelativeLayout {
    private static final String TAG = "SchoolInfoCourseListView";
    private Context mContext;
    private RelativeLayout findMoreLayout;
    public SchoolInfoCourseListView(Context context) {
        super(context);
        this.mContext = context;
        initView();
    }

    public SchoolInfoCourseListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView();
    }

    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.view_school_info_course_list,this);
        findMoreLayout = (RelativeLayout) this.findViewById(R.id.rl_find_more);
        findMoreLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext, PublicListActivity.class));
            }
        });
    }
}
