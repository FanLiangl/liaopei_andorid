package com.zm.liaopei.modules.login.activity;

import android.os.Bundle;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.zm.liaopei.R;
import com.zm.liaopei.base.BaseMvpActivity;
import com.zm.liaopei.constants.RouterPathManager;
import com.zm.liaopei.databinding.LoginActivityBinding;
import com.zm.liaopei.modules.login.contract.LoginContract;
import com.zm.liaopei.modules.login.presenter.LoginPresenter;
/**
 * @author fanliangliang
 * @description:登录页面
 * @date : 2021/4/28 12:18
 */
@Route(path = RouterPathManager.LoginStart.LOGIN__ACTIVITY)
public class LoginActivity extends BaseMvpActivity<LoginActivityBinding, LoginPresenter>implements LoginContract{
    @Override
    protected LoginPresenter initPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.login_activity;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }
}
