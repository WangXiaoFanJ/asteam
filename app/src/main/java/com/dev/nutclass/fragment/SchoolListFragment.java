package com.dev.nutclass.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextClock;
import android.widget.TextView;

import com.dev.nutclass.R;
import com.dev.nutclass.activity.SchoolInfoActivity;
import com.dev.nutclass.adapter.CardListAdapter;
import com.dev.nutclass.constants.UrlConst;
import com.dev.nutclass.entity.BaseCardEntity;
import com.dev.nutclass.entity.JsonDataList;
import com.dev.nutclass.network.OkHttpClientManager;
import com.dev.nutclass.parser.CardListParser;
import com.dev.nutclass.utils.LogUtil;
import com.squareup.okhttp.Request;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SchoolListFragment extends BaseFragment {
    private static final String TAG = "SchoolListFragment";
    private RecyclerView recyclerView;
    private Context mContext;
    private List<BaseCardEntity> list;
    public SchoolListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_school_list, container, false);
        mContext = getActivity();
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        initData();
        return view;
    }

    private void initData() {
        String url = UrlConst.SCHOOL_LIST_URL;
        OkHttpClientManager.getAsyn(url, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
                LogUtil.d(TAG, "error e=" + e.getMessage());
            }

            @Override
            public void onResponse(String response) {
                LogUtil.d(TAG, "response=" + response);
                CardListParser parser = new CardListParser();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONObject jsonObject1 = jsonObject.optJSONObject("data");
                    JSONArray cardListArray = jsonObject1.optJSONArray("list");
                    JsonDataList<BaseCardEntity> result = (JsonDataList<BaseCardEntity>) parser.parse(cardListArray);
                    if(result.getErrorCode()== UrlConst.SUCCESS_CODE){
                        ArrayList<BaseCardEntity> list = result.getList();
                        if(list!=null&&list.size()>0){
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
        recyclerView.setAdapter(new CardListAdapter(list,mContext));
    }

}
