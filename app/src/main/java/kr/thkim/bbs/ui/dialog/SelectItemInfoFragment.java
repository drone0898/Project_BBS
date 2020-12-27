package kr.thkim.bbs.ui.dialog;

import androidx.lifecycle.ViewModelProvider;
import kr.thkim.bbs.R;
import kr.thkim.bbs.databinding.FragmentSelectInfoItemBinding;
import kr.thkim.bbs.vm.SelectItemInfoViewModel;

/**
 * 선택한 아이템 정보 표시
 */
public class SelectItemInfoFragment extends BaseBottomSheetFragment<FragmentSelectInfoItemBinding,
        SelectItemInfoViewModel> {
    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_select_info_item;
    }

    @Override
    protected SelectItemInfoViewModel getViewModel() {
        return new ViewModelProvider(this).get(SelectItemInfoViewModel.class);
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
