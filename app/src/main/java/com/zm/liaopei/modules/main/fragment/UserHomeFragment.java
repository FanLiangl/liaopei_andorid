package com.zm.liaopei.modules.main.fragment;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.zm.liaopei.R;
import com.zm.liaopei.base.BaseMvpFragment;
import com.zm.liaopei.constants.RouterPathManager;
import com.zm.liaopei.databinding.UserHomeFragmentBinding;
import com.zm.liaopei.modules.main.contract.UserHomeFragmentContract;
import com.zm.liaopei.modules.main.presenter.UserHomeFragmentPresenter;

/**
 * @author fanliangliang
 * @description:个人主页主页fragment
 * @date : 2021/4/29 9:58
 */
@Route(path = RouterPathManager.Main.USER_HOME_FRAGMENT)
public class UserHomeFragment extends BaseMvpFragment<UserHomeFragmentBinding, UserHomeFragmentPresenter> implements UserHomeFragmentContract{
    @Override
    protected UserHomeFragmentPresenter initPresenter() {
        return new UserHomeFragmentPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.user_home_fragment;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {

    }
}
