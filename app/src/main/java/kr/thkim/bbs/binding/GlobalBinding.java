package kr.thkim.bbs.binding;

import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;

import com.hwangjr.rxbus.RxBus;

import androidx.databinding.BindingAdapter;

import static kr.thkim.bbs.util.BusTag.EVENT_TOUCH_MAP_ID;

public final class GlobalBinding {

    @BindingAdapter(value={"mapId"},requireAll = true)
    public static void setHighlighTouchListener(View self, int mapId){
        self.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() != MotionEvent.ACTION_DOWN){
                    return false;
                }

//                Bitmap bmp = Bitmap.createBitmap(view.getDrawingCache());
//                int color = bmp.getPixel((int) event.getX(), (int) event.getY());
//                if (color == Color.TRANSPARENT) {
//                    return false; // 공백터치시 무시
//                }

                int[] l =new int[2];
                view.getLocationOnScreen(l);
                Rect rect = new Rect(
                        l[0],l[1],
                        l[0]+view.getWidth(),
                        l[1]+view.getHeight());
                Rect half = new Rect(
                        rect.centerX()-rect.width()/4,
                        rect.centerY()-rect.height()/4,
                        rect.centerX()+rect.width()/4,
                        rect.centerY()+rect.height()/4);
                if(half.contains((int)event.getRawX(),(int)event.getRawY())){
                    RxBus.get().post(EVENT_TOUCH_MAP_ID,(Integer)mapId);
                    view.setAlpha(
                            view.getAlpha()>0 ?
                                    0f:1f
                    );
                    return true;
                }
                return false;
            }
        });
    }
}
