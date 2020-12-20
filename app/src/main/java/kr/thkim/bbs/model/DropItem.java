package kr.thkim.bbs.model;

import com.google.gson.annotations.SerializedName;

public class DropItem{

	@SerializedName("chance")
	private double chance;

	@SerializedName("animal_id")
	private int animalId;

	public double getChance(){
		return chance;
	}

	public int getAnimalId(){
		return animalId;
	}
}