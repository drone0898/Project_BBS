package kr.thkim.bbs.ui.fragment;

import androidx.lifecycle.ViewModelProvider;

import kr.thkim.bbs.R;
import kr.thkim.bbs.databinding.FragmentRouteTabBinding;
import kr.thkim.bbs.ui.activity.RouteActivity;
import kr.thkim.bbs.ui.adapter.EquipKindRecycler;
import kr.thkim.bbs.ui.adapter.CharacterPortraitRecycler;
import kr.thkim.bbs.vm.RouteTabViewModel;

public class RouteTabFragment extends BaseFragment<FragmentRouteTabBinding, RouteTabViewModel> {

    public EquipKindRecycler equipKindRecycler;
    public CharacterPortraitRecycler characterPortraitRecycler;

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
        equipKindRecycler = EquipKindRecycler.setAdapter(binding.equipKindRecycler);
        characterPortraitRecycler = CharacterPortraitRecycler.setAdapter(binding.characterRecycler,viewModel);
    }

    @Override
    protected void eventBinding() {
        viewModel.characterPortraitList.observe(this,list->
                characterPortraitRecycler.setItemList(list));
        viewModel.equipKindList.observe(this,list->
                equipKindRecycler.setItemList(list));
        viewModel.bundleEvent.observe(this,bundle->
                startTargetActivity(RouteActivity.class,bundle,true));
        viewModel.setRecyclerItem();
    }
}
