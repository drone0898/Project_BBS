package kr.thkim.bbs.vm;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import kr.thkim.bbs.BaseApplication;
import kr.thkim.bbs.R;
import kr.thkim.bbs.database.LocalCachingManager;
import kr.thkim.bbs.model.BserDBModel;
import kr.thkim.bbs.model.CharacterItem;
import kr.thkim.bbs.model.adapter.CharacterPortrait;
import kr.thkim.bbs.model.adapter.ImageButton;
import kr.thkim.bbs.ui.adapter.EquipKindRecycler;
import kr.thkim.bbs.ui.adapter.CharacterPortraitRecycler;
import kr.thkim.bbs.util.BusTag;
import kr.thkim.bbs.util.Global;

public class RouteTabViewModel extends BaseViewModel {

    MutableLiveData<List<CharacterPortrait>> _characterPortraitList = new MutableLiveData<>();
    MutableLiveData<List<ImageButton>> _equipKindList = new MutableLiveData<>();
    public LiveData<List<CharacterPortrait>> characterPortraitList = _characterPortraitList;
    public LiveData<List<ImageButton>> equipKindList = _equipKindList;

    private CharacterItem currentSelected = null;


    public RouteTabViewModel(@NonNull Application application) {
        super(application);
    }

    @Subscribe(tags = @Tag(BusTag.EVENT_CHARACTER_PORTRAIT))
    public void onClickCharacter(Integer id){
        BserDBModel cacheDB = LocalCachingManager.getInstance().getCacheDB();
        List<ImageButton> equipKindList = new ArrayList<>();

        for(String ekind:cacheDB.getCharacters().get(id).getWeapons()){
            ImageButton img = new ImageButton();
            img.setName(ekind);
            img.setSrc(BaseApplication.getDrawableFileResId("weaponkind",3,
                    cacheDB.getWeaponkinds().indexOf(ekind)));
            equipKindList.add(img);
        }
        currentSelected = cacheDB.getCharacters().get(id);
        _equipKindList.postValue(equipKindList);
    }

    @Subscribe(tags = @Tag(BusTag.EVENT_EQUIP_KIND))
    public void onClickEquipKind(String kind){
        Bundle bundle = new Bundle();
        bundle.putParcelable(Global.START_ROUTE_CHARACTER,currentSelected);
        bundle.putString(Global.START_ROUTE_EQUIP_KIND,kind);
        bundleEvent.postValue(bundle);
        // setValue로 하니까 RxBus 실행이 안되는 이상한 버그가 있었다.... 왜지?
    }



    public void setRecyclerItem(){
        //TODO : 비동기로 바꾸기 렉걸린다.
        BserDBModel db = LocalCachingManager.getInstance().getCacheDB();
        List<CharacterPortrait> characterPortraitList = new ArrayList<>();
        for(CharacterItem character:db.getCharacters()){
            CharacterPortrait portrait = new CharacterPortrait();
            portrait.setName(character.getName());
            portrait.setItem(character);
            portrait.setClickEvent(BusTag.EVENT_CHARACTER_PORTRAIT);
            portrait.setSrc(BaseApplication.getDrawableFileResId("char",3,character.getId()));
            portrait.setSrcUrl("");
            characterPortraitList.add(portrait);
        }
        _characterPortraitList.postValue(characterPortraitList);
    }
}
