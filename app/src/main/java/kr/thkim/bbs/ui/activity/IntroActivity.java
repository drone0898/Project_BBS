package kr.thkim.bbs.ui.activity;

import androidx.lifecycle.ViewModelProvider;
import kr.thkim.bbs.BuildConfig;
import kr.thkim.bbs.R;
import kr.thkim.bbs.databinding.ActivityIntroBinding;
import kr.thkim.bbs.vm.IntroViewModel;

public class IntroActivity extends BaseActivity<ActivityIntroBinding, IntroViewModel> {

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_intro;
    }

    @Override
    protected IntroViewModel getViewModel() {
        return new ViewModelProvider(this).get(IntroViewModel.class);
    }

    @Override
    protected void initialize() {

    }

    @Override
    protected void initDataBinding() {
        if(BuildConfig.DEBUG){
            startTargetActivity(DevActivity.class,null,false);
        }else{
            startTargetActivity(MapViewActivity.class,null,false);
        }
    }

    @Override
    protected void eventBinding() {
        viewModel.event.observe(this,event->{
            switch(event){
                default:
                    startTargetActivity(MainActivity.class,null,false);
                    break;
            }
        });
        viewModel.setCacheDB();
    }
}