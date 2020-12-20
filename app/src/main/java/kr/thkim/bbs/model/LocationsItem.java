package kr.thkim.bbs.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class LocationsItem{

	@SerializedName("drop_items")
	private List<DropItemsItem> dropItems;

	@SerializedName("name")
	private String name;

	@SerializedName("eng_name")
	private String engName;

	@SerializedName("gen_animals")
	private List<GenAnimalsItem> genAnimals;

	@SerializedName("id")
	private int id;

	public List<DropItemsItem> getDropItems(){
		return dropItems;
	}

	public String getName(){
		return name;
	}

	public String getEngName(){
		return engName;
	}

	public List<GenAnimalsItem> getGenAnimals(){
		return genAnimals;
	}

	public int getId(){
		return id;
	}
}