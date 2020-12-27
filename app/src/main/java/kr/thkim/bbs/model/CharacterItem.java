package kr.thkim.bbs.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CharacterItem implements Parcelable {
    @SerializedName("id")
    private final int id;
    @SerializedName("name")
    private final String name;
    @SerializedName("eng_name")
    private final String eng_name;
    @SerializedName("weapons")
    private final List<String> weapons;

    protected CharacterItem(Parcel in) {
        id = in.readInt();
        name = in.readString();
        eng_name = in.readString();
        weapons = in.createStringArrayList();
    }

    public static final Creator<CharacterItem> CREATOR = new Creator<CharacterItem>() {
        @Override
        public CharacterItem createFromParcel(Parcel in) {
            return new CharacterItem(in);
        }

        @Override
        public CharacterItem[] newArray(int size) {
            return new CharacterItem[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(eng_name);
        parcel.writeStringList(weapons);
    }
}
