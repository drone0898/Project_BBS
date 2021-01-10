package kr.thkim.bbs.vm;

import android.annotation.SuppressLint;
import android.app.Application;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.NonNull;

import kr.thkim.bbs.util.ParseUtil;

public class DevViewModel extends BaseViewModel {

    public DevViewModel(@NonNull Application application) {
        super(application);
    }

    @SuppressLint("DefaultLocale")
    public void onClickDebugBtn(int id) {
        switch (id) {
            case 1:
                String json = ParseUtil.loadJSONFromAsset(baseApplication, "db.json");
                if (json != null) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        Toast.makeText(baseApplication, "NO",
                                Toast.LENGTH_SHORT).show();
//                        CollectionReference animalRef = FirebaseFirestore.getInstance().collection(Global.FS_DB_ANIMAL_INFO);
//                        CollectionReference effTypeRef = FirebaseFirestore.getInstance().collection(Global.FS_DB_EFFECT_TYPES);
//                        CollectionReference itemRef = FirebaseFirestore.getInstance().collection(Global.FS_DB_ITEM_INFO);
//                        CollectionReference locRef = FirebaseFirestore.getInstance().collection(Global.FS_DB_LOCATION_INFO);
//
//                        BserDBModel db = ParseUtil.fromJson(json, BserDBModel.class);
//
//                        List<AnimalsItem> animalsItemList = db.getAnimals();
//                        List<EffectTypesItem> effectTypesItemList = db.getEffectTypes();
//                        List<ItemsItem> itemsItemList = db.getItems();
//                        List<LocationsItem> locationsItemList = db.getLocations();
//
//                        animalsItemList.forEach(item -> animalRef.document("animal" +
//                                String.format("%05d", item.getAnimalId())).set(item));
//                        effectTypesItemList.forEach(item -> effTypeRef.document(
//                                "effect_type" + String.format("%05d", item.getId())).set(item));
//                        itemsItemList.forEach(item -> itemRef.document("item" +
//                                String.format("%05d", item.getId())).set(item));
//                        locationsItemList.forEach(item -> locRef.document("location" +
//                                String.format("%05d", item.getId())).set(item));
                    } else {
                        Toast.makeText(baseApplication, "안드로이드 N 이상 기기만 가능합니다.",
                                Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case 3:
                event.setValue("START");
                break;
            default:
                break;
        }
    }
}
