package kr.thkim.jsonutil.to;

import com.google.gson.annotations.SerializedName;

// 획득 장소 정보
public class AcqDTO {
    @SerializedName("location_id")
    private int locationId;
    @SerializedName("amount")
    private int amount;

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public AcqDTO(int locationId, int amount) {
        this.locationId = locationId;
        this.amount = amount;
    }
}
