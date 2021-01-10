package kr.thkim.bbs.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

//야생동물 db
public class AnimalModel {

	@SerializedName("animal_name")
	private String animalName;

	@SerializedName("drop_items")
	private List<Integer> dropItems;

	@SerializedName("animal_id")
	private int animalId;

	public String getAnimalName(){
		return animalName;
	}

	public List<Integer> getDropItems(){
		return dropItems;
	}

	public int getAnimalId(){
		return animalId;
	}
}