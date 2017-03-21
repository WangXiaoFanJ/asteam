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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dev.nutclass.R;

/**
 * Created by Administrator on 2017/1/11.
 */
public class TitleBar extends FrameLayout implements View.OnClickListener{
    private static final String TAG = "TitleBar";
    private Context mContext;
    private View mTitlebar;
    private FrameLayout leftLayout;
    private FrameLayout rightLayout1;
    private FrameLayout middleLayout;
    private FrameLayout rightLayout2;

    private TextView mMiddleTv;
    private ImageView middleArrawIv;
    private RelativeLayout rootLayout;
    private BarClickListener mBarClickListener;
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
     * 控件类型-返回按钮 2
     */
    public static final int TYPE_BACK_IMG_WHITE = 10;
    /**
     * 控件类型- 文字
     */
    public static final int TYPE_MIDDLE_TXT = 2;
    /**
    * 控件类型-文字右01
    * */
    public static final int TYPE_RIGHT_TXT_01 = 3;
    /**
     * 控件类型-登录文字
     * */
    public static final int TYPE_MIDDLE_TXT_WHITE = 4;
    //文字(注册（big）)
    public static final int TYPE_MIDDLE_REGISTER_TEX =6;
    //控件类型-文字（注册）
    public static final int TYPE_RIGHT_REGISTER_TEX = 5;
    //控件类型-文字(编辑)
    public static final int TYPE_RIGHT_EDIT_TEX=7;
    //空间类型-文字(完成)
    public static final int TYPE_RIGHT_FINISH_TEX=8;
    //控件类型-文字（忘记密码）
    public static final int TYPE_MIDDLE_TEXT_FOR_PWD=9;
    /**
     * 背景颜色
     * */
    //主题色
    public static final int BG_YELLOW= 1;
    //浅灰色
    public static final int BG_GRAY=2;

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
        rootLayout = (RelativeLayout) this.findViewById(R.id.rl_root);

        if (attrs != null) {
            TypedArray a = mContext.obtainStyledAttributes(attrs, R.styleable.TitleBar, 0, 0);
            int leftType = a.getInt(R.styleable.TitleBar_left_type, TYPE_NULL);
            int rightType1 = a.getInt(R.styleable.TitleBar_right_type1, TYPE_NULL);
            int rightType2 = a.getInt(R.styleable.TitleBar_right_type2, TYPE_NULL);
            int middleId = a.getInt(R.styleable.TitleBar_middle_text_id,TYPE_NULL);
            int titleBg = a.getInt(R.styleable.TitleBar_title_bar_bg,TYPE_NULL);
            String middleText = a.getString(R.styleable.TitleBar_middle_text);
            a.recycle();
            leftLayout.setOnClickListener(this);
            rightLayout1.setOnClickListener(this);
            rightLayout2.setOnClickListener(this);
            middleLayout.setOnClickListener(this);

            setView(TAG_LEFT, createView(leftType));
            setView(TAG_RIGHT1, createView(rightType1));
            setView(TAG_RIGHT2, createView(rightType2));
            setView(TAG_MIDDLE,createView(middleId));
            if(middleText!=null){
                setMiddleTextView(middleText);
            }
           setBackgroundColorNew(titleBg);
        }
    }
    public void setTitleRight2(int rightType2) {
        setView(TAG_RIGHT2, createView(rightType2));
    }

    private void setBackgroundColorNew(int id) {
        switch (id){
            case BG_YELLOW:
                rootLayout.setBackgroundResource(R.color.color_f75250);
                break;
            case BG_GRAY:
                rootLayout.setBackgroundResource(R.color.color_bg);
                break;
        }
    }

    /**
     * titleBar背景颜色
     */
    public void setBackgroundColorResource(int resId) {
//        mTitlebar.setBackgroundResource(resId);
        rootLayout.setBackgroundResource(resId);
    }

    /**
     * 设置中间文字
     * */
    public void setMiddleTextNew(int middleType){
        setView(TAG_MIDDLE,  createView(middleType));

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
    public void setMiddleTextView(String text) {
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
                view =createImageView(R.drawable.icon_back);
                break;
            case TYPE_BACK_IMG_WHITE:
                view = createImageView(R.drawable.icon_back_white);
                break;
            case TYPE_MIDDLE_TXT:
                view = LayoutInflater.from(mContext).inflate(R.layout.view_title_textview,null);
                view.setTag(TYPE_MIDDLE_TXT);
                break;
            case TYPE_RIGHT_TXT_01:
                view = LayoutInflater.from(mContext).inflate(R.layout.view_title_textview,null);
                TextView textView = (TextView) view.findViewById(R.id.view_text);
                textView.setText("兑换");
                textView.setTextColor(getResources().getColor(R.color.color_7f7f7f));
                textView.setTextSize(13);
                break;
            case TYPE_RIGHT_REGISTER_TEX:
                view = LayoutInflater.from(mContext).inflate(R.layout.view_title_textview,null);
                TextView textView03 = (TextView) view.findViewById(R.id.view_text);
                textView03.setText("注册");
                textView03.setTextColor(getResources().getColor(R.color.color_white));
                textView03.setTextSize(13);
                break;
            case TYPE_MIDDLE_TXT_WHITE:
                view = LayoutInflater.from(mContext).inflate(R.layout.view_title_textview,null);
                TextView textView2 = (TextView) view.findViewById(R.id.view_text);
                textView2.setText("登录");
                textView2.setTextColor(getResources().getColor(R.color.color_white));
                textView2.setTextSize(15);
                break;
            case TYPE_MIDDLE_REGISTER_TEX:
                view = LayoutInflater.from(mContext).inflate(R.layout.view_title_textview,null);
                TextView textView04 = (TextView) view.findViewById(R.id.view_text);
                textView04.setText("注册");
                textView04.setTextColor(getResources().getColor(R.color.color_white));
                textView04.setTextSize(15);
                break;
            case TYPE_RIGHT_EDIT_TEX:
                view = LayoutInflater.from(mContext).inflate(R.layout.view_title_textview,null);
                TextView textView05 = (TextView) view.findViewById(R.id.view_text);
                textView05.setText("编辑");
                textView05.setTextColor(getResources().getColor(R.color.color_7f7f7f));
                textView05.setTextSize(14);
                break;
            case TYPE_RIGHT_FINISH_TEX:
                view = LayoutInflater.from(mContext).inflate(R.layout.view_title_textview,null);
                TextView textView06 = (TextView) view.findViewById(R.id.view_text);
                textView06.setText("完成");
                textView06.setTextColor(getResources().getColor(R.color.color_7f7f7f));
                textView06.setTextSize(14);
                break;
            case TYPE_MIDDLE_TEXT_FOR_PWD:
                view = LayoutInflater.from(mContext).inflate(R.layout.view_title_textview,null);
                TextView textView07 = (TextView) view.findViewById(R.id.view_text);
                textView07.setText("忘记密码");
                textView07.setTextColor(getResources().getColor(R.color.color_white));
                textView07.setTextSize(15);
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
        if (doBarClick(v))
            return;
        if (v == leftLayout) {
            if(leftLayout.getChildCount()>0){
                ((Activity) mContext).finish();
            }
        }
    }

    private boolean doBarClick(View v) {
        boolean handled = false;
        if (mBarClickListener != null) {
            if (v == rightLayout1) {
                handled = mBarClickListener.onClickRight1();
            }
            if(v==rightLayout2){
                handled = mBarClickListener.onClickRight2();
            }
        }
        return handled;
    }

    public void setBarClickListener(BarClickListener listener) {
        mBarClickListener = listener;
    }
    public static interface BarClickListener {
        public boolean onClickRight1();
        public boolean onClickRight2();

    }
}
