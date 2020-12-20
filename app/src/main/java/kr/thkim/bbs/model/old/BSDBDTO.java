package kr.thkim.bbs.model.old;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class BSDBDTO implements Serializable {

	@SerializedName("items")
	private List<ItemDTO> items;

	@SerializedName("locations")
	private List<LocationDTO> locations;

	public void setItems(List<ItemDTO> items){
		this.items = items;
	}

	public List<ItemDTO> getItems(){
		return items;
	}

	public void setLocations(List<LocationDTO> locations){
		this.locations = locations;
	}

	public List<LocationDTO> getLocations(){
		return locations;
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