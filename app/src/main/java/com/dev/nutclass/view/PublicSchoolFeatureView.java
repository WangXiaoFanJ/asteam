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
import com.dev.nutclass.entity.ServiceFeatureEntity;
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
    private LinearLayout containerlayout;
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
//        flowlayout = (FlowLayout) this.findViewById(R.id.flow_layout);
        containerlayout = (LinearLayout) this.findViewById(R.id.ll_container);
    }

    public void updateView(ServiceFeatureEntity entity){
        containerlayout.removeAllViews();
        if(entity.getDataList()!=null&&entity.getDataList().size()>0){
            LinearLayout rowLayout=null;
            LinearLayout.LayoutParams rowParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
        for(int i = 0;i<entity.getDataList().size();i++){
            if(i%3==0){
                rowLayout  =new LinearLayout(mContext);
                rowLayout.setOrientation(LinearLayout.HORIZONTAL);
            }
            int ranHeight = DensityUtil.dip2px(mContext, 33);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(DensityUtil.getDisplayWidth(mContext)/3, ranHeight);
            View view = LayoutInflater.from(mContext).inflate(R.layout.view_course_num_item,null);
//            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,ranHeight);
//            params.setMargins(getResources().getDimensionPixelSize(R.dimen.common_26), 0,
//                    getResources().getDimensionPixelSize(R.dimen.common_20), 0);
            TextView tv = (TextView) view.findViewById(R.id.tv_course_num);
            tv.setTextColor(getResources().getColor(R.color.color_333333));
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);
            tv.setText(entity.getDataList().get(i).getServiceInfo());
            rowLayout.addView(view,params);
            if(i%3==2||i==entity.getDataList().size()-1){
                containerlayout.addView(rowLayout,rowParams);
            }

        }
    }
    }
}
