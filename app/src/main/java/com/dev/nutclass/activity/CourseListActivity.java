package com.dev.nutclass.activity;

import android.support.v4.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.dev.nutclass.R;
import com.dev.nutclass.fragment.BaseFragment;
import com.dev.nutclass.fragment.CourseListFragment;
import com.dev.nutclass.fragment.SchoolListFragment;


public class CourseListActivity extends BaseActivity implements View.OnClickListener{
    private static final String TAG = "CourseListActivity";
    private Context mContext;
    private Button titleBarLeftBtn;
    private Button titleBarRightBtn;
    private ImageView backIv;
    private ImageView searchIv;
    private FrameLayout container;

    private CourseListFragment courseFragment;
    private SchoolListFragment schoolFragment;
    private BaseFragment curFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);
        mContext = CourseListActivity.this;
        initView();
        initListener();
    }

    private void initView() {
        titleBarLeftBtn = (Button) findViewById(R.id.btn_left);
        titleBarRightBtn = (Button) findViewById(R.id.btn_right);
        titleBarLeftBtn.setSelected(true);
        backIv = (ImageView) findViewById(R.id.iv_back);
        searchIv = (ImageView) findViewById(R.id.iv_search);
        container = (FrameLayout) findViewById(R.id.container);

        courseFragment = new CourseListFragment();
        curFragment = courseFragment;
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.container,courseFragment);
        transaction.commit();
    }
    private void initListener() {
        titleBarLeftBtn.setOnClickListener(this);
        titleBarRightBtn.setOnClickListener(this);
        backIv.setOnClickListener(this);
        searchIv.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if(v==backIv){
            finish();
        }else if(v==searchIv){
            startActivity(new Intent(mContext,SearchActivity.class));
        }else if (v==titleBarLeftBtn){
            titleBarLeftBtn.setSelected(true);
            titleBarRightBtn.setSelected(false);
            switchFragment(1);
        }else if(v==titleBarRightBtn){
            titleBarLeftBtn.setSelected(false);
            titleBarRightBtn.setSelected(true);
            switchFragment(2);
        }
    }

    private void switchFragment(int pos) {
            if(pos==1){
                if(courseFragment == null){
                    courseFragment = new CourseListFragment();
                }
                switchContent(courseFragment);
            }else if (pos==2){
                if(schoolFragment == null){
                    schoolFragment = new SchoolListFragment();
                }
                switchContent(schoolFragment);
            }
    }

    private void switchContent(BaseFragment to) {
        if(curFragment!=to){
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            if(!to.isAdded()){
                transaction.hide(curFragment).add(R.id.container,to).commit();
            }else{
                transaction.hide(curFragment).show(to).commit();
            }
            curFragment =to;
        }

    }
}
