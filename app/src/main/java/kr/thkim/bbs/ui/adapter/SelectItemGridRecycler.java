package kr.thkim.bbs.ui.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import kr.thkim.bbs.R;
import kr.thkim.bbs.databinding.ItemSelectItemGridBinding;
import kr.thkim.bbs.model.adapter.AdapterBsItem;

public class SelectItemGridRecycler extends ImageButtonRecycler<ItemSelectItemGridBinding, AdapterBsItem> {

    public static ItemOneLineRecycler setAdapter(RecyclerView view) {
        ItemOneLineRecycler adapter = (ItemOneLineRecycler) view.getAdapter();
        if (view.getAdapter() == null) {
            adapter = new ItemOneLineRecycler();
            if (view.getOnFlingListener() == null) {
                LinearSnapHelper snapHelper = new LinearSnapHelper();
                snapHelper.attachToRecyclerView(view);
            }

            if (view.getLayoutManager() == null) {
                LinearLayoutManager manager = new LinearLayoutManager(view.getContext());
                manager.setOrientation(LinearLayoutManager.HORIZONTAL);
                view.setLayoutManager(manager);
            }
        }
        view.setAdapter(adapter);
        return adapter;
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.item_select_item_grid;
    }

    @Override
    public void onBindViewHolder(@NonNull ImageButtonViewHolder holder, int position) {
        holder.bind(itemList.get(position),position);
    }
}
