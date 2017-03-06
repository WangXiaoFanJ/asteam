package com.dev.nutclass.parser;

import com.dev.nutclass.constants.UrlConst;
import com.dev.nutclass.entity.BaseCardEntity;
import com.dev.nutclass.entity.JsonDataList;
import com.dev.nutclass.entity.UserOrderDetailEntity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/2/18.
 */
public class UserOrderInfoParser {
    public Object parse(String jsonString) throws JSONException {
        JsonDataList<BaseCardEntity> retObj=new JsonDataList<>();
        ArrayList<BaseCardEntity> list = new ArrayList<BaseCardEntity>();
        JSONObject jsonObject = new JSONObject(jsonString);
        JSONObject dataObject = jsonObject.optJSONObject("data");
        if(dataObject!=null){
            UserOrderDetailEntity entity = new UserOrderDetailEntity(dataObject);
            list.add(entity);
        }
        retObj.setErrorCode(UrlConst.SUCCESS_CODE);
        retObj.setList(list);
        return retObj;
    }
}
