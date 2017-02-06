package com.dev.nutclass.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.dev.nutclass.R;
import com.dev.nutclass.adapter.BigPicPagerAdapter;
import com.dev.nutclass.entity.BannerCardEntity;
import com.dev.nutclass.entity.BaseCardEntity;
import com.dev.nutclass.entity.ImageEntity;
import com.dev.nutclass.utils.DensityUtil;



import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/29.
 */
public class BannerCardView extends RelativeLayout {
    private static  final  String TAG = "BannerCardView";
    private Context mContext;
    private ViewPager viewPager;
    private BigImgView singleImageView;
//    private CirclePageIndicator pageIndicator;
    private LinearLayout pageIndicatorLayout;
    BigPicPagerAdapter adapter;

    private int perPosition = 0;
    private int dotTotal = 0;

    private static final int OFFSET_LEFT=1000;
    private int curPosition = 1;
    private final static int SCROLL_WHAT = 0;
    private boolean isAutoScroll = true;
    private int interval = 3000;
    private boolean isStopByTouch = false;
    private class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SCROLL_WHAT:
                    viewPager.setCurrentItem(viewPager.getCurrentItem() +1);
                    sendScrollMessage(interval);
                default:
                    break;
            }
        }
    }
    private   Handler handler = new MyHandler();
    public BannerCardView(Context context,int type) {
        super(context);
        mContext = context;
        initView(type);
    }

    public BannerCardView(Context context, AttributeSet attrs,int type) {
        super(context, attrs);
        mContext = context;
        initView(type);
    }

    private void initView(int type) {
        if(type==1){
            LayoutInflater.from(mContext).inflate(R.layout.banner_card_view,this);
        }else if (type== BaseCardEntity.CARD_TYPE_BANNER_COURSE_INFO){
            LayoutInflater.from(mContext).inflate(R.layout.banner_card_view_course_info,this);
        }else if(type==2){
            LayoutInflater.from(mContext).inflate(R.layout.card_banner_76,this);
        }
        viewPager = (ViewPager) this.findViewById(R.id.view_pager_banner);
        singleImageView = (BigImgView) this.findViewById(R.id.iv_single);
        pageIndicatorLayout = (LinearLayout) this.findViewById(R.id.ll_pager_indicator);
    }
    public void updateView() {
        List<String> imgList = new ArrayList<>();
        String [] strings = {"http://cdn2.kobiko.cn/./Uploads/2016-12-03/th_584281b63caa3.jpg",
                "http://cdn1.kobiko.cn/./Uploads/2016-12-03/th_5842775fe7b5a.jpg",
                "http://cdn1.kobiko.cn/./Uploads/2016-12-03/th_5842775fe7b5a.jpg"};
        for(int i=0;i<strings.length;i++){
            imgList.add(strings[i]);
        }

            if (imgList == null || imgList.size() < 0) {
                return;
            }
            if (imgList.size() == 1) {
                singleImageView.setVisibility(View.VISIBLE);
                viewPager.setVisibility(View.GONE);
                pageIndicatorLayout.setVisibility(View.GONE);
                singleImageView.updateView(imgList.get(0));
            } else {
                dotTotal = imgList.size();
                singleImageView.setVisibility(View.GONE);
                viewPager.setVisibility(View.VISIBLE);
                pageIndicatorLayout.setVisibility(View.VISIBLE);
                pageIndicatorLayout.removeAllViews();
                if (imgList.size() == 2) {
                    imgList.addAll(imgList);
                }
                curPosition = 0 % imgList.size();
                List<BigImgView> bigImageViewList = new ArrayList<>();
                for (int i = 0; i < imgList.size(); i++) {
                    BigImgView bigImageView = new BigImgView(mContext);
                    bigImageView.updateView(imgList.get(i));
                    bigImageViewList.add(bigImageView);
                }
                for (int i = 0; i < dotTotal; i++) {
                    //添加圆点
                    View dot = new View(mContext);
                    int width = DensityUtil.dip2px(mContext, 6);
                    int marginLeft = DensityUtil.dip2px(mContext, 8);
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(width, width);
                    lp.setMargins(marginLeft, 0, 0, 0);
                    dot.setBackgroundResource(R.drawable.pager_dot_selector);
                    dot.setLayoutParams(lp);
                    dot.setEnabled(false);
                    pageIndicatorLayout.addView(dot);
                }
                adapter = new BigPicPagerAdapter(bigImageViewList, mContext);
                viewPager.setAdapter(adapter);
                pageIndicatorLayout.getChildAt(0).setEnabled(true);
                viewPager.setCurrentItem(0 + imgList.size() * OFFSET_LEFT);
                startAutoScroll();
                viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {
                        pageIndicatorLayout.getChildAt(perPosition).setEnabled(false);
                        pageIndicatorLayout.getChildAt(position % dotTotal).setEnabled(true);
                        perPosition = position % dotTotal;
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });
            }
    }
    //viewPager自动轮播代码
    public void startAutoScroll() {
        isAutoScroll = true;
        sendScrollMessage(interval);
        viewPager.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                {
                    if (event.getAction() == MotionEvent.ACTION_MOVE && isAutoScroll) {
                        isStopByTouch = true;
                        stopAutoScroll();
                    } else if (event.getAction() == MotionEvent.ACTION_UP && isStopByTouch) {
                        startAutoScroll();
                    }
                    viewPager.getParent().requestDisallowInterceptTouchEvent(true);
                    return onTouchEvent(event);

                }
            }
        });
    }
    private void sendScrollMessage(long delayTimeInMills) {
        /** remove messages before, keeps one message is running at most **/
        handler.removeMessages(SCROLL_WHAT);
        handler.sendEmptyMessageDelayed(SCROLL_WHAT, delayTimeInMills);
    }
//        int currentItem = viewPager.getCurrentItem();
//        int totalCount = 0;
//        if (adapter == null || (totalCount = adapter.getCount()) <= 1) {
//            return;
//        }
//
//        int nextItem = (direction == LEFT) ? --currentItem : ++currentItem;
//        if (nextItem < 0) {
//            if (isCycle) {
//                viewPager.setCurrentItem(totalCount - 1, isBorderAnimation);
//            }
//        } else if (nextItem == totalCount) {
//            if (isCycle) {
//                viewPager.setCurrentItem(0, isBorderAnimation);
//            }
//        } else {
//            viewPager.setCurrentItem(nextItem, true);
//        }
    public void stopAutoScroll() {
        isAutoScroll = false;
        handler.removeMessages(SCROLL_WHAT);
    }
}
