package kr.thkim.bbs.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class BserDBModel {

	@SerializedName("locations")
	private List<LocationsItem> locations;

	@SerializedName("animals")
	private List<AnimalsItem> animals;

	@SerializedName("effect_types")
	private List<EffectTypesItem> effectTypes;

	@SerializedName("items")
	private List<ItemsItem> items;

	@SerializedName("characters")
	private List<CharacterItem> characters;



	public List<LocationsItem> getLocations(){
		return locations;
	}

	public List<AnimalsItem> getAnimals(){
		return animals;
	}

	public List<EffectTypesItem> getEffectTypes(){
		return effectTypes;
	}

	public List<ItemsItem> getItems(){
		return items;
	}
}