package kr.thkim.bbs.vm;

import android.app.Application;
import android.content.Intent;

import com.hwangjr.rxbus.RxBus;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import io.reactivex.disposables.CompositeDisposable;
import kr.thkim.bbs.BaseApplication;

/**
 * ViewModel의 공통기능들을 상속해준다.
 *
 * MVVM 아키텍처 + Livedata (Observer Pattern)
 *
 * ViewModel은 Activity의 생명주기중, onCreate ~ onDestroy 동안 존재한다.
 * ViewModel은 View와 Activity를 알고있어서는 안된다.
 * 따라서 View,Activity에 이벤트를 전달하기 위해 Livedata를 사용한다.
 *
 * Livedata.postValue를 이용해 이벤트를 발생시키고
 * 이를 구독하는 View, Actvity는 livedata 값이 변경될때 화면 데이터를 변경한다.
 *
 *
 * @author taeheunkim on 2020.10.22
 */
public abstract class BaseViewModel extends AndroidViewModel implements LifecycleObserver {

    protected final String TAG = getClass().getSimpleName();

    protected boolean keepRxBusWhenPaused = false; // false => unregister onPaused, true => unregister onDestroy

    protected BaseApplication baseApplication;
    public CompositeDisposable compositeDisposable = new CompositeDisposable();
    MutableLiveData<String> event = new MutableLiveData<>();
    MutableLiveData<Intent> intentEvent = new MutableLiveData<>();

    public MutableLiveData<String> getEvent() {
        return event;
    }

    public MutableLiveData<Intent> getIntentEvent() {
        return intentEvent;
    }

    public BaseViewModel(@NonNull Application application) {
        super(application);
        baseApplication = (BaseApplication) application;
    }

    // 작동순서 : BASE's onCreate() -> BASE's onCreate() End. -> this ON_CREATE
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    protected void onCreate() {
        if (keepRxBusWhenPaused) {
            RxBus.get().register(this);
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    protected void onResume(){
        if (!keepRxBusWhenPaused) {
            RxBus.get().register(this);
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    protected void onPause(){
        if (!keepRxBusWhenPaused) {
            RxBus.get().unregister(this);
        }
    }

    // 작동순서 : BASE's onDestroy() -> this ON_DESTROY -> BASE's onDestroy() End.
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    protected void onDestroy() {
        if (keepRxBusWhenPaused) {
            RxBus.get().unregister(this);
        }
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }
}
