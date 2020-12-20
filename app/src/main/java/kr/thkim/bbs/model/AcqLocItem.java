package kr.thkim.bbs.model;

import com.google.gson.annotations.SerializedName;

public class AcqLocItem{

	@SerializedName("amount")
	private int amount;

	@SerializedName("location_id")
	private int locationId;

	public int getAmount(){
		return amount;
	}

	public int getLocationId(){
		return locationId;
	}
}