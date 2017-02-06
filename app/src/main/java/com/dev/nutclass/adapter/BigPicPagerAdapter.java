package com.dev.nutclass.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.dev.nutclass.view.BigImgView;

import java.util.List;

/**
 * Created by Administrator on 2016/12/29.
 */
public class BigPicPagerAdapter extends PagerAdapter {
    private static final String TAG = "BigPicPagerAdapter";
    private List<BigImgView> imgViewList = null;
    public BigPicPagerAdapter(List<BigImgView> list,Context context) {
        this.imgViewList = list;
    }

    @Override
    public int getCount() {
        if (imgViewList != null) {
            return Integer.MAX_VALUE;
        }
        return 0;
    }
    public int getSize(){
        if(imgViewList==null){
            return  0;
        }
        return imgViewList.size();
    }
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        position=position%getSize();
        if(position<0){
            position = getSize()+position;
        }
        View view = imgViewList.get(position);
        ViewParent vp =view.getParent();
        if (vp!=null){
            ViewGroup parent = (ViewGroup)vp;
            parent.removeView(view);
            System.out.println(position+" remove");
        }else{
            System.out.println(position+" not remove");
        }
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//    container.removeView(imgViewList.get(position));
    }
}
