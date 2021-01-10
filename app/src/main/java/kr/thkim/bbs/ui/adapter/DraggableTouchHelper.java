package kr.thkim.bbs.ui.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class DraggableTouchHelper extends ItemTouchHelper.Callback {

    private DraggableTouchHelpListener listener;

    public DraggableTouchHelper(DraggableTouchHelpListener listener){
        this.listener = listener;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        //이벤트 방향 설정
        //아이템 좌우 배치
        int flagDrag = ItemTouchHelper.START | ItemTouchHelper.END; //item drag
        int flagSwipe = ItemTouchHelper.UP | ItemTouchHelper.DOWN; //item swipe


        return makeMovementFlags(flagDrag,flagSwipe);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        listener.onItemMove(viewHolder.getAdapterPosition(),target.getAdapterPosition());
        return true;
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
    }

    public interface DraggableTouchHelpListener {
        void onItemMove(int fromPosition, int toPosition);
    }
}
