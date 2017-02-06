package com.dev.nutclass.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextClock;
import android.widget.TextView;

import com.dev.nutclass.R;
import com.dev.nutclass.activity.SchoolInfoActivity;
import com.dev.nutclass.adapter.CardListAdapter;
import com.dev.nutclass.entity.BaseCardEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SchoolListFragment extends BaseFragment {
    private RecyclerView recyclerView;
    private Context mContext;
    private List<BaseCardEntity> list;
    public SchoolListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_school_list, container, false);
        mContext = getActivity();
        list = new ArrayList<>();
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

        BaseCardEntity entity = new BaseCardEntity();
        entity.setCardType(BaseCardEntity.CARD_TYPE_SHCOOL_CARD_VIEW);
        for(int i =0;i<10;i++){
            list.add(entity);
        }
        recyclerView.setAdapter(new CardListAdapter(list,mContext));
        return view;
    }

}
