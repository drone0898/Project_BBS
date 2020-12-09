package kr.thkim.bbs.ui.activity;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import io.reactivex.disposables.CompositeDisposable;
import kr.thkim.bbs.BR;
import kr.thkim.bbs.BaseApplication;
import kr.thkim.bbs.vm.BaseViewModel;

/**
 * Activity에서 사용하는 공통기능들을 상속해준다.
 * MVVM 아키텍쳐 + 데이터바인딩
 *
 * MVVM에서는 Activity의 비즈니스 로직 코드를 최소화한다.
 * View 의 역할은 Layout(xml)이 담당한다 (Databinding 활용)
 * View에서 처리하기 힘든 일이나, 안드로이드 컴포넌트 의존성이 발생하는 코드를 여기서 작성한다.
 *
 * Activity에서는 ViewModel의 Livedata, Observable등을 구독하고 이벤트를 처리한다.
 * 단, 직접 접근해서 값을 변경하면 안된다.
 *
 * @author taeheunkim on 2020.10.21
 */
public abstract class BaseActivity<V extends ViewDataBinding, M extends BaseViewModel>
        extends AppCompatActivity {

    protected final String TAG = getClass().getSimpleName();

    // 이 값이 true면 onCreate에서 태블릿은 가로, 모바일에서 세로로 고정한다. 커스텀이 필요하면 false로 설정
    protected boolean fixScreenOrientation = true;
    protected M viewModel;
    protected V binding;
    protected BaseApplication baseApplication;
    public CompositeDisposable compositeDisposable = new CompositeDisposable();

    /**
     * setContentView로 호출할 Layout의 리소스 Id.
     * ex) R.layout.activity_sbs_main
     */
    protected abstract int getLayoutResourceId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseApplication = (BaseApplication) getApplication();
        this.viewModel = getViewModel();

        initialize();

        binding = DataBindingUtil.setContentView(this, getLayoutResourceId());
//        binding.setVariable(BR.activity, this);
//        binding.setVariable(BR.viewModel, viewModel);
        binding.setLifecycleOwner(this); // 바인딩과 라이프사이클을 연결해 LiveData를 사용할 수 있다.
        getLifecycle().addObserver(viewModel); // 뷰모델이 LifecycleObserver를 구현하므로 옵저버를 추가
        initDataBinding();
        eventBinding();
    }

    /**
     * viewModel 로 쓰일 변수.
     * - https://themach.tistory.com/42
     */
    protected abstract M getViewModel();

    /**
     * 초기화, DataBinding 전에 실행된다.
     */
    protected abstract void initialize();       // 초기화

    /**
     * 데이터 바인딩 및 rxjava 설정.
     * ex) rxjava observe, databinding observe..
     */
    protected abstract void initDataBinding();  // 데이터 바인딩

    /**
     * 바인딩 이후에 할 일을 여기에 구현.
     * 그 외에 설정할 것이 있으면 이곳에서 설정.
     * 클릭 리스너도 이곳에서 설정.
     */
    protected abstract void eventBinding();     // 이벤트 바인딩


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }


    /**
     * @param maintain true면 백스택 유지, false면 초기화
     */
    public void startTargetActivity(Class<?> target, Bundle extraData, boolean maintain) {
        Uri uri = getIntent().getData();

        Intent cIntent = new Intent(this, target);
        if (extraData != null) {
            cIntent.putExtras(extraData);
        }
        if (uri != null) {
            cIntent.setData(uri);
        }
        if (!maintain) {
            cIntent.addFlags(
                    Intent.FLAG_ACTIVITY_CLEAR_TASK |
                            Intent.FLAG_ACTIVITY_SINGLE_TOP |
                            Intent.FLAG_ACTIVITY_CLEAR_TOP);
        }
        startActivity(cIntent);
        if (!maintain) {
            finish();
        }
    }
}