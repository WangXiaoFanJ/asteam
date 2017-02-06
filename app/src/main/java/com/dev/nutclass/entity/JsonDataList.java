package com.dev.nutclass.entity;

import org.json.JSONObject;

import java.util.ArrayList;

public class JsonDataList<T> extends JsonResult<JsonDataList<T>>{
	
	private ArrayList<T> mList;
	private ArrayList<ArrayList<BaseCardEntity>> brandList;
	private T obj;

	public ArrayList<T> getList() {
		return mList;
	}
	public void setList(ArrayList<T> mList) {
		this.mList = mList;
	}

	public ArrayList<ArrayList<BaseCardEntity>> getBrandList() {
		return brandList;
	}

	public void setBrandList(ArrayList<ArrayList<BaseCardEntity>> brandList) {
		this.brandList = brandList;
	}
	public T getObj() {
		return obj;
	}
	public void setObj(T obj) {
		this.obj = obj;
	}

}
