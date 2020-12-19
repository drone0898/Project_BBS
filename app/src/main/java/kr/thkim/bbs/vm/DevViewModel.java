package kr.thkim.bbs.vm;

import android.app.Application;
import android.os.Build;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

import androidx.annotation.NonNull;

import kr.thkim.bbs.model.BSDBDTO;
import kr.thkim.bbs.model.ItemDTO;
import kr.thkim.bbs.util.Global;
import kr.thkim.bbs.util.ParseUtil;

public class DevViewModel extends BaseViewModel {

    public DevViewModel(@NonNull Application application) {
        super(application);
    }

    public void onClickDebugBtn(int id){
        switch (id){
            case 1:
                CollectionReference reference = FirebaseFirestore.getInstance().collection(Global.ITEM_INFO);
                BSDBDTO dbDTO = ParseUtil.fromJson(ParseUtil.loadJSONFromAsset(baseApplication,"db.json"), BSDBDTO.class);
                List<ItemDTO> items = dbDTO.getItems();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    items.forEach(item->{
                        reference.document("item"+String.format("%05d",item.getId()))
                                .set(item);
                    });
                }
                break;
            default:
                break;
        }
    }
}
