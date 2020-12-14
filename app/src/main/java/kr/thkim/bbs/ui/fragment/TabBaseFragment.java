package kr.thkim.bbs.ui.fragment;

import androidx.databinding.ViewDataBinding;

import kr.thkim.bbs.model.MainTabModel;
import kr.thkim.bbs.vm.BaseViewModel;

public abstract class TabBaseFragment <V extends ViewDataBinding, M extends BaseViewModel>
        extends BaseFragment<V,M> {

    protected MainTabModel mModel;

    public void initNewInstance(MainTabModel mModel) {
        this.mModel = mModel;
    }
}
