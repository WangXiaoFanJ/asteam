package com.dev.nutclass.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dev.nutclass.R;

/**
 * Created by Administrator on 2017/1/8.
 */
public class SchoolInfoHeadView extends RelativeLayout {
    private static final String TAG = "SchoolInfoHeadView";
    private Context mContext;
    private TextView nameTv;
    public SchoolInfoHeadView(Context context) {
        super(context);
        this.mContext = context;
        initView();
    }

    public SchoolInfoHeadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext =context;
        initView();
    }

    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.view_head_school_view,this);
        nameTv = (TextView) this.findViewById(R.id.tv_school_name);
    }
}
