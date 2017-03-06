package com.dev.nutclass.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.dev.nutclass.R;
import com.dev.nutclass.adapter.CardListAdapter;
import com.dev.nutclass.adapter.CollectCourseListAdapter;
import com.dev.nutclass.callback.EditOnclickListener;
import com.dev.nutclass.constants.UrlConst;
import com.dev.nutclass.entity.BaseCardEntity;
import com.dev.nutclass.entity.CourseCardEntity;
import com.dev.nutclass.entity.CourseListCardEntity;
import com.dev.nutclass.entity.JsonDataList;
import com.dev.nutclass.network.OkHttpClientManager;
import com.dev.nutclass.parser.CollectListParser;
import com.dev.nutclass.utils.LogUtil;
import com.squareup.okhttp.Request;

import org.json.JSONException;

import java.util.ArrayList;

public class MyCollectActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "MyCollectActivity";
    private ImageView backIv;
    private Button leftBtn;
    private Button rightBtn;
    private ListView listView;
    private Context mContext;
    private TextView editTv;
    private boolean isEdit = false;
    private CollectCourseListAdapter adapter;
    private int type;
    private String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_collect);
        mContext = MyCollectActivity.this;
        initView();
        type = BaseCardEntity.CARD_TYPE_COLLECT_COURSE_VIEW;
        url = UrlConst.COLLECT_COURSE_LIST_URL;
        initData(type, url,isEdit);
    }

    private void initView() {
        backIv = (ImageView) findViewById(R.id.iv_back);
        leftBtn = (Button) findViewById(R.id.btn_left);
        rightBtn = (Button) findViewById(R.id.btn_right);
        editTv = (TextView) findViewById(R.id.tv_edit);
        listView = (ListView) findViewById(R.id.list_view);

        leftBtn.setSelected(true);
        backIv.setOnClickListener(this);
        leftBtn.setOnClickListener(this);
        rightBtn.setOnClickListener(this);
        editTv.setOnClickListener(this);
    }

    private void initData(final int type, String url, final boolean isEdit) {
//        String url = UrlConst.COLLECT_COURSE_LIST_URL;
        OkHttpClientManager.getAsyn(url, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
                LogUtil.d(TAG, "error e=" + e.getMessage());
            }

            @Override
            public void onResponse(String response) {
                LogUtil.d(TAG, "result:" + response);
                CollectListParser parser = new CollectListParser();
                try {
                    JsonDataList<BaseCardEntity> result = (JsonDataList<BaseCardEntity>) parser.parser(type, response);
                    update(result.getList(),type,isEdit);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        });
    }

    private void update(ArrayList<BaseCardEntity> list,int type,boolean isEdit) {
//        recyclerView.setAdapter(new CardListAdapter(list, mContext));
        adapter = new CollectCourseListAdapter(mContext,list,type,isEdit);
        listView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_left:
                leftBtn.setSelected(true);
                rightBtn.setSelected(false);
                type = BaseCardEntity.CARD_TYPE_COLLECT_COURSE_VIEW;
                url = UrlConst.COLLECT_COURSE_LIST_URL;
                initData(type,url,isEdit);
                break;
            case R.id.btn_right:
                leftBtn.setSelected(false);
                rightBtn.setSelected(true);
                type =BaseCardEntity.CARD_TYPE_COLLECT_SCHOOL_VIEW ;
                url = UrlConst.COLLECT_SCHOOL_LIST_URL;
                initData(type,url,isEdit);
                break;
            case R.id.iv_back:
                finish();
            case R.id.tv_edit:
                isEdit=!isEdit;
                if(isEdit){
                    editTv.setText("完成");
                }else{
                    editTv.setText("编辑");
                }
                initData(type,url,isEdit);
                break;
        }
    }
}
