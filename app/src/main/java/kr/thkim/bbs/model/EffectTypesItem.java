package kr.thkim.bbs.model;

import com.google.gson.annotations.SerializedName;

public class EffectTypesItem{

	@SerializedName("eng_desc")
	private String engDesc;

	@SerializedName("name")
	private String name;

	@SerializedName("eng_name")
	private String engName;

	@SerializedName("id")
	private int id;

	@SerializedName("val_type")
	private String valType;

	@SerializedName("desc")
	private String desc;

	public String getEngDesc(){
		return engDesc;
	}

	public String getName(){
		return name;
	}

	public String getEngName(){
		return engName;
	}

	public int getId(){
		return id;
	}

	public String getValType(){
		return valType;
	}

	public String getDesc(){
		return desc;
	}
}