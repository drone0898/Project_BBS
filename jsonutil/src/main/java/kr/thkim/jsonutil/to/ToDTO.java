package kr.thkim.jsonutil.to;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import kr.thkim.jsonutil.from.ItemDTO;

public class ToDTO {
    @SerializedName("items")
    private List<ToItemDTO> items;

    @SerializedName("animals")
    private List<AnimalDTO> animals;

    @SerializedName("locations")
    private List<ToLocationDTO> locations;

    @SerializedName("effect_types")
    private List<EffectTypeDTO> effectTypes;

    public void setItems(List<ToItemDTO> items){
        this.items = items;
    }

    public List<ToItemDTO> getItems(){
        return items;
    }

    public void setLocations(List<ToLocationDTO> locations){
        this.locations = locations;
    }

    public List<ToLocationDTO> getLocations(){
        return locations;
    }

    public List<EffectTypeDTO> getEffectTypes() {
        return effectTypes;
    }

    public void setEffectTypes(List<EffectTypeDTO> effectTypes) {
        this.effectTypes = effectTypes;
    }

    public List<AnimalDTO> getAnimals() {
        return animals;
    }

    public void setAnimals(List<AnimalDTO> animals) {
        this.animals = animals;
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
