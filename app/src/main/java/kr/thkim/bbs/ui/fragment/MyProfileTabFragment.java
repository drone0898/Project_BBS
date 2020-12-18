package kr.thkim.bbs.ui.fragment;

import androidx.lifecycle.ViewModelProvider;

import kr.thkim.bbs.R;
import kr.thkim.bbs.databinding.FragmentMyProfileTabBinding;
import kr.thkim.bbs.vm.MyProfileViewModel;

public class MyProfileTabFragment extends BaseFragment<FragmentMyProfileTabBinding,
        MyProfileViewModel> {
    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_my_profile_tab;
    }

    @Override
    protected MyProfileViewModel getViewModel() {
        return new ViewModelProvider(this).get(MyProfileViewModel.class);
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
