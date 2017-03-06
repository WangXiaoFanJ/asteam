package com.dev.nutclass.view;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dev.nutclass.R;
import com.dev.nutclass.activity.CourseListActivity;
import com.dev.nutclass.entity.HomeIconEntity;
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
    private LinearLayout containerLayout;
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
        containerLayout = (LinearLayout) this.findViewById(R.id.ll_container);
    }
    public void updateView(HomeIconEntity entity){
        containerLayout.removeAllViews();
        if(entity.getHomeIconList()!=null&&entity.getHomeIconList().size()>0){
            LinearLayout rowLayout = null;
            LinearLayout.LayoutParams rowParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            for(int i=0;i<entity.getHomeIconList().size();i++){
                if(i%5==0){
                    rowLayout = new LinearLayout(mContext);
                    rowLayout.setOrientation(HORIZONTAL);
                }
//                int ranHeight = DensityUtil.dip2px(mContext, 60);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(DensityUtil.getDisplayWidth(mContext)/5,
                        LayoutParams.WRAP_CONTENT);

                View itemView = LayoutInflater.from(mContext).inflate(R.layout.home_icon_view_item,null);
                ImageView imageView = (ImageView) itemView.findViewById(R.id.iv_icon_item);
                TextView cateNameTv = (TextView) itemView.findViewById(R.id.tv_cate_name);
                itemView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, CourseListActivity.class);
                        mContext.startActivity(intent);
                    }
                });
                GlideUtils.loadImageView(mContext,entity.getHomeIconList().get(i).getIconUrl(),imageView);
                cateNameTv.setText(entity.getHomeIconList().get(i).getCateName());
                rowLayout.addView(itemView,params);
                if(i%5==4||i==entity.getHomeIconList().size()-1){
                    containerLayout.addView(rowLayout,rowParams);
                }
            }
        }


    }
    public void createLayout(LinearLayout container,List<HomeIconEntity.HomeIconData> iconLists){
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
            TextView cateNameTv = (TextView) itemView.findViewById(R.id.tv_cate_name);
            itemView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, CourseListActivity.class);
                    mContext.startActivity(intent);
                }
            });
            GlideUtils.loadImageView(mContext,iconLists.get(i).getIconUrl(),imageView);
            cateNameTv.setText(iconLists.get(i).getCateName());
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
