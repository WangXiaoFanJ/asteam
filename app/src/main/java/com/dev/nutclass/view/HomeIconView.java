package com.dev.nutclass.view;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.dev.nutclass.R;
import com.dev.nutclass.activity.CourseListActivity;
import com.dev.nutclass.entity.IconEntity;
import com.dev.nutclass.utils.DensityUtil;
import com.dev.nutclass.utils.GlideUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/1/4.
 */
public class HomeIconView extends LinearLayout {
    private Context mContext;
    private LinearLayout container01;
    private LinearLayout container02;
    public HomeIconView(Context context) {
        super(context);
        initView(context);
    }

    public HomeIconView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        this.mContext = context;
        LayoutInflater.from(mContext).inflate(R.layout.home_icon_view,this);
        container01 = (LinearLayout) this.findViewById(R.id.container01);
        container02 = (LinearLayout) this.findViewById(R.id.container02);
    }
    public void updateView(IconEntity iconEntity){
        List<String> iconLists = new ArrayList<>();
        iconLists.addAll(iconEntity.getIconList());
        List<String> iconLists01 = new ArrayList<>();
        List<String> iconLists02 = new ArrayList<>();
        for(int i =0;i<iconLists.size()/2;i++){
            iconLists01.add(iconLists.get(i));
        }
        for(int i = iconLists.size()/2;i<iconLists.size();i++){
            iconLists02.add(iconLists.get(i));
        }
        createLayout(container01,iconLists01);
        createLayout(container02,iconLists02);

    }
    public void createLayout(LinearLayout container,List<String> iconLists){
        LinearLayout rowLayout=null;
        LinearLayout.LayoutParams rowParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT);
        int size= DensityUtil.getDisplayWidth(mContext)/5;
        for(int i = 0;i<iconLists.size();i++){
            LinearLayout.LayoutParams cellParams = new LinearLayout.LayoutParams(size,LayoutParams.WRAP_CONTENT);
            if(i%iconLists.size()==0){
                rowLayout = new LinearLayout(mContext);
                rowLayout.setOrientation(HORIZONTAL);
            }
            View itemView = LayoutInflater.from(mContext).inflate(R.layout.home_icon_view_item,null);
            ImageView imageView = (ImageView) itemView.findViewById(R.id.iv_icon_item);
            itemView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, CourseListActivity.class);
                    mContext.startActivity(intent);
                }
            });
            GlideUtils.loadImageView(mContext,iconLists.get(i),imageView);
//            Picasso.with(mContext).load(iconLists.get(i)).into(imageView);
//            Glide.with(mContext).load(iconLists.get(i)).into(imageView);
//            GlideUtils.loadImageViewLoding(mContext,iconLists.get(i),imageView,R.mipmap.ic_launcher,R.mipmap.ic_launcher);
            rowLayout.addView(itemView,cellParams);
            if(i%iconLists.size()==iconLists.size()-1){
                container.addView(rowLayout, rowParams);
            }
        }
    }
}
