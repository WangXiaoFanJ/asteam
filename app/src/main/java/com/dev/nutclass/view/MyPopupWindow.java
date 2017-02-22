package com.dev.nutclass.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.dev.nutclass.R;


/**
 * 项目名称：tools
 * 类描述：
 * 创建人：zz
 * 创建时间：2016/6/27 MXS 17:40
 */
public class MyPopupWindow extends PopupWindow {

    private View mMenuView;
    public MyPopupWindow(Activity context) {
        super(context);
//        LayoutInflater inflater = (LayoutInflater) context
//                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        mMenuView = inflater.inflate(R.layout.view_discount_result, null);

        //设置按钮监听

        //设置SelectPicPopupWindow的View
        this.setContentView(mMenuView);
       attributeSetting(context);
    }
    public MyPopupWindow(Context context,View view){
        super(context);
        this.setContentView(view);
        attributeSetting(context);
    }

    public MyPopupWindow(Activity context, View view) {
        super(context);
        //设置SelectPicPopupWindow的View
        this.setContentView(view);
        attributeSetting(context);
    }


    /**
     * 属性设置
     */
    public void attributeSetting(final Context context){
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(LayoutParams.WRAP_CONTENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
        //this.setAnimationStyle(R.style.AppTheme);
        this.setBackgroundDrawable(new ColorDrawable());     //设置该背景点击空白区域视图消失
//            this.setBackgroundDrawable(null);               //设置为Null，点击空白区域不会消失

    /**
     * 弹出popupwindow，背景置灰
     * */
        WindowManager.LayoutParams lp = ((Activity)context).getWindow().getAttributes();
        lp.alpha = 0.8f;
        ((Activity)context).getWindow().setAttributes(lp);
        this.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp =   ((Activity)context).getWindow().getAttributes();
                lp.alpha = 1f;
                ((Activity)context).getWindow().setAttributes(lp);
            }
        });
    }


}
