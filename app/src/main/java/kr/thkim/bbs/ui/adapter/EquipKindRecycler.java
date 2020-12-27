package kr.thkim.bbs.ui.adapter;

import android.view.View;

import com.hwangjr.rxbus.RxBus;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import kr.thkim.bbs.R;
import kr.thkim.bbs.databinding.ItemImageButtonBinding;
import kr.thkim.bbs.model.adapter.ImageButton;
import kr.thkim.bbs.util.BusTag;

public class EquipKindRecycler extends
        ImageButtonRecycler<ItemImageButtonBinding, ImageButton> {

    public static EquipKindRecycler setAdapter(RecyclerView view){
        EquipKindRecycler adapter = (EquipKindRecycler) view.getAdapter();
        if(view.getAdapter() == null){
            adapter = new EquipKindRecycler();
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
        return R.layout.item_image_button;
    }

    @Override
    public void onBindViewHolder(@NonNull ImageButtonViewHolder holder, int position) {
        holder.bind(itemList.get(position),position);
        holder.itemBinding.imgView.setOnClickListener(view ->
                RxBus.get().post(BusTag.EVENT_EQUIP_KIND,itemList.get(position).getName()));
    }

}