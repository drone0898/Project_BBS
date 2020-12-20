package kr.thkim.jsonutil.to;

import com.google.gson.annotations.SerializedName;

public class EffectTypeDTO {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name; // 효과이름
    @SerializedName("eng_name")
    private String engName;
    @SerializedName("desc")
    private String desc; // 효과 설명
    @SerializedName("eng_desc")
    private String engDesc;
    @SerializedName("val_type")
    private String valType; // 효과 타입

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEngName() {
        return engName;
    }

    public void setEngName(String engName) {
        this.engName = engName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getEngDesc() {
        return engDesc;
    }

    public void setEngDesc(String engDesc) {
        this.engDesc = engDesc;
    }

    public String getValType() {
        return valType;
    }

    public void setValType(String valType) {
        this.valType = valType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
