package com.dev.nutclass.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dev.nutclass.R;
import com.dev.nutclass.constants.Const;
import com.dev.nutclass.constants.UrlConst;
import com.dev.nutclass.entity.UserOrderDetailEntity;
import com.dev.nutclass.network.OkHttpClientManager;
import com.dev.nutclass.utils.GlideUtils;
import com.dev.nutclass.utils.LogUtil;
import com.dev.nutclass.view.MyPopupWindow;
import com.squareup.okhttp.Request;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class OrderInfoActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "OrderInfoActivity";
    private Context mContext;
    private TextView applyBackMoneyTv;
    private MyPopupWindow popupWindow;

    private ImageView goodsImage;
    private TextView goodsNameTv;
    private TextView shopCircleNameTv;
    private TextView cateNameTv;
    private TextView goodsPriceTv;
    private RelativeLayout writeCommentLayout;
    private TextView schoolNameTv;
    private TextView schoolAddrTv;
    private TextView distanceTv;
    private ImageView phoneIv;
    private TextView goodsAttrTv;
    private TextView shopPriceTv;
    private TextView promotionInfoTv;
    private TextView couponTv;
    private TextView totalTv;
    private TextView orderNumberTv;
    private TextView userPhoneTv;
    private TextView orderTimeTv;
    private TextView courseCodeTv;
    private String orderId;
    private LinearLayout backMoneyStatusLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_info);
        mContext = OrderInfoActivity.this;
        orderId = getIntent().getStringExtra(Const.ORDER_ID);
        LogUtil.d("===","orderID:"+orderId);
        initView();
        initData();
    }

    private void initView() {
        applyBackMoneyTv = (TextView) findViewById(R.id.tv_apply_back_money);
        goodsImage = (ImageView) findViewById(R.id.iv_goods_image);
        goodsNameTv = (TextView) findViewById(R.id.tv_goods_name);
        shopCircleNameTv = (TextView) findViewById(R.id.tv_shop_circle);
        cateNameTv = (TextView) findViewById(R.id.tv_cate_name);
        goodsPriceTv = (TextView) findViewById(R.id.tv_goods_price);
        writeCommentLayout = (RelativeLayout) findViewById(R.id.rl_write_comment);
        schoolNameTv = (TextView) findViewById(R.id.tv_school_name);
        schoolAddrTv = (TextView) findViewById(R.id.tv_school_addr);
        distanceTv = (TextView) findViewById(R.id.tv_distance);
        phoneIv = (ImageView) findViewById(R.id.iv_phone);
        goodsAttrTv = (TextView) findViewById(R.id.tv_goods_attr);
        shopPriceTv = (TextView) findViewById(R.id.tv_shop_money);
        promotionInfoTv = (TextView) findViewById(R.id.tv_promotion_info);
        couponTv = (TextView) findViewById(R.id.tv_coupon);
        totalTv = (TextView) findViewById(R.id.tv_total_price);
        orderNumberTv = (TextView) findViewById(R.id.tv_order_number);
        userPhoneTv = (TextView) findViewById(R.id.tv_user_phone);
        orderTimeTv = (TextView) findViewById(R.id.tv_order_time);
        courseCodeTv = (TextView) findViewById(R.id.tv_course_code);
        backMoneyStatusLayout = (LinearLayout) findViewById(R.id.ll_back_money_status);
        applyBackMoneyTv.setOnClickListener(this);
    }

    private void initData() {
        reqData();
    }

    private void reqData() {
        String url = UrlConst.USER_ORDER_DETAIL_URL;
        Map<String,String> map = new HashMap<>();
        map.put("orderId",orderId);
        OkHttpClientManager.postAsyn(url, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
                LogUtil.e(TAG, "error:" + e.getMessage());
            }

            @Override
            public void onResponse(String response) {
                LogUtil.d(TAG, "response:" + response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONObject dataJsonObject = jsonObject.optJSONObject("data");
                    if(jsonObject!=null){
                        UserOrderDetailEntity entity = new UserOrderDetailEntity(dataJsonObject,1);
                        LogUtil.d("===","entity:"+entity.getCateName());
                        update(entity);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

//                JsonDataList<BaseCardEntity> result = null;
//                UserOrderInfoParser parser = new UserOrderInfoParser();
//                try {
//                    result = (JsonDataList<BaseCardEntity>) parser.parse(response);
//                    if (result.getErrorCode() == UrlConst.SUCCESS_CODE) {
//                        LogUtil.d("===", "result:" + result.getList().size());
//                        UserOrderDetailEntity entity = (UserOrderDetailEntity) result.getList().get(0);
//                        update(entity);
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
            }
        },map);
    }

    private void update(UserOrderDetailEntity entity) {
        GlideUtils.loadImageView(mContext,entity.getGoodsImage(),goodsImage);
        goodsNameTv.setText(entity.getGoodsName());
        shopCircleNameTv.setText(entity.getShopCircleName());
        cateNameTv.setText(entity.getCateName());
        goodsPriceTv.setText(entity.getKbkMoney());
        schoolNameTv.setText(entity.getSchoolName());
        schoolAddrTv.setText(entity.getSchoolAddr());
        distanceTv.setText(entity.getDistance());
        goodsAttrTv.setText(entity.getGoodsAttr());
        shopPriceTv.setText(entity.getShopMoney());
        promotionInfoTv.setText(entity.getPromotionInfo());
        orderNumberTv.setText(entity.getOrderNumber());
        userPhoneTv.setText(entity.getUserPhone());
        orderTimeTv.setText(entity.getOrderTime());
        courseCodeTv.setText(entity.getCourseCode());
        if(entity.getPayStatus().equals("5")){
            backMoneyStatusLayout.setVisibility(View.VISIBLE);
            applyBackMoneyTv.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_apply_back_money:
                showPopupWindow();
                break;
        }
    }

    private void showPopupWindow() {
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.view_pop_apply_back_money, null);
        popupWindow = new MyPopupWindow(mContext, rootView);
        popupWindow.showAtLocation(applyBackMoneyTv, Gravity.CENTER, 0, 0);
    }
}
