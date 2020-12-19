package kr.thkim.bbs.ui.activity;

import androidx.lifecycle.ViewModelProvider;
import kr.thkim.bbs.R;
import kr.thkim.bbs.databinding.ActivityDevBinding;
import kr.thkim.bbs.vm.DevViewModel;

public class DevActivity extends BaseActivity<ActivityDevBinding, DevViewModel> {
    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_dev;
    }

    @Override
    protected DevViewModel getViewModel() {
        return new ViewModelProvider(this).get(DevViewModel.class);
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
