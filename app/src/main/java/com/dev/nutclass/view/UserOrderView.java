package com.dev.nutclass.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.dev.nutclass.R;

/**
 * Created by Administrator on 2017/1/20.
 */
public class UserOrderView extends RelativeLayout {
    private Context mContext;
    public UserOrderView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public UserOrderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.view_user_order,this);

    }
}
