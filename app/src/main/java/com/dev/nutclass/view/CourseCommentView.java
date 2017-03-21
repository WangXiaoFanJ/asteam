package com.dev.nutclass.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dev.nutclass.R;
import com.dev.nutclass.activity.CommentDetailActivity;
import com.dev.nutclass.constants.Const;
import com.dev.nutclass.constants.UrlConst;
import com.dev.nutclass.entity.CourseCommentCardEntity;
import com.dev.nutclass.entity.CourseCommentEntity;
import com.dev.nutclass.network.OkHttpClientManager;
import com.dev.nutclass.parser.SimpleParser;
import com.dev.nutclass.utils.DensityUtil;
import com.dev.nutclass.utils.GlideUtils;
import com.dev.nutclass.utils.LogUtil;
import com.squareup.okhttp.Request;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/22.
 */
public class CourseCommentView extends RelativeLayout implements View.OnClickListener{
    private static final String TAG="CourseCommentView";
    private static final int TYPE_FORM_COURSE_DETAIL=1;
    private static final int TYPE_FROM_COMMENT_LIST = 2;
    private Context mContext;
    private ImageView userHeadImg;
    private TextView userNameTv;
    private TextView commentTimeTv;
    private TextView commentInfoTv;
    private LinearLayout containerLayout;
    private TextView brownNumTv;
    private TextView praiseNumTv;
    private TextView commentNumTv;
    private LinearLayout rootLayout;
    private String goodsId,commentId;
    private LinearLayout changeLayout;
    private LinearLayout commentLayout;
    private ImageView clickPraiseIv;
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
        rootLayout = (LinearLayout) this.findViewById(R.id.ll_rootLayout);
        userHeadImg = (ImageView) this.findViewById(R.id.iv_user_head_img);
        userNameTv = (TextView) this.findViewById(R.id.tv_user_name);
        commentTimeTv = (TextView) this.findViewById(R.id.tv_comment_time);
        containerLayout = (LinearLayout) this.findViewById(R.id.ll_container);
        brownNumTv = (TextView) this.findViewById(R.id.tv_brown_num);
        praiseNumTv = (TextView) this.findViewById(R.id.tv_praise_num);
        commentNumTv = (TextView) this.findViewById(R.id.tv_comment_num);
        commentInfoTv = (TextView) this.findViewById(R.id.tv_comment_info);
        commentLayout = (LinearLayout) this.findViewById(R.id.ll_comment_root);
        changeLayout = (LinearLayout) this.findViewById(R.id.ll_change_layout);
        clickPraiseIv = (ImageView) this.findViewById(R.id.iv_click_praise);
        commentLayout.setOnClickListener(this);
        clickPraiseIv.setOnClickListener(this);

    }
    public void updateView(CourseCommentCardEntity entity,int type){
        goodsId = entity.getGoodsId();
        commentId = entity.getCommentId();
        containerLayout.removeAllViews();
        GlideUtils.loadImageView(mContext,entity.getUserImg(),userHeadImg);
        userNameTv.setText(entity.getUserName());
        commentTimeTv.setText(entity.getCommentTime());
        brownNumTv.setText(entity.getBrowseNum());
        praiseNumTv.setText(entity.getFabulousNum());
        commentNumTv.setText(entity.getCommentNum());
        commentInfoTv.setText(entity.getCommentInfo());
        if(type==2){
            //对比课程详情页课程评论列表右移60dp
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(DensityUtil.dip2px(mContext,50),0,0,0);
            changeLayout.setLayoutParams(layoutParams);
        }

        //是否已经点赞
        if(entity.getIsPraise().equals("1")){
            LogUtil.d("===","ispraise:"+entity.getIsPraise());
            clickPraiseIv.setImageDrawable(getResources().getDrawable(R.drawable.praise_icon_light));
            clickPraiseIv.setEnabled(false);
        }
        //展示图片
        if(entity.getImageLists()!=null&&entity.getImageLists().size()>0){
            LinearLayout rootLayout = null;
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT
            );
            for(int i= 0;i<entity.getImageLists().size();i++){
                if(i%3==0){
                    rootLayout = new LinearLayout(mContext);
                    rootLayout.setOrientation(LinearLayout.HORIZONTAL);
                }
                View cellView = LayoutInflater.from(mContext).inflate(R.layout.view_comment_img,null);
                ImageView commentIv = (ImageView) cellView.findViewById(R.id.iv_comment_img);
                LinearLayout.LayoutParams cellParams = null;
                if(type==1){
                    cellParams= new LinearLayout.LayoutParams(
                            (DensityUtil.getDisplayWidth(mContext)-DensityUtil.dip2px(mContext,20))/3,
                            (DensityUtil.getDisplayWidth(mContext)-DensityUtil.dip2px(mContext,20))/3
                    );
                }else if(type==2){
                  cellParams = new LinearLayout.LayoutParams(
                          (DensityUtil.getDisplayWidth(mContext)-DensityUtil.dip2px(mContext,70))/3,
                          (DensityUtil.getDisplayWidth(mContext)-DensityUtil.dip2px(mContext,70))/3
                    );
                }
                GlideUtils.loadImageView(mContext,entity.getImageLists().get(i).getImagePath(),commentIv);
                rootLayout.addView(cellView,cellParams);
                if(i%3==2||i==entity.getImageLists().size()-1){
                    containerLayout.addView(rootLayout,params);
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        if(v==commentLayout){
            Intent intent = new Intent(mContext, CommentDetailActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString(Const.GOODS_ID,goodsId);
            bundle.putString(Const.COMMENT_ID,commentId);
            intent.putExtras(bundle);
            mContext.startActivity(intent);
        }else if (v==clickPraiseIv){
            clickPraiseIv.setImageDrawable(getResources().getDrawable(R.drawable.praise_icon_light));
            clickPraiseIv.setEnabled(false);
            clickPraise();
        }
    }

    /**点赞接口*/
    private void clickPraise() {
        String url= UrlConst.CLICK_PRAISE_URL;
        Map<String,String> map = new HashMap<>();
        map.put("commentId",commentId);
        map.put("userId","12");
        OkHttpClientManager.postAsyn(url, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
                LogUtil.d(TAG,"error:"+e.getMessage());
            }

            @Override
            public void onResponse(String response) {
                LogUtil.d(TAG,"response:"+response);
                try {
                    JSONObject jsonObj = new JSONObject(response);
                    String status = jsonObj.optString("status");
                    String message = jsonObj.optString("message");
                    LogUtil.d(TAG,"message:"+message);
                    if(status.equals("1")){
                        JSONObject jsonObject = jsonObj.optJSONObject("data");
                        String praiseNum = jsonObject.optString("praise_num");
                        praiseNumTv.setText(praiseNum);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        },map);
    }
}
