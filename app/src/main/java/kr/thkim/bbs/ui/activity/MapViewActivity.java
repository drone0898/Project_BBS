package kr.thkim.bbs.ui.activity;

import androidx.lifecycle.ViewModelProvider;
import kr.thkim.bbs.R;
import kr.thkim.bbs.databinding.ActivityMapViewBinding;
import kr.thkim.bbs.vm.MapViewViewModel;

public class MapViewActivity extends BaseActivity<ActivityMapViewBinding, MapViewViewModel> {
    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_map_view;
    }

    @Override
    protected MapViewViewModel getViewModel() {
        return new ViewModelProvider(this).get(MapViewViewModel.class);
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void initDataBinding() {

    }

    @Override
    protected void eventBinding() {

    }
}
