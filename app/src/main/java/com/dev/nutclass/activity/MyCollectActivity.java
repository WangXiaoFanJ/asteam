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
import com.dev.nutclass.constants.Const;
import com.dev.nutclass.constants.UrlConst;
import com.dev.nutclass.entity.BaseCardEntity;
import com.dev.nutclass.entity.CourseListCardEntity;
import com.dev.nutclass.entity.JsonDataList;
import com.dev.nutclass.entity.SimpleEntity;
import com.dev.nutclass.network.OkHttpClientManager;
import com.dev.nutclass.parser.CollectListParser;
import com.dev.nutclass.parser.SimpleParser;
import com.dev.nutclass.utils.DialogUtils;
import com.dev.nutclass.utils.LogUtil;
import com.squareup.okhttp.Request;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyCollectActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "MyCollectActivity";
    private ImageView backIv;
    private Button leftBtn;
    private Button rightBtn;
    private ListView listView;
    private Context mContext;
    private TextView editTv,deleteTv;
    private boolean isEdit = false;
    private CollectCourseListAdapter adapter;
    private int type;
    private String url;
    private String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_collect);
        mContext = MyCollectActivity.this;
        userId = getIntent().getStringExtra(Const.TYPE_USER_ID);
        initView();
        type = BaseCardEntity.CARD_TYPE_COLLECT_COURSE_VIEW;
        url  = UrlConst.COLLECT_COURSE_LIST_URL;
        initData(type, url,isEdit);
    }

    private void initView() {
        backIv = (ImageView) findViewById(R.id.iv_back);
        leftBtn = (Button) findViewById(R.id.btn_left);
        rightBtn = (Button) findViewById(R.id.btn_right);
        editTv = (TextView) findViewById(R.id.tv_edit);
        listView = (ListView) findViewById(R.id.list_view);
        deleteTv = (TextView) findViewById(R.id.tv_delete);
        leftBtn.setSelected(true);
        backIv.setOnClickListener(this);
        leftBtn.setOnClickListener(this);
        rightBtn.setOnClickListener(this);
        editTv.setOnClickListener(this);
        deleteTv.setOnClickListener(this);
    }

    private void initData(final int type, String url, final boolean isEdit) {
//        String url = UrlConst.COLLECT_COURSE_LIST_URL;
        Map<String,String> map =new HashMap<>();
        map.put("userId",userId);
        OkHttpClientManager.postAsyn(url, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
                LogUtil.d(TAG, "error e=" + e.getMessage());
            }

            @Override
            public void onResponse(String response) {
                LogUtil.d(TAG, "result:" + response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.optString("status");
                    if(status.equals("0")){
                        listView.setVisibility(View.GONE);
                    }else{
                        listView.setVisibility(View.VISIBLE);
                        CollectListParser parser = new CollectListParser();
                        JsonDataList<BaseCardEntity> result = (JsonDataList<BaseCardEntity>) parser.parser(type, response);
                        update(result.getList(),type,isEdit);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        },map);
    }

    private void update(ArrayList<BaseCardEntity> list,int type,boolean isEdit) {
//        recyclerView.setAdapter(new CardListAdapter(list, mContext));
        adapter = new CollectCourseListAdapter(mContext,list,type,isEdit);
        adapter.notifyDataSetChanged();
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
                break;
            case R.id.tv_edit:
                isEdit=!isEdit;
                if(isEdit){
                    editTv.setText("取消");
                    deleteTv.setVisibility(View.VISIBLE);
                }else{
                    deleteTv.setVisibility(View.GONE);
                    editTv.setText("编辑");
                }
                initData(type,url,isEdit);
                break;
            case R.id.tv_delete:
               String goodsID = adapter.deleteList();
                LogUtil.d(TAG,"goodsIDList:"+goodsID);
                reqDelete(type,goodsID);
                break;
        }
    }

    //删除收藏课程或校区
    private void reqDelete(final int type, String goodsId) {
        String deleteUrl = "" ;
        Map<String,String> map = new HashMap<>();
        if(type== BaseCardEntity.CARD_TYPE_COLLECT_COURSE_VIEW){
            deleteUrl= UrlConst.DELETE_COURSE_RUL;
            map.put("userId",userId);
            map.put("goodsId",goodsId);
        }else if (type == BaseCardEntity.CARD_TYPE_COLLECT_SCHOOL_VIEW){
            deleteUrl= UrlConst.DELETE_SCHOOL_RUL;
            map.put("userId",userId);
            map.put("schoolId",goodsId);
        }

        OkHttpClientManager.postAsyn(deleteUrl, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
                LogUtil.d(TAG,"error:"+e.getMessage());
            }

            @Override
            public void onResponse(String response) {
                LogUtil.d(TAG,"response:"+response);
                SimpleParser parser = new SimpleParser();
                try {
                    SimpleEntity entity = (SimpleEntity) parser.parse(response);
                    if(entity.getStatus().equals("1")){
                        initData(type, url,isEdit);
                    }
                    DialogUtils.showToast(mContext,entity.getMessage());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        },map);
    }
}
