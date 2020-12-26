package kr.thkim.bbs.ui.activity;

import androidx.lifecycle.ViewModelProvider;
import kr.thkim.bbs.R;
import kr.thkim.bbs.databinding.ActivityRouteBinding;
import kr.thkim.bbs.vm.RouteViewModel;

public class RouteActivity extends BaseActivity<ActivityRouteBinding, RouteViewModel> {
    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_route;
    }

    @Override
    protected RouteViewModel getViewModel() {
        return new ViewModelProvider(this).get(RouteViewModel.class);
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
