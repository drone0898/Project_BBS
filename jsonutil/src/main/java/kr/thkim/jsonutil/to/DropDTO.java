package kr.thkim.jsonutil.to;

import com.google.gson.annotations.SerializedName;

// 드랍 장소
public class DropDTO {
    @SerializedName("animal_id")
    private int animalId;
    @SerializedName("chance")
    private float chance;

    public int getAnimalId() {
        return animalId;
    }

    public void setAnimalId(int animalId) {
        this.animalId = animalId;
    }

    public float getChance() {
        return chance;
    }

    public void setChance(float chance) {
        this.chance = chance;
    }

    public DropDTO(int animalId, float chance) {
        this.animalId = animalId;
        this.chance = chance;
    }
}
