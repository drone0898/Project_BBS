package kr.thkim.bbs.model;

import com.google.gson.annotations.SerializedName;

public class GenAnimalsItem{

	@SerializedName("amount")
	private int amount;

	@SerializedName("animal_id")
	private int animalId;

	public int getAmount(){
		return amount;
	}

	public int getAnimalId(){
		return animalId;
	}
}