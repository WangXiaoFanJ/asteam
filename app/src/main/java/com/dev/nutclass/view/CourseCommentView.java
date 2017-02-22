package com.dev.nutclass.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dev.nutclass.R;
import com.dev.nutclass.entity.CourseCommentEntity;
import com.dev.nutclass.utils.GlideUtils;

/**
 * Created by Administrator on 2017/2/22.
 */
public class CourseCommentView extends RelativeLayout {
    private Context mContext;
    private ImageView userHeadImg;
    private TextView userNameTv;
    private TextView commentTimeTv;
    private TextView commentInfoTv;
    private LinearLayout containerLayout;
    private TextView brownNumTv;
    private TextView praiseNumTv;
    private TextView commentNumTv;
    public CourseCommentView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public CourseCommentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.view_course_comment,this);
        userHeadImg = (ImageView) this.findViewById(R.id.iv_user_head_img);
        userNameTv = (TextView) this.findViewById(R.id.tv_user_name);
        commentTimeTv = (TextView) this.findViewById(R.id.tv_comment_time);
        containerLayout = (LinearLayout) this.findViewById(R.id.ll_container);
        brownNumTv = (TextView) this.findViewById(R.id.tv_brown_num);
        praiseNumTv = (TextView) this.findViewById(R.id.tv_praise_num);
        commentNumTv = (TextView) this.findViewById(R.id.tv_comment_num);
        commentInfoTv = (TextView) this.findViewById(R.id.tv_comment_info);
    }
    public void updateView(CourseCommentEntity.CourseCommentCardEntity entity){
        GlideUtils.loadImageView(mContext,entity.getUserImg(),userHeadImg);
        userNameTv.setText(entity.getUserName());
        commentTimeTv.setText(entity.getCommentTime());
        brownNumTv.setText(entity.getBrowseNum());
        praiseNumTv.setText(entity.getFabulousNum());
        commentNumTv.setText(entity.getCommentNum());
        commentInfoTv.setText(entity.getCommentInfo());
        if(entity.getCommentImgList()!=null&&entity.getCommentImgList().size()>0){
            for(int i= 0;i<entity.getCommentImgList().size();i++){
                View cellView = LayoutInflater.from(mContext).inflate(R.layout.view_comment_img,null);
                ImageView commentIv = (ImageView) cellView.findViewById(R.id.iv_comment_img);
                GlideUtils.loadImageView(mContext,entity.getCommentImgList().get(i),commentIv);
                containerLayout.addView(cellView);
            }
        }
    }
}
