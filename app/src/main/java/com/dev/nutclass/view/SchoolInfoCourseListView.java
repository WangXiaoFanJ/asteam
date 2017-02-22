package com.dev.nutclass.view;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dev.nutclass.R;
import com.dev.nutclass.activity.PublicListActivity;
import com.dev.nutclass.constants.Const;
import com.dev.nutclass.entity.SchoolDetailToCourseEntity;
import com.dev.nutclass.entity.SchoolToCourseCardEntity;
import com.dev.nutclass.utils.GlideUtils;

/**
 * Created by Administrator on 2017/1/9.
 */
public class SchoolInfoCourseListView extends RelativeLayout {
    private static final String TAG = "SchoolInfoCourseListView";
    private Context mContext;
    private RelativeLayout findMoreLayout;
    private ImageView titleIconIv;
    private TextView titleInfoTv;
    private TextView titleMoreTv;
    private LinearLayout containerLayout;
    public SchoolInfoCourseListView(Context context) {
        super(context);
        this.mContext = context;
        initView();
    }

    public SchoolInfoCourseListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView();
    }

    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.view_school_info_course_list,this);
        findMoreLayout = (RelativeLayout) this.findViewById(R.id.rl_find_more);
        titleIconIv = (ImageView) this.findViewById(R.id.iv_title_icon);
        titleInfoTv = (TextView) this.findViewById(R.id.tv_title_info);
        titleMoreTv = (TextView) this.findViewById(R.id.tv_title_find_more);
        containerLayout = (LinearLayout) this.findViewById(R.id.ll_container);
        findMoreLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, PublicListActivity.class);
                intent.putExtra(Const.KEY_TYPE,Const.TYPE_FROM_SCHOLL_MOR_COURSE);
                mContext.startActivity(intent);
            }
        });
    }
    public void updateView(SchoolDetailToCourseEntity entity){
        containerLayout.removeAllViews();
        GlideUtils.loadImageView(mContext,entity.getIcon(),titleIconIv);
        titleInfoTv.setText(entity.getInfo());
        titleMoreTv.setText(entity.getMoreString());
        for(int i=0;i<entity.getCourseEntityLists().size();i++){
            SchoolCommonCourseView cellView = new SchoolCommonCourseView(mContext);
            cellView.updateView(entity.getCourseEntityLists().get(i));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT
            );
            containerLayout.addView(cellView,params);
        }

    }
}
