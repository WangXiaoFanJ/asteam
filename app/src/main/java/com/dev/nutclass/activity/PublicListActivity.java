package com.dev.nutclass.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.dev.nutclass.R;
import com.dev.nutclass.adapter.CardListAdapter;
import com.dev.nutclass.adapter.RecyclerItemClickListener;
import com.dev.nutclass.constants.Const;
import com.dev.nutclass.constants.UrlConst;
import com.dev.nutclass.entity.BaseCardEntity;
import com.dev.nutclass.entity.JDCatCardEntity;
import com.dev.nutclass.entity.JsonDataList;
import com.dev.nutclass.entity.SingleItemCardEntity;
import com.dev.nutclass.network.HttpUtil;
import com.dev.nutclass.network.OkHttpClientManager;
import com.dev.nutclass.parser.BaseParser;
import com.dev.nutclass.parser.CardListParser;
import com.dev.nutclass.parser.JDCatListParse2;
import com.dev.nutclass.parser.JDCatListParser;
import com.dev.nutclass.utils.LogUtil;
import com.dev.nutclass.view.TitleBar;
import com.squareup.okhttp.Request;

import org.json.JSONException;

import java.util.ArrayList;

public class PublicListActivity extends BaseActivity {
    private static final String TAG = "PublicListActivity";
    private Context mContext;
    private int curPage = 1;
    private TitleBar titleBar;
    private RecyclerView recyclerView;
    private int type = 0;// 判断来源
    private CardListAdapter adapter;
    private String selectedP="";
    private String selectedC="";
    private RecyclerItemClickListener itemClickListener5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_public_list);
        mContext = PublicListActivity.this;
        initView();
        initIntent();
        initListener();
        initData();
    }


    private void initView() {
        titleBar = (TitleBar) findViewById(R.id.title_bar);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager =new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

    }

    private void initIntent() {
        type =getIntent().getIntExtra(Const.KEY_TYPE,0);
    }

    private void initListener() {
        itemClickListener5 = new RecyclerItemClickListener() {

            @Override
            public void onItemClick(View view, Object obj) {
                // TODO Auto-generated method stub
                SingleItemCardEntity entity = (SingleItemCardEntity) obj;
//				DialogUtils.showToast(context, entity.getDesc());
                // 此处点击重新请求
                selectedC=entity.getId();
                selectedP=entity.getTag();
                reqData(1);
            }


        };

    }

    private void initData() {
        if(type==Const.TYPE_FROM_AMUSE_PARK){
            titleBar.setMiddleText("附近游乐场");
        }else if(type == Const.TYPE_FROM_JD){
            titleBar.setMiddleText("京东商城");
            reqData(1);
        }else if(type == Const.TYPE_FROM_SCHOLL_MOR_COURSE){
            titleBar.setMiddleText("正课");
            reqData(1);
        }

    }

    private void reqData(final int page) {
        if (page == 1) {
            curPage = 1;
//            refreshLayout.setLoadMore(true);
        }
        String url = null;
        if (type == Const.TYPE_FROM_JD) {
            url = String.format(HttpUtil.addBaseGetParams(UrlConst.KJD_CAT_URL),
                    selectedC);
            url = url + "&pageNo=" + page;
        }else if (type == Const.TYPE_FROM_SCHOLL_MOR_COURSE){
            url = UrlConst.SCHOOL_TO_COURSE_LIST_URL;
        }
        OkHttpClientManager.getAsyn(url, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
                LogUtil.d(TAG, "error e=" + e.getMessage());
            }

            @Override
            public void onResponse(String response) {
                LogUtil.d(TAG, "response=" + response);
                BaseParser<BaseCardEntity> parser = null;
                if (type == Const.TYPE_FROM_JD) {
                    //根据不同page解析得到list
                    if(page==1) {
                        parser = new JDCatListParser();
                    }else{
                        parser = new JDCatListParse2();
                    }
                }else if(type == Const.TYPE_FROM_SCHOLL_MOR_COURSE){
                    parser = new CardListParser();
                }
                JsonDataList<BaseCardEntity> result;
                try {
                    result = (JsonDataList<BaseCardEntity>) parser
                            .parse(response);
                    if (result.getErrorCode() == UrlConst.SUCCESS_CODE) {
                        ArrayList<BaseCardEntity> list = result
                                .getList();
                        if (list != null && list.size() > 0) {
                            curPage = page;
                            update(list);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        });
    }

    private void update(ArrayList<BaseCardEntity> list) {
        if (curPage == 1) {
            if (list!=null&&list.size()>0) {
                for(int i=0;i<list.size();i++){
                    if(list.get(i).getCardType()==BaseCardEntity.CARD_TYPE_JD_CAT_CARD){
                        if(TextUtils.isEmpty(selectedC)||TextUtils.isEmpty(selectedP)){

                        }else{
                            ((JDCatCardEntity)(list.get(i))).setSelectedC(selectedC);
                            ((JDCatCardEntity)(list.get(i))).setSelectedP(selectedP);
                        }
                        break;

                    }
                }
            }
            adapter = new CardListAdapter(mContext, list,itemClickListener5);
            recyclerView.setAdapter(adapter);
        } else {
//            adapter.addList(list);
        }
    }


}
