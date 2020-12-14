package kr.thkim.bbs.ui.activity;

import androidx.lifecycle.ViewModelProvider;

import kr.thkim.bbs.R;
import kr.thkim.bbs.databinding.ActivityMainBinding;
import kr.thkim.bbs.vm.MainViewModel;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {
    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_main;
    }

    @Override
    protected MainViewModel getViewModel() {
        return new ViewModelProvider(this).get(MainViewModel.class);
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
