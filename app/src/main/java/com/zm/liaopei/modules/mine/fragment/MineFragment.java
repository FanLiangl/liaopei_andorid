package com.zm.liaopei.modules.mine.fragment;

import android.os.Bundle;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.zm.liaopei.R;
import com.zm.liaopei.constants.RouterPathManager;
import com.zm.liaopei.base.BaseMvpFragment;
import com.zm.liaopei.databinding.MainFragmentBinding;
import com.zm.liaopei.modules.mine.contract.MineContract;
import com.zm.liaopei.modules.mine.presenter.MinePresenter;
/**
 * @author fanliangliang
 * @description:个人页面fragment
 * @date : 2021/4/26 16:29
 */
@Route(path = RouterPathManager.Mine.MINE_FRAGMENT)
public class MineFragment extends BaseMvpFragment<MainFragmentBinding, MinePresenter>implements MineContract {
    @Override
    protected MinePresenter initPresenter() {
        ARouter.getInstance().inject(this);
        return new MinePresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.mine_fragment;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {


    }

    @Override
    protected void initData() {

    }
}
