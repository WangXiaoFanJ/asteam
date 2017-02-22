package com.dev.nutclass.entity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/20.
 */
public class ServiceFeatureEntity extends BaseCardEntity {
    private List<ServiceFeatureData> dataList;


    public ServiceFeatureEntity(JSONObject jsonObject) {
        setCardType(BaseCardEntity.CARD_TYPE_SCHOOL_FEATURE_VIEW);
        optJsonObj(jsonObject);
    }

    private void optJsonObj(JSONObject jsonObject) {
        List<ServiceFeatureData> list = new ArrayList<>();
        JSONArray jsonArray = jsonObject.optJSONArray("service_characteristic");
        for(int i=0;i<jsonArray.length();i++){
            JSONObject jsonObj = jsonArray.optJSONObject(i);
            ServiceFeatureData data = new ServiceFeatureData(jsonObj);
            list.add(data);
        }
        setDataList(list);
    }
    public List<ServiceFeatureData> getDataList() {
        return dataList;
    }

    public void setDataList(List<ServiceFeatureData> dataList) {
        this.dataList = dataList;
    }

    public class ServiceFeatureData{
        private String serviceInfo;
        public ServiceFeatureData(JSONObject jsonObject) {
            setServiceInfo(jsonObject.optString("ser1"));
        }

        public String getServiceInfo() {
            return serviceInfo;
        }

        public void setServiceInfo(String serviceInfo) {
            this.serviceInfo = serviceInfo;
        }
    }
}
