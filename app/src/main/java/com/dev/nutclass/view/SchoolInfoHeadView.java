package com.dev.nutclass.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dev.nutclass.R;
import com.dev.nutclass.entity.SchoolDetailHeadEntity;
import com.dev.nutclass.utils.GlideUtils;
import com.dev.nutclass.utils.LogUtil;

import org.w3c.dom.Text;

/**
 * Created by Administrator on 2017/1/8.
 */
public class SchoolInfoHeadView extends RelativeLayout {
    private static final String TAG = "SchoolInfoHeadView";
    private Context mContext;
    private TextView schoolNameTv;
    private TextView cateNameTv;
    private TextView interestedNumTv;
    private TextView commentNumTv;
    private TextView schoolAddrTv;
    private TextView distanceTv;
    private TextView isNearbyTv;

    private LinearLayout imageLayout;
    public SchoolInfoHeadView(Context context) {
        super(context);
        this.mContext = context;
        initView();
    }

    public SchoolInfoHeadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext =context;
        initView();
    }

    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.view_head_school_view,this);
        schoolNameTv = (TextView) this.findViewById(R.id.tv_school_name);
        cateNameTv = (TextView) this.findViewById(R.id.tv_cate_name);
        interestedNumTv = (TextView) this.findViewById(R.id.tv_interest_num);
        commentNumTv = (TextView) this.findViewById(R.id.tv_comment_num);
        schoolAddrTv = (TextView) this.findViewById(R.id.tv_school_addr);
        distanceTv = (TextView) this.findViewById(R.id.tv_distance);
//        isNearbyTv = (TextView) this.findViewById(R.id.tv_nearby);
        imageLayout = (LinearLayout) this.findViewById(R.id.ll_image_container);
    }
    public void updateView(SchoolDetailHeadEntity entity){
        LogUtil.d("===","entity:"+entity.getCateName());
        schoolNameTv.setText(entity.getSchoolName());
        cateNameTv.setText(entity.getCateName());
        interestedNumTv.setText(entity.getInterestedNum());
        commentNumTv.setText(entity.getCommentNum());
        schoolAddrTv.setText(entity.getSchoolAddr());
        distanceTv.setText(entity.getDistance());
        if(entity.getIsNearby()!=null){
//            isNearbyTv.setText(entity.getIsNearby());
        }
        if(entity.getSchoolImageList()!=null&&entity.getSchoolImageList().size()>0) {
            for (int i = 0; i < entity.getSchoolImageList().size(); i++) {
                View cellView = LayoutInflater.from(mContext).inflate(R.layout.view_school_info_image_item,null);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
                ImageView imageView = (ImageView) cellView.findViewById(R.id.iv_head_image);
                GlideUtils.loadImageView(mContext,entity.getSchoolImageList().get(i),imageView);
                imageLayout.addView(cellView, params);
            }
        }
    }
}
