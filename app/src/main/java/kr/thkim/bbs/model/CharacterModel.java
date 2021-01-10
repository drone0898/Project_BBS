package kr.thkim.bbs.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

// 캐릭터
public class CharacterModel {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("eng_name")
    private String eng_name;
    @SerializedName("weapons")
    private List<String> weapons;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEng_name() {
        return eng_name;
    }

    public List<String> getWeapons() {
        return weapons;
    }
}
