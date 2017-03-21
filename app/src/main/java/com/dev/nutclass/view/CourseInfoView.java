package com.dev.nutclass.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.dev.nutclass.R;
import com.dev.nutclass.activity.PublicListActivity;
import com.dev.nutclass.constants.Const;
import com.dev.nutclass.entity.CourseInfoEntity;
import com.dev.nutclass.utils.DialogUtils;
import com.dev.nutclass.utils.GlideUtils;
import com.dev.nutclass.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

import cn.lankton.flowlayout.FlowLayout;

/**
 * Created by Administrator on 2017/1/5.
 */
public class CourseInfoView extends LinearLayout implements View.OnClickListener {
    private static final String TAG = "CourseInfoView";
    private CourseInfoEntity entity;
    private static final int POPUPWINDOW_TYPE_SERVICE = 1;
    private static final int POPUPWINDOW_TYPE_COURESE_TIME=2;
    private static final int POPUPWINDOW_TYPE_MINUS_ACTIVITY =3;
    private static final int POPUPWINDOW_TYPE_GIFT_ACTIVITY =4;
    private LinearLayout courseLableLayout;
    private RelativeLayout selectTimeLayout;
    private TextView confirmCourseTimeTv;
    private Context mContext;
    MyPopupWindow menuWindow;
    TextView textView;
    private TextView courseTimeTv;

    private String selectedCourseTime;
    List<String> courseList = new ArrayList<>();

    private TextView schoolNameTv;
    private TextView schoolAddrTv;
    private TextView distanceTv;
    private RelativeLayout minusLayout;
    private RelativeLayout giftLayout;
    private TextView minusTv;
    private TextView giftTv;
    private ImageView minusIv,cellPhoneIv;
    private ImageView giftIv;
    private RelativeLayout moreSchoolLayout;
    boolean isEdited = false;
    public CourseInfoView(Context context) {
        super(context);
        initView(context);
    }

    public CourseInfoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        mContext = context;
        LayoutInflater.from(context).inflate(R.layout.view_course_info, this);
        courseLableLayout = (LinearLayout) this.findViewById(R.id.ll_course_time);
        selectTimeLayout = (RelativeLayout) this.findViewById(R.id.rl_select_course_time);
        courseTimeTv = (TextView) this.findViewById(R.id.tv_select_course_time);

        schoolNameTv = (TextView) this.findViewById(R.id.tv_school_name);
        schoolAddrTv = (TextView) this.findViewById(R.id.tv_school_addr);
        distanceTv = (TextView) this.findViewById(R.id.tv_distance);
        minusLayout = (RelativeLayout) this.findViewById(R.id.rl_minus);
        giftLayout = (RelativeLayout) this.findViewById(R.id.rl_gift);
        minusTv = (TextView) this.findViewById(R.id.tv_minus_info);
        minusIv = (ImageView) this.findViewById(R.id.iv_icon_minus);
        giftTv = (TextView) this.findViewById(R.id.tv_gift_info);
        giftIv = (ImageView) this.findViewById(R.id.iv_icon_gift);
        cellPhoneIv = (ImageView) this.findViewById(R.id.iv_phone);
        moreSchoolLayout = (RelativeLayout) this.findViewById(R.id.rl_show_other_school);
        selectTimeLayout.setOnClickListener(this);
        courseLableLayout.setOnClickListener(this);
        moreSchoolLayout.setOnClickListener(this);
        cellPhoneIv.setOnClickListener(this);
    }

    public void updateView(CourseInfoEntity entity) {
        this.entity = entity;
        schoolNameTv.setText(entity.getSchoolInfoBean().getSchoolName());
        schoolAddrTv.setText(entity.getSchoolInfoBean().getSchoolAddr());
        distanceTv.setText(entity.getSchoolInfoBean().getDistance());
        if(entity.getPromotionInfoBean().getPromontionInfo()!=null){
            minusLayout.setVisibility(View.VISIBLE);
            minusLayout.setOnClickListener(this);
            minusTv.setText(entity.getPromotionInfoBean().getPromontionInfo());
            GlideUtils.loadImageView(mContext,entity.getPromotionInfoBean().getPromotionImg(),minusIv);
        }
        if(entity.getGiftInfoBean().getGiftInfo()!=null){
            giftLayout.setVisibility(View.VISIBLE);
            giftLayout.setOnClickListener(this);
            giftTv.setText(entity.getGiftInfoBean().getGiftInfo());
            GlideUtils.loadImageView(mContext,entity.getGiftInfoBean().getGiftImg(),giftIv);
        }
        if(entity.getKbkServiceList()!=null&&entity.getKbkServiceList().size()>0) {
            courseLableLayout.removeAllViews();
            for (int i = 0; i < entity.getKbkServiceList().size(); i++) {
                LinearLayout layout = (LinearLayout) LayoutInflater.from(mContext).inflate(
                        R.layout.view_course_num_item, null);
                textView = (TextView) layout.findViewById(R.id.tv_course_num);
                textView.setText((entity.getKbkServiceList().get(i)).getServiceInfo());
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
                        LayoutParams.WRAP_CONTENT);
                params.setMargins(getResources().getDimensionPixelSize(R.dimen.common_10), 0,
                        getResources().getDimensionPixelSize(R.dimen.common_20), 0);
                courseLableLayout.addView(layout, params);
            }
        }
//        for(int i=0;i<2;i++){
//            LinearLayout layout = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.view_course_promotion_info,null);
//            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
//            promotionLayout.addView(layout,params);
//        }
    }

    @Override
    public void onClick(View v) {
        if (v == selectTimeLayout) {
            showPopUpWindow(POPUPWINDOW_TYPE_COURESE_TIME);
        }else if(v==courseLableLayout){
            showPopUpWindow(POPUPWINDOW_TYPE_SERVICE);
        }else if (v ==minusLayout){
            showPopUpWindow(POPUPWINDOW_TYPE_MINUS_ACTIVITY);
        }else if (v== giftLayout){
            showPopUpWindow(POPUPWINDOW_TYPE_GIFT_ACTIVITY);
        }
        else if (v == confirmCourseTimeTv){
            if(selectedCourseTime!=null){
                courseTimeTv.setText(selectedCourseTime);
            }
            menuWindow.dismiss();
        }
        else if (v==moreSchoolLayout){
            Intent intent = new Intent(mContext, PublicListActivity.class);
            intent.putExtra(Const.KEY_TYPE,Const.TYPE_FROM_COURSE_DETAIL_OTHER_SCHOOL);
            mContext.startActivity(intent);
        }else if (v==cellPhoneIv){
            DialogUtils.cellPhoneDialog(mContext,entity.getSchoolInfoBean().getSchoolTel());
        }
    }

    private void showPopUpWindow(int type) {

        if(type == POPUPWINDOW_TYPE_COURESE_TIME){
            ShowCourseTimeWindow(isEdited);
        }else if (type == POPUPWINDOW_TYPE_SERVICE){
            showKBKService();
        }else if (type==POPUPWINDOW_TYPE_MINUS_ACTIVITY){
            showPromotionActivity(POPUPWINDOW_TYPE_MINUS_ACTIVITY);
        }else if (type == POPUPWINDOW_TYPE_GIFT_ACTIVITY){
            showPromotionActivity(POPUPWINDOW_TYPE_GIFT_ACTIVITY);
        }

    }

        //  立减弹窗 和 报课礼弹窗
    private void showPromotionActivity(int type) {
        View rootView  = LayoutInflater.from(mContext).inflate(R.layout.view_show_serve_pop,null);
        TextView confirm = (TextView) rootView.findViewById(R.id.tv_confirm);
        confirm.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                menuWindow.dismiss();
            }
        });
        TextView titleName = (TextView) rootView.findViewById(R.id.tv_title_name);
        titleName.setText("促销");
        LinearLayout containerLayout = (LinearLayout) rootView.findViewById(R.id.ll_container);
        View cellView = LayoutInflater.from(mContext).inflate(R.layout.view_cell_promotion_info,null);
        TextView contentInfo = (TextView) cellView.findViewById(R.id.iv_content_info);
        TextView contentName = (TextView) cellView.findViewById(R.id.tv_content_name);
//        ImageView contentImg = (ImageView) cellView.findViewById(R.id.iv_content_img);
        if(type==POPUPWINDOW_TYPE_GIFT_ACTIVITY){
            contentInfo.setText(entity.getGiftInfoBean().getContentInfo());
            contentName.setText(entity.getGiftInfoBean().getContentImg());
        }else if (type ==POPUPWINDOW_TYPE_MINUS_ACTIVITY ){
            contentInfo.setText(entity.getPromotionInfoBean().getContentInfo());
            contentName.setText(entity.getPromotionInfoBean().getContentImg());
        }
//        GlideUtils.loadImageView(mContext,entity.getPromotionInfoBean().getContentImg(),contentImg);
        LinearLayout.LayoutParams cellParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        containerLayout.addView(cellView,cellParams);
        menuWindow = new MyPopupWindow(mContext, rootView);
        menuWindow.setAnimationStyle(R.style.Anim_Menu_Bottombar);
        menuWindow.showAtLocation(textView, Gravity.BOTTOM, 0, 0);
//        for(int i = 0;i<entity.get)
    }

    //点击课比课服务弹出弹窗
    private void showKBKService() {
        View rootView  = LayoutInflater.from(mContext).inflate(R.layout.view_show_serve_pop,null);
        TextView confirm = (TextView) rootView.findViewById(R.id.tv_confirm);
        confirm.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
               menuWindow.dismiss();
            }
        });
        LinearLayout containerLayout = (LinearLayout) rootView.findViewById(R.id.ll_container);
        for(int i = 0;i<entity.getKbkServiceList().size();i++){
            View cellView = LayoutInflater.from(mContext).inflate(R.layout.view_kbk_service_item,null);
            TextView serviceName = (TextView) cellView.findViewById(R.id.tv_service_name);
            TextView serviceInfo = (TextView) cellView.findViewById(R.id.tv_service_info);
            serviceName.setText(entity.getKbkServiceList().get(i).getServiceInfo());
            LinearLayout.LayoutParams cellParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            containerLayout.addView(cellView,cellParams);
        }
        menuWindow = new MyPopupWindow(mContext, rootView);
        menuWindow.setAnimationStyle(R.style.Anim_Menu_Bottombar);
        menuWindow.showAtLocation(textView, Gravity.BOTTOM, 0, 0);
    }

    //点击选择课时后弹出弹窗
    private void ShowCourseTimeWindow(final boolean edited) {
        final List<View> viewLists= new ArrayList<>();
        LayoutInflater inflater = (LayoutInflater) mContext.getApplicationContext().
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        courseList.clear();
        View rootView  = inflater.inflate(R.layout.view_select_coures_time_pop, null);
        ImageView schoolImage = (ImageView) rootView.findViewById(R.id.iv_goods_image);
        final TextView kbkMoneyTv= (TextView) rootView.findViewById(R.id.tv_kbk_money);
        final TextView shopMoneyTv = (TextView) rootView.findViewById(R.id.tv_shop_money);
        final TextView selectedNumTv = (TextView) rootView.findViewById(R.id.tv_selected_num);
        final TextView isSelectTv = (TextView) rootView.findViewById(R.id.tv_is_select);
        if(edited){
            isSelectTv.setText("已选择：");
        }else {
            isSelectTv.setText("请选择：");
            kbkMoneyTv.setText(entity.getGoodsAttrBeanList().get(0).getKbkMoney());
            shopMoneyTv.setText(entity.getGoodsAttrBeanList().get(0).getShopMoney());
        }
        GlideUtils.loadImageView(mContext,entity.getPromotionInfoBean().getPromotionImg(),schoolImage);
//        selectedNumTv.setText(entity.getGoodsAttrBeanList().get(0).getGoodsAttr());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        confirmCourseTimeTv = (TextView) rootView.findViewById(R.id.tv_confirm);
        confirmCourseTimeTv.setOnClickListener(this);
        final FlowLayout courseTimeLayout = (FlowLayout) rootView.findViewById(R.id.ll_container_course_time);
        if(entity.getGoodsAttrBeanList()!=null&&entity.getGoodsAttrBeanList().size()>0){
            for(int j=0;j<entity.getGoodsAttrBeanList().size();j++){
                courseList.add(entity.getGoodsAttrBeanList().get(j).getGoodsAttr());
                LogUtil.d("===","courseList:"+entity.getGoodsAttrBeanList().get(j).getGoodsAttr());
            }
        }
        for ( int i = 0; i < courseList.size(); i++) {

            final LinearLayout courseLayout = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.view_course_time_item, null);
            final TextView itemView = (TextView) courseLayout.findViewById(R.id.tv_course_time);
            viewLists.add(itemView);
            itemView.setText(courseList.get(i));
            itemView.setBackgroundResource(R.drawable.shape_tv_course_time_normal);
            itemView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    for(int j = 0;j<viewLists.size();j++){
                        isEdited=true;
                        if(v==viewLists.get(j)){
                            isSelectTv.setText("已选择：");
                            viewLists.get(j).setBackgroundResource(R.drawable.shape_tv_course_time_selected);
                            ((TextView)viewLists.get(j)).setTextColor(getResources().getColor(R.color.color_f75250));
                            selectedCourseTime =   ((TextView)viewLists.get(j)).getText().toString();
                            selectedNumTv.setText(selectedCourseTime);
                            kbkMoneyTv.setText(entity.getGoodsAttrBeanList().get(j).getKbkMoney());
                            shopMoneyTv.setText(entity.getGoodsAttrBeanList().get(j).getShopMoney());
                        }else{
                            viewLists.get(j).setBackgroundResource(R.drawable.shape_tv_course_time_normal);
                            ((TextView)viewLists.get(j)).setTextColor(getResources().getColor(R.color.color_333333));
                        }
                    }

                }
            });
            courseTimeLayout.addView(courseLayout,params);
        }
        menuWindow = new MyPopupWindow(mContext, rootView);
        menuWindow.setAnimationStyle(R.style.Anim_Menu_Bottombar);
        menuWindow.showAtLocation(textView, Gravity.BOTTOM, 0, 0);
    }
}
