package kr.thkim.jsonutil.from;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import kr.thkim.jsonutil.to.EffectTypeDTO;

public class FromDTO implements Serializable {

	@SerializedName("items")
	private List<ItemDTO> items;

	@SerializedName("locations")
	private List<FromLocationDTO> locations;

	@SerializedName("effect_types")
	private List<EffectTypeDTO> effectTypes;

	public void setItems(List<ItemDTO> items){
		this.items = items;
	}

	public List<ItemDTO> getItems(){
		return items;
	}

	public void setLocations(List<FromLocationDTO> locations){
		this.locations = locations;
	}

	public List<FromLocationDTO> getLocations(){
		return locations;
	}

	public List<EffectTypeDTO> getEffectTypes() {
		return effectTypes;
	}

	public void setEffectTypes(List<EffectTypeDTO> effectTypes) {
		this.effectTypes = effectTypes;
	}

	@Override
 	public String toString(){
		return 
			"BSItemDTO{" + 
			"items = '" + items + '\'' + 
			",locations = '" + locations + '\'' + 
			"}";
		}
}