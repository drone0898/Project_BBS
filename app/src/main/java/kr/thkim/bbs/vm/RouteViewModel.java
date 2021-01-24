package kr.thkim.bbs.vm;

import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import kr.thkim.bbs.R;
import kr.thkim.bbs.database.LocalCachingManager;
import kr.thkim.bbs.model.adapter.AdapterBsItem;
import kr.thkim.bbs.util.LoggerUtil;

public class RouteViewModel extends BaseViewModel {

    MutableLiveData<List<AdapterBsItem>> _itemOneLineRecyclerItem = new MutableLiveData<>(); // 아이템 추가버튼들
    MutableLiveData<List<AdapterBsItem>> _selectItemRecyclerItem = new MutableLiveData<>();
    MutableLiveData<Set<String>> _selectItemEquipFilterList = new MutableLiveData<>();
    MutableLiveData<List<Set<String>>> _selectedEquipList = new MutableLiveData<>();
    MutableLiveData<Integer> _currentSelItemIndex = new MutableLiveData<>();

    public LiveData<List<AdapterBsItem>> itemOneLineRecyclerItem = _itemOneLineRecyclerItem;
    public LiveData<List<AdapterBsItem>> selectItemRecyclerItem = _selectItemRecyclerItem;
    public LiveData<Set<String>> selectItemEquipFilterList = _selectItemEquipFilterList;
    public LiveData<List<Set<String>>> selectedEquipList = _selectedEquipList;
    public LiveData<Integer> currentSelItemIndex = _currentSelItemIndex;

    public void addSelectedEquipList(String equip, int listIdx) {
        if (_selectedEquipList.getValue() != null && _selectedEquipList.getValue().get(listIdx) != null) {
            _selectedEquipList.getValue().get(listIdx).add(equip);
        }
    }

    public void removeSelectedEquipList(String equip, int listIdx) {
        if (_selectedEquipList.getValue() != null && _selectedEquipList.getValue().get(listIdx) != null) {
            _selectedEquipList.getValue().get(listIdx).remove(equip);
        }
    }

    public void onClickAddItem(View view, AdapterBsItem item, int position) {
        LoggerUtil.d("click");
        _selectItemEquipFilterList.setValue(LocalCachingManager.getInstance().getCacheDB().getEquipkinds()); // 칩 업데이트
        _currentSelItemIndex.setValue(position);
    }

    public void setOnCheckedChange(View button, boolean isChecked, String equip) {
        int index = currentSelItemIndex.getValue();
        if (isChecked) {
            addSelectedEquipList(equip, index);
        } else {
            removeSelectedEquipList(equip, index);
        }
    }

    public void setRecyclerItem() {
        List<AdapterBsItem> list = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            AdapterBsItem adapterBsItem = new AdapterBsItem();
            adapterBsItem.setSrc(R.drawable.ic_add);
            list.add(adapterBsItem);
        }
        _itemOneLineRecyclerItem.setValue(list);
    }
}
