package com.dev.nutclass.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dev.nutclass.R;
import com.dev.nutclass.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/28.
 */
public class HomeNavView extends FrameLayout implements View.OnClickListener{
    private static final String TAG = "HomeNavView";
    private Context mContext;
    private LinearLayout rootLayout;
    private List<ItemView> mItemLists;
    public HomeNavView(Context context) {
        super(context);
        this.mContext = context;
        intiView();
    }

    public HomeNavView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        intiView();
    }

    private void intiView() {
        mItemLists = new ArrayList<>();
        rootLayout = new LinearLayout(mContext);
        rootLayout.setOrientation(LinearLayout.HORIZONTAL);
        addView(1,mContext.getString(R.string.home_nav_home),R.drawable.selector_home_nav_home);
        addView(2,mContext.getString(R.string.home_nav_brand),R.drawable.selector_home_nav_brand);
        addView(3,mContext.getString(R.string.home_nav_find_course),R.drawable.selector_home_nav_map);
        addView(4,mContext.getString(R.string.home_nav_me),R.drawable.selector_home_nav_me);
        rootLayout.setWeightSum(mItemLists.size());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT
                ,LinearLayout.LayoutParams.WRAP_CONTENT);
        this.addView(rootLayout,params);
    }
    public void addView(int id,String text,int res){
        LinearLayout llLayout = new LinearLayout(mContext);
        llLayout.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0,
                ViewGroup.LayoutParams.WRAP_CONTENT,1.0f);
        rootLayout.addView(llLayout,params);

        View child = LayoutInflater.from(mContext).inflate(R.layout.view_item_home_nav,null);
        LinearLayout.LayoutParams childParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        llLayout.addView(child,childParams);

        child.setClickable(true);
        child.setOnClickListener(this);
        child.setTag(id-1);

        ItemView it = new ItemView(child,id,text,res);
        mItemLists.add(it);
    }

    @Override
    public void onClick(View v) {
        LogUtil.d(TAG,"onclick:"+v.getTag());
        int tag = (Integer) v.getTag();
        for(int i = 0;i<mItemLists.size();i++){
            if(tag==i){
                boolean isChange = true;
                if(mOnTabClickListener!=null){
                    isChange = mOnTabClickListener.onTabClick(i);
                }
                if(isChange){
                    updateState(i);
                }
                break;
            }
        }
    }
    public void setSelected(int index){
        if(index>=0&&index<mItemLists.size()){
            updateState(index);
        }
    }
    private void updateState(int selected){
        if(selected<0||selected>mItemLists.size()){
            return;
        }else{
            for(int i = 0;i<mItemLists.size();i++){
                if(i==selected){
                    mItemLists.get(i).setSelected(true);
                }else{
                    mItemLists.get(i).setSelected(false);
                }
            }
        }
    }
    private OnTabClickListener mOnTabClickListener;

    public OnTabClickListener getmOnTabClickListener() {
        return mOnTabClickListener;
    }

    public void setmOnTabClickListener(OnTabClickListener mOnTabClickListener) {
        this.mOnTabClickListener = mOnTabClickListener;
    }

    public interface OnTabClickListener{
        public boolean onTabClick(int pos);
    }
    class ItemView{
        private final int id;
        ImageView imageView;
        TextView textView;

        public ItemView(View v,int id,String text,int res) {
            this.id = id;
            imageView = (ImageView) v.findViewById(R.id.iv_home_nav);
            textView = (TextView) v.findViewById(R.id.tv_home_nav);
            imageView.setImageResource(res);
            textView.setText(text);
        }
        public void setSelected(boolean b){
            imageView.setSelected(b);
            textView.setSelected(b);
        }
    }
}
