package kr.thkim.bbs.ui.adapter;

import com.hwangjr.rxbus.RxBus;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import kr.thkim.bbs.R;
import kr.thkim.bbs.databinding.ItemCharacterRoutePortraitBinding;
import kr.thkim.bbs.model.adapter.CharacterPortrait;
import kr.thkim.bbs.util.BusTag;
import kr.thkim.bbs.vm.RouteTabViewModel;

public class CharacterPortraitRecycler extends
        ImageButtonRecycler<ItemCharacterRoutePortraitBinding, CharacterPortrait, RouteTabViewModel> {

    public CharacterPortraitRecycler(RouteTabViewModel viewModel) {
        super(viewModel);
    }

    public static CharacterPortraitRecycler setAdapter(RecyclerView view, RouteTabViewModel viewModel){
        CharacterPortraitRecycler adapter = (CharacterPortraitRecycler) view.getAdapter();
        if(adapter == null){
            adapter = new CharacterPortraitRecycler(viewModel);
            if (view.getOnFlingListener() == null) {
                LinearSnapHelper snapHelper = new LinearSnapHelper();
                snapHelper.attachToRecyclerView(view);
            }

            if (view.getLayoutManager() == null) {
                LinearLayoutManager manager = new LinearLayoutManager(view.getContext());
                manager.setOrientation(LinearLayoutManager.HORIZONTAL);
                view.setLayoutManager(manager);
            }

//            if (recyclerView.getItemDecorationCount() <= 0) {
//                ShapeDrawable dw = new ShapeDrawable();
//                dw.setAlpha(0);
//                dw.setIntrinsicWidth((int) padding);
//
//                DividerItemDecoration decoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.HORIZONTAL);
//                decoration.setDrawable(dw);
//
//                recyclerView.addItemDecoration(decoration);
//            }
        }
        view.setAdapter(adapter);
        return adapter;
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.item_character_route_portrait;
    }

    @Override
    public void onBindViewHolder(@NonNull ImageButtonViewHolder holder, int position) {
        holder.bind(itemList.get(position),position);
        holder.itemBinding.imgView.setOnClickListener(view ->
                RxBus.get().post(BusTag.EVENT_CHARACTER_PORTRAIT, itemList.get(position)));
//        holder.itemBinding.imgView.setOnClickListener();
    }
}