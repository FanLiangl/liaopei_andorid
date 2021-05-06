package com.zm.liaopei.modules.main.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.zm.liaopei.R;
import com.zm.liaopei.constants.RouterPathManager;
import com.zm.liaopei.base.BaseMvpFragment;
import com.zm.liaopei.databinding.MainFragmentBinding;
import com.zm.liaopei.modules.main.contract.MainFraContract;
import com.zm.liaopei.modules.main.presenter.MainFragPresenter;
import java.util.Arrays;
import java.util.List;

/**
 * @author fanliangliang
 * @description:主页fragment
 * @date : 2021/4/26 16:28
 */
@Route(path = RouterPathManager.Main.MAIN_FRAGMENT_PATH)
public class MainFragment extends BaseMvpFragment <MainFragmentBinding, MainFragPresenter>implements MainFraContract {
    private static final String HOT_STR="热门";
    private static final String NEW_PEOPLE="新人";
    private static final String AUTHENTICATION="认证";
    /**
     * 男性看到的首页标签
     */
    private List<String> maleTitles= Arrays.asList(HOT_STR,NEW_PEOPLE,AUTHENTICATION);



    @Override
    protected MainFragPresenter initPresenter() {
        return new MainFragPresenter();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.main_fragment;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        if (mTitles!=null){
            binding.viewpage.setOffscreenPageLimit(mTitles.size());
            binding.viewpage.setAdapter(new FragmentPagerAdapter(getChildFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
                @NonNull
                @Override
                public Fragment getItem(int position) {
                    return null;
                }

                @Override
                public int getCount() {
                    return 0;
                }
            });
        }


    }

    @Override
    protected void initData() {

    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onFail(String message) {

    }

    @Override
    protected List<String> initTitleList() {
        return maleTitles;
    }
}
