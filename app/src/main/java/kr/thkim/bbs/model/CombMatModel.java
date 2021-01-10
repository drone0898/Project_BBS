package kr.thkim.bbs.model;

import com.google.gson.annotations.SerializedName;

// 조합 재료
public class CombMatModel {

	@SerializedName("item_id")
	private int itemId;

	@SerializedName("req")
	private int req;

	public int getItemId(){
		return itemId;
	}

	public int getReq(){
		return req;
	}
}