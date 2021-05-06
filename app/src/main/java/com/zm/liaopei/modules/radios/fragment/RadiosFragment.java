package com.zm.liaopei.modules.radios.fragment;

import android.os.Bundle;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.zm.liaopei.R;
import com.zm.liaopei.constants.RouterPathManager;
import com.zm.liaopei.base.BaseMvpFragment;
import com.zm.liaopei.databinding.RadiosFragmentBinding;
import com.zm.liaopei.modules.radios.contract.RadiosContract;
import com.zm.liaopei.modules.radios.presenter.RadiosPresenter;

/**
 * @author fanliangliang
 * @description:电台fragment
 * @date : 2021/4/26 16:29
 */
@Route(path = RouterPathManager.Radios.RADIOS_FRAGMENT)
public class RadiosFragment extends BaseMvpFragment<RadiosFragmentBinding, RadiosPresenter>implements RadiosContract{

    @Override
    protected RadiosPresenter initPresenter() {
        ARouter.getInstance().inject(this);
        return new RadiosPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.radios_fragment;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {

    }
}
