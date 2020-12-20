package kr.thkim.bbs.vm;

import android.app.Application;

import androidx.annotation.NonNull;
import io.reactivex.Completable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import kr.thkim.bbs.database.LocalCachingManager;
import kr.thkim.bbs.model.BserDBModel;
import kr.thkim.bbs.util.ParseUtil;

public class IntroViewModel extends BaseViewModel {

    public static final String END_LOADING = "END_LOADING";
    public static final String LOADING_ERROR = "LOADING_ERROR";

    public IntroViewModel(@NonNull Application application) {
        super(application);
    }

    public void setCacheDB() {
        compositeDisposable.add(Completable.fromAction(() -> {
                    String json = ParseUtil.loadJSONFromAsset(baseApplication, "db.json");
                    BserDBModel db = ParseUtil.fromJson(json, BserDBModel.class);
                    LocalCachingManager.getInstance().setCacheDB(db);
                }
        )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> event.setValue(END_LOADING), error -> event.setValue(LOADING_ERROR)
                ));
    }
}
