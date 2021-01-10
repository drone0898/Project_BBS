package kr.thkim.bbs.vm;

import android.app.Application;
import android.os.Bundle;

import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import kr.thkim.bbs.BaseApplication;
import kr.thkim.bbs.database.LocalCachingManager;
import kr.thkim.bbs.model.BserDBModel;
import kr.thkim.bbs.model.CharacterModel;
import kr.thkim.bbs.model.adapter.CharacterPortrait;
import kr.thkim.bbs.model.adapter.ImageButton;
import kr.thkim.bbs.util.BusTag;
import kr.thkim.bbs.util.Global;

public class RouteTabViewModel extends BaseViewModel {

    MutableLiveData<List<CharacterPortrait>> _characterPortraitList = new MutableLiveData<>();
    MutableLiveData<List<ImageButton>> _equipKindList = new MutableLiveData<>();
    public LiveData<List<CharacterPortrait>> characterPortraitList = _characterPortraitList;
    public LiveData<List<ImageButton>> equipKindList = _equipKindList;

    private int currentCharacterResId;


    public RouteTabViewModel(@NonNull Application application) {
        super(application);
    }

    @Subscribe(tags = @Tag(BusTag.EVENT_CHARACTER_PORTRAIT))
    public void onClickCharacter(CharacterPortrait item){
        BserDBModel cacheDB = LocalCachingManager.getInstance().getCacheDB();
        List<ImageButton> equipKindList = new ArrayList<>();

        for(String ekind:cacheDB.getCharacters().get(item.getItem().getId()).getWeapons()){
            ImageButton img = new ImageButton();
            img.setName(ekind);
            img.setSrc(BaseApplication.getDrawableFileResId("weaponkind",3,
                    cacheDB.getWeaponkinds().indexOf(ekind)));
            equipKindList.add(img);
        }
        currentCharacterResId = item.getSrc();
        _equipKindList.postValue(equipKindList);
    }

    @Subscribe(tags = @Tag(BusTag.EVENT_EQUIP_KIND))
    public void onClickEquipKind(String kind){
        Bundle bundle = new Bundle();
        bundle.putInt(Global.START_ROUTE_CHARACTER_PORTRAIT_RES_ID,currentCharacterResId);
        bundle.putString(Global.START_ROUTE_EQUIP_KIND,kind);
        bundleEvent.postValue(bundle);
        // setValue로 하니까 RxBus 실행이 안되는 이상한 버그가 있었다.... 왜지?
    }



    public void setRecyclerItem(){
        //TODO : 비동기로 바꾸기 렉걸린다.
        BserDBModel db = LocalCachingManager.getInstance().getCacheDB();
        List<CharacterPortrait> characterPortraitList = new ArrayList<>();
        for(CharacterModel character:db.getCharacters()){
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
