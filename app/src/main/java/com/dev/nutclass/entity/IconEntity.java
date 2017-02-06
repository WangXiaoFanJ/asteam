package com.dev.nutclass.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/1/4.
 */
public class IconEntity extends BaseCardEntity{
    public IconEntity(int cardType) {
        setCardType(cardType);
    }
    private List<String> iconList;

    public List<String> getIconList() {
        return iconList;
    }

    public void setIconList(List<String> iconList) {
        this.iconList = iconList;
    }
}
