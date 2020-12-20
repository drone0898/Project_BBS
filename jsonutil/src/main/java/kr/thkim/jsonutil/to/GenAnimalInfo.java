package kr.thkim.jsonutil.to;

import com.google.gson.annotations.SerializedName;

public class GenAnimalInfo {
    @SerializedName("animal_id")
    private int animalId;
    @SerializedName("amount")
    private int amount;

    public int getAnimalId() {
        return animalId;
    }

    public void setAnimalId(int animalId) {
        this.animalId = animalId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public GenAnimalInfo(int animalId, int amount) {
        this.animalId = animalId;
        this.amount = amount;
    }
}
