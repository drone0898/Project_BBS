package kr.thkim.bbs.ui.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import kr.thkim.bbs.R;
import kr.thkim.bbs.databinding.ItemCharacterRoutePortraitBinding;

public class CharacterPortraitRecycler extends
        ImageButtonRecycler<ItemCharacterRoutePortraitBinding,CharacterPortrait> {

    public static CharacterPortraitRecycler setAdapter(RecyclerView view){
        CharacterPortraitRecycler adapter = (CharacterPortraitRecycler) view.getAdapter();
        if(view.getAdapter() == null){
            adapter = new CharacterPortraitRecycler();
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
        return adapter;
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.item_character_route_portrait;
    }

    @Override
    public void onBindViewHolder(@NonNull ImageButtonViewHolder holder, int position) {
        holder.bind(itemList.get(position),position);
//        holder.itemBinding.imgView.setOnClickListener();
    }

}