package com.dev.nutclass.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.dev.nutclass.R;

/**
 * Created by Administrator on 2017/2/20.
 */
public class SchoolCommentView extends RelativeLayout {
    private Context mContext;
    public SchoolCommentView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public SchoolCommentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }
    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.view_course_comment,this);
    }
}
