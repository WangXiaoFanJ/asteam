package com.dev.nutclass.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.dev.nutclass.activity.MainActivity;
import com.dev.nutclass.entity.BannerCardEntity;
import com.dev.nutclass.entity.BaseCardEntity;
import com.dev.nutclass.entity.ClassifyHomeCourseEntity;
import com.dev.nutclass.entity.CourseCommentEntity;
import com.dev.nutclass.entity.CourseCommentListEntity;
import com.dev.nutclass.entity.CourseInfoEntity;
import com.dev.nutclass.entity.CourseListCardEntity;
import com.dev.nutclass.entity.CourseRecommendForUEntity;
import com.dev.nutclass.entity.CourserDetailHeadEntity;
import com.dev.nutclass.entity.DiscountCouponEntity;
import com.dev.nutclass.entity.HomeIconEntity;
import com.dev.nutclass.entity.HomeJDCardEntity;
import com.dev.nutclass.entity.JDCatCardEntity;
import com.dev.nutclass.entity.JDItemCardEntity;
import com.dev.nutclass.entity.LookHistoryEntity;
import com.dev.nutclass.entity.OtherSchoolEntity;
import com.dev.nutclass.entity.SchoolCardEntity;
import com.dev.nutclass.entity.SchoolDetailHeadEntity;
import com.dev.nutclass.entity.SchoolDetailMerchInfoEntity;
import com.dev.nutclass.entity.SchoolDetailToCourseEntity;
import com.dev.nutclass.entity.SchoolIntroduceEntity;
import com.dev.nutclass.entity.SchoolRecommentForUEntity;
import com.dev.nutclass.entity.SchoolToCourseCardEntity;
import com.dev.nutclass.entity.ServiceFeatureEntity;
import com.dev.nutclass.entity.UserOrdeCardEntity;
import com.dev.nutclass.view.BannerCardView;
import com.dev.nutclass.view.ClassifyHomeCourseView;
import com.dev.nutclass.view.CommentForCourseDetailView;
import com.dev.nutclass.view.CourseCardView;
import com.dev.nutclass.view.CourseCommentListView;
import com.dev.nutclass.view.CourseInfoHeadView;
import com.dev.nutclass.view.CourseInfoView;
import com.dev.nutclass.view.CourseRecommendView;
import com.dev.nutclass.view.DiscountCouponView;
import com.dev.nutclass.view.HomeIconView;
import com.dev.nutclass.view.JDCardView;
import com.dev.nutclass.view.JDCatCardView;
import com.dev.nutclass.view.JDItemCardView;
import com.dev.nutclass.view.LookHistoryView;
import com.dev.nutclass.view.NearbyAmuseParkView;
import com.dev.nutclass.view.OtherSchoolView;
import com.dev.nutclass.view.PublicSchoolFeatureView;
import com.dev.nutclass.view.SchoolCardView;
import com.dev.nutclass.view.SchoolCommonCourseView;
import com.dev.nutclass.view.SchoolInfoModule001View;
import com.dev.nutclass.view.SchoolInfoCourseListView;
import com.dev.nutclass.view.SchoolInfoHeadView;
import com.dev.nutclass.view.SchoolIntroduceView;
import com.dev.nutclass.view.SchoolRecommendView;
import com.dev.nutclass.view.UserOrderAppointmentView;
import com.dev.nutclass.view.UserOrderBackMoneyView;
import com.dev.nutclass.view.UserOrderCompletedView;
import com.dev.nutclass.view.UserOrderWaitCommentView;
import com.dev.nutclass.view.UserOrderWaitPayView;
import com.dev.nutclass.view.UserOrderWaitUseView;

import java.util.List;

/**
 * Created by Administrator on 2016/12/29.
 */
public class CardListAdapter extends RecyclerView.Adapter<CardListAdapter.MyViewHolder> {
    private List<BaseCardEntity> list;
    private Context mContext;
    private RecyclerItemClickListener itemClickListener;
    private boolean isEdit;
    public CardListAdapter(List<BaseCardEntity> list,Context context) {
        this.list = list;
        this.mContext = context;
    }
    public CardListAdapter(List<BaseCardEntity> list,Context context,boolean isEdit) {
        this.list = list;
        this.mContext = context;
        this.isEdit = isEdit;
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
        if(viewType==BaseCardEntity.CARD_TYPE_BANNER_VIEW||viewType==BaseCardEntity.CARD_TYPE_JD_BANNER){
            if(viewType== BaseCardEntity.CARD_TYPE_JD_BANNER){
                view = new BannerCardView(mContext,2);
            }else if (mContext instanceof MainActivity&&viewType == BaseCardEntity.CARD_TYPE_BANNER_VIEW){
                //首页banner
                view= new BannerCardView(mContext,1);
            }else{
                view= new BannerCardView(mContext,viewType);
            }
        }
//        else if(viewType==2){
//            view = new ClassifyView(mContext);
//        }
        //首页icon(1002)
        else if (viewType == BaseCardEntity.CARD_TYPE_HOME_ICON_VIEW){
            view = new HomeIconView(mContext);
        }
        //首页课程分类页（1004）
        else if (viewType == BaseCardEntity.CARD_TYPE_HOME_COURSE_CLASSIFY){
            view = new ClassifyHomeCourseView(mContext);
        }
        else if (viewType == BaseCardEntity.CARD_TYPE_COURSE_CARD_VIEW){
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
        }
        else if (viewType == BaseCardEntity.CARD_TYPE_JD_CARD_VIEW){
            view = new JDItemCardView(mContext);
        }

        //校区详情页面
        else if(viewType == BaseCardEntity.CARD_TYPE_SCHOOL_INFO_HEAD_NAME) {
            view = new SchoolInfoHeadView(mContext);
        }else if (viewType == BaseCardEntity.CARD_TYPE_SCHOOL_INFO_COURSE_VIEW){
            view = new SchoolInfoCourseListView(mContext);
        }else if (viewType == BaseCardEntity.CARD_TYPE_SCHOOL_INFO_001_View){
            view = new SchoolInfoModule001View(mContext);
        }
        //校区详情页-为您推荐（1027）
        else if (viewType == BaseCardEntity.CARD_TYPE_SCHOOL_RECOMMEND_VIEW){
            view = new SchoolRecommendView(mContext);
        }
        //校区详情页-机构介绍（1018）
        else if (viewType == BaseCardEntity.CARD_TYPE_SCHOOL_DETAIL_INTRODUCE_VIEW){
            view = new SchoolIntroduceView(mContext);
        }

        //校区正课列表
        else if (viewType == BaseCardEntity.CARD_TYPE_SCHOOL_COMMON_COURSE_VIEW){
            view = new SchoolCommonCourseView(mContext);
        }

        //校区详情页与课程详情页公用view
        else if(viewType == BaseCardEntity.CARD_TYPE_SCHOOL_FEATURE_VIEW){
            view = new PublicSchoolFeatureView(mContext);
        }
        //校区详情页课程评论（1011）
        else if (viewType == BaseCardEntity.CARD_TYPE_CORUSE_COMMENT_VIEW){
            view = new CommentForCourseDetailView(mContext);
        }

        //订单详情
        else if (viewType == BaseCardEntity.CARD_TYPE_USER_ORDER_VIEW){
            view = new UserOrderWaitCommentView(mContext);
        }else if (viewType == BaseCardEntity.CARD_TYPE_USER_ORDER_WAIT_PAY_VIEW){
            view = new UserOrderWaitPayView(mContext);
        }else if (viewType == BaseCardEntity.CARD_TYPE_USER_ORDER_WAIT_USE_VIEW){
            view = new UserOrderWaitUseView(mContext);
        }else if (viewType == BaseCardEntity.CARD_TYPE_USER_ORDER_AFTER_SALE_VIEW){
            view = new UserOrderBackMoneyView(mContext);
        }else if (viewType == BaseCardEntity.CARD_TYPE_USER_ORDER_APPOINTMENT){
            view = new UserOrderAppointmentView(mContext);
        }else if (viewType == BaseCardEntity.CARD_TYPE_USER_ORDER_COMPLETED){
            view = new UserOrderCompletedView(mContext);
        }
        //优惠券
        else if (viewType == BaseCardEntity.CARD_TYPE_DISCOUNT_COUPON_VIEW){
            view = new DiscountCouponView(mContext);
        }
        //其他校区
        else if (viewType == BaseCardEntity.CARD_TYPE_OTHER_SCHOOL){
            view = new OtherSchoolView(mContext);
        }
        //浏览历史
        else if (viewType == BaseCardEntity.CARD_TYPE_LOOK_HISTORY_VIEW){
            view = new LookHistoryView(mContext);
        }
        //课程评论列表
        else if (viewType ==BaseCardEntity.CARD_TYPE_COURSE_COMMENT_LIST_VIEW){
            view = new CourseCommentListView(mContext);
        }
//        View view = LayoutInflater.from(mContext).inflate(R.layout.banner_card_view,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
//        holder.tv.setText(list.get(position));
        int type = list.get(position).getCardType();
        if(type == 1||type==BaseCardEntity.CARD_TYPE_BANNER_VIEW||type==BaseCardEntity.CARD_TYPE_JD_BANNER){
//            ImageEntity imageEntity = (ImageEntity) list.get(position);
            BannerCardEntity entity = (BannerCardEntity) list.get(position);
            ((BannerCardView)holder.view).updateView(entity);
//            if(type==BaseCardEntity.CARD_TYPE_JD_BANNER){
//                BannerCardEntity entity = (BannerCardEntity) list.get(position);
//                ((BannerCardView) (holder.view)).updateView(entity,1);
//            }
        }
//        else if(type==2){
//            ClassifyHomeCourseEntity classifyEntity = (ClassifyHomeCourseEntity) list.get(position);
//            ((ClassifyView)holder.view).updateView(classifyEntity);
//        }
        //首页京东banner
        else if(type == BaseCardEntity.CARD_TYPE_JD_CARD_VIEW) {
                HomeJDCardEntity entity = (HomeJDCardEntity) list.get(position);
                ((JDItemCardView) (holder.view)).updateView(entity);
            }
            //首页icon(1002)
            else if (type == BaseCardEntity.CARD_TYPE_HOME_ICON_VIEW) {
                HomeIconEntity entity = (HomeIconEntity) list.get(position);
                ((HomeIconView) (holder.view)).updateView(entity);
            }
            //首页课程分类页（1004）
        else if (type == BaseCardEntity.CARD_TYPE_HOME_COURSE_CLASSIFY){
            ClassifyHomeCourseEntity entity = (ClassifyHomeCourseEntity) list.get(position);
            ((ClassifyHomeCourseView)(holder.view)).updateView(entity);
        }
        else if (type == BaseCardEntity.CARD_TYPE_COURSE_INFO) {
                CourseInfoEntity courseCardEntity = (CourseInfoEntity) list.get(position);
                ((CourseInfoView) holder.view).updateView(courseCardEntity);
            } else if (type == BaseCardEntity.CARD_TYPE_COURSE_CARD_VIEW) {
                CourseListCardEntity entity = (CourseListCardEntity) list.get(position);
                ((CourseCardView) (holder.view)).updateView(entity);

            } else if (type == BaseCardEntity.CARD_TYPE_AMUSE_PARK_VIEW) {

            } else if (type == BaseCardEntity.CARD_TYPE_JD_CAT_CARD) {
                JDCatCardEntity entity = (JDCatCardEntity) list.get(position);
                ((JDCatCardView) (holder.view)).updateView(entity, itemClickListener);
            } else if (type == BaseCardEntity.CARD_TYPE_JD_DOUBLE_CARD) {
                JDItemCardEntity entity = (JDItemCardEntity) list.get(position);
                ((JDCardView) (holder.view)).updateView(entity);
            }
            //课程详情页
            else if (type == BaseCardEntity.CARD_TYPE_COURSE_INFO_HEAD_VIEW) {
                CourserDetailHeadEntity entity = (CourserDetailHeadEntity) list.get(position);
                ((CourseInfoHeadView) (holder.view)).updateView(entity);
            } else if (type == BaseCardEntity.CARD_TYPE_COURSE_RECOMMEND_CARD_VIEW) {
                CourseRecommendForUEntity entity = (CourseRecommendForUEntity) list.get(position);
                ((CourseRecommendView) (holder.view)).updateView(entity);
            } else if (type == BaseCardEntity.CARD_TYPE_SHCOOL_CARD_VIEW) {
                SchoolCardEntity entity = (SchoolCardEntity) list.get(position);
                ((SchoolCardView) (holder.view)).updateView(entity);
            }

            //校区详情页面
            else if (type == BaseCardEntity.CARD_TYPE_SCHOOL_INFO_HEAD_NAME) {
                SchoolDetailHeadEntity entity = (SchoolDetailHeadEntity) list.get(position);
                ((SchoolInfoHeadView) holder.view).updateView(entity);
            } else if (type == BaseCardEntity.CARD_TYPE_SCHOOL_INFO_COURSE_VIEW) {
                SchoolDetailToCourseEntity entity = (SchoolDetailToCourseEntity) list.get(position);
                ((SchoolInfoCourseListView) (holder.view)).updateView(entity);
            } else if (type == BaseCardEntity.CARD_TYPE_SCHOOL_INFO_001_View) {
                SchoolDetailMerchInfoEntity entity = (SchoolDetailMerchInfoEntity) list.get(position);
                ((SchoolInfoModule001View) (holder.view)).updateView(entity);
            }
            //校区详情页-为您推荐（1027）
            else if (type == BaseCardEntity.CARD_TYPE_SCHOOL_RECOMMEND_VIEW) {
                SchoolRecommentForUEntity entity = (SchoolRecommentForUEntity) list.get(position);
                ((SchoolRecommendView) (holder.view)).update(entity);
            }
            //校区详情页-机构介绍（1018）
            else if (type == BaseCardEntity.CARD_TYPE_SCHOOL_DETAIL_INTRODUCE_VIEW) {
                SchoolIntroduceEntity entity = (SchoolIntroduceEntity) list.get(position);
                ((SchoolIntroduceView) (holder.view)).updateView(entity);
            } else if (type == BaseCardEntity.CARD_TYPE_CORUSE_COMMENT_VIEW) {
                CourseCommentEntity entity = (CourseCommentEntity) list.get(position);
                ((CommentForCourseDetailView) (holder.view)).updateView(entity);
            }

            //校区正课列表
            else if (type == BaseCardEntity.CARD_TYPE_SCHOOL_COMMON_COURSE_VIEW) {
                SchoolToCourseCardEntity entity = (SchoolToCourseCardEntity) list.get(position);
                ((SchoolCommonCourseView) (holder.view)).updateView(entity);
            }
            //用户订单页面
            else if (type == BaseCardEntity.CARD_TYPE_USER_ORDER_WAIT_PAY_VIEW) {
                UserOrdeCardEntity entity = (UserOrdeCardEntity) list.get(position);
                ((UserOrderWaitPayView) (holder.view)).updateView(entity);
            } else if (type == BaseCardEntity.CARD_TYPE_USER_ORDER_WAIT_USE_VIEW) {
                UserOrdeCardEntity entity = (UserOrdeCardEntity) list.get(position);
                ((UserOrderWaitUseView) (holder.view)).updateView(entity);
            } else if (type == BaseCardEntity.CARD_TYPE_USER_ORDER_AFTER_SALE_VIEW) {
                UserOrdeCardEntity entity = (UserOrdeCardEntity) list.get(position);
                ((UserOrderBackMoneyView) (holder.view)).updateView(entity);
            } else if (type == BaseCardEntity.CARD_TYPE_USER_ORDER_VIEW) {
                UserOrdeCardEntity entity = (UserOrdeCardEntity) list.get(position);
                ((UserOrderWaitCommentView) (holder.view)).updateView(entity);
            }else if (type == BaseCardEntity.CARD_TYPE_USER_ORDER_APPOINTMENT){
            UserOrdeCardEntity entity = (UserOrdeCardEntity) list.get(position);
            ((UserOrderAppointmentView) (holder.view)).updateView(entity);
            }else if (type == BaseCardEntity.CARD_TYPE_USER_ORDER_COMPLETED){
            UserOrdeCardEntity entity = (UserOrdeCardEntity) list.get(position);
            ((UserOrderCompletedView) (holder.view)).updateView(entity);
        }
            //校区详情页与课程详情页公用view
            else if (type == BaseCardEntity.CARD_TYPE_SCHOOL_FEATURE_VIEW) {
                ServiceFeatureEntity entity = (ServiceFeatureEntity) list.get(position);
                ((PublicSchoolFeatureView) (holder.view)).updateView(entity);
            }
            //优惠券
            else if (type == BaseCardEntity.CARD_TYPE_DISCOUNT_COUPON_VIEW) {
                DiscountCouponEntity entity = (DiscountCouponEntity) list.get(position);
                ((DiscountCouponView) (holder.view)).updateView(entity);
            }
        //其他校区
        else if (type == BaseCardEntity.CARD_TYPE_OTHER_SCHOOL){
            OtherSchoolEntity entity = (OtherSchoolEntity) list.get(position);
            ((OtherSchoolView)(holder.view)).updateView(entity);
        }
        //浏览历史
        else if (type == BaseCardEntity.CARD_TYPE_LOOK_HISTORY_VIEW){
            LookHistoryEntity entity = (LookHistoryEntity) list.get(position);
            ((LookHistoryView)(holder.view)).updateView(entity,isEdit);
        }
        //课程评论列表
        else if (type ==BaseCardEntity.CARD_TYPE_COURSE_COMMENT_LIST_VIEW){
            CourseCommentListEntity entity = (CourseCommentListEntity) list.get(position);
            ((CourseCommentListView)(holder.view)).updateView(entity);
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
