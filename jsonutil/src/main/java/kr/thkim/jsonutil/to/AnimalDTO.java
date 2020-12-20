package kr.thkim.jsonutil.to;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class AnimalDTO {
    @SerializedName("animal_id")
    private int animalId;
    @SerializedName("animal_name")
    private String animalName;
    @SerializedName("animal_eng_name")
    private String animalEngName;
    @SerializedName("drop_items")
    private List<Integer> dropItems = new ArrayList<>();

    public int getAnimalId() {
        return animalId;
    }

    public void setAnimalId(int animalId) {
        this.animalId = animalId;
    }

    public String getAnimalName() {
        return animalName;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public List<Integer> getDropItems() {
        return dropItems;
    }

    public void setDropItems(List<Integer> dropItems) {
        this.dropItems = dropItems;
    }

    public String getAnimalEngName() {
        return animalEngName;
    }

    public void setAnimalEngName(String animalEngName) {
        this.animalEngName = animalEngName;
    }
}
