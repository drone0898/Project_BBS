package kr.thkim.bbs.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class LocationModel {

	@SerializedName("drop_items")
	private List<DropItemModel> dropItems;

	@SerializedName("name")
	private String name;

	@SerializedName("eng_name")
	private String engName;

	@SerializedName("gen_animals")
	private List<GenAnimalModel> genAnimals;

	@SerializedName("id")
	private int id;

	public List<DropItemModel> getDropItems(){
		return dropItems;
	}

	public String getName(){
		return name;
	}

	public String getEngName(){
		return engName;
	}

	public List<GenAnimalModel> getGenAnimals(){
		return genAnimals;
	}

	public int getId(){
		return id;
	}
}