package com.dev.nutclass.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.dev.nutclass.entity.BannerCardEntity;
import com.dev.nutclass.entity.BaseCardEntity;
import com.dev.nutclass.entity.ClassifyEntity;
import com.dev.nutclass.entity.CourseCardEntity;
import com.dev.nutclass.entity.IconEntity;
import com.dev.nutclass.entity.JDCatCardEntity;
import com.dev.nutclass.entity.JDItemCardEntity;
import com.dev.nutclass.view.BannerCardView;
import com.dev.nutclass.view.ClassifyView;
import com.dev.nutclass.view.CourseCardView;
import com.dev.nutclass.view.CourseInfoHeadView;
import com.dev.nutclass.view.CourseInfoView;
import com.dev.nutclass.view.CourseRecommendView;
import com.dev.nutclass.view.HomeIconView;
import com.dev.nutclass.view.JDCardView;
import com.dev.nutclass.view.JDCatCardView;
import com.dev.nutclass.view.JDItemCardView;
import com.dev.nutclass.view.NearbyAmuseParkView;
import com.dev.nutclass.view.PublicSchoolFeatureView;
import com.dev.nutclass.view.SchoolCardView;
import com.dev.nutclass.view.SchoolInfoModule001View;
import com.dev.nutclass.view.SchoolInfoCourseListView;
import com.dev.nutclass.view.SchoolInfoHeadView;
import com.dev.nutclass.view.SchoolRecommendView;
import com.dev.nutclass.view.UserOrderView;

import java.util.List;

/**
 * Created by Administrator on 2016/12/29.
 */
public class CardListAdapter extends RecyclerView.Adapter<CardListAdapter.MyViewHolder> {
    private List<BaseCardEntity> list;
    private Context mContext;
    private RecyclerItemClickListener itemClickListener;
    public CardListAdapter(List<BaseCardEntity> list,Context context) {
        this.list = list;
        this.mContext = context;
    }

    public CardListAdapter(Context context, List<BaseCardEntity> list,
                           RecyclerItemClickListener listener) {
        this.list = list;
        this.mContext = context;
        itemClickListener = listener;
    }
    public void setItemClickListener(RecyclerItemClickListener listener) {
        itemClickListener = listener;
    }
    @Override
    public int getItemViewType(int position) {
        if(list == null || position<0||position>list.size()){
            if(position == 0){
                return position;
            }else{
                return -1;
            }
        }
        int type = list.get(position).getCardType();
        return type;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if(viewType ==1||viewType==BaseCardEntity.CARD_TYPE_BANNER_COURSE_INFO||viewType==BaseCardEntity.CARD_TYPE_JD_BANNER){
            if(viewType== BaseCardEntity.CARD_TYPE_JD_BANNER){
                view = new BannerCardView(mContext,2);
            }else{
                view= new BannerCardView(mContext,viewType);
            }
        }else if(viewType==2){
            view = new ClassifyView(mContext);
        }else if (viewType == 3){
            view = new HomeIconView(mContext);
        }else if (viewType == BaseCardEntity.CARD_TYPE_COURSE_CARD_VIEW){
            view = new CourseCardView(mContext);
        }else if (viewType == BaseCardEntity.CARD_TYPE_SHCOOL_CARD_VIEW){
            view = new SchoolCardView(mContext);
        }else if(viewType==BaseCardEntity.CARD_TYPE_JD_CAT_CARD){
            view=new JDCatCardView(mContext);
        }else if(viewType==BaseCardEntity.CARD_TYPE_JD_DOUBLE_CARD){
            view=new JDCardView(mContext);
        }
        //课程详情页面
        else if (viewType == BaseCardEntity.CARD_TYPE_COURSE_INFO_HEAD_VIEW){
            view = new CourseInfoHeadView(mContext);
        } else if (viewType == BaseCardEntity.CARD_TYPE_COURSE_INFO){
            view = new CourseInfoView(mContext);
        }else if (viewType == BaseCardEntity.CARD_TYPE_NEARBY_AMUSE_PARK_VIEW){
            view  = new NearbyAmuseParkView(mContext);
        }else if(viewType == BaseCardEntity.CARD_TYPE_COURSE_RECOMMEND_CARD_VIEW){
            view = new CourseRecommendView(mContext);
        }else if (viewType == BaseCardEntity.CARD_TYPE_JD_CARD_VIEW){
            view = new JDItemCardView(mContext);
        }

        //校区详情页面
        else if(viewType == BaseCardEntity.CARD_TYPE_SCHOOL_INFO_HEAD_NAME) {
            view = new SchoolInfoHeadView(mContext);
        }else if (viewType == BaseCardEntity.CARD_TYPE_SCHOOL_INFO_COURSE_VIEW){
            view = new SchoolInfoCourseListView(mContext);
        }else if (viewType == BaseCardEntity.CARD_TYPE_SCHOOL_INFO_001_View){
            view = new SchoolInfoModule001View(mContext);
        }else if (viewType == BaseCardEntity.CARD_TYPE_SCHOOL_RECOMMEND_VIEW){
            view = new SchoolRecommendView(mContext);
        }

        //校区详情页与课程详情页公用view
        else if(viewType == BaseCardEntity.CARD_TYPE_SCHOOL_FEATURE_VIEW){
            view = new PublicSchoolFeatureView(mContext);
        }

        //订单详情
        else if (viewType == BaseCardEntity.CARD_TYPE_USER_ORDER_VIEW){
            view = new UserOrderView(mContext);
        }
//        View view = LayoutInflater.from(mContext).inflate(R.layout.banner_card_view,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
//        holder.tv.setText(list.get(position));
        int type = list.get(position).getCardType();
        if(type == 1||type==BaseCardEntity.CARD_TYPE_BANNER_COURSE_INFO||type==BaseCardEntity.CARD_TYPE_JD_BANNER){
//            ImageEntity imageEntity = (ImageEntity) list.get(position);
            ((BannerCardView)holder.view).updateView();
//            if(type==BaseCardEntity.CARD_TYPE_JD_BANNER){
//                BannerCardEntity entity = (BannerCardEntity) list.get(position);
//                ((BannerCardView) (holder.view)).updateView(entity,1);
//            }
        }else if(type==2){
            ClassifyEntity classifyEntity = (ClassifyEntity) list.get(position);
            ((ClassifyView)holder.view).updateView(classifyEntity);
        }else if (type == 3){
            IconEntity iconEntity = (IconEntity) list.get(position);
            ((HomeIconView)holder.view).updateView(iconEntity);
        }else if (type == BaseCardEntity.CARD_TYPE_COURSE_INFO){
            CourseCardEntity courseCardEntity = (CourseCardEntity) list.get(position);
            ((CourseInfoView)holder.view).updateView(courseCardEntity);
        }else if (type == BaseCardEntity.CARD_TYPE_AMUSE_PARK_VIEW){

        }else if(type==BaseCardEntity.CARD_TYPE_JD_CAT_CARD){
            JDCatCardEntity entity = (JDCatCardEntity) list.get(position);
            ((JDCatCardView) (holder.view)).updateView(entity,itemClickListener);
        }else if(type==BaseCardEntity.CARD_TYPE_JD_DOUBLE_CARD){
            JDItemCardEntity entity = (JDItemCardEntity) list.get(position);
            ((JDCardView) (holder.view)).updateView(entity);
        }
        //课程详情页
        else if (type == BaseCardEntity.CARD_TYPE_COURSE_INFO_HEAD_VIEW){

        }



        //校区详情页面
        else if(type == BaseCardEntity.CARD_TYPE_SCHOOL_INFO_HEAD_NAME){
//            ((SchoolInfoHeadView)holder.view).
        }else if (type == BaseCardEntity.CARD_TYPE_SCHOOL_INFO_COURSE_VIEW){

        }else if (type == BaseCardEntity.CARD_TYPE_SCHOOL_INFO_001_View){

        }else if (type == BaseCardEntity.CARD_TYPE_SCHOOL_RECOMMEND_VIEW);{

        }
    }

    @Override
    public int getItemCount() {
        if(list==null){
            return 0;
        }
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
       private View view;
        public MyViewHolder(View itemView) {
            super(itemView);
          view = itemView;
        }
    }
}
