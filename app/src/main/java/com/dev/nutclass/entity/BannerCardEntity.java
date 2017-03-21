package com.dev.nutclass.entity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BannerCardEntity extends BaseCardEntity {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private List<ImageEntity> list = null;
	private List<ImageEntityNew> imgList= null;

	public List<ImageEntityNew> getImgList() {
		return imgList;
	}

	public void setImgList(List<ImageEntityNew> imgList) {
		this.imgList = imgList;
	}

	public BannerCardEntity() {
		setCardType(BaseCardEntity.CARD_TYPE_BANNER);
	}

	public BannerCardEntity(int cardType, JSONArray jsonArray) {
		setCardType(cardType);
		optJsonObjBanner(jsonArray);
	}

	private void optJsonObjBanner(JSONArray jsonArray) {
		List<ImageEntityNew> list = new ArrayList<>();
		for(int i = 0;i<jsonArray.length();i++){
			JSONObject jsonObject = jsonArray.optJSONObject(i);
			ImageEntityNew entityNew = new ImageEntityNew(jsonObject);
			list.add(entityNew);
		}
		setImgList(list);
	}


	//old
//	public BannerCardEntity(int cardType, JSONArray jsonArray) {
//		setCardType(cardType);
//		optJsonObjNew(jsonArray);
//	}
	//new
	public BannerCardEntity(JSONArray jsonArray) {
		setCardType(BaseCardEntity.CARD_TYPE_BANNER);
		optJsonObjNew(jsonArray);
	}


	public void setList(List<ImageEntity> list) {
		this.list = list;
	}

	// Banner数据解析
	public void optJsonObj(JSONArray jsonArray) {
		if (jsonArray == null)
			return;
		if (list == null) {
			list = new ArrayList<ImageEntity>();
		}
		for (int i = 0; i < jsonArray.length(); i++) {
			try {
				ImageEntity entity = new ImageEntity(jsonArray.getJSONObject(i));
				list.add(entity);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
	//Banner数据解析new
	public void optJsonObjNew(JSONArray jsonArray) {
		if (jsonArray == null)
			return;
		if (list == null) {
			list = new ArrayList<ImageEntity>();
		}
		for (int i = 0; i < jsonArray.length(); i++) {
			try {
				ImageEntity entity = new ImageEntity();
				if(getCardType() == 2){
					entity.optJsonObjAD(jsonArray.getJSONObject(i));
				}else {
					entity.optJsonObjNew(jsonArray.getJSONObject(i));
				}
				list.add(entity);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
	//Banner数据解析JD
		public void optJsonObjJD(JSONArray jsonArray) {
			if (jsonArray == null)
				return;
			if (list == null) {
				list = new ArrayList<ImageEntity>();
			}
			for (int i = 0; i < jsonArray.length(); i++) {
				try {
					JSONObject jsonObj=jsonArray.getJSONObject(i);
					ImageEntity entity = new ImageEntity();
					entity.setSmallPath(jsonObj.optString("banner_image"));
					list.add(entity);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
}
