package com.dev.nutclass.parser;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by Administrator on 2017/3/1.
 */
public abstract class BaseParser02<T>{
    public static final String TAG = "BaseParser02";
    public abstract Object parse(String jsonString) throws JSONException;
}
