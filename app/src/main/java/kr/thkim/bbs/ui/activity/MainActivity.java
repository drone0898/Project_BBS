package kr.thkim.bbs.ui.activity;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import androidx.viewpager2.widget.ViewPager2;
import kr.thkim.bbs.R;
import kr.thkim.bbs.databinding.ActivityMainBinding;
import kr.thkim.bbs.databinding.LayoutTabItemBinding;
import kr.thkim.bbs.model.MainTabModel;
import kr.thkim.bbs.ui.adapter.MainTabAdapter;
import kr.thkim.bbs.ui.fragment.HomeTabFragment;
import kr.thkim.bbs.ui.fragment.InformationTabFragment;
import kr.thkim.bbs.ui.fragment.MyProfileTabFragment;
import kr.thkim.bbs.ui.fragment.RouteTabFragment;
import kr.thkim.bbs.vm.MainViewModel;

public class MainActivity extends BaseFragmentActivity<ActivityMainBinding, MainViewModel> {

    private MainTabAdapter mainTabAdapter;
    private LayoutTabItemBinding[] tabItemBindings;
    private int currentTabPosition;

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
        mainTabAdapter = setViewPagerAdapter(binding.mainViewpager);
        ArrayList<MainTabModel> tabs = getTabModels();
        binding.mainViewpager.setUserInputEnabled(false); // 스와이프 불가능
        binding.mainViewpager.setOffscreenPageLimit(tabs.size()); // 모든 탭을 로딩하도록 만듬
        mainTabAdapter.setItemList(tabs);
        tabItemBindings = new LayoutTabItemBinding[tabs.size()];
        for (int i = 0; i < tabs.size(); i++) {
            tabItemBindings[i] = LayoutTabItemBinding.inflate(getLayoutInflater());
            tabItemBindings[i].tabImage.setImageDrawable(ContextCompat.getDrawable(this,tabs.get(i).getIcon()));
            tabItemBindings[i].tabName.setText(tabs.get(i).getTabName());
            if (i == 0) {
                tabItemBindings[i].tabName.setTextColor(getColor(R.color.color_secondary_variant));
                tabItemBindings[i].tabImage.setColorFilter(getColor(R.color.color_secondary_variant));
            }else{
                tabItemBindings[i].tabName.setTextColor(getColor(R.color.white));
                tabItemBindings[i].tabImage.setColorFilter(getColor(R.color.white));
            }
        }

        TabLayoutMediator mediator = new TabLayoutMediator(binding.tabLayout,
                binding.mainViewpager, false, false, (tab, position) ->
                tab.setCustomView(tabItemBindings[position].getRoot()));
        mediator.attach();

        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                currentTabPosition = tab.getPosition();
                tabItemBindings[currentTabPosition].tabName.setTextColor(getColor(
                        R.color.color_secondary_variant));
                tabItemBindings[currentTabPosition].tabImage.setColorFilter(getColor(
                        R.color.color_secondary_variant));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                int i = tab.getPosition();
                tabItemBindings[i].tabName.setTextColor(getColor(R.color.white));
                tabItemBindings[i].tabImage.setColorFilter(getColor(R.color.white));
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // empty method
            }
        });
    }

    @Override
    protected void eventBinding() {

    }

    public MainTabAdapter setViewPagerAdapter(ViewPager2 viewPager) {
        MainTabAdapter adapter = null;
        if (viewPager.getAdapter() == null) {
            adapter = new MainTabAdapter(this);
            viewPager.setAdapter(adapter);
        }
        return adapter;
    }

    public ArrayList<MainTabModel> getTabModels() {
        ArrayList<MainTabModel> models = new ArrayList<>();
        models.add(new MainTabModel("홈", R.drawable.ic_round_home_24,
                HomeTabFragment.class, R.layout.fragment_home_tab));
        models.add(new MainTabModel("루트", R.drawable.ic_round_home_24,
                RouteTabFragment.class, R.layout.fragment_home_tab));
        models.add(new MainTabModel("정보", R.drawable.ic_round_home_24,
                InformationTabFragment.class, R.layout.fragment_information_tab));
        models.add(new MainTabModel("내 정보", R.drawable.ic_round_person_24,
                MyProfileTabFragment.class, R.layout.fragment_home_tab));
        return models;
    }
}
