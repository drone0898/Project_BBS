package kr.thkim.bbs.vm;

import android.app.Application;
import android.view.View;
import android.widget.Toast;

import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;

import androidx.annotation.NonNull;
import kr.thkim.bbs.database.LocalCachingManager;
import kr.thkim.bbs.model.BserDBModel;
import kr.thkim.bbs.util.BusTag;

public class MapViewViewModel extends BaseViewModel {

    @Subscribe(tags = @Tag(BusTag.EVENT_TOUCH_MAP_ID))
    public void onClickHighlight(Integer id){
//        BserDBModel cacheDB = LocalCachingManager.getInstance().getCacheDB();
//        Toast.makeText(baseApplication, cacheDB.getLocations().get(id).getName(), Toast.LENGTH_SHORT).show();
    }
}
