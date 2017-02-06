package com.dev.nutclass.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dev.nutclass.R;
import com.dev.nutclass.activity.UserInfoActivity;
import com.dev.nutclass.activity.UserOrderActivity;
import com.dev.nutclass.activity.WriteCommentActivity;

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
        return view;
    }

}
