package com.dev.nutclass.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dev.nutclass.R;

/**
 * Created by Administrator on 2017/1/11.
 */
public class TitleBar extends FrameLayout implements View.OnClickListener{
    private final String TAG = "TitleBar";
    private Context mContext;
    private View mTitlebar;
    private FrameLayout leftLayout;
    private FrameLayout rightLayout1;
    private FrameLayout middleLayout;
    private FrameLayout rightLayout2;

    private TextView mMiddleTv;
    private ImageView middleArrawIv;

    /**
     * TAG值，标题栏左边
     */
    public static final int TAG_LEFT = 0;
    /**
     * TAG值，标题栏中间
     */
    public static final int TAG_MIDDLE = 3;

    /**
     * TAG值，标题栏右边第一个按钮
     */
    public static final int TAG_RIGHT1 = 1;

    /**
     * TAG值，标题栏右边第二个按钮
     */
    public static final int TAG_RIGHT2 = 2;
    /**
     * 控件类型-空
     */
    public static final int TYPE_NULL = 0;

    /**
     * 控件类型-返回按钮
     */
    public static final int TYPE_BACK_IMG = 1;

    /**
     * 控件类型- 文字
     */
    public static final int TYPE_MIDDLE_TXT = 2;
    public TitleBar(Context context) {
        super(context);
        mContext = context;
    }

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        mTitlebar = LayoutInflater.from(mContext).inflate(R.layout.view_title_tab,this);
        leftLayout = (FrameLayout) this.findViewById(R.id.fl_title_left);
        rightLayout1 = (FrameLayout)this.findViewById(R.id.fl_title_right1);
        rightLayout2 = (FrameLayout)this.findViewById(R.id.fl_title_right2);
        middleLayout = (FrameLayout)this.findViewById(R.id.fl_title_middle);

        if (attrs != null) {
            TypedArray a = mContext.obtainStyledAttributes(attrs, R.styleable.TitleBar, 0, 0);
            int leftType = a.getInt(R.styleable.TitleBar_left_type, TYPE_NULL);
            int rightType1 = a.getInt(R.styleable.TitleBar_right_type1, TYPE_NULL);
            int rightType2 = a.getInt(R.styleable.TitleBar_right_type2, TYPE_NULL);
            String middleText = a.getString(R.styleable.TitleBar_middle_text);
            a.recycle();
            leftLayout.setOnClickListener(this);
            rightLayout1.setOnClickListener(this);
            rightLayout2.setOnClickListener(this);
            middleLayout.setOnClickListener(this);

            setView(TAG_LEFT, createView(leftType));
            setView(TAG_RIGHT1, createView(rightType1));
            setView(TAG_RIGHT2, createView(rightType2));
            setMiddleTextView(middleText);
        }
    }
    /**
     * titleBar背景颜色
     */
    public void setBackgroundColorResource(int resId) {
        mTitlebar.setBackgroundResource(resId);
    }
    public void setMiddleText(String text){
        setMiddleText(text, false, false);
    }

    public void setMiddleText(String text, boolean showArray, boolean isOpen) {
        if (mMiddleTv != null) {
            mMiddleTv.setText(text);
        }
        if (showArray) {
            middleArrawIv.setVisibility(View.VISIBLE);
            if (isOpen) {
                middleArrawIv.setImageResource(R.drawable.titlebar_album_dwon);
            } else {
                middleArrawIv
                        .setImageResource(R.drawable.titlebar_album_normal);
            }
        } else {
            middleArrawIv.setVisibility(View.GONE);
        }
    }
    private void setMiddleTextView(String text) {
        View view = (View) createView(TYPE_MIDDLE_TXT);
        mMiddleTv = (TextView) view.findViewById(R.id.view_text);
        middleArrawIv = (ImageView) view.findViewById(R.id.iv_arraw);
        middleArrawIv.setVisibility(View.GONE);
        if (view != null) {
            mMiddleTv.setText(text);
            setView(TAG_MIDDLE, view);
        }
    }

    @SuppressLint("NewApi")
    private View createView(int type){
        View view = null;
        switch (type){
            case TYPE_BACK_IMG:
                view =createImageView(R.drawable.icon_back_yellow);
                break;
            case TYPE_MIDDLE_TXT:
                view = LayoutInflater.from(mContext).inflate(R.layout.view_title_textview,null);
                view.setTag(TYPE_MIDDLE_TXT);
                break;
            default:
                break;
        }

        return view;
    }

    private View createImageView(int resId){
        ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(resId);
        return imageView;
    }


    /**
     * 给title bar设置特定的控件
     *
     * @param position
     *            控件位置：TAG_LEFT, TAG_MIDDLE, OR TAG_RIGHT
     * @param view
     *            添加的控件
     */
    public void setView(int position,View view){
        if(view==null){
            return;
        }
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        switch (position){
            case TAG_LEFT:
                leftLayout.removeAllViews();
                leftLayout.addView(view,param);
                break;
            case TAG_RIGHT1:
                rightLayout1.removeAllViews();
                rightLayout1.addView(view,param);
                break;
            case TAG_RIGHT2:
                rightLayout2.removeAllViews();
                rightLayout2.addView(view,param);
                break;
            case TAG_MIDDLE:
                middleLayout.removeAllViews();
                middleLayout.addView(view);
                break;
            default:
                break;
        }

    }

    @Override
    public void onClick(View v) {
        if (v == leftLayout) {
            if(leftLayout.getChildCount()>0){
                ((Activity) mContext).finish();
            }
        }
    }
}
