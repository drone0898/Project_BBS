package kr.thkim.bbs.ui.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import kr.thkim.bbs.R;
import kr.thkim.bbs.databinding.ItemSelectItemGridBinding;
import kr.thkim.bbs.model.adapter.AdapterBsItem;
import kr.thkim.bbs.vm.RouteViewModel;

public class SelectItemGridRecycler extends ImageButtonRecycler<ItemSelectItemGridBinding, AdapterBsItem, RouteViewModel> {

    public static final int SELECT_ITEM_SPAN_COUNT = 6;

    public SelectItemGridRecycler(RouteViewModel viewModel) {
        super(viewModel);
    }

    public static SelectItemGridRecycler setAdapter(RecyclerView view, RouteViewModel viewModel) {
        SelectItemGridRecycler adapter = (SelectItemGridRecycler) view.getAdapter();
        if (adapter == null) {
            adapter = new SelectItemGridRecycler(viewModel);
            if (view.getOnFlingListener() == null) {
                LinearSnapHelper snapHelper = new LinearSnapHelper();
                snapHelper.attachToRecyclerView(view);
            }

            if (view.getLayoutManager() == null) {
                GridLayoutManager gridLayoutManager = new GridLayoutManager(view.getContext(),
                        SELECT_ITEM_SPAN_COUNT, GridLayoutManager.VERTICAL, false);
                view.setLayoutManager(gridLayoutManager);
            }
            if (view.getItemDecorationCount() <= 0) {
                int sideMargin = (int) view.getResources().getDimension(R.dimen.select_item_grid_margin);
                GridSpacingItemDecoration decoration = new GridSpacingItemDecoration(SELECT_ITEM_SPAN_COUNT, sideMargin, true);
                view.addItemDecoration(decoration);
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
