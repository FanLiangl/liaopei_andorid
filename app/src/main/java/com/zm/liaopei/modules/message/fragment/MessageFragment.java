package com.zm.liaopei.modules.message.fragment;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.zm.liaopei.R;
import com.zm.liaopei.constants.RouterPathManager;
import com.zm.liaopei.base.BaseMvpFragment;
import com.zm.liaopei.databinding.MainFragmentBinding;
import com.zm.liaopei.modules.message.contract.MessageContract;
import com.zm.liaopei.modules.message.presenter.MessagePresenter;
/**
 * @author fanliangliang
 * @description:消息fragment
 * @date : 2021/4/26 16:29
 */
@Route(path = RouterPathManager.Message.MESSAGE_FRAGMENT)
public class MessageFragment extends BaseMvpFragment<MainFragmentBinding, MessagePresenter> implements MessageContract{
    @Override
    protected MessagePresenter initPresenter() {
        ARouter.getInstance().inject(this);
        return new MessagePresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.message_fragment;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {

    }
}
