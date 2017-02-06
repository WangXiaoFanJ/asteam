package com.dev.nutclass.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.dev.nutclass.R;

/**
 * Created by Administrator on 2017/1/9.
 */
public class SchoolInfoModule001View extends RelativeLayout {
    private static final String TAG = "SchoolInfo001View";
    private Context mContext;
    public SchoolInfoModule001View(Context context) {
        super(context);
        this.mContext = context;
        initView();
    }

    public SchoolInfoModule001View(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView();
    }

    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.view_school_info_001,this);
    }
}
