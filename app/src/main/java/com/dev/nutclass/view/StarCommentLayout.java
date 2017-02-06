package com.dev.nutclass.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.dev.nutclass.R;

/**
 * Created by Administrator on 2017/1/19.
 */
public class StarCommentLayout extends LinearLayout {
    private LinearLayout rootLayout;
    private Context mContext;
    private OnClickChildView onClickChildView;

    public OnClickChildView getOnClickChildView() {
        return onClickChildView;
    }

    public void setOnClickChildView(OnClickChildView onClickChildView) {
        this.onClickChildView = onClickChildView;
    }

    public StarCommentLayout(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public StarCommentLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }
    private void initView() {
        rootLayout = new LinearLayout(mContext);
        rootLayout.setOrientation(LinearLayout.HORIZONTAL);
        rootLayout.setWeightSum(5);
        LayoutParams param = new LayoutParams(LayoutParams.MATCH_PARENT
                , LayoutParams.WRAP_CONTENT);
        this.addView(rootLayout,param);
        LayoutParams params = new LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        for(int i = 0;i<5;i++){
            final View childView = LayoutInflater.from(mContext).inflate(R.layout.view_star_star,null);
            rootLayout.addView(childView,params);
            childView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    for(int j=0;j<5;j++){
                        if(childView==rootLayout.getChildAt(j)){
                            onClickChildView.onClickChild(j);
                            for(int k=0;k<5;k++){
                                if(k<j+1){
                                    rootLayout.getChildAt(k).setSelected(true);
                                }else {
                                    rootLayout.getChildAt(k).setSelected(false);
                                }

                            }
                            }
                    }
                }
            });
        }
    }
    public interface OnClickChildView{
        void onClickChild(int i);
    }
}
