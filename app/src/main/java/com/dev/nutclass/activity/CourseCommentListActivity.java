package com.dev.nutclass.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dev.nutclass.R;
import com.dev.nutclass.adapter.CardListAdapter;
import com.dev.nutclass.constants.Const;
import com.dev.nutclass.constants.UrlConst;
import com.dev.nutclass.entity.BaseCardEntity;
import com.dev.nutclass.entity.JsonDataList;
import com.dev.nutclass.network.OkHttpClientManager;
import com.dev.nutclass.parser.CardListParser;
import com.dev.nutclass.parser.CourseCommentParser;
import com.dev.nutclass.utils.LogUtil;
import com.squareup.okhttp.Request;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CourseCommentListActivity extends BaseActivity {
    private Context mContext;
    private static final String TAG = "CourseCommentListActivity";
    private RecyclerView recyclerView;
    private String goodsId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_comment_list);
        mContext = CourseCommentListActivity.this;
        initView();
        initData();
    }
    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext,
        LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void initData() {
        goodsId = getIntent().getStringExtra(Const.GOODS_ID);
        reqData();
    }

    private void reqData() {
        String url = UrlConst.COURSE_COMMENT_LIST_URL;
        Map<String,String> map = new HashMap<>();
        LogUtil.d(TAG,"goodsId:"+goodsId);
        map.put("goodsId",goodsId);
        OkHttpClientManager.postAsyn(url, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
                LogUtil.d(TAG,"error:"+e.getMessage());
            }

            @Override
            public void onResponse(String response) {
                LogUtil.d(TAG,"response:"+response);
                CourseCommentParser parser = new CourseCommentParser();
                try {
                    JsonDataList<BaseCardEntity> result =null;
                    result = (JsonDataList<BaseCardEntity>) parser.parse(response);
                    if(result.getErrorCode()==UrlConst.SUCCESS_CODE){
                        if(result.getList()!=null&&result.getList().size()>0){
                            updata(result.getList());
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        },map);
    }

    private void updata(ArrayList<BaseCardEntity> list) {
        CardListAdapter adapte = new CardListAdapter(list,mContext);
        recyclerView.setAdapter(adapte);
    }

}
