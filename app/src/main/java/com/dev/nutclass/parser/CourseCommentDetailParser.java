package com.dev.nutclass.parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2017/3/13.
 */
public class CourseCommentDetailParser extends BaseParser02 {
    @Override
    public Object parse(String jsonString) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsonString);
        JSONArray jsonArray = jsonObject.optJSONArray("data");
        JSONObject jsonObject1 = jsonArray.optJSONObject(0);

        return null;
    }
}
