package kr.thkim.bbs.model.adapter;

import kr.thkim.bbs.model.CharacterItem;

public class CharacterPortrait extends ImageButton {
    private CharacterItem item;

    public CharacterItem getItem() {
        return item;
    }

    public void setItem(CharacterItem item) {
        this.item = item;
    }

}
