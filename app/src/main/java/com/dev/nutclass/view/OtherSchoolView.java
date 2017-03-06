package com.dev.nutclass.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dev.nutclass.R;
import com.dev.nutclass.entity.OtherSchoolEntity;

/**
 * Created by Administrator on 2017/2/27.
 */
public class OtherSchoolView extends RelativeLayout {
    private Context mContext;
    private TextView schoolNameTv;
    private TextView schoolAddrTv;
    private TextView distanceTv;
    private ImageView phoneIv;
    private TextView isLatelyTv;
    public OtherSchoolView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public OtherSchoolView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.view_other_school,this);
        schoolNameTv = (TextView) this.findViewById(R.id.tv_school_name);
        schoolAddrTv = (TextView) this.findViewById(R.id.tv_school_addr);
        distanceTv = (TextView) this.findViewById(R.id.tv_distance);
        isLatelyTv = (TextView) this.findViewById(R.id.tv_is_lately);
    }
    public void updateView(OtherSchoolEntity entity){
        schoolNameTv.setText(entity.getSchoolName());
        schoolAddrTv.setText(entity.getSchoolAddr());
        distanceTv.setText(entity.getGpsCn());
        if(!entity.getIsLateLy().equals("")){
            isLatelyTv.setVisibility(View.VISIBLE);
            isLatelyTv.setText(entity.getIsLateLy());
        }
    }
}
