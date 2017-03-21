package com.dev.nutclass.view;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.dev.nutclass.R;
import com.dev.nutclass.activity.CourseCommentListActivity;
import com.dev.nutclass.constants.Const;
import com.dev.nutclass.entity.CourseCommentEntity;

/**
 * Created by Administrator on 2017/2/22.
 */
public class CommentForCourseDetailView extends RelativeLayout implements View.OnClickListener{
    private Context mContext;
    private LinearLayout containerLayout;
    private RelativeLayout lookMoreLayout;
    private String goodsId;
    public CommentForCourseDetailView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public CommentForCourseDetailView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext =context;
        initView();
    }

    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.view_comment_in_course_detail,this);
        containerLayout = (LinearLayout) this.findViewById(R.id.ll_container);
        lookMoreLayout = (RelativeLayout) this.findViewById(R.id.rl_look_more);
        lookMoreLayout.setOnClickListener(this);
    }
    public void updateView(CourseCommentEntity entity){
        if(entity.getCourseCommentCardEntityList()!=null){
            goodsId = entity.getCourseCommentCardEntityList().get(0).getGoodsId();
        }
        containerLayout.removeAllViews();
        if(entity.getCourseCommentCardEntityList()!=null&&entity.getCourseCommentCardEntityList().size()>0){
            if(entity.getCourseCommentCardEntityList().size()<3){
                for(int i=0;i<entity.getCourseCommentCardEntityList().size();i++){
                    CourseCommentView commentView = new CourseCommentView(mContext);
                    commentView.updateView(entity.getCourseCommentCardEntityList().get(i),1);
                    containerLayout.addView(commentView);
                }
            }else{
                for(int i=0;i<2;i++){
                    CourseCommentView commentView = new CourseCommentView(mContext);
                    commentView.updateView(entity.getCourseCommentCardEntityList().get(i),1);
                    containerLayout.addView(commentView);
                }
            }


        }

    }

    @Override
    public void onClick(View v) {
        if(v==lookMoreLayout){
            Intent intent = new Intent(mContext, CourseCommentListActivity.class);
            intent.putExtra(Const.GOODS_ID,goodsId);
            mContext.startActivity(intent);
        }
    }
}
