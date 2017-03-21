package com.dev.nutclass.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dev.nutclass.R;
import com.dev.nutclass.entity.CourseCommentListEntity;

/**
 * Created by Administrator on 2017/3/11.
 */
public class CommentReplyView extends RelativeLayout {
    private Context mContext;
    private static final String TAG = "CommentReplyView";
    private TextView userOneTv,userTwoTv,commentInfoTv,commentDate,replyTv;
    private LinearLayout replyUserLayout;
    public CommentReplyView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public CommentReplyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext =context;
        initView();
    }

    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.view_comment_reply,this);
        userOneTv = (TextView) findViewById(R.id.user_one);
        userTwoTv = (TextView) findViewById(R.id.tv_user_two);
        commentInfoTv = (TextView) findViewById(R.id.tv_comment_info);
        commentDate = (TextView) findViewById(R.id.tv_comment_date);
        replyTv = (TextView) findViewById(R.id.tv_reply);
        replyUserLayout = (LinearLayout) findViewById(R.id.ll_reply_user);
    }
    public void updateView(CourseCommentListEntity.CommentReplyEntity entity){
        userOneTv.setText(entity.getUserName());
        commentDate.setText(entity.getCommentDate());
        if(!TextUtils.isEmpty(entity.getReplyUserName())){
            replyUserLayout.setVisibility(VISIBLE);
            userTwoTv.setText(entity.getReplyUserName()+":");
            int length = entity.getReplyUserName().length()+3;
            String caseString = "";
            for(int i = 0;i<length;i++){
                caseString+="\u3000";
            }
            commentInfoTv.setText(caseString+entity.getComentInfo());
        }
    }
}
