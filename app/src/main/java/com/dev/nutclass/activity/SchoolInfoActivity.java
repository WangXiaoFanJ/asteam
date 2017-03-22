package com.dev.nutclass.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.dev.nutclass.R;
import com.dev.nutclass.adapter.CardListAdapter;
import com.dev.nutclass.constants.Const;
import com.dev.nutclass.constants.UrlConst;
import com.dev.nutclass.entity.BaseCardEntity;
import com.dev.nutclass.entity.JsonDataList;
import com.dev.nutclass.entity.ShareInfoEntity;
import com.dev.nutclass.entity.SimpleEntity;
import com.dev.nutclass.network.OkHttpClientManager;
import com.dev.nutclass.parser.CardListParser;
import com.dev.nutclass.parser.SimpleParser;
import com.dev.nutclass.utils.DialogUtils;
import com.dev.nutclass.utils.LogUtil;
import com.dev.nutclass.utils.SharedPrefUtil;
import com.dev.nutclass.utils.SnsUtil;
import com.squareup.okhttp.Request;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SchoolInfoActivity extends BaseActivity  implements View.OnClickListener{
    private static final String TAG = "SchoolInfoActivity";
    private ImageView backIv,shareIv;
    private RecyclerView recyclerView;
    private Context mContext;
    private List<BaseCardEntity> lists;
    private String schoolId;
    private ImageView attentionTv;
    private ShareInfoEntity shareInfoEntity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_info);
        mContext = SchoolInfoActivity.this;
        schoolId = getIntent().getStringExtra(Const.SCHOOL_ID);
        intiView();
        initListener();
        initData();
    }


    private void intiView() {
        backIv = (ImageView) findViewById(R.id.iv_back);
        shareIv = (ImageView) findViewById(R.id.iv_share);
        recyclerView = (RecyclerView) findViewById(R.id.container);
        attentionTv = (ImageView) findViewById(R.id.iv_attention);
    }
    private void initListener() {
        backIv.setOnClickListener(this);
        shareIv.setOnClickListener(this);
        attentionTv.setOnClickListener(this);
    }
    private void initData() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        reqData();
//        lists = new ArrayList<>();
//        BaseCardEntity headView = new BaseCardEntity();
//        headView.setCardType(BaseCardEntity.CARD_TYPE_SCHOOL_INFO_HEAD_NAME);
//        lists.add(headView);
//        BaseCardEntity courseView = new BaseCardEntity();
//        courseView.setCardType(BaseCardEntity.CARD_TYPE_SCHOOL_INFO_COURSE_VIEW);
//        for(int i = 0;i<3;i++){
//            lists.add(courseView);
//        }
//        BaseCardEntity view001 = new BaseCardEntity();
//        view001.setCardType(BaseCardEntity.CARD_TYPE_SCHOOL_INFO_001_View);
//        lists.add(view001);
//        BaseCardEntity schoolFeatureView = new BaseCardEntity();
//        schoolFeatureView.setCardType(BaseCardEntity.CARD_TYPE_SCHOOL_FEATURE_VIEW);
//        lists.add(schoolFeatureView);
//        BaseCardEntity amusePark = new BaseCardEntity();
//        amusePark.setCardType(BaseCardEntity.CARD_TYPE_NEARBY_AMUSE_PARK_VIEW);
//        lists.add(amusePark);
//        BaseCardEntity recommendView = new BaseCardEntity();
//        recommendView.setCardType(BaseCardEntity.CARD_TYPE_SCHOOL_RECOMMEND_VIEW);
//        lists.add(recommendView);

//        CardListAdapter adapter = new CardListAdapter(lists,mContext);
//        recyclerView.setAdapter(adapter);
    }

    private void reqData() {
        String url = UrlConst.SCHOOL_DETAIL_URL;
        Map<String,String> map =new HashMap<>();
        LogUtil.d("===","schoolId"+schoolId);
        map.put("schoolId",schoolId);
        OkHttpClientManager.postAsyn(url, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
                LogUtil.e(TAG,"error:"+e.getMessage());
            }

            @Override
            public void onResponse(String response) {
                LogUtil.d(TAG,"response:"+response);
                CardListParser parser = new CardListParser();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONObject shareObj = jsonObject.optJSONObject("share_info");
                    shareInfoEntity = new ShareInfoEntity(shareObj);
                    JSONArray cardListArray = jsonObject.optJSONArray("data");
                    JsonDataList<BaseCardEntity> result = (JsonDataList<BaseCardEntity>) parser.parse(cardListArray);
                    if(result.getErrorCode()== UrlConst.SUCCESS_CODE){
                        if(result.getList()!=null&&result.getList().size()>0){
                            update(result.getList());
                        }}
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        },map);
    }

    private void update(ArrayList<BaseCardEntity> list) {
        recyclerView.setAdapter(new CardListAdapter(list,mContext));
    }

    @Override
    public void onClick(View v) {
        if(v==backIv){
            finish();
        }else if(v == shareIv){
            UMWeb web = new UMWeb(shareInfoEntity.getUrl());
            web.setTitle(shareInfoEntity.getTitle());//标题
            //分享图片
            UMImage image = new UMImage(mContext, R.mipmap.ic_launcher);
//                image.compressStyle = UMImage.CompressStyle.SCALE;//大小压缩，默认为大小压缩，适合普通很大的图
//                image.compressStyle = UMImage.CompressStyle.QUALITY;//质量压缩，适合长图的分享
            UMImage thumb =  new UMImage(this,R.mipmap.ic_launcher);
            image.setThumb(thumb);
            web.setThumb(image);  //缩略图
            web.setDescription(shareInfoEntity.getContext());//描述
            SnsUtil.getInstance(mContext).openShare((CourseInfoActivity) mContext,web);
        }else if (v==attentionTv){
            collectSchool();
        }
    }

    private void collectSchool() {
        {
            String userId = null;
            if(SharedPrefUtil.getInstance().getSession()!=null){
                userId = SharedPrefUtil.getInstance().getSession().getUserId();
            }else{
                startActivity(new Intent(mContext,LoginActivity.class));
            }
            final Map<String,String> map = new HashMap();
            map.put("userId",userId);
            map.put("schoolId",schoolId);
            String url =UrlConst.COLLECT_SCHOOL_URL;
            OkHttpClientManager.postAsyn(url, new OkHttpClientManager.ResultCallback<String>() {
                @Override
                public void onError(Request request, Exception e) {
                    LogUtil.d(TAG,"error:"+e.getMessage());
                }

                @Override
                public void onResponse(String response) {
                    LogUtil.d(TAG,"response:"+response);
                    SimpleParser parse = new SimpleParser();
                    try {
                        SimpleEntity entity = (SimpleEntity) parse.parse(response);
                        if(entity.getStatus().equals("1")){
                            DialogUtils.showToast(mContext,entity.getMessage());
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            },map);
        }
    }
}
