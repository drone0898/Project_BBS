package kr.thkim.bbs.model.adapter;

import kr.thkim.bbs.model.CharacterModel;

public class CharacterPortrait extends ImageButton {
    private CharacterModel item;

    public CharacterModel getItem() {
        return item;
    }

    public void setItem(CharacterModel item) {
        this.item = item;
    }

}
