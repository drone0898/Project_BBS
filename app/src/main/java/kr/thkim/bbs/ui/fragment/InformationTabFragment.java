package kr.thkim.bbs.ui.fragment;

import androidx.lifecycle.ViewModelProvider;
import kr.thkim.bbs.R;
import kr.thkim.bbs.databinding.FragmentInformationTabBinding;
import kr.thkim.bbs.vm.InformationTabViewModel;

public class InformationTabFragment extends BaseFragment<FragmentInformationTabBinding,
        InformationTabViewModel> {

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_information_tab;
    }

    @Override
    protected InformationTabViewModel getViewModel() {
        return new ViewModelProvider(this).get(InformationTabViewModel.class);
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
