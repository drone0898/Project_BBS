package kr.thkim.bbs.ui.dialog;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import io.reactivex.disposables.CompositeDisposable;
import kr.thkim.bbs.BR;
import kr.thkim.bbs.BaseApplication;
import kr.thkim.bbs.R;
import kr.thkim.bbs.util.LoggerUtil;
import kr.thkim.bbs.vm.BaseViewModel;

public abstract class BaseBottomSheetFragment <V extends ViewDataBinding, M extends BaseViewModel>
        extends BottomSheetDialogFragment {

    @SuppressWarnings("unused")
    protected final String TAG = getClass().getSimpleName();

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
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getActivity()!=null){
            baseApplication = (BaseApplication) getActivity().getApplication();
        }
        this.viewModel = getViewModel();

        initialize();

        this.binding = DataBindingUtil.inflate(inflater, getLayoutResourceId(), container,false);
        binding.setVariable(BR.fragment, this);
        binding.setVariable(BR.viewModel, viewModel);
        binding.setLifecycleOwner(this); // 바인딩과 라이프사이클을 연결해 LiveData를 사용할 수 있다.
        getLifecycle().addObserver(viewModel); // 뷰모델이 LifecycleObserver를 구현하므로 옵저버를 추가
        initDataBinding();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
    public void onDestroy() {
        super.onDestroy();
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }

    // 태블릿일경우 시트 width를 절반크기로, 모바일일 경우 match_parent로
    @Override
    public void onResume() {
        super.onResume();
    }

    // corner 적용
    @Override
    public int getTheme() {
        return R.style.corner_bottom_sheet_dialog;
    }

    // corner 적용 (EXPANDED에도)
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);

        BottomSheetBehavior<?> behavior = ((BottomSheetDialog) dialog).getBehavior();
        behavior.setHideable(false);
        behavior.setDraggable(false);
        behavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {

            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                    //In the EXPANDED STATE apply a new MaterialShapeDrawable with rounded cornes
                    // 피커 그림자 없앰
                    MaterialShapeDrawable newMaterialShapeDrawable = createMaterialShapeDrawable(bottomSheet);
                    ViewCompat.setBackground(bottomSheet, newMaterialShapeDrawable);
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
            }
        });
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(()->
                behavior.setState(BottomSheetBehavior.STATE_EXPANDED),100);
        return dialog;
    }


    // 그림자 & 라운드 코너가 EXPANDED 상태에서도 필요할때 사용
    @SuppressWarnings("unused")
    @NonNull
    private MaterialShapeDrawable createMaterialShapeDrawable(@NonNull View bottomSheet) {
        ShapeAppearanceModel shapeAppearanceModel =
                //Create a ShapeAppearanceModel with the same shapeAppearanceOverlay used in the style
                ShapeAppearanceModel.builder(getContext(), 0, R.style.corner_bottom_sheet_dialog_val)
                        .build();

        //Create a new MaterialShapeDrawable (you can't use the original MaterialShapeDrawable in the BottomSheet)
        MaterialShapeDrawable currentMaterialShapeDrawable = (MaterialShapeDrawable) bottomSheet.getBackground();
        MaterialShapeDrawable newMaterialShapeDrawable = new MaterialShapeDrawable((shapeAppearanceModel));
        //Copy the attributes in the new MaterialShapeDrawable
        if (getContext() != null) {
            newMaterialShapeDrawable.initializeElevationOverlay(getContext());
        }
        newMaterialShapeDrawable.setFillColor(currentMaterialShapeDrawable.getFillColor());
        newMaterialShapeDrawable.setTintList(currentMaterialShapeDrawable.getTintList());
        newMaterialShapeDrawable.setElevation(currentMaterialShapeDrawable.getElevation());
        newMaterialShapeDrawable.setStrokeWidth(currentMaterialShapeDrawable.getStrokeWidth());
        newMaterialShapeDrawable.setStrokeColor(currentMaterialShapeDrawable.getStrokeColor());

        return newMaterialShapeDrawable;
    }


    /**
     * @param maintain true면 백스택 유지, false면 초기화
     */
    @SuppressWarnings("unused")
    public void startTargetActivity(Class<?> target, @Nullable Bundle extraData, boolean maintain) {
        if(getActivity()==null){
            LoggerUtil.d("getActivity is null");
            return;
        }
        Uri uri = getActivity().getIntent().getData();

        Intent cIntent = new Intent(getActivity(), target);
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
            getActivity().finish();
        }
    }
}
