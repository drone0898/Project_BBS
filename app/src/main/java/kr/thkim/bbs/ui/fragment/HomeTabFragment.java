package kr.thkim.bbs.ui.fragment;

import androidx.lifecycle.ViewModelProvider;

import kr.thkim.bbs.R;
import kr.thkim.bbs.databinding.FragmentHomeTabBinding;
import kr.thkim.bbs.vm.HomeTabViewModel;

public class HomeTabFragment extends TabBaseFragment<FragmentHomeTabBinding, HomeTabViewModel> {
    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_home_tab;
    }

    @Override
    protected HomeTabViewModel getViewModel() {
        return new ViewModelProvider(this).get(HomeTabViewModel.class);
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
