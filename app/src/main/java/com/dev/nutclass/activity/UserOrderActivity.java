package com.dev.nutclass.activity;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;


import com.dev.nutclass.R;
import com.dev.nutclass.adapter.CardListAdapter;
import com.dev.nutclass.constants.UrlConst;
import com.dev.nutclass.entity.BaseCardEntity;
import com.dev.nutclass.entity.JsonDataList;
import com.dev.nutclass.network.OkHttpClientManager;
import com.dev.nutclass.parser.CardListParser;
import com.dev.nutclass.utils.LogUtil;
import com.sina.weibo.sdk.api.share.Base;
import com.squareup.okhttp.Request;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class UserOrderActivity extends BaseActivity implements View.OnClickListener{
    private Context mContext;
    private TabLayout tabLayout;
    private List<BaseCardEntity> entityList;
    private List<String> stringLists;
    private RecyclerView recyclerView;
    private CardListAdapter adapter;
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
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        for(int i = 0;i<stringLists.size();i++){
            tabLayout.addTab(tabLayout.newTab().setText(stringLists.get(i)));
        }
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition()==1){
                    reqData(2);
//                    List<BaseCardEntity> waitPayList = new ArrayList<BaseCardEntity>();
//                    BaseCardEntity waitPayentity = new BaseCardEntity();
//                    waitPayentity.setCardType(BaseCardEntity.CARD_TYPE_USER_ORDER_WAIT_PAY_VIEW);
//                    for(int i=0;i<4;i++){
//                        waitPayList.add(waitPayentity);
//                    }
//                    adapter = new CardListAdapter(waitPayList,mContext);
//                    recyclerView.setAdapter(adapter);
                }else if(tab.getPosition()==2){
                    reqData(3);
                }else if(tab.getPosition()==3){
                    reqData(4);
                }else if(tab.getPosition()==0){
                    reqData(1);
                }else{
                    reqData(5);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void initData() {
//        BaseCardEntity waitCommentEntity= new BaseCardEntity();
//        waitCommentEntity.setCardType(BaseCardEntity.CARD_TYPE_USER_ORDER_VIEW);
//        for(int i=0;i<4;i++){
//            entityList.add(waitCommentEntity);
//        }
        reqData(2);
    }

    private void reqData(int type) {
        String url = "http://dev.kobiko.cn/api/index/individualOrderList?orderType="+type;
        OkHttpClientManager.getAsyn(url, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
                LogUtil.e(TAG,"error:"+e.getMessage());
            }

            @Override
            public void onResponse(String response) {
               LogUtil.d(TAG,"response:"+response);
                CardListParser parser = new CardListParser();
                JsonDataList<BaseCardEntity> result  = null;
                try {
                    result = (JsonDataList<BaseCardEntity>) parser.parse(response);
                    if(result.getErrorCode()== UrlConst.SUCCESS_CODE){
                        ArrayList<BaseCardEntity> list = result
                                .getList();
                        if (list != null && list.size() > 0) {
                            update(list);
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });
    }

    private void update(ArrayList<BaseCardEntity> list) {
        adapter  = new CardListAdapter(list,mContext);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {

    }
}
