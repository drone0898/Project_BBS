package kr.thkim.bbs.model;

import com.google.gson.annotations.SerializedName;

public class DropItemModel {

	@SerializedName("amount")
	private int amount;

	@SerializedName("item_id")
	private int itemId;

	public int getAmount(){
		return amount;
	}

	public int getItemId(){
		return itemId;
	}
}