package kr.thkim.bbs.model;

import com.google.gson.annotations.SerializedName;

public class EffectItem{

	@SerializedName("effect_type_id")
	private int effectTypeId;

	@SerializedName("value")
	private String value;

	public int getEffectTypeId(){
		return effectTypeId;
	}

	public String getValue(){
		return value;
	}
}