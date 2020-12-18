package kr.thkim.bbs.ui.fragment;

import androidx.lifecycle.ViewModelProvider;

import kr.thkim.bbs.R;
import kr.thkim.bbs.databinding.FragmentRouteTabBinding;
import kr.thkim.bbs.vm.RouteViewModel;

public class RouteTabFragment extends BaseFragment<FragmentRouteTabBinding, RouteViewModel> {
    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_route_tab;
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
