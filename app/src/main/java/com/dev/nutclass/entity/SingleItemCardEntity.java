package com.dev.nutclass.entity;

import org.json.JSONArray;

public class SingleItemCardEntity extends BaseCardEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String desc;
	private String tag;
	private boolean isRecommend;
	private boolean showTag=true;
	private boolean selected=false;
	private boolean parent=false;
	
	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public boolean isParent() {
		return parent;
	}

	public void setParent(boolean parent) {
		this.parent = parent;
	}

	private String inputDesc;
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public boolean isRecommend() {
		return isRecommend;
	}

	public void setRecommend(boolean isRecommend) {
		this.isRecommend = isRecommend;
	}

	public SingleItemCardEntity() {
		setCardType(CARD_TYPE_SINGLE_ITEM);
	}
	public SingleItemCardEntity(String desc) {
		setCardType(CARD_TYPE_SINGLE_ITEM);
		setDesc(desc);
	}
	public SingleItemCardEntity(int cardType) {
		setCardType(cardType);
	}

	public SingleItemCardEntity(int cardType, JSONArray jsonArray) {
		setCardType(cardType);
	}

	public boolean isShowTag() {
		return showTag;
	}

	public void setShowTag(boolean showTag) {
		this.showTag = showTag;
	}

	public String getInputDesc() {
		return inputDesc;
	}

	public void setInputDesc(String inputDesc) {
		this.inputDesc = inputDesc;
	}
}
