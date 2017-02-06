package com.dev.nutclass.activity;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;


import com.dev.nutclass.R;
import com.dev.nutclass.adapter.CardListAdapter;
import com.dev.nutclass.entity.BaseCardEntity;

import java.util.ArrayList;
import java.util.List;

public class UserOrderActivity extends BaseActivity implements View.OnClickListener{
    private Context mContext;
    private TabLayout tabLayout;
    private List<BaseCardEntity> entityList;
    private List<String> stringLists;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_order);
        mContext = UserOrderActivity.this;
        stringLists = new ArrayList<>();
        entityList = new ArrayList<>();
        stringLists.add("全部订单");
        stringLists.add("待付款");
        stringLists.add("待使用");
        stringLists.add("待评价");
        stringLists.add("退款/售后");
        initView();
        initData();
    }




    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        for(int i = 0;i<stringLists.size();i++){
            TabLayout.Tab tab = tabLayout.newTab();
            tab.setText(stringLists.get(i));
            tabLayout.addTab(tabLayout.newTab().setText(stringLists.get(i)));
        }

    }
    private void initData() {
        BaseCardEntity waitPay = new BaseCardEntity();
        waitPay.setCardType(BaseCardEntity.CARD_TYPE_USER_ORDER_VIEW);
        for(int i=0;i<4;i++){
            entityList.add(waitPay);
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        CardListAdapter adapter = new CardListAdapter(entityList,mContext);
        recyclerView.setAdapter(adapter);
    }
    @Override
    public void onClick(View v) {

    }
}
