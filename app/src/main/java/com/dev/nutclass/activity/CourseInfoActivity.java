package com.dev.nutclass.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.dev.nutclass.R;
import com.dev.nutclass.adapter.CardListAdapter;
import com.dev.nutclass.entity.BaseCardEntity;
import com.dev.nutclass.entity.CourseCardEntity;
import com.dev.nutclass.utils.SnsUtil;

import java.util.ArrayList;
import java.util.List;

public class CourseInfoActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "CourseInfoActivity";
    private RecyclerView recyclerView;
    private RelativeLayout titleBar;
    private Context mContext;

    private ImageView shareIv;
    private LinearLayout callPhoneLayout;
    private ImageView backImage;
    private List<BaseCardEntity> lists;
    private int mDistanceY;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_info);
        mContext =CourseInfoActivity.this;
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
        recyclerViewScrollListener();
    }


    private void initListener() {
        backImage.setOnClickListener(this);
        callPhoneLayout.setOnClickListener(this);
        shareIv.setOnClickListener(this);
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
                } else {
                    //上述虽然判断了滑动距离与toolbar高度相等的情况，但是实际测试时发现，标题栏的背景色
                    //很少能达到完全不透明的情况，所以这里又判断了滑动距离大于toolbar高度的情况，
                    //将标题栏的颜色设置为完全不透明状态
                    titleBar.setBackgroundColor(getResources().getColor(R.color.color_white));
                }

            }
        });
    }

    private void initData() {
        lists = new ArrayList<>();
        /**
         * 假数据 bannerEntity
         * */
        BaseCardEntity imageEntity = new BaseCardEntity();
        imageEntity.setCardType(BaseCardEntity.CARD_TYPE_BANNER_COURSE_INFO);
//        ImageEntity imageEntity = new ImageEntity(BaseCardEntity.CARD_TYPE_BANNER_COURSE_INFO);
//        List<String> stringList = new ArrayList<>();
//        String [] strings = {"http://cdn2.kobiko.cn/./Uploads/2016-12-03/th_584281b63caa3.jpg",
//                "http://cdn1.kobiko.cn/./Uploads/2016-12-03/th_5842775fe7b5a.jpg",
//                "http://cdn1.kobiko.cn/./Uploads/2016-12-03/th_5842775fe7b5a.jpg"};
//        for(int i=0;i<strings.length;i++){
//            stringList.add(strings[i]);
//        }
//        imageEntity.setImages(stringList);
        /**
         * 假数据 课时选择
         * */
        CourseCardEntity courseCardEntity = new CourseCardEntity();
        List<String> couresList = new ArrayList<>();
        couresList.add("官方认证");
        couresList.add("免费试听");
        couresList.add("7天退款");
        couresList.add("信用卡分期");
        courseCardEntity.setCourseTimeList(couresList);

        lists.add(imageEntity);
        BaseCardEntity baseCardEntity = new BaseCardEntity();
        baseCardEntity.setCardType(BaseCardEntity.CARD_TYPE_COURSE_INFO_HEAD_VIEW);
        lists.add(baseCardEntity);
        lists.add(courseCardEntity);

        //校区特色
        BaseCardEntity featureEntity = new BaseCardEntity();
        featureEntity.setCardType(BaseCardEntity.CARD_TYPE_SCHOOL_FEATURE_VIEW);
        lists.add(featureEntity);

        //游乐场数据
        BaseCardEntity amusePark = new BaseCardEntity();
        amusePark.setCardType(BaseCardEntity.CARD_TYPE_NEARBY_AMUSE_PARK_VIEW);
        lists.add(amusePark);
        //课程推荐模块
        BaseCardEntity recommendView = new BaseCardEntity();
        recommendView.setCardType(BaseCardEntity.CARD_TYPE_COURSE_RECOMMEND_CARD_VIEW);
        lists.add(recommendView);
        /**
         * recyclerview绑定适配器
         * */
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        CardListAdapter adapter = new CardListAdapter(lists, mContext);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        if(v==backImage){
            finish();
        }else if (v == callPhoneLayout){
            callPhoneKobiko();
        }else if (v==shareIv){
            SnsUtil.getInstance(mContext).openShare((Activity) mContext);
        }
    }

    private void callPhoneKobiko() {

    }

}
