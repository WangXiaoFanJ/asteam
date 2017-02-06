package com.dev.nutclass.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.dev.nutclass.R;
import com.dev.nutclass.adapter.CardListAdapter;
import com.dev.nutclass.entity.BaseCardEntity;

import java.util.ArrayList;
import java.util.List;

public class SchoolInfoActivity extends BaseActivity  implements View.OnClickListener{
    private static final String TAG = "SchoolInfoActivity";
    private ImageView backIv;
    private RecyclerView recyclerView;
    private Context mContext;
    private List<BaseCardEntity> lists;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_info);
        mContext = SchoolInfoActivity.this;

        intiView();
        initListener();
        initData();
    }


    private void intiView() {
        backIv = (ImageView) findViewById(R.id.iv_back);
        recyclerView = (RecyclerView) findViewById(R.id.container);
    }
    private void initListener() {
        backIv.setOnClickListener(this);
    }
    private void initData() {
        lists = new ArrayList<>();
        BaseCardEntity headView = new BaseCardEntity();
        headView.setCardType(BaseCardEntity.CARD_TYPE_SCHOOL_INFO_HEAD_NAME);
        lists.add(headView);
        BaseCardEntity courseView = new BaseCardEntity();
        courseView.setCardType(BaseCardEntity.CARD_TYPE_SCHOOL_INFO_COURSE_VIEW);
        for(int i = 0;i<3;i++){
            lists.add(courseView);
        }
        BaseCardEntity view001 = new BaseCardEntity();
        view001.setCardType(BaseCardEntity.CARD_TYPE_SCHOOL_INFO_001_View);
        lists.add(view001);
        BaseCardEntity schoolFeatureView = new BaseCardEntity();
        schoolFeatureView.setCardType(BaseCardEntity.CARD_TYPE_SCHOOL_FEATURE_VIEW);
        lists.add(schoolFeatureView);
        BaseCardEntity amusePark = new BaseCardEntity();
        amusePark.setCardType(BaseCardEntity.CARD_TYPE_NEARBY_AMUSE_PARK_VIEW);
        lists.add(amusePark);
        BaseCardEntity recommendView = new BaseCardEntity();
        recommendView.setCardType(BaseCardEntity.CARD_TYPE_SCHOOL_RECOMMEND_VIEW);
        lists.add(recommendView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        CardListAdapter adapter = new CardListAdapter(lists,mContext);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        if(v==backIv){
            finish();
        }
    }
}
