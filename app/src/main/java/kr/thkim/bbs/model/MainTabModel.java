package kr.thkim.bbs.model;

import androidx.annotation.LayoutRes;

import kr.thkim.bbs.ui.fragment.BaseFragment;

public class MainTabModel {
    private String tabName;
    private int icon;
    private Class<? extends BaseFragment<?,?>> fragment; // 탭 아이템에 프래그먼트 매핑
    @LayoutRes
    private int layout;

    public String getTabName() {
        return tabName;
    }

    public void setTabName(String tabName) {
        this.tabName = tabName;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public Class<? extends BaseFragment<?, ?>> getFragment() {
        return fragment;
    }

    public void setFragment(Class<? extends BaseFragment<?, ?>> fragment) {
        this.fragment = fragment;
    }

    public int getLayout() {
        return layout;
    }

    public void setLayout(int layout) {
        this.layout = layout;
    }

    public MainTabModel(String tabName, int icon, Class<? extends BaseFragment<?, ?>> fragment, int layout) {
        this.tabName = tabName;
        this.icon = icon;
        this.fragment = fragment;
        this.layout = layout;
    }
}
