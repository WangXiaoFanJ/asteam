package com.dev.nutclass.activity;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TableLayout;

import com.dev.nutclass.R;
import com.dev.nutclass.adapter.CardListAdapter;
import com.dev.nutclass.constants.UrlConst;
import com.dev.nutclass.entity.BaseCardEntity;
import com.dev.nutclass.entity.JsonDataList;
import com.dev.nutclass.network.OkHttpClientManager;
import com.dev.nutclass.parser.BaseParser;
import com.dev.nutclass.parser.CardListParser;
import com.dev.nutclass.utils.LogUtil;
import com.squareup.okhttp.Request;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DiscountCouponActivity extends BaseActivity {
    private static final String TAG = "DiscountCouponActivity";
    private TabLayout tabLayout;
    private Context mContext;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discount_coupon);
        mContext = DiscountCouponActivity.this;
        initView();
        initData();
    }

    private void initView() {
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    }
    private void initData() {
        List<String> stringList = new ArrayList<>();
        stringList.add("未使用");
        stringList.add("已过期");
        stringList.add("已使用");
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        for(int i=0;i<stringList.size();i++){
            tabLayout.addTab(tabLayout.newTab().setText(stringList.get(i)));
        }
        reqData(1);
//        List<BaseCardEntity> discountList = new ArrayList<>();
//        BaseCardEntity discountEntity = new BaseCardEntity();
//        discountEntity.setCardType(BaseCardEntity.CARD_TYPE_DISCOUNT_COUPON_VIEW);
//        for(int i = 0;i<4;i++){
//            discountList.add(discountEntity);
//        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        reqData(1);
                        break;
                    case 1:
                        reqData(2);
                        break;
                    case 2:
                        reqData(3);
                        break;
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

    private void reqData(int type) {
        String url = "http://dev.kobiko.cn/api/index/getCouponList?couponType="+type;
        OkHttpClientManager.getAsyn(url, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
                LogUtil.e(TAG,"error:"+e.getMessage());
            }

            @Override
            public void onResponse(String response) {
                LogUtil.d(TAG,"response:"+response);
                CardListParser parser = new CardListParser();
                JsonDataList<BaseCardEntity> result = null;
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONObject jsonObject1 = jsonObject.optJSONObject("data");
                    JSONArray cardListArray = jsonObject1.optJSONArray("list");
                    result = (JsonDataList<BaseCardEntity>) parser.parse(cardListArray);
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
        CardListAdapter adapter = new CardListAdapter(list,mContext);
        recyclerView.setAdapter(adapter);
    }
}
