package kr.thkim.bbs.vm;

import android.app.Application;

import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import kr.thkim.bbs.R;
import kr.thkim.bbs.model.adapter.AdapterBsItem;
import kr.thkim.bbs.util.BusTag;
import kr.thkim.bbs.util.LoggerUtil;

public class RouteViewModel extends BaseViewModel {

    MutableLiveData<List<AdapterBsItem>> _itemOneLineRecyclerItem = new MutableLiveData<>();
    public LiveData<List<AdapterBsItem>> itemOneLineRecyclerItem = _itemOneLineRecyclerItem;

    public RouteViewModel(@NonNull Application application) {
        super(application);
    }


    @Subscribe(tags = @Tag(BusTag.EVENT_ADD_ONE_LINE_ITEM))
    public void onClickAddItem(AdapterBsItem item){
        LoggerUtil.d("click");
    }

    public void setRecyclerItem(){
        AdapterBsItem adapterBsItem = new AdapterBsItem();
        adapterBsItem.setClickEvent(BusTag.EVENT_ADD_ONE_LINE_ITEM);
        adapterBsItem.setSrc(R.drawable.ic_add);
        List<AdapterBsItem> list = new ArrayList<>();
        list.add(adapterBsItem);
        AdapterBsItem adapterBsItem2 = new AdapterBsItem();
        adapterBsItem2.setClickEvent(BusTag.EVENT_ADD_ONE_LINE_ITEM);
        adapterBsItem2.setSrc(R.drawable.ic_add);
        list.add(adapterBsItem2);
        _itemOneLineRecyclerItem.setValue(list);
    }
}
