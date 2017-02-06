package com.dev.nutclass.view;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.dev.nutclass.R;
import com.dev.nutclass.activity.PublicListActivity;
import com.dev.nutclass.constants.Const;

/**
 * Created by Administrator on 2017/1/9.
 */
public class NearbyAmuseParkView extends RelativeLayout {
    private static final String TAG = "NearbyAmuseParkView";
    private Context mContext;
    private LinearLayout containerLayout;
    private RelativeLayout findMoreLayout;
    public NearbyAmuseParkView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public NearbyAmuseParkView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.view_nearby_amuse_park,this);
        containerLayout = (LinearLayout) this.findViewById(R.id.ll_container);
        findMoreLayout = (RelativeLayout) this.findViewById(R.id.rl_find_more);
        findMoreLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, PublicListActivity.class);
                intent.putExtra(Const.KEY_TYPE,PublicListActivity.TYPE_FROM_AMUSE_PARK);
                mContext.startActivity(intent);
            }
        });
        for(int i = 0;i<3;i++){
            LinearLayout layout = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.view_amusement_park_card,null);
            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            containerLayout.addView(layout,param);
        }
    }
}
