package kr.thkim.bbs.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import kr.thkim.bbs.BR;

public abstract class ImageButtonRecycler <T extends ViewDataBinding,I extends ImageButton>
        extends RecyclerView.Adapter<ImageButtonRecycler<T,I>.ImageButtonViewHolder> {

    protected List<I> itemList;

    protected abstract int getLayoutResourceId();

    public void setItemList(List<I> itemList){
        if(itemList == null){
            itemList = new ArrayList<>();
        }
        this.itemList = itemList;
        notifyDataSetChanged();
    }

    public void addItem(I item){
        int pos = itemList.size();
        itemList.add(item);
        notifyItemInserted(pos);
    }

    public void removeItemAt(int position){
        itemList.remove(position);
        notifyItemRemoved(position);
    }

    @NonNull
    @Override
    public ImageButtonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        T binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), getLayoutResourceId(), parent, false);
        return new ImageButtonViewHolder(binding);
    }

    @Override
    public abstract void onBindViewHolder(@NonNull ImageButtonRecycler<T,I>.ImageButtonViewHolder holder, int position);

    @Override
    public int getItemCount() {
        return itemList == null ? 0 : itemList.size();
    }

    class ImageButtonViewHolder extends RecyclerView.ViewHolder{

        T itemBinding;

        public ImageButtonViewHolder(@NonNull T binding) {
            super(binding.getRoot());
            this.itemBinding = binding;
        }

        void bind(I item,int position){
            itemBinding.setVariable(BR.item, item);
            itemBinding.setVariable(BR.position, position);
        }
    }
}
