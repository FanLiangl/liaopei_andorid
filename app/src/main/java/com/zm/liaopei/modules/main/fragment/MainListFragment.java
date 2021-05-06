package com.zm.liaopei.modules.main.fragment;

import android.os.Bundle;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.zm.liaopei.R;
import com.zm.liaopei.constants.RouterPathManager;
import com.zm.liaopei.base.BaseMvpFragment;
import com.zm.liaopei.databinding.MainListFragmentBinding;
import com.zm.liaopei.modules.main.contract.MainListContract;
import com.zm.liaopei.modules.main.presenter.MainListPresenter;
/**
 * @author fanliangliang
 * @description:首页列表头部标签的fragment
 * @date : 2021/4/27 17:46
 */
@Route(path = RouterPathManager.Main.MAIN_LISTFRAGMENT_PATH)
public class MainListFragment extends BaseMvpFragment<MainListFragmentBinding, MainListPresenter> implements MainListContract{
    @Override
    protected MainListPresenter initPresenter() {
        return new MainListPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.main_list_fragment;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {

    }
}
