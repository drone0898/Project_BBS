package kr.thkim.bbs.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import io.reactivex.disposables.CompositeDisposable;
import kr.thkim.bbs.BR;
import kr.thkim.bbs.BaseApplication;
import kr.thkim.bbs.vm.BaseViewModel;

public abstract class BaseFragment <V extends ViewDataBinding, M extends BaseViewModel>
        extends Fragment {

    protected final String TAG = getClass().getSimpleName();

    // 이 값이 true면 onCreate에서 태블릿은 가로, 모바일에서 세로로 고정한다. 커스텀이 필요하면 false로 설정
    protected M viewModel;
    protected V binding;
    protected BaseApplication baseApplication;
    public CompositeDisposable compositeDisposable = new CompositeDisposable();

    /**
     * setContentView로 호출할 Layout의 리소스 Id.
     * ex) R.layout.activity_sbs_main
     */
    protected abstract int getLayoutResourceId();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(getActivity()!=null){
            baseApplication = (BaseApplication) getActivity().getApplication();
        }
        this.viewModel = getViewModel();

        initialize();

        this.binding = DataBindingUtil.inflate(inflater, getLayoutResourceId(), container,false);
//        binding.setVariable(BR.fragment, this);
//        binding.setVariable(BR.viewModel, viewModel);
        binding.setLifecycleOwner(this); // 바인딩과 라이프사이클을 연결해 LiveData를 사용할 수 있다.
        getLifecycle().addObserver(viewModel); // 뷰모델이 LifecycleObserver를 구현하므로 옵저버를 추가
        initDataBinding();
        eventBinding();
        return binding.getRoot();
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
    public void onDestroy() {
        super.onDestroy();
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }
}