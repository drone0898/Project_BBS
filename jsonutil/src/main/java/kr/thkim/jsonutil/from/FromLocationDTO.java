package kr.thkim.jsonutil.from;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class FromLocationDTO implements Serializable {

	@SerializedName("id")
	private int id;

	@SerializedName("name")
	private String name;

	@SerializedName("eng_name")
	private String engName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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