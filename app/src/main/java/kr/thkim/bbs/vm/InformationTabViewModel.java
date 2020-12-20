package kr.thkim.bbs.vm;

import android.app.Application;

import androidx.annotation.NonNull;

public class InformationTabViewModel extends BaseViewModel {
    public static final String START_MAP_VIEW = "START_MAP_VIEW";
    public InformationTabViewModel(@NonNull Application application) {
        super(application);
    }

    public void startMapView(){
        event.setValue(START_MAP_VIEW);
    }
}
