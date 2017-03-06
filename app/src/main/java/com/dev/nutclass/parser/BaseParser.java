package com.dev.nutclass.parser;

import android.content.Context;

import com.dev.nutclass.constants.UrlConst;
import com.dev.nutclass.entity.JsonResult;
import com.dev.nutclass.utils.LogUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public abstract class BaseParser<T>  {
	public static final String TAG = "BaseParser";
	public static String replacement = "";
	protected Context mContext;

	public abstract Object parse(JSONArray jsonArray) throws JSONException;
	public BaseParser() {
	}

	public BaseParser(Context context) {
		this.mContext = context;
	}
}
