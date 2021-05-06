package com.zm.liaopei.modules.login.activity;

import android.os.Bundle;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.zm.liaopei.R;
import com.zm.liaopei.base.BaseMvpActivity;
import com.zm.liaopei.constants.RouterPathManager;
import com.zm.liaopei.databinding.StartUpActivityBinding;
import com.zm.liaopei.modules.login.contract.StartUpContract;
import com.zm.liaopei.modules.login.presenter.StartUpPresenter;
/**
 * @author fanliangliang
 * @description:启动页面
 * @date : 2021/4/28 12:02
 */
@Route(path = RouterPathManager.LoginStart.START_UP_ACTIVITY)
public class StartUpActivity extends BaseMvpActivity<StartUpActivityBinding, StartUpPresenter> implements StartUpContract{
    @Override
    protected StartUpPresenter initPresenter() {
        return new StartUpPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.start_up_activity;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }
}
