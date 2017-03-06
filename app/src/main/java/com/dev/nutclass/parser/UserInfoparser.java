package com.dev.nutclass.parser;

import com.dev.nutclass.constants.UrlConst;
import com.dev.nutclass.entity.BaseCardEntity;
import com.dev.nutclass.entity.JsonDataList;
import com.dev.nutclass.entity.UserInfoEntity;
import com.dev.nutclass.utils.SharedPrefUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/1.
 */
public class UserInfoparser extends BaseParser02 {
    @Override
    public Object parse(String jsonString) throws JSONException {
        JsonDataList<BaseCardEntity> retObj = new JsonDataList<BaseCardEntity>();
        ArrayList<BaseCardEntity> list = new ArrayList<BaseCardEntity>();
        JSONObject jsonObject = new JSONObject(jsonString);
        int status = Integer.parseInt(jsonObject.optString("status"));
        JSONObject jsonObject1 = jsonObject.optJSONObject("data");
        if(jsonObject1!=null){
            String token = jsonObject1.optString("token");
            UserInfoEntity entity = new UserInfoEntity(jsonObject1);
            SharedPrefUtil.getInstance().setToken(token);
            SharedPrefUtil.getInstance().setMobile(jsonObject1.optString("user_phone"));
            SharedPrefUtil.getInstance().setUserSession(entity.buildJsonObject().toString());
            retObj.setObj(entity);
            retObj.setErrorCode(status);
            list.add(entity);
        }
        retObj.setList(list);
        return retObj;
    }
}
