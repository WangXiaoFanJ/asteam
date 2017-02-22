package com.dev.nutclass.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dev.nutclass.R;
import com.dev.nutclass.activity.DiscountCouponActivity;
import com.dev.nutclass.activity.UserInfoActivity;
import com.dev.nutclass.activity.UserOrderActivity;
import com.dev.nutclass.activity.WriteCommentActivity;
import com.dev.nutclass.network.OkHttpClientManager;
import com.squareup.okhttp.Request;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends BaseFragment {
    private static final String TAG = "MeFragment";
    private Context mContext;
    private TextView openUserTv;
    private TextView openCommentTv;
    private TextView openOrderTv;
    private TextView openDiscountCouponTv;
    private TextView testHttpsTv;
    public MeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mContext = getActivity();
        View view = inflater.inflate(R.layout.fragment_me, container, false);
        openUserTv = (TextView) view.findViewById(R.id.tv_open_user_info);
        openCommentTv = (TextView) view.findViewById(R.id.tv_open_comment);
        openOrderTv = (TextView) view.findViewById(R.id.tv_open_order);
        openDiscountCouponTv = (TextView) view.findViewById(R.id.tv_open_discount_coupon);
        testHttpsTv = (TextView) view.findViewById(R.id.tv_test_https);
        openUserTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext, UserInfoActivity.class));
            }
        });
        openCommentTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext, WriteCommentActivity.class));
            }
        });
        openOrderTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext, UserOrderActivity.class));
            }
        });
        openDiscountCouponTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext, DiscountCouponActivity.class));
            }
        });
        testHttpsTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpClientManager.getAsyn("https://kyfw.12306.cn/otn/", new OkHttpClientManager.ResultCallback<String>() {
                    @Override
                    public void onError(Request request, Exception e) {
                        Log.d("===","error:"+e.getMessage());
                    }

                    @Override
                    public void onResponse(String response) {
                        Log.d("===","response:"+response.toString());
                    }

                });
            }
        });
        return view;
    }

}
