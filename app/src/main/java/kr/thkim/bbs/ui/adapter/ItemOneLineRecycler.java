package kr.thkim.bbs.ui.adapter;

import com.hwangjr.rxbus.RxBus;

import java.util.Collections;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import kr.thkim.bbs.R;
import kr.thkim.bbs.databinding.ItemOneLineBsItemBinding;
import kr.thkim.bbs.model.adapter.AdapterBsItem;

public class ItemOneLineRecycler extends ImageButtonRecycler<ItemOneLineBsItemBinding, AdapterBsItem>
        implements DraggableTouchHelper.DraggableTouchHelpListener {

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

            ItemTouchHelper helper = new ItemTouchHelper(new DraggableTouchHelper(adapter));
            helper.attachToRecyclerView(view);
        }
        view.setAdapter(adapter);
        return adapter;
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.item_one_line_bs_item;
    }

    @Override
    public void onBindViewHolder(@NonNull ImageButtonViewHolder holder, int position) {
        AdapterBsItem item = itemList.get(position);
        holder.bind(item,position);
        holder.itemBinding.imgView.setOnClickListener(view->
                RxBus.get().post(item.getClickEvent(),item));
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(itemList, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(itemList, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
        // TODO : 최적경로 변경알림 필요
    }
}
