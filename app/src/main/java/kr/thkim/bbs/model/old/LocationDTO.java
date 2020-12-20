package kr.thkim.bbs.model.old;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LocationDTO implements Serializable {

	@SerializedName("id")
	private String id;

	@SerializedName("name")
	private String name;

	@SerializedName("eng_name")
	private String engName;

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setEngName(String engName){
		this.engName = engName;
	}

	public String getEngName(){
		return engName;
	}

	@Override
 	public String toString(){
		return 
			"LocationsDTO{" + 
			"id = '" + id + '\'' + 
			",name = '" + name + '\'' + 
			",eng_name = '" + engName + '\'' + 
			"}";
		}
}