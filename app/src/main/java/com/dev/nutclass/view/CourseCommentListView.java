package com.dev.nutclass.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.dev.nutclass.R;
import com.dev.nutclass.entity.CourseCommentListEntity;
import com.dev.nutclass.utils.DensityUtil;

/**
 * Created by Administrator on 2017/3/11.
 */
public class CourseCommentListView extends RelativeLayout {
    private Context mContext;
    private static final String TAG = "CourseCommentListView";
    private LinearLayout commentLayout;
    private LinearLayout replyLayout,goodsInfoLayout;
    private RelativeLayout lookMoreDetailLayout;
    private View lineView;
    public CourseCommentListView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public CourseCommentListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.view_course_comment_list,this);
        commentLayout = (LinearLayout) this.findViewById(R.id.ll_container_comment);
        replyLayout = (LinearLayout) this.findViewById(R.id.ll_container_reply);
        lookMoreDetailLayout = (RelativeLayout) this.findViewById(R.id.rl_look_more);
        lineView = this.findViewById(R.id.line_view);
    }
    public void updateView(CourseCommentListEntity entity){
        commentLayout.removeAllViews();
        replyLayout.removeAllViews();
        int type=1;
        CourseCommentView commentView = new CourseCommentView(mContext);
        commentView.updateView(entity.getCourseCommentCardEntity(),2);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT
        );
        commentLayout.addView(commentView,params);
        /**
         * 如果商品信息存在时，渲染 。  设置商品存在时标记type=1评价列表页 type=2评价详情页面
         * */
        if(entity.getGoodsInfo()!=null){
            type = 2;
            goodsInfoLayout = (LinearLayout) commentView.findViewById(R.id.ll_container_goods_info);
            goodsInfoLayout.setVisibility(View.VISIBLE);
            CourseCardFroCommentDetailView courseView = new CourseCardFroCommentDetailView(mContext);
            courseView.updateView(entity.getGoodsInfo());
            LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT
            );
            goodsInfoLayout.addView(courseView,params1);
        }else {
            type=1;
        }

        if(entity.getReplyList()!=null&&entity.getReplyList().size()>0){
            if(type==1){
                if(entity.getReplyList().size()>2){
                    for(int i=0;i<2;i++){
                        CommentReplyView replyView = new CommentReplyView(mContext);
                        replyView.updateView(entity.getReplyList().get(i));
                        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT
                        );
                        replyLayout.addView(replyView,param);
                    }
                }else if(0<entity.getReplyList().size()){
                    for(int i=0;i<entity.getReplyList().size();i++){
                        CommentReplyView replyView = new CommentReplyView(mContext);
                        replyView.updateView(entity.getReplyList().get(i));
                        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT
                        );
                        replyLayout.addView(replyView,param);
                    }
                    lookMoreDetailLayout.setVisibility(View.GONE);
                }
            }else if (type==2){
                for(int i=0;i<entity.getReplyList().size();i++){
                    CommentReplyView replyView = new CommentReplyView(mContext);
                    replyView.updateView(entity.getReplyList().get(i));
                    LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT
                    );
                    replyLayout.addView(replyView,param);
                }
                lookMoreDetailLayout.setVisibility(View.GONE);
                lineView.setVisibility(View.GONE);
            }

        }
    }
}
