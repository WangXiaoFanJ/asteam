package com.dev.nutclass.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.dev.nutclass.R;
import com.dev.nutclass.utils.DensityUtil;
import com.dev.nutclass.view.MyPopupWindow;

import cn.lankton.flowlayout.FlowLayout;

public class SearchActivity extends BaseActivity implements OnClickListener {
    private static  final  String TAG = "SearchActivity";
    private FlowLayout flowLayout;
    private Context mContext;
    private MyPopupWindow menuWindow;
    private TextView searchTv;
    String[] texts = new String[]{
            "绘画课程", "艺术", "大脑开发", "语言培训",
            "球", "少儿培训", "纽约国际儿童俱乐部", "早教", "体育",
            "瑞思", "大脑开发"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mContext = SearchActivity.this;
        initView();
        initListener();
        initData();
    }
    private void initView() {
        flowLayout = (FlowLayout) findViewById(R.id.flow_layout_hot);

        searchTv = (TextView) findViewById(R.id.tv_select);
    }
    private void initListener() {
        searchTv.setOnClickListener(this);
    }

    private void initData() {
        showFlowLayout();
    }

    private void showFlowLayout() {
        for(int i=0;i<texts.length;i++){
            int ranHeight = DensityUtil.dip2px(this, 33);
            ViewGroup.MarginLayoutParams lp = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ranHeight);
            lp.setMargins(DensityUtil.dip2px(this, 10), 0, 0, 0);
            TextView tv = new TextView(this);
            tv.setPadding(DensityUtil.dip2px(this, 10), 0, DensityUtil.dip2px(this, 10), 0);
            tv.setTextColor(Color.parseColor("#333333"));
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
//            int index = (int)(Math.random() * texts.length);
            tv.setText(texts[i]);
            tv.setGravity(Gravity.CENTER_VERTICAL);
            tv.setLines(1);
            tv.setBackgroundResource(R.drawable.flow_btn_shape);
            flowLayout.addView(tv, lp);
            final int finalI = i;
            tv.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(SearchActivity.this,texts[finalI]+"",Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public void onClick(View v) {
        if(v == searchTv){
            showPopupWindow();
        }
    }

    private void showPopupWindow() {
        LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rootView = inflater.inflate(R.layout.view_select_school_popup,null);
        menuWindow = new MyPopupWindow((Activity) mContext,rootView);
        menuWindow.showAsDropDown(searchTv);
//        menuWindow.showAtLocation(SearchActivity.this.findViewById(R.id.relative_parent),Gravity.LEFT|Gravity.TOP,0,0);
//        popWindowSetting();
    }
    private void popWindowSetting() {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.5f;
        getWindow().setAttributes(lp);
        menuWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
    }
}
