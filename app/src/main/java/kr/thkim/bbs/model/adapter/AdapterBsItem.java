package kr.thkim.bbs.model.adapter;

import kr.thkim.bbs.model.ItemModel;

public class AdapterBsItem extends ImageButton {
    private ItemModel bsItem;

    public ItemModel getBsItem() {
        return bsItem;
    }

    public void setBsItem(ItemModel bsItem) {
        this.bsItem = bsItem;
    }
}
