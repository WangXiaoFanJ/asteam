package com.dev.nutclass.entity;

import com.sina.weibo.sdk.api.share.Base;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/7.
 */
public class LookHistoryEntity extends BaseCardEntity {
    private String logDate;
    private List<CourseListCardEntity> list;

    public LookHistoryEntity(int type,JSONObject jsonObject) {
       setCardType(type);
        setLogDate(jsonObject.optString("log_date"));
        optJson(jsonObject);
    }

    private void optJson(JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.optJSONArray("list");
        if(jsonArray!=null&&jsonArray.length()>0){
            List<CourseListCardEntity> list = new ArrayList<>();
            for(int i = 0;i<jsonArray.length();i++){
                JSONObject jsonObj = jsonArray.optJSONObject(i);
                CourseListCardEntity entity = new CourseListCardEntity(jsonObj);
                list.add(entity);
            }
            setList(list);
        }
    }

    public String getLogDate() {
        return logDate;
    }

    public void setLogDate(String logDate) {
        this.logDate = logDate;
    }

    public List<CourseListCardEntity> getList() {
        return list;
    }

    public void setList(List<CourseListCardEntity> list) {
        this.list = list;
    }
}
