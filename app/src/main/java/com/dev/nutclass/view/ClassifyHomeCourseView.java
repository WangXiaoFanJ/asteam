package com.dev.nutclass.view;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;


import com.dev.nutclass.R;
import com.dev.nutclass.entity.BaseCardEntity;
import com.dev.nutclass.entity.ClassifyHomeCourseEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/30.
 */
public class ClassifyHomeCourseView extends LinearLayout {
    private Context mContext;
    private TabLayout tabLayout;
    private LinearLayout containerLayout;
//    private TextView tv;
    public ClassifyHomeCourseView(Context context) {
        super(context);
        initView(context);
    }

    public ClassifyHomeCourseView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        mContext = context;
        LayoutInflater.from(mContext).inflate(R.layout.classify_card_view,this);
        containerLayout = (LinearLayout) this.findViewById(R.id.ll_container);
        tabLayout = (TabLayout) this.findViewById(R.id.tab_layout);
//        tv = (TextView) this.findViewById(R.id.tv);
    }
    public void updateView(ClassifyHomeCourseEntity entity){
        containerLayout.removeAllViews();
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.home_nav_txt_ed));
        tabLayout.setSelectedTabIndicatorHeight(2);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        for(int i=0;i<entity.getGoodCateLists().size();i++){
            tabLayout.addTab(tabLayout.newTab().setText(entity.getGoodCateLists().get(i).getCateName()));
        }
        for(int i=0;i<entity.getCourseCardLists().size();i++){
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT
            );
            CourseCardView cardView = new CourseCardView(mContext);
            cardView.updateView(entity.getCourseCardLists().get(i));
            containerLayout.addView(cardView,params);
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
