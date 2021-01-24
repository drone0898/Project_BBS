package kr.thkim.bbs.vm;

public class InformationTabViewModel extends BaseViewModel {
    public static final String START_MAP_VIEW = "START_MAP_VIEW";

    public void startMapView(){
        event.setValue(START_MAP_VIEW);
    }
}
