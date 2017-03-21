package com.dev.nutclass.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.dev.nutclass.R;
import com.dev.nutclass.activity.MyCollectActivity;
import com.dev.nutclass.callback.EditOnclickListener;
import com.dev.nutclass.entity.BaseCardEntity;
import com.dev.nutclass.entity.CourseCardEntity;
import com.dev.nutclass.entity.CourseListCardEntity;
import com.dev.nutclass.entity.SchoolCardEntity;
import com.dev.nutclass.utils.GlideUtils;
import com.dev.nutclass.utils.LogUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2017/2/25.
 */
public class CollectCourseListAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<BaseCardEntity> lists;
    private Map<Integer, Integer> map = new HashMap<>();
    private int type;
    private boolean isEdit;

    public CollectCourseListAdapter(Context context, ArrayList<BaseCardEntity> list, int type, boolean isEdit) {
        mContext = context;
        lists = list;
        this.type = type;
        this.isEdit = isEdit;
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.view_amusement_park_card, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (type == BaseCardEntity.CARD_TYPE_COLLECT_COURSE_VIEW) {
            final CourseListCardEntity courseEntity = (CourseListCardEntity) lists.get(position);
            GlideUtils.loadImageView(mContext, courseEntity.getGoodsImage(), viewHolder.goodsImageIv);
            viewHolder.goodsNameTv.setText(courseEntity.getGoodsName());
            if (courseEntity.getIsPromotion().equals("1")) {
                viewHolder.promotionIv.setVisibility(View.VISIBLE);
            }
            if (courseEntity.getIsHot().equals("1")) {
                viewHolder.hotIv.setVisibility(View.VISIBLE);
            }
            viewHolder.shopCircleTv.setText(courseEntity.getShopCircleName());
            viewHolder.cateNameTv.setText(courseEntity.getCateName());
            viewHolder.gpsCnTv.setText(courseEntity.getGpsCn());
            viewHolder.kbkMoneyTv.setText(courseEntity.getGpsCn());
            viewHolder.shopMoneyTv.setText(courseEntity.getShopMoney());
            viewHolder.checkBoxIv.setTag(position);
            viewHolder.checkBoxIv.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        if (!map.containsKey(viewHolder.checkBoxIv.getTag())) {
                            map.put((Integer) viewHolder.checkBoxIv.getTag(), position);
                        }
                    } else {
                        map.remove(viewHolder.checkBoxIv.getTag());
                    }
                }
            });
        } else if (type == BaseCardEntity.CARD_TYPE_COLLECT_SCHOOL_VIEW) {
            SchoolCardEntity schoolCardEntity = (SchoolCardEntity) lists.get(position);
            GlideUtils.loadImageView(mContext, schoolCardEntity.getSchoolImage(), viewHolder.goodsImageIv);
            viewHolder.goodsNameTv.setText(schoolCardEntity.getSchoolName());
            if (schoolCardEntity.getIsPromotion().equals("1")) {
                viewHolder.promotionIv.setVisibility(View.VISIBLE);
            }
            if (schoolCardEntity.getIsHot().equals("1")) {
                viewHolder.hotIv.setVisibility(View.VISIBLE);
            }
            viewHolder.shopCircleTv.setText(schoolCardEntity.getShop_circle_text());
            viewHolder.cateNameTv.setText(schoolCardEntity.getSchoolCateName());
            viewHolder.gpsCnTv.setText(schoolCardEntity.getGpsCn());
            viewHolder.kbkMoneyTv.setVisibility(View.GONE);
            viewHolder.shopMoneyTv.setText(schoolCardEntity.getInterestNum());
            viewHolder.checkBoxIv.setTag(position);
            viewHolder.checkBoxIv.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        if (!map.containsKey(viewHolder.checkBoxIv.getTag())) {
                            map.put((Integer) viewHolder.checkBoxIv.getTag(), position);
                        }
                    } else {
                        map.remove(viewHolder.checkBoxIv.getTag());
                    }
                }
            });
        }
        if (isEdit) {
            viewHolder.checkBoxIv.setVisibility(View.VISIBLE);
        } else {
            viewHolder.checkBoxIv.setVisibility(View.GONE);
        }
        return convertView;
    }


    public String deleteList() {
        StringBuilder sb = new StringBuilder();
//        Set<Integer> keys = map.keySet();
//        for (int key: keys) {
//        }
        for (int i = 0; i < lists.size(); i++) {
            if (map.get(i) != null && map.get(i) == i) {
                if (type == BaseCardEntity.CARD_TYPE_COLLECT_COURSE_VIEW) {
                    CourseListCardEntity entity = (CourseListCardEntity) lists.get(i);
                    sb.append(entity.getGoodsId());
                } else if (type == BaseCardEntity.CARD_TYPE_COLLECT_SCHOOL_VIEW) {
                    SchoolCardEntity schoolCardEntity = (SchoolCardEntity) lists.get(i);
                    sb.append(schoolCardEntity.getSchoolId());
                }
                if (lists.size() > 1 && i < lists.size() - 1) {
                    sb.append(",");
                }
            }
        }
        return sb.toString().trim();
    }

    class ViewHolder {
        private ImageView goodsImageIv;
        private TextView goodsNameTv;
        private ImageView promotionIv;
        private ImageView hotIv;
        private TextView shopCircleTv;
        private TextView cateNameTv;
        private TextView gpsCnTv;
        private TextView kbkMoneyTv;
        private TextView shopMoneyTv;
        private CheckBox checkBoxIv;

        public ViewHolder(View convertView) {

            goodsImageIv = (ImageView) convertView.findViewById(R.id.iv_school_image);
            goodsNameTv = (TextView) convertView.findViewById(R.id.tv_goods_name);
            promotionIv = (ImageView) convertView.findViewById(R.id.iv_icon_promotion);
            gpsCnTv = (TextView) convertView.findViewById(R.id.tv_gps_cn);
            hotIv = (ImageView) convertView.findViewById(R.id.iv_icon_hot);
            shopCircleTv = (TextView) convertView.findViewById(R.id.tv_shop_circle);
            cateNameTv = (TextView) convertView.findViewById(R.id.tv_cate_name);
            kbkMoneyTv = (TextView) convertView.findViewById(R.id.tv_kbk_money);
            shopMoneyTv = (TextView) convertView.findViewById(R.id.tv_shop_money);
            checkBoxIv = (CheckBox) convertView.findViewById(R.id.iv_check_box);
        }
    }


}
