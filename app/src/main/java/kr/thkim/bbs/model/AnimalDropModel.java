package kr.thkim.bbs.model;

import com.google.gson.annotations.SerializedName;

// 야생동물 드랍 아이템
public class AnimalDropModel {

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