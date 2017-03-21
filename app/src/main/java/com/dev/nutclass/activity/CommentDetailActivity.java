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
import com.dev.nutclass.parser.CourseCommentDetailParser;
import com.dev.nutclass.parser.CourseCommentParser;
import com.dev.nutclass.utils.LogUtil;
import com.squareup.okhttp.Request;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CommentDetailActivity extends BaseActivity {
    private static  final  String TAG = "CommentDetailActivity";
    private Context mContext;
    private String goodsId,commentId;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_detail);
        mContext = CommentDetailActivity.this;
        initView();
        initIntent();
        initData();
    }

    private void initIntent() {
        Bundle bundle = getIntent().getExtras();
        goodsId = bundle.getString(Const.GOODS_ID);
        commentId = bundle.getString(Const.COMMENT_ID);
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext,
                LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void initData() {
        String url = UrlConst.COURSE_COMMENT_DETAIL_URL;
        Map<String,String> map = new HashMap<>();
        map.put("goodsId",goodsId);
        map.put("commentId",commentId);
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
        CardListAdapter adapter = new CardListAdapter(list,mContext);
        recyclerView.setAdapter(adapter);
    }
}
