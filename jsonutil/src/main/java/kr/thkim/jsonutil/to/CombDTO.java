package kr.thkim.jsonutil.to;

import com.google.gson.annotations.SerializedName;

// 조합 재료
public class CombDTO {
    @SerializedName("item_id")
    private int itemId; // 재료 아이템 id
    @SerializedName("req")
    private int req; // 몇 개

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getReq() {
        return req;
    }

    public void setReq(int req) {
        this.req = req;
    }

    public CombDTO(int itemId, int req) {
        this.itemId = itemId;
        this.req = req;
    }
}
