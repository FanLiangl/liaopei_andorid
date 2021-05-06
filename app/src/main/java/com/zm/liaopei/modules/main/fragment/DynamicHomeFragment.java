package com.zm.liaopei.modules.main.fragment;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.zm.liaopei.R;
import com.zm.liaopei.base.BaseMvpFragment;
import com.zm.liaopei.constants.RouterPathManager;
import com.zm.liaopei.databinding.DynamicHomeFragmentBinding;
import com.zm.liaopei.modules.main.contract.DynamicContract;
import com.zm.liaopei.modules.main.presenter.DynamicPresenter;

/**
 * @author fanliangliang
 * @description:
 * @date : 2021/4/29 14:12
 */
@Route(path = RouterPathManager.Main.DYNAMIC_HOME_FRAGMENT)
public class DynamicHomeFragment extends BaseMvpFragment<DynamicHomeFragmentBinding, DynamicPresenter> implements DynamicContract {
    @Override
    protected DynamicPresenter initPresenter() {
        return new DynamicPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.dynamic_home_fragment;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {

    }
}
