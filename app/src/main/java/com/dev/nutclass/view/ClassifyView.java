package com.dev.nutclass.view;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.dev.nutclass.R;
import com.dev.nutclass.entity.BaseCardEntity;
import com.dev.nutclass.entity.ClassifyEntity;

/**
 * Created by Administrator on 2016/12/30.
 */
public class ClassifyView extends LinearLayout {
    private Context mContext;
    private TabLayout tabLayout;
//    private TextView tv;
    public ClassifyView(Context context) {
        super(context);
        initView(context);
    }

    public ClassifyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        mContext = context;
        LayoutInflater.from(mContext).inflate(R.layout.classify_card_view,this);
        tabLayout = (TabLayout) this.findViewById(R.id.tab_layout);
//        tv = (TextView) this.findViewById(R.id.tv);
    }
    public void updateView(BaseCardEntity entity){
//        tv.setText("说什么是什么");
//        tabLayout.removeAllViews();
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.home_nav_txt_ed));
        tabLayout.setSelectedTabIndicatorHeight(2);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        ClassifyEntity classifyEntity = (ClassifyEntity) entity;
        for(int i =0;i<classifyEntity.getTitleList().size();i++){
            Log.d("===","classifyEntity"+classifyEntity.getTitleList().get(i));
            tabLayout.addTab(tabLayout.newTab().setText(classifyEntity.getTitleList().get(i)));
        }
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
