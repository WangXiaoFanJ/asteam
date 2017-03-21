package com.dev.nutclass.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dev.nutclass.R;
import com.dev.nutclass.entity.CourseListCardEntity;
import com.dev.nutclass.entity.LookHistoryEntity;

import java.util.List;

/**
 * Created by Administrator on 2017/3/7.
 */
public class LookHistoryView extends RelativeLayout{
    private Context mContext;
    private TextView lookDateTx;
    private LinearLayout containerLayout;
    private CheckBox checkBoxTotal;
    public LookHistoryView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public LookHistoryView(Context context, AttributeSet attrs) {
        super(context, attrs);
       mContext = context;
        initView();
    }

    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.view_look_history,this);
        lookDateTx = (TextView) this.findViewById(R.id.tv_history_date);
        containerLayout = (LinearLayout) this.findViewById(R.id.ll_container);
        checkBoxTotal = (CheckBox) this.findViewById(R.id.iv_check_box);
    }
    public void updateView(LookHistoryEntity entity,boolean isEdit){
        if(isEdit){
            checkBoxTotal.setVisibility(View.VISIBLE);
        }else{
            checkBoxTotal.setVisibility(View.GONE);
        }
        lookDateTx.setText(entity.getLogDate());
        List<CourseListCardEntity> list = entity.getList();
        if(list!=null&&list.size()>0){
            for(int i=0;i<list.size();i++){
                AmusementParkCardView cellView = new AmusementParkCardView(mContext);
                cellView.updateView(entity.getList().get(i),isEdit);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                containerLayout.addView(cellView,params);
            }
        }
    }
}
