package kr.thkim.bbs.vm;

import android.app.Application;
import android.content.Context;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import androidx.annotation.NonNull;
import io.reactivex.Completable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import kr.thkim.bbs.database.LocalCachingManager;
import kr.thkim.bbs.model.BserDBModel;
import kr.thkim.bbs.model.ItemModel;
import kr.thkim.bbs.util.LoggerUtil;
import kr.thkim.bbs.util.ParseUtil;

public class IntroViewModel extends BaseViewModel {

    public static final String END_LOADING = "END_LOADING";
    public static final String LOADING_ERROR = "LOADING_ERROR";

    public void setCacheDB(Context context) {
        compositeDisposable.add(Completable.fromAction(() -> {
                    String json = ParseUtil.loadJSONFromAsset(context, "db.json");
                    if (json != null) {
                        BserDBModel db = ParseUtil.fromJson(json, BserDBModel.class);
                        Set<String> equipSet = new HashSet<>();
                        db.setEquipkinds(
                                db.getItems().stream().filter(item -> {
                                    if (equipSet.contains(item.getEquip())) {
                                        return false;
                                    } else {
                                        equipSet.add(item.getEquip());
                                        return true;
                                    }
                                }).map(ItemModel::getEquip).collect(Collectors.toSet())
                        );
                        LocalCachingManager.getInstance().setCacheDB(db);
                    } else {
                        LoggerUtil.e("intro db json null exception");
                        event.setValue(LOADING_ERROR);
                    }
                }
        )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> event.setValue(END_LOADING), error -> event.setValue(LOADING_ERROR)
                ));
    }
}
