package com.dev.nutclass.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.dev.nutclass.R;
import com.dev.nutclass.entity.CourseCommentEntity;

/**
 * Created by Administrator on 2017/2/22.
 */
public class CommentForCourseDetailView extends RelativeLayout {
    private Context mContext;
    private LinearLayout containerLayout;
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
    }
    public void updateView(CourseCommentEntity entity){
        containerLayout.removeAllViews();
        if(entity.getCourseCommentCardEntityList()!=null&&entity.getCourseCommentCardEntityList().size()>0){
            for(int i=0;i<entity.getCourseCommentCardEntityList().size();i++){
                CourseCommentView commentView = new CourseCommentView(mContext);
                commentView.updateView(entity.getCourseCommentCardEntityList().get(i));
                containerLayout.addView(commentView);
            }

        }

    }

}
