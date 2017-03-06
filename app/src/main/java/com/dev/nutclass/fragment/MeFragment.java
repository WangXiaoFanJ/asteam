package com.dev.nutclass.fragment;


import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dev.nutclass.R;
import com.dev.nutclass.activity.DiscountCouponActivity;
import com.dev.nutclass.activity.LoginActivity;
import com.dev.nutclass.activity.MyCollectActivity;
import com.dev.nutclass.activity.UserInfoActivity;
import com.dev.nutclass.activity.UserOrderActivity;
import com.dev.nutclass.activity.WriteCommentActivity;
import com.dev.nutclass.constants.Const;
import com.dev.nutclass.entity.UserInfoEntity;
import com.dev.nutclass.network.OkHttpClientManager;
import com.dev.nutclass.utils.GlideUtils;
import com.dev.nutclass.utils.LogUtil;
import com.dev.nutclass.utils.SharedPrefUtil;
import com.squareup.okhttp.Request;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends BaseFragment implements View.OnClickListener {
    private static final String TAG = "MeFragment";
    private Context mContext;
    private ImageView userHeadIv;
    private LinearLayout waitPayLayout;
    private LinearLayout waitUseLayout;
    private LinearLayout waitCommentLayout;
    private LinearLayout backMoneyLayout;
    private RelativeLayout couponLayout;
    private RelativeLayout collectLayout;
    private RelativeLayout dayTaskLayout;
    private TextView loginTv;
    private TextView userNameTv;
    private TextView userInfoTv;
    private LinearLayout userNameLayout;
    private boolean isLogin = false;

    public MeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mContext = getActivity();
        View view = inflater.inflate(R.layout.fragment_me, container, false);
        couponLayout = (RelativeLayout) view.findViewById(R.id.rl_coupon);
        userHeadIv = (ImageView) view.findViewById(R.id.iv_user_head);
        waitPayLayout = (LinearLayout) view.findViewById(R.id.ll_order_wait_pay);
        waitUseLayout = (LinearLayout) view.findViewById(R.id.ll_order_wait_use);
        waitCommentLayout = (LinearLayout) view.findViewById(R.id.ll_order_wait_comment);
        backMoneyLayout = (LinearLayout) view.findViewById(R.id.ll_order_back_money);
        collectLayout = (RelativeLayout) view.findViewById(R.id.rl_collect);
        dayTaskLayout = (RelativeLayout) view.findViewById(R.id.rl_day_task);
        loginTv = (TextView) view.findViewById(R.id.tv_login);
        userNameLayout = (LinearLayout) view.findViewById(R.id.ll_user_name_and_info);
        userNameTv = (TextView) view.findViewById(R.id.tv_user_name);
        userInfoTv = (TextView) view.findViewById(R.id.tv_user_info);

        couponLayout.setOnClickListener(this);
        userHeadIv.setOnClickListener(this);
        waitPayLayout.setOnClickListener(this);
        waitUseLayout.setOnClickListener(this);
        waitCommentLayout.setOnClickListener(this);
        backMoneyLayout.setOnClickListener(this);
        collectLayout.setOnClickListener(this);
        dayTaskLayout.setOnClickListener(this);
        loginTv.setOnClickListener(this);

        String[] filters = {Const.ACTION_BROADCAST_LOGIN_SUCC,
                Const.ACTION_BROADCAST_UPDATA_USER_INFO
                ,Const.ACTION_BROADCAST_EXIT_SUCC};
        registerReceiver(filters);

        isLogin = SharedPrefUtil.getInstance().isLogin();
        LogUtil.d(TAG, "isLogin" + isLogin);
        if (isLogin) {
            setUserInfo();
        } else {
            loginTv.setVisibility(View.VISIBLE);
            userNameLayout.setVisibility(View.GONE);
        }
//        openCommentTv = (TextView) view.findViewById(R.id.tv_open_comment);
//        openCommentTv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mContext.startActivity(new Intent(mContext, WriteCommentActivity.class));
//            }
//        });
        return view;
    }

    private void initView() {
        userNameTv.setText(SharedPrefUtil.getInstance().getMobile());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_order_wait_pay:
                mContext.startActivity(new Intent(mContext,UserOrderActivity.class)
                        .putExtra(Const.USER_ORDER_TYPE,"2"));
                break;
            case R.id.ll_order_wait_use:
                mContext.startActivity(new Intent(mContext,UserOrderActivity.class)
                        .putExtra(Const.USER_ORDER_TYPE,"3"));
                break;
            case R.id.ll_order_wait_comment:
                mContext.startActivity(new Intent(mContext,UserOrderActivity.class)
                        .putExtra(Const.USER_ORDER_TYPE,"4"));
                break;
            case R.id.ll_order_back_money:
                mContext.startActivity(new Intent(mContext,UserOrderActivity.class)
                        .putExtra(Const.USER_ORDER_TYPE,"5"));
                break;
            case R.id.rl_coupon:
                mContext.startActivity(new Intent(mContext, DiscountCouponActivity.class));
                break;
            case R.id.iv_user_head:
                mContext.startActivity(new Intent(mContext, UserInfoActivity.class));
                break;
            case R.id.rl_collect:
                mContext.startActivity(new Intent(mContext, MyCollectActivity.class));
                break;
            case R.id.rl_day_task:
//                loginTv.setVisibility(View.VISIBLE);
//                userNameLayout.setVisibility(View.GONE);
                break;
            case R.id.tv_login:
                mContext.startActivity(new Intent(mContext, LoginActivity.class));
                break;
        }
    }

    //更新用户信息
    @Override
    public void updateUserInfo(boolean isBackGround) {
        super.updateUserInfo(isBackGround);
        setUserInfo();
    }
    //退出登录


    @Override
    public void exitLogin(boolean isExitLogin) {
        super.exitLogin(isExitLogin);
        GlideUtils.loadImageView(mContext,"",userHeadIv,0);
        userNameTv.setText("");
        loginTv.setVisibility(View.VISIBLE);
        userNameLayout.setVisibility(View.GONE);
    }

    private void setUserInfo() {
        loginTv.setVisibility(View.GONE);
        userNameLayout.setVisibility(View.VISIBLE);
        UserInfoEntity entity = SharedPrefUtil.getInstance().getSession();
        userNameTv.setText(entity.getUserName());
        GlideUtils.loadImageView(mContext, entity.getHeadIcon(), userHeadIv, 0);
        userInfoTv.setText(entity.getToken());
    }
}
