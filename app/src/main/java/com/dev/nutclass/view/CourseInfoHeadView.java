package com.dev.nutclass.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.dev.nutclass.R;

/**
 * Created by Administrator on 2017/1/10.
 */
public class CourseInfoHeadView extends RelativeLayout{
    private static final String TAG = "CourseInfoHeadView";
    private Context mContext;
    public CourseInfoHeadView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public CourseInfoHeadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();

    }
    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.view_course_info_head,this);
    }
}
