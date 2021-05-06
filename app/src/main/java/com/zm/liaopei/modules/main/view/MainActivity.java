package com.zm.liaopei.modules.main.view;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.LogUtils;
import com.zm.liaopei.R;
import com.zm.liaopei.constants.Constants;
import com.zm.liaopei.constants.RouterPathManager;
import com.zm.liaopei.base.BaseMvpActivity;
import com.zm.liaopei.databinding.ActivityMainBinding;
import com.zm.liaopei.modules.main.TabViewFragmentFactory;
import com.zm.liaopei.modules.main.contract.MainActContract;
import com.zm.liaopei.modules.main.presenter.MainActPresenter;
import com.zm.liaopei.listenter.NoDoubleClickListener;
import com.zm.liaopei.widgets.TabView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * 主页，接应fragment
 */
@Route(path = RouterPathManager.Main.MAINACTIVITY)
public class MainActivity extends BaseMvpActivity<ActivityMainBinding, MainActPresenter>implements MainActContract {

    private List<Integer> tabIndexList = Arrays.asList(Constants.CONST_ZERO, Constants.CONST_ONE, Constants.CONST_TWO, Constants.CONST_THREE);
    private List<TabView> mTabViews = new ArrayList<>();

    @Override
    protected MainActPresenter initPresenter() {
        ARouter.getInstance().inject(this);
        return new MainActPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        initViewPage();
    }

    private void initViewPage() {
        binding.vpMainPage.setOffscreenPageLimit(tabIndexList.size());
        binding.vpMainPage.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager(),FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return TabViewFragmentFactory.getTabViewFragment(tabIndexList.get(position));
            }

            @Override
            public int getCount() {
                return tabIndexList.size();
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                Fragment fragment = (Fragment) super.instantiateItem(container, position);
                TabViewFragmentFactory.addFragment(tabIndexList.get(position), fragment);
                return fragment;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                TabViewFragmentFactory.removeFragment(tabIndexList.get(position));
                super.destroyItem(container, position, object);
            }
        });
        binding.vpMainPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                LogUtils.i("onPageSelected: " + position);
                doSelectTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        binding.tabviewMain.setOnClickListener(noDoubleClickListener);
        binding.tabviewMain.setIconResource(R.mipmap.home_un_select, R.mipmap.home_un_select, getResources().getString(R.string.home_str));

        binding.tabviewMoment.setOnClickListener(noDoubleClickListener);
        binding.tabviewMoment.setIconResource(R.mipmap.discover_un_select, R.mipmap.discover_select, getResources().getString(R.string.radios_str));

        binding.tabviewMessage.setOnClickListener(noDoubleClickListener);
        binding.tabviewMessage.setIconResource(R.mipmap.message_un_select, R.mipmap.message_select, getResources().getString(R.string.message_str));

        binding.tabviewMine.setOnClickListener(noDoubleClickListener);
        binding.tabviewMine.setIconResource(R.mipmap.me_un_select, R.mipmap.me_select, getResources().getString(R.string.mine_str));

        mTabViews.add(binding.tabviewMain);
        mTabViews.add( binding.tabviewMoment);
        mTabViews.add(binding.tabviewMessage);
        mTabViews.add(binding.tabviewMine);

        // 默认选中第一个tab
        doSelectTab(0);

    }

    private void doSelectTab(int position) {
        for (int i = 0; i < mTabViews.size(); i++) {
            TabView tabView = mTabViews.get(i);
            if (i == position) {
                tabView.setSelected(true);
            } else {
                tabView.setSelected(false);
            }
        }
    }
    NoDoubleClickListener noDoubleClickListener=new NoDoubleClickListener() {
        @Override
        public void onNoDoubleClick(View v) {
            TabView tabView = (TabView) v;
            int index = mTabViews.indexOf(tabView);
            doSelectTab(index);
            for (int i = 0; i < mTabViews.size(); i++) {
                if (tabView.getId() == mTabViews.get(i).getId()) {
                   binding.vpMainPage.setCurrentItem(i, false);
                    break;
                }
            }
        }
    };
}