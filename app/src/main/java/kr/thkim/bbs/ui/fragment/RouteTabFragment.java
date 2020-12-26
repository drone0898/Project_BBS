package kr.thkim.bbs.ui.fragment;

import androidx.lifecycle.ViewModelProvider;

import kr.thkim.bbs.R;
import kr.thkim.bbs.databinding.FragmentRouteTabBinding;
import kr.thkim.bbs.ui.activity.RouteActivity;
import kr.thkim.bbs.vm.RouteTabViewModel;

public class RouteTabFragment extends BaseFragment<FragmentRouteTabBinding, RouteTabViewModel> {
    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_route_tab;
    }

    @Override
    protected RouteTabViewModel getViewModel() {
        return new ViewModelProvider(this).get(RouteTabViewModel.class);
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
