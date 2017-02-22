package com.dev.nutclass.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.dev.nutclass.R;
import com.dev.nutclass.constants.Const;
import com.dev.nutclass.constants.UrlConst;
import com.dev.nutclass.entity.BaseCardEntity;
import com.dev.nutclass.entity.JsonDataList;
import com.dev.nutclass.entity.UserOrderDetailEntity;
import com.dev.nutclass.network.OkHttpClientManager;
import com.dev.nutclass.parser.UserOrderInfoParser;
import com.dev.nutclass.utils.GlideUtils;
import com.dev.nutclass.utils.LogUtil;
import com.squareup.okhttp.Request;

import org.json.JSONException;

public class WaitPayOrderInfoActivity extends BaseActivity {
    private static final String TAG = "WaitPayOrderInfoActivity";
    private Context mContext;
    private ImageView schoolImageIv;
    private TextView goodsNameTv;
    private TextView shopCircleTv;
    private TextView goodsTypeTv;
    private TextView schoolNameTv;
    private TextView schoolAddrTv;
    private TextView goodsAttrTv;
    private TextView goodsPriceTv;
    private TextView promotionInfoTv;
    private TextView couponTv;
    private TextView totalPriceTv;
    private TextView userPhoneTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait_pay_order_info);
        mContext = WaitPayOrderInfoActivity.this;
        initView();
        initData();
    }

    private void initData() {
        reqData();
    }


    private void initView() {
        schoolImageIv = (ImageView) findViewById(R.id.iv_school_image);
        goodsNameTv = (TextView) findViewById(R.id.tv_goods_name);
        shopCircleTv = (TextView) findViewById(R.id.tv_shop_circle);
        goodsTypeTv = (TextView) findViewById(R.id.tv_goods_type);
        schoolNameTv = (TextView) findViewById(R.id.tv_school_name);
        schoolAddrTv = (TextView) findViewById(R.id.tv_school_addr);
        goodsAttrTv = (TextView) findViewById(R.id.tv_goods_attr);
        goodsPriceTv = (TextView) findViewById(R.id.tv_goods_price);
        promotionInfoTv = (TextView) findViewById(R.id.tv_promotion_info);
        couponTv = (TextView) findViewById(R.id.tv_coupon_name);
        totalPriceTv = (TextView) findViewById(R.id.tv_total_price);
        userPhoneTv = (TextView) findViewById(R.id.tv_user_phone);
    }

    private void reqData() {
        String url = "http://dev.kobiko.cn/api/index/confirmOrderList";
        OkHttpClientManager.getAsyn(url, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
                LogUtil.e(TAG,"error:"+e.getMessage());
            }

            @Override
            public void onResponse(String response) {
                LogUtil.d(TAG,"response:"+response);
                JsonDataList<BaseCardEntity> result = null;
                UserOrderInfoParser parser = new UserOrderInfoParser();
                try {
                    result= (JsonDataList<BaseCardEntity>) parser.parse(response);
                    if(result.getErrorCode()== UrlConst.SUCCESS_CODE){
                        LogUtil.d("===","result:"+result.getList().size());
                        UserOrderDetailEntity entity = (UserOrderDetailEntity) result.getList().get(0);
                        update(entity);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void update(UserOrderDetailEntity entity) {
        goodsNameTv.setText(entity.getGoodsName());
        GlideUtils.loadImageView(mContext,entity.getGoodsImage(),schoolImageIv);
        shopCircleTv.setText(entity.getShopCircleName());
        goodsTypeTv.setText(entity.getCateName());
        schoolNameTv.setText(entity.getSchoolName());
        schoolAddrTv.setText(entity.getSchoolAddr());
        goodsAttrTv.setText(entity.getGoodsAttr());
        goodsPriceTv.setText(entity.getGoodsPrice());
        promotionInfoTv.setText(entity.getPromotionInfo());
        totalPriceTv.setText(entity.getWaitPayMoney());
        userPhoneTv.setText(entity.getUserPhone());
    }
}
