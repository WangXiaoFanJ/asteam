package com.dev.nutclass.view;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.dev.nutclass.R;
import com.dev.nutclass.activity.PublicListActivity;
import com.dev.nutclass.constants.Const;

/**
 * Created by Administrator on 2017/1/12.
 */
public class JDItemCardView extends RelativeLayout{
    private Context mContext;
    public JDItemCardView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public JDItemCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.view_jd_card,this);
        View view = this.findViewById(R.id.view_jd);
        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, PublicListActivity.class);
                intent.putExtra(Const.KEY_TYPE,PublicListActivity.TYPE_FROM_JD);
                mContext.startActivity(intent);
            }
        });
    }
}
