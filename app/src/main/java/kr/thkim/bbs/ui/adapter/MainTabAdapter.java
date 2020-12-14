package kr.thkim.bbs.ui.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.lang.reflect.Constructor;
import java.util.ArrayList;

import kr.thkim.bbs.model.MainTabModel;
import kr.thkim.bbs.ui.fragment.BaseFragment;
import kr.thkim.bbs.ui.fragment.TabBaseFragment;
import kr.thkim.bbs.util.LoggerUtil;

/**
 * 메인화면 하단 탭어댑터
 * @author taeheunkim drone0898@gmail.com
 */
public class MainTabAdapter extends FragmentStateAdapter {

    ArrayList<MainTabModel> itemList;
    Fragment pageNotFound;

    public MainTabAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public void setItemList(ArrayList<MainTabModel> list) {
        itemList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return (itemList == null) ? 0 : itemList.size();
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position < itemList.size()) {
            try {
                Constructor<?> constructor = itemList.get(position).getFragment().getConstructor();
                TabBaseFragment<?,?> f = (TabBaseFragment<?,?>) constructor.newInstance();
                f.initNewInstance(itemList.get(position));
                return f;
            } catch (Exception e) {
                LoggerUtil.e(e + "\nFRAGMENT NOT FOUND EXCEPTION!! Make sure your fragment have " +
                        "'PUBLIC' Default Constructor");
                return pageNotFound;
            }
        }
        return pageNotFound;
    }
}
