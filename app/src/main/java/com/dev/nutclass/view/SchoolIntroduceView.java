package com.dev.nutclass.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dev.nutclass.R;
import com.dev.nutclass.entity.SchoolIntroduceEntity;

/**
 * Created by Administrator on 2017/2/22.
 */
public class SchoolIntroduceView extends RelativeLayout {
    private Context mContext;
    private TextView introduceTv;
    public SchoolIntroduceView(Context context) {
        super(context);
        mContext=context;
        initView();
    }

    public SchoolIntroduceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.view_school_introduce,this);
        introduceTv = (TextView) this.findViewById(R.id.tv_introduce_name);
    }
    public void updateView(SchoolIntroduceEntity entity){
        introduceTv.setText(entity.getBrandIntroductName());
    }
}
