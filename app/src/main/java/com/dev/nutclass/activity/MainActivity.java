package com.dev.nutclass.activity;



import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.dev.nutclass.R;
import com.dev.nutclass.fragment.BaseFragment;
import com.dev.nutclass.fragment.BrandFragment;
import com.dev.nutclass.fragment.EduMapFragment;
import com.dev.nutclass.fragment.HomeFragment;
import com.dev.nutclass.fragment.MeFragment;
import com.dev.nutclass.utils.LogUtil;
import com.dev.nutclass.view.HomeNavView;

public class MainActivity extends BaseActivity {
    private Context mContext;
    private HomeNavView homeNavView;
    private FrameLayout container;
    private HomeFragment homeFragment;
    private BrandFragment brandFragment;
    private MeFragment meFragment;
    private EduMapFragment eduMapFragment;
    private BaseFragment curFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = MainActivity.this;
        initView();
        initData();
        initListener();
    }

    private void initView() {
        homeNavView = (HomeNavView) findViewById(R.id.home_nav);
        container = (FrameLayout) findViewById(R.id.container);
    }
    private void initData() {
        container.removeAllViews();
        homeFragment = new HomeFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.container,homeFragment);
        curFragment = homeFragment;
        transaction.commit();
        homeNavView.setSelected(0);
    }
    private void initListener() {
        homeNavView.setmOnTabClickListener(new HomeNavView.OnTabClickListener() {
            @Override
            public boolean onTabClick(int pos) {
                switchFragment(pos);
                return true;
            }
        });
    }
    private void switchFragment(int to){
        LogUtil.i(TAG, "switchFragment to:" + to);
        switch (to){
            case 0:
                if(homeFragment == null){
                    homeFragment = new HomeFragment();
                }
                switchContent(homeFragment);
            break;
            case 1:
                if(brandFragment == null){
                    brandFragment = new BrandFragment();
                }
                switchContent(brandFragment);
                break;
            case 2:
                if(eduMapFragment == null){
                    eduMapFragment = new EduMapFragment();
                }
                switchContent(eduMapFragment);
                break;
            case 3:
                if(meFragment ==null){
                    meFragment = new MeFragment();
                }
                switchContent(meFragment);
                break;
        }
    }
    /**
     * fragment切换
     * */
    private void switchContent(BaseFragment to){
        if(curFragment != to){
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            if(!to.isAdded()){
                transaction.hide(curFragment).add(R.id.container,to).commit();
            }else{
                transaction.hide(curFragment).show(to).commit();
            }
            curFragment = to;
        }else{

        }
    }
}
