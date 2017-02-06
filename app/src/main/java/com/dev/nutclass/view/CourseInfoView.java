package com.dev.nutclass.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dev.nutclass.R;
import com.dev.nutclass.entity.CourseCardEntity;

import java.util.ArrayList;
import java.util.List;

import cn.lankton.flowlayout.FlowLayout;

/**
 * Created by Administrator on 2017/1/5.
 */
public class CourseInfoView extends LinearLayout implements View.OnClickListener {
    private static final String TAG = "CourseInfoView";
    private static final int POPUPWINDOW_TYPE_SERVICE = 1;
    private static final int POPUPWINDOW_TYPE_COURESE_TIME=2;
    private LinearLayout courseLableLayout;
    private RelativeLayout selectTimeLayout;
    private LinearLayout promotionLayout;
    private TextView confirmCourseTimeTv;
    private Context mContext;
    MyPopupWindow menuWindow;
    TextView textView;
    private TextView courseTimeTv;

    private String selectedCourseTime;
    List<String> courseList = new ArrayList<>();

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
        promotionLayout = (LinearLayout) this.findViewById(R.id.ll_container_promotion);
        courseTimeTv = (TextView) this.findViewById(R.id.tv_select_course_time);
        selectTimeLayout.setOnClickListener(this);
        courseLableLayout.setOnClickListener(this);
        promotionLayout.setOnClickListener(this);
    }

    public void updateView(CourseCardEntity courseEntity) {
        courseList.addAll(courseEntity.getCourseTimeList());
        for (int i = 0; i < courseEntity.getCourseTimeList().size(); i++) {
            LinearLayout layout = (LinearLayout) LayoutInflater.from(mContext).inflate(
                    R.layout.view_course_num_item, null);
            textView = (TextView) layout.findViewById(R.id.tv_course_num);
            textView.setText(courseEntity.getCourseTimeList().get(i));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT);
            params.setMargins(getResources().getDimensionPixelSize(R.dimen.common_10), 0,
                    getResources().getDimensionPixelSize(R.dimen.common_20), 0);
            courseLableLayout.addView(layout, params);
        }

        for(int i=0;i<2;i++){
            LinearLayout layout = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.view_course_promotion_info,null);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
            promotionLayout.addView(layout,params);
        }
    }

    @Override
    public void onClick(View v) {
        if (v == selectTimeLayout) {
            showPopUpWindow(POPUPWINDOW_TYPE_COURESE_TIME);
        }else if(v==courseLableLayout){
            showPopUpWindow(POPUPWINDOW_TYPE_SERVICE);
        }else if (v ==promotionLayout){
            showPopUpWindow(POPUPWINDOW_TYPE_SERVICE);
        }else if (v == confirmCourseTimeTv){
            if(selectedCourseTime!=null){
                courseTimeTv.setText(selectedCourseTime);
            }
            menuWindow.dismiss();
        }
    }

    private void showPopUpWindow(int type) {
        final List<View> viewLists= new ArrayList<>();
        LayoutInflater inflater = (LayoutInflater) mContext.getApplicationContext().
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rootView = null;
        if(type == POPUPWINDOW_TYPE_COURESE_TIME){
            rootView = inflater.inflate(R.layout.view_select_coures_time_pop, null);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
                    LayoutParams.WRAP_CONTENT);
            confirmCourseTimeTv = (TextView) rootView.findViewById(R.id.tv_confirm);
            confirmCourseTimeTv.setOnClickListener(this);
            final FlowLayout courseTimeLayout = (FlowLayout) rootView.findViewById(R.id.ll_container_course_time);
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
                            if(v==viewLists.get(j)){
                                viewLists.get(j).setBackgroundResource(R.drawable.shape_tv_course_time_selected);
                                ((TextView)viewLists.get(j)).setTextColor(getResources().getColor(R.color.color_f75250));
                                selectedCourseTime =   ((TextView)viewLists.get(j)).getText().toString();
                            }else{
                                viewLists.get(j).setBackgroundResource(R.drawable.shape_tv_course_time_normal);
                                ((TextView)viewLists.get(j)).setTextColor(getResources().getColor(R.color.color_333333));
                            }
                        }

                    }
                });
                courseTimeLayout.addView(courseLayout,params);
        }

        }else if (type == POPUPWINDOW_TYPE_SERVICE){
            rootView = LayoutInflater.from(mContext).inflate(R.layout.view_show_serve_pop,null);
        }
        menuWindow = new MyPopupWindow(mContext, rootView);
        menuWindow.setAnimationStyle(R.style.Anim_Menu_Bottombar);
        menuWindow.showAtLocation(textView, Gravity.BOTTOM, 0, 0);
    }
}
