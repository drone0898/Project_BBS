package kr.thkim.jsonutil.to;

import com.google.gson.annotations.SerializedName;

// 효과
public class EffectDTO {

    @SerializedName("effect_type_id")
    private int effectTypeId;
    @SerializedName("value")
    private String value; // 효과값

    public int getEffectTypeId() {
        return effectTypeId;
    }

    public void setEffectTypeId(int effectTypeId) {
        this.effectTypeId = effectTypeId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public EffectDTO(int effectTypeId, String value) {
        this.effectTypeId = effectTypeId;
        this.value = value;
    }
}