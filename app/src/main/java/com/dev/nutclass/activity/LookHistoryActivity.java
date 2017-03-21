package com.dev.nutclass.activity;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.dev.nutclass.R;
import com.dev.nutclass.adapter.CardListAdapter;
import com.dev.nutclass.constants.Const;
import com.dev.nutclass.constants.UrlConst;
import com.dev.nutclass.entity.BaseCardEntity;
import com.dev.nutclass.entity.JsonDataList;
import com.dev.nutclass.network.OkHttpClientManager;
import com.dev.nutclass.parser.CardListParser;
import com.dev.nutclass.parser.LookHistoryParser;
import com.dev.nutclass.utils.LogUtil;
import com.dev.nutclass.utils.SharedPrefUtil;
import com.dev.nutclass.view.TitleBar;
import com.squareup.okhttp.Request;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LookHistoryActivity extends BaseActivity {
    private static final String TAG = "LookHistoryActivity";
    private Context mContext;
    private RecyclerView recyclerView;
    private TitleBar titleBar;
    private boolean isEdit = false;
    private String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_look_history);
        mContext = LookHistoryActivity.this;
        userId = getIntent().getStringExtra(Const.TYPE_USER_ID);
        initView();
        initData();
    }

    private void initView() {
        titleBar = (TitleBar) findViewById(R.id.title_bar);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        titleBar.setBarClickListener(new TitleBar.BarClickListener() {
            @Override
            public boolean onClickRight1() {
                return false;
            }

            @Override
            public boolean onClickRight2() {
                isEdit=!isEdit;
                if(isEdit){
                    titleBar.setTitleRight2(8);
                    reqData(isEdit);
                }else{
                    titleBar.setTitleRight2(7);
                    reqData(isEdit);
                }
                return false;
            }
        });
    }

    private void initData() {
        LinearLayoutManager layouManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layouManager);
        reqData(false);
    }

    private void reqData(final boolean isEdit) {
        String url = UrlConst.LOOK_HISTORY_URL;
        Map<String,String> map = new HashMap<>();
        map.put("userId", userId);
        OkHttpClientManager.postAsyn(url, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
                LogUtil.d(TAG, "error:" + e.getMessage());
            }

            @Override
            public void onResponse(String response) {
                LogUtil.d(TAG, "response:" + response);
                LookHistoryParser parser = new LookHistoryParser();
                try {
                    JsonDataList<BaseCardEntity> result = (JsonDataList<BaseCardEntity>) parser.parse(response);
                    if (result.getErrorCode() == 1) {
                        if (result.getList() != null && result.getList().size() > 0) {
                            update(result,isEdit);
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        },map);
    }

    private void update(JsonDataList<BaseCardEntity> result,boolean isEdit) {
        CardListAdapter adapter = new CardListAdapter(result.getList(), mContext,isEdit);
        recyclerView.setAdapter(adapter);
    }

}
