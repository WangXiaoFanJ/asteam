package com.dev.nutclass.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.dev.nutclass.R;
import com.dev.nutclass.adapter.CardListAdapter;
import com.dev.nutclass.constants.Const;
import com.dev.nutclass.constants.UrlConst;
import com.dev.nutclass.entity.BannerCardEntity;
import com.dev.nutclass.entity.BaseCardEntity;
import com.dev.nutclass.entity.CourseCardEntity;
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
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseInfoActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "CourseInfoActivity";
    private RecyclerView recyclerView;
    private RelativeLayout titleBar;
    private Context mContext;

    private ImageView shareIv;
    private LinearLayout callPhoneLayout;
    private ImageView backImage,attentionIv,messageIv;
    private List<BaseCardEntity> lists;
    private int mDistanceY;
    private String goodsId;
    private  ShareInfoEntity shareInfoEntity;
    private LinearLayout writeCommentLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_info);
        mContext =CourseInfoActivity.this;
        goodsId = getIntent().getStringExtra(Const.GOODS_ID);
        initView();
        initListener();
        initData();
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        titleBar = (RelativeLayout) findViewById(R.id.title_bar);
        backImage = (ImageView) findViewById(R.id.iv_back);
        callPhoneLayout = (LinearLayout) findViewById(R.id.ll_phone_kobiko);
        shareIv = (ImageView) findViewById(R.id.iv_share);
        attentionIv = (ImageView) findViewById(R.id.iv_attention);
        messageIv = (ImageView) findViewById(R.id.iv_message);
        writeCommentLayout = (LinearLayout) findViewById(R.id.ll_write_comment);
        recyclerViewScrollListener();
    }


    private void initListener() {
        backImage.setOnClickListener(this);
        callPhoneLayout.setOnClickListener(this);
        shareIv.setOnClickListener(this);
        writeCommentLayout.setOnClickListener(this);
        attentionIv.setOnClickListener(this);
    }

    /**
     * recycler滚动改变标题栏透明度
     * */
    private void recyclerViewScrollListener() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mDistanceY += dy;
                //toolbar的高度
                int scrollHeight = titleBar.getBottom()+100;

                //当滑动的距离 <= toolbar高度的时候，改变Toolbar背景色的透明度，达到渐变的效果
                if (mDistanceY <= scrollHeight) {
                    float scale = (float) mDistanceY / scrollHeight;
                    float alpha = scale * 255;
                    titleBar.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
                    backImage.setImageDrawable(getResources().getDrawable(R.drawable.iocn_back_black_bg));
                    messageIv.setImageDrawable(getResources().getDrawable(R.drawable.icon_message));
                    shareIv.setImageDrawable(getResources().getDrawable(R.drawable.title_bar_share));
                    attentionIv.setImageDrawable(getResources().getDrawable(R.drawable.icon_keep_attention));
                } else {
                    //上述虽然判断了滑动距离与toolbar高度相等的情况，但是实际测试时发现，标题栏的背景色
                    //很少能达到完全不透明的情况，所以这里又判断了滑动距离大于toolbar高度的情况，
                    //将标题栏的颜色设置为完全不透明状态
                    titleBar.setBackgroundColor(getResources().getColor(R.color.color_white));
                    backImage.setImageDrawable(getResources().getDrawable(R.drawable.icon_back_course_02));
                    messageIv.setImageDrawable(getResources().getDrawable(R.drawable.icon_message_course_02));
                    shareIv.setImageDrawable(getResources().getDrawable(R.drawable.icon_share_course_02));
                    attentionIv.setImageDrawable(getResources().getDrawable(R.drawable.icon_attention_course_02));
                }

            }
        });
    }

    private void initData() {
//        lists = new ArrayList<>();
//        /**
//         * 假数据 bannerEntity
//         * */
//        BaseCardEntity imageEntity = new BaseCardEntity();
//        imageEntity.setCardType(BaseCardEntity.CARD_TYPE_BANNER_COURSE_INFO);
////        ImageEntity imageEntity = new ImageEntity(BaseCardEntity.CARD_TYPE_BANNER_COURSE_INFO);
////        List<String> stringList = new ArrayList<>();
////        String [] strings = {"http://cdn2.kobiko.cn/./Uploads/2016-12-03/th_584281b63caa3.jpg",
////                "http://cdn1.kobiko.cn/./Uploads/2016-12-03/th_5842775fe7b5a.jpg",
////                "http://cdn1.kobiko.cn/./Uploads/2016-12-03/th_5842775fe7b5a.jpg"};
////        for(int i=0;i<strings.length;i++){
////            stringList.add(strings[i]);
////        }
////        imageEntity.setImages(stringList);
//        /**
//         * 假数据 课时选择
//         * */
//        CourseCardEntity courseCardEntity = new CourseCardEntity();
//        List<String> couresList = new ArrayList<>();
//        couresList.add("官方认证");
//        couresList.add("免费试听");
//        couresList.add("7天退款");
//        couresList.add("信用卡分期");
//        courseCardEntity.setCourseTimeList(couresList);
//
//        lists.add(imageEntity);
//        BaseCardEntity baseCardEntity = new BaseCardEntity();
//        baseCardEntity.setCardType(BaseCardEntity.CARD_TYPE_COURSE_INFO_HEAD_VIEW);
//        lists.add(baseCardEntity);
//        lists.add(courseCardEntity);
//
//        //校区特色
//        BaseCardEntity featureEntity = new BaseCardEntity();
//        featureEntity.setCardType(BaseCardEntity.CARD_TYPE_SCHOOL_FEATURE_VIEW);
//        lists.add(featureEntity);
//
//        //游乐场数据
//        BaseCardEntity amusePark = new BaseCardEntity();
//        amusePark.setCardType(BaseCardEntity.CARD_TYPE_NEARBY_AMUSE_PARK_VIEW);
//        lists.add(amusePark);
//        //课程推荐模块
//        BaseCardEntity recommendView = new BaseCardEntity();
//        recommendView.setCardType(BaseCardEntity.CARD_TYPE_COURSE_RECOMMEND_CARD_VIEW);
//        lists.add(recommendView);
        /**
         * recyclerview绑定适配器
         * */
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
//        CardListAdapter adapter = new CardListAdapter(lists, mContext);
        reqData();
//        recyclerView.setAdapter(adapter);
    }

    private void reqData() {
        String url = UrlConst.COURSE_DETAIL_URL;
        Map<String,String> map =  new HashMap<>();
        map.put("goodsId",goodsId);
        LogUtil.d(TAG,"goodsId"+goodsId);
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
                    JSONObject shareObject = jsonObject.optJSONObject("share_info");
                    shareInfoEntity = new ShareInfoEntity(shareObject);
                    JSONArray cardListArray = jsonObject.optJSONArray("data");
                    JsonDataList<BaseCardEntity> result = (JsonDataList<BaseCardEntity>) parser.parse(cardListArray);
                    if(result.getErrorCode()== UrlConst.SUCCESS_CODE){
                        if(result.getList()!=null&&result.getList().size()>0){
                            update(result.getList());
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        },map);
    }

    private void update(ArrayList<BaseCardEntity> list) {
//        List<BaseCardEntity> lists = new ArrayList<>();
//        lists.add(lists.get(0));
        recyclerView.setAdapter(new CardListAdapter(list,mContext));
    }

    @Override
    public void onClick(View v) {
        if(v==backImage){
            finish();
        }else if (v == callPhoneLayout){
            DialogUtils.cellPhoneDialog(mContext,"010-56265474");
        }else if (v==shareIv){

            if(shareInfoEntity!=null) {
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
            }
        }else if (v==writeCommentLayout){
            Intent intent = new Intent(mContext,WriteCommentActivityNew.class);
            intent.putExtra(Const.GOODS_ID,goodsId);
            startActivity(intent);
        }else if (v == attentionIv){
            collectCourse();
        }
        }

    private void collectCourse() {
        String userId = null;
        if(SharedPrefUtil.getInstance().getSession()!=null){
            userId = SharedPrefUtil.getInstance().getSession().getUserId();
        }else{
            startActivity(new Intent(mContext,LoginActivity.class));
        }
        final Map<String,String> map = new HashMap();
        map.put("userId",userId);
        map.put("goodsId",goodsId);
        String url =UrlConst.COLLECT_COURSE_URL;
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UMShareAPI.get(this).release();
    }
}
