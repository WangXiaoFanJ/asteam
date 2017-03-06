package com.dev.nutclass.parser;

import com.dev.nutclass.entity.BaseCardEntity;
import com.dev.nutclass.entity.CourseListCardEntity;
import com.dev.nutclass.entity.JsonDataList;
import com.dev.nutclass.entity.SchoolCardEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/2/25.
 */
public class CollectListParser {
    public Object parser(int type,String jsonString)throws JSONException{
        JsonDataList<BaseCardEntity> retObj=new JsonDataList<>();
        ArrayList<BaseCardEntity> list = new ArrayList<BaseCardEntity>();
        JSONObject jsonObject = new JSONObject(jsonString);
        JSONArray jsonArray = jsonObject.optJSONArray("data");
        if(jsonArray!=null&&jsonArray.length()>0){
            for(int i = 0;i<jsonArray.length();i++){
                BaseCardEntity entity = null;
                JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                if(type ==BaseCardEntity.CARD_TYPE_COLLECT_COURSE_VIEW ){
                    entity = new CourseListCardEntity(BaseCardEntity.CARD_TYPE_COLLECT_COURSE_VIEW,jsonObject1);
                }else if(type == BaseCardEntity.CARD_TYPE_COLLECT_SCHOOL_VIEW){
                    entity = new SchoolCardEntity(BaseCardEntity.CARD_TYPE_COLLECT_SCHOOL_VIEW,jsonObject1);
                }

                list.add(entity);
            }
        retObj.setList(list);
        }
        return retObj;
    }
}
