package kr.thkim.jsonutil.to;

import com.google.gson.annotations.SerializedName;

public class DropItemInfo {
    @SerializedName("item_id")
    private int itemId;
    @SerializedName("amount")
    private int amount;

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
