package kr.thkim.bbs.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

//캐싱 db를 위한 모델
public class BserDBModel {

	@SerializedName("locations")
	private List<LocationModel> locations;

	@SerializedName("animals")
	private List<AnimalModel> animals;

	@SerializedName("effect_types")
	private List<EffectTypesModel> effectTypes;

	@SerializedName("items")
	private List<ItemModel> items;

	@SerializedName("characters")
	private List<CharacterModel> characters;

	@SerializedName("weaponkinds")
	private List<String> weaponkinds;



	public List<LocationModel> getLocations(){
		return locations;
	}

	public List<AnimalModel> getAnimals(){
		return animals;
	}

	public List<EffectTypesModel> getEffectTypes(){
		return effectTypes;
	}

	public List<ItemModel> getItems(){
		return items;
	}

	public List<CharacterModel> getCharacters() {
		return characters;
	}

	public List<String> getWeaponkinds() {
		return weaponkinds;
	}
}