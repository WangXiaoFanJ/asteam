package com.dev.nutclass.parser;

import com.dev.nutclass.entity.SimpleEntity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2017/3/1.
 */
public class SimpleParser extends BaseParser02 {
    @Override
    public Object parse(String jsonString) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsonString);
        SimpleEntity entity = new SimpleEntity(jsonObject);
        return entity;
    }
}
