package com.dev.nutclass.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.dev.nutclass.R;

/**
 * Created by Administrator on 2017/1/10.
 */
public class CourseInfoModule001View extends RelativeLayout {
    private static final String TAG = "CourseInfo001View";
    private Context mContext;
    public CourseInfoModule001View(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext= context;
    }

    public CourseInfoModule001View(Context context) {
        super(context);
        mContext=context;
        initView();

    }

    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.view_course_info_module_001,this);
    }
}
