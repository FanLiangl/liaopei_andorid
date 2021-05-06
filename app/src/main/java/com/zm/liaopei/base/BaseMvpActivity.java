package com.zm.liaopei.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.WindowManager;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.gyf.barlibrary.ImmersionBar;
import com.zm.utils.AppManager;

import org.greenrobot.eventbus.EventBus;
/**
 * @author fanliangliang
 * @description:定义一个抽象Activity基类，可以抽象一些基础方法
 * @date : 2021/4/26 10:47
 */
public abstract class BaseMvpActivity <VDB extends ViewDataBinding, P extends BasePresenter> extends AppCompatActivity implements BaseView{
    /**
     * p类 用来处理一些业务操作逻辑
     */
    protected P presenter;
    protected VDB binding;
    private ImmersionBar mImmersionBar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppManager.getAppManager().addActivity(this);
        presenter = initPresenter();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        if (presenter != null) {
            presenter.onAttach(this);
        }
        if (isUseDataBinding()) {
            initDataBinding(getLayoutInflater());
            setContentView(binding.getRoot());
        } else {
            int layoutId = getLayoutId();
            if (layoutId != 0) {
                setContentView(getLayoutId());
            }
        }
        initImmersionBar();
        setStatusBarFontColor(setDarkMode());

        initView(savedInstanceState);

        if (presenter != null) {
            presenter.onCreate();
        }
        if (openEventBus()) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (presenter != null) {
            presenter.onResume();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (presenter!=null){
            presenter.onStop();
        }
    }

    @Override
    protected void onDestroy() {
        if (presenter !=null){
            presenter.onDestroy();
        }
        AppManager.removeActivity(this);
        if (openEventBus()) {
            EventBus.getDefault().unregister(this);
        }
        if (mImmersionBar != null) {
            mImmersionBar.destroy();
        }

        super.onDestroy();
    }

    /**
     * 返回一个presenter
     * @return
     */
    protected abstract P initPresenter();

    /**
     * 获取layout
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 初始组件
     * @param savedInstanceState
     */
    protected abstract void initView(Bundle savedInstanceState);
    /**
     * 是否使用DataBinding
     *
     * @return true 使用 false 不使用用
     */
    protected boolean isUseDataBinding() {
        return true;
    }

    /**
     * 在BaseActivity里初始化
     */
    private void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.fitsSystemWindows(false).statusBarColor("#00000000").init();
    }

    /**
     * 绑定组件
     * @param layoutInflater
     */
    private void initDataBinding(LayoutInflater layoutInflater) {
        int layoutId = getLayoutId();
        binding = DataBindingUtil.inflate(layoutInflater, layoutId, null, false);
    }

    /**
     * 设置状态栏字体颜色方法
     * isDarkMode: 是否深色字体
     */
    protected void setStatusBarFontColor(boolean isDarkMode) {
        if (ImmersionBar.isSupportStatusBarDarkFont()) {
            mImmersionBar.statusBarDarkFont(isDarkMode).init();
        }
    }

    /**
     * @return true 黑色字体  false 白色字体
     */
    protected boolean setDarkMode() {
        return true;
    }


    /**
     * 是否开启EventBus
     * @return true 开启 false 关闭
     */
    protected boolean openEventBus() {
        return false;
    }

}
