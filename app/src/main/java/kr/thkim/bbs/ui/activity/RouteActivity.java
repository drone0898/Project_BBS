package kr.thkim.bbs.ui.activity;

import androidx.lifecycle.ViewModelProvider;
import kr.thkim.bbs.R;
import kr.thkim.bbs.databinding.ActivityRouteBinding;
import kr.thkim.bbs.ui.adapter.ItemOneLineRecycler;
import kr.thkim.bbs.util.Global;
import kr.thkim.bbs.vm.RouteViewModel;

public class RouteActivity extends BaseActivity<ActivityRouteBinding, RouteViewModel> {

    ItemOneLineRecycler itemOneLineRecycler;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_route;
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
        binding.setCharacterResId(getIntent().getExtras().getInt(Global.START_ROUTE_CHARACTER_PORTRAIT_RES_ID));
        binding.setEquipKind(getIntent().getExtras().getString(Global.START_ROUTE_EQUIP_KIND));
        itemOneLineRecycler = ItemOneLineRecycler.setAdapter(binding.itemOneLineRecycler);
        viewModel.itemOneLineRecyclerItem.observe(this,list->itemOneLineRecycler.setItemList(list));
    }

    @Override
    protected void eventBinding() {
        viewModel.setRecyclerItem();
    }
}
