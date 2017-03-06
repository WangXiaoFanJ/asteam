package com.dev.nutclass.entity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/23.
 */
public class HomeIconEntity extends BaseCardEntity {
    private List<HomeIconData> homeIconList;
    public HomeIconEntity(JSONArray jsonArray) {
        setCardType(BaseCardEntity.CARD_TYPE_HOME_ICON_VIEW);
        optJsonObj(jsonArray);
    }

    private void optJsonObj(JSONArray jsonArray) {
        List<HomeIconData> list = new ArrayList<>();
        for(int i = 0;i<jsonArray.length();i++){
            JSONObject jsonObj = jsonArray.optJSONObject(i);
            HomeIconData homeIconData = new HomeIconData(jsonObj);
            list.add(homeIconData);
        }
        setHomeIconList(list);
    }
    public class HomeIconData{
        private String iconUrl;
        private String cateName;
        public HomeIconData(JSONObject jsonObj) {
            setIconUrl(jsonObj.optString("image_url"));
            setCateName(jsonObj.optString("catename"));
        }

        public String getIconUrl() {
            return iconUrl;
        }

        public void setIconUrl(String iconUrl) {
            this.iconUrl = iconUrl;
        }

        public String getCateName() {
            return cateName;
        }

        public void setCateName(String cateName) {
            this.cateName = cateName;
        }
    }

    public List<HomeIconData> getHomeIconList() {
        return homeIconList;
    }

    public void setHomeIconList(List<HomeIconData> homeIconList) {
        this.homeIconList = homeIconList;
    }
}
