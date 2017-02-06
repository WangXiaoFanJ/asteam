package com.dev.nutclass.view;

import android.content.Context;
import android.text.Layout;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dev.nutclass.R;
import com.dev.nutclass.utils.DensityUtil;

import java.util.ArrayList;
import java.util.List;

import cn.lankton.flowlayout.FlowLayout;

/**
 * Created by Administrator on 2017/1/9.
 */
public class PublicSchoolFeatureView extends RelativeLayout{
    private static final String TAG = "PlulicSchoolFeatureView";
    private Context mContext;
    private FlowLayout flowlayout;
    private List<String> list;
    public PublicSchoolFeatureView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public PublicSchoolFeatureView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext =context;
        initView();
    }

    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.view_school_feature_public,this);
        flowlayout = (FlowLayout) this.findViewById(R.id.flow_layout);
        list = new ArrayList<>();
        for(int i = 0;i<6;i++){
            list.add("测试数据");
        }


        for(int i = 0;i<list.size();i++){
            int ranHeight = DensityUtil.dip2px(mContext, 33);
            LinearLayout layout = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.view_course_num_item,null);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,ranHeight);
            params.setMargins(getResources().getDimensionPixelSize(R.dimen.common_26), 0,
                    getResources().getDimensionPixelSize(R.dimen.common_20), 0);
            TextView tv = (TextView) layout.findViewById(R.id.tv_course_num);
            tv.setTextColor(getResources().getColor(R.color.color_333333));
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);
            tv.setText(list.get(i));
            flowlayout.addView(layout,params);
        }

    }
}
