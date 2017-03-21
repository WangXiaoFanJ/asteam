package com.dev.nutclass.parser;

import com.dev.nutclass.constants.UrlConst;
import com.dev.nutclass.entity.BaseCardEntity;
import com.dev.nutclass.entity.CourseCommentListEntity;
import com.dev.nutclass.entity.JsonDataList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/11.
 */
public class CourseCommentParser extends BaseParser02 {
    @Override
    public Object parse(String jsonString) throws JSONException {
        JsonDataList<BaseCardEntity> jsonDataList =new JsonDataList<>();
        ArrayList<BaseCardEntity> list = new ArrayList<BaseCardEntity>();
        JSONObject jsonObject = new JSONObject(jsonString);
        JSONArray jsonArray = jsonObject.optJSONArray("data");
        if(jsonArray!=null&&jsonArray.length()>0){
            for (int i= 0;i<jsonArray.length();i++){
                JSONObject jsonObject2 = jsonArray.optJSONObject(i);
                CourseCommentListEntity entity = new CourseCommentListEntity(jsonObject2);
                list.add(entity);
            }
        }
        jsonDataList.setErrorCode(UrlConst.SUCCESS_CODE);
        jsonDataList.setList(list);
        return jsonDataList;
    }
}
