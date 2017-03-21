package com.dev.nutclass.fragment;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.dev.nutclass.ApplicationConfig;
import com.dev.nutclass.R;
import com.dev.nutclass.activity.SearchActivity;
import com.dev.nutclass.adapter.CardListAdapter;
import com.dev.nutclass.constants.Const;
import com.dev.nutclass.constants.UrlConst;
import com.dev.nutclass.entity.BaseCardEntity;
import com.dev.nutclass.entity.JsonDataList;
import com.dev.nutclass.network.OkHttpClientManager;
import com.dev.nutclass.parser.CardListParser;
import com.dev.nutclass.utils.LogUtil;
import com.dev.nutclass.utils.SharedPrefUtil;
import com.squareup.okhttp.Request;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener {
    private static final String TAG = "HomeFragment";
    private RecyclerView recyclerView;
    private LinearLayout titleBar;
    private List<BaseCardEntity> lists;
    private LinearLayout searchLayout;
    private Context mContext;
    private int mDistanceY;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mContext = getActivity();
        lists = new ArrayList<>();
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        titleBar = (LinearLayout) view.findViewById(R.id.title_bar);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        searchLayout = (LinearLayout) view.findViewById(R.id.ll_search);
        initListener();

        initData();



        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mDistanceY += dy;
                //toolbar的高度
                int scrollHeight = recyclerView.getChildAt(0).getBottom();

                //当滑动的距离 <= toolbar高度的时候，改变Toolbar背景色的透明度，达到渐变的效果
                if (mDistanceY <= scrollHeight) {
                    float scale = (float) mDistanceY / scrollHeight;
                    float alpha = scale * 255;
                    titleBar.setBackgroundColor(Color.argb((int) alpha, 255, 100, 100));
                } else {
                    //上述虽然判断了滑动距离与toolbar高度相等的情况，但是实际测试时发现，标题栏的背景色
                    //很少能达到完全不透明的情况，所以这里又判断了滑动距离大于toolbar高度的情况，
                    //将标题栏的颜色设置为完全不透明状态
                    titleBar.setBackgroundColor(getResources().getColor(R.color.color_ff5916));
                }

            }
        });
        return view;
    }
    private void initListener() {
        searchLayout.setOnClickListener(this);
    }

    private void initData() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        reqData();
//        ImageEntity imageEntity = new ImageEntity(1);
//        BaseCardEntity imageEntity = new BaseCardEntity();
//        imageEntity.setCardType(BaseCardEntity.CARD_TYPE_BANNER_COURSE_INFO);
//        IconEntity iconEntity = new IconEntity(3);
//        List<String> stringList = new ArrayList<>();
//        String [] strings = {"http://cdn2.kobiko.cn/./Uploads/2016-12-03/th_584281b63caa3.jpg",
//                "http://cdn1.kobiko.cn/./Uploads/2016-12-03/th_5842775fe7b5a.jpg",
//                "http://cdn1.kobiko.cn/./Uploads/2016-12-03/th_5842775fe7b5a.jpg"};
//        for(int i=0;i<strings.length;i++){
//            stringList.add(strings[i]);
//        }
//        List<String> iconLists = new ArrayList<>();
//        for(int i = 0;i<10;i++){
//            iconLists.add("http://182.92.7.222/app_files_v3/images/icon/zaojiao.png");
//        }

//        ClassifyHomeCourseEntity classifyEntity = new ClassifyHomeCourseEntity();
////        imageEntity.setImages(stringList);
//        iconEntity.setIconList(iconLists);
//        lists.add(imageEntity);
//        lists.add(iconEntity);
//        BaseCardEntity jdEntity = new BaseCardEntity();
//        jdEntity.setCardType(BaseCardEntity.CARD_TYPE_JD_CARD_VIEW);
//        lists.add(jdEntity);
//        lists.add(classifyEntity);
//        BaseCardEntity cardEntity = new BaseCardEntity();
//        cardEntity.setCardType(BaseCardEntity.CARD_TYPE_COURSE_CARD_VIEW);
//        for(int i =0;i<4;i++){
//            lists.add(cardEntity);
//        }

    }

    private void reqData() {
        String url = UrlConst.HOME_LIST_URL;
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
                    JSONArray cardListArray = jsonObject.optJSONArray("data");
                    result = (JsonDataList<BaseCardEntity>) parser.parse(cardListArray);
                    if(result.getErrorCode()== UrlConst.SUCCESS_CODE){
                        if(result.getList()!=null&&result.getList().size()>0){
                            update(result.getList());
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        });
    }

    private void update(ArrayList<BaseCardEntity> list) {
        recyclerView.setAdapter( new CardListAdapter(list, mContext));
    }

    @Override
    public void onClick(View v) {
        if(v == searchLayout){
            startActivity(new Intent(mContext, SearchActivity.class));
        }
    }


}
