package kr.thkim.bbs.binding;

import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.hwangjr.rxbus.RxBus;

import java.util.Set;

import androidx.core.content.ContextCompat;
import androidx.databinding.BindingAdapter;
import androidx.lifecycle.LiveData;
import kr.thkim.bbs.BaseApplication;
import kr.thkim.bbs.R;
import kr.thkim.bbs.model.adapter.ImageButton;
import kr.thkim.bbs.vm.RouteViewModel;

import static kr.thkim.bbs.util.BusTag.EVENT_TOUCH_MAP_ID;

public final class GlobalBinding {

    @BindingAdapter(value = {"mapId"}, requireAll = true)
    public static void setHighlighTouchListener(View self, int mapId) {
        self.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() != MotionEvent.ACTION_DOWN) {
                    return false;
                }

//                Bitmap bmp = Bitmap.createBitmap(view.getDrawingCache());
//                int color = bmp.getPixel((int) event.getX(), (int) event.getY());
//                if (color == Color.TRANSPARENT) {
//                    return false; // 공백터치시 무시
//                }

                int[] l = new int[2];
                view.getLocationOnScreen(l);
                Rect rect = new Rect(
                        l[0], l[1],
                        l[0] + view.getWidth(),
                        l[1] + view.getHeight());
                Rect half = new Rect(
                        rect.centerX() - rect.width() / 4,
                        rect.centerY() - rect.height() / 4,
                        rect.centerX() + rect.width() / 4,
                        rect.centerY() + rect.height() / 4);
                if (half.contains((int) event.getRawX(), (int) event.getRawY())) {
                    view.setAlpha(
                            view.getAlpha() > 0 ?
                                    0f : 1f
                    );
                    RxBus.get().post(EVENT_TOUCH_MAP_ID, (Integer) mapId);
                    return true;
                }
                return false;
            }
        });
    }

    @BindingAdapter(value = {"srcItemGlide"})
    public static void setSrcItemGlide(View view, ImageButton item) {
        if (item == null) {
            return;
        }
        if (item.getSrc() != 0) {
//            Glide.with(view).load(item.getSrc()).placeholder(R.drawable.ic_wifi_off).thumbnail(.1f).into((ImageView) view);
//            --> 위의 코드를 쓰면 리사이클러 맨마지막 아이템이 직전아이템이랑 똑같은 버그있음 엠마 이미지가 나와야하는데 실비아가나옴 ㅡㅡ;
            // http://bumptech.github.io/glide/doc/getting-started.html#listview-and-recyclerview clear문제..?
            // 그러나 해결안됨.. 일단 해결보류.
            ImageView iv = (ImageView) view;
            iv.setImageDrawable(ContextCompat.getDrawable(BaseApplication.getBaseApplication(), item.getSrc()));
        } else if (item.getSrcUrl() != null && !item.getSrcUrl().equals("")) {
            Glide.with(view).load(item.getSrcUrl()).placeholder(R.drawable.ic_wifi_off).thumbnail(.1f).into((ImageView) view);
        }
    }

    @BindingAdapter(value = {"srcByResId"})
    public static void setSrcByResId(View view, int resId) {
        if (resId != 0) {
            Glide.with(view).load(resId).placeholder(R.drawable.ic_wifi_off).thumbnail(.1f).into((ImageView) view);
//            --> 위의 코드를 쓰면 리사이클러 맨마지막 아이템이 직전아이템이랑 똑같은 버그있음 엠마 이미지가 나와야하는데 실비아가나옴 ㅡㅡ;
            // http://bumptech.github.io/glide/doc/getting-started.html#listview-and-recyclerview clear문제..?
            // 그러나 해결안됨.. 일단 해결보류.
//            ImageView iv = (ImageView) view;
//            iv.setImageDrawable(ContextCompat.getDrawable(BaseApplication.getBaseApplication(),resId));
        }
    }


    @BindingAdapter(value = {"setFilterChipItems", "viewModel"})
    public static void setFilterChipItems(ChipGroup view, LiveData<Set<String>> chipItems,
                                          RouteViewModel viewModel) {
        if (chipItems != null && chipItems.getValue() != null) {
            Integer idx = 0;
            for (String item : chipItems.getValue()) {
                Chip chip = new Chip(view.getContext());
                chip.setText(item);
                chip.setCheckable(true);
                int finalIdx = idx;
                chip.setOnCheckedChangeListener((buttonView, isChecked) -> {
                    if (isChecked) {
                        viewModel.addSelectedEquipList(item, finalIdx);
                    } else {
                        viewModel.removeSelectedEquipList(item, finalIdx);
                    }
                });
                view.addView(chip);
                chip.setChecked(idx.equals(viewModel.currentSelItemIndex.getValue()));
                idx++;
            }
        }
    }


}
