package com.dev.nutclass.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.dev.nutclass.R;

/**
 * Created by Administrator on 2017/1/9.
 */
public class AmusementParkCardView extends RelativeLayout {
   private static final String TAG = "AmusementParkCardView";
    private Context mContext;
    public AmusementParkCardView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public AmusementParkCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.view_amusement_park_card,this);
    }
}
