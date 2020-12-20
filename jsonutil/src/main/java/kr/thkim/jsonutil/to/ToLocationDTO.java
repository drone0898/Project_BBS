package kr.thkim.jsonutil.to;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ToLocationDTO {
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("eng_name")
    private String engName;

    @SerializedName("drop_items")
    private List<DropItemInfo> dropItems = new ArrayList<>(); // 나오는 아이템 id,수

    @SerializedName("gen_animals")
    private List<GenAnimalInfo> genAnimals = new ArrayList<>(); // 나오는 동물 id,수

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setEngName(String engName){
        this.engName = engName;
    }

    public String getEngName(){
        return engName;
    }

    public List<DropItemInfo> getDropItems() {
        return dropItems;
    }

    public void setDropItems(List<DropItemInfo> dropItems) {
        this.dropItems = dropItems;
    }

    public List<GenAnimalInfo> getGenAnimals() {
        return genAnimals;
    }

    public void setGenAnimals(List<GenAnimalInfo> genAnimals) {
        this.genAnimals = genAnimals;
    }
}
