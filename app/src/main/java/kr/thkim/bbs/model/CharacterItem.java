package kr.thkim.bbs.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CharacterItem {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("eng_name")
    private String eng_name;
    @SerializedName("weapons")
    private List<String> weapons;
}
